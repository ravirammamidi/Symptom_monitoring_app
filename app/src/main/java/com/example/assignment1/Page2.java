package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.assignment1.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Page2 extends AppCompatActivity {

    //Global Variables
    private static String[] symptoms_items = new String[]{"Nausea", "Headache", "Sore Throat","Diarrhea","Fever","Muscle Ache","Loss of smell or taste","Cough","Shortness of breath","Feeling Tired"};
    private static ArrayList<String> selected_symptoms= new ArrayList<String>();
    private static ArrayList<Float> selected_rating= new ArrayList<Float>();
    private static Map<String,Float> combined_list;
    static {
        combined_list = new HashMap<>();
        combined_list.put("Nausea", Float.intBitsToFloat(0));
        combined_list.put("Headache", Float.intBitsToFloat(0) );
        combined_list.put("Sore Throat", Float.intBitsToFloat(0) );
        combined_list.put("Diarrhea", Float.intBitsToFloat(0) );
        combined_list.put("Fever", Float.intBitsToFloat(0) );
        combined_list.put("Muscle Ache", Float.intBitsToFloat(0) );
        combined_list.put("Loss of smell or taste", Float.intBitsToFloat(0) );
        combined_list.put("Cough", Float.intBitsToFloat(0) );
        combined_list.put("Shortness of breath", Float.intBitsToFloat(0) );
        combined_list.put("Feeling Tired", Float.intBitsToFloat(0) );
    }

    // using above item list initialize an array



//    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {
//        @Override
//        public void onItemSelected(AdapterView<?> parent, View view,
//                                   int position, long id) {
//            Log.i("ITEM", (String) parent.getItemAtPosition(position));
//            selected_symptoms.add((String) parent.getItemAtPosition(position));
//        }
//        @Override
//        public void onNothingSelected(AdapterView<?> parent) {
//            // TODO Auto-generated method stub
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);


        Spinner dropdown = findViewById(R.id.spinner);
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, symptoms_items);
        dropdown.setAdapter(adapter);// set these values



        // temporary save button
        Button b6=(Button) findViewById(R.id.button6);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner dropdown = findViewById(R.id.spinner);
                dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view,
                                               int position, long id) {
                        Log.i("ITEM", (String) parent.getItemAtPosition(position));
                        selected_symptoms.add((String) parent.getItemAtPosition(position));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                RatingBar ratingBar=findViewById(R.id.ratingBar);
                float getrating = ratingBar.getRating();
                selected_rating.add(getrating);
                Log.i("SYMP_LIST",String.valueOf(getrating) + " ");
                Toast.makeText(view.getContext(), "Symptom Saved "+selected_symptoms.size()+" "+getrating,Toast.LENGTH_LONG).show();
            }
        });


        Button b5=(Button) findViewById(R.id.button5);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code for pushing data into db



                Toast.makeText(view.getContext(),"List saved and pushed to DB",Toast.LENGTH_LONG).show();
                // end for db push

                // For going back to home page
                Intent int2=new Intent( Page2.this,Page1.class); // send back to main context
                startActivity(int2);
            }
        });
    }
}