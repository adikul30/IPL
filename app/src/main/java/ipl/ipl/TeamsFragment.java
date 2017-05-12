package ipl.ipl;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;




public class TeamsFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    public TeamsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_teams, container, false);
        ArrayList<String> teams= new ArrayList<String>();
        teams.add("Mumbai Indians");
        teams.add("Delhi Daredevils");
        teams.add("Gujarat Lions");
        teams.add("Kings XI Punjab");
        teams.add("Kolkata Knight Riders");
        teams.add("Rising Pune Supergiants");
        teams.add("Royal Challengers Bangalore");
        teams.add("Sunrisers Hyderabad");

        final ListView teamslistView = (ListView)view.findViewById(R.id.teamlist);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1, teams);

        teamslistView.setAdapter(adapter);
        teamslistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putInt("position",position);
                Intent intent = new Intent(getActivity(),DetailActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
//        Button scoresButton = (Button) view.findViewById(R.id.score_button);
//        scoresButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(),Scores.class));
//            }
//        });
        return view;
    }


}
