package pdfreader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class dbcon {



public static void main() throws SQLException, FileNotFoundException, IOException,
    ClassNotFoundException {

    String[] x = null;
    String[] arr9 = null;
    String[] arr2 = null;
    String z1 = null;
    String shipTo="";
    String vAcctno=null,vendor=null,ship=null;
    // TODO code application logic here
    try {

      String[] faults = { "Purchase Order", "PO Date", "Need By", "A-PCCC5", "Ship To",
          "Shipping Terms", "Vendor Acct No.", "Vendor Item #", "Ordered", "Unit Price",
          "Ext. Price", "Total" };
      Class.forName("org.postgresql.Driver");
      Connection con = null;

      String url = "jdbc:postgresql://localhost:5432/openbravo";
      String user = "postgres";
      String password = "123456";
      BufferedReader reader = new BufferedReader(new FileReader("D://converted0.txt"));
      String line = reader.readLine();
      int j = 0;
      int counter1=0;
      String[] m = new String[2000];
      //String[] line3=new String[100];
      while (line != null) {
        counter1=counter1+1;
        if(counter1==4){
        	vendor=line;
        	//vendor+=vendor;
        	
        }
        if(counter1==5){
        	vendor=vendor+line;
        System.out.println(vendor);
        }
        if(counter1==10){
        String	line3[]=line.split(" ");
        for(int i=4;i<7;i++){
        	shipTo=shipTo+" "+line3[i];
        	System.out.println(line3[i]);
        }
        System.out.println(shipTo);
        }
        if(counter1==11){
        	//line3=line.split(" ");
        }
        if(counter1==14){
        	String line2[]=line.split(" ");
        	System.out.println(line2[1]);
        	vAcctno=line2[1];
        }
        
    	  for (int i = 0; i < 10; i++) {
          // int c =0;
          if (line.contains(faults[i])) {
            String k = faults[i];
            Pattern pattern = Pattern.compile(k + "(.*)" + "(.*)");

            Pattern pattern1 = Pattern.compile("/^[a-zA-Z/s/d//]*/d[a-zA-Z/s/d//]*$/");
            Matcher matcher = pattern.matcher(line);
            Matcher matcher1 = pattern1.matcher(line);
            if (matcher.find()) {
              m[j] = matcher.group(1);
              j++;
            }
            if (matcher1.find()) {
              m[j] = matcher1.group(0);
              j++;
            }
            // Pattern p2=Pattern
            // Pattern pattern2=Pattern.compile();
            // Matcher matcher2=pattern2.matcher(line);
            // if ()

          }
        }
        line = reader.readLine();

      }
      int counter = 0;
      //int z00=0;
      BufferedReader br1 = new BufferedReader(new FileReader("D://Converted0.txt"));
      String line1;
      while ((line1 = br1.readLine())!= null) {
        // process the line.
        counter++;
//       System.out.println(line1);
       // if(line1.matches("^\\d.*$"))
        if((counter==15))
        {
        switch (counter) {
        
        case 15:// for the matching of group items
        	System.out.println("case 15");  
        Pattern expression = Pattern.compile("\\d+\\s+\\b+.*");
          String z = "";
          String mystring = br1.readLine();

          Matcher matcher = expression.matcher(mystring);

          while (matcher.find())
          {z = matcher.group();}
          x = z.split(" ");
          for (int i = 0; i < x.length; i++) {
            System.out.println(i + ":" + x[i]);
          }
          
          // mystring.split("|", 1);
        
          // to be stripped till 4th character+11+12

        case 16:// for the matching ofgroup items
          Pattern expression1 = Pattern.compile("\\d+\\s+\\b+.*");
          String z7 = "";
          System.out.println("case 16");
          mystring = br1.readLine();

          Matcher matcher3 = expression1.matcher(mystring);

          while (matcher3.find()){
            z7 = matcher3.group();}
          arr2 = z7.split(" ");
          for (int i1 = 0; i1 < arr2.length; i1++) {
            System.out.println(i1 + ":" + arr2[i1]);
          }
          //System.out.println(15 + ":" + arr2[15]);

          // mystring.split("|", 1);
      
          // to be stripped till 4th character+11+12

        case 17:// for the matching ofgroup items
          Pattern expression2 = Pattern.compile("\\d+\\s+\\b+.*");
        
          String z9 = "";
      
          //System.out.println(2);
          mystring = br1.readLine();
          System.out.println(mystring);
         Matcher matcher2 = expression2.matcher(mystring);
          //System.out.println(4);
          while (matcher2.find()){
              z9 = matcher2.group();}
            arr9 = z9.split(" ");
            for (int i1 = 0; i1 < arr9.length; i1++) {
              System.out.println(i1 + ":" + arr9[i1]);
            }
            //System.out.println(15 + ":" + arr2[15]);

            // mystring.split("|", 1);
            
            // to be stripped till 4th character+11+12
         

        case 18:
          Pattern expression3 = Pattern.compile("(\\d+).(\\d)");
          String total = br1.readLine();
          z1 = "";
          System.out.println("HI"+total);
          Matcher matcher1 = expression3.matcher(total);
          while (matcher1.find())
          { z1 = matcher1.group(0);}
          System.out.println(z1 + "yaha z1 he");

          break;
        
        }}

       
        
       
      }

      // Regex reggie = new Regex(@"^(?<name>\w[\s\w]+?)\s*(?<num>\d+\s*[a-z]?)$",
      // RegexOptions.IgnoreCase());
      con = DriverManager.getConnection(url, user, password);
      // Statement st = con.createStatement();
      
      PreparedStatement pst = null;
      String query;
      // m[5]="shipping";
      System.out.println("yaha query he niche");
      query = "INSERT INTO pdf_pdfreader1(PODate,"
          + "                   NeedBy,"
          + "                   Vendor,"
          + "                   shipto,"
          + "                   shippingterms,"// delivered always
          + "                   vendoracctno," + "                   vendoritemno1,"
          + "                   Ordered1,"
          + "                   UnitPrice1,"
          + "                   ExtPrice1,"
          + "                   Total,"
          // + "                   UID,"
          + "                   PurchaseOrder," + "vendoritemno2," + "Ordered2,"
          + "                   UnitPrice2," + "                   ExtPrice2," + "vendoritemno3,"
          + "                   Ordered3," + "                   UnitPrice3,"
          + "                   ExtPrice3)     "
                    
          + "            VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

      pst = con.prepareStatement(query);
      // String zt=Integer.toString('\+');
  
      for(int i=0;i<15;i++){
    	  System.out.println("for arrays");
      System.out.println(arr2[i]);
      System.out.println(arr9[i]);
      System.out.println(x[i]);
      }
      
      pst.setTimestamp(1, (Timestamp.valueOf(m[1] + " 00:00:00")));

      
      pst.setTimestamp(2, (Timestamp.valueOf(m[2] + " 00:00:00")));
      
      pst.setString(3, vendor);

      
      pst.setString(4, shipTo);

      pst.setString(5, "DELIVERED");
  
      pst.setString(6, vAcctno);

     
      pst.setString(7, x[1]);
      System.out.println(m[0]);
     
      pst.setFloat(8, Float.parseFloat(x[12]));
     
      pst.setFloat(9, Float.parseFloat(x[14]));
      
      pst.setFloat(10, Float.parseFloat(x[15]));
     
      pst.setFloat(11, Float.parseFloat(z1));

    //change on each run time
      pst.setString(12, m[0]);
      
      pst.setString(13, arr2[1]);
      
      
      pst.setFloat(14, Float.parseFloat(arr2[11]));
      
      pst.setFloat(15, Float.parseFloat(arr2[13]));
      
      pst.setFloat(16, Float.parseFloat(arr2[14]));
      pst.setString(17, arr9[1]);
      
      
      pst.setFloat(18, Float.parseFloat(arr9[12]));
      
      pst.setFloat(19, Float.parseFloat(arr9[14]));
      
      pst.setFloat(20, Float.parseFloat(arr9[15]));
      pst.execute();
      // m[1]="2014-04-03";
      // String
      // query=" CREATE TABLE ne6(PurchaseOrder character(15),PODate varchar(15),NeedBy varchar(15),Vendor varchar(20),ShipTo varchar(40),ShippingTerms character(20),VendorAcctNo character(20),VendorItemNo character(20),Ordered varchar(20),UnitPrice double precision,ExtPrice double precision,Total double precision,id integer,CONSTRAINT ne6_pk PRIMARY KEY (id));";
      // pst.executeUpdate(query);

      // query="SET datestyle = 'ISO,DMY';";
      // pst.executeUpdate(query);
      // 'm[0]','m[1]','m[2]','m[3]','m[4]','m[5]','m[6]','m[7]','m[8]','m[9]','m[10]','m[11]')";
      // pst.executeUpdate(query);
      // query="Select * from public.freeware";
      // st.executeQuery(query);

    } catch (Exception e) {

      System.out.println(e);

    }
  }
}
