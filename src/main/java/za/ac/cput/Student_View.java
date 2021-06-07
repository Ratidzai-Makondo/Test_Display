package za.ac.cput;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;




public class Student_View extends JFrame {
    private static Connection connect = null;
    private static  Statement statement = null;
    private static ResultSet resultSet = null;
    private static  String url = " jdbc:mysql://localhost:3306/connect_database" ;
    private static String user = "root";
    private static String pass = "";

    DefaultTableModel model = new DefaultTableModel();
    JTable jtable = new JTable(model);
    JPanel panel = new JPanel();
    Container co = this.getContentPane();

    public Student_View() {
        co.setLayout(new BorderLayout());
        model.addColumn("id");
        model.addColumn("firstname");
        model.addColumn("lastname");
        model.addColumn("course_name");

        try {

           Class.forName("com.mysql.cj.jdbc.Driver");
           connect = DriverManager.getConnection(url,user, pass);
           //PreparedStatement ps = con.prepareStatement("select * from student_information");
           statement = connect.createStatement();
             resultSet = statement.executeQuery("select* from adult_table");

             while(resultSet.next()){ model.addRow(new Object[]{resultSet.getInt(1),resultSet.getString(2),
                       resultSet.getString(3), resultSet.getString(4)});

           }
         } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    JScrollPane pg = new JScrollPane(jtable);
        co.add(pg);
        this.pack();

}
    public static void main(String[] args) {

         JFrame fr = new Student_View();
        fr.setTitle("Student Database Example");
        //fr.setSize(700,600);//
        fr.setLocationRelativeTo(null);
        fr.setLayout(new BorderLayout());
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}





