import { Component, Input, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Form } from 'src/app/entity/form';
import { Question } from 'src/app/entity/question';

@Component({
  selector: 'app-question-edit',
  templateUrl: './question-edit.component.html',
  styleUrls: ['./question-edit.component.css']
})
export class QuestionEditComponent implements OnInit {

  @Input() question!: Question;
  @Input() form!: Form;
  @Input() index!: number;
  constructor() { }

  ngOnInit(): void {
  }

  createNewChoice() {
    this.question.choices.push({description: "", choiceId: null, interviewedChoices: null})
  }

  deleteQuestion() {
    this.form.questions.splice(this.index, 1);
  }
}
