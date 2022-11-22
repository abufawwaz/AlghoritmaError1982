package com.roaita.imsakiyah.activity;


import android.graphics.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.os.CountDownTimer;
import android.app.Activity;
import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.provider.*;
import android.view.ViewGroup;
import android.util.*;

import android.media.MediaPlayer;
import android.content.res.*;
import java.io.*;
import android.graphics.drawable.*;
import android.content.pm.*;

import com.roaita.imsakiyah.R;
import com.roaita.imsakiyah.util.Audio;

public class MainJadwal extends Activity //implements //OnSharedPreferenceChangeListener
{
	
	Toast mToast;
	private static MediaPlayer mediaPlayer;

	private Audio audio; //= new Audio(this.getApplicationContext());

	private Context context;
	private MediaPlayer mp;  
	private AlarmManager alarmManager;
	private SharedPreferences prefs;


	private TextView TDateMasehi;
	private TextView TTime;
	private TextView TMasjid;
	private TextView Tinfo;
	private TextView Tinfo2;
	private Button Tnetxpray;
	private TextView Tsubuh;
	private TextView Tterbit;
	private TextView Tdzuhur;
	private TextView Tashar;
	private TextView Tmaghrib;
	private TextView Tisya;
	private TextView Timsak;
	private TextView Tfootnote;
	private TextView TprogresS;
	private TextView TprogresM;
	private LinearLayout layoutbg;
	private ProgressBar progressBarS;
	private ProgressBar progressBarM;
	private int progressStatus = 0;
	
	
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
	private String resetwaktu ="mati listrik waktu tanggal \nbelum diset";
	private int    errorwaktu =0;
	private String nMaghribMnt;
	private String nMaghribJam;
	
	private String timerIqomat = "TIMER IQOMAT";
	private String infoKajian;
	private String infoKajian2;
	
	private String infoAyat;
	private String infoAyat2;
	
	private String infoHadist;
	private String infoHadist2;
	
	private String infoFoot;
	private String infoFoot2;
	
	private int shltjmt;

	private String ifJumat;
	//private StringBuilder sb;
	//private StringBuilder sb2;
	private long startTime	= 1000*60*10;
	private long interval = 1000;

	private long startTime2 = 1000*60*15;
	private long startTime3 = 1000*60*10;
	private long startTimetest = 1000*60*3;
	private boolean test = false;
	
	private final long interval2 = 1000;
	private long secondtimer = 0;
	Timer timer;
	MyTimerTask myTimerTask;
    private Calendar c;
	static final int DATE_DIALOG_ID = 0;
	final Kalender kal3 = new Kalender();
	String[] menittimer = { 
		"60",
		"59",  "58","57","56","55","54","53","52","51","50",
		"49",  "48","47","46","45","44","43","42","41","40",
		"39",  "38","37","36","35","34","33","32","31","30",
		"29",  "28","27","26","25","24","23","22","21","20",
		"19",  "18","17","16","15","14","13","12","11","10",	
		"09",  "08","07","06","05","04","03","02","01","00"

	};
	
	final int sdk = android.os.Build.VERSION.SDK_INT;


	//to know tv or mobile
	private boolean isDirectToTV() {
		return(getPackageManager().hasSystemFeature(PackageManager.FEATURE_TELEVISION)
			|| getPackageManager().hasSystemFeature(PackageManager.FEATURE_LEANBACK));
	}
	
	//Resources res = getResources();
	//String[] ayatrandom = res.getStringArray(R.array.ayats);
/*
	
	if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
		layout.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ready) );
	} else {
		layout.setBackground(ContextCompat.getDrawable(context, R.drawable.ready));
	}
	RelativeLayout layout =(RelativeLayout)findViewById(R.id.background);
	layout.setBackgroundResource(R.drawable.ready);
*/
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		boolean tvorhp = isDirectToTV();
		/*
		if(tvorhp!=true) {
			//Tfootnote.setText("bukan tv");
			setContentView(R.layout.layouthp_vertical);
		}else{ 
			//Tfootnote.setText(" tv");
			
			setContentView(R.layout.jadwal_main);
		}*/
		//setContentView(R.layout.jadwal_main);
		//setContentView(R.layout.layout_hp);
		setContentView(com.roaita.imsakiyah.R.layout.layouthp_vertical);
		
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

