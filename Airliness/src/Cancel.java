import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

public class Cancel extends JFrame implements ActionListener {
    JLabel  Heading,Jpnr,jName,JCancel,Jflight,jdate;
    JTextField jtpnr;
    JButton show,bt;
    JLabel j1,j2,j3,j4;
    Cancel(){
        getContentPane().setBackground(Color.white);
        setSize(800,400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        Heading=new JLabel("CANCELATION");
        Heading.setBounds(250,10,300,30);
        Heading.setFont(new Font("Arial",Font.BOLD,30));
        Heading.setForeground(Color.BLUE);
        add(Heading);

        Jpnr=new JLabel("PNR");
        Jpnr.setBounds(20,70,100,20);
        add(Jpnr);

        jtpnr=new JTextField();
        jtpnr.setBounds(70,70,150,20);
        add(jtpnr);

        show=new JButton("Show Details");
        show.setBackground(Color.white);
        show.setBackground(Color.cyan);
        show.setBounds(200,70,150,20);
        show.addActionListener(this);
        add(show);


        jName=new JLabel("Name:");
        jName.setBounds(20,100,100,20);
        add(jName);

        j1=new JLabel();
        j1.setBounds(120,100,100,20);
        add(j1);

        JCancel=new JLabel("Cancellation no");
        JCancel.setBounds(20,120,150,20);

        add(JCancel);


        Random rn=new Random();
        j2=new JLabel();
        j2.setText(String.valueOf(rn.nextInt(99999)));
        j2.setBounds(170,120,100,20);
        add(j2);

        Jflight=new JLabel("Flight code:");
        Jflight.setBounds(20,140,150,20);
        add(Jflight);

        j3=new JLabel();
        j3.setBounds(170,140,150,20);
        add(j3);

        jdate=new JLabel("Date");
        jdate.setBounds(20,160,100,20);
        add(jdate);

        j4=new JLabel();
        j4.setBounds(120,160,150,20);
        add(j4);

        bt=new JButton("Cancel");
        bt.addActionListener(this);
        bt.setBounds(100,200,100,20);
        add(bt);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("cancel.jpg"));
        Image I2= i1.getImage().getScaledInstance(300,300,Image.SCALE_SMOOTH);
        ImageIcon i2=new ImageIcon(I2);
        JLabel IMage=new JLabel(i2);
        IMage.setBounds(370,60,300,300);
        add(IMage);

    }
    @Override
    public  void actionPerformed(ActionEvent ae){

        if (ae.getSource()==show) {
            try {
                dbcon d = new dbcon();
                PreparedStatement ps = d.con.prepareStatement("select * from reservation where PNR=?");
                ps.setString(1, jtpnr.getText());
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    jName.setText(rs.getString("name"));
                    Jflight.setText(rs.getString("flightcode"));
                    jdate.setText(rs.getString("ddate"));

                } else {
                    JOptionPane.showMessageDialog(null, "worng PNR");
                }
                ps.close();
                d.con.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

            else if(ae.getSource()==bt){
                    try{
                        dbcon db=new dbcon();
                        PreparedStatement ps1=db.con.prepareStatement("delete from reservation where PNR=?");
                        ps1.setString(1,jtpnr.getText());
                        System.out.println(Jpnr.getText().trim());
                        int res=ps1.executeUpdate();
                        if(res>0){

                            JOptionPane.showMessageDialog(null,"Cancel sucess");
                            new Home();
                        }
                        else {
                         JOptionPane.showMessageDialog(null,"sonething went worng");
                        }
                        ps1.close();
                        db.con.close();

                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }


            }




    public static void main(String[] args) {
 new Cancel();
    }
}
