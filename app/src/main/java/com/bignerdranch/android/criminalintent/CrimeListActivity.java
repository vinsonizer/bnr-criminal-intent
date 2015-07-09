package com.bignerdranch.android.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by jv on 7/7/2015.
 *
 * Activity to host a single fragment of CrimeLists
 *
 */
public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
