package de.nasri.payment.classes;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides functionalities for reading JSON files containing 
 * an array of JSON objects. It parses the file content and extracts individual 
 * JSON objects into a list.
 */
public class ReadJsonFiles {

	public List<JsonObject> readJsonFile(Path filePath) {
		
        List<JsonObject> jsonObjects = new ArrayList<>();

        try {
            String jsonContent = Files.readString(filePath);
            
            int startIndex = jsonContent.indexOf("[");
            int endIndex = jsonContent.lastIndexOf("]");

            if (startIndex != -1 && endIndex != -1) {
                String jsonArrayString = jsonContent.substring(startIndex, endIndex + 1);
                
                try (JsonReader jsonReader = Json.createReader(new java.io.StringReader(jsonArrayString))) {
                    JsonArray jsonArray = jsonReader.readArray();
                    for (JsonValue jsonValue : jsonArray) {
                        if (jsonValue instanceof JsonObject) {
                            JsonObject jsonObject = (JsonObject) jsonValue;
                            jsonObjects.add(jsonObject);
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonObjects;
    }

}
