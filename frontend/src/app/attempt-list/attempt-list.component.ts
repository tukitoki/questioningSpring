import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Interviewed } from '../entity/interviewed';
import { Page } from '../entity/page';
import { InterviewedService } from '../service/interviewed.service';

@Component({
  selector: 'app-attempt-list',
  templateUrl: './attempt-list.component.html',
  styleUrls: ['./attempt-list.component.css']
})
export class AttemptListComponent implements OnInit {

  attempts!: Interviewed[];
  page!: Page<Interviewed>;
  currPage: number = 0;
  currPageSize: number = 1;

  constructor(private interviewedService: InterviewedService,
    private router: Router,
    private activatedRt: ActivatedRoute) { 

  }

  ngOnInit(): void {
    this.activatedRt.queryParams.subscribe(params => {
      this.currPage = params['page'];
      this.currPageSize = params['size'];
    });
    this.getAllFromServer(this.currPage, this.currPageSize);
  }

  private getAllFromServer(currPage: number, currPageSize: number) {
    this.activatedRt.queryParams.subscribe(params => {
      this.currPage = params['page'];
      this.currPageSize = params['size'];
    })
    this.interviewedService.getAllAttempts(this.currPage, this.currPageSize).subscribe(data => {
      this.page = data;
      this.attempts = this.page.content;
      setTimeout(() => this.getAllFromServer(currPage, currPageSize), 1000);
    })
  }

  toAttempt(id: number | null) {
    this.router.navigate([`attempts/${id}`])
  }

  deleteAttempt(id: number | null) {

  }
}
