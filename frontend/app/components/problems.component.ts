import {Component} from 'angular2/core';
import {Router} from 'angular2/router';
import {ROUTER_DIRECTIVES} from 'angular2/router';
import {Problem} from '../models/problem';
import {ProblemsService} from '../services/problems.service';

@Component({
  selector:'problems-list',
  templateUrl: 'app/components/problems.component.html'
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
    this.router.navigate(['ProblemDetail', {id: this.selectedProblem.id}]);
  }

}
