import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.*;

public class deleteMenuItem1 extends JFrame {
    public JTextField textField2;
    public JTextField textField3;
    public JTextField textField4;
    public JTextField textField5;
    public JTextField textField6;
    public JTextField textField7;
    public JTextField textField8;
    public JTextField textField9;
    public JTextField textField10;
    public JTextField textField11;
    public JTextField textField12;
    public JButton deleteButton;
    public JPanel deletePanel;
    public JComboBox<String> serialCombo;

    Statement st14, st15;
    Connection conn10, conn11;
    ResultSet rs9, rs10;

    public deleteMenuItem1() {
        setContentPane(deletePanel);
        setVisible(true);
        setSize(700, 500);
        setDefaultCloseOperation(deleteMenuItem1.EXIT_ON_CLOSE);

        try {
            conn10 = DriverManager.getConnection("JDBC:mysql://localhost:3306/libraryproject", "root", "AfnanBaig@123");
            st14 = conn10.createStatement();
            rs10 = st14.executeQuery("Select * from Stock");
            conn11 = DriverManager.getConnection("JDBC:mysql://localhost:3306/libraryproject", "root", "AfnanBaig@123");
            st15 = conn11.createStatement();
        } catch (SQLException e) {throw new RuntimeException(e);}

        serialCombo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                try {
                    while (rs10.next()) {
                        serialCombo.addItem(rs10.getString("bookserialno"));
                    }
                } catch (SQLException ex) {throw new RuntimeException(ex);}
            }
        });

        serialCombo.addActionListener(e -> {
            String ty;
            ty = serialCombo.getItemAt(serialCombo.getSelectedIndex());
            try {
                // JOptionPane.showMessageDialog(null,"okay");
                rs9 = st14.executeQuery("Select * from Stock where bookserialno='"+ty+"'"); //Here Information will be shown which is stored in a workbench
                rs9.next();
                textField2.setText(rs9.getString("department"));
                textField3.setText(rs9.getString("subject"));
                textField4.setText(rs9.getString("bookname"));
                textField5.setText(rs9.getString("author"));
                textField6.setText(rs9.getString("publication"));
                textField7.setText(rs9.getString("price"));
                textField8.setText(rs9.getString("college_serialno"));
                textField9.setText(rs9.getString("dealer"));
                textField10.setText(rs9.getString("building"));
                textField11.setText(rs9.getString("rackno"));
                textField12.setText(rs9.getString("date_purchase"));
            } catch (SQLException io) {
                throw new RuntimeException(io);
            }
        });

        deleteButton.addActionListener(e -> {
            try {
                String dd;
                String serialNO = serialCombo.getItemAt(serialCombo.getSelectedIndex());
                 int result = JOptionPane.showConfirmDialog(null,"Sure? You want to delete?", "Swing Tester",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                            if(result == JOptionPane.YES_OPTION){
                dd = "Delete from stock where bookserialno='" + serialNO + "'";
                st15.executeUpdate(dd);}
            } catch (HeadlessException | SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public static void main(String[] args) {
        new deleteMenuItem1();
    }
}
