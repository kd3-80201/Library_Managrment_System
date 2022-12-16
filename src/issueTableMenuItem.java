import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.*;

public class issueTableMenuItem extends JFrame {
    private JPanel issueTablePanel;
    private JTextField nameField;
    private JTextField bookNameTextField;
    private JTextField bookSubjectTextField;
    private JTextField dateOfIssue;
    private JButton issueButton;
    private JTextField yearField;
    private JComboBox<String> idComboBox;
    private JComboBox<String> bookComboBox;

    Statement st16,st17,st18,st19;
    Connection conn12,conn13;
    ResultSet rs11,rs12,rs13,rs14;

    public issueTableMenuItem(){
         setContentPane(issueTablePanel);
        setVisible(true);
        setSize(700, 500);
        setDefaultCloseOperation(issueTableMenuItem.EXIT_ON_CLOSE);
        try {
            conn12= DriverManager.getConnection("JDBC:mysql://localhost:3306/libraryproject", "root", "AfnanBaig@123");
            conn13= DriverManager.getConnection("JDBC:mysql://localhost:3306/libraryproject", "root", "AfnanBaig@123");
            st16=conn12.createStatement();
            st17=conn12.createStatement();
            st18=conn13.createStatement();
            st19=conn12.createStatement();
            rs11=st16.executeQuery("select * from student");
        } catch (Exception e) {throw new RuntimeException(e);}


        idComboBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                try {
                    while (rs11.next()){
                        idComboBox.addItem(rs11.getString("st_id"));
                    }
                } catch (SQLException ex) {throw new RuntimeException(ex);}

            }
        });
        idComboBox.addActionListener(e -> {
           String cd;
           cd=idComboBox.getItemAt(idComboBox.getSelectedIndex());
           try {
               rs12=st17.executeQuery("select * from student where st_id='"+cd+"'");
               rs12.next();
               nameField.setText(rs12.getString("st_name"));
               yearField.setText(rs12.getString("st_year"));
           } catch (SQLException ex) {throw new RuntimeException(ex);}

        });

        bookComboBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                try {
                    rs13=st18.executeQuery("select * from stock");
                    while (rs13.next()){
                        bookComboBox.addItem(rs13.getString("bookserialno"));
                    }

                } catch (SQLException ex) {throw new RuntimeException(ex);}

            }
        });
        bookComboBox.addActionListener(e -> {
            String bookSerial;
            bookSerial=bookComboBox.getItemAt(bookComboBox.getSelectedIndex());
            try {
                rs13=st18.executeQuery("select * from stock where bookserialno='"+bookSerial+"'");
                rs13.next();
                bookNameTextField.setText(rs13.getString("bookname"));
                bookSubjectTextField.setText(rs13.getString("subject"));
            } catch (SQLException ex) {throw new RuntimeException(ex);}
        });
        issueButton.addActionListener(e -> {
            try {


                String idBox, serialBox, bookName, bookSubject, dof, zz,yy;

                idBox = idComboBox.getItemAt(idComboBox.getSelectedIndex());
                serialBox = bookComboBox.getItemAt(bookComboBox.getSelectedIndex());
                bookName = bookNameTextField.getText();
                bookSubject = bookSubjectTextField.getText();
                dof = dateOfIssue.getText();

                rs14=st19.executeQuery("select * from issuetable where student_Id=" + idBox + "");

                int ww;
                ww= rs11.getRow();
                ww=0;
                while (rs14.next()){
                    ww=ww+1;
                }
                if (ww > 2) {

                    JOptionPane.showMessageDialog(null, "Please return the books that you have issued before");
                }
                else {
                    zz = "INSERT INTO issuetable (student_Id,dateOfIssue,bookName,bookSubject,bookSerialNo)VALUES(" + idBox + ",'" + dof + "','" + bookName + "','" + bookSubject + "','" + serialBox + "')";
                    st18.executeUpdate(zz);
                }

            } catch (SQLException ex) {throw new RuntimeException(ex);}
        });
    }

    public static void main(String[] args) {
        new issueTableMenuItem();
    }
}