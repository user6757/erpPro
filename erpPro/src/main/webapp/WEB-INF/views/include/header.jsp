<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- header -->
<header>
    <div class="inner-header">
        <h1 class="logo">
            <a href="/">
                <img src="/resources/img/logo.png" alt="로고이미지">
            </a>
        </h1>
        <h2 class="intro-text">어서오세요.
            <c:choose>
            	<c:when test="${userid != null}">${userid}님</c:when>
            	<c:when test="${adminid != null}">${adminid}님</c:when>
            </c:choose>
        </h2>
        <a href="#" class="menu-open">
            <span class="menu-txt">MENU</span>
            <span class="lnr lnr-menu"></span>
        </a>
    </div>

    <nav class="gnb">
        <a href="#" class="close">
            <span class="lnr lnr-cross"></span>
        </a>
        <ul>
            <li><a href="/">Home</a></li>
            <li><a href="#">About</a></li>
            <li><a href="/free/list">Free</a></li>
            <li><a href="/qna/list">Q&A</a></li>
            <li><a href="/mroom/mrlist">Room</a></li>	<!-- 회의실 -->
            <li><a href="#">Contact</a></li>

            <c:if test="${userid == null && adminid == null}">
                <li><a href="/member/membership">Sign Up</a></li>
                <li><a href="/member/login">Sign In</a></li>
            </c:if>
            
            <c:if test="${userid != null || adminid != null}">
                <li><a href="#">My Page</a></li>
                <li><a href="/member/sign-out">Sign Out</a></li>
            </c:if>
        </ul>
    </nav>
</header>
<!-- //header -->