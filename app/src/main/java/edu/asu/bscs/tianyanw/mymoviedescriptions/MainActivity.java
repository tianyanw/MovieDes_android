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

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.content.Intent;




/*
This class is the main activity, it show all the movie in a expendable lsitview
 */
public class MainActivity extends AppCompatActivity {

    public String selectedStuff;
    public ExpandableListView elview;
    public static ExpandableMovieListAdapter myListAdapter;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        elview = (ExpandableListView)findViewById(R.id.lvExp);
        myListAdapter = new ExpandableMovieListAdapter(this);
        elview.setAdapter(myListAdapter);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent i = new Intent(this, AddMovieActivity.class);
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setSelectedStuff(String aStuff) {
        this.selectedStuff = aStuff;
    }

    // this method is called when the web view activity when the action bar back button is clicked.
    // the view activity sets the result of the intent and finishes the web view activiy.
    // You could use this mechanism to communicate any serializable object between activities.
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String itemname = data.getStringExtra("myresult");
                Log.d(this.getClass().getSimpleName(), "Returned list item name: " + itemname);
            }
        }
    }


}
