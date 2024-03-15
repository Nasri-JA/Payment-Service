package de.nasri.payment.main;

import javax.json.JsonObject;

import de.nasri.payment.classes.MakeJsonObjects;
import de.nasri.payment.classes.Personal;
import de.nasri.payment.classes.ReadJsonFiles;
import de.nasri.payment.classes.WriteJsonFiles;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Main class for launching the payment processing application.
 *
 * Reads employee and wage data from JSON files, creates "Personal" objects,
 * calculates their payments, and writes the new data to a separate JSON file.
 */
public class LaunchPayment {

	public static void main(String[] args) throws IOException {
		
		Path EMPLOYEES_FILE_PATH = Paths.get("D:\\MyJava\\Employees.json");
        Path WAGES_FILE_PATH = Paths.get("D:\\MyJava\\Wages.json");

	    ReadJsonFiles jsonReader = new ReadJsonFiles();
        List<JsonObject> employeesJson = jsonReader.readJsonFile(EMPLOYEES_FILE_PATH);
        List<JsonObject> wagesJson = jsonReader.readJsonFile(WAGES_FILE_PATH);
        
        MakeJsonObjects jsonObjectsMaker = new MakeJsonObjects();
        List<Personal> personalList = jsonObjectsMaker.createPersonalObjects(employeesJson, wagesJson);
        
        System.out.println("\n################# Mitarbeiterliste mit berechnetem Gehalt #################");
        for (Personal personal : personalList) {
        	System.out.println("----------------------------------------");
            System.out.println(personal.toString());
            
            BigDecimal paymentAmount = personal.payment();
            System.out.println("Wage: " + paymentAmount + "\n\n\n");
        }
        
        Path NEW_JSON_FILE_PATH = Paths.get("D:\\Myjava\\newPersonal.json");

        System.out.println("\n\n################# Information über neue JSON-DATEI: #################\n");
        if (Files.exists(NEW_JSON_FILE_PATH)) {
            System.out.println("Die JSON-DATEI existiert bereits im Ordner: "+ NEW_JSON_FILE_PATH);
        } else {
            WriteJsonFiles.writeJsonFile(personalList, NEW_JSON_FILE_PATH);
            System.out.println("JSON-Datei wurde erfolgreich erstellt: " + NEW_JSON_FILE_PATH);
        }
	    
	}

}
