package sg.edu.rp.c346.id22025566.problemstatementp08songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import sg.edu.rp.c346.id22025566.problemstatementp08songs.DBHelper;
import sg.edu.rp.c346.id22025566.problemstatementp08songs.R;

public class MainActivity extends AppCompatActivity {
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
    Button btnInsert;
    Button btnShowList;
    ArrayList songs = new ArrayList();
    ListView songsLv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSongTitle=findViewById(R.id.SongTitle);
        etSongTitle=findViewById(R.id.enterSongTitle);
        tvSingers=findViewById(R.id.Singers);
        etSingers=findViewById(R.id.enterSinger);
        tvYear=findViewById(R.id.Year);
        etYear=findViewById(R.id.enterReleaseDate);
        tvStars=findViewById(R.id.Stars);
        rgStars=findViewById(R.id.rgStars);
        rbOneStar=findViewById(R.id.oneStar);
        rbTwoStar=findViewById(R.id.twoStar);
        rbThreeStar=findViewById(R.id.threeStar);
        rbFourStar=findViewById(R.id.fourStar);
        rbFiveStar=findViewById(R.id.fiveStar);
        btnInsert=findViewById(R.id.btnInsert);
        btnShowList=findViewById(R.id.btnShowList);
        songsLv=findViewById(R.id.songsLv);






        //onClickListener for btnInsert
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                String title = etSongTitle.getText().toString();
                String singers = etSingers.getText().toString();
                String year = etYear.getText().toString();
                int selectedRadioButtonId = rgStars.getCheckedRadioButtonId();
                int stars = 0;
                int yearInt = 0;
                if (selectedRadioButtonId > 0) {
                    RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                    String selectedStars = selectedRadioButton.getText().toString();
                    // Convert the selectedStars to an integer

                    stars = Integer.parseInt(selectedStars);
                }
                else {
                    String selectedStars = "0";
                    stars = Integer.parseInt(selectedStars);
                }
                yearInt = Integer.parseInt(year);
                db.insertSong(title, singers, yearInt, stars);

            }

        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper dbHelper = new DBHelper(MainActivity.this);
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);


            }
        });


    }
}