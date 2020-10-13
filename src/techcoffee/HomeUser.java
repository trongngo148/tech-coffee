/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techcoffee;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import static java.lang.Thread.sleep;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dell
 */
public class HomeUser extends javax.swing.JFrame {

    /**
     * Creates new form HomeUser
     */
    private String usernamesession;
    int tempForm=0;
    String bann;
    int soban;
    int hour,minute,day,month,year;
    public void getUser(String temp){
        this.usernamesession=temp;
    }
    
    public void clock(){
        
        Thread clock=new Thread(){
            public void run(){
                try{
                   for(;;){
                        Calendar call =new GregorianCalendar();
                        int thu=call.get(Calendar.DAY_OF_WEEK);
                        day=call.get(Calendar.DAY_OF_MONTH);
                        month=call.get(Calendar.MONTH)+1;
                        year=call.get(Calendar.YEAR);
                        int year1=call.get(Calendar.AM_PM);//Test sau 
                        int second=call.get(Calendar.SECOND);
                        minute=call.get(Calendar.MINUTE);
                        hour=call.get(Calendar.HOUR_OF_DAY);
                        if(thu==1)
                            lbThu.setText("Chủ Nhật");
                        else
                            lbThu.setText("Thứ: "+thu);
                        
                        if(minute>=0 && minute <=9 )
                                lbTime.setText(""+hour+":0"+minute);
                        else
                            lbTime.setText(""+hour+":"+minute);
                        
                         if(hour >=0 && hour <=9)
                             if(minute>=0 && minute <=9 )
                                lbTime.setText("0"+hour+":0"+minute);
                             else
                                lbTime.setText("0"+hour+":"+minute);
                       
                           
                        
                        lbDay.setText(""+day+"-"+month+"-"+year);
                        
                        if(hour >=5 && hour <=10)
                            lbWelcome.setText("Tôtô xin chúc quý khách buổi sáng vui vẻ !");
                        else if(hour >10 && hour <=3)
                            lbWelcome.setText("Tôtô xin chúc quý khách buổi trưa vui vẻ !");
                        else if(hour >3 && hour <=6)
                            lbWelcome.setText("Tôtô xin chúc quý khách buổi chiều vui vẻ !");
                        else 
                            lbWelcome.setText("Tôtô xin chúc quý khách buổi tối hạnh phúc !");
                        
                        
                        sleep(1000);
                   }
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        
        clock.start();
        lbSo1.setBackground(Color.red);
        
    }
    public void switchPanel(JPanel panel){
        Container.removeAll();
        Container.add(panel);
        Container.repaint();
        Container.revalidate();
        
        //hihi la cardlayout
    
    }

    Connection conn=null;
    public void ConnectDatabase(){
        
        try{
            
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/techcoffee?"+"user=root&password=000001");     
        }catch(Exception ex){
            System.out.println("That Bai");
            System.out.println("SQL exception: "+ex.getMessage());
        }
    }
    public void setTichDiem(String user1){
        String tempTD;
        if(getTichDiem(user1)==15){
            tempTD="14/14";
        }else{
            tempTD=getTichDiem(user1)+"/14";}
        lbTichDiem.setText(tempTD);
    }
    public int getTichDiem(String userTD){
        int xuat=0;
        PreparedStatement pStmt=null;
        ResultSet rs =null;
        
        try{
            pStmt = conn.prepareStatement("select xuatTichDiem(?)");
            pStmt.setString(1, userTD);
            rs=pStmt.executeQuery();
            while(rs.next()){
               xuat=rs.getInt(1); 
            }
        }catch(Exception ex){
            System.out.println("SQL exception: "+ex.getMessage());
        }finally{
            //Giai phong
            if(rs!=null){
               try{
                   rs.close();    
               }catch(SQLException sqlEx){}
               rs=null;
            }
            if(pStmt!= null){
                try{
                    pStmt.close();
                }catch(SQLException sqlEx){}
                pStmt=null;
            }
        }
        return xuat;
        
    }
    public int getStatus(String ban){
        int xuat=0;
        PreparedStatement pStmt=null;
        ResultSet rs =null;
        
        try{
            pStmt = conn.prepareStatement("select xuatTT(?)");
            pStmt.setString(1, ban);
            rs=pStmt.executeQuery();
            while(rs.next()){
               xuat=rs.getInt(1); 
            }
        }catch(Exception ex){
            System.out.println("SQL exception: "+ex.getMessage());
        }finally{
            //Giai phong
            if(rs!=null){
               try{
                   rs.close();    
               }catch(SQLException sqlEx){}
               rs=null;
            }
            if(pStmt!= null){
                try{
                    pStmt.close();
                }catch(SQLException sqlEx){}
                pStmt=null;
            }
        }
        return xuat;
        
    }
    private void update_label(){
        for(int i=1;i<=10;i++){
            String temp="So"+i;
            if(getStatus(temp)==3){
                if(i==1)
                    lbSo1.setForeground(Color.red);
                else if(i==2)
                    lbSo2.setForeground(Color.red);
                else if(i==3)
                    lbSo3.setForeground(Color.red);
                else if(i==4)
                   lbSo4.setForeground(Color.red);
                else if(i==5)
                    lbSo5.setForeground(Color.red);
                else if(i==6)
                    lbSo6.setForeground(Color.red);
                else if(i==7)
                    lbSo7.setForeground(Color.red);
                else if(i==8)
                    lbSo8.setForeground(Color.red);
                else if(i==9)
                    lbSo9.setForeground(Color.red);
                else
                lbSo10.setForeground(Color.red);
                
            }else{
                if(i==1)
                    lbSo1.setForeground(Color.BLACK);
                else if(i==2)
                    lbSo2.setForeground(Color.BLACK);
                else if(i==3)
                    lbSo3.setForeground(Color.BLACK);
                else if(i==4)
                    lbSo4.setForeground(Color.BLACK);
                else if(i==5)
                    lbSo5.setForeground(Color.BLACK);
                else if(i==6)
                    lbSo6.setForeground(Color.BLACK);
                else if(i==7)
                    lbSo7.setForeground(Color.BLACK);
                else if(i==8)
                    lbSo8.setForeground(Color.BLACK);
                else if(i==9)
                    lbSo9.setForeground(Color.BLACK);
                else 
                    lbSo10.setForeground(Color.BLACK);
                
                
            }
                 
        }
    }
    
    private void dangDat(String ban){
        CallableStatement cStmt = null; 
        ResultSet rs =null;
        try{
        cStmt = conn.prepareCall("{call dangdat(?)}"); 
        cStmt.setString(1, ban); 
        rs = cStmt.executeQuery();
        }catch(Exception ex){
            System.out.println("SQL exception: "+ex.getMessage());
        }finally{
            //Giai phong
            if(rs!=null){
               try{
                   rs.close();    
               }catch(SQLException sqlEx){}
               rs=null;
            }
            if(cStmt!= null){
                try{
                    cStmt.close();
                }catch(SQLException sqlEx){}
                cStmt=null;
            }
        }
    }
    private void dungXong(String ban){
        CallableStatement cStmt = null; 
        ResultSet rs =null;
        try{
        cStmt = conn.prepareCall("{call dungXong(?)}"); 
        cStmt.setString(1, ban); 
        rs = cStmt.executeQuery();
        }catch(Exception ex){
            System.out.println("SQL exception: "+ex.getMessage());
        }finally{
            //Giai phong
            if(rs!=null){
               try{
                   rs.close();    
               }catch(SQLException sqlEx){}
               rs=null;
            }
            if(cStmt!= null){
                try{
                    cStmt.close();
                }catch(SQLException sqlEx){}
                cStmt=null;
            }
        }
    }
    private void datXong(String ban){
        CallableStatement cStmt = null; 
        ResultSet rs =null;
        PreparedStatement pSm= null;
        try{
        cStmt = conn.prepareCall("{call datXong(?)}"); 
        cStmt.setString(1, ban); 
        rs = cStmt.executeQuery();
        pSm = conn.prepareStatement("insert into datBan(soBan,userName,soNguoi,timeDatban,yeuCau) values(?,?,?,?,?)");
        pSm.setString(1, bann);
        pSm.setString(2, usernamesession);
        pSm.setString(3, cbxBanNguoi.getSelectedItem().toString());
        String timedat=cbxTimeDat.getSelectedItem().toString()+" kể từ "+hour+":"+minute;
        pSm.setString(4,timedat );
        pSm.setString(5, jTextArea1.getText().toString());
        pSm.executeUpdate();
        }catch(Exception ex){
            System.out.println("SQL exception: "+ex.getMessage());
        }finally{
            //Giai phong
            if(rs!=null){
               try{
                   rs.close();    
               }catch(SQLException sqlEx){}
               rs=null;
            }
            if(cStmt!= null){
                try{
                    cStmt.close();
                }catch(SQLException sqlEx){}
                cStmt=null;
            }
            if(pSm!= null){
                try{
                    pSm.close();
                }catch(SQLException sqlEx){}
                pSm=null;
            }
        }
    }
    
    private void update_history(String nd){
        ResultSet rs =null;
        PreparedStatement pSm= null;
        try{
        pSm = conn.prepareStatement("insert into history_users(userNameH,TimeH,noiDung) values(?,?,?)");
        pSm.setString(1, usernamesession);
        String time1=hour+":"+minute+" ngay: "+day+"-"+month+"-"+year;
        pSm.setString(2,time1 );
        pSm.setString(3,nd);
        pSm.executeUpdate();
        }catch(Exception ex){
            System.out.println("SQL exception: "+ex.getMessage());
        }finally{
            //Giai phong
            if(rs!=null){
               try{
                   rs.close();    
               }catch(SQLException sqlEx){}
               rs=null;
            }
            if(pSm!= null){
                try{
                    pSm.close();
                }catch(SQLException sqlEx){}
                pSm=null;
            }
        }
    }
    
    public HomeUser() {
        initComponents();
        clock();
        ConnectDatabase();
        usernamesession="";
       
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        btn_4 = new javax.swing.JPanel();
        ind_4 = new javax.swing.JPanel();
        lbThu4 = new javax.swing.JLabel();
        btn_2 = new javax.swing.JPanel();
        ind_2 = new javax.swing.JPanel();
        lbThu2 = new javax.swing.JLabel();
        btn_5 = new javax.swing.JPanel();
        ind_5 = new javax.swing.JPanel();
        lbThu5 = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbNhanTichDiem = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbTichDiem = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbNameuser = new javax.swing.JLabel();
        lbTime = new javax.swing.JLabel();
        lbThu = new javax.swing.JLabel();
        lbDay = new javax.swing.JLabel();
        Container = new javax.swing.JPanel();
        PanelHome = new javax.swing.JPanel();
        btnOrder = new javax.swing.JButton();
        lbStaff = new javax.swing.JLabel();
        btnDailyCheck = new javax.swing.JButton();
        lbOrder = new javax.swing.JLabel();
        lbDailyCheckin = new javax.swing.JLabel();
        btnContact = new javax.swing.JButton();
        lbContact = new javax.swing.JLabel();
        lbWelcome = new javax.swing.JLabel();
        lbNameuser1 = new javax.swing.JLabel();
        lbWelcome1 = new javax.swing.JLabel();
        PanelDatban = new javax.swing.JPanel();
        btnSo1 = new javax.swing.JButton();
        btnSo2 = new javax.swing.JButton();
        btnSo3 = new javax.swing.JButton();
        btnSo4 = new javax.swing.JButton();
        btnSo5 = new javax.swing.JButton();
        btnSo6 = new javax.swing.JButton();
        btnSo7 = new javax.swing.JButton();
        btnSo8 = new javax.swing.JButton();
        btnSo9 = new javax.swing.JButton();
        btnSo10 = new javax.swing.JButton();
        lbSo1 = new javax.swing.JLabel();
        lbSo2 = new javax.swing.JLabel();
        lbSo3 = new javax.swing.JLabel();
        lbSo4 = new javax.swing.JLabel();
        lbSo5 = new javax.swing.JLabel();
        lbSo6 = new javax.swing.JLabel();
        lbSo7 = new javax.swing.JLabel();
        lbSo8 = new javax.swing.JLabel();
        lbSo9 = new javax.swing.JLabel();
        lbSo10 = new javax.swing.JLabel();
        lbBack = new javax.swing.JLabel();
        lbWelcome3 = new javax.swing.JLabel();
        lbWelcome2 = new javax.swing.JLabel();
        btnRefesh = new javax.swing.JButton();
        lbWelcome4 = new javax.swing.JLabel();
        PanelLienhe = new javax.swing.JPanel();
        lbBack1 = new javax.swing.JLabel();
        lbStaff2 = new javax.swing.JLabel();
        lbWelcome7 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tAPhanHoi = new javax.swing.JTextArea();
        btnPhanHoi = new javax.swing.JButton();
        PanelFormDB = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btnDat = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cbxBanNguoi = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbxTimeDat = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        lbTitle = new javax.swing.JLabel();
        PanelHistory = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbHistory = new javax.swing.JTable();
        PanelProfile = new javax.swing.JPanel();
        lbStaff1 = new javax.swing.JLabel();
        lbNameuser2 = new javax.swing.JLabel();
        lbWelcome5 = new javax.swing.JLabel();
        lbWelcome6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lbNameuser3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lbPasswordprofile = new javax.swing.JLabel();
        btnShowpass = new javax.swing.JButton();
        btnChangePass = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        topPanel.setBackground(new java.awt.Color(32, 90, 180));
        topPanel.setForeground(new java.awt.Color(255, 255, 255));
        topPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                topPanelMouseDragged(evt);
            }
        });
        topPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                topPanelMousePressed(evt);
            }
        });
        topPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_4.setBackground(new java.awt.Color(32, 90, 180));
        btn_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_4MousePressed(evt);
            }
        });
        btn_4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ind_4.setOpaque(false);
        ind_4.setPreferredSize(new java.awt.Dimension(3, 42));

        javax.swing.GroupLayout ind_4Layout = new javax.swing.GroupLayout(ind_4);
        ind_4.setLayout(ind_4Layout);
        ind_4Layout.setHorizontalGroup(
            ind_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        ind_4Layout.setVerticalGroup(
            ind_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        btn_4.add(ind_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 3));

        lbThu4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lbThu4.setForeground(new java.awt.Color(255, 255, 255));
        lbThu4.setText("History");
        btn_4.add(lbThu4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, -1, 50));

        topPanel.add(btn_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 110, 50));

        btn_2.setBackground(new java.awt.Color(45, 57, 80));
        btn_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_2MousePressed(evt);
            }
        });
        btn_2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ind_2.setPreferredSize(new java.awt.Dimension(42, 3));

        javax.swing.GroupLayout ind_2Layout = new javax.swing.GroupLayout(ind_2);
        ind_2.setLayout(ind_2Layout);
        ind_2Layout.setHorizontalGroup(
            ind_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        ind_2Layout.setVerticalGroup(
            ind_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        btn_2.add(ind_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 3));

        lbThu2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lbThu2.setForeground(new java.awt.Color(255, 255, 255));
        lbThu2.setText("HOME");
        btn_2.add(lbThu2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 2, -1, 50));

        topPanel.add(btn_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 120, 50));

        btn_5.setBackground(new java.awt.Color(32, 90, 180));
        btn_5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_5MousePressed(evt);
            }
        });
        btn_5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ind_5.setOpaque(false);
        ind_5.setPreferredSize(new java.awt.Dimension(3, 42));

        javax.swing.GroupLayout ind_5Layout = new javax.swing.GroupLayout(ind_5);
        ind_5.setLayout(ind_5Layout);
        ind_5Layout.setHorizontalGroup(
            ind_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        ind_5Layout.setVerticalGroup(
            ind_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        btn_5.add(ind_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 3));

        lbThu5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lbThu5.setForeground(new java.awt.Color(255, 255, 255));
        lbThu5.setText("Profile");
        btn_5.add(lbThu5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, 50));

        topPanel.add(btn_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, 110, 50));

        btnExit.setBackground(new java.awt.Color(32, 90, 180));
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/techcoffee/Images/ic-turnoff.png"))); // NOI18N
        btnExit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnExit.setBorderPainted(false);
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitMouseClicked(evt);
            }
        });
        topPanel.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 0, 40, 50));

        getContentPane().add(topPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 50));

        jPanel3.setBackground(new java.awt.Color(32, 90, 180));

        jPanel4.setBackground(new java.awt.Color(97, 136, 198));

        jPanel5.setBackground(new java.awt.Color(32, 56, 151));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/techcoffee/Images/ic-avatar.png"))); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/techcoffee/Images/ic-calendar.png"))); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/techcoffee/Images/ic-padlock.png"))); // NOI18N

        lbNhanTichDiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/techcoffee/Images/ic-giftbox.png"))); // NOI18N
        lbNhanTichDiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbNhanTichDiemMouseClicked(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/techcoffee/Images/ic-notiffi.png"))); // NOI18N

        lbTichDiem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbTichDiem.setForeground(new java.awt.Color(255, 255, 255));
        lbTichDiem.setText("jLabel14");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNhanTichDiem))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel6)
                        .addGap(43, 43, 43))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(lbTichDiem)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel6)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)))
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTichDiem)
                    .addComponent(lbNhanTichDiem))
                .addGap(23, 23, 23))
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/techcoffee/Images/ic-user.png"))); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/techcoffee/Images/ic-logout.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        lbNameuser.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lbNameuser.setForeground(new java.awt.Color(255, 255, 255));
        lbNameuser.setText("NameUser");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbNameuser, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(27, 27, 27))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNameuser)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lbTime.setFont(new java.awt.Font("SansSerif", 0, 42)); // NOI18N
        lbTime.setForeground(new java.awt.Color(255, 255, 255));
        lbTime.setText(" Clock");

        lbThu.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lbThu.setForeground(new java.awt.Color(255, 255, 255));
        lbThu.setText("Thu");

        lbDay.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lbDay.setForeground(new java.awt.Color(255, 255, 255));
        lbDay.setText("Date");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lbThu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbDay, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbTime, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbThu)
                    .addComponent(lbDay))
                .addGap(56, 56, 56)
                .addComponent(lbTime, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 260, 440));

        Container.setLayout(new java.awt.CardLayout());

        PanelHome.setBackground(new java.awt.Color(255, 255, 255));
        PanelHome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnOrder.setBackground(new java.awt.Color(255, 255, 255));
        btnOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/techcoffee/Images/ic-order.png"))); // NOI18N
        btnOrder.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnOrder.setBorderPainted(false);
        btnOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOrderMouseClicked(evt);
            }
        });
        PanelHome.add(btnOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, 130, 100));

        lbStaff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/techcoffee/Images/ic-staff.png"))); // NOI18N
        PanelHome.add(lbStaff, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 130, 150));

        btnDailyCheck.setBackground(new java.awt.Color(255, 255, 255));
        btnDailyCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/techcoffee/Images/ic-dailycheck.png"))); // NOI18N
        btnDailyCheck.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnDailyCheck.setBorderPainted(false);
        btnDailyCheck.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDailyCheckMouseClicked(evt);
            }
        });
        PanelHome.add(btnDailyCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 130, 100));

        lbOrder.setFont(new java.awt.Font("Noto Sans CJK KR Bold", 1, 14)); // NOI18N
        lbOrder.setText("Đặt Bàn");
        PanelHome.add(lbOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, 80, 30));

        lbDailyCheckin.setFont(new java.awt.Font("Noto Sans CJK KR Bold", 1, 14)); // NOI18N
        lbDailyCheckin.setText("Tích Điểm ");
        PanelHome.add(lbDailyCheckin, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 330, 90, 30));

        btnContact.setBackground(new java.awt.Color(255, 255, 255));
        btnContact.setIcon(new javax.swing.ImageIcon(getClass().getResource("/techcoffee/Images/ic-email.png"))); // NOI18N
        btnContact.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnContact.setBorderPainted(false);
        btnContact.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnContactMouseClicked(evt);
            }
        });
        PanelHome.add(btnContact, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 230, 130, 100));

        lbContact.setFont(new java.awt.Font("Noto Sans CJK KR Bold", 1, 14)); // NOI18N
        lbContact.setText("Liên Hệ");
        PanelHome.add(lbContact, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 330, 90, 30));

        lbWelcome.setFont(new java.awt.Font("Noto Sans CJK TC Thin", 1, 16)); // NOI18N
        lbWelcome.setText("Tôtô xin chúc quý khách có một ngày vui vẻ !");
        PanelHome.add(lbWelcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 500, 30));

        lbNameuser1.setFont(new java.awt.Font("Noto Sans CJK KR Thin", 1, 16)); // NOI18N
        lbNameuser1.setText("NameUser");
        PanelHome.add(lbNameuser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 200, 30));

        lbWelcome1.setFont(new java.awt.Font("Noto Sans CJK JP Thin", 1, 16)); // NOI18N
        lbWelcome1.setText("TechCoffe hân hạnh được phục vụ quý khách !");
        PanelHome.add(lbWelcome1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 500, 30));

        Container.add(PanelHome, "card2");

        PanelDatban.setBackground(new java.awt.Color(255, 255, 255));
        PanelDatban.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSo1.setBackground(new java.awt.Color(255, 255, 255));
        btnSo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/techcoffee/Images/ic-table.png"))); // NOI18N
        btnSo1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSo1.setBorderPainted(false);
        btnSo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSo1MouseClicked(evt);
            }
        });
        PanelDatban.add(btnSo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, 100));

        btnSo2.setBackground(new java.awt.Color(255, 255, 255));
        btnSo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/techcoffee/Images/ic-table.png"))); // NOI18N
        btnSo2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSo2.setBorderPainted(false);
        btnSo2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSo2MouseClicked(evt);
            }
        });
        PanelDatban.add(btnSo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 130, 100));

        btnSo3.setBackground(new java.awt.Color(255, 255, 255));
        btnSo3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/techcoffee/Images/ic-table.png"))); // NOI18N
        btnSo3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSo3.setBorderPainted(false);
        btnSo3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSo3MouseClicked(evt);
            }
        });
        PanelDatban.add(btnSo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 130, 100));

        btnSo4.setBackground(new java.awt.Color(255, 255, 255));
        btnSo4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/techcoffee/Images/ic-table.png"))); // NOI18N
        btnSo4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSo4.setBorderPainted(false);
        btnSo4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSo4MouseClicked(evt);
            }
        });
        PanelDatban.add(btnSo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 130, 100));

        btnSo5.setBackground(new java.awt.Color(255, 255, 255));
        btnSo5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/techcoffee/Images/ic-table.png"))); // NOI18N
        btnSo5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSo5.setBorderPainted(false);
        btnSo5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSo5MouseClicked(evt);
            }
        });
        PanelDatban.add(btnSo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 130, 100));

        btnSo6.setBackground(new java.awt.Color(255, 255, 255));
        btnSo6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/techcoffee/Images/ic-table.png"))); // NOI18N
        btnSo6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSo6.setBorderPainted(false);
        btnSo6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSo6MouseClicked(evt);
            }
        });
        PanelDatban.add(btnSo6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 130, 100));

        btnSo7.setBackground(new java.awt.Color(255, 255, 255));
        btnSo7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/techcoffee/Images/ic-table.png"))); // NOI18N
        btnSo7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSo7.setBorderPainted(false);
        btnSo7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSo7MouseClicked(evt);
            }
        });
        PanelDatban.add(btnSo7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, 130, 100));

        btnSo8.setBackground(new java.awt.Color(255, 255, 255));
        btnSo8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/techcoffee/Images/ic-table.png"))); // NOI18N
        btnSo8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSo8.setBorderPainted(false);
        btnSo8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSo8MouseClicked(evt);
            }
        });
        PanelDatban.add(btnSo8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 130, 100));

        btnSo9.setBackground(new java.awt.Color(255, 255, 255));
        btnSo9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/techcoffee/Images/ic-table.png"))); // NOI18N
        btnSo9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSo9.setBorderPainted(false);
        btnSo9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSo9MouseClicked(evt);
            }
        });
        PanelDatban.add(btnSo9, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 160, 130, 100));

        btnSo10.setBackground(new java.awt.Color(255, 255, 255));
        btnSo10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/techcoffee/Images/ic-table.png"))); // NOI18N
        btnSo10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSo10.setBorderPainted(false);
        btnSo10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSo10MouseClicked(evt);
            }
        });
        PanelDatban.add(btnSo10, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 160, 130, 100));

        lbSo1.setFont(new java.awt.Font("Noto Sans CJK KR Bold", 1, 14)); // NOI18N
        lbSo1.setText("Bàn Số 1");
        PanelDatban.add(lbSo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 90, 30));

        lbSo2.setFont(new java.awt.Font("Noto Sans CJK KR Bold", 1, 14)); // NOI18N
        lbSo2.setText("Bàn Số 2");
        PanelDatban.add(lbSo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 90, 30));

        lbSo3.setFont(new java.awt.Font("Noto Sans CJK KR Bold", 1, 14)); // NOI18N
        lbSo3.setText("Bàn Số 3");
        PanelDatban.add(lbSo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 90, 30));

        lbSo4.setFont(new java.awt.Font("Noto Sans CJK KR Bold", 1, 14)); // NOI18N
        lbSo4.setText("Bàn Số 4");
        PanelDatban.add(lbSo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 110, 90, 30));

        lbSo5.setFont(new java.awt.Font("Noto Sans CJK KR Bold", 1, 14)); // NOI18N
        lbSo5.setText("Bàn Số 5");
        PanelDatban.add(lbSo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 110, 90, 30));

        lbSo6.setFont(new java.awt.Font("Noto Sans CJK KR Bold", 1, 14)); // NOI18N
        lbSo6.setText("Bàn Số 6");
        PanelDatban.add(lbSo6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 90, 30));

        lbSo7.setFont(new java.awt.Font("Noto Sans CJK KR Bold", 1, 14)); // NOI18N
        lbSo7.setText("Bàn Số 7");
        PanelDatban.add(lbSo7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, 90, 30));

        lbSo8.setFont(new java.awt.Font("Noto Sans CJK KR Bold", 1, 14)); // NOI18N
        lbSo8.setText("Bàn Số 8");
        PanelDatban.add(lbSo8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 260, 90, 30));

        lbSo9.setFont(new java.awt.Font("Noto Sans CJK KR Bold", 1, 14)); // NOI18N
        lbSo9.setText("Bàn Số 9");
        PanelDatban.add(lbSo9, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 260, 90, 30));

        lbSo10.setFont(new java.awt.Font("Noto Sans CJK KR Bold", 1, 14)); // NOI18N
        lbSo10.setText("Bàn Số 10");
        PanelDatban.add(lbSo10, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 260, 90, 30));

        lbBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/techcoffee/Images/ic-back.png"))); // NOI18N
        lbBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbBackMouseClicked(evt);
            }
        });
        PanelDatban.add(lbBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 360, -1, 60));

        lbWelcome3.setFont(new java.awt.Font("Noto Sans CJK JP Thin", 1, 16)); // NOI18N
        lbWelcome3.setText("Lưu ý: Quý khách chỉ được đặt bàn trong ngày !");
        PanelDatban.add(lbWelcome3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 380, 30));

        lbWelcome2.setFont(new java.awt.Font("Noto Sans CJK JP Thin", 1, 16)); // NOI18N
        lbWelcome2.setText("Refesh list");
        PanelDatban.add(lbWelcome2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 310, 80, 30));

        btnRefesh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/techcoffee/Images/ic-refresh.png"))); // NOI18N
        btnRefesh.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnRefesh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRefeshMouseClicked(evt);
            }
        });
        PanelDatban.add(btnRefesh, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 300, 40, 40));

        lbWelcome4.setFont(new java.awt.Font("Noto Sans CJK JP Thin", 1, 16)); // NOI18N
        lbWelcome4.setText("Màu đỏ: Đã có khách khác đặt !");
        PanelDatban.add(lbWelcome4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 260, 30));

        Container.add(PanelDatban, "card3");

        PanelLienhe.setBackground(new java.awt.Color(255, 255, 255));
        PanelLienhe.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbBack1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/techcoffee/Images/ic-back.png"))); // NOI18N
        lbBack1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbBack1MouseClicked(evt);
            }
        });
        PanelLienhe.add(lbBack1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 360, -1, 60));

        lbStaff2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/techcoffee/Images/ic-staff.png"))); // NOI18N
        PanelLienhe.add(lbStaff2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 130, 150));

        lbWelcome7.setFont(new java.awt.Font("Noto Sans CJK JP Thin", 1, 16)); // NOI18N
        lbWelcome7.setText("Tôtô rất muốn nhận được những lời phản hồi từ phía bạn !");
        PanelLienhe.add(lbWelcome7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 460, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Phản hồi :");
        PanelLienhe.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, -1, 20));

        tAPhanHoi.setColumns(20);
        tAPhanHoi.setRows(5);
        jScrollPane2.setViewportView(tAPhanHoi);

        PanelLienhe.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 410, 120));

        btnPhanHoi.setText("SEND");
        btnPhanHoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPhanHoiMouseClicked(evt);
            }
        });
        PanelLienhe.add(btnPhanHoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 330, 80, 30));

        Container.add(PanelLienhe, "card3");

        PanelFormDB.setBackground(new java.awt.Color(255, 255, 255));
        PanelFormDB.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Hủy Đặt");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        PanelFormDB.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 360, -1, -1));

        btnDat.setText("Đặt");
        btnDat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDatMouseClicked(evt);
            }
        });
        PanelFormDB.add(btnDat, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 360, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("tính từ bây giờ.");
        PanelFormDB.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, 100, 30));

        cbxBanNguoi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 người", "2 người", "3 người", "4 người", "5 người", "6 người", "hơn 6 người" }));
        PanelFormDB.add(cbxBanNguoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, -1, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Các yêu cầu khác      :        ");
        PanelFormDB.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 170, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Thời gian đặt   :");
        PanelFormDB.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 100, 30));

        cbxTimeDat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "15 phút", "30 phút", "45 phút", "1 giờ", "1 giờ 30 phút", "2 giờ", "2 giờ 30 phút", "3 giờ" }));
        PanelFormDB.add(cbxTimeDat, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, -1, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Số người       :");
        PanelFormDB.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 90, 30));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        PanelFormDB.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, 260, 140));

        lbTitle.setFont(new java.awt.Font("Noto Sans CJK JP Thin", 1, 16)); // NOI18N
        lbTitle.setText("Temp");
        PanelFormDB.add(lbTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 100, 30));

        Container.add(PanelFormDB, "card3");

        PanelHistory.setBackground(new java.awt.Color(255, 255, 255));
        PanelHistory.setMinimumSize(new java.awt.Dimension(690, 360));
        PanelHistory.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Thời Gian", "Nội Dung"
            }
        ));
        jScrollPane3.setViewportView(tbHistory);

        PanelHistory.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 660, 210));

        Container.add(PanelHistory, "card3");

        PanelProfile.setBackground(new java.awt.Color(255, 255, 255));
        PanelProfile.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbStaff1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/techcoffee/Images/ic-staff.png"))); // NOI18N
        PanelProfile.add(lbStaff1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 130, 150));

        lbNameuser2.setFont(new java.awt.Font("Noto Sans CJK KR Thin", 1, 16)); // NOI18N
        lbNameuser2.setText("NameUser");
        PanelProfile.add(lbNameuser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 200, 30));

        lbWelcome5.setFont(new java.awt.Font("Noto Sans CJK JP Thin", 1, 16)); // NOI18N
        lbWelcome5.setText("Tôtô xin chào quý khách !");
        PanelProfile.add(lbWelcome5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 230, 30));

        lbWelcome6.setFont(new java.awt.Font("Noto Sans CJK JP Thin", 1, 16)); // NOI18N
        lbWelcome6.setText("Đây là phần xem và hiển thị cài đặt tài khoản ! ");
        PanelProfile.add(lbWelcome6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 380, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Mật khẩu          :");
        PanelProfile.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, -1, -1));

        lbNameuser3.setFont(new java.awt.Font("Noto Sans CJK KR Thin", 1, 16)); // NOI18N
        lbNameuser3.setText("NameUser");
        PanelProfile.add(lbNameuser3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, 200, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Tên Đăng Nhập: ");
        PanelProfile.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, -1, -1));

        lbPasswordprofile.setFont(new java.awt.Font("Noto Sans CJK KR Thin", 1, 16)); // NOI18N
        lbPasswordprofile.setText("*************");
        PanelProfile.add(lbPasswordprofile, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 249, 110, -1));

        btnShowpass.setBackground(new java.awt.Color(255, 255, 255));
        btnShowpass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/techcoffee/Images/ic-show.png"))); // NOI18N
        btnShowpass.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnShowpass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnShowpassMouseClicked(evt);
            }
        });
        PanelProfile.add(btnShowpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 240, 40, 30));

        btnChangePass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnChangePass.setText("Đổi mật khẩu");
        btnChangePass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnChangePassMouseClicked(evt);
            }
        });
        PanelProfile.add(btnChangePass, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 280, 120, 30));

        Container.add(PanelProfile, "card3");

        getContentPane().add(Container, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 700, 440));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_4MousePressed
        // TODO add your handling code here:
        
        setColor(btn_4);
        ind_4.setOpaque(true);
        resetColor(new JPanel[]{btn_2,btn_5},new JPanel[]{ind_2,ind_5});
        switchPanel(PanelHistory);
        PreparedStatement pSm=null;
        ResultSet rs =null;
        try {
            pSm = conn.prepareStatement("SELECT * from history_users");
            rs = pSm.executeQuery();
            DefaultTableModel tm = (DefaultTableModel) tbHistory.getModel();
            tm.setRowCount(0);
            while (rs.next()) {
                Object o[] = {rs.getString("idHistory"), rs.getString("TimeH"), rs.getString("noiDung")}; 
                tm.addRow(o);
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }//GEN-LAST:event_btn_4MousePressed

    private void btn_5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_5MousePressed
        // TODO add your handling code here:
        setColor(btn_5);
        ind_5.setOpaque(true);
        resetColor(new JPanel[]{btn_2,btn_4},new JPanel[]{ind_2,ind_4});
        String lbnameuser2 ="Chào "+usernamesession+",";
        lbNameuser2.setText(lbnameuser2);
        lbNameuser3.setText(usernamesession);
        switchPanel(PanelProfile);
    }//GEN-LAST:event_btn_5MousePressed

    private void btn_2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_2MousePressed
        // TODO add your handling code here:
        setColor(btn_2);
        ind_2.setOpaque(true);
        resetColor(new JPanel[]{btn_4,btn_5},new JPanel[]{ind_4,ind_5});
        if(tempForm==1)
            switchPanel(PanelFormDB);
        else
            switchPanel(PanelHome);
    }//GEN-LAST:event_btn_2MousePressed

    //Drag window 
    int xx,xy;
    private void topPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topPanelMousePressed
        // TODO add your handling code here:
        xx=evt.getX();
        xy=evt.getY();
    }//GEN-LAST:event_topPanelMousePressed

    private void topPanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topPanelMouseDragged
        // TODO add your handling code here:
        int x=evt.getXOnScreen();
        int y=evt.getYOnScreen();
        this.setLocation(x-xx,y-xy);
    }//GEN-LAST:event_topPanelMouseDragged

    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseClicked
        // TODO add your handling code here:
        //Khi o trong form dat ban ma thoat ra
        if(tempForm==1)
            dungXong(bann);
        System.exit(0);
    }//GEN-LAST:event_btnExitMouseClicked

    private void btnOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOrderMouseClicked
        // TODO add your handling code here:
        switchPanel(PanelDatban);
        update_label();
    }//GEN-LAST:event_btnOrderMouseClicked

    private void btnDailyCheckMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDailyCheckMouseClicked
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this,"Với mỗi lần đặt bàn bạn sẽ có 1 điểm, đủ 14 điểm nhấn vào hộp quà bên trái để nhận thưởng");
    }//GEN-LAST:event_btnDailyCheckMouseClicked

    private void btnContactMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContactMouseClicked
        // TODO add your handling code here:
        switchPanel(PanelLienhe);
    }//GEN-LAST:event_btnContactMouseClicked

    private void btnSo10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSo10MouseClicked
        // TODO add your handling code here:
        bann="So10";
        int temp;
        temp=getStatus(bann);
        if(temp==2)
            JOptionPane.showMessageDialog(this,"Bàn đang được người khác đặt");
        else if(temp==3){
            JOptionPane.showMessageDialog(this,"Bàn đã được người khác đặt");
        }
        else{
        dangDat(bann);
        switchPanel(PanelFormDB);
        lbTitle.setText("Bàn "+bann);
        tempForm=1;
        }
    }//GEN-LAST:event_btnSo10MouseClicked

    private void btnSo9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSo9MouseClicked
        // TODO add your handling code here:
        bann="So9";
        int temp;
        temp=getStatus(bann);
        if(temp==2)
            JOptionPane.showMessageDialog(this,"Bàn đang được người khác đặt");
        else if(temp==3){
            JOptionPane.showMessageDialog(this,"Bàn đã được người khác đặt");
        }
        else{
        dangDat(bann);
        switchPanel(PanelFormDB);
        lbTitle.setText("Bàn "+bann);
        tempForm=1;
        }
    }//GEN-LAST:event_btnSo9MouseClicked

    private void btnSo8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSo8MouseClicked
        // TODO add your handling code here:
        bann="So8";
        int temp;
        temp=getStatus(bann);
        if(temp==2)
            JOptionPane.showMessageDialog(this,"Bàn đang được người khác đặt");
        else if(temp==3){
            JOptionPane.showMessageDialog(this,"Bàn đã được người khác đặt");
        }
        else{
        dangDat(bann);
        switchPanel(PanelFormDB);
        lbTitle.setText("Bàn "+bann);
        tempForm=1;
        
        }
    }//GEN-LAST:event_btnSo8MouseClicked

    private void btnSo7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSo7MouseClicked
        // TODO add your handling code here:
        bann="So7";
        int temp;
        temp=getStatus(bann);
        if(temp==2)
            JOptionPane.showMessageDialog(this,"Bàn đang được người khác đặt");
        else if(temp==3){
            JOptionPane.showMessageDialog(this,"Bàn đã được người khác đặt");
        }
        else{
        dangDat(bann);
        switchPanel(PanelFormDB);
        lbTitle.setText("Bàn "+bann);
        tempForm=1;
        }
    }//GEN-LAST:event_btnSo7MouseClicked

    private void btnSo6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSo6MouseClicked
        // TODO add your handling code here:
        bann="So6";
        int temp;
        temp=getStatus(bann);
        if(temp==2)
            JOptionPane.showMessageDialog(this,"Bàn đang được người khác đặt");
        else if(temp==3){
            JOptionPane.showMessageDialog(this,"Bàn đã được người khác đặt");
        }
        else{
        dangDat(bann);
        switchPanel(PanelFormDB);
        lbTitle.setText("Bàn "+bann);
        tempForm=1;
        }
    }//GEN-LAST:event_btnSo6MouseClicked

    private void btnSo5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSo5MouseClicked
        // TODO add your handling code here:
        bann="So5";
        int temp;
        temp=getStatus(bann);
        if(temp==2)
            JOptionPane.showMessageDialog(this,"Bàn đang được người khác đặt");
        else if(temp==3){
            JOptionPane.showMessageDialog(this,"Bàn đã được người khác đặt");
        }
        else{
        dangDat(bann);
        switchPanel(PanelFormDB);
        lbTitle.setText("Bàn "+bann);
        tempForm=1;
        }
    }//GEN-LAST:event_btnSo5MouseClicked

    private void btnSo4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSo4MouseClicked
        // TODO add your handling code here:
        bann="So4";
        int temp;
        temp=getStatus(bann);
        if(temp==2)
            JOptionPane.showMessageDialog(this,"Bàn đang được người khác đặt");
        else if(temp==3){
            JOptionPane.showMessageDialog(this,"Bàn đã được người khác đặt");
        }
        else{
        dangDat(bann);
        switchPanel(PanelFormDB);
        lbTitle.setText("Bàn "+bann);
        tempForm=1;
        }
    }//GEN-LAST:event_btnSo4MouseClicked

    private void btnSo3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSo3MouseClicked
        // TODO add your handling code here:
        bann="So3";
        int temp;
        temp=getStatus(bann);
        if(temp==2)
            JOptionPane.showMessageDialog(this,"Bàn đang được người khác đặt");
        else if(temp==3){
            JOptionPane.showMessageDialog(this,"Bàn đã được người khác đặt");
        }
        else{
        dangDat(bann);
        switchPanel(PanelFormDB);
        lbTitle.setText("Bàn "+bann);
        tempForm=1;
        }
    }//GEN-LAST:event_btnSo3MouseClicked

    private void btnSo2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSo2MouseClicked
        // TODO add your handling code here:
        soban=2;
        bann="So2";
        int temp;
        temp=getStatus(bann);
        if(temp==2)
            JOptionPane.showMessageDialog(this,"Bàn đang được người khác đặt");
        else if(temp==3){
            JOptionPane.showMessageDialog(this,"Bàn đã được người khác đặt");
        }
        else{
        dangDat(bann);
        switchPanel(PanelFormDB);
        lbTitle.setText("Bàn "+bann);
        tempForm=1;
        }
    }//GEN-LAST:event_btnSo2MouseClicked

    private void btnSo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSo1MouseClicked
        // TODO add your handling code here:
        soban=1;
        bann="So1";
        int temp;
        temp=getStatus(bann);
        if(temp==2)
            JOptionPane.showMessageDialog(this,"Bàn đang được người khác đặt");
        else if(temp==3){
            JOptionPane.showMessageDialog(this,"Bàn đã được người khác đặt");
        }
        else{
        dangDat(bann);
        switchPanel(PanelFormDB);
        lbTitle.setText("Bàn "+bann);
        tempForm=1;
        }
        
