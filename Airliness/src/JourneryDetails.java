import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import net.proteanit.sql.*;

public class JourneryDetails extends JFrame implements ActionListener {
    JTable table;
    JLabel jpnr;
    JTextField jtpnr;
    JButton jb;
    JourneryDetails(){
        getContentPane().setBackground(Color.white);
       setVisible(true);
       setSize(800,600);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setLayout(null);
       setLocationRelativeTo(null);
       // for jLabel
jpnr=new JLabel("Enter PNR:");
jpnr.setBounds(30,10,150,20);
jpnr.setFont(new Font("Arial",Font.PLAIN,20));
add(jpnr);

jtpnr=new JTextField();
jtpnr.setBounds(150,10,150,20);
add(jtpnr);

jb=new JButton("Search");
jb.setBounds(130,35,100,30);
jb.setBackground(Color.black);
jb.setForeground(Color.white);
add(jb);
jb.addActionListener(this);


    }

    @Override
    public void actionPerformed(ActionEvent se) {
        table=new JTable();

        try{
            String PNR=jtpnr.getText();
            dbcon jd=new dbcon();
            PreparedStatement ps=jd.con.prepareStatement("select * from reservation where PNR=? ");
            ps.setString(1,PNR);
            ResultSet res= ps.executeQuery();
            if(res.next()){
                table=new JTable();

                table.setModel(DbUtils.resultSetToTableModel(res));

            }
            else {
                JOptionPane.showMessageDialog(null,"no informatiom");
            }
            res.close();
            ps.close();
            jd.con.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        JScrollPane js=new JScrollPane(table);
        js.setBounds(0,100,800,500);
        add(js);
    }




    public static void main(String[] args) {
        new JourneryDetails();
    }
}
