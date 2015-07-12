package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.UUID;

/**
 * Created by jv on 7/7/2015.
 *
 * General Singleton to handle Crime Management
 */
public class CrimeLab {
    private static final String TAG = "CrimeLab";
    private static final String FILENAME = "crimes.json";

    private static CrimeLab crimeLab;
    private ArrayList<Crime> crimes;
    private CriminalIntentJSONSerializer serializer;

    private Context appContext;

    private CrimeLab(Context appContext) {
        this.appContext = appContext;
        try {
            crimes = serializer.loadCrimes();
        } catch (Exception e) {
            crimes = new ArrayList<>();
            Log.e(TAG, "Error loading crimes:", e);
        }
    }

    public static CrimeLab get(Context c) {
        if (crimeLab == null) {
            crimeLab = new CrimeLab(c.getApplicationContext());
        }
        return crimeLab;
    }

    public ArrayList<Crime> getCrimes() {
        return crimes;
    }

    public Crime getCrime(UUID id) {
        for (Crime c : crimes) {
            if (c.getId().equals(id))
                return c;
        }
        return null;
    }

    public void addCrime(Crime c) {
        this.crimes.add(c);
    }

    public boolean saveCrimes() {
        try {
            serializer.saveCrimes(crimes);
            Log.d(TAG, "crimes ssaved to file");
            return true;
        } catch (Exception e) {
            Log.d(TAG, "Error saving crimes");
            return false;
        }
    }
}
