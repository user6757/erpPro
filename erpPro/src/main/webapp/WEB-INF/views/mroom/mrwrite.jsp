<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="card-title">
    <!-- Page Heading 회의실관리 예약-->
    <div class="d-sm-flex align-items-center justify-content-between mb-4" style="margin-left: 15px;">
        <div class="border-bottom-1">
            <h1 class="h3 mb-2 text-gray-700">회의실 예약</h1>
        </div>
    </div>
</div>
<!-- 구분 선 속성-->
<hr class="sidebar-divider d-none d-md-block">
<div style="background:#ffffff; border-radius: 5px; margin-top: 30px">
    <div class="default">
        <form action="/mrmain/mrwrite" name="signup" id="mr-for" method="post" style="margin-bottom: 0;">
            <div style="cellpadding: 0; cellspacing: 0; margin: 0 auto; width: 80%">
                <div>
                    <div class="input-group input-group-lg" style="margin-bottom: 30px">
                        <span class="input-group-text" id="mr-tit">회의실 명:</span>
                        <input type="text" class="form-control" aria-label="Sizing example input"
                               aria-describedby="inputGroup-sizing-lg" name="mrTitle" required>
                    </div>
                </div>
                <div>
                    <div class="input-group input-group-lg" style="margin-bottom: 30px">
                        <span class="input-group-text" id="inputGroup-sizing-lg1">최대 인원:</span>
                        <input type="text" class="form-control" aria-label="Sizing example input"
                               aria-describedby="inputGroup-sizing-lg" name="mrMax">
                    </div>
                </div>
                <div>
                    <div class="input-group input-group-lg" style="margin-bottom: 30px">
                        <span class="input-group-text" id="inputGroup-sizing-lg2">예약자 명:</span>
                        <input type="text" class="form-control" aria-label="Sizing example input"
                               aria-describedby="inputGroup-sizing-lg" name="mrUsers">
                    </div>
                </div>
                <div style="text-align: left; margin-bottom: 1.5em">
                    <div class="mb-5">
                        <div class="input-group" style="margin-bottom: 30px">
                            <span class="input-group-text" id="mr-con">회의실 설명:</span>
                            <textarea class="form-control" aria-label="With textarea" rows="5"
                                      placeholder="사용하실 품목들을 입력해주세요." name="mrContent"></textarea>
                        </div>
                    </div>
                </div>
                <div>
                    <div class="input-group input-group-lg" style="margin-bottom: 30px">
                        <span class="input-group-text" id="inputGroup-sizing-lg3">사용 시간:</span>
                        <input type="text" class="form-control" aria-label="Sizing example input"
                               aria-describedby="inputGroup-sizing-lg" name="mrTime">
                    </div>
                </div>
                <div style="margin-left: 1px;">
                    <button style="float: right" type="submit" class="btn btn-primary" id="mr-save">저장</button>
                    <button type="button" class="btn btn-primary" onclick="changeContentCus('main', 'mrmain')">목록</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    // 게시물 등록 입력값 검증 함수
    function mrValidateFormValue() {
        // 제목 입력 태그, 내용 입력 태그
        const $mrTit = document.getElementById('mr-tit');
        const $mrCon = document.getElementById('mr-con');
        let flag = false; // 입력 제대로 하면 true로 변경

        console.log('t: ', $mrTit);
        console.log('c: ', $mrCon);

        if ($mrTit.value.trim() === '') {
            alert('제목은 필수 입니다.');
        } else if ($mrCon.value.trim() === '') {
            alert('내용은 필수 입니다.');
        } else {
            flag = true;
        }

        console.log('flag: ', flag);

        return flag;
    }

    // 게시물 입력값 검증
    const $mrSave = document.getElementById('mr-save');

    $mrSave.onclick = e => {
        // 입력값을 제대로 채우지 않았는지 확인
        if (!mrValidateFormValue()) {
            return;
        }

        // 필수 입력값을 잘 채웠으면 폼을 서브밋한다
        const $mrFor = document.getElementById('mr-for');
        $mrFor.submit();
    };
</script>



