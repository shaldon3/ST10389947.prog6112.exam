/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.mainapplication;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Shaldon
 */
public class EstateAgentTest {
    
    @Test
    public void calculateTotalSales_ReturnsTotalSales() {
        EstateAgent estateAgent = new EstateAgent();
        double[] propertySales = {800000, 1500000, 2000000};
        double expectedTotalSales = 4300000; // 800000 + 1500000 + 2000000

        double actualTotalSales = estateAgent.estateAgentSales(propertySales);

        assertEquals(expectedTotalSales, actualTotalSales, 0.01); // Allow for a small error due to double precision
    }

    @Test
    public void calculateTotalCommission_ReturnsCommission() {
        EstateAgent estateAgent = new EstateAgent();
        double totalSales = 4300000;
        double expectedCommission = 86000; // 2% of 4300000

        double actualCommission = estateAgent.estateAgentCommission(totalSales);

        assertEquals(expectedCommission, actualCommission, 0.01); // Allow for a small error due to double precision
    }

    @Test
    public void topAgent_ReturnsTopPosition() {
        EstateAgent estateAgent = new EstateAgent();
        double[] totalSales = {4300000, 3500000};
        int expectedTopAgentIndex = 0; // Joe Bloggs has higher total sales

        int actualTopAgentIndex = estateAgent.topEstateAgent(totalSales);

        assertEquals(expectedTopAgentIndex, actualTopAgentIndex);
    }
}
    

