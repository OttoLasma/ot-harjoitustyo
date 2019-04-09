/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RideSharing;

/**
 *
 * @author ottlasma
 */
public class Ride {
    private int id;
    private String DepartureLocation;
    private String DestinationLocation;
    private int price;
    private int seatsAvailable;
    private String departureDate;
    private int available;
    private int user_id;
    public Ride(String DepartureLocation, String DestinationLocation, int price, int seatsAvailable, String departureDate, int user_id){
        this.DepartureLocation = DepartureLocation;
        this.DestinationLocation = DestinationLocation;
        this.price = price;
        this.seatsAvailable = seatsAvailable;
        this.departureDate = departureDate;
        this.available = 0;
        this.user_id = user_id;
    }
    
    public void setUser_id(int user){
        this.user_id =user;
    }
    public int getUser_id(){
        return this.user_id;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
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

 
    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
    @Override
    public String toString(){
        return this.DepartureLocation + " "+ " " + this.DestinationLocation +" "+" " + this.price + " " + this.seatsAvailable;
    }
    
}
