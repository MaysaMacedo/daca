System.register(['angular2/core', '../models/problem', '../services/problems.service', 'angular2/router'], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, problem_1, problems_service_1, router_1;
    var ProblemDetail;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (problem_1_1) {
                problem_1 = problem_1_1;
            },
            function (problems_service_1_1) {
                problems_service_1 = problems_service_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            }],
        execute: function() {
            ProblemDetail = (function () {
                function ProblemDetail(problemsService, routeParam) {
                    var _this = this;
                    this.problemsService = problemsService;
                    this.routeParam = routeParam;
                    this.problem = new problem_1.Problem();
                    var problemId = Number.parseInt(this.routeParam.get('id'));
                    this.problemsService.getProblemById(problemId).subscribe(function (problem) {
                        _this.problem = JSON.parse(JSON.parse(JSON.stringify(problem))._body);
                    });
                }
                ProblemDetail = __decorate([
                    core_1.Component({
                        selector: 'problem-details',
                        templateUrl: 'app/components/problem-detail.components.html'
                    }), 
                    __metadata('design:paramtypes', [problems_service_1.ProblemsService, router_1.RouteParams])
                ], ProblemDetail);
                return ProblemDetail;
            }());
            exports_1("ProblemDetail", ProblemDetail);
        }
    }
});
//# sourceMappingURL=problem-detail.component.js.map