package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignupTwo extends JFrame implements ActionListener
{
    long random;
    String formno;
    JTextField panTextField,aadharTextField;
    JButton next;
    JRadioButton syes,sno,ayes,ano;
    JComboBox religion,category, inc, education,occp;
    
    SignupTwo(String formno)
    {
        this.formno=formno;
        setLayout(null);
        
        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 2");
                      
        JLabel additionalDetails = new JLabel("Page 2: Additional Details");
        additionalDetails.setFont(new Font("Raleway",Font.BOLD,22));
        additionalDetails.setBounds(290,80,400,30);
        add(additionalDetails);
        
        JLabel name = new JLabel("Religion:");
        name.setFont(new Font("Raleway",Font.BOLD,20));
        name.setBounds(100,140,100,30);
        add(name);
        
        String valReligion[] = {"Hindu","Muslim","Sikh","Christian","Other"};
        religion = new JComboBox(valReligion);
        religion.setBounds(300,140,400,30);
        religion.setBackground(Color.WHITE);
        add(religion);
       
        JLabel categoryname = new JLabel("Category:");
        categoryname.setFont(new Font("Raleway",Font.BOLD,20));
        categoryname.setBounds(100,190,200,30);
        add(categoryname);
        
        String valCategory[] = {"General","OBC","SC","ST","Other"};
        category = new JComboBox(valCategory);
        category.setBounds(300,190,400,30);
        category.setBackground(Color.WHITE);
        add(category);
        
        JLabel income = new JLabel("Income:");
        income.setFont(new Font("Raleway",Font.BOLD,20));
        income.setBounds(100,240,200,30);
        add(income);
        
        String incomeCategory[] = {"Null","<1,50,000","<2,50,000","<5,00,000","Upto 10,00,000"};
        inc = new JComboBox(incomeCategory);
        inc.setBounds(300,240,400,30);
        inc.setBackground(Color.WHITE);
        add(inc);
        
             
        JLabel edu = new JLabel("Educational");
        edu.setFont(new Font("Raleway",Font.BOLD,20));
        edu.setBounds(100,290,200,30);
        add(edu);  
        
        JLabel qualification = new JLabel("Qualification:");
        qualification.setFont(new Font("Raleway",Font.BOLD,20));
        qualification.setBounds(100,315,200,30);
        add(qualification);
        
        String edValues[] = {"Non-Graduate","Graduate","Post-Graduate","PHD","Others"};
        education = new JComboBox(edValues);
        education.setBounds(300,315,400,30);
        education.setBackground(Color.WHITE);
        add(education);
        
        JLabel occ = new JLabel("Occupation:");
        occ.setFont(new Font("Raleway",Font.BOLD,20));
        occ.setBounds(100,390,200,30);
        add(occ);
        
        String occupation[] = {"Salaried","Self-Employed","Bussinessman","Student","Retired","Others"};
        occp = new JComboBox(occupation);
        occp.setBounds(300,390,400,30);
        occp.setBackground(Color.WHITE);
        add(occp);
        
        JLabel pan = new JLabel("Pan Number:");
        pan.setFont(new Font("Raleway",Font.BOLD,20));
        pan.setBounds(100,440,200,30);
        add(pan);
        
        panTextField = new JTextField();
        panTextField.setFont(new Font("Raleway",Font.BOLD,14));
        panTextField.setBounds(300,440,400,30);
        add(panTextField);
        
        JLabel aadhar = new JLabel("Aadhar Number:");
        aadhar.setFont(new Font("Raleway",Font.BOLD,20));
        aadhar.setBounds(100,490,200,30);
        add(aadhar);
        
        aadharTextField = new JTextField();
        aadharTextField.setFont(new Font("Raleway",Font.BOLD,14));
        aadharTextField.setBounds(300,490,400,30);
        add(aadharTextField);
        
        JLabel srctz = new JLabel("Senior Citizen:");
        srctz.setFont(new Font("Raleway",Font.BOLD,20));
        srctz.setBounds(100,540,200,30);
        add(srctz);
        
        syes = new JRadioButton("Yes");
        syes.setBounds(300,540,100,30);
        syes.setBackground(Color.WHITE);
        add(syes);
        
        sno = new JRadioButton("No");
        sno.setBounds(450,540,100,30);
        sno.setBackground(Color.WHITE);
        add(sno);
        
        ButtonGroup snctzgrp = new ButtonGroup();
        snctzgrp.add(syes);
        snctzgrp.add(sno);
        
        JLabel account = new JLabel("Existing Account:");
        account.setFont(new Font("Raleway",Font.BOLD,20));
        account.setBounds(100,590,200,30);
        add(account);
        
        ayes = new JRadioButton("Yes");
        ayes.setBounds(300,590,100,30);
        ayes.setBackground(Color.WHITE);
        add(ayes);
        
        ano = new JRadioButton("No");
        ano.setBounds(450,590,100,30);
        ano.setBackground(Color.WHITE);
        add(ano);
        
        ButtonGroup exstgrp = new ButtonGroup();
        exstgrp.add(ayes);
        exstgrp.add(ano);
        
        
        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.setBounds(620,660,80,30);
        next.addActionListener(this);
        add(next);
        
        getContentPane().setBackground(Color.WHITE);
        
        setSize(850,800);
        setLocation(350,10);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        
        String sreligion=(String) religion.getSelectedItem();
        String scategory = (String) category.getSelectedItem();
        String sincome = (String) inc.getSelectedItem();
        String seducation = (String) education.getSelectedItem();
        String occupation = (String) occp.getSelectedItem();
        String seniorCitizen = null;
        if(syes.isSelected())
        {
            seniorCitizen="Yes";
        }
        else if(sno.isSelected())
        {
            seniorCitizen="No";
        }
        String existingaccount=null;
        if(ayes.isSelected())
        {
            existingaccount="Yes";
        }
        else if(ano.isSelected())
        {
            existingaccount="No";
        }
                
        String panNumber = panTextField.getText();
        String aadharNumber = aadharTextField.getText();
               
        try{
                Conn c = new Conn();
                String  query = "INSERT INTO signup2 VALUES('"+formno+"', '"+sreligion+"','"+scategory+"','"+sincome+"','"+seducation+"','"+occupation+"','"+panNumber+"','"+aadharNumber+"','"+seniorCitizen+"','"+existingaccount+"')";
                c.s.executeUpdate(query);
                
                //signup3 object
                setVisible(false);
                new SignupThree(formno).setVisible(true);
            }
        catch(Exception e){
            System.out.println(e);
        }
        
    }
    
    public static void main(String args[]) {
        new SignupTwo("");
    }
}
