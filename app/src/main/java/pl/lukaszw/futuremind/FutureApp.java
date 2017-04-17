package pl.lukaszw.futuremind;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import pl.lukaszw.futuremind.di.component.ApplicationComponent;
import pl.lukaszw.futuremind.di.component.DaggerApplicationComponent;
import pl.lukaszw.futuremind.di.module.AppModule;
import pl.lukaszw.futuremind.di.module.NetModule;

/**
 * Created by Lukasz on 14.04.2017.
 */

public class FutureApp extends Application {

    private ApplicationComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerApplicationComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(getString(R.string.base_service_url)))
                .build();

        Realm.init(this);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public ApplicationComponent getAppComponent() {
        return mAppComponent;
    }
}
