/*
 * package com.ecommerceproject.utils; import
 * com.fasterxml.jackson.databind.node.ArrayNode; import java.util.Arrays;
 * import java.util.*; import java.util.HashMap;
 * 
 * import org.bson.json.JsonObject; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Component; import
 * com.fasterxml.jackson.databind.ObjectMapper; import
 * com.fasterxml.jackson.databind.JsonNode;
 * 
 * import com.fasterxml.jackson.databind.node.ObjectNode; import
 * com.opencsv.CSVReader; import com.opencsv.exceptions.CsvException;
 * 
 * import java.io.FileReader; import java.io.IOException; import
 * java.nio.file.Path; import java.nio.file.Paths;
 * 
 * import java.nio.file.Path; import java.nio.file.Paths; import
 * java.io.FileReader; import com.opencsv.CSVReader; import
 * com.fasterxml.jackson.databind.ObjectMapper; import java.util.*;
 * 
 * @Component public class Filreader {
 * 
 * 
 * public void saveDatainMongo() { Path su =
 * Paths.get("C:/Users/moham/Documents/data/data.csv"); Mongodb mdb = new
 * Mongodb();
 * 
 * String productName; String productCategory; String price; String images;
 * String productDesc;
 * 
 * String brand; String productSpecifications;
 * 
 * 
 * // Parse product_spec as a JSON string into a Map ObjectMapper mapper = new
 * ObjectMapper(); ObjectNode products = mapper.createObjectNode();
 * 
 * try (CSVReader csvReader = new CSVReader(new FileReader(su.toString()))) {
 * 
 * String[] row; boolean skipHeader = true; int rowCount = 0; // Initialize a
 * counter for rows int maxRows = 100; // You can change this limit based on
 * your need
 * 
 * while ((row = csvReader.readNext()) != null && rowCount < 50) { rowCount++;
 * 
 * if (skipHeader) { skipHeader = false; continue; }
 * 
 * try { // MongoDB connection logic (replace this with your actual logic)
 * 
 * // Extracting data from the row
 * 
 * productName = row[1]; productCategory = row[2]; price = row[3]; images =
 * row[4]; productDesc = row[5];
 * 
 * brand = row[6]; productSpecifications= row[7];
 * 
 * String cleanedCategory = productCategory.replaceAll("[\\[\\]\"]", "");
 * 
 * // Step 2: Split by comma, and trim spaces from each category String[]
 * categoriesArray = cleanedCategory.split("\\s*,\\s*");
 * 
 * // Optional: Convert array to List (if you prefer working with List)
 * ArrayNode categoriesNode = mapper.createArrayNode(); for (String category :
 * categoriesArray) { categoriesNode.add(category.trim()); // Trim and add each
 * category }
 * 
 * 
 * products.put("productName", productName); products.put("productCategory",
 * categoriesNode); products.put("price", price); products.put("images",
 * images); products.put("productDesc",productDesc);
 * 
 * products.put("brand", brand);
 * 
 * ObjectMapper objectMapper = new ObjectMapper(); Map<String, List<Map<String,
 * String>>> parsedData = objectMapper.readValue( productSpecifications,
 * HashMap.class);
 * 
 * // Get the product_specification list List<Map<String, String>>
 * product_specifications = parsedData.get("product_specification");
 * 
 * // Iterate over the list and check if any key is missing for (Map<String,
 * String> spec : product_specifications) { String key = spec.get("key"); String
 * value = spec.get("value");
 * 
 * // If the key is null or missing, assign a default key if (key == null ||
 * key.isEmpty()) { spec.put("key","Defaultkey"); spec.put(key, value); //key =
 * "DefaultKey"; // Default key name }
 * 
 * 
 * } JsonNode specificationsNode = mapper.valueToTree(product_specifications);
 * 
 * // Add the product_specifications to the products ObjectNode
 * products.set("productSpecifications", specificationsNode); // You can now
 * insert this data into MongoDB using your MongoDB object `mdb`.
 * 
 * 
 * 
 * System.out.println(Arrays.toString(row)); // Print row (for debugging)
 * 
 * } catch (Exception e) { // Catching exceptions during row processing
 * System.err.println("Error processing row: "); e.printStackTrace(); //
 * Optionally log the error continue; // Skip this row } }
 * 
 * mdb.mongoConnect(products);
 * 
 * } catch (Exception e) { // Catching errors related to reading the CSV file or
 * setting up the reader System.err.println("Error reading CSV file: ");
 * e.printStackTrace(); } } }
 */