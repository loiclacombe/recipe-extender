package net.damaki.recipe.storage;

import net.damaki.recipe.storage.backend.db.RecipeCouchbasePersistor;
import net.damaki.recipe.storage.backend.index.RecipeIndexPersist;
import net.damaki.recipe.storage.beans.Recipe;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 17/08/13
 * Time: 21:18
 */
public class PersistService {
    private RecipeCouchbasePersistor couchbasePersistor;
    private RecipeIndexPersist recipeIndexPersist;

    @Inject
    public PersistService(RecipeCouchbasePersistor couchbasePersistor, RecipeIndexPersist recipeIndexPersist) {
        this.couchbasePersistor = couchbasePersistor;
        this.recipeIndexPersist = recipeIndexPersist;
    }

    public void persist(Recipe recipe) throws PersistenceException {
        couchbasePersistor.persist(recipe);
        recipeIndexPersist.index(recipe);
    }
}
