/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan_barang;
// import JTABLE model data didalam nya
import javax.swing.table.*;
// import untuk fungsi GUI FORM nya -swing
import javax.swing.*;
// import untuk printing
import java.awt.print.*;
// import untuk akses file TXT
import java.io.*;
// import untuk baca input data
import java.util.*;

/**
 *
 * @author ASUS
 */
public class form_databarang extends javax.swing.JFrame {

    void kosong() {
        txt_NomorBon.setText("");
        combo_NamaBarang.setSelectedItem("");
        txt_Harga.setText("");
        txt_Jumlah.setText("");
        txt_Hasil.setText("");
        txt_Total.setText("");

    }
        
    void simpan() {
        
        String file_nya = "d:\\databarang.txt";
        try(BufferedWriter dt = new BufferedWriter(new FileWriter(file_nya,true)))
        {
            //cek data yg sama,jika ada muncul pemeberitahuan
            File file_old = new File("d:\\databarang.txt");

            //file baru =WRITE, file lama=READER
            BufferedReader lama = new BufferedReader( new FileReader(file_old) );

            String isi_data; //var isi file
            String NomorBon; //var data nya
            
            // LOOPING UTAMA
            int nomer=0;
            while( ( isi_data = lama.readLine() ) != null )
            {
            
            
                StringTokenizer dm = new StringTokenizer(isi_data,",");
                NomorBon = dm.nextToken();
                if (  (NomorBon.equalsIgnoreCase(txt_NomorBon.getText()) ) ) {

                nomer=nomer+1;
                //munculkan data di jtable
                JOptionPane.showMessageDialog(null, "Sudah ada.");
                }
            }
        
            if(nomer == 0){
                if(txt_NomorBon.getText().trim().equals("")) 
                {
                    JOptionPane.showMessageDialog(null,"Nomor Bon  KOSONG SIMPAN BATAL");
                } else {

                    dt.write(txt_NomorBon.getText()  +","+combo_NamaBarang.getSelectedItem() +","+txt_Harga.getText() +","+txt_Jumlah.getText());
                    dt.flush(); //SAVE
                    dt.newLine(); //SIAPKAN BARIS BARU
                    JOptionPane.showMessageDialog(null, "SAVE");
                }
            }


            dt.close(); //TUTUP FILE

        //CODE ERROR
        }catch(Exception pesan) {
            JOptionPane.showMessageDialog(null,"ERROR = " + pesan.getMessage());
            }
        // LIHAT DATA
        this.lihat();
    }
    
void lihat() {
        try {
        //BACA ISI FILE= BufferedReader
        BufferedReader file_nya = new BufferedReader( new FileReader("d:\\databarang.txt") );
        String isi_data;
        String  NomerBon, NamaBarang,Harga,Jumlah;
        //------------------------------------------------------//
        // DIFINISI JTABLE
        //------------------------------------------------------//
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("NOMER");
        model.addColumn("NOMER BON");
        model.addColumn("NAMA BARANG");
        model.addColumn("HARGA");
        model.addColumn("JUMLAH");
        table_datatoko.setModel(model);
        //LOOP, SAMPAI DATA HABIS
        int nomer=0;
        
        int total = 0;
        
        while( ( isi_data = file_nya.readLine() ) != null )
        {
        nomer = nomer+1; //NOMER URUT
        //AMBIL DATA, PISAHKAN DG KOMA
        StringTokenizer dt = new StringTokenizer(isi_data,",");
         NomerBon= dt.nextToken(); 
         NamaBarang= dt.nextToken(); 
         Harga= dt.nextToken(); 
         Jumlah= dt.nextToken();
         
         int hargabarang = Integer.parseInt(Harga);
         int jumlahbarang = Integer.parseInt(Jumlah);
         total = total + (hargabarang * jumlahbarang);
         
         txt_Total.setText(Integer.toString(total));
        //------------------------------------------/
        // CETAK KE JTABLE
        //------------------------------------------/
        model.addRow(new Object[] { nomer, NomerBon, NamaBarang, Harga, Jumlah } );
        
        table_datatoko.setModel(model);
        }
        // DATA TIDAK ADA, BERI PESAN
        if (nomer == 0) {
        JOptionPane.showMessageDialog(null, "TIDAK ADA DATA");
        }
        file_nya.close(); //TUTUP FILE
        }
        catch (Exception ex){
        JOptionPane.showMessageDialog(null, "ADA ERROR = " + ex.getMessage());
        }
    }

    
    
