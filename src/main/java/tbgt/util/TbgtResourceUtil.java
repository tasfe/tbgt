package tbgt.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;

public class TbgtResourceUtil {
    private static final TbgtResourceUtil tbgt_resource_util = new TbgtResourceUtil();
    private Properties prop = new Properties();

    public TbgtResourceUtil() {
        InputStream is = null;
        try {
            is = getClass().getResourceAsStream("/tbgt.properties");
            prop.load(is);

        } catch (Exception e) {
            //do nothing
        } finally {
            if (is != null)
                try {
                    is.close();
                } catch (IOException e) {
                    //do nothing
                }
        }
    }

    public static TbgtResourceUtil getInstance() {
        return tbgt_resource_util;
    }

    public String getMessage(String code) {
        return prop.getProperty(code);
    }
}
