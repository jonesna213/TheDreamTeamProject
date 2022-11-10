package com.statefarm.codingcompetition.simpledatatool.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet(
        name = "runDataTool",
        urlPatterns = {"/runDataTool"}
)
/**
 * This servlet runs the data tool and returns the results for whichever results were wanted
 */
public class RunDataTool extends HttpServlet {
    /**
     * Runs data tool then redirects to results page
     *
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException for servlet exceptions
     * @throws IOException for io exceptions
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String results = null;

        session.setAttribute("results", results);
        resp.sendRedirect("results.jsp");
    }
}
