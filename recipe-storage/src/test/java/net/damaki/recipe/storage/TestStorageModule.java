package net.damaki.recipe.storage;

import com.couchbase.client.CouchbaseClient;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import net.damaki.recipe.storage.backend.db.DbModule;
import net.damaki.recipe.storage.backend.index.IndexModule;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static net.damaki.recipe.storage.backend.db.DbModule.*;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 17/08/13
 * Time: 18:31
 */
public class TestStorageModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new IndexModule());
        install(new DbModule());

        bind(Directory.class).to(RAMDirectory.class).in(Singleton.class);
        bindConstant().annotatedWith(Names.named(COUCHBASE_HOST)).to("http://127.0.0.1:8091/pools");
        bindConstant().annotatedWith(Names.named(COUCHBASE_BUCKET)).to("test");
        bindConstant().annotatedWith(Names.named(COUCHBASE_PASSWORD)).to("");

        Names.bindProperties(binder(), System.getProperties());
    }

}
