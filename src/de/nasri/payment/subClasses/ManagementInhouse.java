package de.nasri.payment.subClasses;

import java.math.BigDecimal;
import de.nasri.payment.classes.Personal;

/**
 * Subclass of the abstract `Personal` class representing in-house management.
 * Overrides the `payment()` method to return the fixed salary for in-house management personnel.
 */
public class ManagementInhouse extends Personal {

	public ManagementInhouse(String name, String type, String payment, BigDecimal salary, BigDecimal hourlyWage, BigDecimal spentHours, BigDecimal profitSharing) {
	    super(name, type, payment, salary, hourlyWage, spentHours, profitSharing); 
	}
    
    @Override
    public BigDecimal payment() {
        return salary;
    }
    
}
