package ipl.ipl;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by adicool on 15/5/17.
 */

public class BackgroundService extends Service {

    private Timer timer;
    private TimerTask timerTask;

    int NOTIFICATION_ID = 0;

    //TextView team1, team2, team1score, team2score;
    NotificationCompat.Builder builder;
    public NotificationManager mNotificationManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();


        //Toast.makeText(BackgroundService.this, "Score will be autoupdated in 10 sec", Toast.LENGTH_SHORT).show();

        try {
            timer = new Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    RequestQueue queue = Volley.newRequestQueue(BackgroundService.this);
                    final String url = "http://stabsiesgst.tk/api/score.php";

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {

                                        //On clicking the notification it enters MainActivity.class

                                        Intent notifyIntent = new Intent(BackgroundService.this, MainActivity.class);

                                        TaskStackBuilder stackBuilder = TaskStackBuilder.create(BackgroundService.this);
                                        stackBuilder.addParentStack(MainActivity.class);
                                        stackBuilder.addNextIntent(notifyIntent);
                                        final PendingIntent resultPendingIntent =
                                                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

                                        mNotificationManager =
                                                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                                        //Building the notification

                                        final NotificationCompat.InboxStyle inboxStyle =
                                                new NotificationCompat.InboxStyle();
                                        builder = (NotificationCompat.Builder) new NotificationCompat.Builder(BackgroundService.this)
                                                .setSmallIcon(R.drawable.ic_github)
                                                //.setDefaults(Notification.DEFAULT_SOUND)
                                                .setOngoing(true)
                                                .setContentTitle("IPL")
                                                .setContentText("Live Scores")
                                                .setContentIntent(resultPendingIntent);

                                        //Getting values from JSON

                                        Log.v("Background onresponse",response);
                                        JSONObject jsonObj = new JSONObject(response);
                                        JSONArray result = jsonObj.optJSONArray("result");

                                        JSONObject first = result.optJSONObject(0);
                                        String firstteam = first.optString("team");
                                        String firstteamscore = first.optString("score");

                                        JSONObject second = result.optJSONObject(1);
                                        String secondteam = second.optString("team");
                                        String secondteamscore = second.optString("score");

                                        //This will show on different lines in Notifications
                                        String[] events = new String[6];

                                        events[0]=firstteam;
                                        events[1]=firstteamscore;
                                        events[2]=secondteam;
                                        events[3]=secondteamscore;

                                        for (int i = 0; i < events.length; i++) {

                                            inboxStyle.addLine(events[i]);
                                        }
                                        builder.setStyle(inboxStyle);

                                        // This is used to send data to MainActivity through a BroadcastReceiver

                                        Intent intent = new Intent("LiveUpdates");
                                        intent.putExtra("Status", "extra");
                                        Bundle b = new Bundle();

                                        b.putString("firstteam", firstteam);
                                        b.putString("firstteamscore", firstteamscore);
                                        b.putString("secondteam", secondteam);
                                        b.putString("secondteamscore", secondteamscore);

                                        intent.putExtra("ScoreSheet",b);
                                        LocalBroadcastManager.getInstance(BackgroundService.this).sendBroadcast(intent);


                                        mNotificationManager.notify(0, builder.build());


                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.v("Background Service","inside onerror");

                        }
                    });
                    queue.add(stringRequest);
                }
            };
            timer.schedule(timerTask, 10000, 10000);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        //Notification code block ends here


    }

    @Override
    public void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mNotificationManager.cancel(0);
        timer.cancel();
        return super.onUnbind(intent);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        timer.cancel();
        mNotificationManager.cancel(0);
        stopSelf();
        super.onTaskRemoved(rootIntent);
    }
}


