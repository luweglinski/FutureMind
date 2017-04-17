package pl.lukaszw.futuremind.ui.main.base;

import android.content.Context;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

/**
 * Created by Lukasz on 14.04.2017.
 */

public abstract class BasePresenterImpl<V extends BaseView> implements BasePresenter<V> {

    protected WeakReference<V> mView;

    public V getView() {
        return mView != null ? mView.get() : null;
    }

    public void onCreate(Context context) {
        injectSelf(context);
    }

    public abstract void injectSelf(Context context);

    public void attachView(@NonNull V view) {
        mView = new WeakReference<V>(view);
    }

    public  void detachView() {
        mView = null;
    }

    public abstract void onDestroy();
}