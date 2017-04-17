package pl.lukaszw.futuremind.data.net.services;

import pl.lukaszw.futuremind.data.model.DataResponse;
import pl.lukaszw.futuremind.data.net.api.MainApi;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by Lukasz on 14.04.2017.
 */

public class MainService implements MainApi {

    private final MainApi mMainApi;

    public MainService(Retrofit retrofit) {
        mMainApi = retrofit.create(MainApi.class);
    }

    @Override
    public Observable<DataResponse> getData() {
        return mMainApi.getData();
    }
}
