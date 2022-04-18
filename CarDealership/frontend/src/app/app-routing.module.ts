import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookedCarsComponent } from './layouts/bookedCars/bookedCars.component';
import { CarComponent } from './layouts/cars/car.component';

const routes: Routes = [
  { path: 'car', component: CarComponent },
  { path: 'bookedCars', component: BookedCarsComponent }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
