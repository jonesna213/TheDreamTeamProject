package com.statefarm.codingcompetition.simpledatatool.controller;

import com.statefarm.codingcompetition.simpledatatool.model.Claim;
import com.statefarm.codingcompetition.simpledatatool.model.Policy;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
        ServletContext context = getServletContext();
        SimpleDataToolController controller = new SimpleDataToolController();
        String claimsFilePath = (String) context.getAttribute("claimsFilePath");
        String agentsFilePath = (String) context.getAttribute("agentsFilePath");
        String customersFilePath = (String) context.getAttribute("customersFilePath");
        String policiesFilePath = (String) context.getAttribute("policiesFilePath");
        String choice = req.getParameter("choice");

        //Couldn't think of a cleaner way...
        if (choice.equals("Get Number Of Open Claims")) {
            session.setAttribute("results", controller.getNumberOfOpenClaims(controller.readCsvFile(claimsFilePath, Claim.class)));

        } else if (choice.equals("Get Map Of Agent Premiums")) {
            session.setAttribute("results", controller.buildMapOfAgentPremiums(customersFilePath, policiesFilePath));

        } else if (choice.equals("Get Customer With Highest Total Premium")) {
            session.setAttribute("results", controller.getCustomerWithHighestTotalPremium(customersFilePath, controller.readCsvFile(policiesFilePath, Policy.class)));

        } else if (choice.equals("Get Number Of Customers For Agent Id")) {
            int agentId = Integer.parseInt(req.getParameter("agentId"));
            session.setAttribute("results", controller.getNumberOfCustomersForAgentId(customersFilePath, agentId));

        } else if (choice.equals("Get Number Of Agents For State")) {
            String state = req.getParameter("stateAgent");
            session.setAttribute("results", controller.getNumberOfAgentsForState(customersFilePath, state));

        } else if (choice.equals("Sum Monthly Premium For Customer Id")) {
            int customerId = Integer.parseInt(req.getParameter("customerId"));
            session.setAttribute("results", controller.sumMonthlyPremiumForCustomerId(controller.readCsvFile(policiesFilePath, Policy.class), customerId));

        } else if (choice.equals("Get Number Of Open Claims For Customer Name")) {
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            session.setAttribute("results", controller.getNumberOfOpenClaimsForCustomerName(customersFilePath, policiesFilePath, claimsFilePath, firstName, lastName));

        } else if (choice.equals("Get Most Spoken Language For State")) {
            String state = req.getParameter("stateLanguage");
            session.setAttribute("results", controller.getMostSpokenLanguageForState(customersFilePath, state));

        } else if (choice.equals("Get Number Of Open Claims For State")) {
            String state = req.getParameter("stateClaims");
            session.setAttribute("results", controller.getOpenClaimsForState(customersFilePath, policiesFilePath, claimsFilePath, state));
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, resp);
    }
}
