package managedbean;

import java.sql.*;

public class dbConnection {
    private String dbURL = "jdbc:mysql://localhost:3306/person";
    private String username = "root";
    private String password = "";
    private Connection connection;

    public dbConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL, username, password);
            if (connection != null) {
                System.out.println("Success");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertRecord(String name ,String email,String password) {
        try {
            System.out.println("db");
            String sqlQuery = "INSERT INTO data(uname,email,password) VALUES(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);


            int noOfRowsInserted = preparedStatement.executeUpdate();
            if (noOfRowsInserted > 0) {
                System.out.println(noOfRowsInserted + " rows inserted!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRecord(String uname,String email, String password) {
        try {
            String sqlQuery = "UPDATE data SET uname=?,email=?,password=? WHERE email=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, uname);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, email);

            preparedStatement.executeUpdate();
//            System.out.println("sucess");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRecord(String name) {
        try {
            String sqlQuery = "DELETE from data WHERE uname=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getSpecificRecord(String username) {
        try {
            String sqlQuery = "SELECT username,password FROM signup where username=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, username);
            ResultSet result = preparedStatement.executeQuery();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }public ResultSet getSpecificRecordAll(String username) {
        try {
            String sqlQuery = "SELECT * FROM signup where username=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, username);
            ResultSet result = preparedStatement.executeQuery();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getRecords() {
        try {
            String sqlQuery = "SELECT * FROM data";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sqlQuery);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean validatepassword(String uname,String password){
        try {
            String sqlQuery = "SELECT uname,password FROM data where uname=? and password=?";
            PreparedStatement preparedStatement=connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, uname);
            preparedStatement.setString(2, password);


            ResultSet result = preparedStatement.executeQuery();
            return result.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
