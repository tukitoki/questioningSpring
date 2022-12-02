import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { InterviewedService } from 'src/app/service/interviewed.service';
import Swal from 'sweetalert2';
import { Interviewed } from '../entity/interviewed';

@Component({
  selector: 'app-attempt',
  templateUrl: './attempt.component.html',
  styleUrls: ['./attempt.component.css']
})
export class AttemptComponent implements OnInit {

  attempt!: Interviewed;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private interviewedService: InterviewedService) { }

  ngOnInit(): void {
    this.getAttemptFromServer(this.route.snapshot.params['id'])
  }

  private getAttemptFromServer(id: number) {
    this.interviewedService.getAttempt(id).subscribe({
      next: next => this.attempt = next,
      error: (error: HttpErrorResponse) => {
        Swal.fire(error.error.message);
        this.router.navigate([`attempts`])
      }
    });
  }

}
