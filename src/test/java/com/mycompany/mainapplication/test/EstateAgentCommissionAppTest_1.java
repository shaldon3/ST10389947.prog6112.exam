import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EstateAgentCommissionAppTest_1 {

    @Test
    void calculateCommission_CalculatedSuccessfully() {
        EstateAgent estateAgent = new EstateAgent();
        
        // Test with valid property price and commission percentage
        double commission = estateAgent.calculateCommission("1000000", "5");
        assertEquals(50000, commission, 0.001); // Acceptable delta for floating-point comparison
    }

    @Test
    void calculateCommission_CalculatedUnsuccessfully() {
        EstateAgent estateAgent = new EstateAgent();

        // Test with invalid property price (non-numeric)
        double commission = estateAgent.calculateCommission("InvalidPrice", "5");
        assertEquals(-1, commission); // Return -1 for calculation error

        // Test with invalid commission percentage (non-numeric)
        commission = estateAgent.calculateCommission("1000000", "InvalidCommission");
        assertEquals(-1, commission); // Return -1 for calculation error
    }

    @Test
    void validateData() {
        EstateAgent estateAgent = new EstateAgent();

        // Test with valid data
        assertTrue(estateAgent.validateData(new Data("Cape Town", "John Doe", "500000", "3")));

        // Test with invalid data (empty location)
        assertFalse(estateAgent.validateData(new Data("", "John Doe", "500000", "3")));

        // Test with invalid data (empty name)
        assertFalse(estateAgent.validateData(new Data("Cape Town", "", "500000", "3")));

        // Test with invalid data (zero property price)
        assertFalse(estateAgent.validateData(new Data("Cape Town", "John Doe", "0", "3")));

        // Test with invalid data (zero commission percentage)
        assertFalse(estateAgent.validateData(new Data("Cape Town", "John Doe", "500000", "0")));
    }
}
