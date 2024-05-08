import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;

public class UserList{

   public ArrayList<User> userlist = new ArrayList<>();
   
   public void addUserToUserList(String username, String password, int usertype){
      if( usertype == 1 ){
         
         boolean found = false;
         
         for( User user : userlist ) {
            if( user.getUsername().equals(username) ){
               found = true;
               break;
            } 
         
         } if (found) {
            System.out.println("Brugernavn allerede taget ...");
         } else {        
            Admin newAdmin = new Admin(username, password);
            userlist.add(newAdmin);
            try {
               BufferedWriter writer = new BufferedWriter(new FileWriter("Users.txt", true)); // 2. parametet booleanen "true" gør, at filen ikke overskrives, men at der tilføjes til den!!!
               writer.write(username + "|" + password + "|" + usertype + "\n");
               writer.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
      }
      if(usertype == 2){
         boolean found = false;        
         for( User user : userlist ) {
            if( user.getUsername().equals(username) ){
               found = true;
               break;
            } 
         } if (found) {
            System.out.println("Brugernavn allerede taget ...");
         } else { 
            Trainer newTrainer = new Trainer(username, password);
            userlist.add(newTrainer);
            try {
               BufferedWriter writer = new BufferedWriter(new FileWriter("Users.txt", true)); // 2. parametet booleanen "true" gør, at filen ikke overskrives, men at der tilføjes til den!!!
               writer.write(username + "|" + password + "|" + usertype + "\n");
               writer.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
      }
      if(usertype == 3){
         boolean found = false;
         for( User user : userlist ) {
            if( user.getUsername().equals(username) ){
               found = true;
               break;
            } 
         } if (found) {
            System.out.println("Brugernavn allerede taget ...");
         } else { 
            Accountant newAccountant = new Accountant(username, password);
            userlist.add(newAccountant);
            try {
               BufferedWriter writer = new BufferedWriter(new FileWriter("Users.txt", true)); // 2. parametet booleanen "true" gør, at filen ikke overskrives, men at der tilføjes til den!!!
               writer.write(username + "|" + password + "|" + usertype + "\n");
               writer.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
      }
   }

   // læs fra filen Users.txt
   public void readUsersFromFile(){
      try{

        File file = new File("Users.txt");
        Scanner filescanner = new Scanner(file);
        while (filescanner.hasNextLine()){
            String line = filescanner.nextLine();
            String[] data = line.split("\\|");

            String username = data[0];
            String password = data[1];
            int usertype = Integer.parseInt(data[2]); 
            
            if(usertype == 1){
               Admin admin = new Admin(username,password);
               this.userlist.add(admin);
            }
            if(usertype == 2){
               Trainer trainer = new Trainer(username,password);
               this.userlist.add(trainer);
            }
            if(usertype == 3){
               Accountant accountant = new Accountant(username,password);
               this.userlist.add(accountant);
            }
                                   
            //this.addUserToUserList(username, password, usertype);
            
            //filescanner.close(); 
         }  
      }catch(IOException e){
         System.out.println("Ingen brugere fundet i systemet endnu ...");
      } 
   }

   // toString-metode for ArrayListen
   public void myToString(){
      for(User user : userlist ) {
      
         System.out.println("Brugernavn: " + user.getUsername() + " Er admin: " + user.isAdmin() + " Er træner: " + user.isTrainer() + " Er regnskabsmedarbejder: " + user.isAccountant() );
   
      }
   }
}