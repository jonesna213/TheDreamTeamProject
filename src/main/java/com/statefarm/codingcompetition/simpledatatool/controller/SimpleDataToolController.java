package com.statefarm.codingcompetition.simpledatatool.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.statefarm.codingcompetition.simpledatatool.model.Agent;
import com.statefarm.codingcompetition.simpledatatool.model.Claim;
import com.statefarm.codingcompetition.simpledatatool.model.Customer;
import com.statefarm.codingcompetition.simpledatatool.model.Policy;

public class SimpleDataToolController {

    /**
     * Read in a CSV file and return a list of entries in that file
     *
     * Inspired by https://cowtowncoder.medium.com/reading-csv-with-jackson-c4e74a15ddc1
     * and https://www.java67.com/2019/05/how-to-read-csv-file-in-java-using-jackson-library.html
     * 
     * @param <T>
     * @param filePath  Path to the file being read in
     * @param classType Class of entries being read in
     * @return List of entries from CSV file
     */
    public <T> List<T> readCsvFile(String filePath, Class<T> classType) {
        List<T> entries = new ArrayList<>();
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();

        try (Reader reader = new FileReader(filePath)) {
            MappingIterator<T> iterator = mapper
                    .readerFor(classType)
                    .with(schema)
                    .readValues(reader);
            while (iterator.hasNext()) {
                entries.add(iterator.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return entries;
    }

    /**
     * Gets the number of open claims
     *
     * L
     *
     * @param claims List of all claims
     * @return number of open claims
     */
    public int getNumberOfOpenClaims(List<Claim> claims) {
        int numberOfOpenClaims = 0;

        for (int i = 0; i < claims.size(); i++) {
            boolean isClaimOpen = claims.get(i).getIsClaimOpen();
                if (isClaimOpen) {
                    numberOfOpenClaims++;
                }
        }

        return numberOfOpenClaims;
    }

    /**
     * Get the number of customer for an agent id
     *
     * L
     *
     * @param filePath File path to the customers CSV
     * @param agentId  Agent id as int
     * @return number of customer for agent
     */
    public int getNumberOfCustomersForAgentId(String filePath, int agentId) {
        int numberOfCustomersForAgentId = 0;
        List<Customer> customers = readCsvFile(filePath, Customer.class);

        for (Customer customer : customers) {
            int assignedAgentToCustomer = customer.getAgentId();
            if (assignedAgentToCustomer == agentId) {
                numberOfCustomersForAgentId++;
            }
        }

        return numberOfCustomersForAgentId;
    }

    /**
     * Get the number of agents for a state
     *
     * L
     *
     * @param filePath File path to the customers CSV
     * @param state    Agent id as int
     * @return number of agents for state
     */
    public int getNumberOfAgentsForState(String filePath, String state) {
        int numberOfAgentsForState = 0;
        List<Agent> agents = readCsvFile(filePath, Agent.class);

        for (Agent agent : agents) {
            String stateOfAgent = agent.getState();
            if (state.equals(stateOfAgent)) {
                numberOfAgentsForState++;
            }
        }

        return numberOfAgentsForState;
    }

    /**
     * Sum total premium for a specific customer id
     *
     * B
     *
     * @param policies   List of all policies
     * @param customerId Customer id as int
     * @return float of monthly premium
     */
    public double sumMonthlyPremiumForCustomerId(List<Policy> policies, int customerId) {
        return 0d;
    }

    /**
     * For a given customer (by first and last names), return the number of open
     * claims they have
     *
     * B
     *
     * @param filePathToCustomer File path to customers CSV
     * @param filePathToPolicy   File path to policies CSV
     * @param filePathToClaims   File path to claims CSV
     * @param firstName          First name of customer to search for
     * @param lastName           Last name of customer to search for
     * @return Number of open claims for customer or null if customer doesn't exist
     */
    public Integer getNumberOfOpenClaimsForCustomerName(String filePathToCustomer, String filePathToPolicy,
            String filePathToClaims, String firstName, String lastName) {
        return null;
    }

    /**
     * Returns the most spoken language (besides English) for customers in a given
     * state
     *
     * B
     *
     * @param customersFilePath File path to customers CSV
     * @param state             State abbreviation ex: AZ, TX, IL, etc.
     * @return String of language
     */
    public String getMostSpokenLanguageForState(String customersFilePath, String state) {
        return null;
    }

    /**
     * Returns Customer with the highest, total premium
     *
     * N
     *
     * @param customersFilePath File path to customers CSV
     * @param policies          List of all policies
     * @return Customer that has the highest, total premium as Customer object
     */
    public Customer getCustomerWithHighestTotalPremium(String customersFilePath, List<Policy> policies) {
        List<Customer> customers = readCsvFile(customersFilePath, Customer.class);
        Map<Integer, Double> customerPolicies = new HashMap<>();

        Customer customerWithHighestPremium = null;
        double highestTotal = 0;

        //Mapping all customer ids with their total premiums from the policies list
        for (Policy policy:policies) {
            if (customerPolicies.containsKey(policy.getCustomerId())) {
                customerPolicies.replace(policy.getCustomerId(),
                        customerPolicies.get(policy.getCustomerId()) + policy.getPremiumPerMonth());
            } else {
                customerPolicies.put(policy.getCustomerId(), policy.getPremiumPerMonth());
            }
        }

        //finding the customer with the highest total premium
        for (Customer customer:customers) {
            if (customerPolicies.containsKey(customer.getId()) && customerPolicies.get(customer.getId()) > highestTotal) {
                customerWithHighestPremium = customer;
                highestTotal = customerPolicies.get(customer.getId());
            }
        }

        return customerWithHighestPremium;
    }

    /**
     * Returns the total number of open claims for a given state
     *
     * N
     *
     * @param customersFilePath File path to customers CSV
     * @param policiesFilePath  File path to policies CSV
     * @param claimsFilePath    File path to claims CSV
     * @param state             State abbreviation ex: AZ, TX, IL, etc.
     * @return number of open claims as int
     */
    public int getOpenClaimsForState(String customersFilePath, String policiesFilePath, String claimsFilePath,
            String state) {
        int openClaims = 0;
        List<Customer> customers = readCsvFile(customersFilePath, Customer.class);
        List<Policy> policies = readCsvFile(policiesFilePath, Policy.class);
        List<Claim> claims = readCsvFile(claimsFilePath, Claim.class);

        List<Integer> customerIds = new ArrayList<>();
        List<Integer> policyIds = new ArrayList<>();

        //adding all customer ids with correct state to customerIds list
        for (Customer customer:customers) {
            if (customer.getState().equals(state)) {
                customerIds.add(customer.getId());
            }
        }

        //adding all policy ids with customers with the correct state into policy ids list
        for (Policy policy:policies) {
            if (customerIds.contains(policy.getCustomerId())) {
                policyIds.add(policy.getId());
            }
        }

        //looking at all claims to see if the policy id is in the policyIds list and if the claim is open
        for (Claim claim:claims) {
            if (policyIds.contains(claim.getPolicyId()) && claim.getIsClaimOpen()) {
                openClaims++;
            }
        }

        return openClaims;
    }

    /**
     * Builds a dictionary/map of agents and their total premium from their
     * customers
     *
     * N
     *
     * @param customersFilePath File path to customers CSV
     * @param policiesFilePath  File path to policies CSV
     * @return Map of agent id as int to agent's total premium as double
     */
    public Map<Integer, Double> buildMapOfAgentPremiums(
            String customersFilePath, String policiesFilePath) {
        List<Customer> customers = readCsvFile(customersFilePath, Customer.class);
        List<Policy> policies = readCsvFile(policiesFilePath, Policy.class);
        Map<Integer, Double> agentTotalPremiums = new HashMap<>();

        for (Customer customer:customers) {
            if (agentTotalPremiums.containsKey(customer.getAgentId())) {
                agentTotalPremiums.replace(customer.getAgentId(), agentTotalPremiums.get(customer.getAgentId()) +
                        sumMonthlyPremiumForCustomerId(policies, customer.getId()));
            } else {
                agentTotalPremiums.put(customer.getAgentId(), sumMonthlyPremiumForCustomerId(policies, customer.getId()));
            }
        }

        return agentTotalPremiums;
    }
}