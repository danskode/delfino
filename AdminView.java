import java.util.Scanner;

public class AdminView {

   private UserList userlist;
   private Session session;
   private MemberList memberlist;
   
   public MemberList getMemberList(){
      return memberlist;
   }

   public UserList getUserlist(){
      return userlist;
   }
   
   public Session getSession(){
      return session;
   }
   
   private void setUserlist(UserList userlist){
      this.userlist = userlist;
   }
   private void setMemberlist(MemberList memberlist){
      this.memberlist = memberlist;
   }

   public AdminView(UserList userlist, Session session, MemberList memberlist){
      this.userlist = userlist;
      this.session = session;
      this.memberlist = memberlist;
   }

   public void isLoggedIn(){
      
      Scanner sc = new Scanner(System.in);
      
      System.out.println("""
         * * * * * * * * * * * * * * * * * * * *
         *                                     *
         *  ADMINISTRATORMENU:                 *
         *                                     *
         *  [1] Opret nyt medlem               *
         *  [2] Rediger/slet medlem - mangler  *
         *  [3] Se liste over medlemmer        *
         *  [4] Opret ny systembruger          *
         *  [0] Log dig ud                     *
         *                                     *
         * * * * * * * * * * * * * * * * * * * *
         
      """);
      
      int adminMenu = sc.nextInt();

      if(adminMenu == 1){
         System.out.println("   Her mangler noget funktionalitet endnu ... ");
         memberlist.newMember();
      }
      
      if(adminMenu == 2){
         System.out.println("""
            
            Ønsker du at
            [1] Ændre en bruger
            [2] Slette en bruger

         """);
         int memberEditMenu = sc.nextInt();
         if(memberEditMenu == 1){
            System.out.println("   Her mangler noget funktionalitet endnu ... ");
         }
         if(memberEditMenu == 2){
            System.out.print("   Indtast nummeret på det medlem, du ønsker at slette: ");
            String memberID = sc.next();
            memberlist.removeMember(memberID);
         }
      }
      
      if(adminMenu == 3){
         memberlist.printMemberList();
      }
      
      if(adminMenu == 4){
         System.out.println("""
         
            * * * * * * * * * * * * * * * * * * * *
                                                  
                Opret en ny bruger i systemet:    
                [1] Administrator                 
                [2] Træner                        
                [3] Regnskabsmedarbejder          
                                                 
         """);
         int usertype = sc.nextInt();
         
         System.out.println();                  
         System.out.print("   Indtast ønskede brugernavn: ");
         String username = sc.next();
         System.out.print("   Indtast ønskede adgangskode: ");
         String password = sc.next();
                  
         userlist.addUserToUserList(username, password, usertype);
      }
      if(adminMenu == 0){
         Session mySession = getSession();
         String username = mySession.getUsername();
         int usertype = mySession.getUsertype();
         mySession.logout(username, usertype);
      }        
   }
}