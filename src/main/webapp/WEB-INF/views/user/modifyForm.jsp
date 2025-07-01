<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Image Shop</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="/css/codegroup.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<jsp:include page="/WEB-INF/views/common/menu.jsp" />
	<div align="center">

<h2><spring:message code="user.header.read" /></h2> 
		<form:form modelAttribute="member"  action="modify">
			<form:hidden path="userNo" />
			<table>
				<tr>
					<td><spring:message code="user.userId" /></td>
					<td><form:input path="userId"  /></td>
				</tr>
				<tr>
					<td><spring:message code="user.userName" /></td>
					<td><form:input path="userName"  /></td>
				</tr>
				<tr>
					<td><spring:message code="user.job" /></td>
					<td><form:select path="job" items="${jobList}"
							itemValue="value" itemLabel="label"  /></td>
				</tr>
				<tr>
					<td><spring:message code="user.auth" /> - 1</td>
					<td><form:select path="authList[0].auth" >
							<form:option value="" label="=== 선택해 주세요 ===" />
							<form:option value="ROLE_USER" label="사용자" />
							<form:option value="ROLE_MEMBER" label="회원" />
							<form:option value="ROLE_ADMIN" label="관리자" />
						</form:select></td>
				</tr>
				<tr>
					<td><spring:message code="user.auth" /> - 2</td>
					<td><form:select path="authList[1].auth">
							<form:option value="" label="=== 선택해 주세요 ===" />
							<form:option value="ROLE_USER" label="사용자" />
							<form:option value="ROLE_MEMBER" label="회원" />
							<form:option value="ROLE_ADMIN" label="관리자" />
						</form:select></td>
				</tr>
				<tr>
					<td><spring:message code="user.auth" /> - 3</td>
					<td><form:select path="authList[2].auth" >
							<form:option value="" label="=== 선택해 주세요 ===" />
							<form:option value="ROLE_USER" label="사용자" />
							<form:option value="ROLE_MEMBER" label="회원" />
							<form:option value="ROLE_ADMIN" label="관리자" />
						</form:select></td>
				</tr>
			</table>
		</form:form>
		<div>
			<button type="submit" id="btnModify">
				<spring:message code="action.modify" />
			</button>
			
			<button type="button" id="btnList">
				<spring:message code="action.list" />
			</button>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
<script>
	$(document).ready(function() {
		var formObj = $("#member");
		console.log(formObj);
		$("#btnModify").on("click", function() {
			 formObj.submit();
		});

		$("#btnList").on("click", function() {
			self.location = "list";
		});

	});
</script>


</html>
