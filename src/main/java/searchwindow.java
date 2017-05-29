package dankclient;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class searchwindow extends JFrame implements ActionListener{
      //defining stuff
      public JFrame frame1 = new JFrame();
      public JLabel label1 = new JLabel("Title");
      public JTextField searchfield = new JTextField();
      public JPanel jpl2 = new JPanel();
      public JButton button1 = new JButton();
      public JButton button2 = new JButton();
      public JTextArea resultbox = new JTextArea();
      public String searchin;
      JScrollPane sp = new JScrollPane(resultbox);
      public window jf = new window();
      public ResultSet rs;
      public Statement st; 
     
    
   
    public void searchwindow(){
         
        //window properties
        frame1.setTitle("Dank Client");
        frame1.setSize(800,600);
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jpl2.setLayout(null);
        
        //labels
        label1.setVerticalTextPosition(JLabel.TOP);
        label1.setHorizontalTextPosition(JLabel.CENTER);
        label1.setText("Search");
        label1.setBounds(350, -100, 200, 300);
        label1.setFont(new Font(label1.getFont().getName(), label1.getFont().getStyle(), 30));
        label1.setPreferredSize(new Dimension(100,70));
        //input field
        searchfield.setBounds(250, 100, 300, 30);
        //text box
        //same thing again. deciding where the object is placed on the frame
        //absolute position is not recommended.  i just prefer to not use layout managers. 
        resultbox.setBounds(250, 200, 400, 300);
        resultbox.setEditable(false);
       
        //button
        button1.setBounds(550,100, 80, 30);
        button1.setText("Search");
        button1.addActionListener(this);
        button2.setBounds(0,0, 120, 30);
        button2.setText("Get all data");
        button2.addActionListener(this);
        //scroll
        sp.setBounds(650, 200, 17, 300);
        sp.setVerticalScrollBarPolicy(sp.VERTICAL_SCROLLBAR_ALWAYS);
        
        //adding stuff
        jpl2.add(label1);
        jpl2.add(searchfield);
        jpl2.add(resultbox);
        jpl2.add(button1);
        jpl2.add(button2);
        jpl2.add(sp);
        frame1.add(jpl2);
        frame1.validate();
         
        
    }
   //what happens when user clicks on a button
     @Override
     public void actionPerformed(ActionEvent e){
        if(e.getSource() == button1){   
           try{
            
               //getting and putting input from the JTextField into a string
               String searchin = searchfield.getText();  
               //making a query
               String query = "SELECT * FROM dbo.users WHERE username like '"+searchin+"' or firstname like '"+searchin+"' or lastname like '"+searchin+"' or country like '"+searchin+"'";
               //executing and storing data in resultset
               rs = st.executeQuery(query); 
               //calling while loop  
                   while(rs.next()){
                  //getting data from rs     
                   String username = rs.getString("username");
                   String firstname = rs.getString("firstname");
                   String lastname = rs.getString("lastname");
                   String email = rs.getString("email");
                   String country = rs.getString("country");
                   String ip = rs.getString("ip");
                   String mac = rs.getString("mac");
                   String city = rs.getString("city");
                   //setting the text in an JTextArea to be the data from the database 
                   resultbox.setText("username: " + username + System.lineSeparator() + " firstname: " + firstname +  System.lineSeparator() + " lastname: " + lastname + System.lineSeparator() + " email: " + email +System.lineSeparator() + " city: " + city + System.lineSeparator() + " country: " + country + System.lineSeparator() + " ip: " + ip + System.lineSeparator() + " mac: " + mac);
                   
                 }
               //handling exceptions, preventing unnesecary crashes
               }catch(SQLException ex){
                  System.out.println(ex);
                  
                     }
                   }if(e.getSource() == button2){   
           try{
            
               //getting and putting input from the JTextField into a string
               String searchin = searchfield.getText();  
               //making a query
               String query = "SELECT * FROM dbo.users";
               //executing and storing data in resultset
               rs = st.executeQuery(query); 
               //calling while loop  
                   while(rs.next()){
                  //getting data from rs     
                   String username = rs.getString("username");
                   String firstname = rs.getString("firstname");
                   String lastname = rs.getString("lastname");
                   String email = rs.getString("email");
                   String country = rs.getString("country");
                   String ip = rs.getString("ip");
                   String mac = rs.getString("mac");
                   String city = rs.getString("city");
                   //setting the text in an JTextArea to be the data from the database 
                   resultbox.setText("username: " + username + System.lineSeparator() + " firstname: " + firstname +  System.lineSeparator() + " lastname: " + lastname + System.lineSeparator() + " email: " + email +System.lineSeparator() + " city: " + city + System.lineSeparator() + " country: " + country + System.lineSeparator() + " ip: " + ip + System.lineSeparator() + " mac: " + mac);
                   
                 }
               //handling exceptions, preventing unnesecary crashes
               }catch(SQLException ex){
                  System.out.println(ex);
            
                    }
                }
            }
           
        }


