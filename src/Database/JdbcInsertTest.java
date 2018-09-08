package Database;

import java.sql.*;

public class JdbcInsertTest
{
    public static void main(String[] args)
    {
        try(Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ebookshop", "root", "root");
            Statement statement = connection.createStatement()
            )
        {
            //Delete a record
            String sqlDelete = "delete from books where id>=3000 and id<4000";
            System.out.println("The SQL query is: " + sqlDelete);
            int countDeleted = statement.executeUpdate(sqlDelete);
            System.out.println(countDeleted + " records deleted.\n");

            //Insert a record
            String sqlInsert = "insert into books " +
                    "values (3001,'Gone Fishing', 'Kumar', 11.11 , 11)";
            System.out.println("The SQL query is: " + sqlInsert);
            int countInserted = statement.executeUpdate(sqlInsert);
            System.out.println(countInserted + " records inserted.\n");

            //Insert multiple records
            sqlInsert = "insert into books values " +
                    "(3002, 'Gone Fishing 2', 'Kumar', 22.22, 22)," +
                    "(3003, 'Gone Fishing 3', 'Kumar', 33.33, 33)";
            System.out.println("The SQL query is: " + sqlInsert);
            countInserted  = statement.executeUpdate(sqlInsert);
            System.out.println(countInserted + " records inserted.\n");

            // INSERT a partial record
            sqlInsert = "insert into books (id, title, author) "
                    + "values (3004, 'Fishing 101', 'Kumar')";
            System.out.println("The SQL query is: " + sqlInsert);  // Echo for debugging
            countInserted = statement.executeUpdate(sqlInsert);
            System.out.println(countInserted + " records inserted.\n");

            // Issue a SELECT to check the changes
            String strSelect = "select * from books";
            System.out.println("The SQL query is: " + strSelect);  // Echo For debugging
            ResultSet resultSet = statement.executeQuery(strSelect);
            while(resultSet.next())
            {   // Move the cursor to the next row
                System.out.println(resultSet.getInt("id") + ", "
                        + resultSet.getString("author") + ", "
                        + resultSet.getString("title") + ", "
                        + resultSet.getDouble("price") + ", "
                        + resultSet.getInt("qty"));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
