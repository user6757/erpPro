<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <%@ include file="../../include/static-head.jsp" %>

    <style>
        .content-container {
            width: 60%;
            margin: 150px auto;
            position: relative;
        }

        .content-container .main-title {
            font-size: 24px;
            font-weight: 700;
            text-align: center;
            border-bottom: 2px solid rgb(75, 73, 73);
            padding: 0 20px 15px;
            width: fit-content;
            margin: 20px auto 30px;
        }

        .content-container .main-content {
            border: 2px solid #ccc;
            border-radius: 20px;
            padding: 10px 25px;
            font-size: 1.1em;
            text-align: justify;
            min-height: 400px;
        }

        .content-container .custom-btn-group {
            position: absolute;
            bottom: -10%;
            left: 50%;
            transform: translateX(-50%);
        }

        /* 페이지 액티브 기능 */
        .pagination .page-item.p-active a {
            background: #333 !important;
            color: #fff !important;
            cursor: default;
            pointer-events: none;
        }

        .pagination .page-item:hover a {
            background: #888 !important;
            color: #fff !important;
        }


        .uploaded-list {
            display: flex;
        }

        .img-sizing {
            display: block;
            width: 100px;
            height: 100px;
        }
    </style>
</head>

<body>

    <div class="wrap">
        <%@ include file="../../include/header.jsp" %>

        <div class="content-container">

            <h1 class="main-title">${qna.qnano}번 게시물</h1>

            <div class="mb-3">
                <label for="exampleFormControlInput1" class="form-label">작성자</label>
                <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="이름" name="writer"
                    value="${qna.writer}" disabled>
            </div>
            <div class="mb-3">
                <label for="exampleFormControlInput2" class="form-label">글제목</label>
                <input type="text" class="form-control" id="exampleFormControlInput2" placeholder="제목" name="title"
                    value="${qna.title}" disabled>
            </div>
            <div class="mb-3">
                <label for="exampleFormControlTextarea1" class="form-label">내용</label>
                <p class="main-content">
                    ${qna.content}
                </p>
            </div>       
            <!-- 파일 첨부 영역 -->
            <div class="form-group">
                <ul class="uploaded-list"></ul>
            </div>
            <div class="btn-group btn-group-lg custom-btn-group" role="group">
				<c:if test="${userid != null && userid !=''}">
					<button id="mod-btn" type="button" class="btn btn-warning">수정</button>
                  	<button id="del-btn" type="button" class="btn btn-danger">삭제</button>
				</c:if>
                
                <button id="list-btn" type="button" class="btn btn-dark">목록</button>
            </div>
            <!-- 댓글 영역 -->
            <div id="replies" class="row">
                <div class="offset-md-1 col-md-10">
                    <!-- 댓글 쓰기 영역 -->
                    <div class="card">
                        <div class="card-body">

                            <c:if test="${empty userid}">
                                <a href="/member/sign-in">댓글은 로그인 후 작성 가능합니다.</a>
                            </c:if>

                            <c:if test="${not empty userid}">
                                <div class="row">
                                    <div class="col-md-9">
                                        <div class="form-group">
                                            <label for="newReplyText" hidden>댓글 내용</label>
                                            <textarea rows="3" id="newReplyText" name="replyText" class="form-control"
                                                placeholder="댓글을 입력해주세요."></textarea>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label for="newReplyWriter" hidden>댓글 작성자</label>
                                            <input id="newReplyWriter" name="replyWriter" type="text"
                                                value="${loginUser.name}" class="form-control" placeholder="작성자 이름"
                                                readonly style="margin-bottom: 6px;">
                                            <button id="replyAddBtn" type="button"
                                                class="btn btn-dark form-control">등록</button>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </div> <!-- end reply write -->
                    <!--댓글 내용 영역-->
                    <div class="card">
                        <!-- 댓글 내용 헤더 -->
                        <div class="card-header text-white m-0" style="background: #343A40;">
                            <div class="float-left">댓글 (<span id="replyCnt">0</span>)</div>
                        </div>
                        <!-- 댓글 내용 바디 -->
                        <div id="replyCollapse" class="card">
                            <div id="replyData">
                                <!-- 
								< JS로 댓글 정보 DIV삽입 > 
							-->
                            </div>
                            <!-- 댓글 페이징 영역 -->
                            <ul class="pagination justify-content-center">
                                <!-- 
								< JS로 댓글 페이징 DIV삽입 > 
							-->
                            </ul>
                        </div>
                    </div> <!-- end reply content -->
                </div>
            </div> <!-- end replies row -->
            <!-- 댓글 수정 모달 -->
            <div class="modal fade bd-example-modal-lg" id="replyModifyModal">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <!-- Modal Header -->
                        <div class="modal-header" style="background: #343A40; color: white;">
                            <h4 class="modal-title">댓글 수정하기</h4>
                            <button type="button" class="close text-white" data-bs-dismiss="modal">X</button>
                        </div>
                        <!-- Modal body -->
                        <div class="modal-body">
                            <div class="form-group">
                                <input id="modReplyId" type="hidden">
                                <label for="modReplyText" hidden>댓글내용</label>
                                <textarea id="modReplyText" class="form-control" placeholder="수정할 댓글 내용을 입력하세요."
                                    rows="3"></textarea>
                            </div>
                        </div>
                        <!-- Modal footer -->
                        <div class="modal-footer">
                            <button id="replyModBtn" type="button" class="btn btn-dark">수정</button>
                            <button id="modal-close" type="button" class="btn btn-danger"
                                data-bs-dismiss="modal">닫기</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@ include file="../../include/footer.jsp" %>
    </div>

    <!-- 게시글 상세보기 관련 script -->
    <script>
        const $modBtn = document.getElementById('mod-btn');
        const $delBtn = document.getElementById('del-btn');
        const $listBtn = document.getElementById('list-btn');

        if ($modBtn !== null) {
            //수정버튼
            $modBtn.onclick = e => {
                location.href = '/qna/modify?qnano=${qna.qnano}';
            };
        }
        if ($delBtn !== null) {
            //삭제버튼
            $delBtn.onclick = e => {
                if (!confirm('정말 삭제하시겠습니까?')) {
                    return;
                }
                location.href = '/qna/delete?seq=${qna.qnano}';
            };
        }
        //목록버튼
        $listBtn.onclick = e => {
            location.href = '/qna/list';
        };
    </script>

</body>

</html>