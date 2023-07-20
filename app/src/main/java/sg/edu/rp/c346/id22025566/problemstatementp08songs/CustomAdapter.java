package sg.edu.rp.c346.id22025566.problemstatementp08songs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Song> songArrayList;


    public CustomAdapter (Context context, int resource, ArrayList<Song> objects){
        super (context, resource, objects);
        this.parent_context=context;
        this.layout_id=resource;
        this.songArrayList=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvSongTitle = rowView.findViewById(R.id.textViewSongTitle);
        TextView tvSingers = rowView.findViewById(R.id.textViewSingers);
        TextView tvYear = rowView.findViewById(R.id.textViewYear);
        TextView tvStars = rowView.findViewById(R.id.textViewStars);

        // Obtain the Android Version information based on the position
        Song currentSong = songArrayList.get(position);

        // Set values to the TextView to display the corresponding information
        tvSongTitle.setText(currentSong.getTitle());
        tvSingers.setText(currentSong.getSingers());
        tvYear.setText(""+currentSong.getYear());
        tvStars.setText(String.valueOf(currentSong.getStars()));

        return rowView;
    }

    @Override
    public Song getItem(int position) {
        return songArrayList.get(position);
    }






}
