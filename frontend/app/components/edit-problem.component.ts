import {Component} from 'angular2/core';
import {Router} from 'angular2/router';
import {Problem} from '../models/problem';
import {ProblemsService} from '../services/problems.service';

@Component({
  selector: 'edit-problem',
  providers: [ProblemsService],
  templateUrl: 'app/components/edit-problem.component.html'
})

export class EditProblem {
  newProblem: Problem = new Problem();
  problemAdded: boolean = false;

  constructor(
    private problemService: ProblemsService){}

    onSubmit() {
      this.problemService.sendProblem(this.newProblem).subscribe(
        data => {this.problemAdded = true;
        this.newProblem = new Problem();}
      ),
      error => console.log(error);
    }
}
