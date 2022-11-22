package com.roaita.imsakiyah.activity;
import android.app.*;
import android.graphics.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import android.os.CountDownTimer;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.view.ViewGroup;
import android.util.*;

import android.media.MediaPlayer;

import com.roaita.imsakiyah.R;
import com.roaita.imsakiyah.util.Audio;

public class main extends Activity implements OnSharedPreferenceChangeListener{
	
	Toast mToast;
	private static MediaPlayer mediaPlayer;

	private Audio audio; //= new Audio(this.getApplicationContext());

	private Context context;
	private MediaPlayer mp;  
	private AlarmManager alarmManager;
	private SharedPreferences prefs;


	private TextView TDateMasehi;
	private TextView TDateHijri;
	private TextView TMasjid;
	private TextView Tinfo;
	private TextView Tinfo2;
	private TextView Tnetxpray;
	private TextView Tsubuh;
	private TextView Tterbit;
	private TextView Tdzuhur;
	private TextView Tashar;
	private TextView Tmaghrib;
	private TextView Tisya;
	private TextView Timsak;
	private TextView Tfootnote;


	private int viewmodeon;
    private int viewmode;
	private Button mPickDate;
	private int mYear;
	private int mMonth;
	private int mDay;
    private int sDay;
	private int sHour;
	private int sMinut;
	private int nRandom;
	private int sSecond;

	private int i = 0;
	private int stssubuh = 0;
	private String adzan;
	private String praAdzan;
	private String nDhuhur;
	private String nAshar;
	private String nMagrib;
	private String nIsya;
	private String nShubuh;
	private String nTerbit;
	private String nextSholat;
	private String nMaghribMnt;
	private String nMaghribJam;
	private int shltjmt;

	private String ifJumat;
	private StringBuilder sb;
	private StringBuilder sb2;
	private long startTime	= 1000*60*10;
	private long interval = 1000;

	private long startTime2 = 1000*60*15;
	private long startTime3 = 1000*60*10;

	private final long interval2 = 1000;
	private long secondtimer = 0;
	Timer timer;
	MyTimerTask myTimerTask;
    private Calendar c;
	static final int DATE_DIALOG_ID = 0;
	final Kalender kal3 = new Kalender();
	String[] menittimer = { 
		"60","59",  "58","57","56","55","54","53","52","51","50",
		"49",  "48","47","46","45","44","43","42","41","30",
		"39",  "38","37","36","35","34","33","32","31","30",
		"29",  "28","27","26","25","24","23","22","21","20",
		"19",  "18","17","16","15","14","13","12","11","10",	
		"09",  "08","07","06","05","04","03","02","01","00","0","0",

	};


	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(com.roaita.imsakiyah.R.layout.jadwal_main);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


		//time
		timer = new Timer();
		myTimerTask = new MyTimerTask();
		this.prefs = PreferenceManager.getDefaultSharedPreferences(this);
        this.prefs.registerOnSharedPreferenceChangeListener(this);
        this.alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
		

        TMasjid = (TextView) this.findViewById(com.roaita.imsakiyah.R.id.masjid);
        TDateMasehi = (TextView) findViewById(com.roaita.imsakiyah.R.id.date);
		TDateHijri = (TextView) findViewById(com.roaita.imsakiyah.R.id.time);
		Tinfo = (TextView) findViewById(com.roaita.imsakiyah.R.id.vinfo);
		Tinfo2 = (TextView) findViewById(com.roaita.imsakiyah.R.id.vinfo2);
		Tnetxpray = (TextView) findViewById(com.roaita.imsakiyah.R.id.nextpray);

		Tsubuh = (TextView) this.findViewById(com.roaita.imsakiyah.R.id.subuh);
		Tterbit = (TextView) findViewById(com.roaita.imsakiyah.R.id.terbit);
		Tdzuhur = (TextView) findViewById(com.roaita.imsakiyah.R.id.dzuhur);
		Tashar = (TextView) findViewById(com.roaita.imsakiyah.R.id.ashar);
		Tmaghrib = (TextView) findViewById(com.roaita.imsakiyah.R.id.maghrib);
		Tisya = (TextView) findViewById(com.roaita.imsakiyah.R.id.isya);
		//Timsak = (TextView) findViewById(R.id.im);
        // get the current date
		viewmodeon = 0;
		viewmode = 0;
        c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        sDay = c.get(Calendar.DAY_OF_WEEK);
		sHour = c.get(Calendar.HOUR_OF_DAY)-1;
		shltjmt = 1;
		updateDisplay(mYear, mMonth, mDay);
		timer.schedule(myTimerTask, 1000, 1000);
	
	}





	// updates the date in the TextView
    private void updateDisplay(int y, int m, int d) 
	
