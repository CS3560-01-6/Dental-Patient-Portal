import java.sql.*;
import java.util.*;

public class Handler {
    public static void main(String[] args) throws Exception
    {
        try
        {
            Connection conn = connectDB();
            menu(conn);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    // end main 

    public static Connection connectDB() throws Exception
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Password should be what you entered for your local mySQL DB
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs3560group6","root","2LsQL@:O");  
            System.out.println("Connected to Database");
            return conn;
        }
        catch(Exception e)
        {
           System.out.println(e.getMessage());
        } 
        System.out.println("Could not connect to the Database");
        return null;
    }
    // end connectDB 

    public static void menu(Connection conn) throws Exception
    {
            // Scanner input = new Scanner(System.in);
            // input.close();
            // showTable(conn);
            // updateEmail(conn);
            // ERROR NO LINE FOUND (When calling showTable after updateEmail)
    }
    // end menu 

    public static void updateEmail(Connection conn) throws Exception
    {
        try 
        {
            Scanner input = new Scanner(System.in);
            boolean exists = true;
            // Create statement object
            Statement stmt = conn.createStatement();
            // Read patientID 
            System.out.print("Enter your patientID: ");
            String pID = input.nextLine();
            // Check if patientID exists
            ResultSet rs = stmt.executeQuery("Select * from patient WHERE patientID = " + pID + ";");
            if(!rs.next())
            {
                System.out.println("Patient ID Does Not Exist!");
                exists = false;
            }
            // Read new email address
            if(exists)
            {
                System.out.print("Enter your new email address: ");
                String email = input.nextLine();
                // Execute Update Query 
                stmt.executeUpdate("UPDATE patient SET email = '" + email + "' WHERE patientID = '" + pID + "';");
                System.out.println("Your email address was successfully updated.");
            }
            else
                System.out.println("Your email address could not be updated.");
            // Close input 
            input.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    // end updateEmail

    public static void showTable(Connection conn) throws Exception
    {
        try
        {
            Scanner input = new Scanner(System.in);
            Statement statement = conn.createStatement();

            System.out.print("Select table: ");
            String table = input.nextLine();
            System.out.print("Select column: ");
            String column = input.nextLine();

            ResultSet rs = statement.executeQuery("SELECT " + column + " FROM " + table);            
            ResultSetMetaData rsmd = rs.getMetaData();
            int col = rsmd.getColumnCount();
            while(rs.next())
            {   
                for(int i = 1; i <= col; i++)
                {
                    String colVal = rs.getString(i);
                    System.out.print(colVal + " ");
                }
                System.out.println("");
            }
            input.close();
        }   

        catch(Exception e)
        {
           System.out.println(e.getMessage());
        } 
    }
    // end showTable
}
// end Handler.java
