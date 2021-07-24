/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_akhir_dvd;

import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import static java.time.Instant.now;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicPanelUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hajir
 */
public class Tampilan extends javax.swing.JFrame {

    /**
     * Creates new form Tampilan
     */
    Calendar kal = new GregorianCalendar();
    int tahun = kal.get(Calendar.YEAR);
    int bulan = kal.get(Calendar.MONTH)+1;
    int hari = kal.get(Calendar.DAY_OF_MONTH);
    int harip = kal.get(Calendar.DAY_OF_MONTH);
    LocalDate today = LocalDate.now();     
    
    String tanggall = hari+"-"+bulan+"-"+tahun;
    String tanggallk = hari+"-"+bulan+"-"+tahun;
    String status1 = "Tersedia";
    String status2 = "Tersedia";
    String status3 = "Tersedia";
    String status4 = "Tersedia";
    String status5 = "Tersedia";
    String status6 = "Tersedia";
    String status7 = "Tersedia";
    String status8 = "Tersedia";
    String status9 = "Tersedia";
    Set<String> status = new HashSet<>();
    public static String user;

    public Statement st;
    public ResultSet rs;
    public static Connection con;
    public DefaultTableModel table;
    
    private void kosongkan_form(){
        tkode.setText(null);
        tkode.setEnabled(true);
        tnama.setText(null);
        talamat.setText(null);
        thp.setText(null);


        cbmem.setSelectedIndex(0);
        bsimpan.setText("Simpan");
        
        labelhapus.setEnabled(false);
    }
    private void cekStatus(){
        status1 = status.contains("Ashiapman") ? "Tersedia" : "Tidak Tersedia";
        status2 = status.contains("Joker") ? "Tersedia" : "Tidak Tersedia";
        status3 = status.contains("Bucin") ? "Tersedia" : "Tidak Tersedia";
        status4 = status.contains("WandaVision") ? "Tersedia" : "Tidak Tersedia";
        status5 = status.contains("Mulan") ? "Tersedia" : "Tidak Tersedia";
        status6 = status.contains("Soul") ? "Tersedia" : "Tidak Tersedia";
        status7 = status.contains("WonderWoman") ? "Tersedia" : "Tidak Tersedia";
        status8 = status.contains("Imperfect") ? "Tersedia" : "Tidak Tersedia";
        status9 = status.contains("El Camino") ? "Tersedia" : "Tidak Tersedia";
    }
    
    private void scaleImage(String path,JButton button){
        ImageIcon icon = new ImageIcon(path);
        Image img;
        img = icon.getImage();
        Image iconscaled = img.getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledicon = new ImageIcon(iconscaled);
        button.setIcon(scaledicon);
    }
    private void scaleImage2(String path,JLabel label){
        ImageIcon icon = new ImageIcon(path);
        Image img;
        img = icon.getImage();
        Image iconscaled = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledicon = new ImageIcon(iconscaled);
        label.setIcon(scaledicon);
    }
    
