<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>AngularJS $http Example</title>  
          
      <script data-require="angularjs@1.5.5" data-semver="1.5.5" src="https://code.angularjs.org/1.5.5/angular.js"></script>
      <script data-require="angular-ui-bootstrap@*" data-semver="1.1.2" src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/1.3.3/ui-bootstrap-tpls.js"></script>
      <script data-require="angular-resource@*" data-semver="1.4.8" src="https://code.angularjs.org/1.5.6/angular-resource.js"></script>
      <script data-require="angular-route@*" data-semver="1.4.8" src="https://code.angularjs.org/1.5.6/angular-route.js"></script>
      <script data-require="angular-ui-router@*" data-semver="0.2.15" src="//cdnjs.cloudflare.com/ajax/libs/angular-ui-router/0.2.15/angular-ui-router.min.js"></script>
      <script src="<c:url value='/static/js/app.js' />"></script>
      <script src="<c:url value='/static/js/service/UserService.js' />"></script>
      <script src="<c:url value='/static/js/controller/UserController.js' />"></script>
      <script src="<c:url value='/static/js/controller/MainController.js' />"></script>
      <script src="<c:url value='/static/js/router/MainRouter.js' />"></script>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  </head>
  <body ng-app="MyApp" ng-controller="MainController">
        <div ui-view="header"></div>
        <div ui-view="content"></div>

  </body>
</html>