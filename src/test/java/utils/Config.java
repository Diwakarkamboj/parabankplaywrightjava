package utils;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Config {

	private static final Logger log = LoggerFactory.getLogger(Config.class);
	private static final String DEFAULT_PROPERTIES = "config/default.properties";
	private static Properties properties;

	public static void initialized() {

		//load default properties then check for override
		properties = loadProperties();

		for(String Key: properties.stringPropertyNames()) {
			if(System.getProperties().containsKey(Key)) {
				properties.setProperty(Key, System.getProperty(Key));
			}
		}
		//print
		log.info("Test Properties");
		log.info("-------------------");
		for(String Key: properties.stringPropertyNames()) {
			log.info("{}={}", Key, properties.getProperty(Key));
		}
	}

	public static String get(String Key) {
		return properties.getProperty(Key);
	}


	private static Properties loadProperties() {
		Properties properties = new Properties();
		try(InputStream stream = ResourceLoader.getResource(DEFAULT_PROPERTIES)){
			properties.load(stream);

		}catch(Exception e) {
			log.error("unable to read the property file {}", DEFAULT_PROPERTIES, e);
		}
		return properties;
	}

}
