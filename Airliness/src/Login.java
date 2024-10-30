import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class Login  extends JFrame implements ActionListener {
    JLabel jlbname,jlbpassword;
    JTextField jtname,jtPassword;
    JButton jblogin,jbReset,jbback;
    String name,password;
    Login(){

        getContentPane().setBackground(Color.white);
        setSize(400,300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        jlbname=new JLabel("Name:");
        jlbname.setBounds(50,50,100,20);
        add(jlbname);

        jtname=new JTextField();
        jtname.setBounds(130,50,200,20);
        add(jtname);

        jlbpassword=new JLabel("Password:");
        jlbpassword.setBounds(50,100,100,20);
        add(jlbpassword);

        jtPassword=new JTextField();
        jtPassword.setBounds(130,100,200,20);
        add(jtPassword);

        jblogin=new JButton("login");
        jblogin.setBounds(170,130,100,20);
        add(jblogin);
        jblogin.addActionListener(this);

        jbReset=new JButton("Reset");
        jbReset.setBounds(60,160,100,20);
        jbReset.addActionListener(this);
        add(jbReset);

        jbback=new JButton("Close");
        jbback.setBounds(270,160,100,20);
        add(jbback);
        jbback.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        // for loginbutton Action
        name=jtname.getText();
        password=jtPassword.getText();
        // from validation
    if(ae.getSource()==jblogin){
            if(jtname.getText().trim().isEmpty()||jtPassword.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(null,"please enter user name and passsword");
            }

            // connecting the database and checeking it to Login
        try {
            dbcon db=new dbcon();
            PreparedStatement ps= db.con.prepareStatement("select uname, password from login where uname=? and password=?");
            ps.setString(1,name);
            ps.setString(2,password);

            ResultSet res=ps.executeQuery();
            if (res.next()){
                JOptionPane.showMessageDialog(null,"Welcome "+name);
                setVisible(false);
                new Home();
            }
            else {
                JOptionPane.showMessageDialog(null,"Incorrect password and Username" );
            }
            res.close();
            db.con.close();
            setVisible(false);




        }
        catch (Exception e){
            e.printStackTrace();
        }
        }
        else if (ae.getSource()==jbReset){
            jtname.setText(" ");
            jtPassword.setText("");

        }
        else {
            setVisible(false);

        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
