/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package edutrack;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author chauanhkhoi
 */
public class quantrivien extends javax.swing.JFrame {

    /**
     * Creates new form ADMIN_EDUTRACK
     */
    mysql connect = new mysql();
    String user_id;
    Boolean them;
    thong_tin_ca_nhan a;
    public quantrivien() {
        initComponents();
        init_hocsinh();
    }
    public quantrivien(String user_idString_log) {
        user_id = user_idString_log;
        initComponents();
        init_hocsinh();
        btnvohieu.setVisible(false);
        btnkichhoat.setVisible(false);
    }
    private void init_hocsinh()
    {
        DefaultTableModel DefaultTable = new DefaultTableModel()
        {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if(columnIndex == 0)
                    return Boolean.class;
                else
                    return super.getColumnClass(columnIndex);
            }
            
        };
        String tieuDe[] = {"","Mã học sinh", "Tên học sinh", "Email", "Số điện thoại", "Ngày sinh", "Giới tính"};
        DefaultTable.setColumnIdentifiers(tieuDe);
        String sql = "select * from hocsinh";
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Statement st = (Statement) connect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            Object row[] = new Object[7];
            while(rs.next())
            {
                row[0] = Boolean.FALSE;
                row[1] = rs.getString("userid");
                row[2] = rs.getString("lastname") + " "+rs.getString("firstname");
                row[3] = rs.getString("email");
                row[4] = rs.getString("phone");
                row[5] = format.format(rs.getDate("dateofbirth"));
                row[6] = rs.getString("Gender");
                DefaultTable.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Lỗi querry hàm init học sinh");
        }
        table.setModel(DefaultTable);
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(0).setWidth(10);
    }
    private void init_giaovien()
    {
        DefaultTableModel DefaultTable = new DefaultTableModel()
        {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if(columnIndex == 0)
                    return Boolean.class;
                else
                    return super.getColumnClass(columnIndex);
            }
            
        };
        String tieuDe[] = {"","Mã giáo viên", "Tên giáo viên", "Email", "Số điện thoại", "Ngày sinh", "Giới tính"};
        DefaultTable.setColumnIdentifiers(tieuDe);
        String sql = "select * from giaovien";
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Statement st = (Statement) connect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            Object row[] = new Object[7];
            while(rs.next())
            {
                row[0] = Boolean.FALSE;
                row[1] = rs.getString("userid");
                row[2] = rs.getString("lastname") + " "+rs.getString("firstname");
                row[3] = rs.getString("email");
                row[4] = rs.getString("phone");
                row[5] = format.format(rs.getDate("dateofbirth"));
                row[6] = rs.getString("Gender");
                DefaultTable.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Lỗi querry hàm init giáo viên");
        }
        table.setModel(DefaultTable);
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(0).setWidth(10);
    }
    private void init_nhanvien()
    {
        DefaultTableModel DefaultTable = new DefaultTableModel()
        {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if(columnIndex == 0)
                    return Boolean.class;
                else
                    return super.getColumnClass(columnIndex);
            }
            
        };
        String tieuDe[] = {"","Mã nhân viên", "Tên nhân viên", "Email", "Số điện thoại", "Ngày sinh", "Giới tính"};
        DefaultTable.setColumnIdentifiers(tieuDe);
        String sql = "select * from nhanvienhocvu";
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Statement st = (Statement) connect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            Object row[] = new Object[7];
            while(rs.next())
            {
                row[0] = Boolean.FALSE;
                row[1] = rs.getString("userid");
                row[2] = rs.getString("lastname") + " "+rs.getString("firstname");
                row[3] = rs.getString("email");
                row[4] = rs.getString("phone");
                row[5] = format.format(rs.getDate("dateofbirth"));
                row[6] = rs.getString("Gender");
                DefaultTable.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Lỗi querry hàm init nhân viên");
        }
        table.setModel(DefaultTable);
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(0).setWidth(10);
    }
    private void init_lophoc()
    {
        DefaultTableModel DefaultTable = new DefaultTableModel();
        String tieuDe[] = {"Lớp", "Phòng", "Giáo viên phụ trách","Course", "số lượng hiện tại", "trạng thái lớp", "ngày tạo"};
        DefaultTable.setColumnIdentifiers(tieuDe);
        String sql = "SELECT ClassID, CreateDate, RoomNumber,AssignedTeacherID, ClassState, so_luong_hoc_vien,CourseID FROM lop"
                + " left outer join chitietlophoc on chitietlophoc.id_lop_hoc = lop.ClassID";
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Statement st = (Statement) connect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String row[] = new String[7];
            while(rs.next())
            {
                row[0] = rs.getString("classid");
                row[1] = rs.getString("roomnumber");
                row[2] = rs.getString("AssignedTeacherID");
                row[3] = rs.getString("CourseID");
                row[4] = rs.getString("so_luong_hoc_vien");
                row[5] = rs.getString("classstate");
                row[6] = format.format(rs.getDate("createdate"));
                DefaultTable.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Lỗi querry hàm init lớp");
        }
        table.setModel(DefaultTable);
    }
    private void reset_upd_dialog(String select)
    {
        if(them)
        {
            txtID.setEditable(true);
            txtID.setText("");
            txtTen.setText("");
            txtHo.setText("");
            txtEmail.setText("");
            txtSdt.setText("");
            txtDate.setText("");
            cb_gender.setSelectedIndex(0);
            txtTk.setText("");
            txtMk.setText("");
            pn_tk_mk.setVisible(true);
        }
        else
        {
            txtID.setEditable(false);
            pn_tk_mk.setVisible(false);
            String sql = "select FirstName, LastName from "+select+ " WHERE UserID = ?";
            ResultSet rs;
            try {
                PreparedStatement pst = (PreparedStatement) connect.conn.prepareStatement(sql);
                pst.setString(1, table.getValueAt(table.getSelectedRow(), 1).toString());
                rs = pst.executeQuery();
                if(rs.next())
                {
                    txtID.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
                    txtTen.setText(rs.getString("Firstname"));
                    txtHo.setText(rs.getString("LastName"));
                    txtEmail.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
                    txtSdt.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
                    txtDate.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
                    cb_gender.setSelectedItem(table.getValueAt(table.getSelectedRow(), 6).toString());
                }
                else
                    JOptionPane.showMessageDialog(this,"Không có dữ liệu");
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this,"Lỗi querry reset_upd_dialog");
            }
        }
    }
    private void hocsinh()
    {
        String sql = "INSERT INTO hocsinh(FirstName, LastName, Email, Phone, DateOfBirth, Gender, RoleID,UserID) VALUES (?,?,?,?,?,?,?,?)";
        if(them == false)
        {
            sql = "UPDATE hocsinh SET FirstName=?,LastName=?, Email=?,Phone=?,DateOfBirth=?,Gender=?, RoleID= ? WHERE UserID = ?";
        }
        SimpleDateFormat sourceFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            PreparedStatement pst = (PreparedStatement) connect.conn.prepareStatement(sql);
            pst.setString(1, txtTen.getText());
            pst.setString(2, txtHo.getText());
            pst.setString(3, txtEmail.getText());
            pst.setString(4, txtSdt.getText());
            try {
                java.util.Date a =  sourceFormat.parse(txtDate.getText());
                String formattedDate = targetFormat.format(a);
                pst.setDate(5, Date.valueOf(formattedDate));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,"Không chuyển dc");
            }
            pst.setString(6, cb_gender.getSelectedItem().toString());
            pst.setString(7, "3");
            pst.setString(8, txtID.getText());
            int update = pst.executeUpdate();
            if(update == 1)
            {
                JOptionPane.showMessageDialog(this,"Cập nhật thành công");
            }
            else
            JOptionPane.showMessageDialog(this,"Cập nhật thất bại");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Lỗi querry hàm hocsinh");
        }
    }
    private void giaovien()
    {
        String sql = "INSERT INTO giaovien( FirstName, LastName, Email, Phone, DateOfBirth, Gender, RoleID,UserID) VALUES  (?,?,?,?,?,?,?,?)";
        if(them == false)
        {
            sql = "UPDATE giaovien SET FirstName=?,LastName=?, Email=?,Phone=?,DateOfBirth=?,Gender=?, RoleID= ? WHERE UserID = ?";
        }
        SimpleDateFormat sourceFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            PreparedStatement pst = (PreparedStatement) connect.conn.prepareStatement(sql);
            pst.setString(1, txtTen.getText());
            pst.setString(2, txtHo.getText());
            pst.setString(3, txtEmail.getText());
            pst.setString(4, txtSdt.getText());
            try {
                java.util.Date a =  sourceFormat.parse(txtDate.getText());
                String formattedDate = targetFormat.format(a);
                pst.setDate(5, Date.valueOf(formattedDate));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,"Không chuyển dc");
            }
            pst.setString(6, cb_gender.getSelectedItem().toString());
            pst.setString(7, "2");
            pst.setString(8, txtID.getText());
            int update = pst.executeUpdate();
            if(update == 1)
            {
                JOptionPane.showMessageDialog(this,"Cập nhật thành công");
            }
            else
            JOptionPane.showMessageDialog(this,"Cập nhật thất bại");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Lỗi querry hàm giáo viên");
        }
    }
    private void nhanvien()
    {
        String sql = "INSERT INTO nhanvienhocvu( FirstName, LastName, Email, Phone, DateOfBirth, Gender, RoleID,UserID) VALUES  (?,?,?,?,?,?,?,?)";
        if(them == false)
        {
            sql = "UPDATE nhanvienhocvu SET FirstName=?,LastName=?, Email=?,Phone=?,DateOfBirth=?,Gender=?, RoleID= ? WHERE UserID = ?";
        }
        SimpleDateFormat sourceFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            PreparedStatement pst = (PreparedStatement) connect.conn.prepareStatement(sql);
            pst.setString(1, txtTen.getText());
            pst.setString(2, txtHo.getText());
            pst.setString(3, txtEmail.getText());
            pst.setString(4, txtSdt.getText());
            try {
                java.util.Date a =  sourceFormat.parse(txtDate.getText());
                String formattedDate = targetFormat.format(a);
                pst.setDate(5, Date.valueOf(formattedDate));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,"Không chuyển dc");
            }
            pst.setString(6, cb_gender.getSelectedItem().toString());
            pst.setString(7, "1");
            pst.setString(8, txtID.getText());
            int update = pst.executeUpdate();
            if(update == 1)
            {
                JOptionPane.showMessageDialog(this,"Cập nhật thành công");
            }
            else
            JOptionPane.showMessageDialog(this,"Cập nhật thất bại");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Lỗi querry hàm giáo viên");
        }
    }
    private void ins_taikhoan(int role)
    {
        String sql = "INSERT INTO taikhoan(Username, Pass, RoleID, UserID) VALUES (?,?,?,?)";
        try {
            PreparedStatement pst = (PreparedStatement) connect.conn.prepareStatement(sql);
            pst.setString(1, txtTk.getText());
            pst.setString(2, txtMk.getText());
            pst.setInt(3, role);
            pst.setString(4, txtID.getText());
            int update = pst.executeUpdate();
            if(update == 1)
            {
                JOptionPane.showMessageDialog(this,"Cập nhật thành công");
            }
            else
            JOptionPane.showMessageDialog(this,"Cập nhật thất bại");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Lỗi querry hàm ins_taikhoan");
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

        dia_hs_gv_nv = new javax.swing.JDialog();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabelid = new javax.swing.JLabel();
        btnOKhocsinh = new javax.swing.JButton();
        txtID = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtHo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cb_gender = new javax.swing.JComboBox<>();
        txtSdt = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        pn_tk_mk = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtTk = new javax.swing.JTextField();
        txtMk = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        panMenu = new javax.swing.JPanel();
        btnHocsinh = new javax.swing.JButton();
        btnGiaoVien = new javax.swing.JButton();
        btnNhanVien = new javax.swing.JButton();
        btnLopHoc = new javax.swing.JButton();
        btnCanhan = new javax.swing.JButton();
        btnDangxuat = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        pn_tsx = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnkichhoat = new javax.swing.JButton();
        btnvohieu = new javax.swing.JButton();

        dia_hs_gv_nv.setTitle("nhanvien");
        dia_hs_gv_nv.setLocation(new java.awt.Point(0, 0));
        dia_hs_gv_nv.setMinimumSize(new java.awt.Dimension(400, 500));
        dia_hs_gv_nv.setResizable(false);
        dia_hs_gv_nv.setSize(new java.awt.Dimension(400, 500));

        jLabel8.setText("Tên");

        jLabel9.setText("Email");

        jLabelid.setText("Mã học sinh");

        btnOKhocsinh.setText("OK");
        btnOKhocsinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKhocsinhActionPerformed(evt);
            }
        });

        jLabel11.setText("Họ");

        jLabel3.setText("Số điện thoại");

        cb_gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));

        jLabel4.setText("Ngày sinh");

        jLabel5.setText("Giới tính");

        jLabel6.setText("Tài khoản");

        jLabel7.setText("Mật khẩu");

        javax.swing.GroupLayout pn_tk_mkLayout = new javax.swing.GroupLayout(pn_tk_mk);
        pn_tk_mk.setLayout(pn_tk_mkLayout);
        pn_tk_mkLayout.setHorizontalGroup(
            pn_tk_mkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_tk_mkLayout.createSequentialGroup()
                .addGroup(pn_tk_mkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pn_tk_mkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTk, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(txtMk)))
        );
        pn_tk_mkLayout.setVerticalGroup(
            pn_tk_mkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_tk_mkLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pn_tk_mkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pn_tk_mkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dia_hs_gv_nvLayout = new javax.swing.GroupLayout(dia_hs_gv_nv.getContentPane());
        dia_hs_gv_nv.getContentPane().setLayout(dia_hs_gv_nvLayout);
        dia_hs_gv_nvLayout.setHorizontalGroup(
            dia_hs_gv_nvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dia_hs_gv_nvLayout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(btnOKhocsinh, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dia_hs_gv_nvLayout.createSequentialGroup()
                .addGap(0, 65, Short.MAX_VALUE)
                .addGroup(dia_hs_gv_nvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pn_tk_mk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(dia_hs_gv_nvLayout.createSequentialGroup()
                        .addGroup(dia_hs_gv_nvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dia_hs_gv_nvLayout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dia_hs_gv_nvLayout.createSequentialGroup()
                                .addComponent(jLabelid, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dia_hs_gv_nvLayout.createSequentialGroup()
                                .addGroup(dia_hs_gv_nvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
                                .addGap(69, 69, 69)))
                        .addGroup(dia_hs_gv_nvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtID)
                            .addComponent(txtHo, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                            .addComponent(txtTen, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                            .addComponent(txtEmail)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dia_hs_gv_nvLayout.createSequentialGroup()
                        .addGroup(dia_hs_gv_nvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(dia_hs_gv_nvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSdt, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                            .addComponent(txtDate)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dia_hs_gv_nvLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb_gender, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        dia_hs_gv_nvLayout.setVerticalGroup(
            dia_hs_gv_nvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dia_hs_gv_nvLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dia_hs_gv_nvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelid, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(dia_hs_gv_nvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dia_hs_gv_nvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dia_hs_gv_nvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dia_hs_gv_nvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dia_hs_gv_nvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDate)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(dia_hs_gv_nvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pn_tk_mk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(btnOKhocsinh))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản trị viên"); // NOI18N

        panMenu.setBackground(new java.awt.Color(102, 204, 255));
        panMenu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnHocsinh.setText("Học sinh");
        btnHocsinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHocsinhActionPerformed(evt);
            }
        });

        btnGiaoVien.setText("Giáo viên");
        btnGiaoVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGiaoVienActionPerformed(evt);
            }
        });

        btnNhanVien.setText("Nhân Viên");
        btnNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanVienActionPerformed(evt);
            }
        });

        btnLopHoc.setText("Lớp học");
        btnLopHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLopHocActionPerformed(evt);
            }
        });

        btnCanhan.setText("Thông tin cá nhân");
        btnCanhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCanhanActionPerformed(evt);
            }
        });

        btnDangxuat.setText("Đăng xuất");
        btnDangxuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangxuatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panMenuLayout = new javax.swing.GroupLayout(panMenu);
        panMenu.setLayout(panMenuLayout);
        panMenuLayout.setHorizontalGroup(
            panMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panMenuLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(btnHocsinh, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGiaoVien, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLopHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCanhan, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDangxuat, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );
        panMenuLayout.setVerticalGroup(
            panMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panMenuLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHocsinh, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGiaoVien, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLopHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCanhan, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDangxuat, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        table = new JTable()
        {
            public boolean isCellEditable(int row, int colum)
            {
                if(table.getColumnName(0) == "Lớp")
                return false;
                else
                return true;
            }
        };
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(table);

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_tsxLayout = new javax.swing.GroupLayout(pn_tsx);
        pn_tsx.setLayout(pn_tsxLayout);
        pn_tsxLayout.setHorizontalGroup(
            pn_tsxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_tsxLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(btnSua)
                .addGap(56, 56, 56)
                .addComponent(btnXoa)
                .addContainerGap())
        );
        pn_tsxLayout.setVerticalGroup(
            pn_tsxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_tsxLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(pn_tsxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        btnkichhoat.setText("Kích hoạt");
        btnkichhoat.setMaximumSize(new java.awt.Dimension(72, 23));
        btnkichhoat.setMinimumSize(new java.awt.Dimension(72, 23));
        btnkichhoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkichhoatActionPerformed(evt);
            }
        });

        btnvohieu.setText("Vô hiệu");
        btnvohieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvohieuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnvohieu, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnkichhoat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pn_tsx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pn_tsx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnkichhoat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnvohieu, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHocsinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHocsinhActionPerformed
        init_hocsinh();
        pn_tsx.setVisible(true);
        btnkichhoat.setVisible(false);
        btnvohieu.setVisible(false);
    }//GEN-LAST:event_btnHocsinhActionPerformed

    private void btnGiaoVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGiaoVienActionPerformed
        init_giaovien();
        pn_tsx.setVisible(true);
        btnkichhoat.setVisible(false);
        btnvohieu.setVisible(false);
    }//GEN-LAST:event_btnGiaoVienActionPerformed

    private void btnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanVienActionPerformed
        init_nhanvien();
        pn_tsx.setVisible(true);
        btnkichhoat.setVisible(false);
        btnvohieu.setVisible(false);
    }//GEN-LAST:event_btnNhanVienActionPerformed

    private void btnLopHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLopHocActionPerformed
        init_lophoc();
        pn_tsx.setVisible(false);
        btnkichhoat.setVisible(true);
        btnvohieu.setVisible(true);
    }//GEN-LAST:event_btnLopHocActionPerformed

    private void btnOKhocsinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKhocsinhActionPerformed
        if(jLabelid.getText().equals("Mã học sinh"))
        {
            hocsinh();
            if(them)
                ins_taikhoan(3);
        }
        else if(jLabelid.getText().equals("Mã giáo viên"))
        {
            giaovien();
            if(them)
                ins_taikhoan(2);
        }
        else if(jLabelid.getText().equals("Mã nhân viên"))
        {
            nhanvien();
            if(them)
                ins_taikhoan(1);
        }
        dia_hs_gv_nv.dispose();
    }//GEN-LAST:event_btnOKhocsinhActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        them = true;
        String name = table.getModel().getColumnName(1);
        switch (name) {
            case "Mã học sinh":
                jLabelid.setText("Mã học sinh");
                reset_upd_dialog("hocsinh");
                dia_hs_gv_nv.setLocationRelativeTo(null);
                dia_hs_gv_nv.setVisible(true);
                break;
            case "Mã giáo viên":
                jLabelid.setText("Mã giáo viên");
                reset_upd_dialog("giaovien");
                dia_hs_gv_nv.setLocationRelativeTo(null);
                dia_hs_gv_nv.setVisible(true);
                break;
            case "Mã nhân viên":
                jLabelid.setText("Mã nhân viên");
                reset_upd_dialog("nhanvienhocvu");
                dia_hs_gv_nv.setLocationRelativeTo(null);
                dia_hs_gv_nv.setVisible(true);
                break;
            default:
                JOptionPane.showMessageDialog(this,"Vui lòng chọn bảng để thêm");
                break;
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        them = false;
        String name = table.getModel().getColumnName(1);
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 dòng để sửa", "lỗi", JOptionPane.ERROR_MESSAGE);
        } 
        else 
        {
            switch (name) {
                case "Mã học sinh":
                    jLabelid.setText("Mã học sinh");
                    reset_upd_dialog("hocsinh");
                    dia_hs_gv_nv.setLocationRelativeTo(null);
                    dia_hs_gv_nv.setVisible(true);
                    break;
                case "Mã giáo viên":
                    jLabelid.setText("Mã giáo viên");
                    reset_upd_dialog("giaovien");
                    dia_hs_gv_nv.setLocationRelativeTo(null);
                    dia_hs_gv_nv.setVisible(true);
                    break;
                case "Mã nhân viên":
                    jLabelid.setText("Mã nhân viên");
                    reset_upd_dialog("nhanvienhocvu");
                    dia_hs_gv_nv.setLocationRelativeTo(null);
                    dia_hs_gv_nv.setVisible(true);
                    break;
                default:
                    JOptionPane.showMessageDialog(this,"Vui lòng chọn bảng để thêm");
                    break;
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        String name = table.getModel().getColumnName(1);
        DefaultTableModel df = (DefaultTableModel) table.getModel();
        String sql1 ="DELETE FROM hocsinh WHERE UserID = ?";
        String sql2 = "DELETE FROM giaovien WHERE UserID = ?";
        String sql3 = "DELETE FROM nhanvienhocvu WHERE UserID = ?";
        switch (name) {
            case "Mã học sinh":
                for (int i = table.getRowCount()-1; i >= 0; i--) 
                {
                    Boolean isChecked = (Boolean) table.getValueAt(i, 0);
                    if (isChecked) 
                    {
                        try {
                            PreparedStatement pst = connect.conn.prepareStatement(sql1);
                            pst.setString(1, (String) table.getValueAt(i, 1));
                            pst.executeUpdate();
                            df.removeRow(i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            case "Mã giáo viên":
                for (int i = table.getRowCount()-1; i >= 0; i--) 
                {
                    Boolean isChecked = (Boolean) table.getValueAt(i, 0);
                    if (isChecked) 
                    {
                        try {
                            PreparedStatement pst = connect.conn.prepareStatement(sql2);
                            pst.setString(1, (String) table.getValueAt(i, 1));
                            pst.executeUpdate();
                            df.removeRow(i);
                        }
                        catch (SQLException e) {
                            if (e.getSQLState().equals("10000")) {
                                JOptionPane.showMessageDialog(null, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                            } else 
                            {
                                e.printStackTrace();
                            }
                        }
                        
                    }
                }
                break;
            case "Mã nhân viên":
                for (int i = table.getRowCount()-1; i >= 0; i--)
                {
                    Boolean isChecked = (Boolean) table.getValueAt(i, 0);
                    if (isChecked) 
                    {
                        try {
                            PreparedStatement pst = connect.conn.prepareStatement(sql3);
                            pst.setString(1, (String) table.getValueAt(i, 1));
                            pst.executeUpdate();
                            df.removeRow(i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            default:
                JOptionPane.showMessageDialog(this,"Vui lòng chọn bảng để thêm");
                break;
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnCanhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCanhanActionPerformed
        pn_tsx.setVisible(false);
        btnkichhoat.setVisible(false);
        btnvohieu.setVisible(false);
        a = new thong_tin_ca_nhan(user_id, "sysadmin");
        a.setVisible(true);
    }//GEN-LAST:event_btnCanhanActionPerformed

    private void btnDangxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangxuatActionPerformed
        dangnhap b = new dangnhap();
        b.setVisible(true);
        if(a != null)
            a.dispose();
        this.dispose();
    }//GEN-LAST:event_btnDangxuatActionPerformed

    private void btnkichhoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkichhoatActionPerformed
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 lớp để kích hoạt", "lỗi", JOptionPane.ERROR_MESSAGE);
        } 
        else 
        {
            if(table.getValueAt(table.getSelectedRow(), 2) == null || table.getValueAt(table.getSelectedRow(), 3) == null)
            {
                JOptionPane.showMessageDialog(this, "Lớp không đủ điều kiện kích hoạt", "lỗi", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                String sql = "UPDATE lop SET ClassState = 'Active' WHERE ClassID = ?";
                try {
                    PreparedStatement pst = connect.conn.prepareStatement(sql);
                    pst.setString(1, table.getValueAt(table.getSelectedRow(), 0).toString());
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(this,"kích hoạt thành công");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this,"lỗi btn kích hoạt");
                }
            }
        }
    }//GEN-LAST:event_btnkichhoatActionPerformed

    private void btnvohieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvohieuActionPerformed
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 lớp để vô hiệu", "lỗi", JOptionPane.ERROR_MESSAGE);
        } 
        else 
        {
            String sql = "UPDATE lop SET ClassState = 'Inactive' WHERE ClassID = ?";
            try {
                PreparedStatement pst = connect.conn.prepareStatement(sql);
                pst.setString(1, table.getValueAt(table.getSelectedRow(), 0).toString());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this,"Vô hiệu thành công");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,"lỗi btn vô hiệu");
            }
        }
    }//GEN-LAST:event_btnvohieuActionPerformed

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
            java.util.logging.Logger.getLogger(quantrivien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(quantrivien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(quantrivien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(quantrivien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new quantrivien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCanhan;
    private javax.swing.JButton btnDangxuat;
    private javax.swing.JButton btnGiaoVien;
    private javax.swing.JButton btnHocsinh;
    private javax.swing.JButton btnLopHoc;
    private javax.swing.JButton btnNhanVien;
    private javax.swing.JButton btnOKhocsinh;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnkichhoat;
    private javax.swing.JButton btnvohieu;
    private javax.swing.JComboBox<String> cb_gender;
    private javax.swing.JDialog dia_hs_gv_nv;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelid;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panMenu;
    private javax.swing.JPanel pn_tk_mk;
    private javax.swing.JPanel pn_tsx;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHo;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtMk;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTk;
    // End of variables declaration//GEN-END:variables
}
