package com.bove.martin.moviedb;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bove.martin.moviedb.ui.MovieFragmentViewModel;

public class MainActivity extends AppCompatActivity {
    private MovieFragmentViewModel viewModel;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MovieFragmentViewModel.class);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("MoviewDB App");
        

        ConnectivityManager conMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        if (conMgr != null) {
            if ( conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED
                    || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED ) {

                ab.setSubtitle("online version");

            }
            else if ( conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.DISCONNECTED
                    || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.DISCONNECTED) {

                ab.setSubtitle("offline version");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.view_menu, menu);
        this.menu = menu;

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.gridMenuItem:
                viewModel.setGridView();
                menu.findItem(R.id.listMenuItem).setVisible(true);
                menu.findItem(R.id.gridMenuItem).setVisible(false);
                return true;
            case R.id.listMenuItem:
                viewModel.setListVIew();
                menu.findItem(R.id.listMenuItem).setVisible(false);
                menu.findItem(R.id.gridMenuItem).setVisible(true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}