package ipl.ipl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.dmoral.toasty.Toasty;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static ipl.ipl.R.id.main_layout;
import static ipl.ipl.R.id.snackbar_action;

public class MainActivity extends AppCompatActivity {

    String firstteam,firstteamscore,secondteam,secondteamscore;
    Intent serviceIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Teams"));
        tabLayout.addTab(tabLayout.newTab().setText("Fixtures"));

        Toasty.info(this, "To kill notification destroy app from the app tray", Toast.LENGTH_SHORT, true).show();

        //Initialzing service

        serviceIntent = new Intent(this,BackgroundService.class);
        startService(serviceIntent);

        //Receiving broadcast from BackgroundService

        LocalBroadcastManager.getInstance(this).registerReceiver(
                mMessageReceiver, new IntentFilter("LiveUpdates"));


        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id==R.id.action_github){
            MaterialStyledDialog dialog = new MaterialStyledDialog.Builder(this)
                    .setTitle("Liked this app ?")
                    .withDialogAnimation(true)
                    .setHeaderDrawable(R.drawable.header)
                    .setScrollable(true)
                    .setIcon(R.drawable.ic_github)
                    .withIconAnimation(true)
                    .setPositiveText("Cool, Let's GO !")
                    .setNegativeText("Maybe Later")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/adikul30")));
                        }
                    })
                    .setDescription("Check me out on GitHub")
                    .build();
            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    //This part is not needed right now...Added for future reference
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String message = intent.getStringExtra("Status");
            Bundle b = intent.getBundleExtra("ScoreSheet");
            firstteam =  b.getString("firstteam");
            firstteamscore =  b.getString("firstteamscore");
            secondteam =  b.getString("secondteam");
            secondteamscore =  b.getString("secondteamscore");
            Log.v("1",firstteam);
            Log.v("2",firstteamscore);
            Log.v("3",secondteam);
            Log.v("4",secondteamscore);


        }
    };
    //

}
