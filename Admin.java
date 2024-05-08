public class Admin extends User {
   
   private boolean admin = true;
   private int usertype = 1;

   public Admin(String username, String password){
      super.setUsername(username);
      super.setPassword(password);
      super.setUsertype(1);
   }
   
   public String getUsername(String username){
      return getUsername(username);
   }
   
   public int getUsertype(){
      return usertype;
   }
   
   public boolean isAdmin(){
      return admin;
   }
}