package net.damaki.recipe.storage;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 17/08/13
 * Time: 22:23
 */
public class ConfigurationModule extends AbstractModule {
    @Override
    protected void configure() {

        bindConstant().annotatedWith(Names.named("conf_properties"))
                .to("${jboss.home.dir}/configuration/recipe-storage.properties");


    }
}