//        int temp;
//        temp=getStatus(soban);
//        if(temp==2)
//            JOptionPane.showMessageDialog(this,"Bàn đang được người khác đặt");
    }//GEN-LAST:event_btnSo1MouseClicked

    private void lbBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbBackMouseClicked
        // TODO add your handling code here:
        switchPanel(PanelHome);
    }//GEN-LAST:event_lbBackMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        Login login=new Login();
        login.setVisible(true);
        login.pack();
        login.setLocationRelativeTo(null);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        tempForm=0;
        dungXong(bann);
        switchPanel(PanelDatban);

    }//GEN-LAST:event_jButton1MouseClicked

    private void btnRefeshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefeshMouseClicked
        // TODO add your handling code here:
        update_label();
    }//GEN-LAST:event_btnRefeshMouseClicked

    private void btnDatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDatMouseClicked
        // TODO add your handling code here:
        tempForm=0;
        datXong(bann);
        int tempTD=getTichDiem(usernamesession);
        if(tempTD<14) {
            CallableStatement cStmt = null; 
            ResultSet rs =null;
            try{
            cStmt = conn.prepareCall("{call update_tichdiem(?,?)}"); 
            cStmt.setString(1,usernamesession); 
            tempTD=tempTD+1;
            cStmt.setInt(2,tempTD); 
            rs = cStmt.executeQuery();
            }catch(Exception ex){
                System.out.println("SQL exception: "+ex.getMessage());
            }finally{
                //Giai phong
                if(rs!=null){
                   try{
                       rs.close();    
                   }catch(SQLException sqlEx){}
                   rs=null;
                }
                if(cStmt!= null){
                    try{
                        cStmt.close();
                    }catch(SQLException sqlEx){}
                    cStmt=null;
                }
            }        
        }
        setTichDiem(usernamesession);
        update_history("Đặt bàn: "+bann);
        switchPanel(PanelHome);
    }//GEN-LAST:event_btnDatMouseClicked

    private void btnShowpassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnShowpassMouseClicked
        // TODO add your handling code here:
        //Popup input
        JPasswordField field1 = new JPasswordField("");
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Nhập mật khẩu:"));
        panel.add(field1);
        int result = JOptionPane.showConfirmDialog(null, panel, "Xác nhận",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
                PreparedStatement pSm=null;
                ResultSet rs =null;
            try {
             
                pSm = conn.prepareCall("SELECT * from users where username=? and passwd=?");
                pSm.setString(1, usernamesession);
                pSm.setString(2, field1.getText().toString());
                rs = pSm.executeQuery();
                if (rs.next()) {
                    String temp1=rs.getString(3);
                    lbPasswordprofile.setText(temp1);

                } else {
                    JOptionPane.showMessageDialog(null, "Mật Khẩu Sai");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Server error");
                System.out.println("SQL exception: " + e.getMessage());
            }finally{
            //Giai phong
            if(rs!=null){
               try{
                   rs.close();    
               }catch(SQLException sqlEx){}
               rs=null;
            }
            if(pSm!= null){
                try{
                    pSm.close();
                }catch(SQLException sqlEx){}
                pSm=null;
            }
        }
        } else {
    
        }
    }//GEN-LAST:event_btnShowpassMouseClicked

    private void btnChangePassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChangePassMouseClicked
        // TODO add your handling code here:
        JPasswordField field0 = new JPasswordField("");
        JPasswordField field1 = new JPasswordField("");
        JPasswordField field2 = new JPasswordField("");
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Nhập mật khẩu cũ:"));
        panel.add(field0);
        panel.add(new JLabel("Nhập mật khẩu mới:"));
        panel.add(field1);
        panel.add(new JLabel("Nhập lại mật khẩu:"));
        panel.add(field2);
        int result = JOptionPane.showConfirmDialog(null, panel, "Đổi mật khẩu",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if(field1.getText().equals("")){
                JOptionPane.showMessageDialog(this,"Phải nhập đủ thông tin đăng ký");
            }else if(!field2.getText().toString().equals(field1.getText().toString())){
                JOptionPane.showMessageDialog(this,"2 mật khẩu phải giống nhau");
            }else if(field1.getText().length()<3 ||field0.getText().length()<3 ){
                JOptionPane.showMessageDialog(this,"Mật khẩu phải nhiều hơn 3 kí tự");
            }else{
                int check=0;
                PreparedStatement pSm=null;
                ResultSet rs =null;
            try {
             
                pSm = conn.prepareCall("SELECT * from users where username=? and passwd=?");
                pSm.setString(1, usernamesession);
                pSm.setString(2, field0.getText().toString());
                rs = pSm.executeQuery();
                
                if (rs.next()) {
                    check=1;

                } else {
                    JOptionPane.showMessageDialog(null, "Mật Khẩu Sai");
                }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Server error");
                    System.out.println("SQL exception: " + e.getMessage());
                }finally{
                //Giai phong
                if(rs!=null){
                   try{
                       rs.close();    
                   }catch(SQLException sqlEx){}
                   rs=null;
                    }
                if(pSm!= null){
                    try{
                        pSm.close();
                    }catch(SQLException sqlEx){}
                    pSm=null;
                }
                }
                
        if(check==1){   
            
            CallableStatement cStmt = null; 
            //ResultSet rs =null;
            try{
            cStmt = conn.prepareCall("{call update_pass(?,?)}"); 
            cStmt.setString(1,usernamesession); 
            cStmt.setString(2,field1.getText().toString()); 
            rs = cStmt.executeQuery();
            JOptionPane.showMessageDialog(this,"Đổi mật khẩu thành công");
            }catch(Exception ex){
                System.out.println("SQL exception: "+ex.getMessage());
            }finally{
                //Giai phong
                if(rs!=null){
                   try{
                       rs.close();    
                   }catch(SQLException sqlEx){}
                   rs=null;
                }
                if(cStmt!= null){
                    try{
                        cStmt.close();
                    }catch(SQLException sqlEx){}
                    cStmt=null;
                }
            }
            update_history("Đổi mật khẩu");
        }
        }
        }
    }//GEN-LAST:event_btnChangePassMouseClicked

    private void lbNhanTichDiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNhanTichDiemMouseClicked
        // TODO add your handling code here:
        if(getTichDiem(usernamesession)<14){
            JOptionPane.showMessageDialog(this,"Bạn chưa đủ điểm để nhận thưởng");
        }else if(getTichDiem(usernamesession)==15){
            JOptionPane.showMessageDialog(this,"Hãy đến techcoffe nhận thưởng và tham gia tiếp");
        }else if(getTichDiem(usernamesession)==14){
            CallableStatement cStmt = null; 
            ResultSet rs =null;
            try{
            cStmt = conn.prepareCall("{call update_tichdiem(?,?)}"); 
            cStmt.setString(1,usernamesession); 
            cStmt.setInt(2,15); 
            rs = cStmt.executeQuery();
            update_history("Xác nhận nhận thưởng");
            JOptionPane.showMessageDialog(this,"Chúc mừng bạn! hãy đến techcoffe để nhận thưởng trực tiếp");
            }catch(Exception ex){
                System.out.println("SQL exception: "+ex.getMessage());
            }finally{
                //Giai phong
                if(rs!=null){
                   try{
                       rs.close();    
                   }catch(SQLException sqlEx){}
                   rs=null;
                }
                if(cStmt!= null){
                    try{
                        cStmt.close();
                    }catch(SQLException sqlEx){}
                    cStmt=null;
                }
            }
        }
    }//GEN-LAST:event_lbNhanTichDiemMouseClicked

    private void lbBack1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbBack1MouseClicked
        // TODO add your handling code here:
        switchPanel(PanelHome);
    }//GEN-LAST:event_lbBack1MouseClicked

    private void btnPhanHoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPhanHoiMouseClicked
        // TODO add your handling code here:
        if(tAPhanHoi.getText().toString().equals("")){
            JOptionPane.showMessageDialog(this,"Bạn hãy nhập gì đó trước khi gửi");
        }else{
        PreparedStatement pSm= null;
        try{
        pSm = conn.prepareStatement("insert into feedback(userNameF,TimeF,noiDung) values(?,?,?)");
        pSm.setString(1, usernamesession);
        String timeF=hour+":"+minute+" date:"+day+"/"+month+"/"+year;
        pSm.setString(2, timeF);
        pSm.setString(3, tAPhanHoi.getText().toString());
        pSm.executeUpdate();
        JOptionPane.showMessageDialog(this,"Phản hồi của bạn đã được gửi");
        switchPanel(PanelHome);
        }catch(Exception ex){
            System.out.println("SQL exception: "+ex.getMessage());
        }finally{
            //Giai phong
            if(pSm!= null){
                try{
                    pSm.close();
                }catch(SQLException sqlEx){}
                pSm=null;
            }
        }
        }
    }//GEN-LAST:event_btnPhanHoiMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomeUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeUser().setVisible(true);
            }
        });
    }

    private void setColor(JPanel pane){
        pane.setBackground(new Color(45,57,80));
    }
    
    private void resetColor(JPanel [] pane, JPanel [] indicators){
        for(int i=0; i<pane.length; i++){
            pane[i].setBackground(new Color(32,90,180));
        }
        for(int i=0; i<indicators.length; i++){
            indicators[i].setOpaque(false);
        }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Container;
    private javax.swing.JPanel PanelDatban;
    private javax.swing.JPanel PanelFormDB;
    private javax.swing.JPanel PanelHistory;
    private javax.swing.JPanel PanelHome;
    private javax.swing.JPanel PanelLienhe;
    private javax.swing.JPanel PanelProfile;
    private javax.swing.JButton btnChangePass;
    private javax.swing.JButton btnContact;
    private javax.swing.JButton btnDailyCheck;
    private javax.swing.JButton btnDat;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnOrder;
    private javax.swing.JButton btnPhanHoi;
    private javax.swing.JButton btnRefesh;
    private javax.swing.JButton btnShowpass;
    private javax.swing.JButton btnSo1;
    private javax.swing.JButton btnSo10;
    private javax.swing.JButton btnSo2;
    private javax.swing.JButton btnSo3;
    private javax.swing.JButton btnSo4;
    private javax.swing.JButton btnSo5;
    private javax.swing.JButton btnSo6;
    private javax.swing.JButton btnSo7;
    private javax.swing.JButton btnSo8;
    private javax.swing.JButton btnSo9;
    private javax.swing.JPanel btn_2;
    private javax.swing.JPanel btn_4;
    private javax.swing.JPanel btn_5;
    private javax.swing.JComboBox<String> cbxBanNguoi;
    private javax.swing.JComboBox<String> cbxTimeDat;
    private javax.swing.JPanel ind_2;
    private javax.swing.JPanel ind_4;
    private javax.swing.JPanel ind_5;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lbBack;
    private javax.swing.JLabel lbBack1;
    private javax.swing.JLabel lbContact;
    private javax.swing.JLabel lbDailyCheckin;
    private javax.swing.JLabel lbDay;
    public static javax.swing.JLabel lbNameuser;
    public static javax.swing.JLabel lbNameuser1;
    public static javax.swing.JLabel lbNameuser2;
    public static javax.swing.JLabel lbNameuser3;
    private javax.swing.JLabel lbNhanTichDiem;
    private javax.swing.JLabel lbOrder;
    public static javax.swing.JLabel lbPasswordprofile;
    private javax.swing.JLabel lbSo1;
    private javax.swing.JLabel lbSo10;
    private javax.swing.JLabel lbSo2;
    private javax.swing.JLabel lbSo3;
    private javax.swing.JLabel lbSo4;
    private javax.swing.JLabel lbSo5;
    private javax.swing.JLabel lbSo6;
    private javax.swing.JLabel lbSo7;
    private javax.swing.JLabel lbSo8;
    private javax.swing.JLabel lbSo9;
    private javax.swing.JLabel lbStaff;
    private javax.swing.JLabel lbStaff1;
    private javax.swing.JLabel lbStaff2;
    private javax.swing.JLabel lbThu;
    private javax.swing.JLabel lbThu2;
    private javax.swing.JLabel lbThu4;
    private javax.swing.JLabel lbThu5;
    private javax.swing.JLabel lbTichDiem;
    private javax.swing.JLabel lbTime;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbWelcome;
    private javax.swing.JLabel lbWelcome1;
    private javax.swing.JLabel lbWelcome2;
    private javax.swing.JLabel lbWelcome3;
    private javax.swing.JLabel lbWelcome4;
    private javax.swing.JLabel lbWelcome5;
    private javax.swing.JLabel lbWelcome6;
    private javax.swing.JLabel lbWelcome7;
    private javax.swing.JTextArea tAPhanHoi;
    private javax.swing.JTable tbHistory;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
}
