import org.w3c.dom.events.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Splash extends JFrame implements ActionListener {

    JLabel Heading;
    JButton Continue;
    Splash(){
        // for jframe
        getContentPane().setBackground(Color.white);
        setSize(1100,650);
setLocation(100,30);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

// for heading
        Heading=new JLabel("Employee Management System");
        Heading.setBounds(50,30 ,1100,70);
        add(Heading);
        Heading.setFont(new Font("serif",Font.PLAIN,60));
        Heading.setForeground(Color.red);
        // for image
        ImageIcon li=new ImageIcon(ClassLoader.getSystemResource("front.jpg"));
        Image l2=li.getImage().getScaledInstance(1100,550,Image.SCALE_SMOOTH);
        ImageIcon li2=new ImageIcon(l2);
        JLabel img=new JLabel(li2);
        img.setBounds(0,110,1100,500);
        add(img);
// for  button
        Continue = new JButton(" Click  here to Continue");
        Continue.setBounds(400,400,200,40);
        Continue.setBackground(Color.black);
        Continue.setForeground(Color.white);
        img.add(Continue);
        Continue.addActionListener(this);

        while (true){
            Heading.setVisible(false);
            try{
                Thread.sleep(500);
            }
            catch (Exception e){
                e.printStackTrace();
            }

                Heading.setVisible(true);
            try{
                Thread.sleep(500);
            }
            catch (Exception e){
                e.printStackTrace();
            }


        }
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
        new Login();


    }
    public static void main(String[] args) {

        new Splash();


    }

}
