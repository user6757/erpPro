<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <%@ include file="../../include/static-head.jsp" %>
    <style>
        .write-container {
            width: 50%;
            margin: 200px auto 150px;
            font-size: 1.2em;
        }
        .fileDrop {
            width: 600px;
            height: 200px;
            border: 1px dashed gray;
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 1.5em;
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
        <div class="write-container">
            <form id="write-form" class="write-form" action="/qna/save" method="post" autocomplete="off">

                <div class="mb-3">
                    <label for="writer-input" class="form-label">작성자</label>
                    <input type="text" class="form-control qna-writer" name="writer" id="qna-writer" placeholder="이름" name="writer"
                        maxlength="20">
                </div>
                <div class="mb-3">
                    <label for="title-input" class="form-label">글제목</label>
                    <input type="text" class="form-control qna-title" name="title" id="qna-title" placeholder="제목">
                </div>
                <div class="mb-3">
                    <label for="exampleFormControlTextarea1" class="form-label">내용</label>
                    <textarea name="content" class="form-control qna-content" id="qna-content" rows="10"></textarea>
                </div>

                <!-- 첨부파일 드래그 앤 드롭 영역 -->
                <!-- <div class="form-group">
                    <div class="fileDrop">
                        <span>Drop Here!!</span>
                    </div>
                    <div class="uploadDiv">
                        <input type="file" name="files" id="ajax-file" style="display:none;">
                       
                    </div>
                    업로드된 파일의 썸네일을 보여줄 영역
                    <div class="uploaded-list">
						<input type="text" name="filename" id="filename" value="">
                    </div>
                </div> -->

                <div class="d-grid gap-2">
                    <button id="qna-reg-btn" class="btn btn-dark qna-reg-btn" type="button">글 작성하기</button>
                    <button id="to-list" class="btn btn-warning" type="button">목록으로</button>
                </div>
            </form>
        </div>
        <%@ include file="../../include/footer.jsp" %>
    </div>
    <script>
    window.onload = function() {
    	
    	const $saveBtn = document.getElementById('qna-reg-btn');
        // 게시물 등록 입력값 검증 함수
        function validateFormValue() {
        	
        	const $writer = document.getElementById('qna-writer');
            const $title = document.getElementById('qna-title');
            const $content = document.getElementById('qna-content');
            
            let flag = false;

            if ($writer.value.trim() === '' || $writer.value.trim() === null) {
                alert('작성자는 필수값입니다.');
                return;
            } else if ($title.value.trim() === '' || $title.value.trim() === null) {
                alert('제목은 필수값입니다.');
                return;
            }else if($content.value.trim() ==='' || $content.value.trim() === null){
            	alert('내용은 필수값입니다.');
            	return;
            } 
            else {
                flag = true;
            }
            
            return flag;
        }
        // 게시물 입력값 검증
        $saveBtn.onclick = e => {
            
            if (!validateFormValue()) {
                return;
            }		                    
            const $form = document.getElementById('write-form');
            $form.submit();
        };
        //목록버튼 이벤트
        const $toList = document.getElementById('to-list');
        $toList.onclick = e => {
            location.href = '/qna/list';
        };
    }
    </script>


</body>

</html>