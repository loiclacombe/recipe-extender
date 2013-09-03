package net.damaki.recipe.storage.backend.db;

import com.couchbase.client.CouchbaseClient;
import com.google.gson.Gson;
import net.damaki.recipe.storage.PersistenceException;
import net.damaki.recipe.storage.backend.index.RecipeIndexPersist;
import net.damaki.recipe.storage.beans.Recipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 17/08/13
 * Time: 16:18
 */
public class RecipeCouchbasePersistor {
    private static final Logger LOG = LoggerFactory.getLogger(RecipeCouchbasePersistor.class);
    private HashUtils hashUtils;
    private Gson gson;
    private CouchbaseClient couchbaseClient;

    @Inject
    public RecipeCouchbasePersistor(CouchbaseClient couchbaseClient, Gson gson, HashUtils hashUtils, RecipeIndexPersist recipeIndexPersist) {
        this.couchbaseClient = couchbaseClient;
        this.gson = gson;
        this.hashUtils = hashUtils;
    }



    public void persist(Recipe recipe) throws PersistenceException {

        try {
            recipe.setCreationDate(new Date());
            String jsonText = gson.toJson(recipe);

            String hash = hashUtils.computeHash(jsonText);

            saveInCouchbase(jsonText, hash);
            recipe.setId(hash);
        } catch (NoSuchAlgorithmException | InterruptedException | ExecutionException | IOException e) {
            throw new PersistenceException(e);
        }
    }



    void saveInCouchbase(String jsonText, String hash) throws InterruptedException, ExecutionException, IOException {
        LOG.debug("persisting value {} with key {}", jsonText, hash);
        couchbaseClient.set(hash, jsonText).get();
    }

}
