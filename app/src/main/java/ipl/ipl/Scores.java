package ipl.ipl;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.RemoteInput;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
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

public class Scores extends AppCompatActivity {
    private Timer timer;
    private TimerTask timerTask;

    TextView team1, team2, team1score, team2score;
    NotificationCompat.Builder builder;
    NotificationManager mNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Score");

        //Notification try starts here

        builder = (NotificationCompat.Builder) new NotificationCompat.Builder(Scores.this)
                .setSmallIcon(R.drawable.ic_github)
                //.setDefaults(Notification.DEFAULT_SOUND)
                .setOngoing(true);

        Intent notifyIntent = new Intent(Scores.this, Scores.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(Scores.this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(notifyIntent);
        final PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        builder.setContentIntent(resultPendingIntent);
        mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        final NotificationCompat.InboxStyle inboxStyle =
                new NotificationCompat.InboxStyle();


        //Notification Try ends here

        Toast.makeText(Scores.this, "Score will be autoupdated in 10 sec", Toast.LENGTH_SHORT).show();

        team1 = (TextView) findViewById(R.id.team1);
        team1score = (TextView) findViewById(R.id.team1_score);
        team2 = (TextView) findViewById(R.id.team2);
        team2score = (TextView) findViewById(R.id.team2_score);

        try {
            timer = new Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    RequestQueue queue = Volley.newRequestQueue(Scores.this);
                    final String url = "http://stabsiesgst.tk/api/score.php";

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {

                                        JSONObject jsonObj = new JSONObject(response);
                                        JSONArray result = jsonObj.optJSONArray("result");

                                        JSONObject first = result.optJSONObject(0);
                                        String firstteam = first.optString("team");
                                        String firstteamscore = first.optString("score");
                                        team1.setText(firstteam);
                                        team1score.setText(firstteamscore);

                                        JSONObject second = result.optJSONObject(1);
                                        String secondteam = second.optString("team");
                                        String secondteamscore = second.optString("score");
                                        team2.setText(secondteam);
                                        team2score.setText(secondteamscore);

                                        String[] events = new String[6];

                                        events[0]=firstteam;
                                        events[1]=firstteamscore;
                                        events[2]=secondteam;
                                        events[3]=secondteamscore;

                                        for (int i = 0; i < events.length; i++) {

                                            inboxStyle.addLine(events[i]);
                                        }
                                        builder.setStyle(inboxStyle);
                                        builder.setContentTitle("IPL");
                                        builder.setContentText("Live Scores");

                                        mNotificationManager.notify(0, builder.build());


                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    queue.add(stringRequest);
                }
            };
            //timer.schedule(timerTask, 10000, 10000);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        timer.schedule(timerTask, 10000, 10000);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mNotificationManager.cancel(0);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
