<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:genericpage>
	<jsp:attribute name="library"> 		
		<spring:url value="/resources/css/login.css" var="loginCss" />
		<link href="${loginCss}" rel="stylesheet" />
	</jsp:attribute>
	<jsp:body>  
		<body onload='document.loginForm.username.focus();'>
			<div id="login-box">
				<h2>Login</h2>
				<c:if test="${not empty error}">
					<div class="error">${error}</div>
				</c:if>
				<c:if test="${not empty msg}">
					<div class="msg">${msg}</div>
				</c:if>	
					
				<form name='loginForm'
							action="<c:url value='/j_spring_security_check' />" method='POST'>		
					<table>
						<tr>
							<td>Username: </td>
							<td><input type='text' name='username' /></td>
						</tr>
						<tr>
							<td>Password: </td>
							<td><input type='password' name='password' /></td>
						</tr>
						<tr>
							<td colspan='2'><input name="submit" type="submit"
										value="Submit" /></td>
						</tr>
					</table>		
					<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
					<br/>	
					<c:if test="${not empty referrer}">
						<div style="text-align:center;font-size: 0.8em;">Redirected from: ${referrer}</div>
					</c:if>			
				</form>
			</div>				
		</body>
	</jsp:body>
</t:genericpage>