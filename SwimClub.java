import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SwimClub{

   Scanner sc = new Scanner(System.in);
   UserList userlist = new UserList();
   Session mySession = new Session();
   MemberList memberlist = new MemberList();
   
   public void start(){
      
      userlist.readUsersFromFile();
      memberlist.readMembersFromFile();      
      
      boolean sentinel = true;
      
      while(true){
         if(mySession.isLoggedIn() == false){
            System.out.println("""
             
               * * * * * * * * * * * * * * * * * * * * * * * * * * *
               *                                                   *
               *        VELKOMMEN TIL SVØMMEKLUBBEN DELFINEN       *
               *                        /\\                         *
               *            -----------/  \\------\\  /|             *
               *           /                      \\/ |             *
               *          /  {-}                     |             *
               *         <                           |             *
               *          \\        \\ |            /\\ |             *
               *           \\_ _ _ _ \\| _ _ _ _ _ /  \\|             *
               *                     v                             *
               *                                                   *      
               *   af Celine, Christoffer, Laura, Nicolai & Sofie  *
               *                                                   *
               * * * * * * * * * * * * * * * * * * * * * * * * * * *
            """);
            
            boolean found = false;
            while(!found){
               
               System.out.println("");
               System.out.print("   Indtast brugernavn: ");  
               String username = sc.next();
               
               System.out.print("   Indtast kodeord: ");
               String password = sc.next();
               System.out.println("");
                               
               for (User user : userlist.userlist) { 
                     
                  if(user.getUsername().equalsIgnoreCase(username) && user.getPassword().equalsIgnoreCase(password) ){
                           
                     found = true;
                     int usertype = user.getUsertype();
                     mySession.login(username, usertype);
                     
                     //mySession.printSession();
                  }
               }
            }
         } else if (mySession.isLoggedIn() ){

                     int usertype = mySession.getUsertype();
                     
                     if (usertype == 0) {
                        // kan evt. bruges til at lave en menu til almindelige brugere ... pt. uden for kategori (0 bruges som standard i User-klassen, men overskrives u sub-klasserne)
                        System.out.println("Du er alm. medlem ... ");
                     }
                     if (usertype == 1) {
                        // Start admin menu ...
                        AdminView aw = new AdminView(userlist, mySession, memberlist);
                        aw.isLoggedIn();
                     }
                     if (usertype == 2) {
                        // Start trainer menu ...
                        System.out.println("Du er logget ind som træner");
                     }
                     if (usertype == 3) {
                        // Start accountant menu ...
                        System.out.println("Velkommen til regnskabet!");
                     }
         }
      }
   }
}