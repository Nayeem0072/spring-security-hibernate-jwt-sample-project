<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:genericpage>	
	<jsp:body>  
		<body>
			<div id="login-box" style="text-align:center">
				<img id="original_rt_logo" src="https://staticv2-4.rottentomatoes.com/static/images/logos/rtlogo.png">
				<h2>Popular Shows Right Now</h2>	
				<c:forEach items="${tvShowList}" var="shows" varStatus="showCounter">	    
		       		${showCounter.count}. <span style=""><a href="${shows.url}">${shows.name}</a> &nbsp;&nbsp;<span style="color:blue;font-weight:bold">${shows.score}</span></span> 
		       		<br/>
				</c:forEach>	
			</div>		
		</body>
	</jsp:body>
</t:genericpage>