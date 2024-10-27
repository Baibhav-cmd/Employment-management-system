import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Delete extends JFrame implements ActionListener {
JLabel emid, lblname,lbname,lblEmail,lbEmail,lblPhone,lbPhone;
Choice cEmp;
JButton delete,back;
    Delete(){
        setSize(700,500);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.white);

        emid=new JLabel("employeeid");
        emid.setBounds(50,50,100,20);
        add(emid);
        cEmp=new Choice();
        cEmp.setBounds(160,50,150,20);
        add(cEmp);
        // select qoery

        try {
            Conn c=new Conn();
            PreparedStatement ps=c.con.prepareStatement("select id from employee");
            ResultSet res=ps.executeQuery();
            while (res.next()){
                cEmp.add(res.getString("id"));
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

        lblname=new JLabel("Name:");
        lblname.setBounds(50,100,100,20);
        add(lblname);

        lbname=new JLabel();
        lbname.setBounds(150,100,100,20);
        add(lbname);

        lblEmail=new JLabel("Email:");
        lblEmail.setBounds(50,150,100,20);
        add(lblEmail);

        lbEmail=new JLabel();
        lbEmail.setBounds(150,150,100,20);
        add(lbEmail);

        lblPhone=new JLabel("Phone");
        lblPhone.setBounds(50,200,100,20);
        add(lblPhone);

        lbPhone=new JLabel();
        lbPhone.setBounds(150,200,100,20);
        add(lbPhone);

        delete=new JButton("DELETE");
        delete.setBounds(100,240,100,20);
        add(delete);
        delete.addActionListener(this);

        back=new JButton("Back");
        back.setBounds(200,240 ,100,20);
        add(back);
        back.addActionListener(this);

        try {
            Conn c=new Conn();
            PreparedStatement ps=c.con.prepareStatement("select * from employee where id=?");

            String selectedId = cEmp.getSelectedItem();
            System.out.println("Selected Employee ID (String): " + selectedId);

            // Ensure that selectedId is a valid integer
            int employeeId = Integer.parseInt(selectedId);
            System.out.println("Parsed Employee ID (Integer): " + employeeId);

            ps.setInt(1,employeeId);
            ResultSet res=ps.executeQuery();
            while (res.next()){
                lbname.setText(res.getString("uname"));
                lbEmail.setText(res.getString("email"));
                lbPhone.setText(res.getString("Phone"));
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        cEmp.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {

                try {
                    Conn c=new Conn();
                    PreparedStatement ps=c.con.prepareStatement("select * from employee where id=?");

                    String selectedId = cEmp.getSelectedItem();
                    System.out.println("Selected Employee ID (String): " + selectedId);

                    // Ensure that selectedId is a valid integer
                    int employeeId = Integer.parseInt(selectedId);
                    System.out.println("Parsed Employee ID (Integer): " + employeeId);

                    ps.setInt(1,employeeId);
                    ResultSet res=ps.executeQuery();
                    while (res.next()){
                        lbname.setText(res.getString("uname"));
                        lbEmail.setText(res.getString("email"));
                        lbPhone.setText(res.getString("Phone"));
                    }

                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });






        // connecting the Databse
//        try{
//            Conn co=new Conn();
//            PreparedStatement stmt =co.con.prepareStatement("delete employee  where id=?");
////            stmt.setInt(1,Integer.parseInt(empid));
//
//            int roe=stmt.executeUpdate();
//            if(roe<0){
//                JOptionPane.showMessageDialog(null,"Data delete sucessfully ");
//            }
//            stmt.close();
//            setVisible(false);
//            new Home();
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }


    }

    public static void main(String[] args) {
    new Delete();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
 if(ae.getSource()==delete){
     try {
         Conn c=new Conn();
         PreparedStatement ps = c.con.prepareStatement("DELETE FROM employee WHERE id=?");

         String selectedId = cEmp.getSelectedItem();
         int employeeId = Integer.parseInt(selectedId);
         ps.setInt(1,employeeId);

         int res=ps.executeUpdate();
          JOptionPane.showMessageDialog(null,"sucessfully delete");
          setVisible(false);
          new Home();



     }
     catch (Exception e){
         e.printStackTrace();
     }
 }

 else if (ae.getSource()==back){
     setVisible(false);
     new Home();
 }
    }

}
