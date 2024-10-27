import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.ExecutionException;

import net.proteanit.sql.*;


public class ViewEmployees extends JFrame implements ActionListener {
    JTable emp;
    JScrollPane js;
    Choice cid;
    JButton Search,update,print,back;
public ViewEmployees(){

    setVisible(true);
    setSize(900,700);
    getContentPane().setBackground(Color.white);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(false);
    setLayout(null);

    // making the table
    emp=new JTable();

    JLabel lblname=new JLabel("Search by employee id:");
    lblname.setBounds(20,30,150,20);
    add(lblname);

    cid=new Choice();
    cid.setBounds(180,30,150,20);
    add(cid);
    // for search
    try{
        Conn db=new Conn();
        PreparedStatement statement=  db.con.prepareStatement("Select * from Employee");
        ResultSet result=statement.executeQuery();

        while (result.next()){
            cid.add(result.getString("id"));
        }



    }
    catch (Exception e){
        e.printStackTrace();
    }
// for table
    try{
        Conn db=new Conn();
        PreparedStatement statement=  db.con.prepareStatement("Select * from Employee");
        ResultSet result=statement.executeQuery();
        emp.setModel(DbUtils.resultSetToTableModel(result));
    }
    catch (Exception e){
e.printStackTrace();
    }
    js=new JScrollPane(emp);
    js.setBounds(0,100,900,600);
    js.setFont(new Font("arial",Font.BOLD,30));
// search button
    Search =new JButton("Search");
    Search.setBounds(80,55,100,20);
    Search.addActionListener(this);
    add(Search);

    // for print Button
    print=new JButton("print");
    print.setBounds(180,55,100,20);
    print.addActionListener(this);
    add(print);

    // forr update
    update=new JButton("Update");
    update.setBounds(280,55,100,20);
    update.addActionListener(this);
    add(update);
// for Back

    back=new JButton("Back");
    back.setBounds(380,55,100,20);
    back.addActionListener(this);
    add(back);





    add(js);


}

public void actionPerformed(ActionEvent ae){
    if(ae.getSource()==Search){
        String query="select * from employee where id='"+cid.getSelectedItem()+"'";
        try{
            Conn db=new Conn();
            PreparedStatement statement=  db.con.prepareStatement(query);
            ResultSet result=statement.executeQuery();
            emp.setModel(DbUtils.resultSetToTableModel(result));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    } else if (ae.getSource()==print) {

        try{
            emp.print();
        }
        catch (Exception E){
            E.printStackTrace();
        }



    } else if (ae.getSource()==update) {
        setVisible(false);
        new UpdateEmployee(cid.getSelectedItem());


    } else  {
        setVisible(false);
        new Home();

    }


}

    public static void main(String[] args) {
    new ViewEmployees();
    }
}
