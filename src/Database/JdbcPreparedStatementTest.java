package Database;

import java.sql.*;

public class JdbcPreparedStatementTest
{
    public static void main(String[] args)
    {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ebookshop", "root", "root");
             //Two PreparedStatements, one for Insert and one for Select
             PreparedStatement preparedStatement = connection.prepareStatement("insert into books values (?, ?, ?, ?, ?)");//Five parameters 1 to 5
             PreparedStatement preparedStatementSelect = connection.prepareStatement("select * from books");
        )
        {
            //set values from parameters 1 to 5
            preparedStatement.setInt(1, 7001);
            preparedStatement.setString(2, "Mahjong 101");
            preparedStatement.setString(3, "Kumar");
            preparedStatement.setDouble(4, 88.88);
            preparedStatement.setInt(5, 88);
            //execute statement
            int rowsInserted = preparedStatement.executeUpdate();
            System.out.println(rowsInserted + " rows affected.");

            //change values for parameter 1 and 2
            preparedStatement.setInt(1, 7002);
            preparedStatement.setString(2, "Mahjong 102");
            //no change in values for parameter 3 to 5
            rowsInserted = preparedStatement.executeUpdate();
            System.out.println(rowsInserted + " rows affected.");

            //Issue a select to check the changes
            ResultSet resultSet = preparedStatementSelect.executeQuery();
            while (resultSet.next())
                System.out.println(resultSet.getInt("id") + ", "
                        + resultSet.getString("author") + ", "
                        + resultSet.getString("title") + ", "
                        + resultSet.getDouble("price") + ", "
                        + resultSet.getInt("qty"));

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
