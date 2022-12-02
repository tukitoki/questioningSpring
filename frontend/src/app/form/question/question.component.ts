import {Component, Input, OnInit} from '@angular/core';
import { InterviewedChoice } from 'src/app/entity/interviewed-choice';
import {Question} from "../../entity/question";

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class QuestionComponent implements OnInit {

  @Input() question!: Question;
  @Input() index!: number;
  @Input() allChoices!: InterviewedChoice[];
  selectedChoices!: (number | null)[];
  @Input() view!: boolean;

  constructor() { }

  ngOnInit(): void {
    this.selectedChoices = []
  }
}
