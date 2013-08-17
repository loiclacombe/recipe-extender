package net.damaki.recipe.storage.backend.db;

import com.couchbase.client.CouchbaseClient;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import javax.inject.Named;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 17/08/13
 * Time: 22:36
 */
public class DbModule extends AbstractModule {
    public static final String COUCHBASE_HOST = "couchbase.host";
    public static final String COUCHBASE_BUCKET = "couchbase.bucket";
    public static final String COUCHBASE_PASSWORD = "couchbase.password";

    @Override
    protected void configure() {
        bind(DbLifeCycleManager.class).in(Singleton.class);
    }

    @Singleton
    @Provides
    public CouchbaseClient provideCouchbaseClient(
            @Named(COUCHBASE_HOST) String host,
            @Named(COUCHBASE_BUCKET) String bucket,
            @Named(COUCHBASE_PASSWORD) String password
    ) throws IOException, URISyntaxException {
        // Connect to the Cluster
        List<URI> hosts = Arrays.asList(
                new URI(host)
        );
        return new CouchbaseClient(hosts, bucket, password);
    }

}
