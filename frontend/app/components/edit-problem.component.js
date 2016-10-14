System.register(['angular2/core', '../models/problem', '../services/problems.service'], function(exports_1, context_1) {
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
    var core_1, problem_1, problems_service_1;
    var EditProblem;
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
            }],
        execute: function() {
            EditProblem = (function () {
                function EditProblem(problemService) {
                    this.problemService = problemService;
                    this.newProblem = new problem_1.Problem();
                    this.problemAdded = false;
                }
                EditProblem.prototype.onSubmit = function () {
                    var _this = this;
                    this.problemService.sendProblem(this.newProblem).subscribe(function (data) {
                        _this.problemAdded = true;
                        _this.newProblem = new problem_1.Problem();
                    }),
                        function (error) { return console.log(error); };
                };
                EditProblem = __decorate([
                    core_1.Component({
                        selector: 'edit-problem',
                        providers: [problems_service_1.ProblemsService],
                        templateUrl: 'app/components/edit-problem.component.html'
                    }), 
                    __metadata('design:paramtypes', [problems_service_1.ProblemsService])
                ], EditProblem);
                return EditProblem;
            }());
            exports_1("EditProblem", EditProblem);
        }
    }
});
//# sourceMappingURL=edit-problem.component.js.map