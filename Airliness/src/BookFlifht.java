import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.Random;

import com.toedter.calendar.JDateChooser;
import net.proteanit.sql.*;


public class BookFlifht extends JFrame  implements ActionListener {
    JDateChooser jd;
    JLabel Heading,Citizenship,name,Nationality,Address,Gender,Source,Desintation,FlightName, FlightCode,DateofTravel;
    JTextField jt1;
    JButton jb1,jb2,jb3;
    JLabel j1,j2,j3,j4,j5;
    Choice c1,c2;
    BookFlifht( ){
        getContentPane().setBackground(Color.white);
        setVisible(true);
        setSize(800,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        Heading=new JLabel("Book Flight");
        Heading.setFont(new Font("Arial" ,Font.BOLD,30));
        Heading.setForeground(Color.cyan);
        Heading.setBounds(300,20,300,30);
        add(Heading);

        Citizenship=new JLabel("Citizenship");
        Citizenship.setBounds(10,50,100,20);
        add(Citizenship);

        jt1=new JTextField();
        jt1.setBounds(115,50,100,20);
        add(jt1);

        jb1=new JButton("Fetch user");
        jb1.setBackground(Color.black);
        jb1.setForeground(Color.white);
        jb1.setBounds(220,50,100,20);
        jb1.addActionListener(this);
        add(jb1);

        name=new JLabel("Name:");
        name.setBounds(10,75,100,20);
        add(name);

        j1=new JLabel();
        j1.setBounds(115,75,160,20);
        add(j1);

        Nationality=new JLabel("Nationality:");
        Nationality.setBounds(10,100,100,20);
        add(Nationality);

        j2=new JLabel();
        j2.setBounds(120,100,150,20);
        add (j2);
        Gender=new JLabel("Gender:");
        Gender.setBounds(10,125,100,20);
        add(Gender);

        j3=new JLabel();
        j3.setBounds(120,125,150,20);
        add(j3);

        Source=new JLabel("Source");
        Source.setBounds(10,150,100,20);
        add(Source);

        c1=new Choice();
        c1.setBounds(120,150,140,20);
        add(c1);


        Desintation=new JLabel("Desintation");
        Desintation.setBounds(10,175,100,20);
        add(Desintation);

        c2=new Choice();
        c2.setBounds(120,175,140,200);
        add(c2);


        jb2=new JButton("Fetch Flight");
        jb2.setBounds(260,175,120,20);
        jb2.setBackground(Color.black);
        jb2.setForeground(Color.white);
        jb2.addActionListener(this);
        add(jb2);


        FlightName=new JLabel("Flight Name");
       FlightName.setBounds(10,205,100,20);
       add(FlightName);

       j4=new JLabel();
       j4.setBounds(120,205,100,20);
       add(j4);

       FlightCode=new JLabel("Flight Code");
       FlightCode.setBounds(10,230,100,20);
       add(FlightCode);

       j5=new JLabel();
       j5.setBounds(120,230,150,20);
       add(j5);


       DateofTravel=new JLabel("Date to Travel");
       DateofTravel.setBounds(10,255,100,20);
       add(DateofTravel);

         jd=new JDateChooser();
        jd.setBounds(120,255,150,20);
        add(jd);

        jb3=new JButton("Book Flight");
            jb3.setBounds(110,300,130,20);
            jb3.setForeground(Color.white);
            jb3.addActionListener(this);
            jb3.setBackground(Color.black);
            add(jb3);
// for image
            ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("details.jpg"));
            Image im=i1.getImage().getScaledInstance(400,400,Image.SCALE_SMOOTH);
            ImageIcon i2=new ImageIcon(im);
            JLabel image=new JLabel(i2);
            image.setBounds(350,60,400,400);
            add(image);

            try {
                dbcon db=new dbcon();
                PreparedStatement ps=db.con.prepareStatement("select * from flight");
                ResultSet rs=ps.executeQuery();
                while (rs.next()){
                    c1.add(rs.getString("source"));
                    c2.add(rs.getString("dentination"));

                }
                ps.close();
                db.con.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }

    }

    public void actionPerformed(ActionEvent ae){
        if (ae.getSource()==jb1){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/airline","root","1166");
                PreparedStatement ps=con.prepareStatement("select uname,Nationality,gender from user where citize=?");
                ps.setString(1,jt1.getText().toString());
                ResultSet res=ps.executeQuery();
                if (res.next()){
                    j1.setText(res.getString("uname"));
                    j2.setText(res.getString("Nationality"));
                    j3.setText(res.getString("gender"));


                }
                else {
                    JOptionPane.showMessageDialog(null,"user is not found");
                }
                ps.close();
                con.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(ae.getSource()==jb2){
            String Source=c1.getSelectedItem();
            String Destination=c2.getSelectedItem();
            dbcon db=new dbcon();
            try {
                PreparedStatement ps = db.con.prepareStatement("select * from flight where source=? and dentination=? ");
              ps.setString(1,Source);
              ps.setString(2,Destination);
              ResultSet res=ps.executeQuery();
              if(res.next()){
                  j4.setText(res.getString("f_name"));
                  j5.setText(res.getString("f_code"));
              }
              else {
                  JOptionPane.showMessageDialog(null,"no flight data found");
              }
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
        else{
            Random rs=new Random();
            String Citizen=jt1.getText();
            String pname=j1.getText();
            String Nationality=j2.getText();
            String gender=j3.getText();
            String source= c1.getSelectedItem();
            String destination =c2.getSelectedItem();
            String flightname= j4.getText();
            String flightcode=j5.getText();
            String Dot=((JTextField) jd.getDateEditor().getUiComponent()).getText();

            try{
                dbcon db=new dbcon();
                PreparedStatement ps = db.con.prepareStatement("INSERT INTO reservation (PNR, TICKET, aadhar, name, nationality, flightname, flightcode, src, des, ddate,gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)");

// Set the values for each parameter;
                ps.setString(1, String.valueOf(rs.nextInt(10000)));
                ps.setString(2, String.valueOf(rs.nextInt(100000)));
                ps.setString(3,Citizen);
                ps.setString(4,pname);
                ps.setString(5,Nationality);
                ps.setString(6,flightcode);
                ps.setString(7,flightname);
                ps.setString(8,source);
                ps.setString(9,destination);
                ps.setString(10,Dot);
                ps.setString(11,gender);

                int res=ps.executeUpdate();
                if (res>0){
                    JOptionPane.showMessageDialog(null,"Sucess");
                    setVisible(false);
                    new Home();
                }
                else {
                    JOptionPane.showMessageDialog(null,"UNSucess");
                }
                ps.close();
                db.con.close();
            }
            catch (Exception e){
                e.printStackTrace();

            }
        }
    }

    public static void main(String[] args) {
        new BookFlifht();
    }
}
