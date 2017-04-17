package pl.lukaszw.futuremind.ui.main.list.master;

import java.util.ArrayList;

import pl.lukaszw.futuremind.data.viewmodel.DataViewModel;
import pl.lukaszw.futuremind.ui.main.base.BasePresenter;
import pl.lukaszw.futuremind.ui.main.base.BaseView;

/**
 * Created by Lukasz on 14.04.2017.
 */

public interface ListContract {

    interface View extends BaseView<Presenter> {

        void showLoader();

        void hideLoader();

        void showData(ArrayList<DataViewModel> dataViewModel);
    }

    interface Presenter extends BasePresenter<View> {

        void loadData();
    }
}
