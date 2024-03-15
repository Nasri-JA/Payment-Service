package de.nasri.payment.classes;

import java.math.BigDecimal;

/**
 * **Abstract base class for different personnel types in a company.**
 * Contains the abstract method `payment()` to calculate the 
 * total payment based on the specific payment type.
 * Offers the `toString()` method for a human-readable representation 
 * of the personal information.
 */
public abstract class Personal {
	
	protected String name;
	protected String type;
	protected String payment;
	protected BigDecimal salary;
	protected BigDecimal hourlyWage;
	protected BigDecimal spentHours;
	protected BigDecimal profitSharing;
	
	public abstract BigDecimal payment();
	
	@Override
    public String toString() {
        return "Name: " + name + "\n" +
               "Type: " + type + "\n" +
               "Payment: " + payment + "\n" +
               "Salary: " + salary + "\n" +
               "Hourly Wage: " + hourlyWage + "\n" +
               "Spent Hours: " + spentHours + "\n" +
               "Profit Sharing: " + profitSharing + "\n";
    }
	

	public Personal(String name, String type, String payment, BigDecimal salary, BigDecimal hourlyWage,
			BigDecimal spentHours, BigDecimal profitSharing) {
		super();
		this.name = name;
		this.type = type;
		this.payment = payment;
		this.salary = salary;
		this.hourlyWage = hourlyWage;
		this.spentHours = spentHours;
		this.profitSharing = profitSharing;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public BigDecimal getHourlyWage() {
		return hourlyWage;
	}

	public void setHourlyWage(BigDecimal hourlyWage) {
		this.hourlyWage = hourlyWage;
	}

	public BigDecimal getSpentHours() {
		return spentHours;
	}

	public void setSpentHours(BigDecimal spentHours) {
		this.spentHours = spentHours;
	}

	public BigDecimal getProfitSharing() {
		return profitSharing;
	}

	public void setProfitSharing(BigDecimal profitSharing) {
		this.profitSharing = profitSharing;
	}
	
}
