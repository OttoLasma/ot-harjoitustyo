/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ridesharing.domain;

/**
 *
 * @author ottlasma
 */
public class Ride {

    private int id;
    private String departureLocation;
    private String destinationLocation;
    private int price;
    private int seatsAvailable;
    private String departureDate;
    private int available;
    private int userId;

    public Ride(String departureLocation, String destinationLocation, int price, int seatsAvailable, String departureDate, int userId) {
        this.departureLocation = departureLocation;
        this.destinationLocation = destinationLocation;
        this.price = price;
        this.seatsAvailable = seatsAvailable;
        this.departureDate = departureDate;
        this.available = 0;
        this.userId = userId;
    }

    public void setUserId(int user) {
        this.userId = user;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    /**
     * @return the DepartureLocation
     */
    public String getDepartureLocation() {
        return departureLocation;
    }

    /**
     * @param departureLocation the DepartureLocation to set
     */
    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    /**
     * @return the DestinationLocation
     */
    public String getDestinationLocation() {
        return destinationLocation;
    }

    /**
     * @param destinationLocation the DestinationLocation to set
     */
    public void setDestinationLocation(String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the seatsAvailable
     */
    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    /**
     * @param seatsAvailable the seatsAvailable to set
     */
    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    /**
     * @return the departureDate
     */
    public String getDepartureDate() {
        return departureDate;
    }

    /**
     * @param departureDate the departureDate to set
     */
    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return this.departureLocation + " " + " " + this.destinationLocation + " " + " " + this.price + " " + this.seatsAvailable;
    }

}
