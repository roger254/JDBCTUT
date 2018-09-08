package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcBatchProcessingTest
{
    public static void main(String[] args)
    {
        try
                (
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookshop", "root", "root");
                        Statement statement = connection.createStatement();
                )

        {
            //Turn off auto-commit for each SQL statement
            connection.setAutoCommit(false);

            statement.addBatch("insert into books values (8001,'Java ABC', 'Kevin Jones',1.1,99)");
            statement.addBatch("insert into books values (8002,'Java XYZ', 'Kevin Jones',1.1,99)");
            statement.addBatch("update books set price = 11.11 where id=8001 or id=8002");
            int [] returnCodes = statement.executeBatch();

            System.out.print("Return codes are: ");
            for (int code : returnCodes)
                System.out.print(code + ", ");
            System.out.println();

            //commit SQL statements
            connection.commit();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
