System.register(['angular2/core', 'angular2/http'], function(exports_1, context_1) {
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
    var core_1, http_1;
    var ProblemsService;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            }],
        execute: function() {
            ProblemsService = (function () {
                function ProblemsService(http) {
                    this.http = http;
                }
                ProblemsService.prototype.getProblems = function () {
                    var url = "http://localhost:8080/problem";
                    return this.http.get(url);
                };
                ProblemsService.prototype.getProblemById = function (problemId) {
                    var url = "http://localhost:8080/problem/" + problemId;
                    var header = new http_1.Headers({ 'Content-Type': 'application/json' });
                    return this.http.get(url, { headers: header });
                };
                ProblemsService.prototype.sendProblem = function (problem) {
                    var url = "http://localhost:8080/problem";
                    var header = new http_1.Headers({ 'Content-Type': 'application/json' });
                    console.log(JSON.stringify(problem));
                    return this.http.post(url, JSON.stringify(problem), { headers: header });
                };
                ProblemsService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [http_1.Http])
                ], ProblemsService);
                return ProblemsService;
            }());
            exports_1("ProblemsService", ProblemsService);
        }
    }
});
//# sourceMappingURL=problems.service.js.map