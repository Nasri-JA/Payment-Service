package de.nasri.payment.subClasses;

import java.math.BigDecimal;

import de.nasri.payment.classes.Personal;

/**
 * Subclass of the abstract `Personal` class representing a sales employee.
 * Overrides the `payment()` method to calculate the total payment for sales personnel based on 
 * their hourly wage and spent hours.
 */
public class Sales extends Personal {
    
	public Sales(String name, String type, String payment, BigDecimal salary, BigDecimal hourlyWage, BigDecimal spentHours, BigDecimal profitSharing) {
	    super(name, type, payment, salary, hourlyWage, spentHours, profitSharing); 
	}
    
    @Override
    public BigDecimal payment() {
        return hourlyWage.multiply(spentHours);
    }
    
}
