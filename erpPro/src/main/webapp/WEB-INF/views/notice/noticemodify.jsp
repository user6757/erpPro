<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h1 class="m-0 font-weight-bold text-primary" style="text-align: center">공지사항 수정</h1>
    </div>
    <div class="card-body col-10" style="left: 7%">
        <form id="notice-mod-form" action="/notice/modify" class="was-validated" method="post" autocomplete="off">
            <input id="notice-no" type="hidden" name="noticeNo" value="${noticeDTO.noticeNo}">
            <select class="form-select float-left" name="type" id="notice-type"
                    style="margin-right: 2em; height: 38px">
                <option value="all">전체 공지</option>
            </select>

            <%--제목 입력--%>
            <div class="mb-3">
                <label for="notice-title" class="form-label">제목 입력</label>
                <input name="noticeTitle" type="text" class="form-control" id="notice-title"
                       placeholder="제목을 입력하세요" required
                       value="${noticeDTO.noticeTitle}">
                <div class="invalid-feedback">
                    제목은 필수사항입니다.
                </div>
            </div>

            <%--내용 입력--%>
            <div class="mb-3">
                <label for="notice-content" class="form-label">공지사항 입력</label>
                <textarea name="noticeContent" class="form-control" id="notice-content" placeholder="내용을 입력하세요"
                          required
                          style="height: 300px">${noticeDTO.noticeContent}</textarea>
                <div class="invalid-feedback">
                    내용은 필수사항입니다.
                </div>
            </div>

            <%--첨부파일--%>
            <%--            <div class="mb-3">--%>
            <%--                <input type="file" class="form-control" aria-label="file example" multiple>--%>
            <%--            </div>--%>

            <%--buttons--%>
            <div class="clearfix" style="margin-bottom: 2em">
                <div class="float-left">
                    <button type="button" class="btn btn-primary">목록</button>
                </div>
                <div class="float-right">
                    <button id="notice-mod-btn" type="button" class="btn btn-primary" onclick="modifyN(${noticeDTO.noticeNo})">수정</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script>

    function changeContentN(noticeNo){
        $('#mainContent').children().remove();
        $('#mainContent').load("/notice/noticeview?noticeNo=" + noticeNo);
    }

    function modifyN(noticeNo) {
        $.ajax({
            type:"post",  //전송타입
            url:"/notice/modify",//서버요청대상파일
            data: {
                noticeNo : $("#notice-no").val(),
                noticeTitle : $("#notice-title").val(),
                noticeContent : $("#notice-content").val()
            },
            success: function (data, status, xhr) {
                console.log(data);
                alert("수정 성공!");
                changeContentN(noticeNo);
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