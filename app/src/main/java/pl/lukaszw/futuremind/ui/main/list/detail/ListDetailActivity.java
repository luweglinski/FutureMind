package pl.lukaszw.futuremind.ui.main.list.detail;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import pl.lukaszw.futuremind.R;

/**
 * Created by Lukasz on 17.04.2017.
 */

public class ListDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_detail_activity);

        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        // Get the requested task id
        String webUrl = getIntent().getStringExtra(ListDetailFragment.ArgKeys.WEB_URL_KEY);

        if (savedInstanceState == null) {
            ListDetailFragment listDetailFragment = ListDetailFragment.newInstance(webUrl);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.list_detail_container, listDetailFragment)
                    .commit();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
