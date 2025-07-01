<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div align="right">
	<table>
		<tr>
			<td width="80"><a href="/user/registerForm"> <spring:message
						code="header.joinMember" /></a></td>
			<!-- 그룹코드 관리를 메뉴에 추가한다. -->
			<td width="120"><a href="/codeGroup/list"><spring:message
						code="menu.codegroup.list" /></a></td>
			<!-- 코드 관리를 메뉴에 추가한다. -->
			<td width="120"><a href="/codedetail/list"><spring:message
						code="menu.codedetail.list" /></a></td>
			<!-- 회원 관리를 메뉴에 추가한다. -->
			<td width="120"><a href="/user/list"><spring:message
						code="menu.user.admin" /></a></td>
		</tr>
	</table>
</div>
<hr>
