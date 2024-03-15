package de.nasri.payment.classes;


import de.nasri.payment.subClasses.Employee;
import de.nasri.payment.subClasses.ManagementInhouse;
import de.nasri.payment.subClasses.ManagementSales;
import de.nasri.payment.subClasses.ManagingDirector;
import de.nasri.payment.subClasses.Sales;

import javax.json.JsonObject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class processes a list of JSON objects representing employee data and 
 * wage information. It parses the employee data, extracts relevant fields, 
 * and creates corresponding "Personal" objects (likely representing employee 
 * details) using the provided wage information. The class handles potential 
 * missing fields and data inconsistencies.
 */
public class MakeJsonObjects {

	/**
	 * Creates a list of "Personal" objects from employee and wage JSON data.
	 */
	public List<Personal> createPersonalObjects(List<JsonObject> employeesJson, List<JsonObject> wagesJson) {
		
        List<Personal> personalList = new ArrayList<>();

        Map<String, BigDecimal> wagesMap = new HashMap<>();        
        
        for (JsonObject wageObject : wagesJson) {
            String name = wageObject.getString("name"); 
            BigDecimal salary = getBigDecimalValue(wageObject, "salary");
            BigDecimal hourlyWage = getBigDecimalValue(wageObject, "hourly wage");
            BigDecimal spentHours = getBigDecimalValue(wageObject, "spent hours");
            BigDecimal profitSharing = getBigDecimalValue(wageObject, "profit sharing");
            
            wagesMap.put(name + "salary", salary);
            wagesMap.put(name + "hourly wage", hourlyWage);
            wagesMap.put(name + "spent hours", spentHours);
            wagesMap.put(name + "profit sharing", profitSharing);
        }

        /* Hand over the Maps*/
        for (JsonObject jsonObject : employeesJson) {
            Personal personal = createPersonalFromJson(jsonObject, wagesMap); 
            if (personal != null) {
                personalList.add(personal);
            }
        }
        return personalList;
    }

	/**
	 * Creates a "Personal" object based on a JSON object and wage map.	 
	 */
    private Personal createPersonalFromJson(JsonObject jsonObject, Map<String, BigDecimal> wagesMap) {
    	
        String name = null;
        String type = null;
        String payment = null;
        BigDecimal salary = BigDecimal.ZERO; 
        BigDecimal hourlyWage = BigDecimal.ZERO; 
        BigDecimal spentHours = BigDecimal.ZERO; 
        BigDecimal profitSharing = BigDecimal.ZERO;

        /* Check for existence of required fields */
        if (jsonObject.containsKey("name")) {
            name = jsonObject.getString("name");
        }
        if (jsonObject.containsKey("type")) {
            type = jsonObject.getString("type");
        } 
        if (jsonObject.containsKey("payment")) {
            payment = jsonObject.getString("payment");
        }            

        salary = wagesMap.getOrDefault(name + "salary", BigDecimal.ZERO);
        hourlyWage = wagesMap.getOrDefault(name + "hourly wage", BigDecimal.ZERO);
        spentHours = wagesMap.getOrDefault(name + "spent hours", BigDecimal.ZERO);
        profitSharing = wagesMap.getOrDefault(name + "profit sharing", BigDecimal.ZERO);
    
     /* Check for required fields again and create objects */
        if (name != null && type != null) { 
            switch (type) {
                case "Managing Director":
                    return new ManagingDirector(name, type, payment, salary, hourlyWage, spentHours, profitSharing);
                case "Management / Inhouse ":
                    return new ManagementInhouse(name, type, payment, salary, hourlyWage, spentHours, profitSharing);
                case "Management / Sales":
                    return new ManagementSales(name, type, payment, salary, hourlyWage, spentHours, profitSharing);
                case "Sales":
                    return new Sales(name, type, payment, salary, hourlyWage, spentHours, profitSharing);
                case "Employee":
                    return new Employee(name, type, payment, salary, hourlyWage, spentHours, profitSharing);
                default:
                    throw new IllegalArgumentException("Unbekannter Typ: " + type);
            }
        } else {            
            System.out.println("WARNING: Missing required fields for object: " + jsonObject);
            return null; 
        }
    }


    /**
     * Extracts a BigDecimal value from a JSON object with error handling.
     * Cleans and parses the string value before conversion.
     */
    private BigDecimal getBigDecimalValue(JsonObject jsonObject, String key) {
    	
    	 if (jsonObject.containsKey(key)) {
    	        try {
    	            String valueString = jsonObject.getString(key);
    	            
    	            valueString = valueString.trim().replaceAll("^\\-", "");  
    	            
    	            if (valueString.endsWith("%")) {
    	                valueString = valueString.substring(0, valueString.length() - 1);
    	            }
    	            
    	            valueString = valueString.replace(",", ".");    	           
    	            valueString = valueString.replaceAll("[^\\d.-]", "");
    	            
    	            return new BigDecimal(valueString);
    	        } catch (NumberFormatException e) {    	            
    	            return BigDecimal.ZERO;
    	        }
    	    }
    	    /* if the value does not exist */
    	    return BigDecimal.ZERO;
    }
    
}
