package service;

import dao.UserDAO;
import exception.DBException;
import model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private UserService(){
    }

    private static class UserHolder{
        private final static UserService instance = new UserService();
    }

    public static UserService getInstance(){
        return UserHolder.instance;
    }

    public boolean AddUser(User user) throws SQLException{
        if (user == null) {
            return false;
        } else if (!user.getLogin().equals(getUserDAO().getUserByLogin(user.getLogin()).getLogin())){
            getUserDAO().addUserDao(user);
            return true;
        } else {
            return false;
        }
    }
    public User getUserById(Long id) throws SQLException{
//        try {
//            return getUserDAO().getUserById(id);
//        } catch (SQLException e){
//            e.printStackTrace();
//        }
//        return null;
        return getUserDAO().getUserById(id);

    }

    public long getUserIdByLogin(String login) throws SQLException {
        return getUserDAO().getUserIdByLogin(login);
    }
    public User getUserByLogin(String login) throws SQLException {
       return getUserDAO().getUserByLogin(login);
    }


    // вывод всех пользователей
    public List<User> getAllUser() throws DBException, SQLException{
        return getUserDAO().getAllUserDao();
    }



    //удаление по ID
    public void removeUserById(long id) throws SQLException,DBException{
        getUserDAO().removeUserByIdDao(id);
    }
    public void removeUserByLogin(User user) throws SQLException{
        getUserDAO().removeUserByLoginDao(user);
    }

    //update user
    public boolean updateUser(User user) throws SQLException {
        if (user == null){
            return false;
        } else {
            getUserDAO().updateUserDAO(user);
            return true;
        }
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
