<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="/WEB-INF/jsp/common/TagLibs.jsp"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<script src="<c:url value="/js/jquery.min.js"/>"></script>	
<script src="<c:url value="/js/bootstrap.js"/>"></script>


</head>
<body>

	<body>		

		<header id="header">
			<div id="logo">
					<tiles:insertAttribute name="header" />
				<section id="mainContent">
					<tiles:insertAttribute name="content" />
				</section>
			</div>
		</header>	
	</body>



</body>
</html>