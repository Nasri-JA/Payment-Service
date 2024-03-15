package de.nasri.payment.classes;

import java.nio.file.Path;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * This class creates and writes JSON files containing a list of "Personal" objects.
 * It takes a list of Personal objects as input, converts them into a JSON array,
 * applies formatting to the JSON output, and writes the formatted JSON data to a file.
 */
public class WriteJsonFiles {

	public static void writeJsonFile(List<Personal> personalList, Path NEW_JSON_FILE_PATH) throws IOException {
		JsonArray jsonArray = createJsonArray(personalList);
	    String formattedJson = formatJson(jsonArray.toString());

	    try (FileWriter fileWriter = new FileWriter(NEW_JSON_FILE_PATH.toString())) {
	        fileWriter.write(formattedJson);
	    }
	}

    private static JsonArray createJsonArray(List<Personal> personalList) {
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        for (Personal personal : personalList) {
            JsonObject jsonObject = createJsonObject(personal);
            jsonArrayBuilder.add(jsonObject);
        }

        return jsonArrayBuilder.build();
    }

    private static JsonObject createJsonObject(Personal personal) {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();

        jsonObjectBuilder.add("name", personal.getName());
        jsonObjectBuilder.add("type", personal.getType());
        jsonObjectBuilder.add("payment", personal.getPayment().toString());
        jsonObjectBuilder.add("salary", personal.getSalary().toString());
        jsonObjectBuilder.add("hourlyWage", personal.getHourlyWage().toString());
        jsonObjectBuilder.add("spentHours", personal.getSpentHours().toString());
        jsonObjectBuilder.add("profitSharing", personal.getProfitSharing().toString());
        jsonObjectBuilder.add("Wage", personal.payment().toString());

        return jsonObjectBuilder.build();
    }
    
    public static String formatJson(String json) {
    	
        StringBuilder builder = new StringBuilder();
        char[] chars = json.toCharArray();
        int indentLevel = 0;
        boolean insideObject = false;

        for (char c : chars) {
            switch (c) {
                case '{':
                    builder.append(c).append('\n');
                    indentLevel++;
                    insideObject = true;
                    break;
                case '}':
                    indentLevel--;
                    if (insideObject) {
                        builder.append('\n');
                    }
                    builder.append(c);
                    insideObject = false;
                    break;
                case ',':
                    builder.append(c).append('\n');
                    break;
                case '[':
                    builder.append(c);
                    indentLevel++;
                    break;
                case ']':
                    indentLevel--;
                    builder.append(c);
                    break;
                default:
                    builder.append(c);
            }
        }        
        if (insideObject) {
            builder.append('\n').append('}');
        }
        return builder.toString();
    }

}