		MediaPlayer mPlayer = MediaPlayer.create(this, com.roaita.imsakiyah.R.raw.alert);
       // mPlayer.start();
		//time
		timer = new Timer();
		myTimerTask = new MyTimerTask();
		/*
		this.prefs = PreferenceManager.getDefaultSharedPreferences(this);
        this.prefs.registerOnSharedPreferenceChangeListener(this);
        this.alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
		*/
		layoutbg =(LinearLayout)findViewById(com.roaita.imsakiyah.R.id.layout_bg);
		
        TMasjid = (TextView) this.findViewById(com.roaita.imsakiyah.R.id.masjid);
		
        TDateMasehi = (TextView) findViewById(com.roaita.imsakiyah.R.id.date);
		TDateMasehi.setTextColor(Color.WHITE);
		TTime = (TextView) findViewById(com.roaita.imsakiyah.R.id.time);
		TTime.setTextColor(Color.WHITE);
		
		Tinfo = (TextView) findViewById(com.roaita.imsakiyah.R.id.vinfo);
		Tinfo2 = (TextView) findViewById(com.roaita.imsakiyah.R.id.vinfo2);
		Tnetxpray = (Button) findViewById(com.roaita.imsakiyah.R.id.nextpray);

		Tsubuh = (TextView) this.findViewById(com.roaita.imsakiyah.R.id.subuh);
		Tterbit = (TextView) findViewById(com.roaita.imsakiyah.R.id.terbit);
		Tdzuhur = (TextView) findViewById(com.roaita.imsakiyah.R.id.dzuhur);
		Tashar = (TextView) findViewById(com.roaita.imsakiyah.R.id.ashar);
		Tmaghrib = (TextView) findViewById(com.roaita.imsakiyah.R.id.maghrib);
		Tisya = (TextView) findViewById(com.roaita.imsakiyah.R.id.isya);
		Timsak = (TextView) findViewById(com.roaita.imsakiyah.R.id.imsak);
		Tfootnote= (TextView) findViewById(com.roaita.imsakiyah.R.id.footnote);
		
		TprogresS =(TextView) findViewById(com.roaita.imsakiyah.R.id.textprogres1);
		TprogresM =(TextView) findViewById(com.roaita.imsakiyah.R.id.textprogres2);
		progressBarM = (ProgressBar) findViewById(com.roaita.imsakiyah.R.id.progressBar);
		progressBarM.setVisibility(LinearLayout.INVISIBLE);
		progressBarS = (ProgressBar) findViewById(com.roaita.imsakiyah.R.id.progressBarS);
		progressBarS.setVisibility(LinearLayout.GONE);
		TprogresS.setVisibility(LinearLayout.GONE);
		TprogresM.setVisibility(LinearLayout.GONE);
		Tfootnote.setSelected(true);  // Set focus to the textview to running text
		infoKajian = getResources().getString(com.roaita.imsakiyah.R.string.kajiansubuh);
		infoKajian2 = getResources().getString(com.roaita.imsakiyah.R.string.kajiansubuh2);
	     //layoutbg.setBackgroundResource(R.drawable.a3) );
		//layoutbg.setBackground(getDrawable( R.drawable.a10) );
		
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
		//setviewtextinfo("coba", Tinfo, 50, "#bdbdbd","#12345678");
		
		updateDisplay(mYear, mMonth, mDay);
		timer.schedule(myTimerTask, 1000, 1000);
		
		if(test == true){ // test
		shubuhtime(startTimetest,1000);
		nextSholat = "Ashar  "+nAshar;
		}
	}

	private Drawable getDrawable(Context context, int a10)
	{
	// TODO: Implement this method
	return null;
	}

	// updates the date in the TextView
    private void updateDisplay(int y, int m, int d) 
	
