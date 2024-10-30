import javax.swing.*;
import java.sql.*;
import java.sql.Driver.*;
public class dbcon {
    Connection con;
dbcon() {

    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection("jdbc:mysql://localhost:3306/airline", "root","1166");
        System.out.println("Register ");


    }

    catch(Exception e){
        e.printStackTrace();
    }

}
    public static void main(String[] args) {

        new dbcon();
    }
}
