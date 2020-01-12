package net.simplifiedcoding.mytodo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

public class Setting extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        PreferenceManager.getDefaultSharedPreferences(getBaseContext()).
                registerOnSharedPreferenceChangeListener(this);

        setTheme();
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().
                replace(android.R.id.content,new PreferenceFrag()).commit();

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        //Toast.makeText(this, key, Toast.LENGTH_SHORT).show();

        if (key.equals("color_choices")){

            this.recreate();
        }
    }

    public static class PreferenceFrag extends PreferenceFragment {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferances);
        }
    }



public void setTheme (){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        if (sp.getString("color_choices","Red   ").equals("Red")){

        setTheme(R.style.RedTheme);
        }
        else {setTheme(R.style.BlueTheme);}
        }


}
