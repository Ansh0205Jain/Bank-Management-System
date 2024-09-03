package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

public class Ministatement extends JFrame implements ActionListener{
   
    Ministatement(String pinNumber)
    {
        setTitle("Mini Statement");
        
        setLayout(null);
        
        JLabel mini = new JLabel("");
        add(mini);
        
        JLabel bank = new JLabel("HDFC BANK");
        bank.setBounds(150,20,100,20);
        add(bank);
        
        JLabel card = new JLabel("");
        card.setBounds(20,80,300,20);
        add(card);
        
        JLabel balance = new JLabel("Account Balance");
        balance.setBounds(20,400,300,20);
        add(balance);
        
        try{
            Conn conn = new Conn();
            ResultSet rs= conn.s.executeQuery("SELECT * FROM login where pinNumber = '"+pinNumber+"'");
            while(rs.next())
            {
                card.setText("Card Number: "+rs.getString("cardNumber").substring(0,4)+"XXXXXXXX"+rs.getString("cardNumber").substring(12));
            }
        }catch(Exception e)
        {
            System.out.println(e);
        }
        
        try{
            Conn conn = new Conn();
            int bal=0;
            ResultSet rs= conn.s.executeQuery("SELECT * FROM bank where pinNumber = '"+pinNumber+"'");
            while(rs.next())
            {
                mini.setText(mini.getText() + "<html>"+ rs.getString("date")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+ rs.getString("type")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+rs.getString("amount")+"<br><html>");
                if(rs.getString("type").equals("Deposit"))
                    {
                        bal += Integer.parseInt(rs.getString("amount"));
                    }
                    else{
                        bal -= Integer.parseInt(rs.getString("amount"));
                    }
            }
            balance.setText("Your current account balance is Rs. "+bal);
        }catch(Exception e)
        {
            System.out.println(e);
        }
        mini.setBounds(20,140,400,200);
        setSize(400,600);
        setLocation(20,20);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        
    }
    
    public static void main(String args[])
    {
        new Ministatement("");
    }
}
