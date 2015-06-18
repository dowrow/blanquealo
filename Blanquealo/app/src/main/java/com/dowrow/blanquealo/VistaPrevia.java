package com.dowrow.blanquealo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.InputStream;
import java.util.List;


public class VistaPrevia extends ActionBarActivity {

    String tweet = "";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        Blanqueador blanqueador = new Blanqueador(this);
        super.onCreate(savedInstanceState);

        tweet = blanqueador.blanquear(getIntent().getStringExtra(MainActivity.BLANQUEALO_TWEET));

        setContentView(R.layout.activity_vista_previa);
        setTitle(R.string.vista_previa);
        ((TextView)findViewById(R.id.tweet_blanco)).setText(tweet);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

    public void twitear (View view) {
        String tweetCompleto = tweet + " vía @blanquealo";
        Intent tweetIntent = new Intent(Intent.ACTION_SEND);
        tweetIntent.putExtra(Intent.EXTRA_TEXT, tweetCompleto);
        tweetIntent.setType("text/plain");

        PackageManager packManager = getPackageManager();
        List<ResolveInfo> resolvedInfoList = packManager.queryIntentActivities(tweetIntent,  PackageManager.MATCH_DEFAULT_ONLY);

        boolean resolved = false;
        for(ResolveInfo resolveInfo: resolvedInfoList){
            if(resolveInfo.activityInfo.packageName.startsWith("com.twitter.android")){
                tweetIntent.setClassName(
                        resolveInfo.activityInfo.packageName,
                        resolveInfo.activityInfo.name );
                resolved = true;
                break;
            }
        }
        if(resolved){
            startActivity(tweetIntent);
        }else{
            Intent i = new Intent();
            i.putExtra(Intent.EXTRA_TEXT, tweetCompleto);
            i.setAction(Intent.ACTION_VIEW);
            i.setData(Uri.parse("https://twitter.com/intent/tweet?text=message&via=profileName"));
            startActivity(i);
            Toast.makeText(this, "No se encontr\u00D3 la app de Twitter", Toast.LENGTH_LONG).show();
        }
    }
}
