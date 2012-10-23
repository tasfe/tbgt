package tbgt.freemarker;

import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import org.springframework.beans.factory.FactoryBean;

import java.io.*;
import java.util.*;

public class ConfigurationFactory implements FactoryBean {

    @Override
    public Configuration getObject() throws Exception {
        Configuration cfg = new Configuration();
        cfg.setEncoding(Locale.CHINA,"utf-8");
        cfg.setClassForTemplateLoading(ConfigurationFactory.class, "/");
        cfg.setObjectWrapper(ObjectWrapper.DEFAULT_WRAPPER);
        return cfg;
    }

    @Override
    public Class<Configuration> getObjectType() {
        return Configuration.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}

