package pl.lukaszw.futuremind.ui.main.list.master;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pl.lukaszw.futuremind.FutureApp;
import pl.lukaszw.futuremind.R;
import pl.lukaszw.futuremind.data.viewmodel.DataViewModel;
import pl.lukaszw.futuremind.ui.main.base.BaseFragment;
import pl.lukaszw.futuremind.ui.main.list.master.item.ItemClickListener;
import pl.lukaszw.futuremind.ui.main.list.master.item.ListAdapter;

public class ListFragment extends BaseFragment<ListPresenter> implements ListContract.View, ItemClickListener<DataViewModel> {

    public static final String TAG = ListFragment.class.getSimpleName();

    @Inject
    ListPresenter mListPresenter;

    @BindView(R.id.list_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private ListAdapter mListAdapter;
    private ItemClickListener<DataViewModel> mItemClickListener;
    private ArrayList<DataViewModel> mData;
    private Unbinder mUnbinder;

    public ListFragment() {
    }

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mItemClickListener = (ItemClickListener<DataViewModel>) context;
        } catch (Exception ex) {
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((FutureApp) getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        initRecyclerView();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().loadData();
            }
        });

        return view;
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mListAdapter = new ListAdapter(getContext(), this);
        mRecyclerView.setAdapter(mListAdapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            getPresenter().loadData();
        } else {
            mData = savedInstanceState.getParcelableArrayList(DataViewModel.KEY);
            showData(mData);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void showLoader() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoader() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showData(ArrayList<DataViewModel> data) {
        mData = data;
        mListAdapter.setData(data);
    }

    @Override
    public ListPresenter getPresenter() {
        return mListPresenter;
    }

    @Override
    public void onItemClick(DataViewModel item) {
        mItemClickListener.onItemClick(item);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(DataViewModel.KEY, mData);
    }
}
