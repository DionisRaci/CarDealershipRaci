import {Component, OnInit} from '@angular/core';
import { AuthService } from '@auth0/auth0-angular';
@Component({
  selector: 'cardealer-navbar',
  templateUrl: './navbar.component.html',
})
export class NavbarComponent implements OnInit {


  constructor(public auth: AuthService) { }

  ngOnInit(): void {
  }
}
