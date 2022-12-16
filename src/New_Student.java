import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class New_Student extends JFrame implements ActionListener {
    public JPanel jp;
    public JTextField idfield;
    public JTextField namefieled;
    public JTextField address1Field;
    public JTextField address2Field;
    public JTextField cityField;
    public JPanel AdityaPanel;
    public JPanel Firstpanel;
    public JPanel Thirdpanel;
    public JTextField DOBField;
    public JPanel Fourthpanel;
    public JTextField QualificationField;
    public JTextField MarksField;
    public JPanel Fifthpanel;
    public JTextField TenthField;
    public JTextField TwelvethField;
    public JPanel SixthPanel;
    public JTextField TotalField;
    public JTextField Outofield;
    public JPanel SeventhField;
    public JTextField percentageField;
    public JTextField MnoField;
    public JTextField ParentField;
    public JTextField MotherNameField;
    public JTextField CourseField;
    public JTextField YearField;
    public JTextField StudentTotalField;
    public JButton OKayButton;
    JComboBox jc;
    JComboBox jc2;
    JComboBox coursecombo;
    public JButton clearButton;


    Statement st, st4;
    Connection conn;
    ResultSet rs, rs2, rs3, rs4;

    //    String k; //Remember to use combobox in the try catch  k=jc.getItemAt(jc.getselectedindex)
    public New_Student() {
        setContentPane(jp);
        setVisible(true);
        setSize(400, 400);
        setDefaultCloseOperation(New_Student.EXIT_ON_CLOSE);

        try {

            conn = DriverManager.getConnection("JDBC:mysql://localhost:3306/libraryproject", "root", "AfnanBaig@123");
            st = conn.createStatement();
            st4 = conn.createStatement();
            rs = st.executeQuery("select * from student");       //Table of student
            rs2 = st.executeQuery("select * from stateandcity"); //Table of stateandcity
            rs4 = st4.executeQuery("select * from fees");        //Table of Fees


        } catch (SQLException tex) {
            tex.printStackTrace();
        }

        OKayButton.addActionListener(e -> {
            String na, add1, add2, city, Dob, qual, parentname, mothername, course, mob;
            int id, qualmarks, tenmarks, twelvemarks, total, outof, year, studentfee;
            double per;

            try {

                // k = (String) jc.getItemAt(jc.getSelectedIndex());  //Remember to use combobox in the try catch

                // conn = DriverManager.getConnection("JDBC:mysql://localhost:3306/libraryproject", "root", "AfnanBaig@123"); //whether to check the programme is running or not


                //rs = st.executeQuery("select * from student");       //Table of student
                //rs2 = st.executeQuery("select * from stateandcity"); //Table of stateandcity


                na = namefieled.getText(); //these all are of strings
                add1 = address1Field.getText();
                add2 = address2Field.getText();
                //city = cityField.getText();
                Dob = DOBField.getText();
                qual = QualificationField.getText();
                parentname = ParentField.getText();
                mothername = MotherNameField.getText();
               // course = CourseField.getText();
                mob = MnoField.getText().trim();


                id = Integer.parseInt(idfield.getText().trim()); //these all are of Integers txtValor.getText().trim()
                qualmarks = Integer.parseInt(MarksField.getText());
                tenmarks = Integer.parseInt(TenthField.getText());
                twelvemarks = Integer.parseInt(TwelvethField.getText());
                total = Integer.parseInt(TotalField.getText());
                outof = Integer.parseInt(Outofield.getText());
                //mob = Integer.parseInt(MnoField.getText());
                year = Integer.parseInt(YearField.getText());
                studentfee = Integer.parseInt(StudentTotalField.getText());

                per = Double.parseDouble(percentageField.getText());

                String cityjc2= (String) jc2.getItemAt(jc2.getSelectedIndex());
                String statejc= (String) jc.getItemAt(jc.getSelectedIndex());
                String coursecmp= (String) coursecombo.getItemAt(coursecombo.getSelectedIndex());
                String q;
                 //q = "INSERT INTO student (st_id,st_name) values(" + id + ",'"+na+"')"; //just to check if the programme is running or not

                 //q = "INSERT INTO stateandcity (state,city) values('"+na+"','"+city+"')"; //just to check if the programme is running or not //just to check the second table is working or not but it iS not rn

                q = "INSERT INTO  student (st_id,st_name,st_add1,st_add2,st_city,st_state,st_dob,st_qualification,st_qmno,st_qtenmno,st_qtwemno,st_total,st_outof,st_per,st_mob,st_parentname,st_mothername,st_course,st_year,st_totalfee) VALUES (" + id + ",'" + na + "','" + add1 + "','" + add2 + "','" + cityjc2 + "','" + statejc + "','" + Dob + "','" + qual + "','" + qualmarks + "'," + tenmarks + "," + twelvemarks + "," + total + "," + outof + ",'" + per + "','" + mob + "','" + parentname + "','" + mothername + "','" + coursecmp + "','" + year + "','" + studentfee + "')";
                //,'" + k + "' is erased from above cuz we need k in combobox(k was in between city or DOB

                st.executeUpdate(q);

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        jc.addFocusListener(new FocusAdapter() {


            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                //here we have to build the connection between the SQL table of name stateandcity
                // String stat, cty;   //for state and city of the table name sateandcity
                try {
                    while (rs2.next()) {

                        jc.addItem(rs2.getString("state"));
                    }

                } catch (SQLException ec) {
                    ec.printStackTrace();
                }
            }
        });


        jc2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                try {
                    String k = (String) jc.getItemAt(jc.getSelectedIndex());
                    rs3 = st.executeQuery("select * from stateandcity where state='" + k + "'");
                    while (rs3.next()) {

                        jc2.addItem(rs3.getString("city"));
                    }

                } catch (SQLException ec) {
                    ec.printStackTrace();
                }
            }
        });
        Outofield.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                int ad = Integer.parseInt(TotalField.getText()); //for total field
                int cd = Integer.parseInt(Outofield.getText()); //for out of field
                if (ad > cd) {
                    JOptionPane.showMessageDialog(null, "total marks is greater than out of marks");
                    TotalField.setText("");
                    TotalField.requestFocus();
                }
            }
        });
        coursecombo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                try {
                    while (rs4.next()) {
                        coursecombo.addItem(rs4.getString("Course_Name"));
                    }

                } catch (SQLException wec) {wec.printStackTrace();}

            }
        });
        StudentTotalField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                try {
                    String tuition = (String) coursecombo.getItemAt(coursecombo.getSelectedIndex());
                    rs4 = st4.executeQuery("select * from fees where Course_Name='"+tuition+"'");
                    while (rs4.next()) {
                        StudentTotalField.setText(rs4.getString("Total_Fees"));
                    }

                } catch (SQLException ec) {
                    ec.printStackTrace();
                }
            }
        });
        clearButton.addActionListener(e -> {
            namefieled.setText(" ");
            idfield.setText(" ");
            address1Field.setText(" ");
            address2Field.setText(" ");
            cityField.setText(" ");
            DOBField.setText(" ");
            QualificationField.setText(" ");
            MarksField.setText(" ");
            TenthField.setText(" ");
            TwelvethField.setText(" ");
            TotalField.setText(" ");
            Outofield.setText(" ");
            percentageField.setText(" ");
            MnoField.setText(" ");
            ParentField.setText(" ");
            MotherNameField.setText(" ");
            CourseField.setText(" ");
            YearField.setText(" ");
            StudentTotalField.setText(" ");
        });
    }


    public static void main(String[] args) {
        new New_Student();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
