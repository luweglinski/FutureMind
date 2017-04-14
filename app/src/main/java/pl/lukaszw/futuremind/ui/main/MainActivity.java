package pl.lukaszw.futuremind.ui.main;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import pl.lukaszw.futuremind.R;

public class MainActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFragmentManager = getSupportFragmentManager();

        ListFragment mainActivityFragment = (ListFragment) mFragmentManager.findFragmentByTag(ListFragment.TAG);
        if (mainActivityFragment == null) {
            mainActivityFragment = ListFragment.newInstance();
        }

        mFragmentManager
                .beginTransaction()
                .replace(R.id.list_container, mainActivityFragment, ListFragment.TAG)
                .commit();
    }

}
