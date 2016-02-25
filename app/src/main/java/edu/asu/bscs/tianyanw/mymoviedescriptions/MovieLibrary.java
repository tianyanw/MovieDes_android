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
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Set;
import java.util.List;

/**
 *this class is the movie library that contains all the movies
 */
public class MovieLibrary extends Object implements Serializable {


    protected Hashtable<String,MovieDescription> moives;
    private transient MainActivity parent;


    public MovieLibrary(MainActivity parent){

        moives = new  Hashtable<String, MovieDescription>();
        this.parent = parent;

    }

    public void addMovie(MovieDescription movie){

        String title = movie.Title;
        moives.put(title, movie);

    }

    public void deletMovie(String tit){


        moives.remove(tit);

    }

    public MovieDescription getMovie(String title){

        return moives.get(title);
    }

    public int MovieCount(){

        return  moives.size();
    }



    public ArrayList<MovieDescription> getAllMovie(){

        ArrayList<MovieDescription> temp = new ArrayList<MovieDescription>();

        Enumeration e =  moives.keys();
        int i = 0;

        while (e.hasMoreElements()){

            String s = (String)e.nextElement();
            MovieDescription m = moives.get(s);
            temp.add(i,m);
            i++;
        }


        return temp;

    }



    public void resetMovies(){

        try{

            moives.clear();

            String fileName = "movies.json";
            InputStream is = parent.getApplicationContext().getResources().openRawResource(R.raw.movies);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            JSONObject movieJson = new JSONObject(new JSONTokener(br.readLine()));
            Iterator<String> it = movieJson.keys();
            while (it.hasNext()){

                String mTitle = it.next();

                JSONObject aMovie = movieJson.optJSONObject(mTitle);

                if (aMovie != null){

                    MovieDescription md = new MovieDescription(aMovie);
                      moives.put(mTitle,md);
                }
            }


        }catch (Exception e){


        }
    }



}
