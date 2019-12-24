package servlets;

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
        UserService userService = UserService.getInstance();
        try {
           User user = userService.getUserById(id);
           if (!(login.equals(userService.getUserByLogin(login).getLogin()))) {
                   user.setLogin(login);
           } else {
               resp.setContentType("text/html; charset=UTF-8");
               PrintWriter wr = resp.getWriter();
               wr.println("Пользователь существует");
               wr.close();
           }
           user.setAge(age);
           user.setPassword(password);
           if (!(login.equals(userService.getUserByLogin(login).getLogin()))) {
               userService.updateUser(user);
               String path = req.getContextPath() + "/list";
               resp.sendRedirect(path);
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
