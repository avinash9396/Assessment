package logExtractor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ApplicationTest {
	
		Application application;
		
		@BeforeEach
		public void setup()
		{
			application = new Application();
		}
		
		public class Application {
//			
			private JsonNode convertStringToJson(Object data) {
				ObjectMapper mapper = new ObjectMapper();
				JsonNode jsonData = mapper.convertValue(data, JsonNode.class);
				return jsonData;
			}
		@Test
		
		private void analyseJson(Object data) {
			JsonNode jsonData = convertStringToJson(data);
			if(jsonData.has("nm")) {
				return;
			}
			return;
			
		}
		public JSONArray readData() {
			// TODO Auto-generated method stub
			return null;
		}
		
		}
		
		@Test
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
		
	
		@Test
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
	
	
	


