package pl.lukaszw.futuremind.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.lukaszw.futuremind.FutureApp;

/**
 * Created by Lukasz on 14.04.2017.
 */

@Module
public class AppModule {

    FutureApp mApplication;

    public AppModule(FutureApp mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    FutureApp provideApplication() {
        return mApplication;
    }
}