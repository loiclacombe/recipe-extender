package net.damaki.recipe.storage;

import net.damaki.recipe.storage.backend.db.RecipeCouchbasePersistor;
import net.damaki.recipe.storage.backend.index.RecipeIndexPersist;
import net.damaki.recipe.storage.beans.Recipe;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 17/08/13
 * Time: 21:18
 */
@Stateless
@Remote(PersistService.class)
@LocalBean
public class PersistServiceBean implements PersistService {
    private RecipeCouchbasePersistor couchbasePersistor;

    private RecipeIndexPersist recipeIndexPersist;

    public PersistServiceBean() {
    }

    @Inject
    public void inject(RecipeCouchbasePersistor couchbasePersistor, RecipeIndexPersist recipeIndexPersist) {
        this.couchbasePersistor = couchbasePersistor;
        this.recipeIndexPersist = recipeIndexPersist;
    }

    @Override
    public void persist(Recipe recipe) throws PersistenceException {
        couchbasePersistor.persist(recipe);
        recipeIndexPersist.index(recipe);
    }
}
