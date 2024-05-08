public class Trainer extends User {
   
   private boolean trainer = true;
   private int usertype = 2;

   public Trainer(String username, String password){
      super.setUsername(username);
      super.setPassword(password);
      super.setUsertype(2);
   }
   
   public String getUsername(String username){
      return getUsername(username);
   }
   
   public int getUsertype(){
      return usertype;
   }
   
   public boolean isTrainer(){
      return trainer;
   }
}