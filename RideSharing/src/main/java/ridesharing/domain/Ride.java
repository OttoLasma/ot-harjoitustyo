
package ridesharing.domain;


public class Ride {

    private int id;
    private String departurelocation;
    private String destinationlocation;
    private int price;
    private int seats;
    private String date;
    private int available;
    private int userId;
    
    
    public Ride(){
        
    }
    public Ride(String departurelocation, String destinationlocation, int price, int seats, String date, int userId) {
        this.departurelocation = departurelocation;
        this.destinationlocation = destinationlocation;
        this.price = price;
        this.seats = seats;
        this.date = date;
        this.available = 0;
        this.userId = userId;
    }
//conn.prepareStatement("DROP TABLE Ride IF EXISTS;").executeUpdate();
//conn.prepareStatement("CREATE TABLE Ride(id integer auto_increment, departurelocation varchar(255), destinationlocation varchar(255), price integer, seats integer, date varchar(255), user_id integer, available integer, primary key (id), foreign key (user_id) references User(id));").executeUpdate();

    
    @Override
    public String toString() {
        return this.getDeparturelocation() + " " + " " + this.getDestinationlocation() + " " + " " + this.getPrice() + " " + this.getSeats();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the departurelocation
     */
    public String getDeparturelocation() {
        return departurelocation;
    }

    /**
     * @param departurelocation the departurelocation to set
     */
    public void setDeparturelocation(String departurelocation) {
        this.departurelocation = departurelocation;
    }

    /**
     * @return the destinationlocation
     */
    public String getDestinationlocation() {
        return destinationlocation;
    }

    /**
     * @param destinationlocation the destinationlocation to set
     */
    public void setDestinationlocation(String destinationlocation) {
        this.destinationlocation = destinationlocation;
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
     * @return the seats
     */
    public int getSeats() {
        return seats;
    }

    /**
     * @param seats the seats to set
     */
    public void setSeats(int seats) {
        this.seats = seats;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the available
     */
    public int getAvailable() {
        return available;
    }

    /**
     * @param available the available to set
     */
    public void setAvailable(int available) {
        this.available = available;
    }

    /**
     * @return the user_id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the user_id to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Ride)) {
            return false;
        }
        
        Ride other = (Ride) obj;
        return departurelocation.equals(other.departurelocation);
    }
    

}