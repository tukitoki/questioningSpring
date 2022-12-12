import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {FormListComponent} from './form-list/form-list.component';
import {FormComponent} from './form/form.component';
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {FormService} from "./service/form.service";
import { FormEditComponent } from './form-edit/form-edit.component';
import { ChoiceComponent } from './form/choice/choice.component';
import { QuestionComponent } from './form/question/question.component';
import { QuestionEditComponent } from './form-edit/question-edit/question-edit/question-edit.component';
import { ChoiceEditComponent } from './form-edit/choice-edit/choice-edit/choice-edit.component';
import { AttemptComponent } from './attempt/attempt.component';
import { AttemptListComponent } from './attempt-list/attempt-list.component';
import { PaginationComponent } from './pagination/pagination.component';

@NgModule({
  declarations: [
    AppComponent,
    FormListComponent,
    FormComponent,
    FormEditComponent,
    ChoiceComponent,
    QuestionComponent,
    QuestionEditComponent,
    ChoiceEditComponent,
    AttemptComponent,
    AttemptListComponent,
    PaginationComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [FormService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
