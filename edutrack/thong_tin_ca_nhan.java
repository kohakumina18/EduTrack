/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package edutrack;

import com.mysql.jdbc.PreparedStatement;
import java.awt.Dialog;
import java.sql.*;
import java.awt.event.WindowAdapter;
import java.security.interfaces.RSAKey;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admins
 */
public class thong_tin_ca_nhan extends javax.swing.JFrame {

    /**
     * Creates new form thong_tin_ca_nhan_hv
     */
    String user_id;
    mysql connect = new mysql();
    DefaultTableModel defaultTable;
    String form;
    public thong_tin_ca_nhan() {
        initComponents();
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }
    public thong_tin_ca_nhan(String user_id_log, String form) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.form = form;
        user_id = user_id_log;
        update_info();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }
    private void update_info()
    {
        String sql = "select * from " + form +" WHERE UserID = ?";
        ResultSet rs;
        try {
            PreparedStatement pst = (PreparedStatement) connect.conn.prepareStatement(sql);
            pst.setString(1, user_id);
            rs = pst.executeQuery();
            if(rs.next())
            {
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                txtID.setText(user_id);
                txtTen.setText(rs.getString("LastName")+" "+ rs.getString("FirstName"));
                txtEmail.setText(rs.getString("Email"));
                txtPhone.setText(rs.getString("Phone"));
                txtDate.setText(format.format(rs.getDate("DateOfBirth")));
                txtGender.setText(rs.getString("Gender"));
            }
            else
                JOptionPane.showMessageDialog(this,"Không có dữ liệu");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Lỗi querry update info");
        }
    }
    private void update_info_dialog()
    {
        String sql = "select FirstName, LastName from " + form + " WHERE UserID = ?";
        ResultSet rs;
        txtEmail1.setText(txtEmail.getText());
        txtPhone1.setText(txtPhone.getText());
        txtDate1.setText(txtDate.getText());
        txtGender1.setText(txtGender.getText());
        try {
            PreparedStatement pst = (PreparedStatement) connect.conn.prepareStatement(sql);
            pst.setString(1, user_id);
            rs = pst.executeQuery();
            if(rs.next())
            {
                txtHo1.setText(rs.getString("LastName"));
                txtTen1.setText(rs.getString("FirstName"));
            }
            else
                JOptionPane.showMessageDialog(this,"Không có dữ liệu");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Lỗi querry update info");
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dia_cap_nhat = new javax.swing.JDialog();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnCap_nhat1 = new javax.swing.JButton();
        txtHo1 = new javax.swing.JTextField();
        txtEmail1 = new javax.swing.JTextField();
        txtPhone1 = new javax.swing.JTextField();
        txtDate1 = new javax.swing.JTextField();
        txtGender1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtTen1 = new javax.swing.JTextField();
        dia_doi_mk = new javax.swing.JDialog();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btnCap_nhat2 = new javax.swing.JButton();
        txt_old = new javax.swing.JTextField();
        txt_again = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txt_new = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtID = new javax.swing.JLabel();
        txtTen = new javax.swing.JLabel();
        txtEmail = new javax.swing.JLabel();
        txtPhone = new javax.swing.JLabel();
        txtDate = new javax.swing.JLabel();
        txtGender = new javax.swing.JLabel();
        btnCap_nhat = new javax.swing.JButton();
        btn_doimk = new javax.swing.JButton();

        dia_cap_nhat.setLocation(new java.awt.Point(0, 0));
        dia_cap_nhat.setSize(new java.awt.Dimension(380, 370));

        jLabel7.setText("Email");

        jLabel8.setText("SDT");

        jLabel9.setText("Ngày sinh");

        jLabel10.setText("Giới tính");

        jLabel12.setText("Họ");

        btnCap_nhat1.setText("Cập nhật");
        btnCap_nhat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCap_nhat1ActionPerformed(evt);
            }
        });

        jLabel11.setText("Tên");

        javax.swing.GroupLayout dia_cap_nhatLayout = new javax.swing.GroupLayout(dia_cap_nhat.getContentPane());
        dia_cap_nhat.getContentPane().setLayout(dia_cap_nhatLayout);
        dia_cap_nhatLayout.setHorizontalGroup(
            dia_cap_nhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dia_cap_nhatLayout.createSequentialGroup()
                .addGap(0, 45, Short.MAX_VALUE)
                .addGroup(dia_cap_nhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dia_cap_nhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dia_cap_nhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dia_cap_nhatLayout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65))
                            .addGroup(dia_cap_nhatLayout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69)))
                        .addGroup(dia_cap_nhatLayout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addGap(72, 72, 72)))
                    .addGroup(dia_cap_nhatLayout.createSequentialGroup()
                        .addGroup(dia_cap_nhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(65, 65, 65)))
                .addGroup(dia_cap_nhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dia_cap_nhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(dia_cap_nhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dia_cap_nhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtHo1)
                                .addComponent(txtDate1)
                                .addComponent(txtTen1, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
                            .addComponent(txtPhone1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtEmail1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtGender1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(dia_cap_nhatLayout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(btnCap_nhat1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dia_cap_nhatLayout.setVerticalGroup(
            dia_cap_nhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dia_cap_nhatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dia_cap_nhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHo1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(dia_cap_nhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(dia_cap_nhatLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(txtTen1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dia_cap_nhatLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(dia_cap_nhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dia_cap_nhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dia_cap_nhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dia_cap_nhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGender1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(btnCap_nhat1)
                .addContainerGap())
        );

        dia_doi_mk.setLocation(new java.awt.Point(0, 0));
        dia_doi_mk.setSize(new java.awt.Dimension(380, 370));

        jLabel13.setText("Nhập lại mật khẩu mới");

        jLabel17.setText("Mật khẩu cũ");

        btnCap_nhat2.setText("Cập nhật");
        btnCap_nhat2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCap_nhat2ActionPerformed(evt);
            }
        });

        jLabel18.setText("Mật khẩu mới");

        javax.swing.GroupLayout dia_doi_mkLayout = new javax.swing.GroupLayout(dia_doi_mk.getContentPane());
        dia_doi_mk.getContentPane().setLayout(dia_doi_mkLayout);
        dia_doi_mkLayout.setHorizontalGroup(
            dia_doi_mkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dia_doi_mkLayout.createSequentialGroup()
                .addGap(0, 21, Short.MAX_VALUE)
                .addGroup(dia_doi_mkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addGroup(dia_doi_mkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dia_doi_mkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txt_old)
                        .addComponent(txt_new, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
                    .addComponent(txt_again, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(dia_doi_mkLayout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(btnCap_nhat2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dia_doi_mkLayout.setVerticalGroup(
            dia_doi_mkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dia_doi_mkLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dia_doi_mkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_old, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(dia_doi_mkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(dia_doi_mkLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(txt_new, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dia_doi_mkLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(dia_doi_mkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_again, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(btnCap_nhat2)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("thông tin cá nhân");
        setBackground(new java.awt.Color(255, 255, 255));
        setLocation(new java.awt.Point(0, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("ID");

        jLabel1.setText("Họ và tên");

        jLabel3.setText("Email");

        jLabel4.setText("SDT");

        jLabel5.setText("Ngày sinh");

        jLabel6.setText("Giới tính");

        txtID.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtTen.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtEmail.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtPhone.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtDate.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtGender.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnCap_nhat.setText("Cập nhật thông tin cá nhân");
        btnCap_nhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCap_nhatActionPerformed(evt);
            }
        });

        btn_doimk.setText("Đổi mật khẩu");
        btn_doimk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_doimkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(284, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(65, 65, 65)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(btn_doimk, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCap_nhat, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCap_nhat)
                    .addComponent(btn_doimk))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCap_nhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCap_nhatActionPerformed
        dia_cap_nhat.setLocationRelativeTo(null);
        update_info_dialog();
        dia_cap_nhat.setVisible(true);
    }//GEN-LAST:event_btnCap_nhatActionPerformed

    private void btnCap_nhat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCap_nhat1ActionPerformed
        String sql = "UPDATE hocsinh SET FirstName= ?,LastName=?,Email=?,Phone=?,DateOfBirth=?,Gender=? WHERE UserID = ? ";
        SimpleDateFormat sourceFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            PreparedStatement pst = (PreparedStatement) connect.conn.prepareStatement(sql);
            pst.setString(1, txtTen1.getText());
            pst.setString(2, txtHo1.getText());
            pst.setString(3, txtEmail1.getText());
            pst.setString(4, txtPhone1.getText());
            try {
                java.util.Date a =  sourceFormat.parse(txtDate1.getText());
                String formattedDate = targetFormat.format(a);
                pst.setDate(5, Date.valueOf(formattedDate));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,"Không chuyển dc");
            }
            pst.setString(6, txtGender1.getText());
            pst.setString(7, user_id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Cập nhật thành công");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Lỗi querry update info");
        }
    }//GEN-LAST:event_btnCap_nhat1ActionPerformed

    private void btn_doimkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_doimkActionPerformed
        dia_doi_mk.setLocationRelativeTo(null);
        dia_doi_mk.setVisible(true);
    }//GEN-LAST:event_btn_doimkActionPerformed

    private void btnCap_nhat2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCap_nhat2ActionPerformed
        String oldPassword = txt_old.getText();
        String newPassword = txt_new.getText();
        String againPassword = txt_again.getText();
    
        if (!newPassword.equals(againPassword)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu mới và xác nhận mật khẩu không khớp.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sqlCheck = "SELECT Pass FROM taikhoan WHERE UserID = ?";
        String sqlUpdate = "UPDATE taikhoan SET Pass = ? WHERE UserID = ?";

        try {
            PreparedStatement pstCheck = (PreparedStatement) connect.conn.prepareStatement(sqlCheck);
            pstCheck.setString(1, user_id);
            ResultSet rs = pstCheck.executeQuery();

            if (rs.next()) {
                String currentPassword = rs.getString("Pass");
                if (!currentPassword.equals(oldPassword)) {
                    JOptionPane.showMessageDialog(this, "Mật khẩu cũ không đúng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy người dùng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (newPassword.equals(oldPassword)) {
                JOptionPane.showMessageDialog(this, "Mật khẩu mới không được giống mật khẩu cũ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            PreparedStatement pstUpdate = (PreparedStatement) connect.conn.prepareStatement(sqlUpdate);
            pstUpdate.setString(1, newPassword); 
            pstUpdate.setString(2, user_id);
            pstUpdate.executeUpdate();
            
            JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công.");
            txt_old.setText("");
            txt_new.setText("");
            txt_again.setText("");
            dia_doi_mk.dispose();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi truy vấn cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    
    }//GEN-LAST:event_btnCap_nhat2ActionPerformed

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
            java.util.logging.Logger.getLogger(thong_tin_ca_nhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(thong_tin_ca_nhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(thong_tin_ca_nhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(thong_tin_ca_nhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new thong_tin_ca_nhan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCap_nhat;
    private javax.swing.JButton btnCap_nhat1;
    private javax.swing.JButton btnCap_nhat2;
    private javax.swing.JButton btn_doimk;
    private javax.swing.JDialog dia_cap_nhat;
    private javax.swing.JDialog dia_doi_mk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel txtDate;
    private javax.swing.JTextField txtDate1;
    private javax.swing.JLabel txtEmail;
    private javax.swing.JTextField txtEmail1;
    private javax.swing.JLabel txtGender;
    private javax.swing.JTextField txtGender1;
    private javax.swing.JTextField txtHo1;
    private javax.swing.JLabel txtID;
    private javax.swing.JLabel txtPhone;
    private javax.swing.JTextField txtPhone1;
    private javax.swing.JLabel txtTen;
    private javax.swing.JTextField txtTen1;
    private javax.swing.JTextField txt_again;
    private javax.swing.JTextField txt_new;
    private javax.swing.JTextField txt_old;
    // End of variables declaration//GEN-END:variables
}