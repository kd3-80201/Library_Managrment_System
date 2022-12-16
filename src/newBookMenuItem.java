import javax.swing.*;
import java.sql.*;

public class newBookMenuItem extends JFrame {
    public JPanel BookPanel;
    public JTextField Bookno;
    public JTextField Dep;
    public JTextField sub;
    public JTextField naBook;
    public JTextField Author;
    public JTextField pub;
    public JTextField Price;
    public JTextField Serial;
    public JTextField Dealer;
    public JTextField purchase;
    public JTextField Rack;
    public JTextField Building;
    public JButton button1;
    public JButton clearButton;

    Statement  st9;
    Connection conn7;

    public newBookMenuItem()
    {
        setContentPane(BookPanel);
        setVisible(true);
        setSize(700, 500);
        setDefaultCloseOperation(newBookMenuItem.EXIT_ON_CLOSE);
        //setResizable(false);
        try {
            conn7 = DriverManager.getConnection("JDBC:mysql://localhost:3306/libraryproject", "root", "AfnanBaig@123");
            //st9 = conn7.createStatement();
            button1.addActionListener(e -> {
                int buildNo,prc,rackNo;
                String bookSerial,subject,dep,nameBook,auth,publication,clgSerial,dealer,dateOf;
                try {
                    JOptionPane.showMessageDialog(null,"Been Saved");
                    buildNo=Integer.parseInt(Building.getText().trim());
                    prc = Integer.parseInt(Price.getText());
                    rackNo = Integer.parseInt(Rack.getText());

                    bookSerial=Bookno.getText();
                    subject= sub.getText();
                    dep=Dep.getText();
                    nameBook=naBook.getText();
                    auth=Author.getText();
                    publication=pub.getText();
                    clgSerial=Serial.getText();
                    dateOf=purchase.getText();
                    dealer=Dealer.getText();

                    String so;
                    so="INSERT INTO stock(bookserialno,subject,department,bookname,author,publication,price,college_serialno,dealer,building,rackno,date_purchase)VALUES('"+bookSerial+"','"+subject+"','"+dep+"','"+nameBook+"','"+auth+"','"+publication+"','"+prc+"','"+clgSerial+"','"+dealer+"','"+buildNo+"','"+rackNo+"',"+dateOf+")";

                    //so ="Insert into stock(building) VALUES ("+buildNo+")";
                    st9 = conn7.createStatement();
                    st9.executeUpdate(so);


                }catch (SQLException yu) {yu.printStackTrace();
                }
            });
        }catch (SQLException ate) {
        ate.printStackTrace();}

        clearButton.addActionListener(e -> {
             Bookno.setText(" ");
             
             sub.setText(" ");
             Dep.setText(" ");
             naBook.setText(" ");
             Author.setText(" ");
             pub.setText(" ");
             Price.setText(" ");
             Serial.setText(" ");
             purchase.setText(" ");
             Dealer.setText(" ");
             Building.setText(" ");
             Rack.setText(" ");
        });
    }

    public static void main(String[] args) {
        newBookMenuItem bk =new newBookMenuItem();
    }
}
