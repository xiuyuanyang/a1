package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

public final class Global {

    private Global() {

    }
    
    private static Map<String, String> map = new HashMap<String, String>();
    
	private static Resource resource = new DefaultResourceLoader().getResource("config.properties");

	private static Properties props = new Properties();
    
	static {
		InputStream is = null;
		try {
			is = resource.getInputStream();
			props.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    public static String getConfig(String key) {
        String value = map.get(key);
        if (value == null){
            value = props.getProperty(key);
            map.put(key, value);
        }
        return value;
    }
    
}
