package pl.lukaszw.futuremind.ui.main.list.master;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import pl.lukaszw.futuremind.FutureApp;
import pl.lukaszw.futuremind.data.model.DataModel;
import pl.lukaszw.futuremind.data.model.DataResponse;
import pl.lukaszw.futuremind.data.net.services.MainService;
import pl.lukaszw.futuremind.data.viewmodel.DataViewModel;
import pl.lukaszw.futuremind.ui.main.base.BasePresenterImpl;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lukasz on 14.04.2017.
 */

public class ListPresenter extends BasePresenterImpl<ListContract.View> implements ListContract.Presenter {

    private MainService mMainService;
    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public ListPresenter(MainService mainService) {
        mMainService = mainService;
    }

    @Override
    public void injectSelf(Context context) {
        ((FutureApp) context.getApplicationContext()).getAppComponent().inject(this);
    }

    @Override
    public void loadData() {
        mCompositeSubscription.add(mMainService.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        getView().showLoader();
                    }
                })
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        getView().hideLoader();
                    }
                })
                .flatMapIterable(new Func1<DataResponse, List<DataModel>>() {
                    @Override
                    public List<DataModel> call(DataResponse dataResponse) {
                        return dataResponse.getDataModelList();
                    }
                })
                .map(new Func1<DataModel, DataViewModel>() {
                    @Override
                    public DataViewModel call(DataModel dataModel) {
                        return new DataViewModel(dataModel);
                    }
                })
                .toSortedList(new Func2<DataViewModel, DataViewModel, Integer>() {
                    @Override
                    public Integer call(DataViewModel dataViewModel, DataViewModel dataViewModel2) {
                        return dataViewModel.getOrderId() - dataViewModel2.getOrderId();
                    }
                })
                .subscribe(new Action1<List<DataViewModel>>() {
                    @Override
                    public void call(List<DataViewModel> dataViewModels) {
                        getView().showData(new ArrayList<>(dataViewModels));
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                }));
    }

    @Override
    public void onDestroy() {
        mCompositeSubscription.unsubscribe();
    }
}
