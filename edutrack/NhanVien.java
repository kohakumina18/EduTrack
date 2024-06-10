/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package edutrack;

import com.mysql.jdbc.Statement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author chauanhkhoi
 */
public class NhanVien extends javax.swing.JFrame {

    /**
     * Creates new form NhanVienMainFrame
     */
    mysql connect = new mysql();
    String user_id;
    Boolean them = true;
    thong_tin_ca_nhan a;
    public NhanVien() {
        initComponents();
        user_id = "nv001";
        init();
    }
    public NhanVien(String user_id_log)
    {
        initComponents();
        this.setLocationRelativeTo(null);
        user_id = user_id_log;
        init();
        init_class();
        btnThem.setVisible(true);
        btnSua.setVisible(true);
        panel_class.setVisible(false);
        panel_hk.setVisible(false);
    }
    private void init()
    {
        String sql_cb_teacher = "select userID,lastname, firstname from giaovien";
        try {
            Statement st = (Statement) connect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql_cb_teacher);
            while(rs.next())
            {
                cb_Teacher.addItem(rs.getString("userID")+" :"+ rs.getString("lastname") + " "+rs.getString("firstname"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        String sql_cb_class = "select ClassID,Classname from lop";
        try {
            Statement st = (Statement) connect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql_cb_class);
            while(rs.next())
            {
                cb_lop.addItem(rs.getString("ClassID")+" :"+ rs.getString("Classname"));
                cb_lop_xep_them.addItem(rs.getString("ClassID")+" :"+ rs.getString("Classname"));
                cb_lop_xep_sua.addItem(rs.getString("ClassID")+" :"+ rs.getString("Classname"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        String sql_cb_student = "SELECT UserID from hocsinh";
        try {
            Statement st = (Statement) connect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql_cb_student);
            while(rs.next())
            {
                cb_student.addItem(rs.getString("userid"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        String sql_cb_course ="select courseid from course";
        try {
            Statement st = (Statement) connect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql_cb_course);
            while(rs.next())
            {
                cb_course.addItem(rs.getString("courseid"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void init_student_xep()
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
        String tieuDe[] = {"","Mã học sinh", "Tên học sinh"};
        DefaultTable.setColumnIdentifiers(tieuDe);
        String sql_tb_xep = "SELECT UserID, FirstName, LastName FROM hocsinh WHERE `UserID` NOT IN (SELECT UserID FROM xeplop "
                + "WHERE classid = ? )";
        try {
            PreparedStatement pst = (PreparedStatement) (Statement) connect.conn.prepareStatement(sql_tb_xep);
            pst.setString(1, cb_lop_xep_them.getSelectedItem().toString().substring(0, 3));
            ResultSet rs = pst.executeQuery();
            Object row[] = new Object[3];
            while(rs.next())
            {
                row[0] = Boolean.FALSE;
                row[1] = rs.getString("userid");
                row[2] = rs.getString("lastname")+" " + rs.getString("firstname");
                DefaultTable.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Lỗi querry hàm init lớp xếp lớp");
        }
        tb_them_xep.setModel(DefaultTable);
        tb_them_xep.getColumnModel().getColumn(0).setPreferredWidth(10);
        tb_them_xep.getColumnModel().getColumn(0).setWidth(10);
    }
    private void init_class()
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
        String tieuDe[] = {"","ClassID", "Tên Lớp", "Phòng", "Giáo viên phụ trách", "CourseID", "trạng thái lớp", "số lượng giới hạn", "ngày tạo"};
        DefaultTable.setColumnIdentifiers(tieuDe);
        String sql = "SELECT ClassID, ClassName, CreateDate, RoomNumber, firstname, lastname,giaovien.userid, courseid, ClassState, so_luong_gioi_han FROM lop"
                + " left outer join giaovien on giaovien.UserID = lop.AssignedTeacherID left outer join chitietlophoc on "
                + "chitietlophoc.id_lop_hoc = lop.ClassID";
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Statement st = (Statement) connect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            Object row[] = new Object[9];
            while(rs.next())
            {
                row[0] = Boolean.FALSE;
                row[1] = rs.getString("classid");
                row[2] = rs.getString("classname");
                row[3] = rs.getString("roomnumber");
                row[4] = rs.getString("userID")+" :"+ rs.getString("lastname") + " "+rs.getString("firstname");
                row[5] = rs.getString("courseid");
                row[6] = rs.getString("classstate");
                row[7] = rs.getString("so_luong_gioi_han");
                row[8] = format.format(rs.getDate("createdate"));
                DefaultTable.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Lỗi querry hàm init class");
        }
        table.setModel(DefaultTable);
        table.getColumnModel().getColumn(4).setPreferredWidth(130);
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(0).setWidth(10);
    }
    private void init_course()
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
        String tieuDe[] = {"","CourseID", "Tên Khóa học", "Nội dung", "Ngày tạo", "Ngày bắt đầu", "Ngày kết thúc", "nhân viên tạo"};
        DefaultTable.setColumnIdentifiers(tieuDe);
        String sql = "SELECT CourseID, Coursename, Noidung, ngay_tao, ngay_bd, ngay_kt, lastname, firstname FROM course inner join "
                + "(select UserID,lastname, firstname from nhanvienhocvu) nhanvienhocvu on Course.UserID = nhanvienhocvu.UserID;";
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Statement pst = (Statement) connect.conn.createStatement();
            ResultSet rs = pst.executeQuery(sql);
            Object row[] = new Object[8];
            while(rs.next())
            {
                row[0] = Boolean.FALSE;
                row[1] = rs.getString("courseid");
                row[2] = rs.getString("coursename");
                row[3] = rs.getString("noidung");
                row[4] = format.format(rs.getDate("ngay_tao"));
                row[5] = format.format(rs.getDate("ngay_bd"));
                row[6] = format.format(rs.getDate("ngay_kt"));
                row[7] = rs.getString("lastname") + " "+rs.getString("firstname");
                DefaultTable.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Lỗi querry hàm init course");
        }
        table.setModel(DefaultTable);
        TableColumn column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(10);
        column.setWidth(10);
    }
    private void init_grade()
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
        String tieuDe[] = {"","GradeID", "Tên Lớp", "Tên học sinh", "Giữa kì", "Cuối kì", "Nhận xét"};
        DefaultTable.setColumnIdentifiers(tieuDe);
        String sql = "select GradeID, lop.ClassName, hocsinh.LastName, hocsinh.FirstName, midtern,final,Comments,studentid from bangdiem "
                + "INNER JOIN lop on lop.ClassID = bangdiem.ClassID INNER JOIN hocsinh on hocsinh.UserID = bangdiem.StudentID;";
        try {
            Statement st = (Statement) connect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            Object row[] = new Object[7];
            while(rs.next())
            {
                row[0] = Boolean.FALSE;
                row[1] = rs.getString("gradeid");
                row[2] = rs.getString("classname");
                row[3] = rs.getString("studentid")+" :"+ rs.getString("lastname") + " "+rs.getString("firstname");
                row[4] = rs.getString("midtern");
                row[5] = rs.getString("final");
                row[6] = rs.getString("comments");
                DefaultTable.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Lỗi querry hàm init grade");
        }
        table.setModel(DefaultTable);
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(0).setWidth(10);
        table.getColumnModel().getColumn(6).setPreferredWidth(100);
        table.getColumnModel().getColumn(6).setWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setWidth(150);
    }
    private void init_phanhoi()
    {
        DefaultTableModel DefaultTable = new DefaultTableModel();
        String tieuDe[] = {"FeedbackID","Học sinh", "Ngày phản hồi", "Trạng thái"};
        DefaultTable.setColumnIdentifiers(tieuDe);
        String sql = "SELECT FeedbackID,hocsinh.LastName, hocsinh.FirstName, DateOfFeedback, FeedbackState FROM phanhoi INNER JOIN hocsinh "
                + "ON hocsinh.UserID = phanhoi.UserID";
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Statement st = (Statement) connect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String row[] = new String[4];
            while(rs.next())
            {
                row[0] = rs.getString("FeedbackID");
                row[1] = rs.getString("firstname") + " "+rs.getString("lastname");
                row[2] = format.format(rs.getDate("DateofFeedback"));
                row[3] = rs.getString("FeedbackState");
                DefaultTable.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Lỗi querry hàm init phản hồi");
        }
        table.setModel(DefaultTable);
    }
    private void init_xeplop()
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
        String tieuDe[] = {"","Lớp", "Học sinh"};
        DefaultTable.setColumnIdentifiers(tieuDe);
        String sql = "SELECT  lop.ClassID,ClassName,xeplop.UserID,FirstName,LastName FROM xeplop inner join "
                + "hocsinh on hocsinh.UserID = xeplop.UserID INNER JOIN lop on lop.ClassID = xeplop.ClassID";
        try {
            Statement st = (Statement) connect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            Object row[] = new Object[3];
            while(rs.next())
            {
                row[0] = Boolean.FALSE;
                row[1] = rs.getString("lop.classid")+" :"+rs.getString("classname");
                row[2] = rs.getString("userID")+" :"+ rs.getString("lastname") + " "+rs.getString("firstname");
                DefaultTable.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Lỗi querry hàm init xếp lớp");
        }
        table.setModel(DefaultTable);
        table.getColumnModel().getColumn(1).setPreferredWidth(250);
        table.getColumnModel().getColumn(1).setWidth(250);
        table.getColumnModel().getColumn(2).setPreferredWidth(250);
        table.getColumnModel().getColumn(2).setWidth(250);
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(0).setWidth(10);
    }
    private void Lop()
    {
        String sql = "INSERT INTO lop(ClassName, CreateDate, RoomNumber, AssignedTeacherID, ClassState, so_luong_gioi_han, ClassID) VALUES (?,?,?,?,?,?,?)";
        if(them == false)
        {
            sql = "UPDATE lop SET ClassName=?,CreateDate=?,RoomNumber=?,AssignedTeacherID=?,ClassState=?,so_luong_gioi_han=? WHERE ClassID = ?";
        }
        Date current_date = Date.valueOf(LocalDate.now());
        try {
            PreparedStatement pst = (PreparedStatement) connect.conn.prepareStatement(sql);
            pst.setString(1, txtClassName.getText());
            pst.setDate(2, current_date);
            pst.setString(3, txtRoom.getText());
            pst.setString(4, cb_Teacher.getSelectedItem().toString().substring(0, 5));
            if(table.getValueAt(table.getSelectedRow(), 6).toString() != null)
            {
                pst.setString(5, table.getValueAt(table.getSelectedRow(), 6).toString());
            }   
            else
            {
                pst.setString(5, "Inactive");
            }
                
            pst.setString(6, txtMax.getText());
            pst.setString(7, txtClassID.getText());
            int update = pst.executeUpdate();
            if(update == 1)
            {
                JOptionPane.showMessageDialog(this,"Cập nhật thành công");
            }
            else
            JOptionPane.showMessageDialog(this,"Cập nhật thất bại");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Lỗi querry hàm Lớp");
        }
    }
    private void ctlh()
    {
        String sql_course ="UPDATE chitietlophoc SET CourseID=? WHERE id_lop_hoc=?";
        try 
        {
            PreparedStatement pst = connect.conn.prepareStatement(sql_course);
            pst.setString(1, cb_course.getSelectedItem().toString());
            pst.setString(2, txtClassID.getText());
            pst.executeUpdate();
        } catch (Exception e) {
        }
    }
    private void Khoahoc()
    {
        String sql = "INSERT INTO course(CourseName, NoiDung, ngay_tao, ngay_bd, ngay_kt, UserID, CourseID) VALUES (?,?,?,?,?,?,?)";
        if(them == false)
        {
            sql = "UPDATE course SET CourseName=?,NoiDung=?,ngay_tao=?, ngay_bd=?, ngay_kt=?,UserID=? WHERE CourseID = ?";
        }
        SimpleDateFormat sourceFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date current_date = Date.valueOf(LocalDate.now());
        try {
            PreparedStatement pst = connect.conn.prepareStatement(sql);
            pst.setString(1, txtCourseName.getText());
            pst.setString(2, txtNoiDung.getText());
            pst.setDate(3, current_date);
            try {
                java.util.Date a =  sourceFormat.parse(txtStart.getText());
                String formattedDate = targetFormat.format(a);
                pst.setDate(4, Date.valueOf(formattedDate));
                a = sourceFormat.parse(txtEnd.getText());
                formattedDate = targetFormat.format(a);
                pst.setDate(5, Date.valueOf(formattedDate));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,"Không chuyển dc");
            }
            pst.setString(6, user_id);
            pst.setString(7, txtCourseID.getText());
            int update = pst.executeUpdate();
            if(update == 1)
            {
                JOptionPane.showMessageDialog(this,"Cập nhật thành công");
            }
            else
            JOptionPane.showMessageDialog(this,"Cập nhật thất bại");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Lỗi querry hàm khóa học");
        }
    }
    private void xeplop(String student_id, int i)
    {
        DefaultTableModel df = (DefaultTableModel) tb_them_xep.getModel();
        String sql = "INSERT INTO xeplop(ClassID,UserID) VALUES (?,?)";
        if(them == false)
        {
            sql = "UPDATE xeplop SET ClassID= ? WHERE UserID= ?";
        }
        try {
            PreparedStatement pst = (PreparedStatement) connect.conn.prepareStatement(sql);
            pst.setString(1, cb_lop_xep_them.getSelectedItem().toString().substring(0, 3));
            pst.setString(2, student_id);
            if(them == false)
            {
                pst.setString(1, cb_lop_xep_sua.getSelectedItem().toString().substring(0, 3));
                pst.setString(2, label_hs_xep_sua.getText().substring(0, 5));
            }
            pst.executeUpdate();
            if(them)
                df.removeRow(i);
        } catch (SQLException e) {
            if (e.getSQLState().equals("30000")) 
            {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            } 
            else 
            {
                e.printStackTrace();
            }
        }
    }
    private void read_commit_Phanhoi(String us)
    {
        String sql = "select content from phanhoi where feedbackid = ?";
        try {
            PreparedStatement st = (PreparedStatement) (Statement) connect.conn.prepareStatement(sql);
            st.setString(1, us);
            ResultSet rs = st.executeQuery();
            if(rs.next())
            {
                txt_phanhoi.setText(rs.getString("Content"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"read commit thất bại");
        }
        
    }
    private void upd_Diem(int i, String grade_id, String newData)
    {
        String sql = "UPDATE bangdiem SET midtern=? WHERE GradeID = ?";
        if(i == 2)
        {
            sql = "UPDATE bangdiem SET final=? WHERE GradeID = ?";
        }
        if(i == 3)
        {
            sql = "UPDATE bangdiem SET comments=? WHERE GradeID = ?";
        }
        try {
            PreparedStatement pst = connect.conn.prepareStatement(sql);
            pst.setString(1, newData);
            pst.setString(2, grade_id);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Sai định dạng, vui lòng kiểm tra lại");
        }
    }
    private void Diem()
    {
        table.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) 
            {
                if (e.getType() == TableModelEvent.UPDATE) 
                {
                    int row = e.getFirstRow();
                    int column = e.getColumn();
                    String colum = table.getModel().getColumnName(column);
                    Object newData = table.getModel().getValueAt(row, column);
                    String id = (String) table.getModel().getValueAt(row, 1);
                    if(colum.equals("Giữa kì"))
                    {
                        upd_Diem(1, id, (String) newData);
                    }
                    else if(colum.equals("Cuối kì"))
                    {
                        upd_Diem(2, id, (String) newData);
                    }
                    else if(colum.equals("Nhận xét"))
                    {
                        upd_Diem(3, id, (String) newData);
                    }
                }
            }
        });
    }
    private void filter(String fil_class, String fil_Gradename)
    {
        DefaultTableModel df = (DefaultTableModel) table.getModel();
        ArrayList<RowFilter<Object, Object>> filter = new ArrayList<>();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(df);
        table.setRowSorter(null);
        if(fil_class != "All")
        {
            filter.add(RowFilter.regexFilter(fil_class));
        }
        if(fil_Gradename != "All")
        {
            filter.add(RowFilter.regexFilter(fil_Gradename));
        }
        if (!filter.isEmpty()) {
                    sorter.setRowFilter(RowFilter.andFilter(filter));
                }
        table.setRowSorter(sorter);
    }
    private void run_fil()
    {
        String fil = cb_lop.getSelectedItem().toString();
        String fil_ = cb_student.getSelectedItem().toString();
        if(!fil.equals("All"))
        {
            fil = fil.substring(5);
        }
        filter(fil, fil_);
    }
    private void reset_upd_class(Boolean reset)
    {
        if(reset)
        {
            txtClassID.setEditable(true);
            txtClassID.setText("");
            txtClassName.setText("");
            txtRoom.setText("");
            cb_Teacher.setSelectedIndex(0);
            cb_course.setSelectedIndex(0);
            txtMax.setText("");
        }
        else
        {
            txtClassID.setEditable(false);
            txtClassID.setText( table.getValueAt(table.getSelectedRow(), 1).toString());
            txtClassName.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
            txtRoom.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
            if(table.getValueAt(table.getSelectedRow(), 4) == null)
                cb_Teacher.setSelectedIndex(0);
            else
                cb_Teacher.setSelectedItem(table.getValueAt(table.getSelectedRow(), 4).toString());
            if(table.getValueAt(table.getSelectedRow(), 5) == null)
                cb_course.setSelectedIndex(0);
            else
                cb_course.setSelectedItem(table.getValueAt(table.getSelectedRow(), 5).toString());
            txtMax.setText(table.getValueAt(table.getSelectedRow(), 7).toString());
        }
    }
    private void reset_upd_course(Boolean reset)
    {
        if(reset)
        {
            txtCourseID.setEditable(true);
            txtCourseID.setText("");
            txtCourseName.setText("");
            txtNoiDung.setText("");
            txtStart.setText("");
            txtEnd.setText("");
        }
        else
        {
            txtCourseID.setEditable(false);
            txtCourseID.setText( table.getValueAt(table.getSelectedRow(), 1).toString());
            txtCourseName.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
            txtNoiDung.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
            txtStart.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
            txtEnd.setText(table.getValueAt(table.getSelectedRow(), 6).toString());
        }
    }
    private void set_df()
    {
        cb_Teacher.setSelectedIndex(0);
        cb_student.setSelectedIndex(0);
        cb_lop.setSelectedIndex(0);
        table.setRowSorter(null);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dia_class = new javax.swing.JDialog();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnOKClass = new javax.swing.JButton();
        txtClassID = new javax.swing.JTextField();
        txtRoom = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtClassName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cb_Teacher = new javax.swing.JComboBox<>();
        txtMax = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cb_course = new javax.swing.JComboBox<>();
        dia_course = new javax.swing.JDialog();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btnOKCourse = new javax.swing.JButton();
        txtCourseID = new javax.swing.JTextField();
        txtNoiDung = new javax.swing.JTextField();
        txtEnd = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtCourseName = new javax.swing.JTextField();
        txtStart = new javax.swing.JTextField();
        dia_phanhoi = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_phanhoi = new javax.swing.JTextArea();
        btn_xacnhan = new javax.swing.JButton();
        dia_xep_them = new javax.swing.JDialog();
        cb_lop_xep_them = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_them_xep = new javax.swing.JTable();
        btnthem = new javax.swing.JButton();
        dia_xep_sua = new javax.swing.JDialog();
        label_hs_xep_sua = new javax.swing.JLabel();
        cb_lop_xep_sua = new javax.swing.JComboBox<>();
        btncapnhat = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnCourse = new javax.swing.JButton();
        btnClass = new javax.swing.JButton();
        btnGrade = new javax.swing.JButton();
        btnDangxuat = new javax.swing.JButton();
        btnPhanhoi = new javax.swing.JButton();
        btnXep = new javax.swing.JButton();
        btnCanhan = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        panel_hk = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cb_student = new javax.swing.JComboBox<>();
        panel_class = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cb_lop = new javax.swing.JComboBox<>();

        dia_class.setTitle("Lớp"); // NOI18N
        dia_class.setLocation(new java.awt.Point(0, 0));
        dia_class.setSize(new java.awt.Dimension(380, 370));

        jLabel8.setText("Số phòng");

        jLabel9.setText("Giáo viên");

        jLabel12.setText("Mã Lớp");

        btnOKClass.setText("OK");
        btnOKClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKClassActionPerformed(evt);
            }
        });

        jLabel11.setText("Tên Lớp");

        jLabel1.setText("Số lượng giới hạn");

        jLabel4.setText("Course");

        javax.swing.GroupLayout dia_classLayout = new javax.swing.GroupLayout(dia_class.getContentPane());
        dia_class.getContentPane().setLayout(dia_classLayout);
        dia_classLayout.setHorizontalGroup(
            dia_classLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dia_classLayout.createSequentialGroup()
                .addGap(0, 45, Short.MAX_VALUE)
                .addGroup(dia_classLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(dia_classLayout.createSequentialGroup()
                        .addGroup(dia_classLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dia_classLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dia_classLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dia_classLayout.createSequentialGroup()
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(65, 65, 65))
                                    .addGroup(dia_classLayout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(69, 69, 69)))
                                .addGroup(dia_classLayout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addGap(72, 72, 72)))
                            .addGroup(dia_classLayout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65)))
                        .addGroup(dia_classLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtClassID)
                            .addComponent(txtClassName, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                            .addComponent(txtRoom, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                            .addComponent(cb_Teacher, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dia_classLayout.createSequentialGroup()
                        .addGroup(dia_classLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(dia_classLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMax, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                            .addComponent(cb_course, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(dia_classLayout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(btnOKClass, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dia_classLayout.setVerticalGroup(
            dia_classLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dia_classLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dia_classLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClassID, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dia_classLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtClassName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(dia_classLayout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)))
                .addGap(18, 18, 18)
                .addGroup(dia_classLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dia_classLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_Teacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dia_classLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cb_course)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(dia_classLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(btnOKClass)
                .addGap(25, 25, 25))
        );

        dia_course.setTitle("Course"); // NOI18N
        dia_course.setLocation(new java.awt.Point(0, 0));
        dia_course.setSize(new java.awt.Dimension(380, 370));

        jLabel13.setText("Nội dung");

        jLabel14.setText("Ngày bắt đầu");

        jLabel15.setText("Ngày kết thúc");

        jLabel16.setText("Mã khóa học");

        btnOKCourse.setText("OK");
        btnOKCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKCourseActionPerformed(evt);
            }
        });

        jLabel17.setText("Tên khóa học");

        javax.swing.GroupLayout dia_courseLayout = new javax.swing.GroupLayout(dia_course.getContentPane());
        dia_course.getContentPane().setLayout(dia_courseLayout);
        dia_courseLayout.setHorizontalGroup(
            dia_courseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dia_courseLayout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(btnOKCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(104, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dia_courseLayout.createSequentialGroup()
                .addGap(0, 45, Short.MAX_VALUE)
                .addGroup(dia_courseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(dia_courseLayout.createSequentialGroup()
                        .addGroup(dia_courseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dia_courseLayout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dia_courseLayout.createSequentialGroup()
                                .addGroup(dia_courseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(45, 45, 45)))
                        .addGroup(dia_courseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCourseID)
                            .addComponent(txtCourseName, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                            .addComponent(txtNoiDung, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                            .addComponent(txtStart)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dia_courseLayout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        dia_courseLayout.setVerticalGroup(
            dia_courseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dia_courseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dia_courseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCourseID, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dia_courseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCourseName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(dia_courseLayout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)))
                .addGap(18, 18, 18)
                .addGroup(dia_courseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dia_courseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dia_courseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addComponent(btnOKCourse)
                .addContainerGap())
        );

        dia_phanhoi.setTitle("Phản hồi"); // NOI18N
        dia_phanhoi.setMinimumSize(new java.awt.Dimension(400, 300));

        txt_phanhoi.setColumns(20);
        txt_phanhoi.setRows(5);
        jScrollPane2.setViewportView(txt_phanhoi);

        btn_xacnhan.setText("Xác nhận");
        btn_xacnhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xacnhanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dia_phanhoiLayout = new javax.swing.GroupLayout(dia_phanhoi.getContentPane());
        dia_phanhoi.getContentPane().setLayout(dia_phanhoiLayout);
        dia_phanhoiLayout.setHorizontalGroup(
            dia_phanhoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(dia_phanhoiLayout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(btn_xacnhan)
                .addContainerGap(164, Short.MAX_VALUE))
        );
        dia_phanhoiLayout.setVerticalGroup(
            dia_phanhoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dia_phanhoiLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(btn_xacnhan)
                .addGap(25, 25, 25))
        );

        dia_xep_them.setTitle("Thêm"); // NOI18N
        dia_xep_them.setMinimumSize(new java.awt.Dimension(400, 400));

        cb_lop_xep_them.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_lop_xep_themItemStateChanged(evt);
            }
        });

        tb_them_xep.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tb_them_xep);

        btnthem.setText("Thêm");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dia_xep_themLayout = new javax.swing.GroupLayout(dia_xep_them.getContentPane());
        dia_xep_them.getContentPane().setLayout(dia_xep_themLayout);
        dia_xep_themLayout.setHorizontalGroup(
            dia_xep_themLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(dia_xep_themLayout.createSequentialGroup()
                .addGroup(dia_xep_themLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dia_xep_themLayout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(cb_lop_xep_them, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dia_xep_themLayout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(btnthem)))
                .addContainerGap(155, Short.MAX_VALUE))
        );
        dia_xep_themLayout.setVerticalGroup(
            dia_xep_themLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dia_xep_themLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cb_lop_xep_them, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnthem)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        dia_xep_sua.setTitle("Sửa"); // NOI18N
        dia_xep_sua.setMinimumSize(new java.awt.Dimension(370, 250));

        label_hs_xep_sua.setText("jLabel4");

        btncapnhat.setText("Cập nhật");
        btncapnhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncapnhatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dia_xep_suaLayout = new javax.swing.GroupLayout(dia_xep_sua.getContentPane());
        dia_xep_sua.getContentPane().setLayout(dia_xep_suaLayout);
        dia_xep_suaLayout.setHorizontalGroup(
            dia_xep_suaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dia_xep_suaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_hs_xep_sua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                .addComponent(cb_lop_xep_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(dia_xep_suaLayout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addComponent(btncapnhat)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dia_xep_suaLayout.setVerticalGroup(
            dia_xep_suaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dia_xep_suaLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(dia_xep_suaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_hs_xep_sua)
                    .addComponent(cb_lop_xep_sua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                .addComponent(btncapnhat)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Nhân viên học vụ"); // NOI18N

        jPanel1.setBackground(new java.awt.Color(102, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnCourse.setText("Course");
        btnCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCourseActionPerformed(evt);
            }
        });

        btnClass.setText("Lớp");
        btnClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClassActionPerformed(evt);
            }
        });

        btnGrade.setText("Điểm số");
        btnGrade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGradeActionPerformed(evt);
            }
        });

        btnDangxuat.setText("Đăng xuất");
        btnDangxuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangxuatActionPerformed(evt);
            }
        });

        btnPhanhoi.setText("Phản hồi");
        btnPhanhoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhanhoiActionPerformed(evt);
            }
        });

        btnXep.setText("Xếp lớp");
        btnXep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXepActionPerformed(evt);
            }
        });

        btnCanhan.setText("Thông tin cá nhân");
        btnCanhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCanhanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnClass, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXep, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPhanhoi, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnCanhan, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDangxuat, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClass, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDangxuat, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPhanhoi, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXep, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCanhan, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        table = new JTable()
        {
            public boolean isCellEditable(int row, int colum)
            {
                if(table.getColumnName(0) == "FeedbackID")
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
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
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

        panel_hk.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Học sinh");

        cb_student.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        cb_student.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_studentItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout panel_hkLayout = new javax.swing.GroupLayout(panel_hk);
        panel_hk.setLayout(panel_hkLayout);
        panel_hkLayout.setHorizontalGroup(
            panel_hkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_hkLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_hkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_hkLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 119, Short.MAX_VALUE))
                    .addComponent(cb_student, 0, 174, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_hkLayout.setVerticalGroup(
            panel_hkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_hkLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(cb_student)
                .addContainerGap())
        );

        panel_class.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Lớp");

        cb_lop.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        cb_lop.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_lopItemStateChanged(evt);
            }
        });
        cb_lop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_lopActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_classLayout = new javax.swing.GroupLayout(panel_class);
        panel_class.setLayout(panel_classLayout);
        panel_classLayout.setHorizontalGroup(
            panel_classLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_classLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(panel_classLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb_lop, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        panel_classLayout.setVerticalGroup(
            panel_classLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_classLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(cb_lop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(panel_class, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(panel_hk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem)
                .addGap(49, 49, 49)
                .addComponent(btnSua)
                .addGap(50, 50, 50)
                .addComponent(btnXoa)
                .addGap(34, 34, 34))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_hk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(panel_class, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCourseActionPerformed
        init_course();
        table.setRowSorter(null);
        btnThem.setVisible(true);
        btnXoa.setVisible(true);
        btnSua.setVisible(true);
        panel_class.setVisible(false);
        panel_hk.setVisible(false);
    }//GEN-LAST:event_btnCourseActionPerformed

    private void btnCanhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCanhanActionPerformed
        a = new thong_tin_ca_nhan(user_id, "nhanvienhocvu");
        a.setVisible(true);
    }//GEN-LAST:event_btnCanhanActionPerformed

    private void btnGradeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGradeActionPerformed
        init_grade();
        set_df();
        Diem();
        btnThem.setVisible(false);
        btnSua.setVisible(false);
        btnXoa.setVisible(false);
        panel_class.setVisible(true);
        panel_hk.setVisible(true);
    }//GEN-LAST:event_btnGradeActionPerformed

    private void btnClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClassActionPerformed
        init_class();
        table.setRowSorter(null);
        btnThem.setVisible(true);
        btnSua.setVisible(true);
        btnXoa.setVisible(true);
        panel_class.setVisible(false);
        panel_hk.setVisible(false);
    }//GEN-LAST:event_btnClassActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        them = true;
        String name = table.getModel().getColumnName(1);
        switch (name) {
            case "ClassID":
                reset_upd_class(them);
                dia_class.setLocationRelativeTo(null);
                dia_class.setVisible(true);
                break;
            case "CourseID":
                reset_upd_course(them);
                dia_course.setLocationRelativeTo(null);
                dia_course.setVisible(true);
                break;
            case "Lớp":
                dia_xep_them.setLocationRelativeTo(null);
                dia_xep_them.setVisible(true);
                break;
            default:
                JOptionPane.showMessageDialog(this,"Vui lòng chọn bảng để thêm");
                break;
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnDangxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangxuatActionPerformed
        dangnhap b = new dangnhap();
        b.setVisible(true);
        if(a != null)
            a.dispose();
        this.dispose();
    }//GEN-LAST:event_btnDangxuatActionPerformed

    private void btnOKClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKClassActionPerformed
        Lop();
        ctlh();
        dia_class.dispose();
    }//GEN-LAST:event_btnOKClassActionPerformed

    private void btnOKCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKCourseActionPerformed
        Khoahoc();
        dia_course.dispose();
    }//GEN-LAST:event_btnOKCourseActionPerformed

    private void cb_lopItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_lopItemStateChanged
        run_fil();
    }//GEN-LAST:event_cb_lopItemStateChanged

    private void cb_studentItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_studentItemStateChanged
        run_fil();
    }//GEN-LAST:event_cb_studentItemStateChanged

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        String name = table.getModel().getColumnName(1);
        DefaultTableModel df = (DefaultTableModel) table.getModel();
        String sql1 ="DELETE FROM lop WHERE ClassID = ?";
        String sql2 = "DELETE FROM course WHERE CourseID = ?";
        String sql3 = "DELETE FROM xeplop WHERE UserID = ? AND ClassID = ?";
        switch (name) {
            case "ClassID":
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
                        } catch (SQLException e) {
                            if (e.getSQLState().equals("20000")) {
                                JOptionPane.showMessageDialog(null, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                            } else 
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                break;
            case "CourseID":
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
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            case "Lớp":
                table.setRowSorter(null);
                cb_lop.setSelectedIndex(0);
                for (int i = table.getRowCount()-1; i >= 0; i--) 
                {
                    Boolean isChecked = (Boolean) table.getValueAt(i, 0);
                    if (isChecked) 
                    {
                        try {
                            PreparedStatement pst = connect.conn.prepareStatement(sql3);
                            pst.setString(1, table.getValueAt(i, 2).toString().substring(0, 5));
                            pst.setString(2, table.getValueAt(i, 1).toString().substring(0, 3));
                            pst.executeUpdate();
                            df.removeRow(i);
                        } catch (Exception e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(this,"xóa lớp thất bại");
                        }
                    }
                }
                break;
            default:
                JOptionPane.showMessageDialog(this,"Vui lòng chọn bảng để thêm");
                break;
        }
    }//GEN-LAST:event_btnXoaActionPerformed

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
                case "ClassID":
                    reset_upd_class(them);
                    dia_class.setLocationRelativeTo(null);
                    dia_class.setVisible(true);
                    break;
                case "CourseID":
                    reset_upd_course(them);
                    dia_course.setLocationRelativeTo(null);
                    dia_course.setVisible(true);
                    break;
                case "Lớp":
                    label_hs_xep_sua.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
                    dia_xep_sua.setLocationRelativeTo(null);
                    dia_xep_sua.setVisible(true);
                    break;
                default:
                    JOptionPane.showMessageDialog(this,"Vui lòng chọn bảng để thêm");
                    break;
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnPhanhoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhanhoiActionPerformed
        init_phanhoi();
        panel_hk.setVisible(false);
        panel_class.setVisible(false);
        btnThem.setVisible(false);
        btnXoa.setVisible(false);
        btnSua.setVisible(false);
        table.setRowSorter(null);
    }//GEN-LAST:event_btnPhanhoiActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        String name = table.getModel().getColumnName(0);
        String us = table.getValueAt(table.getSelectedRow(), 0).toString();
        if(evt.getClickCount() ==2 && name.equals("FeedbackID"))
        {
            read_commit_Phanhoi(us);
            dia_phanhoi.setLocationRelativeTo(null);
            dia_phanhoi.setVisible(true);
        }
    }//GEN-LAST:event_tableMouseClicked

    private void btn_xacnhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xacnhanActionPerformed
        String sql = "UPDATE phanhoi SET FeedbackState= 'Đã xác nhận' WHERE FeedbackID =?";
        try {
            PreparedStatement pst = connect.conn.prepareStatement(sql);
            pst.setString(1, table.getValueAt(table.getSelectedRow(), 0).toString());
            pst.executeUpdate();
            dia_phanhoi.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"lỗi btn xác nhận");
        }
    }//GEN-LAST:event_btn_xacnhanActionPerformed

    private void btnXepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXepActionPerformed
        init_xeplop();
        set_df();
        panel_hk.setVisible(false);
        panel_class.setVisible(true);
        btnThem.setVisible(true);
        btnXoa.setVisible(true);
        btnSua.setVisible(true);
    }//GEN-LAST:event_btnXepActionPerformed

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        for (int i = tb_them_xep.getRowCount()-1; i >= 0; i--) 
        {
            Boolean isChecked = (Boolean) tb_them_xep.getValueAt(i, 0);
            if (isChecked) 
            {
                xeplop(tb_them_xep.getValueAt(i, 1).toString(),i);
            }
        }
    }//GEN-LAST:event_btnthemActionPerformed

    private void cb_lop_xep_themItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_lop_xep_themItemStateChanged
        init_student_xep();
    }//GEN-LAST:event_cb_lop_xep_themItemStateChanged

    private void btncapnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncapnhatActionPerformed
        xeplop("",0);
    }//GEN-LAST:event_btncapnhatActionPerformed

    private void cb_lopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_lopActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_lopActionPerformed

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
            java.util.logging.Logger.getLogger(NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCanhan;
    private javax.swing.JButton btnClass;
    private javax.swing.JButton btnCourse;
    private javax.swing.JButton btnDangxuat;
    private javax.swing.JButton btnGrade;
    private javax.swing.JButton btnOKClass;
    private javax.swing.JButton btnOKCourse;
    private javax.swing.JButton btnPhanhoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXep;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btn_xacnhan;
    private javax.swing.JButton btncapnhat;
    private javax.swing.JButton btnthem;
    private javax.swing.JComboBox<String> cb_Teacher;
    private javax.swing.JComboBox<String> cb_course;
    private javax.swing.JComboBox<String> cb_lop;
    private javax.swing.JComboBox<String> cb_lop_xep_sua;
    private javax.swing.JComboBox<String> cb_lop_xep_them;
    private javax.swing.JComboBox<String> cb_student;
    private javax.swing.JDialog dia_class;
    private javax.swing.JDialog dia_course;
    private javax.swing.JDialog dia_phanhoi;
    private javax.swing.JDialog dia_xep_sua;
    private javax.swing.JDialog dia_xep_them;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel label_hs_xep_sua;
    private javax.swing.JPanel panel_class;
    private javax.swing.JPanel panel_hk;
    private javax.swing.JTable table;
    private javax.swing.JTable tb_them_xep;
    private javax.swing.JTextField txtClassID;
    private javax.swing.JTextField txtClassName;
    private javax.swing.JTextField txtCourseID;
    private javax.swing.JTextField txtCourseName;
    private javax.swing.JTextField txtEnd;
    private javax.swing.JTextField txtMax;
    private javax.swing.JTextField txtNoiDung;
    private javax.swing.JTextField txtRoom;
    private javax.swing.JTextField txtStart;
    private javax.swing.JTextArea txt_phanhoi;
    // End of variables declaration//GEN-END:variables
}
