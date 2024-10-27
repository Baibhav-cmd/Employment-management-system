import com.mysql.cj.protocol.Resultset;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

public class Login extends JFrame  implements ActionListener {
    JLabel LblUsername, lblPassword, Register;
    JButton Login, BtnRegister;
    JTextField jtusername, jtpassword;
    String name ,password;

    Login() {
        getContentPane().setBackground(Color.white);

        setSize(600, 300);
        setVisible(true);
        setLayout(null);
        setLocationRelativeTo(null);
        setTitle("Login form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LblUsername = new JLabel("Enter UserName:");
        LblUsername.setBounds(40, 20, 130, 30);
        add(LblUsername);
        lblPassword = new JLabel("Enter Password:");
        lblPassword.setBounds(40, 60, 130, 30);
        add(lblPassword);
        // for textfeold
        jtusername = new JTextField();
        jtusername.setBounds(150, 30, 230, 20);
        add(jtusername);
        jtpassword = new JTextField();
        jtpassword.setBounds(150, 65, 230, 20);
        add(jtpassword);
        // for login button
        Login = new JButton("Login");
        Login.setBounds(180, 100, 100, 20);
        Login.setBackground(new Color(140, 200, 160));
        Login.setForeground(Color.white);
        Login.addActionListener(this);
        add(Login);
        // for Register button and Label
        Register = new JLabel("Not Register Click Here:");
        Register.setBounds(30, 130, 200, 30);
        add(Register);

        BtnRegister = new JButton("Register");
        BtnRegister.setBounds(180, 135, 100, 20);
        BtnRegister.setForeground(Color.white);
        BtnRegister.setBackground(new Color(140, 200, 200));
       BtnRegister.addActionListener(this);
        add(BtnRegister);

        ImageIcon li=new ImageIcon(ClassLoader.getSystemResource("second.jpg"));
        Image I1=li.getImage().getScaledInstance(300, 300,Image.SCALE_SMOOTH);
        ImageIcon li2=new ImageIcon(I1);
        JLabel Image=new JLabel(li2);
        Image.setBounds(350,0,300,300);
        add(Image);


    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==Login) {
            if (jtusername.getText().trim().isEmpty() && jtpassword.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "enter the name and password first");
            } else {
                try {
                    name = jtusername.getText();
                    password = jtpassword.getText();
                    Conn c = new Conn();
                    String query = "SELECT * FROM login WHERE Username = ? AND password = ?";
                    PreparedStatement pstmt = c.con.prepareStatement(query);
                    pstmt.setString(1, name);
                    pstmt.setString(2, password);

                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(Login, "Welcome" + name);
                        setVisible(false);
                        new Home();
                    } else {
                        JOptionPane.showMessageDialog(null, "Inavlid Login ");
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        else if(ae.getSource()==BtnRegister){
            JOptionPane.showMessageDialog(BtnRegister,"Register");
        }

    }

    public static void main(String[] args) {
        new Login();

    }


}