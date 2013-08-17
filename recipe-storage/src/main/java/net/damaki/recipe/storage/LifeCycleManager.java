package net.damaki.recipe.storage;

import com.couchbase.client.CouchbaseClient;
import net.damaki.recipe.storage.backend.db.DbLifeCycleManager;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 17/08/13
 * Time: 21:02
 */

public class LifeCycleManager {
    private DbLifeCycleManager dbLifeCycleManager;

    @Inject
    public LifeCycleManager(DbLifeCycleManager dbLifeCycleManager) {
        this.dbLifeCycleManager = dbLifeCycleManager;
    }


    public synchronized void startup(){
        dbLifeCycleManager.startup();
    }

    public synchronized void shutdown(){
        dbLifeCycleManager.shutdown();
    }
}
