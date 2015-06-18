package com.dowrow.blanquealo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class MainActivity extends ActionBarActivity {

    public static final String BLANQUEALO_TWEET = "BLANQUEALO_TWEET";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

    public void blanquear (View view) {
        String tweet;
        tweet = ((EditText)findViewById(R.id.tweet)).getText().toString();

        if (tweet.length() == 0) {
            Toast.makeText(this, "No has escrito ning\u00FAn tweet", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(this, VistaPrevia.class);
            intent.putExtra(BLANQUEALO_TWEET, tweet);
            startActivity(intent);
        }

    }
}
