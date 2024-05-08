public abstract class User {
   private String username;
   private String password;
   private boolean admin = false;
   private boolean trainer = false;
   private boolean accountant = false;
   private int usertype = 0;
   
   public String getUsername(){
      return username;
   }
      
   public void setUsername(String username){
      this.username = username;
   }
   
   public String getPassword(){
      return password;
   }
   public void setPassword(String password){
      this.password = password;
   }
   
   public boolean isAdmin(){
      return admin;
   }
   public boolean isTrainer(){
      return trainer;
   }
   public boolean isAccountant(){
      return accountant;
   }
   
   public int getUsertype(){
      return usertype;
   }
   
   public void setUsertype(int usertype){
      this.usertype = usertype;
   }
}