package com.roaita.imsakiyah.notes;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.roaita.imsakiyah.R;


public class Keuangan extends Activity {

    EditText eJmtke, eTgl, ePmskn, eplgrn, esaldo;
    EditText eJmtke2, eTgl2, ePmskn2, eplgrn2, esaldo2;
    EditText eJmtke3, eTgl3, ePmskn3, eplgrn3, esaldo3;
    EditText eJmtke4, eTgl4, ePmskn4, eplgrn4, esaldo4, esaldoakhir;

    private String seJmtke = "jumatI", seTgl = "tglI", sePmskn = "pemasukanI", seplgrn = "pengeluaranI", sesaldo = "saldoI";
    private String seJmtke2 = "jumatII", seTgl2 = "tglII", sePmskn2 = "pemasukanII", seplgrn2 = "pengeluaranII", sesaldo2 = "saldoII";
    private String seJmtke3 = "jumatIII", seTgl3 = "tglIII", sePmskn3 = "pemasukanIII", seplgrn3 = "pengeluaranIII", sesaldo3 = "saldoIII";
    private String seJmtke4 = "jumatIV", seTgl4 = "tglIV", sePmskn4 = "pemasukanIV", seplgrn4 = "pengeluaranIV", sesaldo4 = "saldoIV", sesaldoakhir ="saldoakhir";

    Button Btnadd;




