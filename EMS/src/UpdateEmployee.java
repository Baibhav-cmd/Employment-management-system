import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

public class UpdateEmployee extends JFrame implements ActionListener {
    JLabel Heading,lblname,lblfname,lblAddress,lblEmail,lbldob,lblEmpid,jtempid,lblCourse,lblPhone,jtname,jtfname;
    JTextField jtEmail,jtAddress,jtPhone,jteducation;
    JLabel Datec;
    JButton Add,Back;
    String name,fnam,address,email,dob,id,course,phone;
String empid;
    public UpdateEmployee(String empid){
    this.empid=empid;
        setSize(700,600);
        getContentPane().setBackground(Color.white);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // for heading
        Heading=new JLabel("Update Emoloyee Details");
        Heading.setBounds(200,10,250,20);
        Heading.setForeground(Color.green);
        Heading.setFont(new Font("arail",Font.BOLD,20));
        add(Heading);


        // for name label
        lblname=new JLabel("Name:");
        lblname.setBounds(50,40,90,20);
        lblname.setFont(new Font("Serif",Font.PLAIN,20));
        add(lblname);
        // for name Textfeild
        jtname=new JLabel();
        jtname.setBounds(140,45,200,20);
        add(jtname);
// for FatherName
        lblfname=new JLabel("Father Name:");
        lblfname.setBounds(340,40,150,20);
        lblfname.setFont(new Font("Serif",Font.PLAIN,20));
        add(lblfname);
        // for name Textfeild
        jtfname=new JLabel();
        jtfname.setBounds(450,45,200,20);
        add(jtfname);
// for email
        lblEmail=new JLabel("Email:");
        lblEmail.setBounds(50,70,90,20);
        lblEmail.setFont(new Font("Serif",Font.PLAIN,20));
        add(lblEmail);
        // for  email Textfeild
        jtEmail=new JTextField();
        jtEmail.setBounds(140,70,200,20);
        add(jtEmail);

        // for address
        lblAddress=new JLabel("Address:");
        lblAddress.setBounds(360,70,90,20);
        lblAddress.setFont(new Font("Serif",Font.PLAIN,20));
        add(lblAddress);
        // for Address Textfeild
        jtAddress=new JTextField();
        jtAddress.setBounds(450,75,200,20);
        add(jtAddress);

        // for dob
        lbldob=new JLabel("Date of Birth:");
        lbldob.setBounds(50,100,150,20);
        lbldob.setFont(new Font("Serif",Font.PLAIN,20));
        add(lbldob);

        // for date
        Datec=new JLabel();
        Datec.setBounds(200,100,160,20);
        add(Datec);
        //label for id
        lblEmpid=new JLabel("Enter id:");
        lblEmpid.setBounds(370,100,90,20);
        lblEmpid.setFont(new Font("Serif",Font.PLAIN,20));
        add(lblEmpid);
        // textfeild for if
        jtempid=new JLabel();
        jtempid.setBounds(490,100,100,20);
        add(jtempid);

        lblCourse=new JLabel("Choose course:");
        lblCourse.setBounds(50,140,150,20);
        lblCourse.setFont(new Font("Serif",Font.PLAIN,20));
        add(lblCourse);

        jteducation= new JTextField();
        jteducation.setBounds(172,145,90,20);
        add(jteducation);
// for phone number
        lblPhone=new JLabel("Enter Phone:");
        lblPhone.setBounds(360,140,140,20);
        lblPhone.setFont(new Font("Serif",Font.PLAIN,20));
        add(lblPhone);

        jtPhone=new JTextField();
        jtPhone.setBounds(464,140,140,20);
        add(jtPhone);


        Add=new JButton("Update");
        Add.setBounds(280,180,100,30);
        Add.setBackground(Color.BLUE);
        Add.setForeground(Color.white);
        Add.addActionListener(this);
        add(Add);


        Back=new JButton("back");
        Back.setBounds(400,180,100,30);
        Back.addActionListener(this);
        Back.setBackground(Color.BLUE);
        Back.setForeground(Color.white);
        Back.addActionListener(this);
        add(Back);


        try{
            Conn c=new Conn();
            String query="select * from employee where id ='"+empid+"'";
          PreparedStatement Stmt =  c.con.prepareStatement(query);
            ResultSet rs=Stmt.executeQuery();
            while (rs.next())
            {
                jtname.setText(rs.getString("uname"));
                jtfname.setText(rs.getString("Fname"));
                Datec.setText(rs.getString("dob"));
                jteducation.setText(rs.getString("course"));
                jtEmail.setText(rs.getString("email"));
                jtempid.setText(rs.getString("id"));
                jtAddress.setText(rs.getString("address"));
                jtPhone.setText(rs.getString("Phone"));
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource()==Add){

            address=jtAddress.getText();
            email=jtEmail.getText();
            course=jteducation.getText();
            phone=jtPhone.getText();
// connecting the Databse
            try{
                Conn co=new Conn();
                PreparedStatement stmt =co.con.prepareStatement("update employee set email=?,address=?,Course=?,Phone=? where id=?");
                stmt.setString(1,email);
                stmt.setString(2,address);
                stmt.setString(3,course);
                stmt.setString(4,phone);
                stmt.setInt(5,Integer.parseInt(empid));

                int roe=stmt.executeUpdate();
                if(roe>0){
                    JOptionPane.showMessageDialog(null,"Data Update sucessfully ");
                }
                stmt.close();
                setVisible(false);
                new Home();

            }
            catch(Exception e){
                e.printStackTrace();
            }

        }
        else{
            setVisible(false);
            new Home();
        }
    }
    public static void main(String[] args) {
        new UpdateEmployee("");
    }
}
