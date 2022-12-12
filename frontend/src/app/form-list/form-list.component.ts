import { Component, Input, OnInit, SimpleChanges } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Form } from '../entity/form'
import { Page } from '../entity/page';
import { FormService } from "../service/form.service";

@Component({
  selector: 'app-form-list',
  templateUrl: './form-list.component.html',
  styleUrls: ['./form-list.component.css']
})
export class FormListComponent implements OnInit {
  
  url = "forms"
  forms!: Form[];
  page!: Page<Form>;
  currPage: number = 0;
  currPageSize: number = 10;
  @Input() description!: string;


  constructor(private formService: FormService,
              private router: Router,
              private activatedRt: ActivatedRoute) {
  }

  descriptionSearch(event: any) {
    this.formService.getAll(this.description, this.currPage, this.currPageSize).subscribe(data => {
      this.page = data;
      this.forms = this.page.content;
    });
  }

  ngOnInit(): void {
    this.getAllFromServer(this.currPage, this.currPageSize, this.description);
  }

  private getAllFromServer(currPage: number, currPageSize: number, description: string) {
    this.activatedRt.queryParams.subscribe(params => {
      this.currPage = params['page'];
      this.currPageSize = params['size'];
    })
    this.formService.getAll(this.description, this.currPage, this.currPageSize).subscribe(data => {
      this.page = data;
      this.forms = this.page.content;
      setTimeout(() => this.getAllFromServer(currPage, currPageSize, description), 1000);
    })
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
    this.router.navigate([`edit/${form.formId}`]);
  }
}
