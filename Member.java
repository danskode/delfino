import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

public class Member {
   private String memberID;
   private String fname;
   private String lname;
   private String email;
   private LocalDate bday;
   private int age;
   private boolean subPaid = false;
   // private String membership;
   private Status status; 
   private Type type;
   private int subscription; // junior(0-17), senior (18-59), 60+ 



   public Member(String fname, String lname, LocalDate bday, Type type, Status status, String email){
      this.fname = fname;
      this.lname = lname;
      this.bday = bday;
      this.type = type;
      this.status = status;
      this.email = email;
      
      String uniqueKey = UUID.randomUUID().toString();
      String shortKey = uniqueKey.substring(0,5);
      this.memberID = shortKey;
      // this.memberID = System.currentTimeMillis();
      setAge(bday);
      
      int age = getAge();   
      setSubscription(age, status);    
   }
   public Member(String memberID, String fname, String lname, LocalDate bday, Type type, Status status, String email){
      this.memberID = memberID;
      this.fname = fname;
      this.lname = lname;
      this.bday = bday;
      this.type = type;
      this.status = status;
      this.email = email;
      
      this.memberID = memberID;
      setAge(bday);
      
      int age = getAge();   
      setSubscription(age, status);
      
   }
   

   public String getMemberID(){
      return memberID;
   }

   public void setFname(String fname){
      this.fname = fname;
   }
   public String getFname(){
      return fname;
   }
   public void setLname(String lname){
      this.lname = lname;
   }
   public String getLname(){
      return lname;
   }
   public LocalDate getBday(){
      return bday;
   }
   
   public int getAge(){
      return age;
   }
   
   public Status getStatus(){
      return status;
   }
   
   public void setStatus(Status status){
      this.status = status;
   }
   public Type getType(){
      return type;
   }
   public void setType(Type type){
      this.type = type;
   }   
   
   public void setAge(LocalDate bday){
      LocalDate today = LocalDate.now();
      
      Period period = Period.between(bday, today);
      int age = period.getYears();
      
      this.age = age;
   }
   
   public void setSubscription(int age, Status status){
      if(age < 18 && status == Status.ACTIVE){
         this.subscription = 1000;
      }
      if(age < 18 && status == Status.PASSIVE){
         this.subscription = 500;
      }
      if(age >= 18 && age < 60 && status == Status.ACTIVE){
         this.subscription = 1500;
      }
      if(age >= 18 && age < 60 && status == Status.PASSIVE){
         this.subscription = 500;
      }
      if(age >= 60 && status == Status.ACTIVE){
         this.subscription = (1500/100)*75;
      }
      if(age >= 60 && status == Status.PASSIVE){
         this.subscription = 500;
      }      
   }
   
   public int getSubscription(){
      return subscription;
   }
   
   public boolean isSubPaid(Boolean paid){
      return subPaid;
   }
   public void setSubPaid(Boolean paid){
      this.subPaid = paid;
   }
}  