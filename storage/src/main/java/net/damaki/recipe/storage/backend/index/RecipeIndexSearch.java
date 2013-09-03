package net.damaki.recipe.storage.backend.index;

import net.damaki.recipe.storage.backend.db.RecipeCouchbasePersistor;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
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
 * Time: 22:08
 */
public class RecipeIndexSearch {
    private static final Logger LOG = LoggerFactory.getLogger(RecipeIndexSearch.class);

    private final Provider<IndexSearcher> indexSearcherProvider;

    @Inject
    public RecipeIndexSearch(Provider<IndexSearcher> indexSearcherProvider) {
        this.indexSearcherProvider = indexSearcherProvider;
    }

    public Collection<String> searchIndex(String... query) throws IOException {
        Collection<String> ids = new ArrayList<>();

        LOG.debug("searchIndex: {}", asList(query));

        BooleanQuery booleanQuery = new BooleanQuery();
        for (String curWord : query) {
            String lower = curWord.toLowerCase();
            addSearchedField(booleanQuery, "steps", lower);
            addSearchedField(booleanQuery, "description", lower);
            addSearchedField(booleanQuery, "name", lower);
            addSearchedField(booleanQuery, "tags", lower);
            addSearchedField(booleanQuery, "ingredients", lower);
        }

        IndexSearcher searcher = indexSearcherProvider.get();
        TopDocs topDocs = searcher.search(booleanQuery, 20);

        LOG.debug("found {} matches", topDocs.totalHits);
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            Document doc = searcher.doc(scoreDoc.doc);
            String id = doc.get("id");
            ids.add(id);
            LOG.trace("Found match {}", id);
        }
        return ids;
    }

    private void addSearchedField(BooleanQuery booleanQuery, String field, String curWord) {
        booleanQuery.add(new TermQuery(new Term(field, curWord)), BooleanClause.Occur.SHOULD);
    }
}
