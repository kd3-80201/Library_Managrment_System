import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.*;

public class returnMenuItem extends JFrame {
    Statement st20,st21;
    Connection conn14;
    ResultSet rs15,rs16;
    private JPanel rtnPanel;
    private JComboBox<String> nameComboBox;
    private JTextField dateIssueNameField;
    private JTextField bookSerialNoTextField;
    private JTextField subjectTextField;
    private JButton returnButton;

    public returnMenuItem(){
        setContentPane(rtnPanel);
        setVisible(true);
        setSize(700, 500);
        setDefaultCloseOperation(deleteMenuItem1.EXIT_ON_CLOSE);
        try {
            conn14= DriverManager.getConnection("JDBC:mysql://localhost:3306/libraryproject","root","AfnanBaig@123");
            st20=conn14.createStatement();
//            st21=conn14.createStatement();
        } catch (Exception e) {throw new RuntimeException(e);}

        nameComboBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                try {
                    rs15=st20.executeQuery("Select * from issuetable");
                    while (rs15.next()){
                        nameComboBox.addItem(rs15.getString("student_Id"));
                    }
                } catch (SQLException ex) {throw new RuntimeException(ex);}
            }
        });

        returnButton.addActionListener(e -> {


        });
        nameComboBox.addActionListener(e -> {
            try {
                String na;
                na= nameComboBox.getItemAt(nameComboBox.getSelectedIndex());
                st21=conn14.createStatement();
                rs16 = st21.executeQuery("select * from issuetable where student_Id='"+na+"'");
                rs16.next();

                dateIssueNameField.setText(rs15.getString("dateOfIssue"));
                bookSerialNoTextField.setText(rs15.getString("bookSerialNo"));
                subjectTextField.setText(rs15.getString("bookSubject"));
            } catch (SQLException ex) {throw new RuntimeException(ex);}
        });
    }

    public static void main(String[] args) {
        new returnMenuItem();
    }
}