{
    	final Kalender kal = new Kalender();
		shltjmt = 1;
		nRandom = 1;
		String infoSholat = "";
		StringBuilder sbSholat = new StringBuilder() ;
    	String[] jawa = kal.MasehiToJawa(y, m, d);// (mDay, mMonth, mYear);
		//tanggal masehi

		int mrh = 0;
		/*
		sb = new StringBuilder();
		sb2 = new StringBuilder() ;
		sb2 = kal.KajianPenting(jawa, sb2, mrh);
		sb = kal.AcaraPenting(jawa, sb, mrh);
		*/
		TDateMasehi.setText(
  			" "
			+jawa[0]+", "//+ jawa[4]+"  "
			+jawa[2]+" "
			+jawa[11]+" "
			+jawa[3]+" M\n"
			+jawa[5]+" "
			+jawa[6]+" "
			+jawa[7]+" H");

		//jumat jumat
		Tinfo.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 50);
		Tinfo.setTextColor(Color.WHITE);
	//	StringBuilder khotib = new StringBuilder() ;
		//khotib	= kal.jadwalkhotib(jawa, khotib);
		ifJumat = jawa[0];
		if(ifJumat.equalsIgnoreCase("Jum'at"))
		{
			if(sHour < 12){
				Tinfo.setText(infoKajian);
				Tinfo.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 60);
				Tinfo2.setText(infoKajian2);
				Tinfo2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 60);
			}
		}

		//display garis
		Tnetxpray.setText(
			new StringBuilder()
			.append("JADWAL SHOLAT HARI INI "));	
	//Tnetxpray.setVisibility(LinearLayout.GONE);
	Tnetxpray.setVisibility(LinearLayout.VISIBLE);
	
		TDateMasehi.setTextColor(Color.WHITE);
	if(Integer.parseInt(jawa[3])<2022) {
		TDateMasehi.setText(resetwaktu);
		errorwaktu = 1;
		//this.startActivity(new Intent("android.settings.DATE_SETTINGS"));
	}else{
		errorwaktu = 0;
	}
		
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
		
	//Integer.parseInt() imsak
	String[] subuhaplit =      nShubuh.split(":");
	String nShubuhmnt   = subuhaplit[1].toString();
	
	int number = Integer.parseInt(nShubuhmnt)-10;
	Timsak.setText(subuhaplit[0].toString()+":"+number);
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

