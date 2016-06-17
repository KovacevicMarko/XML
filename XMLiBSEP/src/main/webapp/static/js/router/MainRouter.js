(function() {
    var app = angular.module("MyApp");
    
    app.config(function ($stateProvider,$urlRouterProvider) {
    $urlRouterProvider.otherwise('/main');
    $stateProvider.state('main', 
        {//naziv stanja!
        url: '/main',
        views: {
            header:{
                templateUrl: '/XMLiBSEP/static/content/headerL.jsp',
                controller: 'SessionController'
            },
            content: {
              templateUrl: '/XMLiBSEP/static/content/home.jsp',
              controller: 'MainController'
            }
        }
        })
        .state('login',{//naziv stanja!
        url: '/login',
        views: {
            header:{
                templateUrl: '/XMLiBSEP/static/content/headerNL.jsp',
                controller: 'SessionController'
            },
            content: {
            	templateUrl: '/XMLiBSEP/static/content/home.jsp',
                controller: 'MainController'
            }
        }            
        })  
        .state('sednica',{//naziv stanja!
        url: '/sednica',
        views: {
            header:{
                templateUrl: '/XMLiBSEP/static/content/headerL.jsp',
                controller: 'SessionController'
            },
            content: {
              templateUrl: '/XMLiBSEP/static/content/sednica.jsp',
              controller: 'SednicaController'
            }
        }            
        })
        .state('predlozi',{//naziv stanja!
        url: '/predlozi',
        views: {
            header:{
                templateUrl: '/XMLiBSEP/static/content/headerL.jsp',
                controller: 'SessionController'
            },
            content: {
              templateUrl: '/XMLiBSEP/static/content/predlozi.jsp',
              //controller: ''
            }
        }            
        })
        .state('register', {
        	url: '/register',
        	views: {
        		content: {
                    templateUrl: '/XMLiBSEP/static/content/signUp.jsp',
                    controller: 'UserController'
                }
        	}
        })
     
        
        
        /*
        .state('task', {
            url: '/projects/:projectId/tasks/:taskId',
            views: {
                header:{
                    templateUrl: 'header/headerL.html',
                    controller: 'AuthController'
                },
                content: {
                    templateUrl: 'task/templates/task.html',
                    controller: 'TaskController'
                }
            }  
        }
            
        )
        .state('reports', {
            url: '/reports',
            views: {
                header:{
                    templateUrl: 'header/headerL.html',
                    controller: 'AuthController'
                },
                content: {
                    templateUrl: 'report/reports.html',
                    controller: 'ReportController'
                }
            }  
        })*/;
    });
}())