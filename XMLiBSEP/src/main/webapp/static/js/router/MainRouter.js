(function() {
    var app = angular.module("MyApp");
    
    app.config(function ($stateProvider,$urlRouterProvider) {
    $urlRouterProvider.otherwise('/homePage');
    $stateProvider.state('homePage', 
        {//naziv stanja!
        url: '/homePage',
        views: {
            header:{
                templateUrl: '/XMLiBSEP/static/content/header.jsp',
                controller: 'MainController'
            },
            content: {
              templateUrl: '/XMLiBSEP/static/content/home.jsp',
              controller: 'MainController'
            }
        }
        })
        /*.state('login',{//naziv stanja!
        url: '/login',
        views: {
            header:{
                templateUrl: 'header/headerNL.html',
                controller: 'AuthController'
            },
            content: {
              templateUrl: 'auth/templates/signUp.html',
              controller: 'SignUpController'
            }
        }            
        })  
        .state('projects',{//naziv stanja!
        url: '/projects',
        views: {
            header:{
                templateUrl: 'header/headerL.html',
                controller: 'AuthController'
            },
            content: {
              templateUrl: 'project/templates/projects.html',
              controller: 'ProjectsController'
            }
        }            
        })
        .state('project',{//naziv stanja!
        url: '/projects/:projectId',
        views: {
            header:{
                templateUrl: 'header/headerL.html',
                controller: 'AuthController'
            },
            content: {
              templateUrl: 'project/templates/project.html',
              controller: 'ProjectController'
            }
        }            
        })
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