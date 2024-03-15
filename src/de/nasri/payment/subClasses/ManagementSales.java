package de.nasri.payment.subClasses;

import java.math.BigDecimal;

import de.nasri.payment.classes.Personal;

/**
 * Subclass of the abstract `Personal` class representing sales management.
 * Overrides the `payment()` method to calculate the total payment for sales management personnel.
 * It considers both hourly wage multiplied by spent hours and any profit sharing amount.
 */
public class ManagementSales extends Personal {
    
	public ManagementSales(String name, String type, String payment, BigDecimal salary, BigDecimal hourlyWage, BigDecimal spentHours, BigDecimal profitSharing) {
	    super(name, type, payment, salary, hourlyWage, spentHours, profitSharing); 
	}
    
	@Override
	public BigDecimal payment() {
	    BigDecimal totalPayment = hourlyWage.multiply(spentHours);
	    
	    if (profitSharing.compareTo(BigDecimal.ZERO) != 0) {	        
	        totalPayment = totalPayment.add(profitSharing);
	    }
	    return totalPayment;
	}
    
}
