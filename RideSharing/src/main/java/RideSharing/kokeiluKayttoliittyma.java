
package RideSharing;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@Component
public class kokeiluKayttoliittyma {
    @Autowired
    RideDao rideDao;
    @Autowired
    UserDao userDao;
    private Scanner scanner;
    
    
    public kokeiluKayttoliittyma(){
        
          
    }
    public void start(Scanner scanner) throws SQLException{
        this.scanner = scanner;
        while(true){
            System.out.println("Commands: ");
            System.out.println(" x - exit");
            System.out.println(" 1 - Sign up");
            System.out.println(" 2 - Log in");
            String command = scanner.nextLine();
            if(command.equals("x")){
                break;
            }
            if(command.equals("1")){
                //List<User> list = userDao.list();
                /*List<String> usernames = new ArrayList<>();
                for(User user : list){
                    usernames.add(user.getUsername());
                }*/
                System.out.println("Provide below requested information in order to sign in");
                System.out.println("Name");
                String name = scanner.nextLine();
                System.out.println("Surname");
                String surname = scanner.nextLine();
                System.out.println("email");
                String email = scanner.nextLine();
                System.out.println("Phone");
                String phone = scanner.nextLine();
                System.out.println("Username");
                String username  = scanner.nextLine();
                /*if(usernames.contains(username)){
                    System.out.println("This username is already taken");
                    while(true){
                        System.out.println("Username");
                        username  = scanner.nextLine();
                        if(usernames.contains(username)){
                            System.out.println("This username is already taken");
                        }else{                      
                            break;
                        }
                    }
                }   */         
                System.out.println("Password");
                String password  = scanner.nextLine();
                User user = new User(name, surname, phone, email, username, password); // add to database table user (password information is included when javafx is implemented)
                userDao.create(user);
                features(user);
            }
            if(command.equals("2")){
                System.out.println("Username");
                String username = scanner.nextLine();
                System.out.println("password");
                String password = scanner.nextLine();
                for(User user :userDao.list()){
                    if(user.getUsername().equals(username)){
                        if(user.getPassword().equals(password)){
                            features(user);
                        }
                    }
                }
                System.out.println("Incorrect username or password");
                start(scanner);             
            }         
        }
    }
    private void features(User user) throws SQLException{ 
        while (true){
            System.out.println("Commands: ");
            System.out.println(" 1 - Add new ride");
            System.out.println(" 2 - List all available rides");
            System.out.println(" x - Log out");
            String command = scanner.nextLine();
            if(command.equals("x")){
                start(scanner);
            }
            if(command.equals("1")){
                System.out.println("Provide the needed information below in order to add new ride:");
                System.out.println("Location of departure");
                String departure = scanner.nextLine();
                System.out.println("Location of destination");
                String destination = scanner.nextLine();
                System.out.println("Total price for the ride");
                int price = Integer.parseInt(scanner.nextLine());
                System.out.println("Number of available rides");
                int seats = Integer.parseInt(scanner.nextLine());
                System.out.println("Estimated Time and date of the departure (mm/dd-hh/mm)");
                String date  = scanner.nextLine();
                Ride ride = new Ride(departure, destination, price, seats, date, user.getId()); // add to database table ride (password information is included when javafx is implemented)
                rideDao.create(ride);
            }
            if(command.equals("2")){
                for(Ride ride : rideDao.list()){
                    if(ride.getAvailable() == 0){
                        System.out.println(ride);
                    }
                }
            }
            
        }
    }
}
