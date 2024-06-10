/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package edutrack;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admins
 */
public class giaovien extends javax.swing.JFrame {

    /**
     * Creates new form giaovien
     */
    mysql connect = new mysql();
    String user_id;
    String class_cur_id;
    thong_tin_ca_nhan a;
    public giaovien() {
        initComponents();
        user_id = "gv001";
        this.setLocationRelativeTo(null);
    }
    public giaovien(String user_id_log)
    {
        initComponents();
        user_id = user_id_log;
        this.setLocationRelativeTo(null);
        init_class();
    }
    private void init_class()
    {
        btnOKdd.setVisible(false);
        DefaultTableModel DefaultTable = new DefaultTableModel();
        String tieuDe[] = {"Mã Lớp", "Tên Lớp", "Phòng", "trạng thái lớp", "số lượng hiện tại", "ngày tạo"};
        DefaultTable.setColumnIdentifiers(tieuDe);
        String sql = "SELECT lop.ClassID,lop.ClassName,lop.CreateDate,lop.RoomNumber,lop.ClassState,chitietlophoc.so_luong_hoc_vien"
                + " from lop join chitietlophoc ON lop.ClassID = chitietlophoc.id_lop_hoc WHERE lop.AssignedTeacherID = ? and classState ='Active'";
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            PreparedStatement pst = (PreparedStatement) connect.conn.prepareStatement(sql);
            pst.setString(1, user_id);
            ResultSet rs = pst.executeQuery();
            String row[] = new String[6];
            while(rs.next())
            {
                row[0] = rs.getString("lop.classid");
                row[1] = rs.getString("classname");
                row[2] = rs.getString("roomnumber");
                row[3] = rs.getString("classstate");
                row[4] = rs.getString("so_luong_hoc_vien");
                row[5] = format.format(rs.getDate("createdate"));
                DefaultTable.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Lỗi querry hàm init class");
        }
        table.setModel(DefaultTable);
    }
    private void init_ctlh()
    {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1 && class_cur_id == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 lớp", "lỗi", JOptionPane.ERROR_MESSAGE);            
            return;
        } 
        else if(selectedRow != -1 && class_cur_id == null)
        {
            class_cur_id = table.getValueAt(table.getSelectedRow(), 0).toString();
        }
        DefaultTableModel DefaultTable = new DefaultTableModel();
        String tieuDe[] = {"Mã Lớp", "Tên Lớp", "CourseID", "Tên course", "Nội dung"};
        DefaultTable.setColumnIdentifiers(tieuDe);
        String sql = "SELECT ClassID,ClassName,chitietlophoc.CourseID,Coursename,Noidung FROM lop JOIN chitietlophoc ON "
                    + "lop.ClassID = chitietlophoc.id_lop_hoc JOIN course ON chitietlophoc.CourseID = course.CourseID WHERE lop.ClassID = ?";
        try 
        {
            PreparedStatement pst = (PreparedStatement) connect.conn.prepareStatement(sql);
            pst.setString(1, class_cur_id);
            ResultSet rs = pst.executeQuery();
            String row[] = new String[5];
            while(rs.next())
            {
                row[0] = rs.getString("classid");
                row[1] = rs.getString("classname");
                row[2] = rs.getString("chitietlophoc.CourseID");
                row[3] = rs.getString("Coursename");
                row[4] = rs.getString("Noidung");
                DefaultTable.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Lỗi querry hàm init ctlh");
        }
        table.setModel(DefaultTable);
    }
    private void init_tkb()
    {
        DefaultTableModel DefaultTable = new DefaultTableModel();
        String tieuDe[] = {"Thứ", "Bắt đầu", "Kết thúc", "Lớp"};
        DefaultTable.setColumnIdentifiers(tieuDe);
        String sql = "SELECT thoikhoabieu.Thu,thoikhoabieu.TimeStart,thoikhoabieu.TimeEnd,lop.classid FROM thoikhoabieu JOIN lop ON thoikhoabieu.ClassID = lop.ClassID"
                + " WHERE lop.AssignedTeacherID = ?";
        try {
            PreparedStatement pst = (PreparedStatement) connect.conn.prepareStatement(sql);
            pst.setString(1, user_id);
            ResultSet rs = pst.executeQuery();
            String row[] = new String[4];
            while(rs.next())
            {
                row[0] = rs.getString("Thu");
                row[1] = rs.getString("Timestart");
                row[2] = rs.getString("Timeend");
                row[3] = rs.getString("classid");
                DefaultTable.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Lỗi querry hàm init class");
        }
        table.setModel(DefaultTable);
    }
    private void init_diemdanh()
    {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1 && class_cur_id == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 lớp", "lỗi", JOptionPane.ERROR_MESSAGE);            
            return;
        } 
        else if(selectedRow != -1 && class_cur_id == null)
        {
            class_cur_id = table.getValueAt(table.getSelectedRow(), 0).toString();
        }
        DefaultTableModel DefaultTable = new DefaultTableModel()
        {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if(columnIndex == 2)
                    return Boolean.class;
                else
                    return super.getColumnClass(columnIndex);
            }
        };
        String tieuDe[] = {"Mã học sinh", "Tên Học sinh", "Vắng","Lí do"};
        DefaultTable.setColumnIdentifiers(tieuDe);
        String sql = "SELECT hocsinh.UserID, hocsinh.FirstName, hocsinh.LastName,bangdiemdanh.LyDo FROM hocsinh JOIN xeplop ON hocsinh.UserID = xeplop.UserID"
                    + " LEFT JOIN bangdiemdanh ON hocsinh.UserID = bangdiemdanh.StudentID AND bangdiemdanh.ClassID = xeplop.ClassID"
                + " AND bangdiemdanh.Ngay = CURDATE() WHERE xeplop.ClassID = ?";
        try 
        {
            PreparedStatement pst = (PreparedStatement) connect.conn.prepareStatement(sql);
            pst.setString(1, class_cur_id);
            ResultSet rs = pst.executeQuery();
            Object row[] = new Object[4];
            while(rs.next())
            {
                row[0] = rs.getString("hocsinh.UserID");
                row[1] = rs.getString("lastname")+" "+rs.getString("firstname");
                row[3] = rs.getString("LyDo");
                if(row[3]==null)
                    row[2] = Boolean.FALSE;
                else
                    row[2] = Boolean.TRUE;
                DefaultTable.addRow(row);
            }
        } catch (Exception e) 
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Lỗi querry hàm init điểm danh");
        }
        table.setModel(DefaultTable);   
    }
    private void update_diemdanh()
    {
        String sql_ins ="INSERT INTO bangdiemdanh(ClassID, StudentID, Ngay) VALUES (?,?,curdate())";
        String sql_del ="DELETE FROM bangdiemdanh WHERE ClassID =? AND StudentID = ? AND Ngay = curdate()";
        String sql_upd ="UPDATE bangdiemdanh SET LyDo=? WHERE ClassID=? AND StudentID=? AND Ngay = curdate()";
        try 
        {
            for(int i = 0; i<table.getRowCount();i++)
            {
                Boolean isChecked = (Boolean) table.getValueAt(i, 2);
                if (isChecked)
                {
                    PreparedStatement pst = (PreparedStatement) connect.conn.prepareStatement(sql_ins);
                    PreparedStatement ps = (PreparedStatement) connect.conn.prepareStatement(sql_upd);
                    pst.setString(1, class_cur_id);
                    pst.setString(2, table.getValueAt(i, 0).toString());
                    if(table.getValueAt(i, 3)==null)
                    {
                        ps.setString(1, "không có");
                    }
                    else{
                        ps.setString(1, table.getValueAt(i, 3).toString());
                    }
                    ps.setString(2, class_cur_id);
                    ps.setString(3, table.getValueAt(i, 0).toString());
                    pst.executeUpdate();
                    ps.executeUpdate();
                }
                else
                {
                    try 
                    {
                        PreparedStatement pst = (PreparedStatement) connect.conn.prepareStatement(sql_del);
                        pst.setString(1, class_cur_id);
                        pst.setString(2, table.getValueAt(i, 0).toString());
                        pst.executeUpdate();
                    } catch (SQLException e) 
                    {
                        e.printStackTrace();
                    }
                }
            }
            JOptionPane.showMessageDialog(this,"Điểm danh thành công");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Lỗi điểm danh");
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

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnOKdd = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnLop = new javax.swing.JButton();
        btnDiemdanh = new javax.swing.JButton();
        btnCanhan = new javax.swing.JButton();
        btnctlh = new javax.swing.JButton();
        btntkb = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 204, 255));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(table);

        jPanel1.setBackground(new java.awt.Color(102, 204, 255));

        btnOKdd.setText("Điểm danh");
        btnOKdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(428, 428, 428)
                .addComponent(btnOKdd)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnOKdd)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(102, 204, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(75, 140));

        btnLop.setText("Lớp");
        btnLop.setMaximumSize(new java.awt.Dimension(75, 25));
        btnLop.setMinimumSize(new java.awt.Dimension(75, 25));
        btnLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLopActionPerformed(evt);
            }
        });

        btnDiemdanh.setText("Điểm danh");
        btnDiemdanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiemdanhActionPerformed(evt);
            }
        });

        btnCanhan.setText("Trang thông tin cá nhân");
        btnCanhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCanhanActionPerformed(evt);
            }
        });

        btnctlh.setText("Chi tiết");
        btnctlh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnctlhActionPerformed(evt);
            }
        });

        btntkb.setText("Thời khóa biểu");
        btntkb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntkbActionPerformed(evt);
            }
        });

        jButton8.setText("Đăng xuất");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDiemdanh, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addComponent(btnCanhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnctlh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btntkb, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLop, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(btnctlh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDiemdanh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btntkb, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCanhan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLopActionPerformed
        class_cur_id = null;
        init_class();
    }//GEN-LAST:event_btnLopActionPerformed

    private void btnctlhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnctlhActionPerformed
        btnOKdd.setVisible(false);
        init_ctlh();
    }//GEN-LAST:event_btnctlhActionPerformed

    private void btnDiemdanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiemdanhActionPerformed
        btnOKdd.setVisible(true);
        init_diemdanh();
    }//GEN-LAST:event_btnDiemdanhActionPerformed

    private void btntkbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntkbActionPerformed
        btnOKdd.setVisible(false);
        init_tkb();
    }//GEN-LAST:event_btntkbActionPerformed

    private void btnCanhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCanhanActionPerformed
        btnOKdd.setVisible(false);
        a = new thong_tin_ca_nhan(user_id, "giaovien");
        a.setVisible(true);
    }//GEN-LAST:event_btnCanhanActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        dangnhap b = new dangnhap();
        b.setVisible(true);
        if(a != null)
            a.dispose();
        this.dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void btnOKddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKddActionPerformed
        update_diemdanh();
        init_diemdanh();
    }//GEN-LAST:event_btnOKddActionPerformed

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
            java.util.logging.Logger.getLogger(giaovien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(giaovien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(giaovien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(giaovien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new giaovien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCanhan;
    private javax.swing.JButton btnDiemdanh;
    private javax.swing.JButton btnLop;
    private javax.swing.JButton btnOKdd;
    private javax.swing.JButton btnctlh;
    private javax.swing.JButton btntkb;
    private javax.swing.JButton jButton8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
