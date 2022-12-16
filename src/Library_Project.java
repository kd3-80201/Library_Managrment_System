import javax.swing.*;

public class Library_Project extends JFrame{
    public JMenuBar jmbr;
    public JMenu studentMenu;
    public JMenu stockMenu;
    public JMenu issueMenu;
    public JMenu adminMenu;
    public JMenuItem newStudentMenuItem;
    public JMenuItem updateMenuItem;
    public JMenuItem deleteMenuItem;
    public JMenuItem reportMenuItem;
    public JMenuItem newBookMenuItem;
    public JMenuItem editMenuItem;
    public JMenuItem deleteMenuItem1;
    public JMenuItem damageMenuItem;
    public JMenuItem reportMenuItem1;
    public JMenuItem issueMenuItem;
    public JMenuItem returnMenuItem;
    public JMenuItem availabilityMenuItem;
    public JMenuItem reportMenuItem2;
    public JMenuItem passwordSettingMenuItem;
    public JPanel jpanel;
    public JMenuItem editFeesMenuItem;
    public JMenuItem newCourseMenuItem;
    public JMenuItem issueTableMenuItem;


    public Library_Project() {
        setContentPane(jpanel);
        setVisible(true);
        setSize(400,400);
        setDefaultCloseOperation(Library_Project.EXIT_ON_CLOSE);
        newStudentMenuItem.addActionListener(e -> {
            //JOptionPane.showMessageDialog(null,"okay");
             new New_Student();

        });

        editFeesMenuItem.addActionListener(e -> {
           //JOptionPane.showMessageDialog(null,"okay");
            new  editFeesMenuItem();
        });
        deleteMenuItem1.addActionListener(e ->{
            new deleteMenuItem1();
        });
        newCourseMenuItem.addActionListener(e -> {
            //JOptionPane.showMessageDialog(null,"okay");
            new  newCourseMenuItem();
        });
        newBookMenuItem.addActionListener(e -> {
            //JOptionPane.showMessageDialog(null,"okay");
            new   newBookMenuItem();
        });
        issueTableMenuItem.addActionListener(e-> {
            new issueTableMenuItem();
        });
        returnMenuItem.addActionListener(e->{
            new returnMenuItem();
        });


    }

    public static void main(String[] args) {
        Library_Project lib=new Library_Project();

    }

}
