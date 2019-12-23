package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public void addUserDao(User user) throws SQLException {
        String login = user.getLogin();
        int age = user.getAge();
        String password = user.getPassword();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("insert into users(login, age, password) VALUES ('" +login + "', '" +age + "', '" +password + "')");
        stmt.close();
    }

    public User getUserById(long id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("select * from users where id=?");
        stmt.setLong(1,id);
        ResultSet rs = stmt.executeQuery();
        String login = null;
        int age = 0;
        String password = null;
//        if (!rs.next()) {
//            System.out.println("ResultSet is empty");
//        } else {
//            do {
        while (rs.next()){
                login = rs.getString(2);
                age = rs.getInt(3);
                password = rs.getString(4);
//            } while (rs.next());
        }
        User userById = new User(id, login, age, password);
        rs.close();
        stmt.close();
        return userById;
    }

    public User getUserByLogin(String login) throws SQLException {
        return getUserById(getUserIdByLogin(login));
    }

    public long getUserIdByLogin(String login) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("select * from users where login=?");
        stmt.setString(1,login);
        ResultSet rs = stmt.executeQuery();
        long id = 0;
        if (rs.first()) {
            id = rs.getLong(1);
        }
        rs.close();
        stmt.close();
        return id;
    }

    public List<User> getAllUserDao() throws SQLException {
        List<User> userList = new ArrayList<>();
        Statement stmt = connection.createStatement();
        stmt.execute("select  * from users");
        ResultSet rs = stmt.getResultSet();
        while (rs.next()){
            long id = rs.getLong(1);
            User user = new User(id, getUserById(id).getLogin(), getUserById(id).getAge(), getUserById(id).getPassword());
            userList.add(user);
        }
        rs.close();
        stmt.close();
        return userList;
    }

    //remove
    public void removeUserByIdDao(long id) throws SQLException{

        PreparedStatement stmt = connection.prepareStatement("delete from users where id=?");
        stmt.setLong(1,id);
        stmt.executeUpdate();
        stmt.close();
    }

    public void removeUserByLoginDao(User user) throws SQLException{
        String login1 = user.getLogin();
        String password1 = user.getPassword();
        PreparedStatement stmt = connection.prepareStatement("delete from users where login=? ");
        stmt.setString(1,login1);
        stmt.executeUpdate();
        stmt.close();
    }


    public void updateUserDAO(User user) throws SQLException {

        PreparedStatement stmt = connection.prepareStatement("update users SET login=? , age=? , password=? where id= ?");
        stmt.setString(1, user.getLogin());
        stmt.setInt(2, user.getAge());
        stmt.setString(3, user.getPassword());
        stmt.setLong(4, user.getId());
        stmt.executeUpdate();
        stmt.close();
    }


    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists users (id bigint auto_increment, login varchar(256), age integer, password varchar(256), primary key (id))");
        stmt.close();
    }

    public void dropTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS users");
        stmt.close();
    }
}
