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
 * Purpose: Create json string of a Java object.
 *
 * Ser423 Mobile Applications
 * see http://pooh.poly.asu.edu/Mobile
 * @author Tianyan Wu mailto: Tianyanw@asu.edu
 *
 * @version January 2016
 */



        import org.json.JSONArray;
        import org.json.JSONObject;
        import java.util.Vector;

// MovieDecription Class to create json string

public class MovieDescription {

    public  String Title;
    public  String  Year;
    public  String  Rated;
    public  String  Released;
    public  String  Runtime;
    public  String  Genre;
    public  String  Plot;
    public  String  Actors;

    // init the class
    public MovieDescription(JSONObject jo ){

        try{
            Title = jo.getString("Title");
            Year = jo.getString("Year");
            Rated = jo.getString("Rated");
            Released = jo.getString("Released");
            Runtime = jo.getString("Runtime");
            Genre = jo.getString("Genre");
            Plot = jo.getString("Plot");
            Actors = jo.getString("Actors");


        }catch (Exception ex) {
            android.util.Log.w(this.getClass().getSimpleName(),
                    "error converting to/from json");
        }
    }

    // to string method
    public String toJsonString(){

        String ret = " ";
        try{
            JSONObject jo = new JSONObject();
            jo.put("Title", Title);
            jo.put("Year", Year);
            jo.put("Rated", Rated);
            jo.put("Released", Released);
            jo.put("Runtime",Runtime);
            jo.put("Genre", Genre);
            jo.put("Plot", Plot);
            jo.put("Actors",Actors);
            ret = jo.toString();
        }catch (Exception ex){
            android.util.Log.w(this.getClass().getSimpleName(),
                    "error converting to/from json");
        }

        return ret;
    }


}
