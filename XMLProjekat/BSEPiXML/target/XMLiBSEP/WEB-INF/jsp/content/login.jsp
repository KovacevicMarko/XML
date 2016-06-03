<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/TagLibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
     
  </head>

  <body>

    <div class="login-page">
  <div class="form">
    <c:url var="action" value="/logIn" />
    <form:form id="formSignIn" action="${action}" method="post" modelAttribute="user">
    <table>
    <fildSet>
      <tr>
     <td>
      <form:label path="username">
       <b> Username: </b>
      </form:label>
     </td>
     <td>
      <form:input  path="username" cssErrorClass="error" />
      <form:errors path="username" cssClass="errorMessage" />
     </td>
    </tr>
      <tr>
      
     <td>
      <form:label path="password">
       <b> Password: </b>
      </form:label>
     </td>
     <td>
      <form:input type ="password" path="password" cssErrorClass="error" />
      <form:errors path="password" cssClass="errorMessage" />
     <td>
    </tr>
    </fildSet>
      <button type="submit" name="login" class="button">
       <b> Login </b>
      </button>
      </table>
    </form:form>
  </div>
</div>
    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>


<script type="text/javascript">
$('.message a').click(function(){
	   $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
	});
</script>
    
    
    
  </body>
</html>