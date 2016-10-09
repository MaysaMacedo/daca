System.register(['angular2/core', 'angular2/router', '../services/problems.service'], function(exports_1, context_1) {
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
    var core_1, router_1, problems_service_1;
    var Problems;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            },
            function (problems_service_1_1) {
                problems_service_1 = problems_service_1_1;
            }],
        execute: function() {
            Problems = (function () {
                function Problems(problemsService, router) {
                    var _this = this;
                    this.problemsService = problemsService;
                    this.router = router;
                    this.problemsService.getProblems().subscribe(function (data) { return _this.pros = JSON.parse(JSON.parse(JSON.stringify(data))._body); }, function (error) { return console.log(error); });
                }
                Problems.prototype.onSelect = function (problem) {
                    this.selectedProblem = problem;
                    this.router.navigate(['ProblemDetail', { id: this.selectedProblem.id }]);
                };
                Problems = __decorate([
                    core_1.Component({
                        selector: 'problems-list',
                        templateUrl: 'app/components/problems.component.html'
                    }), 
                    __metadata('design:paramtypes', [problems_service_1.ProblemsService, router_1.Router])
                ], Problems);
                return Problems;
            }());
            exports_1("Problems", Problems);
        }
    }
});
//# sourceMappingURL=problems.component.js.map