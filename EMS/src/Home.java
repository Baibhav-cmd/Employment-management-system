import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener {
    JLabel image, Heading;
    JButton View ,Add,Remove,Update;
    public Home(){
        getContentPane().setBackground(Color.WHITE);
        setSize(1300,800);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        ImageIcon l1=new ImageIcon(ClassLoader.getSystemResource("home.jpg"));
        Image I=l1.getImage().getScaledInstance(1300,800,Image.SCALE_SMOOTH);
        ImageIcon l2=new ImageIcon(I);
        image=new JLabel(l2);
        image.setBounds(0,0,1300,800);
        add(image);

        Heading=new JLabel("Employee Management System");
        Heading.setBounds(800,5,1300,50);
        Heading.setForeground(Color.BLACK);
        Heading.setFont(new Font("arial",Font.BOLD,30) );
        image.add(Heading);

        // for add  Button
        Add=new JButton("Add Employees");
        Add.setBackground(Color.BLACK);
        Add.setForeground(Color.WHITE);
        Add.setBounds(870,60,150,30);
        Add.addActionListener(this);
        image.add(Add);

// for view Button
        View=new JButton("View Employee");
        View.setBackground(Color.BLACK);
        View.setForeground(Color.WHITE);
        View.setBounds(1100,60,150,30);
        View.addActionListener(this);
        image.add(View);

        // for Update Button
        Update=new JButton("Update Employee");
        Update.setBackground(Color.BLACK);
        Update.setForeground(Color.WHITE);
        Update.addActionListener(this);
        Update.setBounds(870,100,150,30);
        image.add(Update);

        // for Remove Button
        Remove=new JButton("Remove Employee");
        Remove.setBackground(Color.BLACK);
        Remove.setForeground(Color.WHITE);
        Remove.addActionListener(this);
        Remove.setBounds(1100,100,150,30);
        image.add(Remove);



    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==Add){
            setVisible(false);
            new AddEmployee();


        }
        else if (ae.getSource()==View) {
            setVisible(false);
            new ViewEmployees();

        }
        else if(ae.getSource()==Update){
            setVisible(false);
          new ViewEmployees();
        }
        else {
            new Delete();
        }




    }
    public static void main(String[] args) {


        new Home();
    }
}
