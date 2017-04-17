package pl.lukaszw.futuremind.data.net.api;

import pl.lukaszw.futuremind.data.model.DataResponse;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Lukasz on 14.04.2017.
 */

public interface MainApi {

    @GET(".")
    Observable<DataResponse> getData();
}
