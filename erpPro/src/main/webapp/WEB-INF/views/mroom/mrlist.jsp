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
	<!-- Begin Page Content 오른쪽 가운데 안에 들어가는 내용들의 전체 박스-->
	<!-- Page Heading 회의실관리 목록 제목 설정-->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
	    <div class="border-bottom-1">
	        <h1 class="h3 mb-2 text-gray-700">회의실 목록</h1>
	    </div>
	</div>
	<!-- 구분 선 속성-->
	<hr class="sidebar-divider d-none d-md-block">
	
	<div class="input-group mb-4">
	    <form action="list" method="get">
	
	        <select class="form-select" name="type" id="search-type">
	            <option value="title">제목</option>
	              <option value="content">내용</option> 
	               <option value="writer">작성자</option>
	               <option value="tc">제목+내용</option> 
	        </select>
	        <br>
	
	        <input style="width: 100%; padding: 10px 20px; margin: 5px 0; box-sizing: border-box;" type="text" class="form-control" name="keyword" placeholder="입력해주세요!" value="${s.keyword}">
	
	        <div class="input-group-append">
	            <button class="btn btn-primary" type="submit">
	                <i class="fas fa-search"></i>
	            </button>
	        </div>
	    </form>
	</div>
	
	<!-- Content Row 회의실관리 목록 내용 담고있는 전체 박스-->
	<div class="row">
	    <!-- Earnings (Monthly) Card Example -->
	    <div class="col-xl-12">
	        <div class="card border-left-primary shadow h-100 py-2">
	            <div class="card-body">
	                <div class="row no-gutters align-items-center">
	                    <div class="col mr-2">
	                        <div class="f-s text-xs font-weight-bold text-primary text-uppercase mb-1">
	                            <table class="table" style="margin-bottom: 5%">
	                                <thead>
	                                <tr>
	                                    <th scope="col">번호</th>
	                                    <th scope="col">회의실명</th>
	                                    <th scope="col">최대인원</th>
	                                    <th scope="col">예약자명</th>
	                                    <th scope="col">사용시간</th>
	                                    <th scope="col">작성시간</th>
	                                </tr>
	                                </thead>
	                                <tbody class="table-group-divider">
	                                <c:forEach var="m" items="${mlist}">
	                                    <tr>
	                                        <td>${m.mrNo}</td>
	                                        <td><a href="/mroom/mrdetail?mrNo=${m.mrNo }">
	                                                ${m.mrTitle}
	                                                <%--                                                ${m.shortTitle}--%>
	                                            <%-- <c:if test="${m.mrNewArticle}">
	                                                <span class="badge rounded-pill bg-danger">new</span>
	                                            </c:if></a> --%>
	                                        </td>
	                                        <td>${m.mrMax}</td>
	                                        <td>${m.mrUsers}</td>
	                                        <td>${m.mrTime}</td>
	                                        <td>${m.mrRegDate}</td>
	                                    </tr>
	                                </c:forEach>
	                                <%--                                    <tr>--%>
	                                <%--                                        <th scope="row">1</th> mrNo - 글 작성 번호--%>
	                                <%--                                        <td><a href="#" onclick="changeContentCus('mrmain', 'mrdetail')">A 회의실</a></td>--%>
	                                <%--                                        <td>5명</td>--%>
	                                <%--                                        <td>변의준</td>--%>
	                                <%--                                        <td>사용 시간</td>--%>
	                                <%--                                    </tr>--%>
	                                </tbody>
	                            </table>
	                            <nav aria-label="Page navigation example">
	                                <button type="button" class="btn btn-primary float-right clearfix" onclick="mrwritepath()">예약</button>
	                                <ul class="pagination justify-content-center">
	                                    <c:if test="${pm.prev}">
	                                        <li class="page-item">
	                                            <a class="page-link" href="/mrmain/list?pageNum=${pm.beginPage - 1}&amount=${pm.page.amount}&type=${s.type}&keyword=${s.keyword}">prev</a>
	                                        </li>
	                                    </c:if>
	
	                                    <c:forEach var="n" begin="${pm.beginPage}" end="${pm.endPage}" step="1">
	                                        <li data-page-num="${n}" class="page-item">
	                                            <a class="page-link" href="/mrmain/list?pageNum=${n}&amount=${pm.page.amount}&type=${s.type}&keyword=${s.keyword}">${n}</a>
	                                        </li>
	                                    </c:forEach>
	
	                                    <c:if test="${pm.next}">
	                                        <li class="page-item">
	                                            <a class="page-link" href="/main/mrmain?pageNum=${pm.endPage + 1}&amount=${pm.page.amount}&type=${s.type}&keyword=${s.keyword}">next</a>
	                                        </li>
	                                    </c:if>
	                                </ul>
	                            </nav>
	                            
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
	
	<%@ include file="../include/footer.jsp" %>
	</body>
	<script type="text/javascript">
		function mrwritepath(){
			window.location.href='/mroom/mrwrite';
		}
	</script>
</html>