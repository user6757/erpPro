<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="../../include/static-head.jsp" %>
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
    <div class="wrap">
        <%@ include file="../../include/header.jsp" %>
		<h2>Q&A 게시판</h2>
        <div class="board-list">

            <div class="top-section">
                <!-- 검색창 영역 -->
                <div class="search">
                    <form action="/qna/list" method="get">
                        
                        <select class="form-select" name="type" id="search-type">
                            <option value="title">제목</option>
                            <option value="content">내용</option>
                            <option value="writer">작성자</option>
                            <!-- <option value="tc">제목+내용</option> -->
                        </select>

                        <input type="text" class="form-control" name="keyword" value="${search.keyword}">

                        <button class="btn btn-primary" type="submit">
                            <i class="fas fa-search"></i>
                        </button>

                    </form>
                </div>

                <!-- 목록 개수별 보기 영역 -->
                <ul class="amount">
                    <li><a class="btn btn-danger" href="/board/list?amount=10">10</a></li>
                    <li><a class="btn btn-danger" href="/board/list?amount=20">20</a></li>
                    <li><a class="btn btn-danger" href="/board/list?amount=30">30</a></li>
                </ul>
            </div>

            <table class="table table-dark table-striped table-hover articles">
                <tr>
                    <th>번호</th>
                    <th>작성자</th>
                    <th>제목</th>
                    <th>조회수</th>
                    <th>작성시간</th>
                </tr>

                <c:forEach var="qnalist" items="${qna}">
                    <tr>
                        <td><a>${qnalist.qnano}</a></td>
                        <td>${qnalist.writer}</td>
                        <td>
                        ${qnalist.title}
                            <%-- ${b.shortTitle} [${b.replyCount}]
                            <c:if test="${b.newArticle}">
                                <span class="badge rounded-pill bg-danger">new</span>
                            </c:if> --%>
                        </td>
                        <td>${qnalist.qnacnt}</td>
                        <td>${qnalist.strdate}</td>
                    </tr>
                </c:forEach>
            </table>

            <!-- 게시글 목록 하단 영역 -->
            <div class="bottom-section">

                <!-- 페이지 버튼 영역 -->
                <%-- <nav aria-label="Page navigation example">
                    <ul class="pagination pagination-lg pagination-custom">

                       <c:if test="${pm.page > 1}">
                            <li class="page-item"><a class="page-link"
                                    href="/free/list?pageNum=${pm.page - 1}">prev</a></li>
                        </c:if>

                        <c:forEach var="p" begin="${pm.startpage}" end="${pm.endpage}" step="1">
                            <li data-page-num="${p}" class="page-item">
                                <a class="page-link" href="/free/list?page=${p}">${p}</a>
                            </li>
                        </c:forEach>

                        <c:if test="${pm.page < pm.maxpage}">
                            <li class="page-item"><a class="page-link"
                                    href="/free/list?page=${pm.endpage + 1}">next</a></li>
                        </c:if>

                    </ul>
                </nav> --%>

                <!-- 글쓰기 버튼 영역 -->
                <div class="btn-write">
                    <a class="btn btn-outline-danger btn-lg" href="/qna/write">글쓰기</a>
                </div>
            </div>
        </div>


        <%@ include file="../../include/footer.jsp" %>

    </div>

    <script>
    
	    function alertServerMessage() {
	        const msg = '${msg}';
	        // console.log('msg: ', msg);
	
	        if (msg === 'reg-success') {
	            alert('게시물이 정상 등록되었습니다.');
	        }
	    }

        function detailEvent() {
            //상세보기 요청 이벤트
            const $table = document.querySelector(".articles");
            $table.addEventListener('click', e => {
            	console.log('확인!');
                if (!e.target.matches('.articles td')) return;
                let bn = e.target.parentElement.firstElementChild.textContent;
                location.href = '/qna/qnadetail'
                                + "?qnano="+bn;
            });
        }

        //현재 위치한 페이지에 active 스타일 부여하기
        function appendPageActive() {

            // 현재 내가 보고 있는 페이지 넘버
            const curPageNum = '${pm.page}';
            // console.log("현재페이지: ", curPageNum);

            // 페이지 li태그들을 전부 확인해서 
            // 현재 위치한 페이지 넘버와 텍스트컨텐츠가 일치하는
            // li를 찾아서 class active 부여
            const $ul = document.querySelector('.pagination');

            for (let $li of [...$ul.children]) {
                if (curPageNum === $li.dataset.pageNum) {
                    $li.classList.add('active');
                    break;
                }
            }

        }

        // 옵션태그 고정
        function fixSearchOption() {
            const $select = document.getElementById('search-type');

            for (let $opt of [...$select.children]) {
                if ($opt.value === '${search.type}') {
                    $opt.setAttribute('selected', 'selected');
                    break;
                }
            }
        }


        (function () {

            alertServerMessage();
            detailEvent();
            appendPageActive();
            fixSearchOption();

        })(); 

    </script>

</body>

</html>