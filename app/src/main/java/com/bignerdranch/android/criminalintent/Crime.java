package com.bignerdranch.android.criminalintent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;

/**
 * Created by jv on 7/1/2015.
 *
 * A Model Class to represent a single Crime
 *
 */
public class Crime {

    private static String JSON_ID = "id";
    private static String JSON_TITLE = "title";
    private static String JSON_SOLVED = "solved";
    private static String JSON_DATE = "date";

    private UUID id;
    private String title;
    private Date date;
    private boolean solved;

    public Crime(JSONObject json) throws JSONException {
        id = UUID.fromString(json.getString(JSON_ID));
        title = json.getString(JSON_TITLE);
        solved = json.getBoolean(JSON_SOLVED);
        date = new Date(json.getLong(JSON_DATE));
    }

    public Crime() {
        id = UUID.randomUUID();
        date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_ID, this.getId());
        json.put(JSON_DATE, this.getDate().getTime());
        json.put(JSON_SOLVED, this.isSolved());
        json.put(JSON_TITLE, this.getTitle());
        return json;
    }


}
