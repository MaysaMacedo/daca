import {Component, Input} from '@angular/core';
import {Problem} from '../models/problem';
import {ProblemsService} from '../services/problems.service';
import { ActivatedRoute, Params } from '@angular/router';

@Component( {
  selector: 'problem-details',
  templateUrl: './problem-detail.components.html'
})

export class ProblemDetail {
  problem: Problem = new Problem();
  problemId: number;

  constructor(
      private problemsService: ProblemsService,
      private route: ActivatedRoute
    ){

    this.route.params.forEach((params: Params) => {
      this.problemId = Number.parseInt(params['id']);
    });

      this.problemsService.getProblemById(this.problemId).subscribe(
        problem => {
          this.problem = JSON.parse(JSON.parse(JSON.stringify(problem))._body);
        }
      )
    }
}
