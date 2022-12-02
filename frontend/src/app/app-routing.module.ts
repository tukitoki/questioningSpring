import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {FormComponent} from "./form/form.component";
import {FormListComponent} from "./form-list/form-list.component";
import { FormEditComponent } from './form-edit/form-edit.component';
import { AttemptListComponent } from './attempt-list/attempt-list.component';
import { AttemptComponent } from './attempt/attempt.component';

const routes: Routes = [
  { path: 'forms', component: FormListComponent },
  { path: 'forms/:id', component: FormComponent },
  { path: 'edit', component: FormEditComponent },
  { path: 'edit/:id', component: FormEditComponent },
  { path: 'attempts', component: AttemptListComponent},
  { path: 'attempts/:id', component: AttemptComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
