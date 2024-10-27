import java.sql.*;
import java.sql.Connection;
import java.sql.Driver;

public class Conn {
    Connection con;
    String stmt;
    public Conn(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Sucessfully Registers");
             con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employeemanagement","root","1166");
            System.out.println("connection sucess");


        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {

        new Conn();
    }
}
