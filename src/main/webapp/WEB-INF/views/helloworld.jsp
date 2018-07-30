<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:genericpage>
 	<jsp:attribute name="library"> 		
	<spring:url value="/resources/js/main.js" var="mainJs" />
	<script src="${mainJs}"></script>  
    </jsp:attribute>    
    <jsp:attribute name="header">
      <h1>Welcome ${name}</h1>
    </jsp:attribute>    
    <jsp:body>
        <p>Message: ${message}</p>
        <h2>1. Test CSS</h2>
		<h3>2. Test JS</h3>
		<div id="msg"></div>
		<div class="form-fs">
		<h3>Create User</h3>
			<form:form method="POST" action="../hello/adduser" modelAttribute="testproctable">
				<table>
	                <tr>
	                    <td><form:label path="name">Name</form:label></td>
	                    <td><form:input path="name"/></td>
	                </tr>                
	                <tr>
	                	<td></td>
	                    <td><input type="submit" value="Submit"/></td>                    
	                </tr>
	            </table>
			</form:form>
		</div>
		<div class="form-fs">
		<h3>List of Users</h3>
		<c:forEach items="${users}" var="user" varStatus="userCounter">	    
       		${userCounter.count}. ${user.name}
       		<br/>
		</c:forEach>
		</div>
    </jsp:body>    
</t:genericpage>