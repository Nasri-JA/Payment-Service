package de.nasri.payment.subClasses;

import java.math.BigDecimal;
import de.nasri.payment.classes.Personal;

/**
 * Subclass of the abstract `Personal` class representing a managing director.
 * Overrides the `payment()` method to calculate the total payment for the managing director.
 * It includes the fixed salary and a bonus based on a percentage of the salary and the profit sharing value.
 */
public class ManagingDirector extends Personal {
    
	public ManagingDirector(String name, String type, String payment, BigDecimal salary, BigDecimal hourlyWage, BigDecimal spentHours, BigDecimal profitSharing) {
	    super(name, type, payment, salary, hourlyWage, spentHours, profitSharing); 
	}
    
    @Override
    public BigDecimal payment() {
        return salary.add(salary.multiply(profitSharing));
    }
   
}
