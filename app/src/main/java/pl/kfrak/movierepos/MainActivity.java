package pl.kfrak.movierepos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pl.kfrak.movierepos.component.DaggerServiceComponent;
import pl.kfrak.movierepos.component.ServiceComponent;
import pl.kfrak.movierepos.model.Movie;
import pl.kfrak.movierepos.module.ServiceModule;
import pl.kfrak.movierepos.service.MovieService;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    ServiceComponent serviceComponent;
    private MovieService.MoviesApi moviesApi;
    private List<Movie> moviesList = new ArrayList<>();

    @Inject
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        injectServiceComponent();
        ButterKnife.bind(this);
    }

    public void injectServiceComponent() {
        String url = "http://www.json-generator.com";
        serviceComponent = DaggerServiceComponent.builder()
                .serviceModule(new ServiceModule(url))
                .build();
        serviceComponent.inject(this);
        moviesApi = retrofit.create(MovieService.MoviesApi.class);
    }

    private void fetchMovie(){
        moviesApi.getMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Movie>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<Movie> movies) {
                        moviesList.addAll(movies);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @OnClick(R.id.dowlnoad_btn)
    public void dowlnoadMovies(){
        fetchMovie();
    }

}
