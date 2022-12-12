import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FormService} from "../service/form.service";
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import { Form } from '../entity/form';
import { Interviewed } from '../entity/interviewed';
import { InterviewedService } from '../service/interviewed.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  @Input() form!: Form;
  sumbitChoices!: number[];
  @Input() interviewed!: Interviewed;
  @Input() view!: boolean;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private formService: FormService,
    private interviewedService: InterviewedService) {
  }

  ngOnInit(): void {
    this.sumbitChoices = [];
    if (this.view == undefined) {
      this.view = false;
      this.getFormFromServer(this.route.snapshot.params['id'])
      this.interviewed = {
        form: this.form,
        interviewedId: null,
        interviewedIp: '',
        interviewedDate: new Date,
        interviewedChoices: [],
        allInterviewedChoices: []
      };
    }
  }

  private getFormFromServer(id: number) {
    this.formService.getForm(id).subscribe({
      next: value => this.form = value,
      error: (error: HttpErrorResponse) => {
        Swal.fire(error.error.message);
        this.router.navigate(["/forms"]);
      }
    });
  }

  submitAttempt() {
    this.interviewed.form = this.form;
    this.interviewedService.save(this.interviewed).subscribe({
      next: next => this.router.navigate([`/attempts/${next.interviewedId}/`]),
      error: (error: HttpErrorResponse) => {
        Swal.fire(error.error.message)
      }
    });
  }
}
