package net.damaki.recipe.storage;

import net.damaki.recipe.storage.beans.Recipe;


/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 19/08/13
 * Time: 17:14
 */

public interface PersistService {
    void persist(Recipe recipe) throws PersistenceException;
}
