<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<title>admin 로그인</title>
    <style>
        .wrap {
            margin: 200px auto;
        }
    </style>
    <%@ include file="../include/static-head.jsp" %>
</head>
<body>
    <%@ include file="../include/header.jsp" %>
    <div class="container wrap">
        <div class="row">
            <div class="offset-md-2 col-md-4">
                <div class="card" style="width:200%;">
                    <div class="card-header text-white" style="background: #343A40;">
                        <h2><span style="color: gray;">관리자</span> 로그인</h2>					
                    </div>
                    <div class="card-body">
                        <table style="cellpadding: 0; cellspacing: 0; margin: 0 auto; width: 100%">
                            <tr>
                                <td style="text-align: left">
                                    <p><strong>아이디</strong>&nbsp;&nbsp;&nbsp;<span id="idCheck"></span></p>
                                </td>
                            </tr>
                            <tr>
                                <td><input type="text" name="adminId" id="admin-signId"
                                    class="form-control tooltipstered admin-signId" maxlength="14"
                                    required="required" aria-required="true"
                                    style="margin-bottom: 25px; width: 100%; height: 40px; border: 1px solid #d9d9de"
                                    placeholder="최대 14자"></td>
                            </tr>
                            <tr>
                                <td style="text-align: left">
                                    <p><strong>비밀번호</strong>&nbsp;&nbsp;&nbsp;<span id="pwCheck"></span></p>
                                </td>
                            </tr>
                            <tr>
                                <td><input type="password" size="17" maxlength="20" id="admin-signInPw"
                                    name="adminPw" class="form-control tooltipstered admin-signInPw" 
                                    maxlength="20" required="required" aria-required="true"
                                    style="ime-mode: inactive; margin-bottom: 25px; height: 40px; border: 1px solid #d9d9de"
                                    placeholder="최대 20자"></td>
                            </tr>                            
                            
                            <tr>
                                <td style="padding-top: 10px; text-align: center">
                                    <p><strong>관리자로 로그인하여 여러 유저를 관리합니다.</strong></p>
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 100%; text-align: center; colspan: 2;"><button
                                         class="btn form-control tooltipstered admin-login-btn" id="admin-login-btn"
                                    style="background-color: #343A40; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">로그인</button>
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 100%; text-align: center; colspan: 2; margin-top: 24px; padding-top: 12px; border-top: 1px solid #ececec">
                                    <a class="btn form-control tooltipstered" href="/member/sign-up"
                                    style="cursor: pointer; margin-top: 0; height: 40px; color: white; background-color: gray; border: 0px solid #388E3C; opacity: 0.8">
                                        일반 회원가입</a>
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 100%; text-align: center; colspan: 2; margin-top: 24px; padding-top: 12px; border-top: 1px solid #ececec">
                                    <a class="btn form-control tooltipstered" href="/member/idfind"
                                    style="cursor: pointer; margin-top: 0; height: 40px; color: white; background-color: gray; border: 0px solid #388E3C; opacity: 0.8">
                                        아이디찾기</a>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="../include/footer.jsp" %>
</body>
	<script>
    	const $adminuserid = document.getElementById("admin-signId");
    	const $adminpassword = document.getElementById("admin-signInPw");
    	const adminIdCheck = RegExp(/^[a-zA-Z0-9]{4,14}$/);
    	
    	document.getElementById("admin-login-btn").onclick = function() {
    		if($adminuserid.value=== null || $adminuserid.value===''){
    			alert('관리자 아이디를 입력하여주십시오!');
    			$adminuserid.focus();
    			
    			return;
    		}
    		if(!adminIdCheck.test($adminuserid.value)){
    			alert('관리자 아이디는 영문, 숫자로 4~14자 사이로 작성하여주십시오!');
    			$adminuserid.focus();
    			return;
    		}
    		
    		if($adminpassword.value=== null || $adminpassword.value===''){
    			alert('페스워드를 입력하여주십시오!');
    			$adminpassword.focus();
    			return;
    		}
    		
    		$.ajax({
                type : 'post',
	            url : '/admin/singnIn',
	            data :{
	            	adminId:$adminuserid.value,
	            	adminPw:$adminpassword.value
	            },
	            success:function(result){
	            	if(result === 'N'){
	            		alert('해당유저가 존재하지않습니다.');
	            		$adminuserid.value='';
	            		$adminpassword.value='';
	            		return;
	            	}else if(result ==='O'){
	            		alert('아이디 또는 비밀번호를 확인해주십시오!');
	            		$adminuserid.value='';
	            		$adminpassword.value='';
	            		return;
	            	}else{
	            		window.location.href='/';
	            	}
	            },
	            error:function(xhres){
	            	console.log(xhres);
	            },
	        });
    	
    	};
    	
    </script>
</html>
