import {Injectable} from 'angular2/core';
import {Problem} from '../models/problem';
import {Http, Headers} from 'angular2/http';
import {User} from '../models/user';



@Injectable()
export class ProblemsService {

  constructor (private http:Http){}

  getProblems() {
    let url = "http://localhost:8080/problem";
    return this.http.get(url);
  }

  getProblemById(problemId: number) {
    let url = "http://localhost:8080/problem/" + problemId;
    let header = new Headers({'Content-Type':'application/json'});
    return this.http.get(url, {headers:header} )
  }

  sendProblem(problem: Problem) {
    let url = "http://localhost:8080/problem";
    let header = new Headers({'Content-Type':'application/json'});
    console.log(JSON.stringify(problem));
    return this.http.post(url, JSON.stringify(problem), {headers:header});
  }





}
