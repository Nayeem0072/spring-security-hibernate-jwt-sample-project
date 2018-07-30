<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="library" fragment="true" %>
<html>
<head>
	<title>EkPay</title>
	<spring:url value="/resources/css/main.css" var="mainCss" />
	<spring:url value="/resources/js/jquery-3.2.1.min.js" var="jqueryJs" />
	
	<link href="${mainCss}" rel="stylesheet" />
	<script src="${jqueryJs}"></script>
	
 	<jsp:invoke fragment="library"/>
</head>
  <body>
  Layout text
    <div id="pageheader">
      <jsp:invoke fragment="header"/>
    </div>
    <div id="body">
      <jsp:doBody/>
    </div>
    <div id="pagefooter">
      <jsp:invoke fragment="footer"/>
    </div>
  </body>
</html>