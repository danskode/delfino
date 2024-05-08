import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Session {

   private String username;
   private boolean loggedIn = false;
   private LocalDateTime sessionStarted;
   private int usertype;


   public Session(){
      LocalDateTime myTime = LocalDateTime.now();
      this.sessionStarted = myTime;
   }

   public Session(String username, int usertype){
      LocalDateTime myTime = LocalDateTime.now();
      setUser(username, usertype);
      this.sessionStarted = myTime;
      this.loggedIn = true;
   }
   
   public String getUsername(){
      return username;
   }
   
   public int getUsertype(){
      return usertype;
   }
   
   public void setUser(String username, int usertype){
      this.username = username;
      this.usertype = usertype;
   }
   
   public boolean isLoggedIn(){
      return loggedIn;
   }
   
   public void login(String username, int usertype){
      this.loggedIn = true;
      this.username = username;
      this.usertype = usertype;
      
      try {
         BufferedWriter writer = new BufferedWriter(new FileWriter("Sessions.txt", true)); // 2. parametet booleanen "true" gør, at filen ikke overskrives, men at der tilføjes til den!!!
         writer.write(username + " | " + usertype + " | " + sessionStarted + "\n");
         writer.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public void logout(String username, int usertype){
      this.loggedIn = false;
      this.username = null;
      this.usertype = -1;
   }

   
   public void printSession(){
      System.out.println("User: " + getUsername() + " Usertype: " + usertype + " Logged in: " + loggedIn + " Time: " + sessionStarted);
   }    
}