package sg.edu.rp.c346.id22025566.problemstatementp08songs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {
    TextView tvSongTitle;
    EditText etSongTitle;
    TextView tvSingers;
    EditText etSingers;
    TextView tvYear;
    EditText etYear;
    TextView tvStars;
    RadioGroup rgStars;
    RadioButton rbOneStar;
    RadioButton rbTwoStar;
    RadioButton rbThreeStar;
    RadioButton rbFourStar;
    RadioButton rbFiveStar;
    Button btnUpdate;
    Button btnDelete;
    DBHelper dbHelper;
    int songId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        etSongTitle = findViewById(R.id.enterSongTitle);
        etSingers = findViewById(R.id.enterSinger);
        etYear = findViewById(R.id.enterReleaseDate);
        rgStars=findViewById(R.id.rgStars);
        rbOneStar=findViewById(R.id.oneStar);
        rbTwoStar=findViewById(R.id.twoStar);
        rbThreeStar=findViewById(R.id.threeStar);
        rbFourStar=findViewById(R.id.fourStar);
        rbFiveStar=findViewById(R.id.fiveStar);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        dbHelper = new DBHelper(ThirdActivity.this);

        // Retrieve the songId passed from the previous activity
        songId = getIntent().getIntExtra("id", 0);

        // Retrieve the song details based on the songId from the database
        Song song = dbHelper.getSong(songId);

        // Populate the EditText fields with the song details
        etSongTitle.setText(song.getTitle());
        etSingers.setText(song.getSingers());
        etYear.setText(String.valueOf(song.getYear()));
        int stars = song.getStars();

        if (stars == 1) {
            rbOneStar.setChecked(true);
        } else if (stars == 2) {
            rbTwoStar.setChecked(true);
        } else if (stars == 3) {
            rbThreeStar.setChecked(true);
        } else if (stars == 4) {
            rbFourStar.setChecked(true);
        } else if (stars == 5) {
            rbFiveStar.setChecked(true);
        }

        // Implement the Update button click listener
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the updated values from the EditText fields
                String title = etSongTitle.getText().toString();
                String singers = etSingers.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                int stars = song.getStars();



                if (rbOneStar.isChecked()) {

                    stars = 1;
                } else if (rbTwoStar.isChecked()) {

                    stars = 2;
                } else if (rbThreeStar.isChecked()) {

                    stars = 3;
                } else if (rbFourStar.isChecked()) {

                    stars = 4;
                } else if (rbFiveStar.isChecked()) {
                    stars = 5;

                }

                // Update the song details in the database
                dbHelper.updateSong(songId, title, singers, year, stars);

                // Close the activity and go back to the previous activity
                finish();
            }
        });

        // Implement the Delete button click listener
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete the song from the database
                dbHelper.deleteSong(songId);

                // Close the activity and go back to the previous activity
                finish();
            }
        });
    }
}