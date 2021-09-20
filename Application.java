package logExtractor;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 *  Java code to print list of unique extenstions with unique fileNames
 example: file1.pdf, file2.pdf, file2.ext, file2.pdf
 output: pdf: 2 
              ext: 1
 *
 */
public class Application {
//	converting String to python Dictionary(Json format)
	private JsonNode convertStringToJson(Object data) {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonData = mapper.convertValue(data, JsonNode.class);
		return jsonData;
	}
	
//	returns the fileName from the given Json
	private String analyseJson(Object data) {
		JsonNode jsonData = convertStringToJson(data);
		if(jsonData.has("nm")) {
			return jsonData.get("nm").asText();
		}
		return null;
	}
	
//	this method will take the data.json file which is saved in the resources.
	private JSONArray readData() throws IOException, ParseException {
		String fileName = "data.json";
		ClassLoader classLoader = Application.class.getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		FileReader fr=new FileReader(file);
		
		JSONParser jsonParser = new JSONParser();
		
		Object obj = jsonParser.parse(fr);
		JSONArray data = (JSONArray) obj;
		return data;
	}

	/**
	 * 
		Reads the file and analyse the Json log and prints the output in stdout 
	 * @param args
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void main(String[] args) throws IOException, ParseException {
		Application obj = new Application();
		JSONArray data = obj.readData();
		Map<String, Integer> unique_extensions = new HashMap<String, Integer>();
		List<String> unique_fileNames = new ArrayList<String>();
		data.forEach(log -> {
			String file_name = obj.analyseJson(log);
			if(!unique_fileNames.contains(file_name)) {
				unique_fileNames.add(file_name);
				String extension = file_name.substring(file_name.indexOf('.')+1);
				if(unique_extensions.containsKey(extension)) {
					unique_extensions.put(extension, unique_extensions.get(extension)+1);
				}
				else {
					unique_extensions.put(extension, 1);
				}
			}
			
		});
		unique_extensions.forEach((extension , value)-> {
			System.out.println(extension + ": " + value);
		});
		
		
	}

}
