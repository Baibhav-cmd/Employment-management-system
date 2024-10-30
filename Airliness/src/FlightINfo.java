import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.ExecutionException;
import net.proteanit.sql.*;

public class FlightINfo extends JFrame {
    FlightINfo(){
        getContentPane().setBackground(Color.white);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(800,500);
        setLocationRelativeTo(null);
// making table
        JTable table=new JTable();
        // database
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/airline","root","1166");
            PreparedStatement ps=con.prepareStatement("select * from flight");
            ResultSet rs =ps.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
            ps.close();
            con.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }


        JScrollPane js=new JScrollPane(table);
        js.setBounds(0,20,800,450);
        add(js);



    }

    public static void main(String[] args) {
        new FlightINfo();
    }
}
