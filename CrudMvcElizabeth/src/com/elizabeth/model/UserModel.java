package com.elizabeth.model;

import com.elizabeth.dao.UserDao;
import com.elizabeth.model.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserModel extends AbstractModel {

    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/user.jsp";
    private static String LIST_USER = "/listUser.jsp";
    private static String INDEX = "/index.jsp";
    private static String LOGIN = "/login.jsp";
    private UserDao dao;


    public UserModel() {
        dao = new UserDao();
    }


    @Override
    public String processGet(HttpServletRequest request) {
        String forward;
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        String loggedUser = (String) session.getAttribute("loggedUser");
        if (loggedUser != null && !loggedUser.isEmpty()) {
            if (action.equalsIgnoreCase("delete")) {
                int userId = Integer.parseInt(request.getParameter("userId"));
                dao.deleteUser(userId);
                forward = LIST_USER;
                request.setAttribute("users", dao.getAllUsers());
            } else if (action.equalsIgnoreCase("edit")) {
                forward = INSERT_OR_EDIT;
                int userId = Integer.parseInt(request.getParameter("userId"));
                User user = dao.getUserById(userId);
                request.setAttribute("user", user);
            } else if(action.equalsIgnoreCase("insert")){
                forward = INSERT_OR_EDIT;
            } else if (action.equalsIgnoreCase("listUser")) {
                forward = LIST_USER;
                request.setAttribute("users", dao.getAllUsers());

            } else if (action.equalsIgnoreCase("logout")) {
                session.invalidate();
                forward = INDEX;
            } else {
                forward = LIST_USER;
            }
        } else {
            forward = LOGIN;
        }
        return forward;
    }

    @Override
    public String processPost(HttpServletRequest request) {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        String loggedUser = (String) session.getAttribute("loggedUser");
        String forward = "";
        if (action.equalsIgnoreCase("login")) {
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            if (dao.login(login, password)) {
                session.setAttribute("loggedUser",login);
                forward = LIST_USER;
                request.setAttribute("users", dao.getAllUsers());
            } else {
                forward = INDEX;
            }
        } else if (loggedUser != null && !loggedUser.isEmpty()) {
            if (action.equalsIgnoreCase("adduser")) {
                createUser(request);
                request.setAttribute("users", dao.getAllUsers());
                forward = LIST_USER;
            }
        }
        return forward;
    }

    private void createUser(HttpServletRequest request) {
        User user = new User();
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        try {
            Date dob = new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("dob"));
            user.setDob(dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setEmail(request.getParameter("email"));
        String userid = request.getParameter("userid");
        user.setPassword(request.getParameter("password"));
        user.setLogin(request.getParameter("login"));
        if (userid == null || userid.isEmpty()) {
            dao.addUser(user);
        } else {
            user.setUserid(Integer.parseInt(userid));
            dao.updateUser(user);
        }
    }
}
