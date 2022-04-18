import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ICar, IUser } from '../../../models/data';
import { AuthService } from '@auth0/auth0-angular';
import { ActivatedRoute, Router } from '@angular/router';
import { RestServiceUser } from 'src/services/rest.service.user';

@Component({
  selector: 'CarComponent',
  templateUrl: './car.component.html',
  styleUrls: ['./car.component.scss']
})
export class CarComponent implements OnInit {
  
  sub: string;
  rest: RestServiceUser = RestServiceUser.getInstance(this.http);
  title = "Dio's Car Dealership, Your Cars";
  user:IUser;
  constructor(private http: HttpClient, private auth: AuthService){
  }
  
  cars?:ICar[];
  path = "http://localhost:8080/CarDealershipAPI/"
  
  ngOnInit():void{
    this.getAvailableCars();
    this.auth.user$.subscribe(profile => {
      this.sub = profile?.sub ?? ''
      this.rest.hasProfile$(this.sub).subscribe(data => {
        this.user = data;
      })
    })
  }
  
  getAvailableCars():void{
    this.http.get<ICar[]>(this.path + "availableCars").subscribe(
      data => {
        this.cars = data;
    })
  }
  
  bookCar(carId: number):void{
    this.http.post(this.path + "book/" + this.user.userId + "/" + carId, null).subscribe(data =>{})
    window.location.reload();
  }
  
  buyCar(carId:number):void{
    this.http.post(this.path + "buy/" + this.user.userId + "/" + carId, null).subscribe(data =>{})
    window.location.reload();
  }
}
