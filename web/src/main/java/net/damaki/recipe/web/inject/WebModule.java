package net.damaki.recipe.web.inject;

import net.damaki.recipe.storage.PersistService;
import net.damaki.recipe.storage.PersistServiceImpl;
import net.damaki.recipe.storage.backend.db.DbModule;
import net.damaki.recipe.storage.backend.db.RecipeCouchbasePersistor;
import net.damaki.recipe.storage.backend.index.IndexModule;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 18/08/13
 * Time: 13:19
 */
public class WebModule {
    private static final Logger LOG = LoggerFactory.getLogger(WebModule.class);

    private Properties properties;

    public WebModule(Properties systemProperties, ServletContext aServletContext) {
        properties=new Properties();
        String jbossDir = systemProperties.getProperty("jboss.home.dir");
        try {
            String confPath = (String) aServletContext.getInitParameter("configurationFile");
            File file = new File(jbossDir,
                    confPath);
            properties.load(new FileInputStream(file));
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        properties.putAll(systemProperties);
        LOG.debug("Loaded configuration keys: {}", properties);
    }


}
