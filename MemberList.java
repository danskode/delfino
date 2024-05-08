import java.util.ArrayList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MemberList{

   ArrayList<Member> memberlist = new ArrayList<>();

   public void newMember(){
      
      Scanner sc = new Scanner(System.in);
      
      System.out.print("   Opret et nyt medlem i svømmeklubben");
      System.out.print("");
      
      System.out.print("   Skriv medlemmets fornavn: ");
      String fname = sc.nextLine();
      
      System.out.print("   Skriv medlemmets efternavn: ");
      String lname = sc.nextLine();
      
      System.out.print("   Skriv medlemmets email: ");
      String email = sc.nextLine();
      
      System.out.print("   Skriv medlemmets fødselsdato (yyyy-mm-dd): ");
      String bdayIn = sc.nextLine();
      
      LocalDate bday = null;
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
      try {
         bday = LocalDate.parse(bdayIn, formatter);
            
      } catch (Exception e) {
         System.out.println("Du SKAL indtaste medlemmets fødselsdag sådan her, yyyy-mm-dd, fx 1999-31-12");
      }
      
      Type type = null;      
      System.out.print("   Ønsker medlemmet at deltage i konkurencer? [J]a / [N]ej: ");
      String typeIn = sc.next();
      if( typeIn.equalsIgnoreCase("j") ){
         type = Type.COMPETITIONAL;
      }
      if( typeIn.equalsIgnoreCase("n") ){
         type = Type.RECREATIONAL;
      }
      
      Status status = null;
      System.out.print("   Skal medlemskabet være [A]ktivt eller [P]assivt: ");
      String statusIn = sc.next();
      
      if( statusIn.equalsIgnoreCase("a") ){
         status = Status.ACTIVE;
      }
      if( statusIn.equalsIgnoreCase("p") ){
         status = Status.PASSIVE;
      }
      
      Member member = new Member(fname, lname, bday, type, status, email);
      String memberID = member.getMemberID();
      memberlist.add(member);

      try {
         BufferedWriter writer = new BufferedWriter(new FileWriter("Members.txt", true)); // 2. parametet booleanen "true" gør, at filen ikke overskrives, men at der tilføjes til den!!!
         writer.write(fname + "|" + lname + "|" + bday + "|" + type+ "|" + status+ "|" + email + "|" + memberID + "\n");
         writer.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   // læs fra filen Users.txt
   public void readMembersFromFile(){
      try{

        File file = new File("Members.txt");
        Scanner filescanner = new Scanner(file);
        while (filescanner.hasNextLine()){
            String line = filescanner.nextLine();
            String[] data = line.split("\\|");
            String fname = data[0];
            String lname = data[1];
            LocalDate bday = LocalDate.parse(data[2]); 
            Type type;
            if (data[3].equalsIgnoreCase("COMPETITIONAL")) {
               type = Type.COMPETITIONAL;
            } else {
               type = Type.RECREATIONAL;
            }     
            Status status;
            if (data[4].equalsIgnoreCase("ACTIVE")) {
               status = Status.ACTIVE;
            } else {
               status = Status.PASSIVE;
            }
            String email = data[5];
            String memberID = data[6];
            
            Member member = new Member(memberID, fname, lname, bday, type, status, email);
            
            memberlist.add(member); 
         }  
      }catch(IOException e){
         System.out.println("Ingen medlemmer i systemet endnu ...");
      } 
   }
   
   // Her fjernes en person fra arraylisten memberlist + i txt-filen ...
   
   public void removeMember(String memberID){  
      for( int i = 0 ; i < memberlist.size() ; i++ ){
         if (memberlist.get(i).getMemberID().equals(memberID)) {
             memberlist.remove(i);
             break;
         }
      }
      String filename = "Members.txt";
      String searchString = memberID;

      try {
        ArrayList<String> lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;

         // Læs filen linje for linje og gem linjerne i en liste
         while ((line = reader.readLine()) != null) {
            lines.add(line);
         }
         reader.close();

         // Find den sidste linje, der indeholder søgestrengen
         int lastMatchIndex = -1;
         for (int i = lines.size() - 1; i >= 0; i--) {
            if (lines.get(i).contains(searchString)) {
               lastMatchIndex = i;
               break;
            }
         }

         // Hvis en matchende linje blev fundet, fjern den fra listen
         if (lastMatchIndex != -1) {
           lines.remove(lastMatchIndex);

           // Skriv ArrayListens linjer til filen ...
           BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
           for (String l : lines) {
               writer.write(l);
               writer.newLine();
           }
           writer.close();

           System.out.println("Medlemmet med " + searchString + " blev fjernet fra filen.");
         } else {
            System.out.println("Ingen matchende linje blev fundet.");
         }
      } catch (IOException e) {
         System.out.println("Der opstod en fejl: " + e.getMessage());
      }    
   }
   
   public void printMemberList(){
      System.out.println("");
      System.out.println("   * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
      for(Member member : memberlist){
         System.out.println("");
         System.out.println("   Medlem: " + member.getMemberID() + " " + member.getFname() + " * " + member.getLname() + " * Medlemspris: " + member.getSubscription() + " * Alder: " + member.getAge() + " * Status på medlemsskab: " +  member.getStatus() + " * Medlemstype: " + member.getType() + " *" );
         System.out.println("");
      }
      System.out.println("   * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
      System.out.println("");
   }
}