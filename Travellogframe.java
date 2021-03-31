/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LetsRide;
import javax.swing.*;  
import java.awt.*;  
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
/**
 *
 * @author Sekar&Kowshik
 */
public class Travellogframe extends JFrame implements ActionListener {
    JLabel Header_label,title_image;
    JPanel Header_panel,Body_panel;
    JButton Back_button; 
    //JTextArea Travel_logs;
    Container C = getContentPane();
    JTable TravellogsTable;
    JScrollPane scroll;
    String[] ColumnNames={"BusId","Boarding Location","Destination","Amount"};
    String[] Table_data={"BusId","Boarding_Loc","Destination","Amount"};
    DefaultTableModel Tabmodel;
    //JScrollPane Travel_logScroll;
    String current_user;
    int admin_or_not;
    JTableHeader head;
    Travellogframe(String current_user,int admin_or_not)
    {
        this.current_user=current_user;
        this.admin_or_not=admin_or_not;
        C.setBackground(new Color(30, 39, 46));
        C.setLayout(null);
        
        
        Header_panel=new JPanel();
        Header_panel.setBounds(0,0,600,60);
        Header_panel.setLayout(null);
        Header_panel.setBackground(new Color(255, 168, 1));
        Header_label=new JLabel("Route Radar");
        Header_label.setBounds(20,10,300,50);
        Header_label.setFont(new Font("Bauhaus 93",Font.BOLD,40));  
        Header_label.setForeground(new Color(255,255,255));
        
        Body_panel=new JPanel();
        Body_panel.setBounds(50,200,500,300);
        Body_panel.setLayout(null);
        Body_panel.setBackground(new Color(255,255,255));

        
        Back_button=new JButton("Back");
        Back_button.setBounds(500,70,70,20);
        Back_button.setFont(new Font("Tahoma",1,14));
        Back_button.setBackground(new Color(61, 193, 211));
        Back_button.setForeground(new Color(255,255,255));
        Back_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Back_button.setBorderPainted(false);
        Back_button.addActionListener(this);
        
       // Travel_logs=new JTextArea(20,20);
        //Travel_logs.setBounds(30,200,500,400);
        //Travel_logs.setText("BusID"+"\t"+"Boarding Location" +"t"+ "Destination" +"t"+ "Amount" +"\n");
        //Travel_logs.setEditable(false);
        //Travel_logs.setLineWrap(true);
        //Travel_logScroll=new JScrollPane(Travel_logs);
        //Travel_logPane=new JScrollPane(Travel_logs, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //Travel_logScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        //Travel_logScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //Travel_logPane.setVisible(true);
         Tabmodel=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row,int column){
                    return false;
            }
        };
        ImageIcon i1=new ImageIcon("D:/Travel.jpeg");
        title_image=new JLabel(i1);
        title_image.setBounds(180,90,203,46); 
         
        Tabmodel.setColumnIdentifiers(Table_data);
        TravellogsTable=new JTable();
        TravellogsTable.setBackground(new Color(154,205,50));
        TravellogsTable.setGridColor(Color.RED);
        TravellogsTable.setRowSelectionAllowed(false);
        TravellogsTable.setColumnSelectionAllowed(false);
        TravellogsTable.setModel(Tabmodel);
        TravellogsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        TravellogsTable.setFillsViewportHeight(true);
        TravellogsTable.setRowHeight(30);
        TravellogsTable.setForeground(new Color(0,0,0));
        TravellogsTable.setFont(new Font("Times New Roman",Font.BOLD,17));
        TravellogsTable.getColumnModel().getColumn(0).setPreferredWidth(60);
        TravellogsTable.getColumnModel().getColumn(1).setPreferredWidth(120);
        TravellogsTable.getColumnModel().getColumn(2).setPreferredWidth(120);
        TravellogsTable.getColumnModel().getColumn(3).setPreferredWidth(60);
        head=TravellogsTable.getTableHeader();
        head.setBackground(new Color(30, 39, 46));
        head.setForeground(new Color(255,165,0));
        head.setFont(new Font("Times New Roman",Font.BOLD,20));
        scroll=new JScrollPane(TravellogsTable);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(30,250,520,150);        
        try{
                Tabmodel.setRowCount(0);    
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bootcamp_project?zeroDateTimeBehavior=convertToNull","kowshik","");
                Statement  st = con.createStatement();
                String Travellogs="select * from travellog where Username='"+current_user+"'";
                ResultSet rs= st.executeQuery(Travellogs);
                while(rs.next()){
                Table_data[0]=rs.getString("BusID");
                Table_data[1]=rs.getString("Boarding_Loc");
                Table_data[2]=rs.getString("Destination");
                Table_data[3]=rs.getString("Amount");
                Tabmodel.addRow(Table_data);
                }
           }
        catch(Exception ex)
            {
                 System.out.println("---------->  "+ex);
            }

        Header_panel.add(Header_label);
        C.add(Header_panel);
        C.add(Back_button);
        C.add(scroll);
        C.add(title_image);

    }
    
    @Override
    public void actionPerformed(ActionEvent e){ 
        if(e.getSource().equals(Back_button))
        {
        this.dispose();
        letsrideframe f=new letsrideframe(current_user,admin_or_not);
        f.setBounds(400,10,600,700);
        f.setTitle("RouteRadar");
        f.setResizable(false);
        f.setDefaultCloseOperation(3);
        f.setVisible(true); 
        }
    }
    /*public static void main(String[] args) {
        Travellogframe t=new Travellogframe();
        t.setBounds(100,100,600,700);
        t.setTitle("RouteRadar");
        t.setResizable(true);
        //f.components();
        t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        t.setVisible(true);
    }*/
}
