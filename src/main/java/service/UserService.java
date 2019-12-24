package service;

import dao.UserDAO;
import exception.DBException;
import model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private UserService(){
    }

    private static class UserHolder{
        private final static UserService instance = new UserService();
    }

    public static UserService getInstance(){
        return UserHolder.instance;
    }

    public void AddUser(User user) throws SQLException{
            getUserDAO().addUserDao(user);
    }

    public User getUserById(Long id) throws SQLException{
        return getUserDAO().getUserById(id);
    }

    public long getUserIdByLogin(String login) throws SQLException {
        return getUserDAO().getUserIdByLogin(login);
    }

    public User getUserByLogin(String login) throws SQLException {
       return getUserDAO().getUserByLogin(login);
    }

    public List<User> getAllUser() throws DBException, SQLException{
        return getUserDAO().getAllUserDao();
    }

    public void removeUserById(long id) throws SQLException,DBException{
        getUserDAO().removeUserByIdDao(id);
    }

    public void updateUser(User user) throws SQLException {
            getUserDAO().updateUserDAO(user);
    }

    public void cleanUp() throws DBException {
        UserDAO dao = getUserDAO();
        try {
            dao.dropTable();
        } catch (SQLException e) {
            throw  new DBException(e);
        }
    }

    public void createTable() throws DBException {
        UserDAO dao = getUserDAO();
        try {
            dao.createTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_example?").          //db name
                    append("user=root&").          //login
                    append("password=jav@MySQ1");       //password

            System.out.println("URL: " + url + "\n");

            return DriverManager.getConnection(url.toString());
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
    private static UserDAO getUserDAO() {
        return new UserDAO(getMysqlConnection());
    }
}
