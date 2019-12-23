package servlets;

import exception.DBException;
import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            long id = Long.parseLong(req.getParameter("id"));
            UserService userService = UserService.getInstance();
            User user;
        try {
            user = userService.getUserById(id);

        req.setAttribute("userId", id);
            req.setAttribute("userLogin", user.getLogin());
            req.setAttribute("userAge", user.getAge());
            req.setAttribute("userPassword", user.getPassword());
            req.setAttribute("user", user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/edit.jsp");
            requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id = Long.parseLong(req.getParameter("id"));
        String login = req.getParameter("login");
        int age = Integer.parseInt(req.getParameter("age"));
        String password = req.getParameter("password");
        //для вывода сообщения о том, что пользователь изменён

        UserService userService = UserService.getInstance();

        try {
           User user = userService.getUserById(id);
           // login
           if (!login.isEmpty() && !(login.equals(userService.getUserByLogin(login).getLogin()))) {
                   user.setLogin(login);
           } else if (login.equals(userService.getUserByLogin(login).getLogin()) && (id != userService.getUserByLogin(login).getId())) {
               req.setAttribute("userLog", login);
//               doGet(req, resp);
            }
           //age
           if (!req.getParameter("age").isEmpty()) {
               user.setAge(age);
           }
           //password
           if (!password.isEmpty()) {
               user.setPassword(password);
           }

           if (userService.updateUser(user) && id == userService.getUserByLogin(login).getId()) {
                   req.setAttribute("userUpdate", user.getId());

           }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        //ошибка вылетает, но всё работает. что за хрень? + 55 + 76 строка
//       doGet(req,resp);
//        String path = req.getContextPath() + "/list";
//        resp.sendRedirect(path);
        doGet(req, resp);
    }

}
