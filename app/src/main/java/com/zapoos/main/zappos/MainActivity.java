package com.zapoos.main.zappos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.zapoos.main.zappos.fragment.DetailsFragment;
import com.zapoos.main.zappos.fragment.ProductFragment;


public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ProductFragment())
                    .commit();
            if (getIntent().getData() != null) {
                String[] path = getIntent().getData().getPath().split("/");
                String asin = path[path.length - 1];
                openDetailsFragment(asin);
            }
        }

    }

    private void openDetailsFragment(String asin) {
        DetailsFragment detailsFragment = DetailsFragment.newInstance(asin);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, detailsFragment).addToBackStack(null).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
