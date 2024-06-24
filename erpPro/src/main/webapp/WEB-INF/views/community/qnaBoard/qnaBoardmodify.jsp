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
    </style>
</head>
<body>
    <div class="wrap">
        <%@ include file="../../include/header.jsp" %>
        <div class="content-container" id="qna-mid-box">
        
            <form action="/qna/update" method="post" class="qna-update-form" id="qna-update-form">
                <input type="hidden" name="qnano" value="${qna.qnano}">
                <h1 class="main-title">${qna.qnano}번 게시물</h1>

                <div class="mb-3">
                    <label for="exampleFormControlInput1" class="form-label">작성자</label>
                    <input type="text" class="form-control writer" id="exampleFormControlInput1 writer" placeholder="이름" name="writer"
                        value="${qna.writer}" disabled>
                </div>
                <div class="mb-3">
                    <label for="exampleFormControlInput2" class="form-label">글제목</label>
                    <input type="text" class="form-control title" id="exampleFormControlInput2 title" placeholder="제목" name="title"
                        value="${qna.title}">
                </div>

                <div class="mb-3">
                    <label for="exampleFormControlTextarea1" class="form-label">내용</label>
                    <textarea name="content" class="form-control content" id="exampleFormControlTextarea1 content" rows="10">${qna.content}</textarea>
                </div>

                <div class="btn-group btn-group-lg custom-btn-group" role="group">
                    <button type="button" class="btn btn-warning qnamodybtn" id="qnamodybtn">완료</button>
                    <button type="button" class="btn btn-dark" onclick="list()">목록</button>
                </div>
            </form>
        </div>
        <%@ include file="../../include/footer.jsp" %>
    </div>
<script type="text/javascript">
	window.onload = function() {
		const $modifybtn = document.getElementById('qnamodybtn');
		
		function list(){
			location.href='/qna/list';
		}
		
		$modifybtn.onclick = function(){
			const $qnaupdateform = document.getElementById('qna-update-form');
			if(confirm('게시글을 수정하시겠습니까?')){
				$qnaupdateform.submit();
			}else{
				return;
			}	
		}
	}
	
</script>
</body>
</html>
