package pl.lukaszw.futuremind.data.net.services;

import io.realm.Realm;
import pl.lukaszw.futuremind.data.model.DataResponse;
import pl.lukaszw.futuremind.data.net.api.MainApi;
import retrofit2.Retrofit;
import rx.Observable;
import rx.functions.Func1;

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
        return mMainApi.getData()
                .map(new Func1<DataResponse, DataResponse>() {
                    @Override
                    public DataResponse call(DataResponse dataResponse) {
                        Realm realm = Realm.getDefaultInstance();

                        realm.beginTransaction();
                        realm.insertOrUpdate(dataResponse);
                        realm.commitTransaction();

                        return dataResponse;
                    }
                });
    }
}
