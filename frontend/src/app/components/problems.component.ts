import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {Problem} from '../models/problem';
import {ProblemsService} from '../services/problems.service';

@Component({
  selector:'problems-list',
  templateUrl: './problems.component.html'
})

export class Problems {
  public pros: Problem[];
  private selectedProblem: Problem;

  constructor(
    private problemsService: ProblemsService,
    private router: Router
  ){
    this.problemsService.getProblems().subscribe(
      data => this.pros = JSON.parse(JSON.parse(JSON.stringify(data))._body),
      error => console.log(error)
    );
  }

  onSelect(problem: Problem) {
    this.selectedProblem = problem;
    this.router.navigate(['/problem-detail', this.selectedProblem.id]);
  }

}
