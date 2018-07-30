<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:genericpage>	
	<jsp:body>  
		<body>
			<div id="login-box">
				<h2>Welcome ${user} (ID: ${userId})</h2>	
				<br/>
				<a href="logout">Logout</a>			
			</div>		
		</body>
	</jsp:body>
</t:genericpage>