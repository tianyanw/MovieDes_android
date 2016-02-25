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
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


/*
this class shows movie description
 */
public class MovieinfoActivity extends AppCompatActivity{

    TextView titleTV, yearTV, ratedTV, releasedTV, runtimeTV, genreTV, actorsTV, plotTV;
    String title, year, rated, released, runtime, genre, actors, plot;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.acticity_movie_info);

        titleTV = (TextView) findViewById(R.id.titleTV);
        yearTV = (TextView) findViewById(R.id.yearTV);
        ratedTV = (TextView) findViewById(R.id.ratedTV);
        releasedTV = (TextView) findViewById(R.id.releasedTV);
        runtimeTV = (TextView) findViewById(R.id.runtimeTV);
        genreTV = (TextView) findViewById(R.id.genreTV);
        actorsTV = (TextView) findViewById(R.id.actorsTV);
        plotTV = (TextView) findViewById(R.id.plotTV);


        Intent i = getIntent();
        title = i.getStringExtra("title");
        year = i.getStringExtra("year");
        rated = i.getStringExtra("rated");
        released = i.getStringExtra("released");
        runtime = i.getStringExtra("runtime");
        genre = i.getStringExtra("genre");
        actors = i.getStringExtra("actors");
        plot = i.getStringExtra("plot");

        titleTV.setText(title);
        yearTV.setText(year);
        ratedTV.setText(rated);
        releasedTV.setText(released);
        runtimeTV.setText(runtime);
        genreTV.setText(genre);
        actorsTV.setText(actors);
        plotTV.setText(plot);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_back, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_back) {

            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


   public void buttondelete(View v){

       MainActivity.myListAdapter.movieLib.deletMovie(title);


       MainActivity.myListAdapter.updateModel();

       MainActivity.myListAdapter.notifyDataSetChanged();


       finish();

   }



}
