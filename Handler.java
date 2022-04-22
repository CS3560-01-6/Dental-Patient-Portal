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
            System.out.println("SQLException:: " + e.getMessage());
        }
    }

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
           System.out.println("SQL Exception: " + e.getMessage());
        } 
        System.out.println("Could not connect to the Database");
        return null;
    }

    public static void menu(Connection conn) throws Exception
    {
            // Scanner input = new Scanner(System.in);
            // input.close();

            showTable(conn);
    }

    public static void showTable(Connection conn) throws Exception
    {
        try
        {
            Scanner input = new Scanner(System.in);
            Statement statement = conn.createStatement();


            System.out.print("Select table: ");
            String table = input.nextLine();
            System.out.println("Select column: ");
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
           System.out.println("SQLException: " + e.getMessage());
        } 
    }
}
