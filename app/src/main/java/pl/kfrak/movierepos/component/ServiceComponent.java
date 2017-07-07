package pl.kfrak.movierepos.component;

import javax.inject.Singleton;

import dagger.Component;
import pl.kfrak.movierepos.MainActivity;
import pl.kfrak.movierepos.module.ServiceModule;

/**
 * Created by RENT on 2017-07-07.
 */

@Singleton
@Component (modules = ServiceModule.class)
public interface ServiceComponent {
    void inject(MainActivity activity);
}