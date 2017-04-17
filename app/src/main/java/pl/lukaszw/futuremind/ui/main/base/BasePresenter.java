package pl.lukaszw.futuremind.ui.main.base;

import android.content.Context;

/**
 * Created by Lukasz on 14.04.2017.
 */

public interface BasePresenter<V> {

    void onCreate(Context context);

    void attachView(V view);

    void detachView();

    void onDestroy();
}
