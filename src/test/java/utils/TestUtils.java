package utils;

import java.util.UUID;

public class TestUtils {

	public static String generateRandomUsername() {
		return "user_" + UUID.randomUUID().toString().substring(0, 8);
	}
}
