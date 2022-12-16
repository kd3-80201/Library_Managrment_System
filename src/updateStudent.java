import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.*;

public class updateStudent extends JFrame{
    public JPanel sPanel;
    public JTextField yearField;
    public JTextField naField;
    public JTextField add1;
    public JTextField add2;
    public JTextField birthField;
    public JTextField qualificationField;
    public JTextField qualificationMarkNoField;
    public JTextField tenthField;
    public JTextField twelfthField;
    public JTextField totalMarkField;
    public JTextField outField;
    public JTextField percentField;
    public JTextField mobileField;
    public JTextField parentField;
    public JTextField motherField;
    public JButton clearButton;
    public JButton updateButton;
    public JButton nextButton;
    public JComboBox<String> branchCombo;
    private JComboBox<String> idCombo;
    private JTextField currentBranchField;
    private JTextField stateField;
    private JTextField cityField;
    private JButton previousButton;

    Statement st10,st11,st12,st13;
    Connection conn8,conn9;
    ResultSet rs5,rs6,rs7,rs8;

    public updateStudent(){

        setContentPane(sPanel);
        setVisible(true);
        setSize(700, 500);
        setDefaultCloseOperation(updateStudent.EXIT_ON_CLOSE);
        try {
            conn8 = DriverManager.getConnection("JDBC:mysql://localhost:3306/libraryproject", "root", "AfnanBaig@123");
            conn9 = DriverManager.getConnection("JDBC:mysql://localhost:3306/libraryproject", "root", "AfnanBaig@123");
            st10 = conn8.createStatement();
            st11 = conn8.createStatement();
            st12 = conn8.createStatement();
            st13 = conn9.createStatement();
            rs5 =  st10.executeQuery("select * from student"); //Table of student
            rs6 =  st11.executeQuery("select * from student"); //Table of student
            rs7 =  st12.executeQuery("select * from student"); //Table of student
        }catch (SQLException re){re.printStackTrace();}

        idCombo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                try {
                    while (rs5.next()){
                        idCombo.addItem(rs5.getString("st_id"));
                    }
                } catch (SQLException app) {throw new RuntimeException(app);}
            }
        });
        branchCombo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                try {
                    while (rs7.next()){
                        branchCombo.addItem(rs7.getString("st_course"));
                    }
                } catch (SQLException zex) {throw new RuntimeException(zex);}
            }
        });
        nextButton.addActionListener(e -> {

            try {

                rs6.next();
                    currentBranchField.setText(rs6.getString("st_course"));
                    yearField.setText(rs6.getString("st_year"));
                    naField.setText(rs6.getString("st_name"));
                    add1.setText(rs6.getString("st_add1"));
                    add2.setText(rs6.getString("st_add2"));
                    stateField.setText(rs6.getString("st_state"));
                    cityField.setText(rs6.getString("st_city"));
                    birthField.setText(rs6.getString("st_dob"));
                    qualificationField.setText(rs6.getString("st_qualification"));
                    qualificationMarkNoField.setText(rs6.getString("st_qmno"));
                    tenthField.setText(rs6.getString("st_qtenmno"));
                    twelfthField.setText(rs6.getString("st_qtwemno"));
                    totalMarkField.setText(rs6.getString("st_total"));
                    outField.setText(rs6.getString("st_outof"));
                    percentField.setText(rs6.getString("st_per"));
                    mobileField.setText(rs6.getString("st_mob"));
                    parentField.setText(rs6.getString("st_parentname"));
                    motherField.setText(rs6.getString("st_mothername"));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        clearButton.addActionListener(e -> {
            try {
               // String year,name,address1,address2,st,cty,dob,qual,mobileNo,parent,mother,course;
                String ff;
                JOptionPane.showMessageDialog(null, "Processing...");
                String idStudent = idCombo.getItemAt(idCombo.getSelectedIndex());
                ff = "DELETE FROM student WHERE  st_id ='"+idStudent+"'";

                st11.executeUpdate(ff);

            }catch (SQLException you){you.printStackTrace();}

            currentBranchField.setText("");
            yearField.setText("");
            naField.setText("");
            add1.setText("");
            add2.setText("");
            stateField.setText("");
            cityField.setText("");
            birthField.setText("");
            qualificationField.setText("");
            qualificationMarkNoField.setText("");
            tenthField.setText("");
            twelfthField.setText("");
            totalMarkField.setText("");
            outField.setText("");
            percentField.setText("");
            mobileField.setText("");
            parentField.setText("");
            motherField.setText("");
        });
        updateButton.addActionListener(e -> {
        String year,name,address1,address2,st,cty,dob,qual,mobileNo,parent,mother,z;
        int qualificationMarks,tenthMarks,twelfthMarks,totalMarks,outOfMarks;
        double per;
        try {
            JOptionPane.showMessageDialog(null, "Done");
            year = yearField.getText(); //All are string Fields
            name = naField.getText();
            address1 = add1.getText();
            address2 = add2.getText();
            st = stateField.getText();
            cty = cityField.getText();
            dob = birthField.getText();
            qual = qualificationField.getText();
            parent = parentField.getText();
            mother = motherField.getText();
            mobileNo =mobileField.getText();
            //  you should find the solution for storing combo box which is done before (course)
            qualificationMarks = Integer.parseInt(qualificationMarkNoField.getText());   //All are Integers fields

            tenthMarks = Integer.parseInt(tenthField.getText());
            twelfthMarks = Integer.parseInt(twelfthField.getText());
            totalMarks = Integer.parseInt(totalMarkField.getText());
            outOfMarks = Integer.parseInt(outField.getText());

            per = Double.parseDouble(percentField.getText()); //Double field

            String idStudent = idCombo.getItemAt(idCombo.getSelectedIndex());
            String courseStudent = branchCombo.getItemAt(branchCombo.getSelectedIndex());
            z = "UPDATE student SET st_name ='" + name + "',st_add1 ='" + address1 + "',st_add2 ='" + address2 + "',st_city ='" + cty + "',st_state ='" + st + "',st_dob ='" + dob + "',st_qualification='" + qual + "',st_qmno =" + qualificationMarks + ",st_qtenmno ="+tenthMarks+",st_qtwemno ="+twelfthMarks+",st_total="+totalMarks+",st_outof =" + outOfMarks+",st_per =" + per + ",st_mob =" + mobileNo + ",st_parentname ='" + parent + "',st_mothername ='" + mother + "',st_course ='" + courseStudent + "',st_year ='" + year + "' WHERE  st_id ='"+idStudent+"'";

            st11.executeUpdate(z);

        }catch (SQLException yu){yu.printStackTrace();}


        });
        idCombo.addActionListener(e -> {
            String jj1;
            jj1= idCombo.getItemAt(idCombo.getSelectedIndex());
            //JOptionPane.showMessageDialog(null, jj1);
            try {
                rs8=st13.executeQuery("select * from student where st_id='"+jj1+"'"); //Table of student
                rs8.next();
                currentBranchField.setText(rs8.getString("st_course"));
                yearField.setText(rs8.getString("st_year"));
                naField.setText(rs8.getString("st_name"));
                add1.setText(rs8.getString("st_add1"));
                add2.setText(rs8.getString("st_add2"));
                stateField.setText(rs8.getString("st_state"));
                cityField.setText(rs8.getString("st_city"));
                birthField.setText(rs8.getString("st_dob"));
                qualificationField.setText(rs8.getString("st_qualification"));
                qualificationMarkNoField.setText(rs8.getString("st_qmno"));
                tenthField.setText(rs8.getString("st_qtenmno"));
                twelfthField.setText(rs8.getString("st_qtwemno"));
                totalMarkField.setText(rs8.getString("st_total"));
                outField.setText(rs8.getString("st_outof"));
                percentField.setText(rs8.getString("st_per"));
                mobileField.setText(rs8.getString("st_mob"));
                parentField.setText(rs8.getString("st_parentname"));
                motherField.setText(rs8.getString("st_mothername"));

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        previousButton.addActionListener(e -> {


        });
    }

    public static void main(String[] args) {
        new updateStudent();
    }
}
