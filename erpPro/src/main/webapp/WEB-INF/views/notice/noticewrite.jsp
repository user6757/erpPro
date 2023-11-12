<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h1 class="m-0 font-weight-bold text-primary" style="text-align: center">공지사항 등록</h1>
    </div>
    <div class="card-body col-10" style="left: 7%">
        <form action="/notice/noticewrite" id="notice-write-form" class="was-validated" method="post" autocomplete="off">
<%--        <form action="/notice/write" id="notice-write-form" class="was-validated" method="post" autocomplete="off">--%>
            <%--태그 선택--%>
            <%--            <div class="dropdown mb-3" style="margin-right: 15px">--%>
            <%--                <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton"--%>
            <%--                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">--%>
            <%--                    태그 선택--%>
            <%--                </button>--%>
            <%--                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">--%>
            <%--                    <li><a class="dropdown-item" href="#" type="all">전체 공지</a></li>--%>
            <%--                    <li><a class="dropdown-item" href="#" type="emp">부서 공지</a></li>--%>
            <%--                    <li><a class="dropdown-item" href="#">부서</a></li>--%>
            <%--                </ul>--%>
            <select class="form-select float-left" name="type" id="notice-type"
                    style="margin-right: 2em; height: 38px">
                <option value="all">전체 공지</option>
            </select>
            <%--            </div>--%>

            <%--제목 입력--%>
            <div class="mb-3">
                <label for="notice-title" class="form-label">제목 입력</label>
                <input name="noticeTitle" type="text" class="form-control" id="notice-title" placeholder="제목을 입력하세요"
                       required>
                <div class="invalid-feedback">
                    제목은 필수사항입니다.
                </div>
            </div>
            <%--내용 입력--%>
            <div class="mb-3">
                <label for="notice-content" class="form-label">공지사항 입력</label>
                <textarea name="noticeContent" class="form-control" id="notice-content" placeholder="내용을 입력하세요" required
                          style="height: 300px"></textarea>
                <div class="invalid-feedback">
                    내용은 필수사항입니다.
                </div>
            </div>
            <%--첨부파일--%>
            <div class="mb-3">
                <input name="notice-file" type="file" class="form-control" aria-label="file example" multiple>
            </div>
            <%--buttons--%>
            <div class="clearfix">
                <div class="float-left">
                    <button id="notice-list" type="button" class="btn btn-primary" onclick="changeContentCus('main', 'notice')">목록</button>
                </div>
                <div class="float-right">
                    <button id="notice-write" type="submit" class="btn btn-primary">등록</button>
                    <%--                <button type="button" class="btn btn-danger" onclick="">취소</button>--%>
                </div>
            </div>
        </form>

    </div>
</div>

<script>
    // 게시물 등록 입력값 검증 함수
    function noticeValidateFormValue() {
        // 제목 입력 태그, 내용 입력 태그
        const $noticeTitleInput = document.getElementById('notice-title');
        const $noticeContentInput = document.getElementById('notice-content');
        let flag = false; // 입력 제대로 하면 true로 변경

        console.log('t: ', $noticeTitleInput);
        console.log('c: ', $noticeContentInput);

        if ($noticeTitleInput.value.trim() === '') {
            alert('제목은 필수 입니다.');
        } else if ($noticeContentInput.value.trim() === '') {
            alert('내용은 필수 입니다.');
        } else {
            flag = true;
        }

        console.log('flag: ', flag);

        return flag;
    }

    // 게시물 입력값 검증
    const $noticeWrite = document.getElementById('notice-write');

    $noticeWrite.onclick = e => {
        // 입력값을 제대로 채우지 않았는지 확인
        if (!noticeValidateFormValue()) {
            return;
        }

        // 필수 입력값을 잘 채웠으면 폼을 서브밋한다
        const $noticeForm = document.getElementById('notice-write-form');
        $noticeForm.submit();
    };

    // 목록버튼 이벤트
    // const $noticeList = document.getElementById('notice-list');
    // $noticeList.onclick = e => {
    //     location.href = '../notice/list';
    // };

    // function goNoticeMain() {
    //     location.href="/main";
    // }
    //
    // function insertNotice() {
    //     location.href="/main";
    //     location.reload();
    // }
</script>

