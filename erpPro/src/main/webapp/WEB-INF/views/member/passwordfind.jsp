<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="../include/static-head.jsp" %>
    <style>
        .wrap {
            margin: 200px auto;
        }
        .c-red {
            color: #e00;
        }
        .c-blue {
            color: rgb(25, 236, 120);
        }
    </style>
</head>
    <body class="bg-gradient-primary">
		<%@ include file="../include/header.jsp" %>
        <div class="container">
            <!--Outet row-->
            <div class="row justify-content-center">
                <!--passwordfind-->
                <div class="col-xl-6 col-lg-12 col-md-9">
                    <div class="card o-hidden border-0 shadow-lg my-5">
                        <div class="card-body p-0">
                            <!-- Nested Row within Card Body -->
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="p-5">
                                        <div class="text-center">
                                            <h1 class="h4 text-gray-900 mb-4">비밀번호 찾기</h1>
                                        </div>
                                        <form class="user">
                                            <div class="form-group">
                                                <input type="text" class="form-control form-control-user" name="user_id"
                                                    id="user_id" aria-describedby="emailHelp" placeholder="아이디 입력:">
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <input type="text" class="form-control form-control-user"
                                                        name="user_dep" id="user_dep" placeholder="부서 입력:"
                                                        aria-label="Search" aria-describedby="basic-addon2">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <input type="text" class="form-control form-control-user"
                                                        name="user_rank" id="user_rank" placeholder="직급 입력:"
                                                        aria-label="Search" aria-describedby="basic-addon2">
                                                </div>
                                            </div>
                                            <a href="index.html" class="btn btn-primary btn-user btn-block">
                                                비밀번호 찾기
                                            </a>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--/passwordfind-->
            </div>
        </div>
    </body>
    <script type="text/javascript">
    
    </script>
</html>