{
    	final Kalender kal = new Kalender();
		shltjmt = 1;
		nRandom = 1;
		String infoSholat = "";
		StringBuilder sbSholat = new StringBuilder() ;
    	String[] jawa = kal.MasehiToJawa(y, m, d);
		
		// (mDay, mMonth, mYear);
		//tanggal masehi

		int mrh = 0;
		sb = new StringBuilder();
		sb2 = new StringBuilder() ;
		sb2 = kal.KajianPenting(jawa, sb2, mrh);
		sb = kal.AcaraPenting(jawa, sb, mrh);
		TDateMasehi.setText(
  			" "
			+jawa[0]+" "+ jawa[4]+" * "
			+jawa[2]+" "
			+jawa[11]+" "
			+jawa[3]+" M * "
			+jawa[5]+" "
			+jawa[6]+" "
			+jawa[7]+" H *");

		//jumat jumat
		Tinfo.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 50);
		Tinfo.setTextColor(Color.WHITE);
		StringBuilder khotib = new StringBuilder() ;
		khotib	= kal.jadwalkhotib(jawa, khotib);
		ifJumat = jawa[0];
		if(ifJumat.equalsIgnoreCase("Jum'at"))
		{
			if(sHour < 12){
				Tinfo.setText(khotib);
				Tinfo.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 35);
			}
		}

		//display garis
		Tnetxpray.setText(
			new StringBuilder()
			.append("JADWAL SHOLAT HARI INI "));					
		TDateMasehi.setTextColor(Color.YELLOW);
		//	mIqomah.setText(sb2);		
		sbSholat = kal.JadwalSholat(jawa, sbSholat);
		//infoSholat = ""  + sbSholat ;
		//TextView tvxSholat = (TextView)findViewById(R.id.legendDisplaySholat);		
		//tvxSholat.setText(infoSholat);



//_-----------list sholatwktu
		//TextView sholatv = (TextView)findViewById(R.id.legendDisplaySholat);		

	String listdtinfosholat = (String) ""  + sbSholat ;   // sholatv.getText();
		String[] separatedsholat =      listdtinfosholat.split("\n");
		Arrays.asList(separatedsholat).indexOf("3");
		ListAdapter adaptersholat = new MyAdaptersholat(this, separatedsholat);
	 //	ListView lvsholat = (ListView) findViewById(R.id.SholatListView);
		//lvsholat.setAdapter(adaptersholat);

		//set string sholat
		nShubuh   = separatedsholat[0].toString();
		nTerbit   = separatedsholat[1].toString();
		nDhuhur   = separatedsholat[2].toString();
		nAshar    = separatedsholat[3].toString();
		nMagrib  = separatedsholat[4].toString();
		nIsya     = separatedsholat[5].toString();
		Tsubuh.setText(nShubuh);
		Tterbit.setText(nTerbit);
		Tdzuhur.setText(nDhuhur);
		Tashar.setText(nAshar);
		Tmaghrib.setText(nMagrib);
		Tisya.setText(nIsya);


		/*
		 //test
		 //int	sHourtest = c.get(Calendar.HOUR_OF_DAY)-1;
		 //int	sMENUTtest = c.get(Calendar.MINUTE);

		 //	nShubuh   = sHourtest+":"+(sMENUTtest+2);
		 //	nTerbit   = sHourtest+":"+(sMENUTtest+8);
		 //	nDhuhur   = sHourtest+":"+(sMENUTtest+4);
		 //nAshar    = sHourtest+":"+(sMENUTtest+4);
		 //	nMagrib  = sHourtest+":"+(sMENUTtest+2);
		 //	nIsya     = sHourtest+":"+(sMENUTtest+1);
		 //

		 String[] separatedmaghrib =      nMagrib.split(":");
		 nMaghribJam = separatedmaghrib[0];
		 nMaghribMnt = separatedmaghrib[1];
		 nMagrib  = nMaghribJam+":"+nMaghribMnt;
		 mIqomah.setText(nextSholat);
		 //	mWellcome.setText(nMaghrib);
		 */
		audio = new Audio(this.getApplicationContext());

		//audio.playClick();

	}//end update



