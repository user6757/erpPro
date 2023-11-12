<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<form id="del-form" action="/notice/delete" method="post">
  <input type="hidden" name="noticeNo" value="${noticeNo}">
</form>

<script>
  const $form = document.getElementById('del-form');
  $form.submit();
</script>