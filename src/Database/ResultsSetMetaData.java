package Database;

import java.sql.*;

public class ResultsSetMetaData
{
    public static void main(String[] args)
    {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ebookshop", "root", "root");
             Statement statement = connection.createStatement()
        )
        {
            ResultSet resultSet = statement.executeQuery("select * from books");
            //get the metadata of the ResultSet
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            //Get the number of column from metadata
            int numColumns = resultSetMetaData.getColumnCount();

            //Print column names - Column Index begins at 1 (instead of 0)
            for (int i = 1; i <= numColumns; i++)
                System.out.printf("%-30s", resultSetMetaData.getColumnName(i));
            System.out.println();

            //Print column class names
            for (int i = 1; i <= numColumns; i++)
                System.out.printf("%-30s", "(" + resultSetMetaData.getColumnClassName(i) + ")");
            System.out.println();

            //Print column contents for all the rows
            while (resultSet.next())
            {
                for (int i = 1; i < numColumns; ++i)
                    System.out.printf("%-30s", resultSet.getString(i));
            }
            System.out.println();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
