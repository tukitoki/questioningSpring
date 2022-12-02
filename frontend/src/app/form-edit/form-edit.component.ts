import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Form } from 'src/app/entity/form';
import { FormService } from 'src/app/service/form.service';
import Swal from 'sweetalert2'
import { Interviewed } from '../entity/interviewed';

@Component({
  selector: 'app-form-edit',
  templateUrl: './form-edit.component.html',
  styleUrls: ['./form-edit.component.css']
})
export class FormEditComponent implements OnInit {

  form!: Form;
  userIp: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private formService: FormService,
    private http: HttpClient) {
  }

  ngOnInit(): void {
    if (this.route.snapshot.params['id'] != null) {
      this.getFormFromServer(this.route.snapshot.params['id'])
    } else {
        this.form = {
        formId: null,
        description: '',
        questions: []
      }
    }
  }

  private getFormFromServer(id: number) {
    this.formService.getForm(id).subscribe(value => 
      this.form = value
    );
  }

  gotoFormList() {
    this.router.navigate(['/forms']);
  }

  createNewQuestion() {
    this.form.questions.push({questionId: null, description: '', numberOfChoices: 1, choices: []})
  }

  submitForm() {
    if (this.form.formId != null) {
      this.formService.update(this.form.formId, this.form).subscribe({
        next: value => this.gotoFormList(), 
        error: (err: HttpErrorResponse) => {
          Swal.fire(err.error.message)
        }
      })
    } else {
      this.formService.save(this.form).subscribe({
        next: value => this.gotoFormList(), 
        error: (error: HttpErrorResponse) => {
          Swal.fire(error.error.message)
        }
      })
    }
  }

}
 