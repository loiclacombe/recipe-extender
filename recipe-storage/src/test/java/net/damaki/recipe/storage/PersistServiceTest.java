package net.damaki.recipe.storage;

import net.damaki.recipe.storage.backend.db.RecipeCouchbasePersistor;
import net.damaki.recipe.storage.backend.index.RecipeIndexPersist;
import net.damaki.recipe.storage.beans.Recipe;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.createStrictControl;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 17/08/13
 * Time: 22:15
 * To change this template use File | Settings | File Templates.
 */
public class PersistServiceTest {
    PersistService persistService;

    IMocksControl control=createStrictControl();
    RecipeCouchbasePersistor couchbasePersistor = control.createMock(RecipeCouchbasePersistor.class);
    RecipeIndexPersist recipeIndex = control.createMock(RecipeIndexPersist.class);


    @Before
    public void setUp() throws Exception {
        persistService=new PersistService(couchbasePersistor, recipeIndex);
    control.reset();
    }

    @Test
    public void testPersist() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setName("kebab");

        couchbasePersistor.persist(recipe);
        recipeIndex.index(recipe);

        control.replay();
        persistService.persist(recipe);
        control.verify();
    }
}
