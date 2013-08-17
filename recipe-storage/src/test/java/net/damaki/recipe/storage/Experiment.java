package net.damaki.recipe.storage;

import com.google.inject.Guice;
import com.google.inject.Injector;
import net.damaki.recipe.storage.beans.Ingredient;
import net.damaki.recipe.storage.beans.Recipe;
import net.spy.memcached.compat.log.SLF4JLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 17/08/13
 * Time: 15:34
 */
public class Experiment {
    private static final Logger LOG = LoggerFactory.getLogger(Experiment.class);
    private PersistService persistService;
    private RecipesQueryFacade queryFacade;
    private LifeCycleManager lifeCycleManager;

    @Inject
    public Experiment(LifeCycleManager lifeCycleManager, PersistService persistService, RecipesQueryFacade queryFacade) {
        this.lifeCycleManager = lifeCycleManager;
        this.persistService = persistService;
        this.queryFacade = queryFacade;
    }

    public void startup() throws Exception {
        lifeCycleManager.startup();
        initDb();
    }

    private void initDb() throws Exception {
        Recipe recipe = new Recipe();

        Ingredient ingredient = new Ingredient();
        recipe.getIngredients().add(ingredient);
        ingredient.setName("farine");
        ingredient.setQuantity("120 g");

        ingredient = new Ingredient();
        recipe.getIngredients().add(ingredient);
        ingredient.setName("oeufs");
        ingredient.setQuantity("2");

        recipe.setDescription("Muffin");
        recipe.setName("Muffin");

        persistService.persist(recipe);
    }

    private void query(String... search) throws IOException {
        Collection<Recipe> result = queryFacade.searchRecipes(search);
        for (Recipe recipe : result) {
            LOG.info("Result: {}", recipe);
        }
    }

    public void shutdown() {
        lifeCycleManager.shutdown();
    }

    public static void main(String[] args) throws Exception {
        Properties systemProperties = System.getProperties();
        systemProperties.put("net.spy.log.LoggerImpl", SLF4JLogger.class.getCanonicalName());
        System.setProperties(systemProperties);

        Injector injector = Guice.createInjector(new TestStorageModule());

        LifeCycleManager lifeCycleManager = injector.getInstance(LifeCycleManager.class);
        Experiment experiment = null;
        try {
            experiment = injector.getInstance(Experiment.class);
            experiment.startup();
            experiment.query("Muffin");
        } finally {
            if (experiment != null) {
                experiment.shutdown();
            }
        }
    }


}
