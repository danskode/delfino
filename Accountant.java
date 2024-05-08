public class Accountant extends User {
   
   private boolean accountant = true;
   private int usertype = 3;

   public Accountant(String username, String password){
      super.setUsername(username);
      super.setPassword(password);
      super.setUsertype(3);
   }
   
   public String getUsername(String username){
      return getUsername(username);
   }
   
   public int getUsertype(){
      return usertype;
   }
   
   public boolean isAccountant(){
      return accountant;
   }
}