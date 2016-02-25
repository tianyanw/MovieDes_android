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
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import java.lang.Object;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * this class is a ExpandableListview adapter
 */
public class ExpandableMovieListAdapter extends BaseExpandableListAdapter
        implements
        ExpandableListView.OnGroupExpandListener,
        ExpandableListView.OnGroupCollapseListener,
        View.OnTouchListener {
    private TextView currentSelectedTV= null;
    private MainActivity parent;
    public static MovieLibrary movieLib;
    public LinkedHashMap<String,String[]> model;

    OnDataChangeListener onDataChangeListener;


    public ExpandableMovieListAdapter(MainActivity parent){

        movieLib = new MovieLibrary(parent);
        movieLib.resetMovies();
        this.parent = parent;
        updateModel();
    }

    public void updateModel(){

        model = new LinkedHashMap<String,String[]>();

        List<String> act = new ArrayList<>();
        List<String> com = new ArrayList<>();
        List<String> ani = new ArrayList<>();
        List<String> dra= new ArrayList<>();
        List<String> bio = new ArrayList<>();
        List<String> adv = new ArrayList<>();

        ArrayList<MovieDescription> arry = movieLib.getAllMovie();

        MovieDescription temp;

        for(int i=0; i< movieLib.MovieCount();i++) {

            temp = arry.get(i);

            String tempGen = temp.Genre;

            switch (tempGen.charAt(1)){
                case 'c':
                    act.add(temp.Title);

                    break;
                case 'o':
                    com.add(temp.Title);

                    break;
                case 'r':
                    dra.add(temp.Title);

                    break;
                case 'n':
                    ani.add(temp.Title);

                    break;
                case 'i':
                    bio.add(temp.Title);

                    break;
                case 'd':
                    adv.add(temp.Title);
                    break;

            }

        }

        String [] act1 = act.toArray(new String[act.size()]);
        String [] com1 = com.toArray(new String[com.size()]);
        String [] dra1 = dra.toArray(new String[dra.size()]);
        String [] ani1 = ani.toArray(new String[ani.size()]);
        String [] bio1 = bio.toArray(new String[bio.size()]);
        String [] adv1 = adv.toArray(new String[adv.size()]);


        model.put("Action", act1);
        model.put("Comedy", com1);
        model.put("Drama", dra1);
        model.put("Animation", ani1);
        model.put("Biography", bio1);
        model.put("Adventure", adv1);

        parent.elview.setOnGroupExpandListener(this);
        parent.elview.setOnGroupCollapseListener(this);

    }

    public void setOnDataChangeListener(OnDataChangeListener onDataChangeListener){
        this.onDataChangeListener = onDataChangeListener;
    }

    @Override
    public void notifyDataSetChanged(){
        super.notifyDataSetChanged();
        if (onDataChangeListener != null){
            onDataChangeListener.onDataChanged(movieLib.MovieCount());
        }
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String[] stuffTitles = model.keySet().toArray(new String[] {});
        return model.get(stuffTitles[groupPosition])[childPosition];
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            android.util.Log.d(this.getClass().getSimpleName(),"in getChildView null so creating new view");
            LayoutInflater inflater = (LayoutInflater) this.parent
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);
        }
        TextView txtListChild = (TextView)convertView.findViewById(R.id.lblListItem);
        convertView.setOnTouchListener(this);
        convertView.setBackgroundResource(R.color.light_blue);
        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String[] stuffTitles = model.keySet().toArray(new String[]{});
        return model.get(stuffTitles[groupPosition]).length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        String[] stuffTitles = model.keySet().toArray(new String[]{});
        return stuffTitles[groupPosition];
    }

    @Override
    public int getGroupCount() {
        String[] stuffTitles = model.keySet().toArray(new String[]{});
        return stuffTitles.length;
    }

    @Override
    public long getGroupId(int groupPosition) {

        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String)getGroup(groupPosition);
        if (convertView == null) {
            android.util.Log.d(this.getClass().getSimpleName(),"in getGroupView null so creating new view");
            LayoutInflater inflater = (LayoutInflater) this.parent
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        return convertView;
    }

    public interface OnDataChangeListener{
        public void onDataChanged(int size);
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {

        return true;
    }

    public boolean onTouch(View v, MotionEvent event){


        if (event.getAction()==MotionEvent.ACTION_DOWN){

            if(v instanceof android.widget.LinearLayout) {
                android.widget.LinearLayout layView = (android.widget.LinearLayout) v;

                for (int i = 0; i <= layView.getChildCount(); i++) {
                    if (layView.getChildAt(i) instanceof TextView) {
                        // keep track of TV stuff was most recently touched to un-highlighted
                        if (currentSelectedTV != null) {

                        }
                        TextView tmp = ((TextView) layView.getChildAt(i));
                        tmp.setBackgroundColor(Color.YELLOW);
                        currentSelectedTV = tmp;
                        parent.setSelectedStuff(tmp.getText().toString());

                        Intent movieInfo = new Intent(parent, MovieinfoActivity.class);

                        String title = currentSelectedTV.getText().toString();

                        MovieDescription movie = movieLib.getMovie(title);

                        movieInfo.putExtra("title",title);
                        movieInfo.putExtra("actors",movie.Actors);
                        movieInfo.putExtra("genre",movie.Genre);
                        movieInfo.putExtra("plot",movie.Plot);
                        movieInfo.putExtra("rated",movie.Rated);
                        movieInfo.putExtra("released",movie.Released);
                        movieInfo.putExtra("runtime",movie.Runtime);
                        movieInfo.putExtra("year",movie.Year);


                        parent.startActivityForResult(movieInfo, 1);

                    }

                }
            }

                if(v instanceof TextView){
                    android.util.Log.d(this.getClass().getSimpleName(),"in onTouch called for: " +
                            ((TextView)v).getText());
                }
            }
            return true;
    }



        public void onGroupExpand(int groupPosition){
        android.util.Log.d(this.getClass().getSimpleName(),"in onGroupExpand called for: "+
                model.keySet().toArray(new String[] {})[groupPosition]);
        if (currentSelectedTV != null){
            currentSelectedTV = null;
        }
        for (int i=0; i< this.getGroupCount(); i++) {
            if(i != groupPosition){
                //parent.elview.collapseGroup(i);
            }
        }
    }

    public void onGroupCollapse(int groupPosition){
        android.util.Log.d(this.getClass().getSimpleName(),"in onGroupCollapse called for: "+
                model.keySet().toArray(new String[] {})[groupPosition]);
        if (currentSelectedTV != null){
            currentSelectedTV.setBackgroundColor(parent.getResources().getColor(R.color.light_blue));
            currentSelectedTV = null;
        }
    }



}
