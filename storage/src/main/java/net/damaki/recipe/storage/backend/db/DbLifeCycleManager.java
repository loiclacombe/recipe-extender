package net.damaki.recipe.storage.backend.db;

import com.couchbase.client.CouchbaseClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 17/08/13
 * Time: 22:45
 */
@ApplicationScoped
public class DbLifeCycleManager {
    private CouchbaseClient client;

    @Inject
    public DbLifeCycleManager(CouchbaseClient client) {
        this.client = client;
    }


    public synchronized void startup(){

    }

    public synchronized void shutdown(){
        if (client != null) {
            client.flush();
            client.shutdown();
        }
    }
}
