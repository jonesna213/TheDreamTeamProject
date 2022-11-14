package com.statefarm.codingcompetition.simpledatatool.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(
        name = "applicationStartup",
        urlPatterns = { "/applicationStartup" },
        loadOnStartup = 1
)

/**
 * This servlet is the startup for the application.
 */
public class ApplicationStartup extends HttpServlet {

    /**
     * This method sets up the initialization of the application.
     */
    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        context.setAttribute("agentsFilePath", "/agents.csv");
        context.setAttribute("claimsFilePath", "/claims.csv");
        context.setAttribute("customersFilePath", "/customers.csv");
        context.setAttribute("policiesFilePath", "/policies.csv");
    }
}
