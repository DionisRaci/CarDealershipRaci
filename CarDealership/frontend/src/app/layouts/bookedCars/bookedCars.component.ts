import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ICar, IUser } from '../../../models/data';
import { AuthService } from '@auth0/auth0-angular';
import { ActivatedRoute, Router } from '@angular/router';
import { RestServiceUser } from 'src/services/rest.service.user';

@Component({
  selector: 'BookedCarComponent',
  templateUrl: './bookedCars.component.html',
  styleUrls: ['./bookedCars.component.scss']
})
export class BookedCarsComponent implements OnInit {
  
  sub: string;
  rest: RestServiceUser = RestServiceUser.getInstance(this.http);
  title = "Dio's Car Dealership";
  user:IUser;
  constructor(private http: HttpClient, private auth: AuthService){
  }

  cars?:ICar[];
  path = "http://localhost:8080/CarDealershipAPI/"
  
  ngOnInit():void{
    this.auth.user$.subscribe(profile => {
      this.sub = profile?.sub ?? ''
      this.rest.hasProfile$(this.sub).subscribe(data => {
        this.user = data;
        this.getBookedCars(this.user.userId);
      })
    })
  }
  
  getBookedCars(userID: number):void{
    this.http.get<ICar[]>(this.path + "bookedCars/" + userID).subscribe(
      data => {
        this.cars = data;
    })
  }
  
  unbookCar(carId: number):void{
    this.http.post(this.path + "unbook/" + this.user.userId + "/" + carId, null).subscribe(data =>{})
    window.location.reload();
  }
}
