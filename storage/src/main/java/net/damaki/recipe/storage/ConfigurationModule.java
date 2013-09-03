package net.damaki.recipe.storage;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 17/08/13
 * Time: 22:23
 */
public class ConfigurationModule  {
    @Produces
    @Named("conf_properties")
    private static final String props="${jboss.home.dir}/configuration/recipe-storage.properties";

}
