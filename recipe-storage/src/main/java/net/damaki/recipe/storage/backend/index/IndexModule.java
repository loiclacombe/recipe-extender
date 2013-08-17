package net.damaki.recipe.storage.backend.index;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.Version;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 17/08/13
 * Time: 22:21
 */
public class IndexModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Version.class).toInstance(Version.LUCENE_44);
    }

    @Provides
    public Analyzer provideAnalyzer(Version version) {
        StandardAnalyzer analyzer = new StandardAnalyzer(version);
        return analyzer;
    }

    @Provides
    public IndexWriter provideIndexWriter(Version version, Analyzer analyzer, Directory index) throws IOException {
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_44, analyzer);

        IndexWriter indexWriter = new IndexWriter(index, config);
        return indexWriter;
    }

    @Provides
    public IndexSearcher provideIndexSearcher(Directory index) throws IOException {
        return new IndexSearcher(DirectoryReader.open(index));
    }
}
