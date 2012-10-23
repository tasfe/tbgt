package tbgt.freemarker;

import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import org.springframework.beans.factory.FactoryBean;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigurationFactory implements FactoryBean {

    @Override
    public Configuration getObject() throws Exception {
        Configuration cfg = new Configuration();
        cfg.setDefaultEncoding("utf-8");
        cfg.setClassForTemplateLoading(ConfigurationFactory.class,"/");
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

