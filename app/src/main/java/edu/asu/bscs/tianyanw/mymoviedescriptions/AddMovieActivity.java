package edu.asu.bscs.tianyanw.mymoviedescriptions;

/*
* Copyright 2016 Tianyan Wu,
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*
* The Instuctor: Tim Lindquist and Arizona State University has right to build and evaluate the software
* Package for the purpose of determining my grade and program assessment.
*
* Purpose: This project is a multiple view Android app for managing MovieDescriptions.
* It displayed the information in your MovieDescription class. it provide the ability to add or remove movie description entries
*
* Ser423 Mobile Applications
* see http://pooh.poly.asu.edu/Mobile
* @author Tianyan Wu mailto: Tianyanw@asu.edu
*
* @version FER 2016
*/

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import android.widget.AdapterView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


/*
this class is for adding new movie description
 */
public class AddMovieActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    EditText titleTV, yearTV, ratedTV, releasedTV, runtimeTV, actorsTV, plotTV;
    private Spinner mySpinner;
    private ArrayAdapter<String> adapter;
    String genre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);


        mySpinner = (Spinner)findViewById(R.id.spinner);
        String[] courses = new String[] {"Action","Comdey","Drama","Animation","Biojraphy","Adventure"};
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                new ArrayList<>(Arrays.asList(courses)));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mySpinner.setAdapter(adapter);
        mySpinner.setOnItemSelectedListener(this);


        titleTV = (EditText) findViewById(R.id.title);
        yearTV = (EditText) findViewById(R.id.year);
        ratedTV = (EditText) findViewById(R.id.rated);
        releasedTV = (EditText) findViewById(R.id.released);
        runtimeTV = (EditText) findViewById(R.id.runtime);
        actorsTV = (EditText) findViewById(R.id.actors);
        plotTV = (EditText) findViewById(R.id.plot);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_back, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)  {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent i = new Intent(this, MainActivity.class);
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_back) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void buttonsubmit(View v){


        try {

            String json = "{\"Title\":\" " + titleTV.getText() + "\",\"Year\":\" " + yearTV.getText() + " \",\"Rated\":\" " + ratedTV.getText() + "\",\"Released\":" +
                    "\"" + releasedTV.getText() + "\",\"Runtime\":\"" + runtimeTV.getText() + "\",\"Genre\":\"" + genre + "\", " +
                    "\"Actors\":\" " + actorsTV.getText() + "\",\"Plot\":\" " + plotTV.getText() + "\"}";

            JSONObject temp = new JSONObject(json);

            MovieDescription newMovie = new MovieDescription(temp);

            MainActivity.myListAdapter.movieLib.addMovie(newMovie);

        }catch (JSONException e){


        }

        MainActivity.myListAdapter.updateModel();

        MainActivity.myListAdapter.notifyDataSetChanged();

        Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();

        finish();
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        genre = mySpinner.getSelectedItem().toString();

    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

}