    private void tampilkan_data(){
        
        try{
            st = con.createStatement();
            rs = st.executeQuery("CALL lihat()");
            
            table = (DefaultTableModel)tabell.getModel();
            table.getDataVector().removeAllElements();
            table.fireTableDataChanged();
            
            while(rs.next()){
                Object[] data = {
                    rs.getString("kode"),
                    rs.getString("nama"),
                    rs.getString("alamat"),
                    rs.getString("nohp"),
                    rs.getString("member")

                };
                table.addRow(data);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void tampilkan_data2(){
        
        try{
            st = con.createStatement();
            rs = st.executeQuery("select * from tabelpinjam");
            
            table = (DefaultTableModel)tabelll.getModel();
            table.getDataVector().removeAllElements();
            table.fireTableDataChanged();
            
            while(rs.next()){
                Object[] data = {
                    rs.getString("kode"),
                    rs.getString("nama"),
                    rs.getString("judul"),
                    rs.getString("jangkawaktu")

                };

                table.addRow(data);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    private void isijudul(){
        cbjudul.removeAllItems();
        status.clear();
        try{
            st = con.createStatement();
            rs = st.executeQuery("select judul from film where status = 'Tersedia'");
            

            
            while(rs.next()){
                Object[] data = {
                    rs.getString("judul")
                    

                };
                cbjudul.addItem(rs.getString("judul").toString());

                
            }
            for (int i=0 ; i<=cbjudul.getItemCount();i++){
                
                status.add(cbjudul.getItemAt(i));
        }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
    }
    
    private void tampilkan_data3(){
        
        try{
            st = con.createStatement();
            rs = st.executeQuery("select * from peminjam");
            
            table = (DefaultTableModel)tabellll.getModel();
            table.getDataVector().removeAllElements();
            table.fireTableDataChanged();
            
            while(rs.next()){
                Object[] data = {
                    rs.getString("kode"),
                    rs.getString("nama")
                    

                };
                table.addRow(data);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void tampilkan_data4(){
        cekStatus();
        
        try{
            st = con.createStatement();
            rs = st.executeQuery("select * from film");
            
            table = (DefaultTableModel)tabelfilm.getModel();
            table.getDataVector().removeAllElements();
            table.fireTableDataChanged();
            
            while(rs.next()){
                Object[] data = {
                    rs.getString("Judul"),
                    rs.getString("Tahun"),
                    rs.getString("Durasi"),
                    rs.getString("Pemeran"),
                    rs.getString("Sutradara"),
                    rs.getString("Status")
                    

                };
                table.addRow(data);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    private void tampilkan_data5(){
        
        try{
            st = con.createStatement();
            rs = st.executeQuery("select * from stok_dvd");
            
            table = (DefaultTableModel)tabelstok.getModel();
            table.getDataVector().removeAllElements();
            table.fireTableDataChanged();
            
            while(rs.next()){
                Object[] data = {
                    rs.getString("jumlah_tersedia"),
                    rs.getString("jumlah_tidak_tersedia")
                    

                };
                table.addRow(data);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    CardLayout card;
    public void hilangFitur(String user){
        if (user.equals("manager")){
            
        }else{
            labelhapus3.setVisible(false);
            labelhapus2.setVisible(false);
            hari1.setEnabled(false);
            hari2.setEnabled(false);
            hari3.setEnabled(false);
            hari4.setEnabled(false);
            hari5.setEnabled(false);
            hari6.setEnabled(false);
        }
    }
    public Tampilan(String user) {
        initComponents();
        tampilkan_data();
        tampilkan_data2();
        tampilkan_data3();
        tampilkan_data4();
        tanggal.setText(tanggall);
        isijudul();
        scaleImage2("/Users/hajir/NetBeansProjects/project_akhir_dvd/src/gambar/dvdicon.png", dvdicon);
        scaleImage2("/Users/hajir/NetBeansProjects/project_akhir_dvd/src/gambar/membericon.png", iconname);
        scaleImage2("/Users/hajir/NetBeansProjects/project_akhir_dvd/src/gambar/dvdfav.png", favorite);
        scaleImage2("/Users/hajir/NetBeansProjects/project_akhir_dvd/src/gambar/4hari.png", label4hari2);
        scaleImage2("/Users/hajir/NetBeansProjects/project_akhir_dvd/src/gambar/1hari.png", labelhari1);
        scaleImage2("/Users/hajir/NetBeansProjects/project_akhir_dvd/src/gambar/2hari.png", labelhari2);
        scaleImage2("/Users/hajir/NetBeansProjects/project_akhir_dvd/src/gambar/3hari.png", labelhari3);
        scaleImage2("/Users/hajir/NetBeansProjects/project_akhir_dvd/src/gambar/5hari.png", labelhari5);
        scaleImage2("/Users/hajir/NetBeansProjects/project_akhir_dvd/src/gambar/6hari.png", labelhari6);
        hilangFitur(user);
        





        kosongkan_form();
        
        
        card = (CardLayout)(cardPan.getLayout());
    }
    
    public void setColor(JPanel panel, JPanel panel2){
        panel.setBackground(new Color(60,63,65));
        panel2.setOpaque(true);
    }
    
    public void resetColor(JPanel panel, JPanel panel2){
        panel.setBackground(new Color(0,102,102));
        panel2.setOpaque(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        memberBtn = new javax.swing.JPanel();
        ind1 = new javax.swing.JPanel();
        memberbtn = new javax.swing.JLabel();
        listBtn = new javax.swing.JPanel();
        ind2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        peminjamanBtn = new javax.swing.JPanel();
        ind3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        dvdicon = new javax.swing.JLabel();
        cardPan = new javax.swing.JPanel();
        memPan = new javax.swing.JPanel();
        lkode = new javax.swing.JLabel();
        lnama = new javax.swing.JLabel();
        lalamat = new javax.swing.JLabel();
        lhp = new javax.swing.JLabel();
        lmem = new javax.swing.JLabel();
        tnama = new javax.swing.JTextField();
        tkode = new javax.swing.JTextField();
        talamat = new javax.swing.JTextField();
        thp = new javax.swing.JTextField();
        bsimpan = new javax.swing.JButton();
        breset = new javax.swing.JButton();
        cbmem = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabell = new javax.swing.JTable();
        ganti = new javax.swing.JButton();
        tsutradara = new javax.swing.JTextField();
        iconname = new javax.swing.JLabel();
        labelhapus = new javax.swing.JLabel();
        listPan = new javax.swing.JPanel();
        favorite = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelfilm = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        film1 = new javax.swing.JButton();
        film2 = new javax.swing.JButton();
        film3 = new javax.swing.JButton();
        film4 = new javax.swing.JButton();
        film5 = new javax.swing.JButton();
        film6 = new javax.swing.JButton();
        film7 = new javax.swing.JButton();
        film8 = new javax.swing.JButton();
        film9 = new javax.swing.JButton();
        labelhapus2 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabelstok = new javax.swing.JTable();
        pinjamPan = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelll = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabellll = new javax.swing.JTable();
        cbjudul = new javax.swing.JComboBox<>();
        cbhari = new javax.swing.JComboBox<>();
        rental = new javax.swing.JButton();
        tanggal = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        hari1 = new javax.swing.JFormattedTextField();
        hari2 = new javax.swing.JFormattedTextField();
        hari3 = new javax.swing.JFormattedTextField();
        hari4 = new javax.swing.JFormattedTextField();
        hari5 = new javax.swing.JFormattedTextField();
        hari6 = new javax.swing.JFormattedTextField();
        jLabel22 = new javax.swing.JLabel();
        labelhapus3 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        labelhari6 = new javax.swing.JLabel();
        labelhari3 = new javax.swing.JLabel();
        labelhari5 = new javax.swing.JLabel();
        label4hari2 = new javax.swing.JLabel();
        labelhari1 = new javax.swing.JLabel();
        labelhari2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane1.setDividerSize(1);
        jSplitPane1.setEnabled(false);

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        memberBtn.setBackground(new java.awt.Color(0, 102, 102));
        memberBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                memberBtnMousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                memberBtnMouseClicked(evt);
            }
        });
        memberBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ind1.setBackground(new java.awt.Color(255, 255, 255));
        ind1.setOpaque(false);
        ind1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        memberBtn.add(ind1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 8, 34));

        memberbtn.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        memberbtn.setForeground(new java.awt.Color(255, 255, 255));
        memberbtn.setText("Member");
        memberBtn.add(memberbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 6, -1, -1));

        jPanel3.add(memberBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 129, -1));

        listBtn.setBackground(new java.awt.Color(0, 102, 102));
        listBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                listBtnMousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listBtnMouseClicked(evt);
            }
        });

        ind2.setBackground(new java.awt.Color(255, 255, 255));
        ind2.setOpaque(false);
        ind2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("List DVD");

        javax.swing.GroupLayout listBtnLayout = new javax.swing.GroupLayout(listBtn);
        listBtn.setLayout(listBtnLayout);
        listBtnLayout.setHorizontalGroup(
            listBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listBtnLayout.createSequentialGroup()
                .addComponent(ind2, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(19, 19, 19))
        );
        listBtnLayout.setVerticalGroup(
            listBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ind2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(listBtnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(listBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 129, -1));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/RENTAL.png"))); // NOI18N
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, -1));

        peminjamanBtn.setBackground(new java.awt.Color(0, 102, 102));
        peminjamanBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                peminjamanBtnMousePressed(evt);
            }
        });

        ind3.setBackground(new java.awt.Color(255, 255, 255));
        ind3.setOpaque(false);
        ind3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setBackground(new java.awt.Color(0, 102, 102));
        jLabel6.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Pinjam");

        javax.swing.GroupLayout peminjamanBtnLayout = new javax.swing.GroupLayout(peminjamanBtn);
        peminjamanBtn.setLayout(peminjamanBtnLayout);
        peminjamanBtnLayout.setHorizontalGroup(
            peminjamanBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(peminjamanBtnLayout.createSequentialGroup()
                .addComponent(ind3, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        peminjamanBtnLayout.setVerticalGroup(
            peminjamanBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ind3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(peminjamanBtnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(peminjamanBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 129, -1));
        jPanel3.add(dvdicon, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 504, 117, 144));

        jSplitPane1.setLeftComponent(jPanel3);

        cardPan.setLayout(new java.awt.CardLayout());

        memPan.setBackground(new java.awt.Color(255, 255, 102));
        memPan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lkode.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lkode.setForeground(new java.awt.Color(0, 0, 0));
        lkode.setText("KODE PENYEWA");
        memPan.add(lkode, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 56, -1, -1));

        lnama.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lnama.setForeground(new java.awt.Color(0, 0, 0));
        lnama.setText("NAMA PENYEWA");
        memPan.add(lnama, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 113, -1, -1));

        lalamat.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lalamat.setForeground(new java.awt.Color(0, 0, 0));
        lalamat.setText("ALAMAT PENYEWA");
        memPan.add(lalamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 168, -1, -1));

        lhp.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lhp.setForeground(new java.awt.Color(0, 0, 0));
        lhp.setText("NOMOR HP");
        memPan.add(lhp, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 223, -1, -1));

        lmem.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lmem.setForeground(new java.awt.Color(0, 0, 0));
        lmem.setText("MEMBER");
        memPan.add(lmem, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 278, -1, -1));
        memPan.add(tnama, new org.netbeans.lib.awtextra.AbsoluteConstraints(226, 114, 291, -1));

        tkode.setForeground(new java.awt.Color(0, 0, 0));
        memPan.add(tkode, new org.netbeans.lib.awtextra.AbsoluteConstraints(226, 57, 291, -1));
        memPan.add(talamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(226, 169, 291, -1));
        memPan.add(thp, new org.netbeans.lib.awtextra.AbsoluteConstraints(226, 224, 289, -1));

        bsimpan.setText("Simpan");
        bsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsimpanActionPerformed(evt);
            }
        });
        memPan.add(bsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 322, 200, -1));

        breset.setText("Kosongkan");
        breset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bresetActionPerformed(evt);
            }
        });
        memPan.add(breset, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 322, 200, -1));

        cbmem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Silver", "Gold" }));
        memPan.add(cbmem, new org.netbeans.lib.awtextra.AbsoluteConstraints(226, 278, 92, -1));

        tabell.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "KODE", "NAMA", "ALAMAT", "NOMOR HP", "MEMBER"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabell.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabellMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabell);

        memPan.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 372, 571, 209));

        ganti.setText("Member");
        ganti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gantiActionPerformed(evt);
            }
        });
        memPan.add(ganti, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 12, -1, -1));

        tsutradara.setEnabled(false);
        memPan.add(tsutradara, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 280, 180, -1));
        memPan.add(iconname, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 60, 40));

        labelhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/hapus.png"))); // NOI18N
        labelhapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelhapusMouseClicked(evt);
            }
        });
        memPan.add(labelhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 600, -1, -1));

        cardPan.add(memPan, "card1");

        listPan.setBackground(new java.awt.Color(255, 255, 255));
        listPan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        favorite.setFont(new java.awt.Font("Lucida Sans Typewriter", 2, 36)); // NOI18N
        listPan.add(favorite, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 470, 60));

        tabelfilm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Judul", "Tahun", "Durasi", "Pemeran", "Strudara", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tabelfilm);

        listPan.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 571, 130));

        jPanel4.setLayout(new java.awt.GridLayout(3, 3));

        film1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        film1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                film1ActionPerformed(evt);
            }
        });
        jPanel4.add(film1);

        film2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                film2ActionPerformed(evt);
            }
        });
        jPanel4.add(film2);

        film3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                film3ActionPerformed(evt);
            }
        });
        jPanel4.add(film3);

        film4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                film4ActionPerformed(evt);
            }
        });
        jPanel4.add(film4);

        film5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                film5ActionPerformed(evt);
            }
        });
        jPanel4.add(film5);

        film6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                film6ActionPerformed(evt);
            }
        });
        jPanel4.add(film6);

        film7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                film7ActionPerformed(evt);
            }
        });
        jPanel4.add(film7);

        film8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                film8ActionPerformed(evt);
            }
        });
        jPanel4.add(film8);

        film9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                film9ActionPerformed(evt);
            }
        });
        jPanel4.add(film9);

        listPan.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 530, 390));

        labelhapus2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/hapus.png"))); // NOI18N
        labelhapus2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelhapus2MouseClicked(evt);
            }
        });
        listPan.add(labelhapus2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 620, -1, -1));

        tabelstok.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Jumlah DVD Tersedia", "Jumlah DVD Tidak Tersedia"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tabelstok);
        if (tabelstok.getColumnModel().getColumnCount() > 0) {
            tabelstok.getColumnModel().getColumn(0).setResizable(false);
            tabelstok.getColumnModel().getColumn(1).setResizable(false);
        }

        listPan.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 620, 370, 40));

        cardPan.add(listPan, "card2");

        pinjamPan.setBackground(new java.awt.Color(153, 153, 255));
        pinjamPan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabelll.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Kode", "Nama", "Judul", "Pengembalian"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabelll);

        pinjamPan.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 101, 594, 105));

        jLabel13.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/biaya.png"))); // NOI18N
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        pinjamPan.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 498, -1, -1));

        jLabel14.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/listpinjam.png"))); // NOI18N
        pinjamPan.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, -1, -1));

        tabellll.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "kode", "nama"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tabellll);

        pinjamPan.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 328, 189, 164));

        cbjudul.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        pinjamPan.add(cbjudul, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 350, 213, 52));

        cbhari.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        cbhari.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6" }));
        pinjamPan.add(cbhari, new org.netbeans.lib.awtextra.AbsoluteConstraints(221, 440, 213, -1));

        rental.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        rental.setText("RENTAL");
        rental.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rentalActionPerformed(evt);
            }
        });
        pinjamPan.add(rental, new org.netbeans.lib.awtextra.AbsoluteConstraints(452, 328, 113, 352));

        tanggal.setFont(new java.awt.Font("Lucida Sans Typewriter", 1, 24)); // NOI18N
        tanggal.setText("tanggal");
        pinjamPan.add(tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 177, 28));

        jLabel15.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/peminjam.png"))); // NOI18N
        pinjamPan.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, 48));

        hari1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("IDR #,##0.00;(IDR#,##0.00)"))));
        hari1.setText("IDR 10,000.00");
        hari1.setToolTipText("");
        pinjamPan.add(hari1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 550, 109, 32));

        hari2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("IDR #,##0.00;(IDR#,##0.00)"))));
        hari2.setText("IDR 15,000.00");
        pinjamPan.add(hari2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 600, 109, 32));

        hari3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("IDR #,##0.00;(IDR#,##0.00)"))));
        hari3.setText("IDR 19,000.00");
        pinjamPan.add(hari3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 650, 109, 32));

        hari4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("IDR #,##0.00;(IDR#,##0.00)"))));
        hari4.setText("IDR 23,000.00");
        pinjamPan.add(hari4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 550, 109, 32));

        hari5.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("IDR #,##0.00;(IDR#,##0.00)"))));
        hari5.setText("IDR 25,000.00");
        pinjamPan.add(hari5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 600, 109, 32));

        hari6.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("IDR #,##0.00;(IDR#,##0.00)"))));
        hari6.setText("IDR 27,000.00");
        hari6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hari6ActionPerformed(evt);
            }
        });
        pinjamPan.add(hari6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 650, 109, 32));

        jLabel22.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/judul.png"))); // NOI18N
        pinjamPan.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 320, 130, 36));

        labelhapus3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/hapus.png"))); // NOI18N
        labelhapus3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelhapus3MouseClicked(evt);
            }
        });
        pinjamPan.add(labelhapus3, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 224, -1, -1));

        jLabel23.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/hari.png"))); // NOI18N
        pinjamPan.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 410, 111, 36));

        labelhari6.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        pinjamPan.add(labelhari6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 650, 100, 30));

        labelhari3.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        pinjamPan.add(labelhari3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 650, 100, 30));

        labelhari5.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        pinjamPan.add(labelhari5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 600, 100, 30));

        label4hari2.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        pinjamPan.add(label4hari2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 550, 100, 30));

        labelhari1.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        pinjamPan.add(labelhari1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 550, 100, 30));

        labelhari2.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        pinjamPan.add(labelhari2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, 100, 30));

        cardPan.add(pinjamPan, "card3");

        jSplitPane1.setRightComponent(cardPan);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 705, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void memberBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_memberBtnMousePressed
        // TODO add your handling code here:
        setColor(memberBtn,ind1);
        resetColor(listBtn,ind2);
        resetColor(peminjamanBtn,ind3);
        card.show(cardPan, "card1");
        
    }//GEN-LAST:event_memberBtnMousePressed

    private void listBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listBtnMousePressed
        // TODO add your handling code here:
        setColor(listBtn,ind2);
        resetColor(memberBtn,ind1);
        resetColor(peminjamanBtn,ind3);
        card.show(cardPan, "card2");
                                isijudul();

        System.out.println(status);
        System.out.println(status1);
                tampilkan_data();
        tampilkan_data2();
        tampilkan_data3();
        tampilkan_data5();


        tampilkan_data4();
                cekStatus();
                
        scaleImage("/Users/hajir/NetBeansProjects/project_akhir_dvd/src/gambar/ashiapman.png", film1);
        scaleImage("/Users/hajir/NetBeansProjects/project_akhir_dvd/src/gambar/joker.png", film2);
        scaleImage("/Users/hajir/NetBeansProjects/project_akhir_dvd/src/gambar/bucin.png", film3);
        scaleImage("/Users/hajir/NetBeansProjects/project_akhir_dvd/src/gambar/wandavision.png", film4);
        scaleImage("/Users/hajir/NetBeansProjects/project_akhir_dvd/src/gambar/mulan.png", film5);
        scaleImage("/Users/hajir/NetBeansProjects/project_akhir_dvd/src/gambar/soul.png", film6);
        scaleImage("/Users/hajir/NetBeansProjects/project_akhir_dvd/src/gambar/wonderwoman.png", film7);
        scaleImage("/Users/hajir/NetBeansProjects/project_akhir_dvd/src/gambar/imperfect.png", film8);
        scaleImage("/Users/hajir/NetBeansProjects/project_akhir_dvd/src/gambar/elcamino.jpeg", film9);

    }//GEN-LAST:event_listBtnMousePressed

    private void peminjamanBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_peminjamanBtnMousePressed
        // TODO add your handling code here:
        setColor(peminjamanBtn,ind3);
        resetColor(memberBtn,ind1);
        resetColor(listBtn,ind2);
        card.show(cardPan, "card3");
        tampilkan_data3();
        tampilkan_data2();
        isijudul();
        


    }//GEN-LAST:event_peminjamanBtnMousePressed

    private void memberBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_memberBtnMouseClicked
        // TODO add your handling code here:
                card.show(cardPan, "pinjamPan");
  
    }//GEN-LAST:event_memberBtnMouseClicked

    private void listBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listBtnMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_listBtnMouseClicked

    private void rentalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rentalActionPerformed
        // TODO add your handling code here:
        try{
            st = con.createStatement();
            st.executeUpdate("insert into tabelpinjam values('"
            + tabellll.getValueAt(tabellll.getSelectedRow(), 0)+"','"
            + tabellll.getValueAt(tabellll.getSelectedRow(), 1)+"','"
            + cbjudul.getSelectedItem().toString()+"','"
            + today.plusDays(cbhari.getSelectedIndex()+1).toString()+"')");
            
            
            try{
                st = con.createStatement();
                st.executeUpdate("update film set "
                        + "Status = '"+"Tidak Tersedia"+"'"
                        + "where Judul = '"+cbjudul.getSelectedItem().toString()+"'"
                );
                tampilkan_data4();
                kosongkan_form();
                }catch(Exception e){
                    e.printStackTrace();
                }
            tampilkan_data2();

            String biaya = "tes";
            if (cbhari.getSelectedIndex() == 0){
                biaya = hari1.getText();
            }else if (cbhari.getSelectedIndex() == 1){
                biaya = hari2.getText();
            }else if (cbhari.getSelectedIndex() == 2){
                biaya = hari3.getText();
            }else if (cbhari.getSelectedIndex() == 3){
                biaya = hari4.getText();
            }else if (cbhari.getSelectedIndex() == 4){
                biaya = hari5.getText();
            }else if (cbhari.getSelectedIndex() == 5){
                biaya = hari6.getText();
            }
            
            if (JOptionPane.showConfirmDialog(rootPane, "--------NOTA RENTAL--------\nKode : "
                            + tabellll.getValueAt(tabellll.getSelectedRow(), 0)
                            +"\nNama : "+ tabellll.getValueAt(tabellll.getSelectedRow(), 1)
                            +"\nJudul : "+ cbjudul.getSelectedItem().toString()
                            +"\nTanggal Peminjaman : " + today.toString()
                            +"\nTanggal Pengembalian : "+ today.plusDays(cbhari.getSelectedIndex() + 1)
                            +"\nBiaya : " + biaya
                            +"\n\n\nMOHON DIKEMBALIKAN TEPAT WAKTU!\nTerima Kasih ~ RENTAL DVD UTM" +"\nData Pinjam Telah Tersimpan\nCetak nota?", "Sukses",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                try {
                    FileWriter myWriter = new FileWriter(tabellll.getValueAt(tabellll.getSelectedRow(), 1)+".txt");
                    myWriter.write("--------NOTA RENTAL--------\nKode : "
                            + tabellll.getValueAt(tabellll.getSelectedRow(), 0)
                            +"\nNama : "+ tabellll.getValueAt(tabellll.getSelectedRow(), 1)
                            +"\nJudul : "+ cbjudul.getSelectedItem().toString()
                            +"\nTanggal Peminjaman : " + today.toString()
                            +"\nTanggal Pengembalian : "+ today.plusDays(cbhari.getSelectedIndex() + 1)
                            +"\nBiaya : " + biaya
                            +"\n\n\nMOHON DIKEMBALIKAN TEPAT WAKTU!\nTerima Kasih ~ RENTAL DVD UTM");
                    myWriter.close();
                }catch (IOException e){
                    e.printStackTrace();
                    
                }
            }
            isijudul();

            
        }catch(Exception e){
            e.printStackTrace();
            }

        
        
    }//GEN-LAST:event_rentalActionPerformed

    private void hari6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hari6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hari6ActionPerformed

    private void film8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_film8ActionPerformed
        // TODO add your handling code here:
        ImageIcon icon = new ImageIcon("/Users/hajir/NetBeansProjects/project_akhir_dvd/src/gambar/imperfect.png");
        JOptionPane.showMessageDialog(rootPane, "Judul : Imperfect\nTahun : 2020\nDurasi : 117 Menit\nPemeran : Jessica Mila\nSutradara : Ernest Prakasa\n\n\n\nStatus DVD : "+status8 , "INFORMASI FILM", JOptionPane.INFORMATION_MESSAGE,icon);
    }//GEN-LAST:event_film8ActionPerformed

    private void film3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_film3ActionPerformed
        // TODO add your handling code here:
        ImageIcon icon = new ImageIcon("/Users/hajir/NetBeansProjects/project_akhir_dvd/src/gambar/bucin.png");
        JOptionPane.showMessageDialog(rootPane, "Judul : Bucin\nTahun : 2020\nDurasi : 94 Menit\nPemeran : Andovi Da Lopez\nSutradara : Chandra Liow\n\n\n\nStatus DVD : "+status3 , "INFORMASI FILM", JOptionPane.INFORMATION_MESSAGE,icon);
    }//GEN-LAST:event_film3ActionPerformed

    private void film5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_film5ActionPerformed
        // TODO add your handling code here:
        ImageIcon icon = new ImageIcon("/Users/hajir/NetBeansProjects/project_akhir_dvd/src/gambar/mulan.png");
        JOptionPane.showMessageDialog(rootPane, "Judul : Mulan\nTahun : 2020\nDurasi : 105 Menit\nPemeran : Jet Li\nSutradara : Niki Caro\n\n\n\nStatus DVD : "+status5 , "INFORMASI FILM", JOptionPane.INFORMATION_MESSAGE,icon);
    }//GEN-LAST:event_film5ActionPerformed

    private void film9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_film9ActionPerformed
        // TODO add your handling code here:
        ImageIcon icon = new ImageIcon("/Users/hajir/NetBeansProjects/project_akhir_dvd/src/gambar/elcamino.jpeg");
        System.out.println(status);
        JOptionPane.showMessageDialog(rootPane, "Judul : El Camino\nTahun : 2019\nDurasi : 122 Menit\nPemeran : Aaron Paul\nSutradara : Vince Gilligan\n\n\n\nStatus DVD : "+status9 , "INFORMASI FILM", JOptionPane.INFORMATION_MESSAGE,icon);
    }//GEN-LAST:event_film9ActionPerformed

    private void film4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_film4ActionPerformed
        // TODO add your handling code here:
        ImageIcon icon = new ImageIcon("/Users/hajir/NetBeansProjects/project_akhir_dvd/src/gambar/wandavision.png");
        JOptionPane.showMessageDialog(rootPane, "Judul : WandaVision\nTahun : 2020\nDurasi : 50 Menit/Eps\nPemeran : Elizabeth Olsen\nSutradara : Matt Shakman\n\n\n\nStatus DVD : "+status4 , "INFORMASI FILM", JOptionPane.INFORMATION_MESSAGE,icon);
    }//GEN-LAST:event_film4ActionPerformed

    private void film7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_film7ActionPerformed
        // TODO add your handling code here:
        ImageIcon icon = new ImageIcon("/Users/hajir/NetBeansProjects/project_akhir_dvd/src/gambar/wonderwoman.png");
        JOptionPane.showMessageDialog(rootPane, "Judul : Wonder Woman 1984\nTahun : 2020\nDurasi : 150 Menit\nPemeran : Gal Gadot\nSutradara : Patty Jenkins\n\n\n\nStatus DVD : "+status7 , "INFORMASI FILM", JOptionPane.INFORMATION_MESSAGE,icon);
    }//GEN-LAST:event_film7ActionPerformed

    private void film2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_film2ActionPerformed
        // TODO add your handling code here:
        ImageIcon icon = new ImageIcon("/Users/hajir/NetBeansProjects/project_akhir_dvd/src/gambar/joker.png");
        JOptionPane.showMessageDialog(rootPane, "Judul : Joker\nTahun : 2019\nDurasi : 115 Menit\nPemeran : Joaquin Phoenix\nSutradara : Todd Phillips\n\n\n\nStatus DVD : "+status2 , "INFORMASI FILM", JOptionPane.INFORMATION_MESSAGE,icon);
    }//GEN-LAST:event_film2ActionPerformed

    private void film6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_film6ActionPerformed
        // TODO add your handling code here:
        ImageIcon icon = new ImageIcon("/Users/hajir/NetBeansProjects/project_akhir_dvd/src/gambar/soul.png");
        JOptionPane.showMessageDialog(rootPane, "Judul : Soul\nTahun : 2020\nDurasi : 106 Menit\nPemeran : Jamie Foxx\nSutradara : Pete Docter\n\n\n\nStatus DVD : "+status6 , "INFORMASI FILM", JOptionPane.INFORMATION_MESSAGE,icon);
    }//GEN-LAST:event_film6ActionPerformed

    private void film1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_film1ActionPerformed
        // TODO add your handling code here:
        ImageIcon icon = new ImageIcon("/Users/hajir/NetBeansProjects/project_akhir_dvd/src/gambar/ashiapman.png");
        JOptionPane.showMessageDialog(rootPane, "Judul : Ashiapman\nTahun : 2020\nDurasi : 90 Menit\nPemeran : Atta Halilintar\nSutradara : Atta Halilintar\n\n\n\nStatus DVD : "+status1 , "INFORMASI FILM", JOptionPane.INFORMATION_MESSAGE,icon);
    }//GEN-LAST:event_film1ActionPerformed

    private void gantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gantiActionPerformed
        // TODO add your handling code here:
        if (ganti.getText() == "Member"){
            ganti.setText("Film");
                        memberbtn.setText("Film");

            bsimpan.setText("Simpan");
            lkode.setText("JUDUL FILM");
            lnama.setText("TAHUN FILM");
            lalamat.setText("DURASI FILM");
            lhp.setText("PEMAIN FILM");
            lmem.setText("SUTRADARA");
            cbmem.setEnabled(false);
            tsutradara.setEnabled(true);
            tabell.setEnabled(false);
            scaleImage2("/Users/hajir/NetBeansProjects/project_akhir_dvd/src/gambar/dvdicoon.png", iconname);

        }else{
            ganti.setText("Member");
            memberbtn.setText("Member");

            lkode.setText("KODE PENYEWA");
            lnama.setText("NAMA PENYEWA");
            lalamat.setText("ALAMAT PENYEWA");
            lhp.setText("NOMOR HP");
            lmem.setText("MEMBER");
            cbmem.setEnabled(true);
            tsutradara.setText("");
            tsutradara.setEnabled(false);
            tabell.setEnabled(true);
            scaleImage2("/Users/hajir/NetBeansProjects/project_akhir_dvd/src/gambar/membericon.png", iconname);
        }

    }//GEN-LAST:event_gantiActionPerformed

    private void tabellMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabellMouseClicked
        // TODO add your handling code here:
        tkode.setText(tabell.getValueAt(tabell.getSelectedRow(), 0).toString());
        tkode.setEnabled(true);
        tnama.setText(tabell.getValueAt(tabell.getSelectedRow(), 1).toString());
        talamat.setText(tabell.getValueAt(tabell.getSelectedRow(), 2).toString());
        thp.setText(tabell.getValueAt(tabell.getSelectedRow(), 3).toString());

        if(tabell.getValueAt(tabell.getSelectedRow(), 4).toString().equals("Silver")){
            cbmem.setSelectedIndex(0);
        }else if(tabell.getValueAt(tabell.getSelectedRow(), 4).toString().equals("Gold")){
            cbmem.setSelectedIndex(1);
        }else{
            cbmem.setSelectedIndex(2);
        }

        bsimpan.setText("Ubah");
        bsimpan.setEnabled(true);
        labelhapus.setEnabled(true);
    }//GEN-LAST:event_tabellMouseClicked

    private void bresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bresetActionPerformed
        // TODO add your handling code here:
        kosongkan_form();
        tabell.clearSelection();
    }//GEN-LAST:event_bresetActionPerformed

    private void bsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpanActionPerformed
        // TODO add your handling code here:
        if(ganti.getText().equals("Member")){
            if(bsimpan.getText().equals("Simpan")){
                try{
                    
                    st = con.createStatement();
                    st.executeUpdate("CALL tambahmember('"
                        + tkode.getText()+"','"
                        + tnama.getText()+"','"
                        + talamat.getText()+"','"
                        + thp.getText()+"','"
                        + cbmem.getSelectedItem().toString()+"')");
                    tampilkan_data();
                    JOptionPane.showMessageDialog(rootPane, "Data telah ditambahkan", "Success",JOptionPane.INFORMATION_MESSAGE);
                    kosongkan_form();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }else{
                try{
                    st = con.createStatement();
                    st.executeUpdate("CALL updatemember('"
                        + tkode.getText()+"','"
                        + tnama.getText()+"','"
                        + talamat.getText()+"','"
                        + thp.getText()+"','"
                        + cbmem.getSelectedItem().toString()+"')"
                    );
                    tampilkan_data();
                    JOptionPane.showMessageDialog(rootPane, "Data telah diubah", "Success",JOptionPane.INFORMATION_MESSAGE);
                    kosongkan_form();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }else{
            if(ganti.getText() == "Film"){
                try{
                    
                    st = con.createStatement();
                    st.executeUpdate("insert into film values('"
                        + tkode.getText()+"','"
                        + tnama.getText()+"','"
                        + talamat.getText()+"','"
                        + thp.getText()+"','"
                        + tsutradara.getText()+"','"
                        + "Tersedia"+"')");
                    tampilkan_data();
                    JOptionPane.showMessageDialog(rootPane, "Data telah ditambahkan", "Success",JOptionPane.INFORMATION_MESSAGE);
                    kosongkan_form();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
            
    }//GEN-LAST:event_bsimpanActionPerformed

    private void labelhapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelhapusMouseClicked
        // TODO add your handling code here:
        try{
            st = con.createStatement();
            
            st.executeUpdate("delete from member where kode = '"+tkode.getText()+"'"
            );
            tampilkan_data();
            JOptionPane.showMessageDialog(rootPane, "Data telah dihapus", "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
            kosongkan_form();

        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_labelhapusMouseClicked

    private void labelhapus2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelhapus2MouseClicked
        // TODO add your handling code here:
        if (tabelfilm.getValueAt(tabelfilm.getSelectedRow(), 5).toString().equals("Tersedia")){
            try{
            st = con.createStatement();
            st.executeUpdate("delete from film where judul = '"+tabelfilm.getValueAt(tabelfilm.getSelectedRow(), 0).toString()+"'"
            );
            tampilkan_data4();
            JOptionPane.showMessageDialog(rootPane, "Data telah dihapus", "Success", JOptionPane.INFORMATION_MESSAGE);
            kosongkan_form();
     
            

        }catch(Exception e){
            e.printStackTrace();
        }
            
        }else{
            try{
                if (JOptionPane.showConfirmDialog(rootPane, "DVD MASIH TERPINJAM, TETAP INGIN MENGHAPUS DATA DVD ?", "Peringatan",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                    st = con.createStatement();
                    st.executeUpdate("delete from film where judul = '"+tabelfilm.getValueAt(tabelfilm.getSelectedRow(), 0).toString()+"'"
                    );
                    tampilkan_data4();
                    JOptionPane.showMessageDialog(rootPane, "Data telah dihapus", "Success", JOptionPane.INFORMATION_MESSAGE);
                    kosongkan_form();
                }
                
     
            

        }catch(Exception e){
            e.printStackTrace();
        }
        }
    }//GEN-LAST:event_labelhapus2MouseClicked

    private void labelhapus3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelhapus3MouseClicked
        // TODO add your handling code here:
        try{
            st = con.createStatement();
            st.executeUpdate("update film set status = 'Tersedia' where judul = '"+tabelll.getValueAt(tabelll.getSelectedRow(), 2).toString()+ "'");
            st.executeUpdate("delete from tabelpinjam where judul = '"+tabelll.getValueAt(tabelll.getSelectedRow(), 2).toString()+"'"
            );
            isijudul();
            tampilkan_data2();
            JOptionPane.showMessageDialog(rootPane, "Data telah diahpus", "Success", JOptionPane.INFORMATION_MESSAGE);
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_labelhapus3MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel13MouseClicked

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tampilan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tampilan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tampilan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tampilan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String pass = JOptionPane.showInputDialog("Masuk Sebagai =");
                if (pass.equals("manager")){
                    
                    con = KoneksiDB.Koneksi(pass);
                    new Tampilan("manager").setVisible(true);

                }else{
                    con = KoneksiDB.Koneksi("penyewa");
                    new Tampilan("penyewa").setVisible(true);

                }

                
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton breset;
    private javax.swing.JButton bsimpan;
    private javax.swing.JPanel cardPan;
    private javax.swing.JComboBox<String> cbhari;
    private javax.swing.JComboBox<String> cbjudul;
    private javax.swing.JComboBox<String> cbmem;
    private javax.swing.JLabel dvdicon;
    private javax.swing.JLabel favorite;
    private javax.swing.JButton film1;
    private javax.swing.JButton film2;
    private javax.swing.JButton film3;
    private javax.swing.JButton film4;
    private javax.swing.JButton film5;
    private javax.swing.JButton film6;
    private javax.swing.JButton film7;
    private javax.swing.JButton film8;
    private javax.swing.JButton film9;
    private javax.swing.JButton ganti;
    private javax.swing.JFormattedTextField hari1;
    private javax.swing.JFormattedTextField hari2;
    private javax.swing.JFormattedTextField hari3;
    private javax.swing.JFormattedTextField hari4;
    private javax.swing.JFormattedTextField hari5;
    private javax.swing.JFormattedTextField hari6;
    private javax.swing.JLabel iconname;
    private javax.swing.JPanel ind1;
    private javax.swing.JPanel ind2;
    private javax.swing.JPanel ind3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel label4hari2;
    private javax.swing.JLabel labelhapus;
    private javax.swing.JLabel labelhapus2;
    private javax.swing.JLabel labelhapus3;
    private javax.swing.JLabel labelhari1;
    private javax.swing.JLabel labelhari2;
    private javax.swing.JLabel labelhari3;
    private javax.swing.JLabel labelhari5;
    private javax.swing.JLabel labelhari6;
    private javax.swing.JLabel lalamat;
    private javax.swing.JLabel lhp;
    private javax.swing.JPanel listBtn;
    private javax.swing.JPanel listPan;
    private javax.swing.JLabel lkode;
    private javax.swing.JLabel lmem;
    private javax.swing.JLabel lnama;
    private javax.swing.JPanel memPan;
    private javax.swing.JPanel memberBtn;
    private javax.swing.JLabel memberbtn;
    private javax.swing.JPanel peminjamanBtn;
    private javax.swing.JPanel pinjamPan;
    private javax.swing.JButton rental;
    private javax.swing.JTable tabelfilm;
    private javax.swing.JTable tabell;
    private javax.swing.JTable tabelll;
    private javax.swing.JTable tabellll;
    private javax.swing.JTable tabelstok;
    private javax.swing.JTextField talamat;
    private javax.swing.JLabel tanggal;
    private javax.swing.JTextField thp;
    private javax.swing.JTextField tkode;
    private javax.swing.JTextField tnama;
    private javax.swing.JTextField tsutradara;
    // End of variables declaration//GEN-END:variables
}
