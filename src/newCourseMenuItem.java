import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Objects;

public class newCourseMenuItem extends JFrame implements ActionListener {
    public JTextField crsField;
    public JTextField feesField;
    public JButton saveButton;
    public JPanel mainPanel;
    public JPasswordField passwordField1;
    public JButton button1;
    public JPanel ghost;
    Statement st8;
    Connection conn6;
    ResultSet rs7;


public newCourseMenuItem() {
    setContentPane(mainPanel);
    setVisible(true);
    setSize(400, 400);
    setDefaultCloseOperation(newCourseMenuItem.DISPOSE_ON_CLOSE);
    try {
        conn6 = DriverManager.getConnection("JDBC:mysql://localhost:3306/libraryproject", "root", "AfnanBaig@123");
        st8 = conn6.createStatement();
        rs7 = st8.executeQuery("select * from fees");


    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    button1.addActionListener(e -> {
        if (Objects.equals(passwordField1.getText(), "123")) {
            ghost.setVisible(true);

        }

    });

    saveButton.addActionListener(e -> {
        int fe;
        String crs,laz;
        crs=crsField.getText().trim();
        fe=Integer.parseInt(feesField.getText());

        try {
            laz="INSERT Into Fees (Course_Name,Total_Fees) VALUES('"+crs+"',"+fe+")";

            st8.executeUpdate(laz);

            crsField.setText("");
            feesField.setText("");
        } catch (SQLException gx) {
            throw new RuntimeException(gx);}


    });
}

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        newCourseMenuItem co = new newCourseMenuItem();
    }
}
