package com.example.ayush.project1.Utilities;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.example.ayush.project1.R;

public class SettingsFragment extends PreferenceFragment implements
        Preference.OnPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        /*
            Instead of loading a layout, preferenced declared in XML are added using this function
            to the activity
         */
        addPreferencesFromResource(R.xml.preferences);

        // For all preferences, attach an OnPreferenceChangeListener so the UI summary can be
        // updated when the preference changes.
        Preference preference = findPreference(getString(R.string.key));
        /**
         * Attaches a listener so the summary is always updated with the preference value.
         * Also fires the listener once, to initialize the summary (so it shows up before the value
         * is changed.)
         */
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(this);

        // Trigger the listener immediately with the preference's current value.
        onPreferenceChange(preference,
                PreferenceManager.getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object value) {
        String stringValue = value.toString();

        ListPreference listPreference = (ListPreference) preference;

        int prefIndex = listPreference.findIndexOfValue(stringValue);   //0/1

        //Sets the selected radio item as summary for this Preference
        preference.setSummary(listPreference.getEntries()[prefIndex]);

        return true;
    }
}