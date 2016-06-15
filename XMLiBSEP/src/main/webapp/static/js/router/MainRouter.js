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
                controller: 'MainController'
            },
            content: {
              templateUrl: '/XMLiBSEP/static/content/home_pre.jsp',
              controller: 'MainController'
            }
        }
        })
        .state('login',{//naziv stanja!
        url: '/login',
        views: {
            header:{
                templateUrl: '/XMLiBSEP/static/content/headerNL.jsp',
                controller: 'UserController'
            },
            content: {
            	templateUrl: '/XMLiBSEP/static/content/home.jsp',
                controller: 'MainController'
            }
        }            
        })  
        /*.state('projects',{//naziv stanja!
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