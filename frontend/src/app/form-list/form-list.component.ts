import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Form } from '../entity/form'
import { FormService } from "../service/form.service";

@Component({
  selector: 'app-form-list',
  templateUrl: './form-list.component.html',
  styleUrls: ['./form-list.component.css']
})
export class FormListComponent implements OnInit {

  forms!: Form[];

  constructor(private formService: FormService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.formService.getAll().subscribe(data => 
      this.forms = data
    );
  }

  toForm(id: number | null) {
    this.router.navigate([`forms/${id}`]);
  }
  
  deleteForm(id: number | null) {
    this.formService.delete(id).subscribe(
      result => this.router.navigate(["forms/"])
    );
  }

  editForm(form: Form) {
    this.router.navigate([`edit/${form.formId}`])
  }
}
