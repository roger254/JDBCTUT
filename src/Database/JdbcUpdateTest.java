package Database;

import java.sql.*;

public class JdbcUpdateTest
{
    public static void main(String[] args)
    {
        try(
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookshop", "root", "root");
                Statement statement = connection.createStatement()
                )
        {
           //Execute a SQL UPDATE via executeUpdate() which returns an int indicating the number of rows affected
            //Increase the price by 7% and qty by 1 for id = 1001
            String strUpdate = "update books set qty = 0 where title = 'A Teaspoon of Java'";
            System.out.println("The SQL query is: " + strUpdate);
            int countUpdated = statement.executeUpdate(strUpdate);
            System.out.println(countUpdated + " records affected.");

            //issue a select to check the update
            String strSelect = "select * from books where title = 'A Teaspoon of Java'";
            System.out.println("The SQL query is: " + strSelect);
            ResultSet resultSet = statement.executeQuery(strSelect);
            while (resultSet.next())
            {
                System.out.println(resultSet.getInt("id") + ", " +
                         resultSet.getString("author") + ", " +
                         resultSet.getString("title") + ", " +
                         resultSet.getDouble("price") + ", " +
                         resultSet.getInt("qty"));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