/*

//-----------------iqomat-----------
    class MyAdapter2 extends ArrayAdapter<String>
    {
		public MyAdapter2(Context context, String[] values) 
		{
			super(context, R.layout.tgl, values);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			LayoutInflater inflater = LayoutInflater.from(getContext());
			View view = inflater.inflate(R.layout.tgl, parent, false);

			String text = getItem(position);

			TextView textView = (TextView) view.findViewById(R.id.tglview1);
			textView.setText(text);
			LinearLayout layout = (LinearLayout) view.findViewById(R.id.tglLayout);
			//	if ("\n".equals(text))layout.setVisibility(LinearLayout.GONE);
			//	if ("Hari Penting".equals(text))layout.setVisibility(LinearLayout.INVISIBLE);



			return view;
		}
    }
*/

	//-------------time
	class MyTimerTask extends TimerTask 
	{	@Override
		public void run() 
		{
			  //buat random
			Random random = new Random();
			final String randomText = ayat2[random.nextInt(ayat2.length)];

			
			
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
						/*
						if(sSecond==10)layoutbg.setBackgroundResource(R.drawable.a1);
						if(sSecond==20)layoutbg.setBackgroundResource(R.drawable.a2);
						if(sSecond==30)layoutbg.setBackgroundResource(R.drawable.a3);
						if(sSecond==40)layoutbg.setBackgroundResource(R.drawable.a4);
						if(sSecond==50)layoutbg.setBackgroundResource(R.drawable.a5);
						if(sSecond==59)layoutbg.setBackgroundResource(R.drawable.a6);
						*/
						if(sSecond==5)imageRandom();
						if(sSecond==20)imageRandom();
						if(sSecond==35)imageRandom();
						if(sSecond==50)imageRandom();
						
						
						if(adzan.equalsIgnoreCase(nShubuh)) //adzan waktu sekarang
						{
							nextSholat = "Dzuhur "+nDhuhur;
							stssubuh = 1;
							if(sSecond==2){
								audio.playClick();
								shubuhtime(startTime2,1000);
							}
							TTime.setText(sHour+":"+strMinut);
							
						}else
						{
							TTime.setText(sHour+":"+strMinut);
							
						}
						if(adzan.equalsIgnoreCase(nDhuhur)) //adzan waktu sekarang
						{
							nextSholat = "Ashar  "+nAshar;
							shltjmt = 2;
							if(sSecond==1){
								audio.playClick();
								shubuhtime(startTime3,1000);
							}

							TTime.setText(sHour+":"+strMinut);
						}else
						{
							TTime.setText(sHour+":"+strMinut);
						}
						if(adzan.equalsIgnoreCase(nAshar)) //adzan waktu sekarang
						{
							nextSholat = "Maghrib  "+nMagrib;
							if(sSecond==2){
								audio.playClick();
								shubuhtime(startTime3,1000);
							}

							TTime.setText(sHour+":"+strMinut);
						}else
						{
							TTime.setText(sHour+":"+strMinut);
						}
						if(adzan.equalsIgnoreCase(nMagrib))//(adzanmaghrib.equalsIgnoreCase(nMagrib)) //adzan waktu sekarang
						{
							nextSholat = "Isya  "+nIsya;
							if(sSecond==1)shubuhtime(startTime3,1000);
							if(sSecond==2)audio.playClick();
							TTime.setText(sHour+":"+strMinut);
						}else
						{
							TTime.setText(sHour+":"+strMinut);
						}
						if(adzan.equalsIgnoreCase(nIsya)) //adzan waktu sekarang
						{
							nextSholat = "Shubuh "+nShubuh;
							if(sSecond==1){audio.playClick();

								shubuhtime(startTime3,1000);}
							TTime.setText(sHour+":"+strMinut);
						}else	
						{
							TTime.setText(sHour+":"+strMinut);
						}

						//++++++++++++++++++++++++++++


						if(praAdzan.equalsIgnoreCase(nShubuh)) //adzan waktu sekarang
						{
							Tinfo.setText(" PERSIAPAN \n ADZAN SHUBUH");
							nRandom =4;
							TTime.setText(sHour+":"+strMinut);
						}else
						{
							TTime.setText(sHour+":"+strMinut);
						}
						if(praAdzan.equalsIgnoreCase(nDhuhur)) //adzan waktu sekarang
						{
							Tinfo.setText(" PERSIAPAN \n ADZAN DZUHUR");
							nRandom =4;
							Tinfo.setText(sHour+":"+strMinut);
						}else
						{
							TTime.setText(sHour+":"+strMinut);
						}
						if(praAdzan.equalsIgnoreCase(nAshar)) //adzan waktu sekarang
						{
							Tinfo.setText(" PERSIAPAN \n ADZAN ASHAR");
							nRandom =4;
							TTime.setText(sHour+":"+strMinut);
						}else
						{
							TTime.setText(sHour+":"+strMinut);
						}

						if(praAdzan.equalsIgnoreCase(nMagrib)) //adzan waktu sekarang
						{
							Tinfo.setText(" PERSIAPAN ADZAN MAGHRIB");
							nRandom =4;
							TTime.setText(sHour+":"+strMinut);
						}else
						{
							TTime.setText(sHour+":"+strMinut);
						}

						if(praAdzan.equalsIgnoreCase(nIsya)) //adzan waktu sekarang
						{
							Tinfo.setText(" PERSIAPAN \n ADZAN ISYA");
							nRandom =4;
							TTime.setText(sHour+":"+strMinut);
						}else	
						{
							
							
							
							
							TTime.setText(sHour+":"+strMinut);
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

								TTime.setText(sHour+":"+strMinut);
								if(mYear<2022) {
									Tinfo.setText("Habis mati lampu, Waktu blm diset");
									errorwaktu = 1;
								}else{
									errorwaktu = 0;
									TTime.setText(sHour+":"+strMinut);
								}
								
								
							}else
							{
								TTime.setText( sHour+":"+strMinut);
							}
							//random
							if(sSecond==59)setviewtextinfo(randomText, Tinfo, 50, "#000000","#99ffffff");
							
							if(sSecond==35)setviewtextinfo("", Tinfo, 50, "##000000","#00345678");
							
							/*
							if(sSecond ==50){

								//Tinfo.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);

								Tnetxpray.setText(nextSholat);}
							if(sSecond ==25){
								//audio.playClick();
								Tinfo.setText("");
								Tinfo.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 35);
								Tinfo2.setText("");
								Tinfo2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 35);
								
							}
							if(sSecond ==30)
							Tinfo.setText("Matikan atau Silent HP anda");
							Tinfo.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 35);
							Tinfo2.setText("HP anda Bisa menggangu Kekhusuan Sholat");
							Tinfo2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 35);

							if(sSecond ==35)
								Tinfo.setText("");
							Tinfo.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 35);
							Tinfo2.setText("");
							Tinfo2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 35);
							

							if(sSecond ==40)
								//Tinfo.setText(" ISYA \n"+nIsya);
							Tinfo.setText("Luruskan dan Rapatkan Shoff Sholat");
							Tinfo.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 35);
							Tinfo2.setText("Lurus dan rapatnya Shoff bagian dari kesempurnaan Sholat");
							Tinfo2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 35);
							if(sSecond ==45)
								//Tinfo.setText(" SHUBUH \n"+nShubuh);
							Tinfo.setText("");
							Tinfo.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 35);
							Tinfo2.setText("");
							Tinfo2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 35);

							if(sSecond ==15){
								Tinfo.setText(infoKajian);
								Tinfo.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 35);
								Tinfo2.setText(infoKajian2);
								Tinfo2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 35);
								
								Tfootnote.setText(getResources().getString(R.string.kajiansubuh));
								audio.playClick();


							}*/
						};
						//end random

					}
				}
			);
		}

	}



