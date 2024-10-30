import com.mysql.cj.xdevapi.DbDoc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AddCustomer extends JFrame implements ActionListener {
    JLabel Heading,Name,Nationality,CitizenshipNumber,Address,Gender,Phone;
    JTextField jtName,jtNationality,jtCitizenship,jtAddress,jtPhone;
    JRadioButton Male,FeMale;
    ButtonGroup gender;
  JButton Sumbit;
  String  name,nationality,citizenshiono,address,geender,phone;
    AddCustomer(){

        setVisible(true);
        setSize(600,450);
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        // for Heading
        Heading=new JLabel("ADD CUSTOMERS DETAILS");
        Heading.setBounds(100,20,600,30);
        Heading.setFont(new Font("Arial",Font.BOLD,30));
        Heading.setForeground(Color.BLUE);
        add(Heading);
// for Name
        Name=new JLabel("Name:");
        Name.setBounds(20,60,100,20);
        add(Name);

        jtName=new JTextField();
        jtName.setBounds(130,60,150,20);
        add(jtName);

        // for Nayionality
        Nationality=new JLabel("Nationaliity:");
        Nationality.setBounds(20,90,100,20);
        add(Nationality);

        jtNationality=new JTextField();
        jtNationality.setBounds(130,90,150,20);
        add(jtNationality);
        // for CitizenshipNumber
        CitizenshipNumber=new JLabel("Citizen number:");
        CitizenshipNumber.setBounds(20,120,100,20);
        add(CitizenshipNumber);

        jtCitizenship=new JTextField();
        jtCitizenship.setBounds(130,120,150,20);
        add(jtCitizenship);
        // for Address
        Address=new JLabel("Address:");
        Address.setBounds(20,150,100,20);
        add(Address);

        jtAddress=new JTextField();
        jtAddress.setBounds(130,150,150,20);
        add(jtAddress);
        // for Gender
        Gender=new JLabel("Gender:");
        Gender.setBounds(20,180,70,20);
        add(Gender);

        Male=new JRadioButton("Male");
        Male.setBounds(90,180,100,20);
        add(Male);
        FeMale=new JRadioButton("Female");
        FeMale.setBounds(200,180,100,20);
        add(FeMale);
        gender=new ButtonGroup();
        gender.add(Male);
        gender.add(FeMale);
        // for phone
        Phone=new JLabel("Phone:");
        Phone.setBounds(20,210,100,20);
        add(Phone);

        jtPhone=new JTextField();
        jtPhone.setBounds(120,210,150,20);
        add(jtPhone);

        // button
        Sumbit=new JButton("Add");
        Sumbit.setBounds(80,250,100,20);
        Sumbit.addActionListener(this);
        add(Sumbit);


        // for photo
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("emp.png"));
        Image I=i1.getImage().getScaledInstance(300,330,Image.SCALE_SMOOTH);
        ImageIcon i2=new ImageIcon(I);
        JLabel image=new JLabel(i2);
        image.setBounds(280,70,300,330);
        add(image);



    }
    public void actionPerformed(ActionEvent ae){
        name=jtName.getText();
        nationality=jtNationality.getText();
        citizenshiono=jtCitizenship.getText();
        address=jtAddress.getText();
        phone=jtPhone.getText();
        if(Male.isSelected()){
            geender="Male";
        } else if (FeMale.isSelected()) {
            geender="Female";

        }
        else {
            JOptionPane.showMessageDialog(null,"Please Choose the gender");
        }
        if(name.isEmpty()&& nationality.isEmpty()&&citizenshiono.isEmpty()&&address.isEmpty()&&phone.isEmpty()){
            JOptionPane.showMessageDialog(null,"fill the feiild");
        }
        else {
            try {
                dbcon db = new dbcon();
                PreparedStatement ps = db.con.prepareStatement("insert into user values(?,?,?,?,?,?)");
                ps.setString(1,name);
                ps.setString(2,nationality);
                ps.setString(3,citizenshiono);
                ps.setString(4,address);
                ps.setString(5,geender);
                ps.setString(6,phone);

                int rs=ps.executeUpdate();
                if(rs>0){
                    JOptionPane.showMessageDialog(null,"Sucess");
                    new Home();
                    setVisible(false);
                }
                else {
                    JOptionPane.showMessageDialog(null,"unsucess");
                }

            }
            catch (Exception e){
                e.printStackTrace();
            }
        }




    }

    public static void main(String[] args) {
        new AddCustomer();
    }
}
