package ipl.ipl;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> teams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teams = new ArrayList<>();
        teams.add("Mumbai Indians");
        teams.add("Delhi Daredevils");
        teams.add("Gujarat Lions");
        teams.add("Kings XI Punjab");
        teams.add("Kolkata Knight Riders");
        teams.add("Rising Pune Supergiants");
        teams.add("Royal Challengers Bangalore");
        teams.add("Sunrisers Hyderabad");

        final ListView teamslistView = (ListView) findViewById(R.id.teamlist);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                MainActivity.this, android.R.layout.simple_list_item_1, teams);

        teamslistView.setAdapter(adapter);
        teamslistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.v("position",String.valueOf(position));
                Bundle bundle = new Bundle();
                bundle.putInt("position",position);
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

}
