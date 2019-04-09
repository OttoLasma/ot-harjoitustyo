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

import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ottlasma
 */
public class kokeiluKayttoliittyma {
    private static Scanner scanner;
    
    public kokeiluKayttoliittyma(Scanner scanner){
        this.scanner = scanner;
    }
    
    public static void start(){
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Ride> rides = new ArrayList<>();
        while(true){
            System.out.println("Commands: ");
            System.out.println(" x - exit");
            System.out.println(" 1 - new user");
            System.out.println(" 2 - add new ride");
            System.out.println(" 3 - list available rides");
            String command = scanner.nextLine();
            if(command.equals("x")){
                break;
            }
            if(command.equals("1")){
                System.out.println("Provide needed information in order to sign in");
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
                User user = new User(name, surname, phone, email, username); // add to database table user (password information is included when javafx is implemented)
                users.add(user);
            }
            if(command.equals("2")){
                System.out.println("Provide the needed information below in order to add new ride:");
                System.out.println("Location of departure");
                String departure = scanner.nextLine();
                System.out.println("Location of destination");
                String destination = scanner.nextLine();
                System.out.println("Total price for the ride");
                int price = Integer.parseInt(scanner.nextLine());
                System.out.println("Number of available rides");
                int seats = Integer.parseInt(scanner.nextLine());
                System.out.println("Time and date of departure (mm/dd-hh/mm)");
                String date  = scanner.nextLine();
                Ride ride = new Ride(departure, destination, price, seats, date); // add to database table ride (password information is included when javafx is implemented)
                rides.add(ride);
            }
            if(command.equals("3")){
                for(Ride ride : rides){
                    if(ride.isAvailable()){
                        System.out.println(ride);
                    }
                }
            }
        }
    }
}
