<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<form id="del-form" action="/mrmain/delete" method="post">
  <input type="hidden" name="mrNo" value="${mrNo}">
</form>

<script>
  const $form = document.getElementById('del-form')
  $form.submit();
</script>
