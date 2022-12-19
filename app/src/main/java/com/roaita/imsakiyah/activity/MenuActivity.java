package com.roaita.imsakiyah.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.roaita.imsakiyah.R;
import com.roaita.imsakiyah.notes.Keuangan;
import com.roaita.imsakiyah.notes.NoteActivity;
import com.roaita.imsakiyah.notes.SpacedService;


public class MenuActivity extends Activity
{
    private boolean doubleBackToExitPressedOnce = false;
    private static SharedPreferences pref;
    private EditText Esubuh, Edzuhur, Eashar, Emaghrib, Eisya,
            eNamaMasjid, eAlamatMasjid, eRunningText;
    private String tmrshu = "timerSubuh",tmrdz="timerDzuhur",tmrAs= "timerAshar",tmrmag="timerMaghrib",tmrIsy="timerIsya";
    private String setnamamasjid ="namamasjid",setalamat="alamat",setfooter ="footer";

    public void onClickSave(View v) {
       if(!Esubuh.getText().toString().trim().isEmpty()) setDataSave(tmrshu, String.valueOf(Esubuh.getText()));
        if(!Edzuhur.getText().toString().trim().isEmpty()) setDataSave(tmrdz, String.valueOf(Edzuhur.getText()));
        if(!Eashar.getText().toString().trim().isEmpty())  setDataSave(tmrAs, String.valueOf(Eashar.getText()));
        if(!Emaghrib.getText().toString().trim().isEmpty())   setDataSave(tmrmag, String.valueOf(Emaghrib.getText()));
        if(!Eisya.getText().toString().trim().isEmpty())   setDataSave(tmrIsy, String.valueOf(Eisya.getText()));

        if(!eNamaMasjid.getText().toString().trim().isEmpty())   setDataSave(setnamamasjid, String.valueOf(eNamaMasjid.getText()));
        if(!eAlamatMasjid.getText().toString().trim().isEmpty())  setDataSave(setalamat, String.valueOf(eAlamatMasjid.getText()));
        if(!eRunningText.getText().toString().trim().isEmpty()) setDataSave(setfooter, String.valueOf(eRunningText.getText()));

    }
    /** Deklarasi Edit Preferences dan mengubah data
     *  yang memiliki key KEY_USERNAME_SEDANG_LOGIN dengan parameter username */
    public void setDataSave(String namadata, String data){
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(namadata, data);
        editor.apply();
        Toast.makeText(getApplicationContext(),"Data Sudah Disimpan !!!",Toast.LENGTH_SHORT).show();

    }
    /** Mengembalikan nilai dari key KEY_USERNAME_SEDANG_LOGIN berupa String */
    public static String getDataSave(String namadata){
        return pref.getString(namadata,"");
    }




    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_menu_setup);
        pref = getSharedPreferences("mypreferences", MODE_PRIVATE);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Esubuh      = (EditText) findViewById(R.id.timersubuh);
        Edzuhur     = (EditText) findViewById(R.id.timerdzuhur);
        Eashar      = (EditText) findViewById(R.id.timerashar);
        Emaghrib    = (EditText) findViewById(R.id.timermaghrib);
        Eisya       = (EditText) findViewById(R.id.timerisya);

        eNamaMasjid   = (EditText) findViewById(R.id.namamasjid);
        eAlamatMasjid   = (EditText) findViewById(R.id.alamatmasjid);
        eRunningText  = (EditText) findViewById(R.id.notefooter);

        Intent localIntent = new Intent(getApplicationContext(), SpacedService.class);
        localIntent.putExtra("KEY1", "Value to be used by the service");
        getApplicationContext().startService(localIntent);
        Esubuh.setText(getDataSave(tmrshu));
        Edzuhur.setText(getDataSave(tmrdz));
        Eashar.setText(getDataSave(tmrAs));
        Emaghrib.setText(getDataSave(tmrmag));
        Eisya.setText(getDataSave(tmrIsy));
        eNamaMasjid.setText(getDataSave(setnamamasjid));
        eAlamatMasjid.setText(getDataSave(setalamat));
        eRunningText.setText(getDataSave(setfooter));

       }


    public void onClickNew(View v) {
        startActivity(new Intent(this, NoteActivity.class));

    }


    public void onClick(View v) {
        Intent i = null;
        switch(v.getId()){
            case R.id.btndate:
                i = new Intent("android.settings.DATE_SETTINGS");
                break;
            case R.id.btnwifi:
                i = new Intent("android.settings.WIFI_SETTINGS");
                break;
            case R.id.btnset:
                i = new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
                break;
            case R.id.btnkeuangan:
                i = new Intent(this, Keuangan.class);
                break;
            case R.id.btnBack:
                i = new Intent(this, MainActivity.class);
                break;
            case R.id.btnUpdate:
                i = new Intent(this, UpdateActivity.class);
                break;
        }
        startActivity(i);
    }

}
