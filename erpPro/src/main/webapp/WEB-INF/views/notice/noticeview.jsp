<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- NoticeContentView_admin -->
<form action="/notice/delete" method="post">
    <input type="hidden" value="${n.noticeNo}">
</form>
<div class="card shadow mb-4" id="noticeview">
    <div class="card-header py-3">
        <h1 class="m-0 font-weight-bold text-primary" style="text-align: center">공지사항</h1>
    </div>
    <div class="col-10" style="left: 7%">
        <div class="card-title">
            <div class="title" style="margin-top: 25px">
                <h2>${n.noticeTitle}</h2>
            </div>
            <div class="subTitle clearfix">
                <div class="float-left">작성자<span style="margin-left: 15px">관리자</span></div>
                <div class="float-left" style="margin-left: 15px">조회수<span style="margin-left: 15px">${n.noticeViewCount}</span></div>
                <div class="float-right">작성일<span style="margin-left: 15px">${n.noticeRegDate}</span></div>
            </div>
        </div>
        <!-- 내용 -->
        <div style="font-size: 1.1em; margin-bottom: 1.5em">
            <p class="notice-content">${n.noticeContent}</p>
        </div>
        <!-- 첨부파일 -->
        <div style="margin-bottom: 2em">
            <div><a href="#" class="uploaded-list"><i class="fa-solid fa-download"></i></a></div>
        </div>
        <!-- 버튼 -->
        <div>
            <div class="clearfix" style="margin-bottom: 2em">
                <div class="float-left">
                    <button id="list-btn" type="button" class="btn btn-primary">목록</button>
                </div>
                <div class="float-right">
                    <button id="mod-btn" onclick="changeContentN('modify?noticeNo=${n.noticeNo}')" type="button" class="btn btn-warning">수정</button>
                    <button id="del-btn" type="button" class="btn btn-danger" onclick="noticeDel(${n.noticeNo})">삭제</button>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    function noticeDel(noticeNo) {
        const ans = confirm("삭제하시겠습니까?");
        if (ans) {
            const query = {"noticeNo" : noticeNo};
            $.ajax({
                url : "notice/delete",
                type : "post",
                data : query,
                success : function(data) {
                    console.log(data);
                    changeContent('notice');
                }
            });
        }
    }
</script>