package pl.kfrak.movierepos.service;

import java.util.List;

import io.reactivex.Observable;
import pl.kfrak.movierepos.model.Movie;
import retrofit2.http.GET;

/**
 * Created by RENT on 2017-07-07.
 */

public class MovieService {
    public interface MoviesApi {

        @GET("/api/json/get/bSciNrOTCa?indent=2")
        Observable<List<Movie>> getMovies();

    }
}
