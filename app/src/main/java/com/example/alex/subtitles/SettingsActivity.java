package com.example.alex.subtitles;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.MenuItem;

public class SettingsActivity extends AppCompatActivity{
    private static final int RQS_OPEN_DOCUMENT_TREE = 2;
    public static final String KEY_PREF_STORAGE_FOLDER = "pref_key_download_location";
    private static StringBuilder storageFolder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();
        //getFragmentManager().beginTransaction().replace(android.R.id.content, new MainSettingsFragment()).commit();
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    //public static class MainSettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener{
    public static class MainSettingsFragment {
    //public static class MainSettingsFragment extends PreferenceFragment {

//        SharedPreferences prefs;
//
//        private void saveStringPreference(final String key , final String value){
//            SharedPreferences.Editor editor = prefs.edit();
//            editor.putString(key, value);
//            editor.commit();
//        }
//
//        @Override
//        public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//            if (null != intent && !intent.getBooleanExtra(FilePickerActivity.EXTRA_ALLOW_MULTIPLE, false)) {
//                if (requestCode == RQS_OPEN_DOCUMENT_TREE && resultCode == Activity.RESULT_OK) {
//                    // Use the provided utility method to parse the result
//                    List<Uri> files = Utils.getSelectedFilesFromResult(intent);
//                    for (Uri uri : files) {
//                        //saveStringPreference("", uri.getPath() );
//                        //File file = Utils.getFileForUri(uri);
//                        storageFolder.append(uri.getPath());
//                    }
//                }
//            }
//        }

//        @Override
//        public void onResume() {
//            super.onResume();
//            //TODO FIX THE CRASH ON RESUME
//            for (int i = 0; i < getPreferenceScreen().getPreferenceCount(); ++i) {
//                Preference preference = getPreferenceScreen().getPreference(i);
//                if (preference instanceof EditTextPreference) {
//                    EditTextPreference editTextPref = (EditTextPreference) preference; preference.setSummary(editTextPref.getText());
//                }
//                if (preference instanceof PreferenceGroup) {
//                    PreferenceGroup preferenceGroup = (PreferenceGroup) preference;
//                    for (int j = 0; j < preferenceGroup.getPreferenceCount(); ++j) {
//                        Preference singlePref = preferenceGroup.getPreference(j);
//                        updatePreference(singlePref, singlePref.getKey());
//                    }
//                } else {
//                    updatePreference(preference, preference.getKey());
//                }
//            }
//        }
//
//        @Override
//        public void onCreate(@Nullable Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
//            //deprecated
//            addPreferencesFromResource(R.xml.preferences);
//            //getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener();
//            folderPickerListener();
////            bindSummaryValues(findPreference("pref_key_language"));
////            bindSummaryValues(findPreference("pref_key_mov"));
////            bindSummaryValues(findPreference("pref_key_tv"));
////            bindSummaryValues(findPreference("pref_key_download_location"));
//        }

//        public void folderPickerListener(){
//            Preference storagePref = (Preference) findPreference(KEY_PREF_STORAGE_FOLDER);
//            storageFolder = new StringBuilder();
//            storagePref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//                @Override
//                public boolean onPreferenceClick(Preference preference) {
//                    Intent intent = new Intent(getActivity() , FilePickerActivity.class);
//                    intent.setType("file/*");
//                    intent.putExtra(FilePickerActivity.EXTRA_ALLOW_CREATE_DIR, true);
//                    intent.putExtra(FilePickerActivity.EXTRA_ALLOW_CREATE_DIR, true);
//                    intent.putExtra(FilePickerActivity.EXTRA_START_PATH, Environment.getExternalStorageDirectory().getPath());
//                    intent.putExtra(FilePickerActivity.EXTRA_MODE, FilePickerActivity.MODE_DIR);
//                    startActivityForResult(intent, RQS_OPEN_DOCUMENT_TREE);
//                    return true;
//                }
//            });
//        }

////        @Override
//        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//            updatePreference(findPreference(key), key);
//        }
//
//        private void updatePreference(Preference preference, String key) {
//            if (preference == null) return;
//            if (preference instanceof ListPreference) {
//                ListPreference listPreference = (ListPreference) preference;
//                listPreference.setSummary(listPreference.getEntry());
//                return;
//            }
//            SharedPreferences sharedPrefs = getPreferenceManager().getSharedPreferences();
//            preference.setSummary(sharedPrefs.getString(key, ""));
//        }
//    }
//
//    private static void bindSummaryValues(Preference preference){
//        preference.setOnPreferenceChangeListener(listener);
//        listener.onPreferenceChange(preference, PreferenceManager.getDefaultSharedPreferences(preference.getContext()).getString(preference.getKey(),""));
//    }
//
//    private static Preference.OnPreferenceChangeListener listener = new Preference.OnPreferenceChangeListener() {
//        @Override
//        public boolean onPreferenceChange(Preference preference, Object o) {
//            String stringVal = o.toString();
//            if (preference instanceof ListPreference) {
//                ListPreference listPreference = (ListPreference) preference;
//                int index = listPreference.findIndexOfValue(stringVal);
//                preference.setSummary(index > 0 ? listPreference.getEntries()[index] : null);
//            } else if (preference instanceof EditTextPreference) {
//                preference.setSummary(stringVal);
//            } else if (preference instanceof CheckBoxPreference){
//                CheckBoxPreference checkBoxPreference = (CheckBoxPreference) preference;
//                checkBoxPreference.setEnabled(Boolean.getBoolean(stringVal));
//            }
//            return true;
//        }

    };

}
