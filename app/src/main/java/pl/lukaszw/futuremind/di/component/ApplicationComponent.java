package pl.lukaszw.futuremind.di.component;

import javax.inject.Singleton;

import dagger.Component;
import pl.lukaszw.futuremind.di.module.AppModule;
import pl.lukaszw.futuremind.di.module.NetModule;
import pl.lukaszw.futuremind.ui.main.list.master.ListFragment;
import pl.lukaszw.futuremind.ui.main.list.master.ListPresenter;

/**
 * Created by Lukasz on 14.04.2017.
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface ApplicationComponent {
    void inject(ListFragment fragment);

    void inject(ListPresenter activity);
}