    void print_data() {
    try {
        boolean print = table_datatoko.print();
        if (!print) {
        JOptionPane.showMessageDialog(null, "OK, PRINT..");
        }
        } catch (PrinterException ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void pilih_data(){
        int baris = table_datatoko.getSelectedRow();
        if(baris !=-1){
            

            txt_NomorBon.setText(table_datatoko.getValueAt(baris,1).toString());

            combo_NamaBarang.setSelectedItem(table_datatoko.getValueAt(baris,2).toString());
            txt_Harga.setText(table_datatoko.getValueAt(baris,3).toString());
            txt_Jumlah.setText(table_datatoko.getValueAt(baris,4).toString());
        }
    }
    
    
    
    public void hapus_data() {
        try {
        String hapus_dt; //var yg di hapus
        Scanner baca = new Scanner (System.in);
       
        if(txt_NomorBon.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Nomor Bon BELUM DI ISI..");
        return;
        }
        //siapkan file lama dan file baru nya
        File file_old = new File("d:\\databarang.txt");
        File file_new = new File("d:\\databarang_new.txt");
        //file baru =WRITE, file lama=READER
        BufferedReader lama = new BufferedReader( new FileReader(file_old) );
        BufferedWriter baru = new BufferedWriter(new FileWriter(file_new));  
        String isi_data; //var isi file
        String NomorBon, NamaBarang, Harga, Jumlah;
        
        
        // LOOPING UTAMA
        int nomer=0;
        while( ( isi_data = lama.readLine() ) != null )
        {
        //ambil data pisahkan dg KOMA
        NomorBon ="";
        NamaBarang = "";
        Harga = "";
        Jumlah = "";
        
        StringTokenizer dt = new StringTokenizer(isi_data,",");
        NomorBon = dt.nextToken(); 
        NamaBarang = dt.nextToken(); 
        Harga = dt.nextToken(); 
        Jumlah = dt.nextToken(); 
        //cek data yg mau dihapus,
        
        if ( ! (NomorBon.equalsIgnoreCase(txt_NomorBon.getText()) ) ) {
        //simpan ke file baru
        baru.write(NomorBon + "," + NamaBarang + "," + Harga + "," + Jumlah); 
        baru.flush(); //refresh
        baru.newLine(); //siapkan baris baru
        }
        }
        //tutup file
        lama.close();
        baru.close();
        //hapus file lama, rename file baru -> file lama
        file_old.delete();
        file_new.renameTo(file_old);
        //munculkan data di jtable
        JOptionPane.showMessageDialog(null, "DATA TELAH DI HAPUS..");
        this.lihat();
        }
        catch (Exception ex){
        JOptionPane.showMessageDialog(null, "ERROR = " + ex.getMessage());
        }
    }
    
    
    public void rubah_data() {
        try {

        //Menulis data String
        String rubah_NomorBon, rubah_NamaBarang, rubah_Harga, rubah_Jumlah ; //var UPDATE BARU
        //siapkan file lama dan file baru nya
        File file_old = new File("d:\\databarang.txt");
        File file_new = new File("d:\\databarang_new.txt");
        //file baru =WRITE, file lama=READER
        BufferedReader lama = new BufferedReader( new FileReader(file_old) );
        BufferedWriter baru = new BufferedWriter(new FileWriter(file_new));
        String isi_data; //var isi file
        String NomorBon, NamaBarang, Harga, Jumlah; //var data nya
        //LOOPING UTAMA
        int nomer=0;
        while( ( isi_data = lama.readLine() ) != null )
        {
        //ambil data pisahkan dg KOMA
        NomorBon= "";
        NamaBarang = "";
        Harga ="";
        Jumlah = "";
        
        StringTokenizer dt = new StringTokenizer(isi_data,",");
        NomorBon = dt.nextToken(); 
        NamaBarang= dt.nextToken(); 
        Harga = dt.nextToken(); 
        Jumlah = dt.nextToken();
        
        

        if (NomorBon.equalsIgnoreCase(txt_NomorBon.getText())) {
        //simpan data baru ke file baru
        baru.write(txt_NomorBon.getText()+","+combo_NamaBarang.getSelectedItem()+","+txt_Harga.getText()+","+txt_Jumlah.getText() );
        baru.flush();
        baru.newLine();
        } else {
        //simpan data lama ke file baru
        baru.write(NomorBon+","+ NamaBarang +","+ Harga + "," + Jumlah);
        baru.flush();
        baru.newLine();
        }
        }
        //tutup file
        lama.close();
        baru.close();
        //hapus file lama, rename file baru -> file lama
        file_old.delete();
        file_new.renameTo(file_old);
        //munculkan data di jtable
        JOptionPane.showMessageDialog(null, "DATA TELAH DI UPADTE..");
        this.lihat();
        }
        catch (Exception ex){
        JOptionPane.showMessageDialog(null, "ERROR = " + ex.getMessage());
        }
    }


    /**
     * Creates new form form_databarang
     */
    public form_databarang() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_NomorBon = new javax.swing.JTextField();
        combo_NamaBarang = new javax.swing.JComboBox<>();
        txt_Harga = new javax.swing.JTextField();
        txt_Jumlah = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_datatoko = new javax.swing.JTable();
        button_new = new javax.swing.JButton();
        button_save = new javax.swing.JButton();
        button_refresh = new javax.swing.JButton();
        button_update = new javax.swing.JButton();
        button_delete = new javax.swing.JButton();
        button_print = new javax.swing.JButton();
        button_exit = new javax.swing.JButton();
        button_hitungbayar = new javax.swing.JButton();
        Bayar = new javax.swing.JLabel();
        txt_Hasil = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_Total = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Nomer BON");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        jLabel3.setText("Nama Barang");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, 20));

        jLabel4.setText("Harga");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, -1, -1));

