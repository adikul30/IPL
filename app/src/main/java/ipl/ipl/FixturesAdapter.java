package ipl.ipl;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import static android.R.attr.typeface;

/**
 * Created by adicool on 9/4/17.
 */

public class FixturesAdapter extends RecyclerView.Adapter<FixturesAdapter.ViewHolder> {

    private ArrayList<Fixtures> fixturesArrayList;

    public FixturesAdapter (ArrayList<Fixtures> fixtures) {
        this.fixturesArrayList = fixtures;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView date;
        TextView title;
        TextView time;
        TextView location;


        public ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)  itemView.findViewById(R.id.fixtures_cardView);
            title = (TextView) itemView.findViewById(R.id.match_teams);
            date = (TextView) itemView.findViewById(R.id.match_date);
            time = (TextView) itemView.findViewById(R.id.match_time);
            location = (TextView) itemView.findViewById(R.id.match_location);
        }
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fixture_design, parent, false);
        ViewHolder cardHolder = new ViewHolder(v);
        return cardHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(fixturesArrayList.get(position).getTitle());
        holder.date.setText(fixturesArrayList.get(position).getDate());
        holder.time.setText(fixturesArrayList.get(position).getTime());
        holder.location.setText(fixturesArrayList.get(position).getLocation());


    }

    @Override
    public int getItemCount() {
        if(fixturesArrayList == null){
            return 0;
        } else {
            return fixturesArrayList.size();
        }
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
