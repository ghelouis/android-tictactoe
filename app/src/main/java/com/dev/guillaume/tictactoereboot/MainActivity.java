package com.dev.guillaume.tictactoereboot;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.dev.guillaume.tictactoereboot.tabs.GameBoardFragment;
import com.dev.guillaume.tictactoereboot.tabs.HistoryFragment;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Tic Tac Toe");

        ActionBar actionBar = getActionBar();
        ActionBar.Tab gameBoardTab = actionBar.newTab();
        ActionBar.Tab historyTab = actionBar.newTab();

        HistoryFragment historyFragment = new HistoryFragment();
        GameBoardFragment gameBoardFragment = new GameBoardFragment();
        gameBoardFragment.setHistoryFragment(historyFragment);

        gameBoardTab.setTabListener(new MyTabListener(gameBoardFragment));
        gameBoardTab.setText("GAME BOARD");
        historyTab.setTabListener(new MyTabListener(historyFragment));
        historyTab.setText("HISTORY");
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.addTab(gameBoardTab);
        actionBar.addTab(historyTab);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
