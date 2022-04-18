package org.acme;

import org.acme.Model.Car;
import org.acme.Model.User;
import org.acme.Model.UserDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("CarDealershipAPI")
public class CarSellerAPI {

    @Inject
    private CarSellerRepository db;

    @GET
    @Path("availableCars")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Car> getAllAvailableCars() {
        return db.getAvailableCars();
    }

    @GET
    @Path("bookedCars/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Car> getBookedCarsFromUser(@PathParam("id") int id){
        return db.getBookedCarsFromUser(id);
    }

    @Path("book/{userId}/{carId}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String bookCarForUser(@PathParam("userId") int userId, @PathParam("carId") int carId) {
        db.bookCar(userId, carId);
        return "Done";
    }

    @Path("unbook/{userId}/{carId}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String unbookCarForUser(@PathParam("userId") int userId, @PathParam("carId") int carId) {
        db.unbookCar(userId, carId);
        return "Change applied";
    }

    @Path("buy/{userId}/{carId}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String buyCar(@PathParam("userId") int userId, @PathParam("carId") int carId){
        db.buyCar(userId, carId);
        return "Done";
    }

    @Path("addCar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addCar(Car car){
        db.addCar(car);
        return "Done";
    }

    @Path("users/{userName}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createUser(@PathParam("userName") String name) {
        db.createUser(name);
        return name;
    }

    @Path("users/{userName}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserDAO checkUser(@PathParam("userName") String name) {
        var x = db.getUserFromName(name);
        UserDAO u = new UserDAO("", 0);
        if (x == null) return u;
        UserDAO n = new UserDAO(x.getUsername(), x.getUserID());
        return n;
    }
}
