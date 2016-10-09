import {Component, Input} from 'angular2/core';
import {Problem} from '../models/problem';
import {ProblemsService} from '../services/problems.service';
import {RouteParams} from 'angular2/router';

@Component( {
  selector: 'problem-details',
  templateUrl: 'app/components/problem-detail.components.html'
})

export class ProblemDetail {
  problem: Problem = new Problem();

  constructor(
      private problemsService: ProblemsService,
      private routeParam: RouteParams
    ){
      let problemId = Number.parseInt(this.routeParam.get('id'));
      this.problemsService.getProblemById(problemId).subscribe(
        problem => {
          this.problem = JSON.parse(JSON.parse(JSON.stringify(problem))._body);
        }
      )
    }
}
