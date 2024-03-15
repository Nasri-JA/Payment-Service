package de.nasri.payment.subClasses;

import java.math.BigDecimal;

import de.nasri.payment.classes.Personal;

/**
 * Subclass of the abstract `Personal` class representing an employee.
 * Overrides the `payment()` method to calculate the employee's payment based on 
 * their hourly wage and spent hours.
 */
public class Employee extends Personal {
    
	public Employee(String name, String type, String payment, BigDecimal salary, BigDecimal hourlyWage, BigDecimal spentHours, BigDecimal profitSharing) {
		super(name, type, payment, salary, hourlyWage, spentHours, profitSharing);
    }
    
    @Override
    public BigDecimal payment() {
        return hourlyWage.multiply(spentHours);
    }
  
}