//-----------------iqomat-----------
    class MyAdapter2 extends ArrayAdapter<String>
    {
		public MyAdapter2(Context context, String[] values) 
		{
			super(context, com.roaita.imsakiyah.R.layout.tgl, values);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			LayoutInflater inflater = LayoutInflater.from(getContext());
			View view = inflater.inflate(com.roaita.imsakiyah.R.layout.tgl, parent, false);

			String text = getItem(position);

			TextView textView = (TextView) view.findViewById(com.roaita.imsakiyah.R.id.tglview1);
			textView.setText(text);
			LinearLayout layout = (LinearLayout) view.findViewById(com.roaita.imsakiyah.R.id.tglLayout);
			//	if ("\n".equals(text))layout.setVisibility(LinearLayout.GONE);
			//	if ("Hari Penting".equals(text))layout.setVisibility(LinearLayout.INVISIBLE);



			return view;
		}
    }


	//-------------time
	class MyTimerTask extends TimerTask 
	{	@Override
		public void run() 
		{
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat simpleDateFormat = 
				new SimpleDateFormat("dd/MMMM/yyyy");
			final String strDate = simpleDateFormat.format(calendar.getTime());
			SimpleDateFormat simpleHourFormat = 
				new SimpleDateFormat("HH");
			final String strHour = simpleHourFormat.format(calendar.getTime());
			SimpleDateFormat simpleMinutFormat = 
				new SimpleDateFormat("mm:ss");
			final String strMinut = simpleMinutFormat.format(calendar.getTime());
			sHour = calendar.get(Calendar.HOUR_OF_DAY);//-1;
			//	test
			int	sHourmaghrib = calendar.get(Calendar.HOUR_OF_DAY);

			sMinut = calendar.get(Calendar.MINUTE);
			sSecond = calendar.get(Calendar.SECOND);
			//String sMinut2 = sMinut;//+10;
			String sMinut2 = ""+sMinut;
			if(sMinut<=9)sMinut2 = "0"+sMinut;
			final int sMinut3 = sMinut;

			adzan = sHour+":"+sMinut2;
			int sHouradzanmaghrib = sHourmaghrib;
			final	String adzanmaghrib = sHouradzanmaghrib+":"+sMinut2;
			int pMinut1 = sMinut + 1;
			String pMinut = ""+pMinut1;
			if(sMinut<=9)pMinut = "0"+pMinut1;

			praAdzan = sHour+":"+pMinut;
			//final String adzanmaghrib = "A"+sHour+":"+sMinut2;
			//final String saatMaghrib = "A"+nMagrib;
			runOnUiThread(new Runnable()
				{
					@Override
					public void run()
					{

						if(adzan.equalsIgnoreCase(nShubuh)) //adzan waktu sekarang
						{
							nextSholat = "Dzuhur  \n"+nDhuhur;
							stssubuh = 1;
							if(sSecond==2){
								audio.playClick();
								shubuhtime(startTime2,1000);
							}
							Tinfo2.setText(sHour+"."+strMinut);
						}else
						{
							Tinfo2.setText(sHour+"."+strMinut);
						}
						if(adzan.equalsIgnoreCase(nDhuhur)) //adzan waktu sekarang
						{
							nextSholat = "Ashar  \n"+nAshar;
							shltjmt = 2;
							if(sSecond==1){
								audio.playClick();
								shubuhtime(startTime3,1000);
							}

							Tinfo2.setText(sHour+"."+strMinut);
						}else
						{
							Tinfo2.setText(sHour+"."+strMinut);
						}
						if(adzan.equalsIgnoreCase(nAshar)) //adzan waktu sekarang
						{
							nextSholat = "Maghrib  \n"+nMagrib;
							if(sSecond==2){
								audio.playClick();
								shubuhtime(startTime3,1000);
							}

							Tinfo2.setText(sHour+"."+strMinut);
						}else
						{
							Tinfo2.setText(sHour+"."+strMinut);
						}
						if(adzan.equalsIgnoreCase(nMagrib))//(adzanmaghrib.equalsIgnoreCase(nMagrib)) //adzan waktu sekarang
						{
							nextSholat = "Isya  \n"+nIsya;
							if(sSecond==1)shubuhtime(startTime3,1000);
							if(sSecond==2)audio.playClick();
							Tinfo2.setText(sHour+"."+strMinut);
						}else
						{
							Tinfo2.setText(sHour+"."+strMinut);
						}
						if(adzan.equalsIgnoreCase(nIsya)) //adzan waktu sekarang
						{
							nextSholat = "Shubuh \n"+nShubuh;
							if(sSecond==1){audio.playClick();

								shubuhtime(startTime3,1000);}
							Tinfo2.setText(sHour+"."+strMinut);
						}else	
						{
							Tinfo2.setText(sHour+"."+strMinut);
						}

						//++++++++++++++++++++++++++++


						if(praAdzan.equalsIgnoreCase(nShubuh)) //adzan waktu sekarang
						{
							Tinfo.setText(" PERSIAPAN \n ADZAN SHUBUH");
							nRandom =4;
							Tinfo2.setText(sHour+"."+strMinut);
						}else
						{
							Tinfo2.setText(sHour+"."+strMinut);
						}
						if(praAdzan.equalsIgnoreCase(nDhuhur)) //adzan waktu sekarang
						{
							Tinfo.setText(" PERSIAPAN \n ADZAN DZUHUR");
							nRandom =4;
							Tinfo.setText(sHour+"."+strMinut);
						}else
						{
							Tinfo2.setText(sHour+"."+strMinut);
						}
						if(praAdzan.equalsIgnoreCase(nAshar)) //adzan waktu sekarang
						{
							Tinfo.setText(" PERSIAPAN \n ADZAN ASHAR");
							nRandom =4;
							Tinfo2.setText(sHour+"."+strMinut);
						}else
						{
							Tinfo2.setText(sHour+"."+strMinut);
						}

						if(praAdzan.equalsIgnoreCase(nMagrib)) //adzan waktu sekarang
						{
							Tinfo.setText(" PERSIAPAN \n ADZAN MAGHRIB");
							nRandom =4;
							Tinfo2.setText(sHour+"."+strMinut);
						}else
						{
							Tinfo2.setText(sHour+":l."+strMinut);
						}

						if(praAdzan.equalsIgnoreCase(nIsya)) //adzan waktu sekarang
						{
							Tinfo.setText(" PERSIAPAN \n ADZAN ISYA");
							nRandom =4;
							Tinfo2.setText(sHour+":l."+strMinut);
						}else	
						{
							Tinfo2.setText(sHour+"."+strMinut);
						}

						//+++++++++++++++++++++++++nextsholat

						//++++++++++++Random
						if(nRandom ==1){
							//uodate
							if(sMinut==38) //update tgl
							{
								c = Calendar.getInstance();

								sHour= c.get(Calendar.HOUR_OF_DAY);
								mYear =  c.get(Calendar.YEAR);
								mMonth =  c.get(Calendar.MONTH);
								mDay = c.get(Calendar.DAY_OF_MONTH);

								updateDisplay(mYear, mMonth, mDay);

								Tinfo2.setText(sHour+"."+strMinut);
							}else
							{
								Tinfo2.setText( sHour+"."+strMinut);
							}
							//random
							if(sSecond ==50){

								Tinfo.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);

								Tinfo.setText(nextSholat);}
							if(sSecond ==25){
								//audio.playClick();
								Tinfo.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
								Tinfo.setText( " Dzuhur \n"+nDhuhur);
							}
							if(sSecond ==30)
								Tinfo.setText( " ASHAR \n"+nAshar);

							if(sSecond ==35)
								Tinfo.setText(" MAGHRIB \n"+nMagrib);

							if(sSecond ==40)
								Tinfo.setText(" ISYA \n"+nIsya);

							if(sSecond ==45)
								Tinfo.setText(" SHUBUH \n"+nShubuh);


							if(sSecond ==15){
								Tinfo.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
								Tinfo.setText(sb2);



							}
						};
						//end random

					}
				}
			);
		}

	}



