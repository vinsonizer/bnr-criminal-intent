package com.bignerdranch.android.criminalintent;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by jv on 7/12/2015.
 */
public class CriminalIntentJSONSerializer {

    private Context context;
    private String filename;


    public CriminalIntentJSONSerializer(Context context, String filename) {
        this.context = context;
        this.filename = filename;
    }

    public void saveCrimes(ArrayList<Crime> crimes) throws JSONException, IOException{
        // Builds an array in JSON
        JSONArray array = new JSONArray();
        for(Crime c: crimes)
            array.put(c.toJSON());

        // Write the file to disk
        Writer writer = null;
        try {
            OutputStream out = context.openFileOutput(filename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        } finally {
            if(writer != null)
                writer.close();
        }
    }

    public ArrayList<Crime> loadCrimes() throws JSONException, IOException {
        ArrayList<Crime> crimes = new ArrayList<>();
        BufferedReader reader = null;
        try {
            // Open and read the file into StringBuilder
            InputStream is = context.openFileInput(filename);
            reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                // line breaks are omitted
                jsonString.append(line);
            }
            // Parse the json using JSONTokenizer
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            // build an array of crimes from JSONObjects
            for (int i = 0; i < array.length(); i++) {
                crimes.add(new Crime(array.getJSONObject(i)));
            }
        } catch (FileNotFoundException fnfe) {
            // Ignore this when happens from starting fresh
        } finally {
            if(reader != null)
                reader.close();
        }
        return crimes;

    }

}
