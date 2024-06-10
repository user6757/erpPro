<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="../include/static-head.jsp" %>
    <style>
        .wrap {
            margin: 200px auto;
        }
    </style>
 
</head>
<body>
    <%@ include file="../include/header.jsp" %>
    <div class="container wrap">
        <div class="row">
            <div class="offset-md-2 col-md-4">
                <div class="card passidfind-div-box" id="passidfind-div-box" style="width:200%;">
                    <div class="card-header text-white" style="background: #343A40;">
                        <h2>비밀번호찾기</h2>					
                    </div>
                    <div class="card-body">
                       <form action="/member/searchfind_ok" class="findokform" id="findokform" style="margin-bottom: 0;">
                        <table style="cellpadding: 0; cellspacing: 0; margin: 0 auto; width: 100%">
                            <tr>
                                <td style="text-align: left">
                                    <p><strong>회원님의 아이디를 입력하여주세요</strong>&nbsp;&nbsp;&nbsp;<span id="idCheck"></span></p>
                                </td>
                            </tr>
                            <tr>
                                <td><input type="text" name="account" id="account"
                                    class="form-control account" maxlength="14"
                                    required="required" aria-required="true"
                                    style="margin-bottom: 25px; width: 100%; height: 40px; border: 1px solid #d9d9de"
                                    placeholder="최대 14자"></td>
                            </tr>
                            <tr>
                                <td style="text-align: left">
                                    <p><strong>회원님의 이메일을 입력해주세요.</strong>&nbsp;&nbsp;&nbsp;<span id="idCheck"></span></p>
                                </td>
                            </tr>
                            <tr>
                                <td><input type="text" name="email" id="email"
                                    class="form-control email" maxlength="25"
                                    required="required" aria-required="true"
                                    style="margin-bottom: 25px; width: 100%; height: 40px; border: 1px solid #d9d9de"
                                    placeholder="최대 25자"></td>
                            </tr>                                                                                 
                             <tr>
                                <td style="width: 100%; text-align: center; colspan: 2;  margin-top: 24px; padding-top: 12px; border-top: 1px solid #ececec">
                                       <button type="button" class="btn form-control tooltipstered findsubmit_btn" id="findsubmit_btn"
                                        style="background-color: #343A40; margin-top: 0; height: 40px;
                                         color: white; border: 0px solid #f78f24; opacity: 0.8">
                                       	비밀번호찾기
                                       </button>
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 100%; text-align: center; colspan: 2;  margin-top: 24px; padding-top: 12px; border-top: 1px solid #ececec">
                                	<a class="btn form-control tooltipstered" href="/"
                                    style="background-color: #343A40; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">
                                        홈으로</a>
                                </td>
                            </tr>
                        </table>
                    </form>
                    </div>
                </div>
                <div style="width:300px; text-align: center;" class="passwordfind-ok" id="passwordfind-ok">
                	<h2>
                		<div class="passwordText" id="passwordText"></div>
                	</h2>
                </div>     	
            </div>
        </div>
    </div>
    <%@ include file="../include/footer.jsp" %>
    
    <script type="text/javascript">
    window.onload = function() {
    	
    	const $submitbtn = document.getElementById('findsubmit_btn');
    	const $account = document.getElementById('account');
    	const $email = document.getElementById('email');
    	document.getElementById('passwordText').style.display="none";
    	
    	$submitbtn.onclick = function(){
    		
    		if($account.value === null || $account.value ===''){
    			alert('아이디는 필수사항입니다!');
    			$account.value.focus();
    			return;
    		}
    		if($email.value === null || $email.value ===''){
    			alert('이메일은 필수사항입니다!');
    			$email.value.focus();
    			return;
    		}
    		
    		let $findform = document.getElementById('findokform');
    		$findform.action='/member/searchfind_ok';
    		$findform.method = 'POST';
    		$findform.submit();
    			
    	}
    	
    	let passwords = '${password}';
    	
    	if(passwords !== null && passwords !== "" && passwords !== undefined){
    		if(passwords === 'REG-NO'){
    			alert('회원정보를 다시확인해주세요');
    			return;
    		}else{
    			alert('임시비밀번호6자리를 생성하였습니다.');
    			/* $findform.style.display='none'; */
    			document.getElementById('passidfind-div-box').style.display="none";
    			document.getElementById('passwordText').style.display="block";
    			document.getElementById('passwordText').append('회원님의 초기화된 임시비밀번호는 '+passwords+'입니다.');
    			return;
    		}	
    	}
    	
    }
	
</script>
</body>
</html>
