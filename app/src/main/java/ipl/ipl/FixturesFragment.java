package ipl.ipl;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import static android.R.attr.typeface;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FixturesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FixturesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FixturesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList<Fixtures> fixtures;

    private OnFragmentInteractionListener mListener;

    public FixturesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FixturesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FixturesFragment newInstance(String param1, String param2) {
        FixturesFragment fragment = new FixturesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fixtures, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.team_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        fixtures = new ArrayList<Fixtures>();

        String jsondata ="{\n" +
                "    \"result\":[\n" +
                "        {\n" +
                "            \"date\":\"5 April\",\n" +
                "            \"title\":\"Sunrisers Hyderabad vs Royal Challengers Bangalore\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Bangalore\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"6 April\",\n" +
                "            \"title\":\"Rising Pune Super Giants vs Mumbai Indians\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Pune\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"7 April\",\n" +
                "            \"title\":\"Gujarat Lions vs Kolkata Knight Riders\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Rajkot\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"8 April\",\n" +
                "            \"title\":\"Kings XI Punjab vs Rising Pune Super Giants\",\n" +
                "            \"time\":\"4:00 pm\",\n" +
                "            \"location\":\"Venue : Indore\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"8 April\",\n" +
                "            \"title\":\"Royal Challengers Bangalore vs Delhi Daredevils\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Bengaluru\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"9 April\",\n" +
                "            \"title\":\"Sunrisers Hyderabad vs Gujarat Lions\",\n" +
                "            \"time\":\"4:00 pm\",\n" +
                "            \"location\":\"Venue : Hyderabad\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"9 April\",\n" +
                "            \"title\":\"Mumbai Indians vs Kolkata Knight Riders\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Mumbai\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"10 April\",\n" +
                "            \"title\":\"Kings XI Punjab vs Royal Challengers Bangalore\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Indore\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"11 April\",\n" +
                "            \"title\":\"Rising Pune Super Giants vs  Delhi Daredevils\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Pune\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"12 April\",\n" +
                "            \"title\":\"Mumbai Indians vs Sunrisers Hyderabad\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Mumbai\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"13 April\",\n" +
                "            \"title\":\"Kolkata Knight Riders vs Kings XI Punjab\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Kolkata\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"14 April\",\n" +
                "            \"title\":\"Royal Challengers Bangalore vs Mumbai Indians\",\n" +
                "            \"time\":\"4:00 pm\",\n" +
                "            \"location\":\"Venue : Bengaluru\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"14 April\",\n" +
                "            \"title\":\"Gujarat Lions vs Rising Pune Super Giants\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Hyderabad\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"15 April\",\n" +
                "            \"title\":\"Kolkata Knight Riders vs Sunrisers Hyderabad\",\n" +
                "            \"time\":\"4:00 pm\",\n" +
                "            \"location\":\"Venue : Rajkot\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"15 April\",\n" +
                "            \"title\":\"Delhi Daredevils vs Kings XI Punjab\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Delhi\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"16 April\",\n" +
                "            \"title\":\"Mumbai Indians vs Gujarat Lions\",\n" +
                "            \"time\":\"4:00 pm\",\n" +
                "            \"location\":\"Venue : Mumbai\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"16 April\",\n" +
                "            \"title\":\"Royal Challengers Bangalore vs Rising Pune Super Giants\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Bengaluru\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"17 April\",\n" +
                "            \"title\":\"Delhi Daredevils vs Sunrisers Kolkata Knight Riders\",\n" +
                "            \"time\":\"4:00 pm\",\n" +
                "            \"location\":\"Venue : Delhi\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"17 April\",\n" +
                "            \"title\":\"Sunrisers Hyderabad vs Kings XI Punjab\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Hyderabad\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"18 April\",\n" +
                "            \"title\":\"Gujarat Lions vs Royal Challengers Bangalore\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Rajkot\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"18 April\",\n" +
                "            \"title\":\"Sunrisers Hyderabad vs Delhi Daredevils\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Hyderabad\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"20 April\",\n" +
                "            \"title\":\"Kings XI Punjab vs Mumbai Indians\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Indore\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"21 April\",\n" +
                "            \"title\":\"Kolkata Knight Riders vs Gujarat Lions\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Kolkata\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"22 April\",\n" +
                "            \"title\":\"Rising Pune Super Giants vs Sunrisers Hyderabad\",\n" +
                "            \"time\":\"4:00 pm\",\n" +
                "            \"location\":\"Venue : Hyderabad\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"22 April\",\n" +
                "            \"title\":\"Delhi Daredevils vs Mumbai Indians\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Delhi\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"23 April\",\n" +
                "            \"title\":\"Gujarat Lions vs Kings XI Punjab\",\n" +
                "            \"time\":\"4:00 pm\",\n" +
                "            \"location\":\"Venue : Rajkot\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"23 April\",\n" +
                "            \"title\":\"Kolkata Knight Riders vs Royal Challengers Bangalore\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Kolkata\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"24 April\",\n" +
                "            \"title\":\"Mumbai Indians vs  Rising Pune Super Giants\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Mumbai\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"25 April\",\n" +
                "            \"title\":\"Royal Challengers Bangalore vs Sunrisers Hyderabad\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Bengaluru\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"26 April\",\n" +
                "            \"title\":\"Rising Pune Super Giants vs Kolkata Knight Riders\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Pune\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"27 April\",\n" +
                "            \"title\":\"Royal Challengers Bangalore vs Gujarat Lions\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Bengaluru\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"28 April\",\n" +
                "            \"title\":\"Kolkata Knight Riders vs Delhi Daredevils\",\n" +
                "            \"time\":\"4:00 pm\",\n" +
                "            \"location\":\"Venue : Kolkata\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"28 April\",\n" +
                "            \"title\":\"Kings XI Punjab vs Sunrisers Hyderabad\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Mohali\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"29 April\",\n" +
                "            \"title\":\"Rising Pune Super Giants vs Royal Challengers Bangalore\",\n" +
                "            \"time\":\"4:00 pm\",\n" +
                "            \"location\":\"Venue : Pune\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"29 April\",\n" +
                "            \"title\":\"Mumbai Indians vs Gujarat Lions\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Rajkot\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"30 April\",\n" +
                "            \"title\":\"Kings XI Punjab vs Delhi Daredevils\",\n" +
                "            \"time\":\"4:00 pm\",\n" +
                "            \"location\":\"Venue : Mohali\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"30 April\",\n" +
                "            \"title\":\"Sunrisers Hyderabad vs Kolkata Knight Riders\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Hyderabad\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"1 May\",\n" +
                "            \"title\":\"Mumbai Indians vs Royal Challengers Bangalore\",\n" +
                "            \"time\":\"4:00 pm\",\n" +
                "            \"location\":\"Venue : Mumbai\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"1 May\",\n" +
                "            \"title\":\"Rising Pune Super Giants vs Gujarat Lions\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Pune\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"2 May\",\n" +
                "            \"title\":\"Delhi Daredevils vs Sunrisers Hyderabad\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Delhi\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"3 May\",\n" +
                "            \"title\":\"Kolkata Knight Riders vs Rising Pune Super Giants\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Kolkata\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"4 May\",\n" +
                "            \"title\":\"Delhi Daredevils vs Gujarat Lions\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Delhi\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"5 May\",\n" +
                "            \"title\":\"Royal Challengers Bangalore vs Kings XI Punjab\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Bengaluru\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"6 May\",\n" +
                "            \"title\":\"Sunrisers Hyderabad vs  Rising Pune Super Giants\",\n" +
                "            \"time\":\"4:00 pm\",\n" +
                "            \"location\":\"Venue : Hyderabad\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"6 May\",\n" +
                "            \"title\":\"Mumbai Indians vs Delhi Daredevils\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Delhi\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"7 May\",\n" +
                "            \"title\":\"Royal Challengers Bangalore vs Kolkata Knight Riders\",\n" +
                "            \"time\":\"4:00 pm\",\n" +
                "            \"location\":\"Venue : Bengaluru\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"7 May\",\n" +
                "            \"title\":\"Kings XI Punjab vs Gujarat Lions\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Mohali\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"8 May\",\n" +
                "            \"title\":\"Sunrisers Hyderabad vs  Mumbai Indians\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Hyderabad\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"9 May\",\n" +
                "            \"title\":\"Kings XI Punjab vs Kolkata Knight Riders\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Mohali\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"10 May\",\n" +
                "            \"title\":\"Gujarat Lions vs Delhi Daredevils\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Kanpur\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"11 May\",\n" +
                "            \"title\":\"Mumbai Indians vs Kings XI Punjab\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Mumbai\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"12 May\",\n" +
                "            \"title\":\"Delhi Daredevils vs Rising Pune Super Giants\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Delhi\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"13 May\",\n" +
                "            \"title\":\"Gujarat Lions vs Sunrisers Hyderabad\",\n" +
                "            \"time\":\"4:00 pm\",\n" +
                "            \"location\":\"Venue : Kanpur\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"13 May\",\n" +
                "            \"title\":\"Kolkata Knight Riders vs Mumbai Indians\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Kolkata\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"14 May\",\n" +
                "            \"title\":\"Rising Pune Super Giants vs Kings XI Punjab\",\n" +
                "            \"time\":\"4:00 pm\",\n" +
                "            \"location\":\"Venue : Pune\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"14 May\",\n" +
                "            \"title\":\"Delhi Daredevils vs Royal Challengers Bangalore\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Delhi\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"16 May\",\n" +
                "            \"title\":\"Qualifier 1\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Mumbai\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"17 May\",\n" +
                "            \"title\":\"Eliminator\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Bangalore\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"19 May\",\n" +
                "            \"title\":\"Qualifier 2\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Bangalore\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\":\"21 May\",\n" +
                "            \"title\":\"Final\",\n" +
                "            \"time\":\"8:00 pm\",\n" +
                "            \"location\":\"Venue : Hyderabad\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        try {
            JSONObject jsonObject = new JSONObject(jsondata);
            String result = jsonObject.optString("result");
            Log.v("GettingResult", result);

            JSONArray resultArray = jsonObject.optJSONArray("result");
            for(int i=0;i<resultArray.length();i++) {
                JSONObject fixtureObject = resultArray.optJSONObject(i);
                String date = fixtureObject.optString("date");
                String time = fixtureObject.optString("time");
                String location = fixtureObject.optString("location");
                String teams = fixtureObject.optString("title");

                fixtures.add(new Fixtures(teams,date,location,time));
            }

            final FixturesAdapter fixturesAdapter = new FixturesAdapter(fixtures);
            recyclerView.setAdapter(fixturesAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
