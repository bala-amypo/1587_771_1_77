
package com.example.demo.servlet;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/health")
public class HealthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    }
}
