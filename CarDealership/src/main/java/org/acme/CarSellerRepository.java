package org.acme;

import org.acme.Model.*;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CarSellerRepository {

    @Inject
    protected EntityManager em;

    @Transactional
    public User getUser(int userId){
        var query = em.createQuery("SELECT u FROM User u WHERE u.UserID = :userId", User.class);
        query.setParameter("userId", userId);
        return query.getSingleResult();
    }

    @Transactional
    public User getUserFromName(String userName){
        var query = em.createQuery("SELECT u FROM User u WHERE u.Username = :userName", User.class);
        query.setParameter("userName", userName);
        try {
            return query.getSingleResult();
        } catch (NoResultException ex){
            return null;
        }
    }

    @Transactional
    public Car getCar(int carId){
        var query = em.createQuery("SELECT c FROM Car c WHERE c.CarID = :carId", Car.class);
        query.setParameter("carId", carId);
        return query.getSingleResult();
    }
    public List<Car> getAvailableCars(){
        var x = em.createQuery("SELECT c FROM Car c WHERE c.IsBought = false and c.IsBooked = false").getResultList();
        return x;
    }

    public List<Car> getBookedCarsFromUser(int userId){
        try{
            User user = getUser(userId);

            return user.getBookedCars();

        }catch(NoResultException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Transactional
    public int createUser(String userName){
        User benutzer = new User(userName);
        em.merge(benutzer);
        return benutzer.getUserID();
    }

    @Transactional
    public void bookCar(int userId, int carId){
        try{
            User user = getUser(userId);
            Car car = getCar(carId);
            List<Car> bookedCars = user.getBookedCars();

            if(bookedCars.contains(car)){
                return;
            }
            else{
                bookedCars.add(car);
                car.setIsBooked(true);
            }
            user.setBookedCars(bookedCars);

            em.merge(car);
            em.merge(user);
        }catch (NoResultException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Transactional
    public void unbookCar(int userId, int carId){
        try{
            User user = getUser(userId);
            Car car = getCar(carId);
            List<Car> bookedCars = user.getBookedCars();

            if(bookedCars.contains(car)){
                bookedCars.remove(car);
                car.setIsBooked(false);
            }
            user.setBookedCars(bookedCars);

            em.merge(car);
            em.merge(user);
        }catch (NoResultException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Transactional
    public void buyCar(int userId, int carId) {
        var carQuery = em.createQuery("SELECT c FROM Car c WHERE c.CarID = :carId", Car.class);
        carQuery.setParameter("carId", carId);

        var userQuery = em.createQuery("SELECT u FROM User u WHERE u.UserID = :userId", User.class);
        userQuery.setParameter("userId", userId);

        try {
            Car car = carQuery.getSingleResult();
            User user = userQuery.getSingleResult();

            car.setIsBought(true);

            List<Car> list = user.getBookedCars();
            list.add(car);
            user.setBookedCars(list);

            em.merge(car);
            em.merge(user);

        }catch(NoResultException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Transactional
    public void addCar(Car car){
        em.merge(car);
    }

}




































