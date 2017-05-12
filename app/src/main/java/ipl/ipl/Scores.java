package ipl.ipl;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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

    TextView team1,team2,team1score,team2score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Score");




        Toast.makeText(Scores.this,"Score will be autoupdated every 10 sec",Toast.LENGTH_LONG).show();

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
                    // Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
//                                    mTextView.setText("Response is: "+ response.substring(0,500));
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

                                    } catch (Exception e) {
                                        e.printStackTrace();

                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
//                            mTextView.setText("That didn't work!");

                            Toast.makeText(Scores.this,"Check your internet",Toast.LENGTH_SHORT).show();


                        }
                    });
                    queue.add(stringRequest);
                }
            };
            timer.schedule(timerTask, 10000, 10000);
        } catch (IllegalStateException e) {
            android.util.Log.i("Damn", "resume error");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


}
