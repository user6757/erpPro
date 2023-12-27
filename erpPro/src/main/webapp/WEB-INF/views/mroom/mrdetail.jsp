<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="../include/static-head.jsp" %>

    <style>
        .board-list {
            width: 70%;
            margin: 230px auto 0;
        }

        .board-list .articles {
            margin: 10px auto 100px;
            border-collapse: collapse;
            font-size: 1.5em;
            border-radius: 10px;
        }

        /* 목록 개수별 보기 스타일 */
        .board-list .amount {
            display: flex;
            /* background: skyblue; */
            justify-content: flex-end;
        }

        .board-list .amount li {
            width: 8%;
            margin-right: 10px;
        }
        .board-list .amount li a {
            width: 100%;
        }

        header {
            background: #222;
            border-bottom: 1px solid #2c2c2c;
        }

        /* pagination style */
        .bottom-section {
            margin-top: -50px;
            margin-bottom: 100px;
            display: flex;
        }

        .bottom-section nav {
            flex: 9;
            display: flex;
            justify-content: center;
        }

        .bottom-section .btn-write {
            flex: 1;
        }

        .pagination-custom a {
            color: #444 !important;
        }

        .pagination-custom li.active a,
        .pagination-custom li:hover a {
            background: #333 !important;
            color: #fff !important;
        }

        /* 검색창 */
        .board-list .top-section {
            display: flex;
            justify-content: space-between;
        }
        .board-list .top-section .search {
            flex: 4;
        }
        .board-list .top-section .amount {
            flex: 4;
        }
        .board-list .top-section .search form {
            display: flex;
        }
        .board-list .top-section .search form #search-type {
            flex: 1;
            margin-right: 10px;
        }
        .board-list .top-section .search form input[name=keyword] {
            flex: 3;
        }

    </style>
</head>

<body>
	<%@ include file="../include/header.jsp" %>
	<br><br><br><br><br><br>
<div class="card-title">

    <!-- Page Heading 회의실관리 상세 제목 설정-->
    <div class="d-sm-flex align-items-center justify-content-between mb-4" style="margin-left: 15px;">
        <div class="border-bottom-1">
            <h1 class="h3 mb-2 text-gray-700">회의실 관리 상세</h1>
        </div>
    </div>
</div>
<!-- 구분 선 속성-->
<hr class="sidebar-divider d-none d-md-block">
<!-- 이미지 속성 -->
<%--<div style="text-align: center; margin-bottom: 0.7em">--%>
<%--    <img src="${pageContext.request.contextPath}/img/mrimg/mr1.jpg" alt="A 회의실"/>--%>
<%--</div>--%>
<%--<div style="text-align: center; margin-bottom: 0.7em">--%>
<%--    <img src="${pageContext.request.contextPath}/img/mrimg/mr2.jpg" alt="B 회의실"/>--%>
<%--</div>--%>
<%--<div style="text-align: center; margin-bottom: 0.7em">--%>
<%--    <img src="${pageContext.request.contextPath}/img/mrimg/mr3.png" alt="C 회의실"/>--%>
<%--</div>--%>
<br>
<form action="/mrmain/delete" method="post">
    <input type="hidden" value="${mr.mrNo}" name="mrNo" id="mrNo">
</form>
<div class="col-10" style="background:#ffffff; border-radius: 5px; left: 7%">
    <div class="mb-3">
        <label class="form-label">회의실명</label>
        <input type="text" class="form-control" name="mrTitle" value="${mr.mrTitle}" disabled>
    </div>
    <div class="mb-3">
        <label class="form-label">최대인원</label>
        <input type="text" class="form-control" name="mrMax" value="${mr.mrMax}" disabled>
    </div>
    <div class="mb-3">
        <label class="form-label">예약자명</label>
        <input type="text" class="form-control" name="mrUsers" value="${mr.mrUsers}" disabled>
    </div>
    <div class="mb-3">
        <label class="form-label">사용시간</label>
        <input type="text" class="form-control" name="mrTime" value="${mr.mrTime}" disabled>
    </div>
    <div class="mb-3">
        <label class="form-label">회의실 설명</label>
        <textarea type="text" class="form-control" name="mrContent" disabled>${mr.mrContent}</textarea>
    </div>
</div>
<br>
	<button id="mr-list" type="button" class="btn btn-primary" onclick="javascript:location.href='/mroom/mrlist'">목록</button>
	<button id="mr-del" type="button" class="btn btn-danger" style="float: right; margin-left: 10px" onclick="deleteM(${mr.mrNo})">삭제</button>
	<button id="mr-mod" type="button" class="btn btn-warning" style="float: right; color: #ffffff" onclick="mroomModifypath()">수정</button>
	
	<br><br>
	<%@ include file="../include/footer.jsp" %>
</body>


<%-- 게시글 상세보기 --%>
<script>
     function deleteM(mrNo) {
        $.ajax({
            type:"post",  //전송타입
            url:"/mroom/delete",//서버요청대상파일
            data: {
                mrNo : $("#mrNo").val(),
            },
            success: function (data) {
                alert("삭제 성공!");
                window.location.href='/mroom/mrlist';
            },
            error: function (xhr, status, e) {
                alert("삭제 실패");
                console.log("xhr", xhr);
                console.log("error", e);
                console.log("status", status);
            }
        });
    } 
    
    function mroomModifypath(){
    	window.location.href='/mroom/mrmodify?mrNo=${mr.mrNo}';
    }
</script>