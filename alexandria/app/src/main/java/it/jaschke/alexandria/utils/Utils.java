package it.jaschke.alexandria.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;

import it.jaschke.alexandria.MainActivity;
import it.jaschke.alexandria.R;

/**
 * Created by laking on 2/16/2016.
 */
public class Utils {
    public static boolean isNetworkAvailable (Context c) {
        ConnectivityManager cm = (ConnectivityManager)
                c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = cm.getActiveNetworkInfo();
        return (network != null && network.isConnectedOrConnecting());
    }

    public static boolean checkNetwork (Context c) {
        boolean status = true;
        if (! Utils.isNetworkAvailable(c)) {
            Intent messageIntent = new Intent(MainActivity.MESSAGE_EVENT);
            messageIntent.putExtra(MainActivity.MESSAGE_KEY, c.getResources().getString(R.string.no_network));
            LocalBroadcastManager.getInstance(c.getApplicationContext()).sendBroadcast(messageIntent);
            status = false;
        }
        return status;
    }
}
