
package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;


public class Fastcash extends JFrame implements ActionListener{
    
    JButton c1,c2,c3,c4,c5,c6,back;
    String pinNumber;
    Fastcash(String pinNumber)
    {
        this.pinNumber=pinNumber;
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);
        
        JLabel text=new JLabel("SELECT WITHDRAWAL AMOUNT");
        text.setBounds(210,300,700,35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System",Font.BOLD,16));
        image.add(text);
        
        c1 = new JButton("Rs. 100");
        c1.setBounds(170,415,150,30);
        c1.addActionListener(this);
        image.add(c1);
        
        c2 = new JButton("Rs. 500");
        c2.setBounds(355,415,150,30);
        c2.addActionListener(this);
        image.add(c2);
        
        c3 = new JButton("Rs. 1000");
        c3.setBounds(170,450,150,30);
        c3.addActionListener(this);
        image.add(c3);
        
        c4 = new JButton("Rs. 2000");
        c4.setBounds(355,450,150,30);
        c4.addActionListener(this);
        image.add(c4);
        
        c5 = new JButton("Rs. 5000");
        c5.setBounds(170,485,150,30);
        c5.addActionListener(this);
        image.add(c5);
        
        c6 = new JButton("Rs. 10000");
        c6.setBounds(355,485,150,30);
        c6.addActionListener(this);
        image.add(c6);
        
        back = new JButton("BACK");
        back.setBounds(355,520,150,30);
        back.addActionListener(this);
        image.add(back);
        
        
        
        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==back)
        {
            setVisible(false);
            new Transactions(pinNumber).setVisible(true);
        }else
        {
            String amount = ((JButton)ae.getSource()).getText().substring(4);
            Conn conn = new Conn();
            try{
                ResultSet rs=conn.s.executeQuery("SELECT * FROM bank where pinNumber = '"+pinNumber+"'");
                int balance=0;
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
                
                if(ae.getSource()!=back && balance < Integer.parseInt(amount))
                {
                    JOptionPane.showMessageDialog(null,"Insufficient Balance");
                    return;
                }
                
                Date date = new Date();
                String query = "insert into bank values('"+pinNumber+"','"+date+"','Withdrawal','"+amount+"')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Rs. "+amount+" Debited Successfully");
                setVisible(false);
                new Transactions(pinNumber).setVisible(true);
            }catch(Exception e)
            {
                System.out.println(e);
            }
        }
    }
    public static void main(String args[])
    {
        new Fastcash("");
    }
}
