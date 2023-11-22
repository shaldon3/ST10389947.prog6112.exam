/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mainapplication;

/**
 *
 * @author Shaldon
 */
// MainApplication.java
public class MainApplication {

    public static void main(String[] args) {
           EstateAgent estateAgent = new EstateAgent();

        String[] agentNames = {"Joe Bloggs", "Jane Doe"};
        String[] months = {"January", "February", "March"};

        double[][] propertySales = {
                {800000, 1500000, 2000000},
                {700000, 1200000, 1600000}
        };

        double[] totalSales = new double[propertySales.length];
        double[] totalCommissions = new double[propertySales.length];
        // Display header for the table
       System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s%n", "Agent", "January", "February", "March", "Total Sales", "Total Commission");

        for (int i = 0; i < propertySales.length; i++) {
            totalSales[i] = estateAgent.estateAgentSales(propertySales[i]);
            totalCommissions[i] = estateAgent.estateAgentCommission(totalSales[i]);
            // Display agent name
            System.out.printf("%-15s", agentNames[i]);
            // Display property sales for each month
            for (int j = 0; j < propertySales[i].length; j++) {
                System.out.printf("R %-13.2f", propertySales[i][j]);
            }
            // Display total sales and total commission
            System.out.printf("R %-13.2fR %-13.2f%n", totalSales[i], totalCommissions[i]);
        }
        // Display the top performinng estate agent
        int topAgentIndex = estateAgent.topEstateAgent(totalSales);
        System.out.println("Top Performing Estate Agent: " + agentNames[topAgentIndex]);
    }

}