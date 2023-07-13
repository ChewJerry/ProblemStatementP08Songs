package sg.edu.rp.c346.id22025566.problemstatementp08songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ListView songsLv;
    ArrayAdapter<Song> adapter;
    DBHelper db;
    Button btn5stars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        songsLv = findViewById(R.id.songsLv);
        btn5stars = findViewById(R.id.btn5stars);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        songsLv.setAdapter(adapter);

        DBHelper dbHelper = new DBHelper(this);
        db = dbHelper; // Initialize the db object with dbHelper

        ArrayList<Song> songs = db.getSongs();

        // Create an ArrayAdapter or custom adapter to link the songs data to the ListView
        ArrayAdapter<Song> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, songs);

        // Set the adapter to the ListView
        songsLv.setAdapter(adapter);


        btn5stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(SecondActivity.this);
                ArrayList<Song> fiveStarSongs = db.getAllFiveStarSongs();
                db.close();

                // Clear the adapter
                adapter.clear();

                // Add the five-star songs to the adapter
                adapter.addAll(fiveStarSongs);

                // Notify the adapter of the data change
                adapter.notifyDataSetChanged();
            }
        });

        songsLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Retrieve the clicked song
                Song song = adapter.getItem(position);

                // Create an intent to start the ThirdActivity
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);

                // Pass the song details as extras to the intent
                intent.putExtra("id", song.get_id());
                intent.putExtra("title", song.getTitle());
                intent.putExtra("singers", song.getSingers());
                intent.putExtra("year", song.getYear());
                intent.putExtra("stars", song.getStars());

                // Start the ThirdActivity
                startActivity(intent);
            }
        });




    }
}