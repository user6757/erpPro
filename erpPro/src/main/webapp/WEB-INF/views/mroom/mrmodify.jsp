<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	    <!-- Page Heading 회의실관리 수정-->
	    <div class="d-sm-flex align-items-center justify-content-between mb-4" style="margin-left: 15px;">
	        <div class="border-bottom-1">
	            <h1 class="h3 mb-2 text-gray-700">회의실 수정</h1>
	        </div>
	    </div>
	</div>
	<!-- 구분 선 속성-->
	<hr class="sidebar-divider d-none d-md-block">
	<div style="background:#ffffff; border-radius: 5px; margin-top: 30px">
	    <div class="default">
	        <form action="/mroom/update" name="signup" id="mr-for" method="post" style="margin-bottom: 0;">
	            <div style="cellpadding: 0; cellspacing: 0; margin: 0 auto; width: 80%">
	                <input type="hidden" value="${mrDTO.mrNo}" name="mrNo" id="mrNo">
	                <div>
	                    <div class="input-group input-group-lg" style="margin-bottom: 30px">
	                        <span class="input-group-text" id="inputGroup-sizing-lg">회의실 명:</span>
	                        <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg" readonly
	                               name="mrTitle" value="${mrDTO.mrTitle}">
	                    </div>
	                </div>
	                <div>
	                    <div class="input-group input-group-lg" style="margin-bottom: 30px">
	                        <span class="input-group-text" id="inputGroup-sizing-lg1">최대 인원:</span>
	                        <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg" readonly
	                               name="mrMax" value="${mrDTO.mrMax}">
	                    </div>
	                </div>
	                <div>
	                    <div class="input-group input-group-lg" style="margin-bottom: 30px">
	                        <span class="input-group-text" id="inputGroup-sizing-lg2">예약자 명:</span>
	                        <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg" readonly
	                               name="mrUsers" value="${mrDTO.mrUsers}">
	                    </div>
	                </div>
	                <div style="text-align: left; margin-bottom: 1.5em">
	                    <div class="mb-5">
	                        <div class="input-group" style="margin-bottom: 30px">
	                            <span class="input-group-text">사용시간:</span>
	                            <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg" readonly
	                                   name="mrTime" value="${mrDTO.mrTime}">
	                        </div>
	                    </div>
	                </div>
	                <div style="text-align: left; margin-bottom: 1.5em">
	                    <div class="mb-5">
	                        <div class="input-group" style="margin-bottom: 30px">
	                            <span class="input-group-text" id="mr-con">회의실 설명:</span>
	                            <textarea id="mrContent" name="mrContent" class="form-control" aria-label="With textarea" rows="5" placeholder="사용하실 품목들을 입력해주세요.">${mrDTO.mrContent}</textarea>
	                        </div>
	                    </div>
	                </div>
	                <div>
	                    <button id="mr-save" style="float: right" type="button" onclick="modifyM(${mrDTO.mrNo})" class="btn btn-primary">저장</button>
	                    <button type="button" class="btn btn-primary" onclick="mroomlistpath()">목록</button>
	                </div>
	            </div>
	        </form>
	    </div>
	</div>
<%@ include file="../include/footer.jsp" %>
</body>
<script>
    // 게시물 등록 입력값 검증 함수
    function mrValidateFormValue() {
        //용 입력 태그
        const $mrCon = $("#mr-con");
            // document.getElementById('mr-con');
        let flag = false; // 입력 제대로 하면 true로 변경

        console.log('c: ', $mrCon);

        if ($mrCon.value.trim() === '') {
            alert('내용은 필수 입니다.');
        } else {
            flag = true;
        }

        console.log('flag: ', flag);

        return flag;
    }

    // 게시물 입력값 검증
    const $mrSave = $("#mr-save");
        // document.getElementById('mr-save');

    $mrSave.onclick = e => {
        // 입력값을 제대로 채우지 않았는지 확인
        if (!mrValidateFormValue()) {
            return;
        }

        // 필수 입력값을 잘 채웠으면 폼을 서브밋한다
        const $mrFor = document.getElementById('mr-for');
        $mrFor.submit();
    };

    function mroomlistpath(){
    	href.location='/mroom/mrlist';
    }

    function modifyM(mrNo) {
        $.ajax({
            type:"post",  //전송타입
            url:"/mroom/update",//서버요청대상파일
            data: {
                mrNo : $("#mrNo").val(),
                mrContent : $("#mrContent").val()
            },
            success: function (data, status, xhr) {
                console.log(data);
                alert("수정 성공!");
                window.location.href='/mroom/mrdetail?mrNo='+${mrDTO.mrNo};
            },
            error: function (xhr, status, e) {
                alert("수정 실패");
                console.log("xhr", xhr);
                console.log("error", e);
                console.log("status", status);
            }
        });
    }
</script>



