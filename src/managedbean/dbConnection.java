package managedbean;

import java.sql.*;

public class dbConnection {
    private String dbURL = "jdbc:mysql://localhost:3306/mcdel";
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

    public void insertRecord(String firstname, String lastname,String email,String password,int phonenumber) {
        try {
            System.out.println("db");
            String sqlQuery = "INSERT INTO new(fname,lname, email,password,phoneno) VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);
            preparedStatement.setInt(5, phonenumber);


            int noOfRowsInserted = preparedStatement.executeUpdate();
            if (noOfRowsInserted > 0) {
                System.out.println(noOfRowsInserted + " rows inserted!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRecord(String fname,String lname,String email, String password,int phno) {
        try {
            String sqlQuery = "UPDATE new SET fname=?,lname=?,email=?,password=?,phoneno=? WHERE email=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, fname);
            preparedStatement.setString(2, lname);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);
            preparedStatement.setInt(5,phno);
            preparedStatement.setString(6, email);

            preparedStatement.executeUpdate();
//            System.out.println("sucess");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRecord(String mail) {
        try {
            String sqlQuery = "DELETE from value WHERE email=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, mail);
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
    public boolean validatepassword(String email,String password){
        try {
            String sqlQuery = "SELECT email,password FROM new where email=? and password=?";
            PreparedStatement preparedStatement=connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);


            ResultSet result = preparedStatement.executeQuery();
            return result.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
