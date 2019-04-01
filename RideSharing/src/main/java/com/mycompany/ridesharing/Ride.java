/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ridesharing;

/**
 *
 * @author ottlasma
 */
public class Ride {
    private String DepartureLocation;
    private String DestinationLocation;
    private int price;
    private int seatsAvailable;
    private String departureDate;
    private boolean available;
    public Ride(String DepartureLocation, String DestinationLocation, int price, int seatsAvailable, String departureDate ){
        this.DepartureLocation = DepartureLocation;
        this.DestinationLocation = DestinationLocation;
        this.price = price;
        this.seatsAvailable = seatsAvailable;
        this.departureDate = departureDate;
        this.available = true;
    }
    /**
     * @return the DepartureLocation
     */
    public String getDepartureLocation() {
        return DepartureLocation;
    }

    /**
     * @param DepartureLocation the DepartureLocation to set
     */
    public void setDepartureLocation(String DepartureLocation) {
        this.DepartureLocation = DepartureLocation;
    }

    /**
     * @return the DestinationLocation
     */
    public String getDestinationLocation() {
        return DestinationLocation;
    }

    /**
     * @param DestinationLocation the DestinationLocation to set
     */
    public void setDestinationLocation(String DestinationLocation) {
        this.DestinationLocation = DestinationLocation;
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

    /**
     * @return the available
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * @param available the available to set
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }
    @Override
    public String toString(){
        return this.DepartureLocation + " "+ " " + this.DestinationLocation +" "+" " + this.price + " " + this.seatsAvailable;
    }
    
}
