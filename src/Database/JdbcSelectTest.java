package Database;

import java.sql.*;

public class JdbcSelectTest
{

    public static void main(String[] args)
    {
        try (
                //Step 1: Allocate a database 'Connection' object
                //Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/ebookshop", "root", "root");
                //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookshop?useSSL=false","roger","r15arch");
                //MySQL: "jdbc:mysql://hostname:port/databaseName","username","password
                //Step 2: Allocate a 'Statement' object in the Connection
                Statement statement = connection.createStatement()
        )
        {
            //Step 3: Execute a SQL SELECT query, the query result
            //is returned in a 'ResultSet' Object
            String strSelect = "select title, author,price, qty from books where author = 'Tan Ah Teck 'or price >= 30 order by price desc, id asc";
            System.out.println("The SQL query is: " + strSelect);
            System.out.println();

            ResultSet resultSet = statement.executeQuery(strSelect);

            //Step 4 : Process the ResultSet by scrolling the cursor forward via next().
            //For each row, retrieve the contents of the cells with getXxx(columnName).
            System.out.println("The records selected are:");
            int rowCount = 0;
            while (resultSet.next())
            {
                //move the cursor to the next row, return false if no more row
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                double price = resultSet.getDouble("price");
                int qty = resultSet.getInt("qty");
                System.out.println(title + ", " + author + ", " + price + ", " + qty);
                ++rowCount;
            }
            System.out.println("Total number of records = " + rowCount);
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        //Step 5: Close the resources -Done automatically by try-with-resources
    }
}
