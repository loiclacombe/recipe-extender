import net.damaki.recipe.storage.PersistService;
import net.damaki.recipe.storage.beans.Recipe;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 19/08/13
 */
public class CreateRecipe {
    /*
    http://glassfish.java.net/javaee5/ejb/EJB_FAQ.html#StandaloneRemoteEJB
     */

    public static void main(String[] args) throws Exception {
        // Sets the container classpath
        Map<String, Object> properties = new HashMap<>();
        properties.put(EJBContainer.MODULES, new File("target/classes"));
        // Creates an Embedded Container and get the JNDI context
        try (EJBContainer ec = EJBContainer.createEJBContainer(properties)) {
            Context ctx = ec.getContext();

            PersistService bean = (PersistService) ctx.lookup(" java:global/storage-service/PersistService");

            Recipe recipe = new Recipe();
            recipe.setDescription("description");
            recipe.setName("myName");
            recipe.getSteps().add("step1");
            recipe.getIngredients().add("ing1");

            bean.persist(recipe);
        }


    }


}
