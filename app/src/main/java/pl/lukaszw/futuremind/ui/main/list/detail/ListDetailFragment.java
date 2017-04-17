package pl.lukaszw.futuremind.ui.main.list.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import pl.lukaszw.futuremind.R;
import pl.lukaszw.futuremind.ui.main.base.BaseFragment;

/**
 * Created by Lukasz on 17.04.2017.
 */

public class ListDetailFragment extends BaseFragment {

    public static final String TAG = ListDetailFragment.class.getSimpleName();

    private WebView mWebView;
    private String mWebUrl;

    public static ListDetailFragment newInstance(String webUrl) {
        ListDetailFragment fragment = new ListDetailFragment();
        Bundle args = new Bundle();
        args.putString(ArgKeys.WEB_URL_KEY, webUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWebUrl = getArguments().getString(ArgKeys.WEB_URL_KEY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_detail_fragment, container, false);

        mWebView = (WebView) view.findViewById(R.id.web_view);
        mWebView.loadUrl(mWebUrl);

        return view;
    }

    public interface ArgKeys {
        String WEB_URL_KEY = "WEB_URL";
    }
}
