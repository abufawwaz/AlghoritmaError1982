package com.roaita.imsakiyah.activity;


import android.app.*;
import android.os.*;
import android.view.*;
import android.content.*;
import android.provider.*;

import com.roaita.imsakiyah.R;

public class AppPreferences extends Activity 
	{
		@Override
		protected void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.setting);
			
			
		}
		
	public void openreferens(View v) {
		//menamp8lkan change keyboard
		//	this.startActivity(new Intent("com.myfawwaz.simplekeyboard.softkeyboard.ImePreferences"));
		this.startActivity(new Intent("android.settings.DATE_SETTINGS"));
		//andorid.Settings.DatePickerDialog

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
		}
		startActivity(i);
	}
		
	}
