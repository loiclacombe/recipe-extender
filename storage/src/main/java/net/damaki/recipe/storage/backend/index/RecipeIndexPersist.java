package net.damaki.recipe.storage.backend.index;

import com.google.common.base.Joiner;
import net.damaki.recipe.storage.PersistenceException;
import net.damaki.recipe.storage.backend.db.RecipeCouchbasePersistor;
import net.damaki.recipe.storage.beans.Recipe;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Provider;
import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 17/08/13
 * Time: 21:14
 */

public class RecipeIndexPersist {
    private static final Logger LOG = LoggerFactory.getLogger(RecipeCouchbasePersistor.class);

    private Provider<IndexWriter> indexWriterProvider;

    @Inject
    public RecipeIndexPersist(Provider<IndexWriter> indexWriterProvider) {
        this.indexWriterProvider = indexWriterProvider;
    }

    public void index(Recipe recipe) throws PersistenceException {
        IndexWriter indexWriter = indexWriterProvider.get();
        try {
            LOG.debug("Indexing...");
            checkNotNull(recipe, "recipe is null");
            checkNotNull(recipe.getId(), "recipe id is null");


            Document doc = new Document();
            doc.add(new TextField("id", recipe.getId(), Field.Store.YES));
            addIndexField(doc, "description", recipe.getDescription());
            addIndexField(doc, "name", recipe.getName());
            addIndexField(doc, "steps", Joiner.on(" ").join(recipe.getSteps()));
            addIndexField(doc, "tags", Joiner.on(" ").join(recipe.getTags()));
            indexIngredients(doc, recipe);

            indexWriter.addDocument(doc);
            indexWriter.commit();
            indexWriter.close();
        } catch (IOException e) {
            throw new PersistenceException(e);
        }
    }

    void indexIngredients(Document doc, Recipe recipe) {
        StringBuilder builder = new StringBuilder();
        for (String ingredient : recipe.getIngredients()) {
            builder.append(' ').append(ingredient);
        }
        addIndexField(doc, "ingredients", builder.toString());
    }

    private void addIndexField(Document doc, String key, String value) {
        doc.add(new TextField(key, value, Field.Store.NO));
    }


}