///	-----+
	private void shubuhtime (long panjang, long akhir)
	{


		new CountDownTimer(panjang, akhir) 
		{

			public void onTick(long millisUntilFinished) 
			{
				Tinfo.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 35);
				nRandom = 5;
				if(secondtimer < 0)secondtimer =59;
				long menit = millisUntilFinished / 60000;
				String sSecondT = "00";
				if(sSecond>1)
				{
					sSecondT = menittimer[sSecond];
				}

				if(menit==14){

					TDateMasehi.setText("Dengarkanlah Adzan");
				}

				if(menit==9)
				{
					if(stssubuh==0)TDateMasehi.setText("Dengarkanlah Adzan");
				}
				if(menit>=3)
				{

					Tinfo.setText(" MENUJU IQOMAT \n" + menit + " menit Lagi\n"+sSecondT+" detik");//millisUntilFinished);
					if(sSecond==20)TDateMasehi.setText("Matikan Handphone anda");
					if(sSecond==39)TDateMasehi.setText("Waktu untuk Sholat Sunnah masih "+menit+" Menit lagi");
					//	if(nextSholat.equalsIgnoreCase("")&&ifJumat.!equals());
				}




				if(menit == 2)
				{
					Tinfo.setText(" MENUJU IQOMAT \n" + menit + " menit Lagi\n"+sSecondT+" dtk");//millisUntilFinished);
					if(sSecond == 30)TDateMasehi.setText("  MAAF MENDEKATI IQOMAT HARAP UNTUK TIDAK SHOLAT SUNNAH!!!");
					Tinfo.setTextColor(Color.YELLOW);
				}
				if(menit == 1){
					Tinfo.setTextColor(Color.RED);
					Tinfo.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 35);
					Tinfo.setText( " IQOMAT "+menit + " : "+sSecondT+" s\nPERSIAPAN IQOMAT");

					if(sSecond == 59){
						TDateMasehi.setText("SAATNYA \nIQOMAT");
						audio.playClick();
					}
				}
				if(menit == 0){
					Tinfo.setText("SHOFF\nLURUS DAN RAPAT");
				}
				//	timeElapsed = startTime - millisUntilFinished;
				//timeElapsedView.setText("Time Elapsed: " + String.valueOf(timeElapsed));
				if(ifJumat.equalsIgnoreCase("Jum'at"))
				{
					if(sHour>11&&sHour<12)
					{
						Tinfo.setText(" Mendengarkan khutbah ");//millisUntilFinished);
					}else{
						Tinfo.setText("IQOMAT \n" + menit + " : "+sSecondT+" s");//millisUntilFinished);
					}
				}else{

				}

			}


			public void onFinish()
			{

				updateDisplay(mYear, mMonth, mDay);
				Tinfo.setText(nextSholat);
				Tinfo.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 50);
				nRandom =1;
				shltjmt = 1;
				stssubuh=0;
			}
		}.start();

	}


