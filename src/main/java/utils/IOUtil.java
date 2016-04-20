package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by hanqingwu on 2015/10/29.
 */
public final class IOUtil {

    private static Logger LOG = LoggerFactory.getLogger(IOUtil.class);

    private IOUtil() {

    }

    public static String getBodyString(BufferedReader br) {
        String inputLine;
        StringBuilder s = new StringBuilder();
        try {
            while ((inputLine = br.readLine()) != null) {
                s.append(inputLine);
            }
            br.close();
        } catch (IOException e) {
            LOG.info("IOException: " + e);

        }
        return s.toString();
    }

}
