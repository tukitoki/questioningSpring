import { Component, Input, OnInit } from '@angular/core';
import { Choice } from 'src/app/entity/choice';
import { Question } from 'src/app/entity/question';

@Component({
  selector: 'app-choice-edit',
  templateUrl: './choice-edit.component.html',
  styleUrls: ['./choice-edit.component.css']
})
export class ChoiceEditComponent implements OnInit {

  @Input() choice!: Choice;
  @Input() index!: number;
  @Input() question!: Question;

  constructor() { }

  ngOnInit(): void {

  }

  deleteChoice() {
    this.question.choices.splice(this.index, 1)
  }

}
