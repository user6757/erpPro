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
                <div class="card idfind-div" id="idfind-div" style="width:200%;">
                    <div class="card-header text-white" style="background: #343A40;">
                        <h2>아이디찾기</h2>					
                    </div>
                    <div class="card-body idfind-div">
                        <form action="/member/idsearch" class="searchform" id="searchform" style="margin-bottom: 0;">
                        <table style="cellpadding: 0; cellspacing: 0; margin: 0 auto; width: 100%">
                            <tr>
                                <td style="text-align: left">
                                    <p><strong>이름을 입력해주세요.</strong>&nbsp;&nbsp;&nbsp;<span id="idCheck"></span></p>
                                </td>
                            </tr>
                            <tr>
                                <td><input type="text" name="name" id="name"
                                    class="form-control tooltipstered" maxlength="10"
                                    required="required" aria-required="true"
                                    style="margin-bottom: 25px; width: 100%; height: 40px; border: 1px solid #d9d9de"
                                    placeholder="최대 7자"></td>
                            </tr>
                            <tr>
                                <td style="text-align: left">
                                    <p><strong>이메일을 입력해주세요.</strong>&nbsp;&nbsp;&nbsp;<span id="idCheck"></span></p>
                                </td>
                            </tr>
                            <tr>
                                <td><input type="text" name="email" id="email"
                                    class="form-control tooltipstered" maxlength="25"
                                    required="required" aria-required="true"
                                    style="margin-bottom: 25px; width: 100%; height: 40px; border: 1px solid #d9d9de"
                                    placeholder="최대 25자"></td>
                            </tr>                                                                                 
                            <tr>
                                <td style="width: 100%; text-align: center; colspan: 2;">
                                <button value="아이디찾기" class="btn form-control tooltipstered" id="idfind-btn"
                                    style="background-color: #343A40; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8"></button>
                                </td>
                            </tr>
                             <tr>
                                <td style="width: 100%; text-align: center; colspan: 2;  margin-top: 24px; padding-top: 12px; border-top: 1px solid #ececec">
                                <a class="btn form-control tooltipstered" href="/member/idfind" style="background-color: #343A40; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">
                                        비밀번호찾기</a>
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
                <div class="idfind-ok" id="idfind-ok">
                	<div class="text-center">
                       <h2>${member.name}님</h2><br>
                            아이디:${member.account}<br>
                     </div>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="../include/footer.jsp" %>
</body>

<script type="text/javascript">

	$(document).ready(function () {
		document.getElementById('idfind-ok').style.display="none";
		
		$('#idfind-btn').on('click', e => {
			const $searchform = $('#searchform');
			const $name = $('#name');
			
			const $email = $('#email');
			if($name.val() === null || $name.val() ===''){
				alert('이름을 입력하세요.');	
				return;
			}
			if($email.val() === null || $email.val() ===''){
				alert('이메일을 입력하세요.');
				return;
			}
			
			$.ajax({
                type : 'post',
	            url : '/member/idsearch',
	            data : {
	            	name: $name.val(),
                    email:$email.val()
	            },
                success:function(result) {
                    console.log('flag:', result);
                    if (result === 0) {
                        alert('정보에 해당하는 유저가 존재하지않습니다.');
                    } else {
                        // 정상적으로 입력한 경우
                    	document.getElementById('idfind-div').style.display="none";
                    	document.getElementById('idfind-ok').style.display="block";
                    }
                },
                error:function(xhres){
					console.log(xhres);
				},
            });
			
		});
			
	})
		
</script>
</html>