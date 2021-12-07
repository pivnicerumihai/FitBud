package com.example.fitbud;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitbud.Model.ExerciseClass;
import com.google.gson.Gson;
import com.google.protobuf.StringValue;

import org.w3c.dom.Text;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExerciseDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    TextView exerciseDescription;
    TextView exerciseName;
    Bundle extras;
    ExerciseClass exercise;
    LinearLayoutCompat recordsContainer;
    View recordsTable;
    EditText etReps;
    EditText etWeight;
    Integer white;
    Integer lightRed;
    Integer darkRed;
    Button btnAddSet;
    Integer numberOfDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_details);
        btnAddSet = findViewById(R.id.btn_add_set);
        btnAddSet.setOnClickListener(this::onClick);
//        Store Color value into variables
        lightRed = getResources().getColor(R.color.light_red);
        white = getResources().getColor(R.color.white);
        darkRed = getResources().getColor(R.color.dark_red);

//      Get Intent
        extras = getIntent().getExtras();
        exercise = extras.getParcelable("exercise");

        numberOfDays = exercise.getRecords().size();

        exerciseDescription = findViewById(R.id.exercise_description);
        exerciseDescription.setText(exercise.getTextDescription());
        exerciseDescription.setTextColor(white);
        exerciseName = findViewById(R.id.exercise_name);
        exerciseName.setText(exercise.getName());
        exerciseName.setTextColor(white);
        recordsContainer = findViewById(R.id.exercise_records);


        generateRecordsTables();
    }

    public void generateRecordsTables(){


        HashMap<String, Integer> sets;
        ArrayList<TableRow> recordsPerDay;


        for (int i = 0; i < numberOfDays; i++){
//            inflate layout
            recordsTable = getLayoutInflater().inflate(R.layout.exercise_records, recordsContainer, false);

//            declare and populate inflated layout views
            TextView tvRecordDate = recordsTable.findViewById(R.id.record_date);
            TableLayout tableLayout = recordsTable.findViewById(R.id.table_exercise_progress);
            TableRow tableRow = tableRowPerRecord(i);
            tableLayout.addView(tableRow);
            tvRecordDate.setText(exercise.getRecords().get(i).getDate());
            tvRecordDate.setTextColor(white);

            sets = exercise.getRecords().get(i).getSet();
            recordsPerDay = generateRowForEachRecord(sets);

            for (TableRow row : recordsPerDay) {
                tableLayout.addView(row);
            }

            recordsContainer.addView(recordsTable);
        }
    }

    public TableRow tableRowPerRecord(int record){
        TableRow tableRow = new TableRow(this);

        if(record == 0){

            etReps = new EditText(this);
            etWeight = new EditText(this);



            etWeight.setInputType(InputType.TYPE_CLASS_NUMBER);
            etWeight.setHint("Weight");
            etWeight.setHintTextColor(white);
            etWeight.setGravity(Gravity.CENTER);
            etWeight.setTextColor(Color.WHITE);
            etWeight.getBackground().setColorFilter(white, PorterDuff.Mode.SRC_IN);
            etWeight.setBackgroundColor(darkRed);
            etWeight.setPadding(15,10,15,10);
            etWeight.setTextSize(20);

            etReps.setInputType(InputType.TYPE_CLASS_NUMBER);
            etReps.setHint("Reps");
            etReps.setHintTextColor(white);
            etReps.setGravity(Gravity.CENTER);
            etReps.setTextColor(Color.WHITE);
            etReps.getBackground().setColorFilter(white,PorterDuff.Mode.SRC_IN);
            etReps.setBackgroundColor(darkRed);
            etReps.setPadding(15,10,15,10);
            etReps.setTextSize(20);

            tableRow.setGravity(Gravity.CENTER);
            tableRow.addView(etWeight);
            tableRow.addView(etReps);

        }
        else {
            TextView tvStringWeight = new TextView(this);
            TextView tvStringReps = new TextView(this);

            //                    tvStringWeight Styling
            tvStringWeight.setText("Weight");
            tvStringWeight.setTextColor(white);
            tvStringWeight.setGravity(Gravity.CENTER);
            tvStringWeight.setPadding(15,10,15,10);
            tvStringWeight.setBackgroundColor(darkRed);
            tvStringWeight.setTextSize(20);
//                    tvStringReps Styling
            tvStringReps.setText("Reps");
            tvStringReps.setTextColor(white);
            tvStringReps.setGravity(Gravity.CENTER);
            tvStringReps.setPadding(15,10,15,10);
            tvStringReps.setBackgroundColor(darkRed);
            tvStringReps.setTextSize(20);
            tableRow.setGravity(Gravity.CENTER);

            tableRow.addView(tvStringWeight);
            tableRow.addView(tvStringReps);

        }

        return tableRow;
    }
    public ArrayList<TableRow> generateRowForEachRecord(HashMap<String,Integer> sets){

        ArrayList<TableRow> tableRowArrayList = new ArrayList<>();

        for(Map.Entry<String,Integer> entry: sets.entrySet()){

            TableRow valuesTableRow = new TableRow(this);


            TextView tvWeight = new TextView(this);
            TextView tvReps = new TextView(this);

            String reps = entry.getValue().toString();
            String weight = entry.getKey();

//                    tvReps Styling
            tvReps.setText(reps);
            tvReps.setTextColor(white);
            tvReps.setGravity(Gravity.CENTER);
            tvReps.setPadding(15,10,15,10);
            tvReps.setBackgroundColor(lightRed);

//                    tvWeight Styling
            tvWeight.setText(weight);
            tvWeight.setTextColor(white);
            tvWeight.setGravity(Gravity.CENTER);
            tvWeight.setPadding(15,10,15,10);
            tvWeight.setBackgroundColor(lightRed);


            valuesTableRow.setGravity(Gravity.CENTER);




            valuesTableRow.addView(tvWeight);
            valuesTableRow.addView(tvReps);
            tableRowArrayList.add(valuesTableRow);
    }

        return tableRowArrayList;
    }

    @Override
    public void onClick(View view) {
            String weight = etWeight.getText().toString();
            HashMap<String,Integer> newRecord = new HashMap<String,Integer>();

            if(weight.isEmpty() || Integer.valueOf(etReps.getText().toString()) == null ){
                Toast.makeText(this, "You need to edit weight and reps to add a new set", Toast.LENGTH_SHORT).show();
            }
            else {
                newRecord.put(weight, Integer.valueOf(etReps.getText().toString()));
            }

//            update records in database

     }
}
