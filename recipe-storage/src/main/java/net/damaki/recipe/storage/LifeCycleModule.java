package net.damaki.recipe.storage;

import com.google.inject.AbstractModule;
import net.damaki.recipe.storage.backend.db.DbLifeCycleManager;

import javax.inject.Singleton;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 17/08/13
 * Time: 22:46
 */
public class LifeCycleModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(LifeCycleManager.class).in(Singleton.class);
    }
}
