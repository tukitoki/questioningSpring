import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Interviewed } from '../entity/interviewed';
import { InterviewedService } from '../service/interviewed.service';

@Component({
  selector: 'app-attempt-list',
  templateUrl: './attempt-list.component.html',
  styleUrls: ['./attempt-list.component.css']
})
export class AttemptListComponent implements OnInit {

  attempts!: Interviewed[];

  constructor(private interviewedService: InterviewedService,
    private router: Router) { 

  }

  ngOnInit(): void {
    this.interviewedService.getAllAttempts().subscribe(data => 
      this.attempts = data)
  };

  toAttempt(id: number | null) {
    this.router.navigate([`attempts/${id}`])
  }

  deleteAttempt(id: number | null) {

  }
}
