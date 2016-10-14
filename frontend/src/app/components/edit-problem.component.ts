import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {Problem} from '../models/problem';
import {ProblemsService} from '../services/problems.service';

@Component({
  selector: 'edit-problem',
  providers: [ProblemsService],
  templateUrl: './edit-problem.component.html'
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
