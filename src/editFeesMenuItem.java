import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Objects;



public class editFeesMenuItem extends JFrame implements ActionListener {
    public JButton upd;
    public JPanel editpanel;
    public JComboBox courseBox;
    public JTextField feesField;
    public JTextField updatefeeField;
    public JPasswordField passwordField1;
    public JButton btn;
    public JPanel Invisible;

    Statement st5,st6,st7;
    Connection conn5;
    ResultSet rs5,rs6;

    public  editFeesMenuItem(){
        setContentPane(editpanel);
        setVisible(true);
        setSize(800,400);
        setDefaultCloseOperation(editFeesMenuItem.EXIT_ON_CLOSE);
        try {
            conn5 = DriverManager.getConnection("JDBC:mysql://localhost:3306/libraryproject", "root", "AfnanBaig@123");
            st5 = conn5.createStatement();
            rs5 = st5.executeQuery("select * from fees");       //Table of fees

            st6 = conn5.createStatement();
            st7 = conn5.createStatement();

            btn.addActionListener(e -> {
                if (Objects.equals(passwordField1.getText(), "123")) {
                    Invisible.setVisible(true);

                }

            });
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        courseBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                try {
                    while (rs5.next())
                    {
                        courseBox.addItem(rs5.getString("Course_Name"));
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        feesField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                String temp = (String) courseBox.getItemAt(courseBox.getSelectedIndex());
                try {
                    rs6 =st6.executeQuery("select * from fees where Course_Name='"+temp+"'");
                    while (rs6.next()) {
                        feesField.setText(rs6.getString("Total_Fees"));
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        upd.addActionListener(e -> {
            String core = (String) courseBox.getItemAt(courseBox.getSelectedIndex());
            int upp = Integer.parseInt(updatefeeField.getText()); // for update textfield for new fees of student
            try {
               st7.executeUpdate("update fees set Total_Fees="+upp+" where Course_Name='"+core+"'");

               updatefeeField.setText(" ");

            } catch (SQLException ex) {
                ex.printStackTrace();
                //throw new RuntimeException(ex);
            }
        });
    }
    public static void main(String[] args) {
        editFeesMenuItem we =new editFeesMenuItem();
            }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

