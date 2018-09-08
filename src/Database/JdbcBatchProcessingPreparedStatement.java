package Database;

import java.sql.*;

public class JdbcBatchProcessingPreparedStatement
{
    public static void main(String[] args)
    {
        try
                (
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookshop", "root", "root");
                        PreparedStatement preparedStatement = connection.prepareStatement("insert into books values (?, ?, ? ,? ,?);")
                )
        {
            //Disable auto-commit for each SQL statement
            connection.setAutoCommit(false);

            preparedStatement.setInt(1,8003);
            preparedStatement.setString(2, "Java 123");
            preparedStatement.setString(3, "Kevin Jones");
            preparedStatement.setDouble(4, 12.34);
            preparedStatement.setInt(5,88);
            preparedStatement.addBatch(); //add the statement for batch processing

            //change values from parameter 1 and 2
            preparedStatement.setInt(1,8004);
            preparedStatement.setString(2,"Java 456");
            //no change is values for parameter 3 to 5
            preparedStatement.addBatch(); //add the statement for batch processing

            //executeBatch() returns an int array, keeping the return codes of all statements
            int[] returnCodes = preparedStatement.executeBatch();
            System.out.print("Return codes are: ");
            for (int code : returnCodes) System.out.print(code + ", ");
            System.out.println();

            connection.commit();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
