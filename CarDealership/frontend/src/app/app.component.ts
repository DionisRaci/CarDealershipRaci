import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AuthService } from '@auth0/auth0-angular';
import { RestServiceUser } from 'src/services/rest.service.user';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  rest: RestServiceUser = RestServiceUser.getInstance(this.http);
  profileSub: string = "";

  constructor(
    private http: HttpClient,
    public dialog: MatDialog,
    private router: Router,
    private auth: AuthService
    ) { }

  ngOnInit(): void {
    this.auth.user$
      .subscribe(profile => {
        this.profileSub = profile?.sub ?? '';
        console.log("Sub " + this.profileSub)
        this.rest.hasProfile$(profile?.sub ?? '').subscribe(
          newProfile => {
            if(newProfile.username === '') {
              this.rest.addUserAsync({
                userId: 0,
                username: this.profileSub
              });
            }
          }
        );
      });
  }
}
