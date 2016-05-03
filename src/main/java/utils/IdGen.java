package utils;

import java.security.SecureRandom;
import java.util.UUID;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 
 * @author ThinkGem
 * @version 2013-01-15
 */
@Service
@Lazy(false)
public final class IdGen {

	private static SecureRandom random = new SecureRandom();

	private IdGen() {

	}
	
	/**
	 * 
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	

	/**
	 * 
	 */
	public static long randomLong() {
		long ll = random.nextLong();
		return ll < 0 ? -ll : ll;
	}

	public static String randomInt(int length) {
		return String.valueOf(random.nextDouble()).substring(2, length+2);
	}
	
	
}
