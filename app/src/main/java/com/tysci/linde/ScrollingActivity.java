package com.tysci.linde;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import utils.linde.library.calendar.DateFormatUtils;
import utils.linde.library.calendar.TimeFormatEnum;
import utils.linde.library.calendar.TimeZoneEnum;

public class ScrollingActivity extends AppCompatActivity
{
    //    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                final String time = DateFormatUtils.getInstance().getString(TimeZoneEnum.DEFAULT, TimeFormatEnum.yyyy_mm_dd_HH_mm_ss, 2015, 2, 11);
                Snackbar.make(fab, time, Snackbar.LENGTH_LONG).show();
            }
        });
//        final String[] apis = Build.SUPPORTED_ABIS;
//        for (String abi : apis)
//        {
//            LogUtils.logE("SUPPORTED_ABIS", abi);
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
