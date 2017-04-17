package pl.lukaszw.futuremind.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import pl.lukaszw.futuremind.R;
import pl.lukaszw.futuremind.data.viewmodel.DataViewModel;
import pl.lukaszw.futuremind.ui.main.list.detail.ListDetailActivity;
import pl.lukaszw.futuremind.ui.main.list.detail.ListDetailFragment;
import pl.lukaszw.futuremind.ui.main.list.master.item.ItemClickListener;

public class MainActivity extends AppCompatActivity implements ItemClickListener<DataViewModel> {

    private boolean isTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FrameLayout fragmentItemDetail = (FrameLayout) findViewById(R.id.list_detail_container);
        if (fragmentItemDetail != null) {
            isTwoPane = true;
        }
    }

    @Override
    public void onItemClick(DataViewModel item) {
        if (isTwoPane) {
            ListDetailFragment listDetailFragment = ListDetailFragment.newInstance(item.getWebUrl());
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.list_detail_container, listDetailFragment)
                    .commit();
        } else {
            Intent i = new Intent(this, ListDetailActivity.class);
            i.putExtra(ListDetailFragment.ArgKeys.WEB_URL_KEY, item.getWebUrl());
            startActivity(i);
        }
    }
}
