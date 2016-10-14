import {ModuleWithProviders} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {HomeComponent} from './components/home.component';
import {Register} from './components/register.component';
import {Login} from './components/login.component';
import {Problems} from './components/problems.component';
import {EditProblem} from './components/edit-problem.component';
import {ProblemDetail} from './components/problem-detail.component';

const appRoutes: Routes = [
  {path:'',
  redirectTo:'/home',
  pathMatch: 'full'},
  {path:'home',
  component: HomeComponent
  },
  {path:'register',
  component: Register},
  {path:'login',
  component: Login},
  {path: 'problem',
  component: Problems},
  {path:'edit-problem',
  component: EditProblem},
  {path:'problem-detail/:id',
  component: ProblemDetail}
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
