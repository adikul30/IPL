package ipl.ipl;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by adicool on 4/4/17.
 */

public class TeamAadapter extends RecyclerView.Adapter<TeamAadapter.ViewHolder> {

    private List<String> mplayers;

    public TeamAadapter(List<String> players) {
        mplayers=players;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView playerName;
        public ViewHolder(View itemView) {
            super(itemView);
            playerName = (TextView) itemView.findViewById(R.id.player_name);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_design,parent,false);
        return new ViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.playerName.setText(mplayers.get(position));
    }

    @Override
    public int getItemCount() {
        if(mplayers==null){
            return 0;
        }
        else {
            return mplayers.size();
        }
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
