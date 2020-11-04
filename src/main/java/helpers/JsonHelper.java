package helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHelper {
	
	private static ObjectMapper objectMapper = getDefaultMapper();
	
	static ObjectMapper getDefaultMapper() {
		
		ObjectMapper defaultMapper = new ObjectMapper();
		defaultMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return defaultMapper;
		
	}
	
	public static JsonNode parse(String src) throws JsonProcessingException {
		
		return objectMapper.readTree(src);
		
	}
	
}


