package Database;

import java.sql.*;

public class JdbcCommitTest
{
    public static void main(String[] args)
    {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ebookshop", "root", "root");
             Statement statement = connection.createStatement()
        )
        {
            // Step 3 & 4: Execute queries and process the query results
            // Disable auto-commit for the connection, which commits every SQL statement.
            connection.setAutoCommit(false);

            // Before Changes
            ResultSet rset = statement.executeQuery("select id, qty from books where id in (1001, 1002)");
            System.out.println("-- Before UPDATE --");
            while (rset.next())
            {
                System.out.println(rset.getInt("id") + ", " + rset.getInt("qty"));
            }
            connection.commit();     // Commit SELECT

            // Issue two UPDATE statements through executeUpdate()
            statement.executeUpdate("update books set qty = qty + 1 where id = 1001");
            statement.executeUpdate("update books set qty = qty + 1 where id = 1002");
            connection.commit();     // Commit UPDATES

            rset = statement.executeQuery("select id, qty from books where id in (1001, 1002)");
            System.out.println("-- After UPDATE and Commit --");
            while (rset.next())
            {
                System.out.println(rset.getInt("id") + ", " + rset.getInt("qty"));
            }
            connection.commit();     // Commit SELECT

            // Issue two UPDATE statements through executeUpdate()
            statement.executeUpdate("update books set qty = qty - 99 where id = 1001");
            statement.executeUpdate("update books set qty = qty - 99 where id = 1002");
            connection.rollback();   // Discard all changes since the last commit

            rset = statement.executeQuery("select id, qty from books where id in (1001, 1002)");
            System.out.println("-- After UPDATE and Rollback --");
            while (rset.next())
            {
                System.out.println(rset.getInt("id") + ", " + rset.getInt("qty"));
            }
            connection.commit();     // Commit SELECT
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
