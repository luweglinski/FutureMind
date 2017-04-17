package pl.lukaszw.futuremind.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.lukaszw.futuremind.FutureApp;
import pl.lukaszw.futuremind.data.net.services.MainService;
import pl.lukaszw.futuremind.ui.main.list.master.ListPresenter;

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

    @Provides
    @Singleton
    ListPresenter provideListPresenter(MainService mainService) {
        return new ListPresenter(mainService);
    }
}