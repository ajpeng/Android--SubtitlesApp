package com.example.alex.subtitles;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v4.provider.DocumentFile;
import android.support.v7.app.ActionBar;
import android.view.MenuInflater;

import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nononsenseapps.filepicker.FilePickerActivity;
import com.nononsenseapps.filepicker.Utils;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;


public class SettingsActivity extends AppCompatPreferenceActivity{
    private static final int RQS_OPEN_DOCUMENT_TREE = 2;
    private static StringBuilder storageFolder ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MainSettingsFragment()).commit();

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


    public static class MainSettingsFragment extends PreferenceFragment {
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent intent) {
            if (null != intent && !intent.getBooleanExtra(FilePickerActivity.EXTRA_ALLOW_MULTIPLE, false)) {
                if (requestCode == RQS_OPEN_DOCUMENT_TREE && resultCode == Activity.RESULT_OK) {
                    // Use the provided utility method to parse the result
                    List<Uri> files = Utils.getSelectedFilesFromResult(intent);
                    for (Uri uri : files) {
                        //File file = Utils.getFileForUri(uri);
                        storageFolder.append(uri.getPath());
                    }
                }
            }
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
            Preference storagePref = (Preference) findPreference("pref_key_download_location");
            storageFolder = new StringBuilder();
            storagePref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Intent intent = new Intent(getActivity() , FilePickerActivity.class);
                    intent.setType("file/*");
                    intent.putExtra(FilePickerActivity.EXTRA_ALLOW_CREATE_DIR, true);
                    intent.putExtra(FilePickerActivity.EXTRA_ALLOW_CREATE_DIR, true);
                    intent.putExtra(FilePickerActivity.EXTRA_START_PATH, Environment.getExternalStorageDirectory().getPath());
                    intent.putExtra(FilePickerActivity.EXTRA_MODE, FilePickerActivity.MODE_DIR);
                    startActivityForResult(intent, RQS_OPEN_DOCUMENT_TREE);

                    return true;
                }


            });
        }


    }

}
