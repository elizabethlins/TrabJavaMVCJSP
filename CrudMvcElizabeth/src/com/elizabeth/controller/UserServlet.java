package com.elizabeth.controller;

import com.elizabeth.model.UserModel;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1123L;
    private static String INDEX = "/index.jsp";
    UserModel userController;

    public UserServlet() {
        super();
        userController = new UserModel();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        forward = userController.processGet(request);

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String forward = userController.processPost(request);

        if (!forward.isEmpty()) {
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
        } else {
            RequestDispatcher view = request.getRequestDispatcher(INDEX);
            view.forward(request, response);
        }

    }


}