        jLabel5.setText("Jumlah");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, -1, -1));

        txt_NomorBon.setText("jTextField1");
        txt_NomorBon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NomorBonActionPerformed(evt);
            }
        });
        jPanel1.add(txt_NomorBon, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 205, -1));

        combo_NamaBarang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hardisk SSD", "Flasdisk", "Laptop ", "Mouse Wireles", "Keyboard", " ", " " }));
        jPanel1.add(combo_NamaBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 205, -1));

        txt_Harga.setText("jTextField2");
        jPanel1.add(txt_Harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 205, -1));

        txt_Jumlah.setText("jTextField3");
        jPanel1.add(txt_Jumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 205, -1));

        table_datatoko.setBackground(new java.awt.Color(204, 204, 204));
        table_datatoko.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        table_datatoko.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nomor ", "Nomer BON", "Nama Barang", "Harga", "Jumlah"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_datatoko.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_datatokoMouseClicked(evt);
            }
        });
        table_datatoko.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                table_datatokoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                table_datatokoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                table_datatokoKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(table_datatoko);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 450, 710, 270));

        button_new.setBackground(new java.awt.Color(119, 119, 175));
        button_new.setText("NEW");
        button_new.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_newActionPerformed(evt);
            }
        });
        jPanel1.add(button_new, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, -1, -1));

        button_save.setBackground(new java.awt.Color(119, 119, 175));
        button_save.setText("SAVE");
        button_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_saveActionPerformed(evt);
            }
        });
        jPanel1.add(button_save, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 410, -1, -1));

        button_refresh.setBackground(new java.awt.Color(119, 119, 175));
        button_refresh.setText("REFRESH");
        button_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_refreshActionPerformed(evt);
            }
        });
        jPanel1.add(button_refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 410, -1, -1));

        button_update.setBackground(new java.awt.Color(119, 119, 175));
        button_update.setText("UPDATE");
        button_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_updateActionPerformed(evt);
            }
        });
        jPanel1.add(button_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 410, -1, -1));

        button_delete.setBackground(new java.awt.Color(119, 119, 175));
        button_delete.setText("DELETE");
        button_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_deleteActionPerformed(evt);
            }
        });
        jPanel1.add(button_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 410, -1, -1));

        button_print.setBackground(new java.awt.Color(119, 119, 175));
        button_print.setText("PRINT");
        button_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_printActionPerformed(evt);
            }
        });
        jPanel1.add(button_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 410, -1, -1));

        button_exit.setBackground(new java.awt.Color(119, 119, 175));
        button_exit.setText("EXIT");
        button_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_exitActionPerformed(evt);
            }
        });
        jPanel1.add(button_exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 410, -1, -1));

        button_hitungbayar.setBackground(new java.awt.Color(119, 119, 175));
        button_hitungbayar.setText("HITUNG");
        button_hitungbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_hitungbayarActionPerformed(evt);
            }
        });
        jPanel1.add(button_hitungbayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 310, -1, -1));

        Bayar.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        Bayar.setText("Bayar");
        jPanel1.add(Bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, 37, 26));

        txt_Hasil.setText("jTextField4");
        txt_Hasil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_HasilActionPerformed(evt);
            }
        });
        jPanel1.add(txt_Hasil, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 340, 202, -1));

        jLabel7.setText("total");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 740, 46, 30));

        txt_Total.setForeground(new java.awt.Color(51, 51, 51));
        txt_Total.setText("total");
        txt_Total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TotalActionPerformed(evt);
            }
        });
        jPanel1.add(txt_Total, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 740, 170, 30));

        jPanel3.setBackground(new java.awt.Color(120, 120, 179));

        jLabel6.setBackground(new java.awt.Color(255, 204, 255));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("PENJUALAN BARANG");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(303, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(270, 270, 270))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 786, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button_hitungbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_hitungbayarActionPerformed
        // TODO add your handling code here:
        int getHarga = Integer.parseInt(txt_Harga.getText());
        int getJumlah = Integer.parseInt(txt_Jumlah.getText());
        int jumlah = getHarga * getJumlah;
        txt_Hasil.setText(Integer.toString(jumlah));
 
    }//GEN-LAST:event_button_hitungbayarActionPerformed

    private void button_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_newActionPerformed

        // TODO add your handling code here:
        this.kosong();
    }//GEN-LAST:event_button_newActionPerformed

    private void button_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_saveActionPerformed

        // TODO add your handling code here:
        this.simpan();
    }//GEN-LAST:event_button_saveActionPerformed

    private void button_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_refreshActionPerformed

        // TODO add your handling code here:
        this.lihat();
    }//GEN-LAST:event_button_refreshActionPerformed

    private void button_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_updateActionPerformed

        // TODO add your handling code here:
        this.rubah_data();
    }//GEN-LAST:event_button_updateActionPerformed

    private void button_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_deleteActionPerformed
        // TODO add your handling code here:
        this.hapus_data();
    }//GEN-LAST:event_button_deleteActionPerformed

    private void button_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_printActionPerformed
        // TODO add your handling code here:
        this.print_data();
    }//GEN-LAST:event_button_printActionPerformed

    private void button_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_exitActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_button_exitActionPerformed

    private void table_datatokoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_table_datatokoKeyPressed
        // TODO add your handling code here:
        this.pilih_data();
    }//GEN-LAST:event_table_datatokoKeyPressed

    private void table_datatokoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_table_datatokoKeyReleased
        // TODO add your handling code here:
        this.pilih_data();
    }//GEN-LAST:event_table_datatokoKeyReleased

    private void table_datatokoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_table_datatokoKeyTyped
        // TODO add your handling code here:
        this.pilih_data();
    }//GEN-LAST:event_table_datatokoKeyTyped

    private void table_datatokoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_datatokoMouseClicked
        // TODO add your handling code here:
        this.pilih_data();
    }//GEN-LAST:event_table_datatokoMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        this.kosong();
        this.lihat();
    }//GEN-LAST:event_formWindowOpened

    private void txt_TotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TotalActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txt_TotalActionPerformed

    private void txt_NomorBonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NomorBonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NomorBonActionPerformed

    private void txt_HasilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_HasilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_HasilActionPerformed

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
            java.util.logging.Logger.getLogger(form_databarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_databarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_databarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_databarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_databarang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Bayar;
    private javax.swing.JButton button_delete;
    private javax.swing.JButton button_exit;
    private javax.swing.JButton button_hitungbayar;
    private javax.swing.JButton button_new;
    private javax.swing.JButton button_print;
    private javax.swing.JButton button_refresh;
    private javax.swing.JButton button_save;
    private javax.swing.JButton button_update;
    private javax.swing.JComboBox<String> combo_NamaBarang;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_datatoko;
    private javax.swing.JTextField txt_Harga;
    private javax.swing.JTextField txt_Hasil;
    private javax.swing.JTextField txt_Jumlah;
    private javax.swing.JTextField txt_NomorBon;
    private javax.swing.JTextField txt_Total;
    // End of variables declaration//GEN-END:variables
}
