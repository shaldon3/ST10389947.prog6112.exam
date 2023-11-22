/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mainapplication;

/**
 *
 * @author Shaldon
 */
public class EstateAgent {
    // Method to calculate total property sales for an estate agent
    public double estateAgentSales(double[] propertySales) {
        double totalSales = 0;
        for (double sale : propertySales) {
            totalSales += sale;
        }
        return totalSales;
    }
    // Method to calculate 2% commission based on total property sales

    public double estateAgentCommission(double propertySales) {
        return propertySales * 0.02;
    }
    // Method to find the index of the top-selling estate agent

    public int topEstateAgent(double[] totalSales) {
        int topAgentIndex = 0;
        for (int i = 1; i < totalSales.length; i++) {
            if (totalSales[i] > totalSales[topAgentIndex]) {
                topAgentIndex = i;
            }
        }
        return topAgentIndex;
    }
}