    private static SharedPreferences pref;
    public static void setDataSave(String namadata, String data){
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(namadata, data);
        editor.apply();
    }
    /** Mengembalikan nilai dari key KEY_USERNAME_SEDANG_LOGIN berupa String */
    public static String getDataSave(String namadata){
        return pref.getString(namadata,"");
    }


    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.card_keuangan_activity);
        pref = getSharedPreferences("mypreferences", MODE_PRIVATE);
        int titleId = getResources().getIdentifier("action_bar_title", "id","android");
        TextView yourTextView = (TextView) findViewById(titleId);

        eJmtke = (EditText) findViewById(R.id.jumatke);
        eTgl = (EditText) findViewById(R.id.pmskntgl);
        ePmskn = (EditText) findViewById(R.id.pmsknnominal);
        eplgrn = (EditText) findViewById(R.id.pglrnnominal);
        esaldo = (EditText) findViewById(R.id.sldonominal);

        eJmtke2 = (EditText) findViewById(R.id.jumatke2);
        eTgl2 = (EditText) findViewById(R.id.pmskntgl2);
        ePmskn2 = (EditText) findViewById(R.id.pmsknnominal2);
        eplgrn2 = (EditText) findViewById(R.id.pglrnnominal2);
        esaldo2 = (EditText) findViewById(R.id.sldonominal2);

        eJmtke3 = (EditText) findViewById(R.id.jumatke3);
        eTgl3 = (EditText) findViewById(R.id.pmskntgl3);
        ePmskn3 = (EditText) findViewById(R.id.pmsknnominal3);
        eplgrn3 = (EditText) findViewById(R.id.pglrnnominal3);
        esaldo3 = (EditText) findViewById(R.id.sldonominal3);

        eJmtke4 = (EditText) findViewById(R.id.jumatke4);
        eTgl4 = (EditText) findViewById(R.id.pmskntgl4);
        ePmskn4 = (EditText) findViewById(R.id.pmsknnominal4);
        eplgrn4 = (EditText) findViewById(R.id.pglrnnominal4);
        esaldo4 = (EditText) findViewById(R.id.sldonominal4);

        esaldoakhir = (EditText) findViewById(R.id.saldoakhir);

        eJmtke.setText(getDataSave(seJmtke));
        eJmtke2.setText(getDataSave(seJmtke2));
        eJmtke3.setText(getDataSave(seJmtke3));
        eJmtke4.setText(getDataSave(seJmtke4));

        eTgl.setText(getDataSave(seTgl));
        eTgl2.setText(getDataSave(seTgl2));
        eTgl3.setText(getDataSave(seTgl3));
        eTgl4.setText(getDataSave(seTgl4));

        ePmskn.setText(getDataSave(sePmskn));
        ePmskn2.setText(getDataSave(sePmskn2));
        ePmskn3.setText(getDataSave(sePmskn3));
        ePmskn4.setText(getDataSave(sePmskn4));

        eplgrn.setText(getDataSave(seplgrn));
        eplgrn2.setText(getDataSave(seplgrn2));
        eplgrn3.setText(getDataSave(seplgrn3));
        eplgrn4.setText(getDataSave(seplgrn4));

        esaldo.setText(getDataSave(sesaldo));
        esaldo2.setText(getDataSave(sesaldo2));
        esaldo3.setText(getDataSave(sesaldo3));
        esaldo4.setText(getDataSave(sesaldo4));

        esaldoakhir.setText(getDataSave(sesaldoakhir));
    }


    public void onClickAddInfak(View v) {
        try{
            if(!eJmtke.getText().toString().trim().isEmpty()) setDataSave(seJmtke, String.valueOf(eJmtke.getText()));
            if(!eJmtke2.getText().toString().trim().isEmpty()) setDataSave(seJmtke2, String.valueOf(eJmtke2.getText()));
            if(!eJmtke3.getText().toString().trim().isEmpty()) setDataSave(seJmtke3, String.valueOf(eJmtke3.getText()));
            if(!eJmtke4.getText().toString().trim().isEmpty()) setDataSave(seJmtke4, String.valueOf(eJmtke4.getText()));

            if(!eTgl.getText().toString().trim().isEmpty()) setDataSave(seTgl, String.valueOf(eTgl.getText()));
            if(!eTgl2.getText().toString().trim().isEmpty()) setDataSave(seTgl2, String.valueOf(eTgl2.getText()));
            if(!eTgl3.getText().toString().trim().isEmpty()) setDataSave(seTgl3, String.valueOf(eTgl3.getText()));
            if(!eTgl4.getText().toString().trim().isEmpty()) setDataSave(seTgl4, String.valueOf(eTgl4.getText()));

            if(!ePmskn.getText().toString().trim().isEmpty()) setDataSave(sePmskn, String.valueOf(ePmskn.getText()));
            if(!ePmskn2.getText().toString().trim().isEmpty()) setDataSave(sePmskn2, String.valueOf(ePmskn2.getText()));
            if(!ePmskn3.getText().toString().trim().isEmpty()) setDataSave(sePmskn3, String.valueOf(ePmskn3.getText()));
            if(!ePmskn4.getText().toString().trim().isEmpty()) setDataSave(sePmskn4, String.valueOf(ePmskn4.getText()));

            if(!eplgrn.getText().toString().trim().isEmpty()) setDataSave(seplgrn, String.valueOf(eplgrn.getText()));
            if(!eplgrn2.getText().toString().trim().isEmpty()) setDataSave(seplgrn2, String.valueOf(eplgrn2.getText()));
            if(!eplgrn3.getText().toString().trim().isEmpty()) setDataSave(seplgrn3, String.valueOf(eplgrn3.getText()));
            if(!eplgrn4.getText().toString().trim().isEmpty()) setDataSave(seplgrn4, String.valueOf(eplgrn4.getText()));

            if(!esaldo.getText().toString().trim().isEmpty()) setDataSave(sesaldo, String.valueOf(esaldo.getText()));
            if(!esaldo2.getText().toString().trim().isEmpty()) setDataSave(sesaldo2, String.valueOf(esaldo2.getText()));
            if(!esaldo3.getText().toString().trim().isEmpty()) setDataSave(sesaldo3, String.valueOf(esaldo3.getText()));
            if(!esaldo4.getText().toString().trim().isEmpty()) setDataSave(sesaldo4, String.valueOf(esaldo4.getText()));
            if(!esaldoakhir.getText().toString().trim().isEmpty()) setDataSave(sesaldoakhir, String.valueOf(esaldoakhir.getText()));

            Toast.makeText(getApplicationContext(),"Berhasil Disimpan",Toast.LENGTH_LONG).show();


    } catch (Exception e){Toast.makeText(getApplicationContext(),"Error Please try again",Toast.LENGTH_LONG).show();}
    }

}
