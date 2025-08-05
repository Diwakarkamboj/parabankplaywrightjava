package utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
	private static final ObjectMapper mapper = new ObjectMapper();


	public static <T> T getTestData(String path, Class<T> type) {
		
		try{
			System.out.println("Resolved path: " + Path.of("src/test/resources/" + path).toAbsolutePath());

	        InputStream stream = ResourceLoader.getResource(path);
	        String jsonRaw = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
	        System.out.println("Raw JSON = " + jsonRaw);

	        InputStream newStream = new ByteArrayInputStream(jsonRaw.getBytes(StandardCharsets.UTF_8));
	        return mapper.readValue(newStream, type);

		}catch (Exception e) {
			log.error("unable to read path {}", path, e);
		}

		return null;

	}


}
