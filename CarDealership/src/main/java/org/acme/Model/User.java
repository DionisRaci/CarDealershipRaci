package org.acme.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private int UserID;

    @Column(name = "username")
    private String Username;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @Column(nullable = true)
    private List<Car> bookedCars;

    public User() {
    }

    public User(String username) {
        setUsername(username);
    }

    public User(String username, List<Car> boughtCars, List<Car> bookedCars) {
        setUsername(username);
        setBookedCars(bookedCars);
    }

    public int getUserID() {
        return UserID;
    }

    public void setUsername(String benutzername) {
        Username = benutzername;
    }

    public String getUsername() {
        return Username;
    }

    public List<Car> getBookedCars() {
        return bookedCars;
    }

    public void setBookedCars(List<Car> bookedCars) {
        this.bookedCars = bookedCars;
    }
}
