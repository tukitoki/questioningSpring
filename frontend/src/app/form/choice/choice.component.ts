import {Component, Input, OnInit} from '@angular/core';
import { InterviewedChoice } from 'src/app/entity/interviewed-choice';
import {Choice} from "../../entity/choice";

@Component({
  selector: 'app-choice',
  templateUrl: './choice.component.html',
  styleUrls: ['./choice.component.css']
})
export class ChoiceComponent implements OnInit {

  @Input() choice!: Choice;
  @Input() index!: number;
  @Input() maxChoices!: number;
  selected!: boolean;
  @Input() selectedChoices!: (number | null)[];
  @Input() allChoices!: InterviewedChoice[];
  @Input() allInterviewedChoices!: InterviewedChoice[];
  @Input() view!: boolean;
  percentile!: number;

  constructor() { }

  ngOnInit(): void {
    if (this.view) {
      for (let i = 0; i < this.allChoices.length; i++) {
        if (this.allChoices[i].interviewedChoiceId.choice == this.choice.choiceId) {
          this.selected = true;
          break;
        }
      }
      for (let i = 0; i < this.allInterviewedChoices.length; i++) {
        if (this.allInterviewedChoices[i].interviewedChoiceId.choice == this.choice.choiceId) {
          this.percentile = this.allInterviewedChoices[i].percentileOfChoice;
          break;
        }
      }
    } else {
      this.selected = false;
    }
  }

  checkSelected(event: any) {
    if (this.view) {
      return;
    }
    this.selected = (event.target as HTMLInputElement).checked;
    if (this.selected) {
      this.selectedChoices.push(this.choice.choiceId);
      this.allChoices.push({interviewedChoiceId: {interviewed: null, choice: this.choice.choiceId}, percentileOfChoice: 0});
    } else {
      this.selectedChoices.splice(this.selectedChoices.indexOf(this.choice.choiceId), 1);
      this.allChoices.splice(this.selectedChoices.indexOf(this.choice.choiceId), 1);
    }
  }
}
