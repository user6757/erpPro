<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="card-title">
    <button onclick="changeContentCus('main', 'mrmain')" style="float: left; font-size: 24px; border: none; background: inherit">
        <i class="fa-solid fa-arrow-left"></i></button>
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
    <input type="hidden" value="${m.mrNo}" name="mrNo" id="mrNo">
</form>
<div class="col-10" style="background:#ffffff; border-radius: 5px; left: 7%">
    <div class="mb-3">
        <label class="form-label">회의실명</label>
        <input type="text" class="form-control" name="mrTitle" value="${m.mrTitle}" disabled>
    </div>
    <div class="mb-3">
        <label class="form-label">최대인원</label>
        <input type="text" class="form-control" name="mrMax" value="${m.mrMax}" disabled>
    </div>
    <div class="mb-3">
        <label class="form-label">예약자명</label>
        <input type="text" class="form-control" name="mrUsers" value="${m.mrUsers}" disabled>
    </div>
    <div class="mb-3">
        <label class="form-label">사용시간</label>
        <input type="text" class="form-control" name="mrTime" value="${m.mrTime}" disabled>
    </div>
    <div class="mb-3">
        <label class="form-label">회의실 설명</label>
        <textarea type="text" class="form-control" name="mrContent">${m.mrContent}</textarea>
    </div>
</div>
<br>
<button id="mr-list" type="button" class="btn btn-primary" onclick="changeContentCus('main', 'mrmain')">목록</button>
<button id="mr-del" type="button" class="btn btn-danger" style="float: right; margin-left: 10px" onclick="deleteM(${m.mrNo})">삭제</button>
<button id="mr-mod" type="button" class="btn btn-warning" style="float: right; color: #ffffff" onclick="changeContentM('modify?mrNo=${m.mrNo}')">수정</button>

<%-- 게시글 상세보기 --%>
<script>
    function deleteM(mrNo) {
        $.ajax({
            type:"post",  //전송타입
            url:"/mrmain/delete",//서버요청대상파일
            data: {
                mrNo : $("#mrNo").val(),
            },
            success: function (data) {
                alert("삭제 성공!");
                changeContentMr(mrNo);
            },
            error: function (xhr, status, e) {
                alert("삭제 실패");
                console.log("xhr", xhr);
                console.log("error", e);
                console.log("status", status);
            }
        });
    }
</script>

