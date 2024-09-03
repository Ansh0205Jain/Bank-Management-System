package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

public class BalanceEnquiry extends JFrame implements ActionListener{
    String pinNumber;
    JButton back;
    
    BalanceEnquiry(String pinNumber){
        this.pinNumber=pinNumber;
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);
        
        back = new JButton("BACK");
        back.setBounds(355,520,150,30);
        back.addActionListener(this);
        image.add(back);
        
        int balance=0;
        Conn conn = new Conn();
            try{
                ResultSet rs=conn.s.executeQuery("SELECT * FROM bank where pinNumber = '"+pinNumber+"'");
                
                while(rs.next())
                {
                    if(rs.getString("type").equals("Deposit"))
                    {
                        balance += Integer.parseInt(rs.getString("amount"));
                    }
                    else{
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                }
            }catch(Exception e)
            {
                System.out.println(e);
            }
        
            
        JLabel text = new JLabel("Your Current Account balance is Rs. "+balance);
        text.setForeground(Color.WHITE);
        text.setBounds(170,300,400,30);
        image.add(text);
            
        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        setVisible(false);
        new Transactions(pinNumber).setVisible(true);
    }
    public static void main(String args[])
    {
        new BalanceEnquiry("");
    }
}
