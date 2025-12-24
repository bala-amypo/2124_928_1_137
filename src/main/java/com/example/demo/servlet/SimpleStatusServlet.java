package com.example.demo.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/simple-status")
public class SimpleStatusServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        res.setContentType("text/plain");
        res.getWriter().write(
            "SaaS User Role Permission Manager is running"
        );
    }
}