//+++++++++

//----------------------------
    class MyAdapter extends ArrayAdapter<String>
    {
		public MyAdapter(Context context, String[] values) 
		{
			super(context, com.roaita.imsakiyah.R.layout.entry, values);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			LayoutInflater inflater = LayoutInflater.from(getContext());
			View view = inflater.inflate(com.roaita.imsakiyah.R.layout.entry, parent, false);

			String text = getItem(position);

			TextView textView = (TextView) view.findViewById(com.roaita.imsakiyah.R.id.entryTextView1);
			textView.setText(text);
			LinearLayout layout = (LinearLayout) view.findViewById(com.roaita.imsakiyah.R.id.entryLayout);
			//	if ("\n".equals(text))layout.setVisibility(LinearLayout.GONE);
			//	if ("Hari Penting".equals(text))layout.setVisibility(LinearLayout.INVISIBLE);


			ImageView imageView = (ImageView) view.findViewById(com.roaita.imsakiyah.R.id.entryImageView1);
			imageView.setImageResource(com.roaita.imsakiyah.R.drawable.referensi);
			if ("Hari Penting".equals(text)) imageView.setImageResource(com.roaita.imsakiyah.R.drawable.mwidgetbg); //android adalah framwork
			if ("".equals(text))imageView.setVisibility(imageView.INVISIBLE);
			if ("Hari Penting".equals(text))imageView.setVisibility(imageView.INVISIBLE);


			return view;
		}
    }
	//expand
	class MyAdaptersholat extends ArrayAdapter<String>
    {
		public MyAdaptersholat(Context context, String[] values) 
		{
			super(context, com.roaita.imsakiyah.R.layout.entrysholat, values);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			LayoutInflater inflater = LayoutInflater.from(getContext());
			View view = inflater.inflate(com.roaita.imsakiyah.R.layout.entrysholat, parent, false);

			String text = getItem(position);

			TextView textView = (TextView) view.findViewById(com.roaita.imsakiyah.R.id.entryTextViewWaktu);
			textView.setText(text);

			TextView textName = (TextView) view.findViewById(com.roaita.imsakiyah.R.id.entryTextViewName);
			if (position==0)textName.setText("Shubuh");
			if (position==1)textName.setText("Dluha");
			if (position==2)textName.setText("Dzuhur");
			if (position==3)textName.setText("Ashar");
			if (position==4)textName.setText("Maghrib");
			if (position==5)textName.setText("Isya");
			if (position==6)textName.setText("Berikutnya");


			LinearLayout layout = (LinearLayout) view.findViewById(com.roaita.imsakiyah.R.id.entryLayoutsholat);
			//	if ("\n".equals(text))layout.setVisibility(LinearLayout.GONE);
			//	if ("Hari Penting".equals(text))layout.setVisibility(LinearLayout.INVISIBLE);


			ImageView imageView = (ImageView) view.findViewById(com.roaita.imsakiyah.R.id.entryImageViewsholat);
			imageView.setImageResource(com.roaita.imsakiyah.R.drawable.referensi);
			if ("Hari Penting".equals(text)) imageView.setImageResource(R.drawable.mwidgetbg); //android adalah framwork
			if ("".equals(text))imageView.setVisibility(imageView.INVISIBLE);
			if ("Hari Penting".equals(text))imageView.setVisibility(imageView.INVISIBLE);


			return view;
		}
    }



	///++++++	

	@Override
	protected void onDestroy() 
	{
		super.onDestroy();
		this.prefs.unregisterOnSharedPreferenceChangeListener(this);
	}


    private void sendTextToWidget(Context context) 
	{
		Intent uiIntent2 = new Intent(Constants.ACTION_WIDGET_UPDATE_FROM_ACTIVITY);
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		uiIntent2.putExtra(Constants.INTENT_EXTRA_WIDGET_TEXT,"sample di klik pada " + sdf.format(new Date()));
		context.sendBroadcast(uiIntent2);
    }

    public void startAlarm()
	{
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTimeInMillis(System.currentTimeMillis());
    	calendar.add(Calendar.SECOND, 100000000);
    	alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 20*10, getPendingIntentForAlarm());
    	final Editor edit = prefs.edit();
		edit.putBoolean(Constants.ALARM_STATUS, true);
		edit.commit();
    }

	private void toggleAlarm() 
	{
		if (isAlarmEnabled()) {
			stopAlarm();
		} else {
			startAlarm();
		}
	}

	private boolean isAlarmEnabled() 
	{
		return this.prefs.getBoolean(Constants.ALARM_STATUS, false);
	}

    private PendingIntent getPendingIntentForAlarm() 
	{
    	Intent intent = new Intent(Constants.ACTION_WIDGET_UPDATE_FROM_ALARM);

		String[] jawa = kal3.MasehiToJawa(mYear, mMonth, mDay);
		String pesan = jawa[0]+" "+jawa[4]+", "+jawa[5]+" "+jawa[6]+" "+jawa[7];

    	intent.putExtra(Constants.INTENT_EXTRA_WIDGET_TEXT,pesan + "\n");
    	return PendingIntent.getBroadcast(this, 0, intent, 0);
    }

	public void stopAlarm()
	{
    	alarmManager.cancel(getPendingIntentForAlarm());
    	final Editor edit = prefs.edit();
		edit.putBoolean(Constants.ALARM_STATUS, false);
		edit.commit();    	
    }

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,String key) {
		if (Constants.ALARM_STATUS.equals(key)) {

		}
	}

	// the callback received when the user "sets" the date in the dialog
    private DatePickerDialog.OnDateSetListener mDateSetListener =
	new DatePickerDialog.OnDateSetListener() 
	{

		public void onDateSet(DatePicker view, int year, 
							  int monthOfYear, int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;

			c = Calendar.getInstance();
			c.set(Calendar.YEAR, mYear);
			c.set(Calendar.MONTH, mMonth);
			c.set(Calendar.DAY_OF_MONTH, mDay);
			viewmode = 0;
			updateDisplay(mYear, mMonth, mDay);
		}
	};


	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
			case DATE_DIALOG_ID:
				return new DatePickerDialog(this,
											mDateSetListener,
											mYear, mMonth, mDay);
		}
		return null;
	}



	//ads

	/**
	 * Part of the activity's life cycle, StartAppAd should be integrated here.
	 */
	@Override
	public void onResume(){
		super.onResume();

	}

	/**
	 * Part of the activity's life cycle, StartAppAd should be integrated here
	 * for the home button exit ad integration.
	 */
	@Override
	public void onPause(){
		super.onPause();

	}

	/**
	 * Part of the activity's life cycle, StartAppAd should be integrated here
	 * for the back button exit ad integration.
	 */
	@Override
	public void onBackPressed() {

		super.onBackPressed();
	}


}

	

		 
		 
		 
	






