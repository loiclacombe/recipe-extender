package net.damaki.recipe.storage;

import com.couchbase.client.CouchbaseClient;
import com.google.gson.Gson;
import net.damaki.recipe.storage.backend.index.RecipeIndexSearch;
import net.damaki.recipe.storage.beans.Recipe;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Provider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static java.util.Arrays.asList;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 17/08/13
 * Time: 17:04
 */
public class RecipesQueryFacade {
    private static final Logger LOG = LoggerFactory.getLogger(RecipesQueryFacade.class);
    private final CouchbaseClient couchbaseClient;
    private final Gson gson;
    private RecipeIndexSearch recipeIndexSearch;

    @Inject
    public RecipesQueryFacade(Gson gson, RecipeIndexSearch recipeIndexSearch, CouchbaseClient couchbaseClient) {
        this.gson = gson;
        this.recipeIndexSearch = recipeIndexSearch;
        this.couchbaseClient = couchbaseClient;
    }


    public Collection<Recipe> searchRecipes(String... query) throws IOException {
        Collection<String> ids = recipeIndexSearch.searchIndex(query);

        Collection<Recipe> recipes = loadRecipesById(ids);

        return recipes;
    }

    private Collection<Recipe> loadRecipesById(Collection<String> ids) {
        Collection<Recipe> recipes = new ArrayList<>();
        for (String curId : ids) {
            String json = (String) couchbaseClient.get(curId);
            recipes.add(gson.fromJson(json, Recipe.class));
        }
        return recipes;
    }


}