///	-----+
	private void shubuhtime (final long panjang, long akhir)
	{


		new CountDownTimer(panjang, akhir) 
		{

			public void onTick(long millisUntilFinished) 
			{
				//Tinfo.setTextSize(TypedValue.COMPLEX_UNIT_DIP, R.dimen.Textinfo);
				//progresbar view
				long menit = millisUntilFinished / 60000;
				int iMenit = (int) menit;
				progressBarM.setVisibility(LinearLayout.VISIBLE);
				progressBarS.setVisibility(LinearLayout.VISIBLE);
				TprogresS.setVisibility(LinearLayout.VISIBLE);
				TprogresM.setVisibility(LinearLayout.VISIBLE);
				TprogresS.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 23);
				TprogresM.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 23);
				progressBarS.setScaleY(5f);
				progressBarM.setScaleY(5f);
				int menitmax = (int) (panjang/1000)/60;
				progressBarM.setMax(menitmax);
				progressBarM.setProgress(iMenit);
				
				Tinfo.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 60);
				Tinfo2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 80);
				nRandom = 5;
				if(secondtimer < 0)secondtimer =59;
				
				String sSecondT = "00";
				if(sSecond>1)
				{
					sSecondT = menittimer[sSecond];
				}
				progressBarS.setMax(60);
				progressBarS.setProgress(Integer.parseInt(sSecondT));
				
				if(menit==14){

					Tfootnote.setText("Dengarkanlah Adzan");
				}

				if(menit==9)
				{
					if(stssubuh==0)Tfootnote.setText("Dengarkanlah Adzan");
				}
				if(menit>=3)
				{

					Tinfo.setText("TIMER IQOMAT");
					Tinfo2.setText( menit + ":"+sSecondT+"");
					TprogresS.setText(sSecondT+" S");
					TprogresM.setText(menit+" M");
					if(sSecond==20)Tfootnote.setText("Matikan Handphone anda");
					if(sSecond==39)Tfootnote.setText("Waktu untuk Sholat Sunnah masih "+menit+" Menit lagi");
					//	if(nextSholat.equalsIgnoreCase("")&&ifJumat.!equals());
				}




				if(menit == 2)
				{
					Tinfo.setText(" MENUJU IQOMAT");
					Tinfo2.setText( menit + ":"+sSecondT+"");
					TprogresS.setText(sSecondT+" S");
					TprogresM.setText(menit+" M");
					
					if(sSecond == 30)Tfootnote.setText("  MAAF MENDEKATI IQOMAT HARAP UNTUK TIDAK SHOLAT SUNNAH!!!");
					Tinfo.setTextColor(Color.YELLOW);
					Tinfo2.setTextColor(Color.YELLOW);
				}
				if(menit == 1){
					Tinfo.setTextColor(Color.RED);
					Tinfo.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 60);
					Tinfo2.setTextColor(Color.RED);
					Tinfo.setText("PERSIAPAN IQOMAT");
					Tinfo2.setText( menit + ":"+sSecondT+"");
					TprogresS.setText(sSecondT+" S");
					TprogresM.setText(menit+" M");
					
					if(sSecond == 59){
						Tinfo.setText("SAATNYA IQOMAT");
						Tinfo2.setText( menit + ":"+sSecondT+"");
						TprogresS.setText(sSecondT+" S");
						TprogresM.setText(menit+" M");
						
						audio.playClick();
					}
				}
				if(menit <1){
					progressBarM.setVisibility(LinearLayout.INVISIBLE);
					TprogresM.setVisibility(LinearLayout.INVISIBLE);
					if(sSecond > 40){
						Tinfo.setText("SAATNYA IQOMAT");
						Tinfo2.setText( sSecondT+" Detik");
						TprogresS.setText(sSecondT+" S");
						}else{
							Tinfo.setText("SHOFF LURUS DAN RAPAT");}
					Tinfo2.setText( sSecondT+" Detik");
					TprogresS.setText(sSecondT+" S");
				}
				//	timeElapsed = startTime - millisUntilFinished;
				//timeElapsedView.setText("Time Elapsed: " + String.valueOf(timeElapsed));
				if(ifJumat.equalsIgnoreCase("Jum'at"))
				{
					if(sHour>11&&sHour<12)
					{
						Tinfo.setText(" Mendengarkan khutbah ");//millisUntilFinished);
					}else{
						Tinfo.setText("IQOMAT " + menit + " : "+sSecondT+" s");//millisUntilFinished);
					}
				}else{

				}

			}


			public void onFinish()
			{
				progressBarM.setVisibility(LinearLayout.INVISIBLE);
				progressBarS.setVisibility(LinearLayout.INVISIBLE);
				TprogresS.setVisibility(LinearLayout.INVISIBLE);
				TprogresM.setVisibility(LinearLayout.INVISIBLE);
				TprogresS.setVisibility(LinearLayout.INVISIBLE);
				Tinfo.setText("");
				Tinfo2.setText("");
				updateDisplay(mYear, mMonth, mDay);
				Tnetxpray.setText("Waktu Sholat Berikutnya " +nextSholat);
				Tnetxpray.setVisibility(LinearLayout.VISIBLE);
				Tinfo.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
				Tinfo2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
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
			if (position==1)textName.setText("Dhuha");
			if (position==2)textName.setText("Dzuhur");
			if (position==3)textName.setText("Ashar");
			if (position==4)textName.setText("Maghrib");
			if (position==5)textName.setText("Isya");
			if (position==6)textName.setText("Berikutnya");

/*
			LinearLayout layout = (LinearLayout) view.findViewById(R.id.entryLayoutsholat);
			//	if ("\n".equals(text))layout.setVisibility(LinearLayout.GONE);
			//	if ("Hari Penting".equals(text))layout.setVisibility(LinearLayout.INVISIBLE);


			//ImageView imageView = (ImageView) view.findViewById(R.id.entryImageViewsholat);
			//imageView.setImageResource(R.drawable.referensi);
			if ("Hari Penting".equals(text)) imageView.setImageResource(R.drawable.mwidgetbg); //android adalah framwork
			if ("".equals(text))imageView.setVisibility(imageView.INVISIBLE);
			if ("Hari Penting".equals(text))imageView.setVisibility(imageView.INVISIBLE);

*/
			return view;
		}
    }



	/*///++++++	

	@Override
	protected void onDestroy() 
	{
		super.onDestroy();
		//this.prefs.unregisterOnSharedPreferenceChangeListener(this);
	}


    private void sendTextToWidget(Context context) 
	{
		Intent uiIntent2 = new Intent(Constants.ACTION_WIDGET_UPDATE_FROM_ACTIVITY);
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		uiIntent2.putExtra(Constants.INTENT_EXTRA_WIDGET_TEXT,"sample di klik pada " + sdf.format(new Date()));
		context.sendBroadcast(uiIntent2);
    }
/*
    public void startAlarm()
	{
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTimeInMillis(System.currentTimeMillis());
    	calendar.add(Calendar.SECOND, 100000000);
    	//alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 20*10, getPendingIntentForAlarm());
    	final Editor edit = prefs.edit();
		edit.putBoolean(Constants.ALARM_STATUS, true);
		edit.commit();
    }
/*
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

*/


	private void setviewtextinfo (String STtext, TextView Tvinfo, int ukuran, String Textcolor, String bgcolor)
	{
		if(STtext ==""){
			Tvinfo.setVisibility(LinearLayout.INVISIBLE);
			Tinfo2.setVisibility(LinearLayout.INVISIBLE);
		}else{
			Tvinfo.setVisibility(LinearLayout.VISIBLE);
			Tinfo2.setVisibility(LinearLayout.VISIBLE);
		}
		int panjangT = STtext.length();
		Tvinfo.setText(
		//panjangT+
		" " + STtext);
		
		Tvinfo.setTextSize(TypedValue.COMPLEX_UNIT_DIP, ukuran);
		
		try
		{
			if(Textcolor !="")
			//Tvinfo.setTvfeextColor(Co lor.parseColor(Textcolor));
				//Tvinfo.setTextColor(   .WHITE);
			if(bgcolor !="")Tvinfo.setBackgroundColor(com.roaita.imsakiyah.R.color.info_bg);
		
		}catch(Exception e){
			//Handle the Null Pointer Exception here.
			Log.e("YOUR_ACTIVITY_NAME", "Error occurred!, Error = "+e.toString());
		
		}
		
	}

	
	
	@Override
	public void onResume(){
		super.onResume();

	}

	@Override
	public void onPause(){
		super.onPause();

	}

	
	@Override
	public void onBackPressed() {

		super.onBackPressed();
	}

	public void testtimer(View v) {
		String waktusholat = (String) getText(v.getId());
		shubuhtime(startTimetest,1000);
		audio.playClick();
	}
	
	private Bitmap getBitmapFromAssets(String fileName){
		AssetManager am = getAssets();
		InputStream is = null;
		try{
			is = am.open(fileName);
		}catch(IOException e){
			e.printStackTrace();
		}
		Bitmap bitmap = BitmapFactory.decodeStream(is);
		return bitmap;
	}
	public void onClickJadwal(View v) {
		Intent i = null;
		switch(v.getId()){
			case com.roaita.imsakiyah.R.id.date:
				i = new Intent("com.roaita.imsakiyah.activity.AppPreferences");
				break;
			case com.roaita.imsakiyah.R.id.masjid:
				i = new Intent("android.settings.WIFI_SETTINGS");
				break;
			case com.roaita.imsakiyah.R.id.alamat:
				i = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
				break;
			case com.roaita.imsakiyah.R.id.time:
				i = new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
				break;
		}
		startActivity(i);
		
		
		
	}
	
	String[] ayat2 = { 
	
		"Matikan atau Silent HP anda"+
		"\nSuara HP anda Bisa menggangu Kekhusuan Sholat",
		
		"Luruskan dan Rapatkan Shoff Sholat"+
		"Lurus dan rapatnya Shoff bagian dari kesempurnaan Sholat",
		
		"اِيَّا كَ نَعْبُدُ وَاِ يَّا كَ نَسْتَعِيْنُ"+
		"\nHanya kepada Engkaulah kami menyembah dan hanya kepada Engkaulah kami mohon pertolongan.\nQS. Al-Fatihah 1: Ayat 5",
		"  وَقَالَ الرَّسُولُ يَا رَبِّ إِنَّ قَوْمِي اتَّخَذُوا هَذَا الْقُرْآنَ مَهْجُوراً الفرقان  "+
	"\nArtinya Berkatalah Rasul Wahai Rabbku sungguh kaumku telah menjadikan Al Quran ini Mahjura (sesuatu yang diabaikan)",
		"RasuIuIIah saw bersabda:\n وقال صلى الله عليه وسلم: {صَلاَةُ الجَمَاعَةِ تَفْضُلُ صَلاَة الفَذِّ بِسَبْعٍ وعِشْرِينَ دَرَجَةً}. \nArtinya: ShaIat berjamaah Iebih utama daripada shaIat sendirian dengan seIisih 27 derajat. (HR. aI-Bukhari).",
		"إِنَّمَا يَعۡمُرُ مَسَـٰجِدَ ٱللَّهِ مَنۡ ءَامَنَ بِٱللَّهِ وَٱلۡيَوۡمِ ٱلۡأَخِرِ وَأَقَامَ ٱلصَّلَوٰةَ وَءَاتَى ٱلزَّڪَوٰةَ وَلَمۡ يَخۡشَ إِلَّا ٱللَّهَ‌ۖ فَعَسَىٰٓ أُوْلَـٰٓٮِٕكَ أَن يَكُونُواْ مِنَ ٱلۡمُهۡتَدِينَ \nSesungguhnya yang memakmurkan masjid-masjid Allah ialah orang yang beriman kepada Allah dan hari kemudian, serta tetap mendirikan shalat, menunaikan zakat dan tidak takut (kepada  siapapun) kecuali kepada Allah, maka semoga mereka menjadi golongan yang mendapatkan petunjuk”[QS at-Taubah ayat 18]",
	
	"MARHABAN YA RAMADHAN"+
	"\nMARI MERAIH TAKWA DI BULAN RAMADHAN 1443H",
	"Jika anda mendapati kekeliruan imam dalam Sholat, maka ingatkanlah dengan mengucapkan \nSubhanAllah bagi laki-laki dan tepuk tangan bagi perempuan",

		};
		
		
	int[] images = {
		com.roaita.imsakiyah.R.drawable.a1,
		com.roaita.imsakiyah.R.drawable.a2,
		com.roaita.imsakiyah.R.drawable.a3,
		com.roaita.imsakiyah.R.drawable.a4,
	com.roaita.imsakiyah.R.drawable.a5,
	com.roaita.imsakiyah.R.drawable.a6,
	com.roaita.imsakiyah.R.drawable.a7,
	com.roaita.imsakiyah.R.drawable.a8,
		com.roaita.imsakiyah.R.drawable.a9,
		com.roaita.imsakiyah.R.drawable.a10,
		R.drawable.a4
		};
		
	public void imageRandom() {
		Random imgrandom = new Random();
		layoutbg.setBackgroundResource(images[imgrandom.nextInt(images.length)]);
		
	}
	
}

	

		 
		 
		 
	






