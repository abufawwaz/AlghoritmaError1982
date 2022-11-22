package com.roaita.imsakiyah.activity;

import android.annotation.SuppressLint;
import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.media.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.util.*;

import android.icu.util.Calendar;
import android.icu.text.*;
import android.util.*;
import android.graphics.*;

import com.roaita.imsakiyah.R;
import com.roaita.imsakiyah.algoritma.*;
import com.roaita.imsakiyah.util.Audio;

import android.provider.*;

public class MainActivity extends Activity 
{
	private LinearLayout cardinfo, cardinfo2, cardinfo3,cardinfo4,pengurus,kajian1,kajian2;
	int statusviewcard =0; //timer run 1, timer of 0
	private int focusinfo =0;
	public void openreferens(View v) {
		//menamp8lkan change keyboard
		//	this.startActivity(new Intent("com.myfawwaz.simplekeyboard.softkeyboard.ImePreferences"));
		this.startActivity(new Intent("AppPreferences"));
		//andorid.Settings.DatePickerDialog

	}
	
	public void testtimer(View v) {
		String waktusholat = (String) getText(v.getId());
		shubuhtime(startTimetest,1000);
		nextSholat = "Dzuhur "+nDhuhur;
		audio.playClick();
	}

	public String getKajian (Calendar tgl,String Pasaran,String Hari,String Kajian){
		String Info = "";
		String infoKajianHariIni = "";
		String infoKhutbahHariIni = "";
		String infoKultumHariIni = "";
		String ImamMuadzin = "";

		if("Jumat"==Hari) {
			if(Pasaran=="Legi")infoKhutbahHariIni = "H. KHABIB MAUFUR,HASAN ABDUROUF,SHOBIRIN,MUBAROQ";
			if(Pasaran=="Pahing")infoKhutbahHariIni ="QOSDAN DAWAMI LC, FIKRIYAN FAJAR A,MUSLIMIN, MUBAROQ";
			if(Pasaran=="Kliwon")infoKhutbahHariIni = "H. MUSBIKHUN M , BASUKI YULIANTO,YULI FARIDIN ,MUBAROQ";
			if(Pasaran=="pon")infoKhutbahHariIni = "H. KHOZIM,NUR ROFIQ,MUJTAHIDIN,YULI FARIDIN";
			if(Pasaran=="Wage")infoKhutbahHariIni = "MI'ROJ MUSTAJIB,MUSLIMAN LILLAH ,ROZIN , MUBAROQ";
			infoKultumHariIni = "KOSDAN DAWAMI LC,TEGAR MAULANA ASKAFF A, MI'ROJ MUSTAJIB";
		}
		if("Ahad"==Hari) {
			infoKultumHariIni = "";
		}
		if("Senin"==Hari) {
			infoKultumHariIni = "NUR ROFIQ, NURUL MILAL FIKRI";
		}
		if("Selasa"==Hari) {
			infoKultumHariIni = "H. MUHTARUDIN ABAS ,FIKRIYAN FAJAR AL FAROBI";
		}
		if("Rabu"==Hari) {
			infoKultumHariIni = "H. KHOZIM ,MUHAMMAD ABDUL JAWAD";
		}
		if("Kamis"==Hari) {
			infoKultumHariIni = "H. MUSBIHUN MUNAWAR";
		}
		if("Sabtu"==Hari) {
			infoKultumHariIni = "MUHAMMAD ABID SOBRI";
		}

/*
SHUBUH	MI’ROJ MUSTAJIB
(IMAM BADAL)	YULI FARADIN
ZHUHUR	MUHAIMIN
(IMAM BADAL)	MUBAROQ
ASHAR	QOSDAN DAWAMI,LC.
(IMAM BADAL)	MUSLIMIN
MAGHRIB	H.MUSBIKHUN MUNAWAR
(IMAM BADAL)	MUJTAHIDIN
‘ISYA	NUR ROFIQ
(IMAM BADAL)
MUSLIMAN LILLAH
(IMAM BADAL)	MUBAROQ


 */
		return infoKajianHariIni;
	}

	private boolean isDirectToTV() {
		return(getPackageManager().hasSystemFeature(PackageManager.FEATURE_TELEVISION)
			|| getPackageManager().hasSystemFeature(PackageManager.FEATURE_LEANBACK));
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	//	boolean tvorhp = isDirectToTV();
     //  setContentView(R.layout.layouthp_vertical);
		setContentView(R.layout.jadwal_main);
		//setContentView(R.layout.layout_hp);

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		 mPlayer = MediaPlayer.create(this, R.raw.alert);
		// mPlayer.start();
		//time
		timer = new Timer();
		myTimerTask = new MyTimerTask();
		/*
		 this.prefs = PreferenceManager.getDefaultSharedPreferences(this);
		 this.prefs.registerOnSharedPreferenceChangeListener(this);
		 this.alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
		 */
		cardinfo = (LinearLayout) findViewById(R.id.cardinfo);
		cardinfo2 = (LinearLayout) findViewById(R.id.cardinfo2);
		cardinfo3 = (LinearLayout) findViewById(R.id.cardinfo3);
		cardinfo4 = (LinearLayout) findViewById(R.id.cardinfo4);
		pengurus = (LinearLayout) findViewById(R.id.cardinfo4);
		kajian1 = (LinearLayout) findViewById(R.id.cardinfo5);
		kajian2 = (LinearLayout) findViewById(R.id.cardinfo6);

		cardinfo.setFocusableInTouchMode(true);
		cardinfo2.setFocusableInTouchMode(true);
		cardinfo3.setFocusableInTouchMode(true);
		cardinfo4.setFocusableInTouchMode(true);
		pengurus.setFocusableInTouchMode(true);
		kajian1.setFocusableInTouchMode(true);
		kajian2.setFocusableInTouchMode(true);
		cardinfo2.requestFocus();


		layoutbg =(LinearLayout)findViewById(R.id.layout_bg);

        TMasjid = (TextView) this.findViewById(R.id.masjid);

        TDateMasehi = (TextView) findViewById(R.id.date);
		TDateMasehi.setTextColor(Color.WHITE);
		TTime = (TextView) findViewById(R.id.time);
		TTime.setTextColor(Color.WHITE);

		Tinfo = (TextView) findViewById(R.id.vinfo);
		Tinfo2 = (TextView) findViewById(R.id.vinfo2);
		TinfoIqomat = (TextView) findViewById(R.id.iqomattimerinfo);

		TinfoIqomat2 = (TextView) findViewById(R.id.iqomattimerinfo2);
		Tnetxpray = (Button) findViewById(R.id.nextpray);

		Tsubuh = (TextView) this.findViewById(R.id.subuh);
		Tterbit = (TextView) findViewById(R.id.terbit);
		Tdzuhur = (TextView) findViewById(R.id.dzuhur);
		Tashar = (TextView) findViewById(R.id.ashar);
		Tmaghrib = (TextView) findViewById(R.id.maghrib);
		Tisya = (TextView) findViewById(R.id.isya);
		Timsak = (TextView) findViewById(R.id.imsak);
		Tfootnote= (TextView) findViewById(R.id.footnote);

		TprogresS =(TextView) findViewById(R.id.textprogres1);
		TprogresM =(TextView) findViewById(R.id.textprogres2);
		progressBarM = (ProgressBar) findViewById(R.id.progressBar);
		progressBarM.setVisibility(LinearLayout.INVISIBLE);
		progressBarS = (ProgressBar) findViewById(R.id.progressBarS);
		progressBarS.setVisibility(LinearLayout.GONE);
		TprogresS.setVisibility(LinearLayout.GONE);
		TprogresM.setVisibility(LinearLayout.GONE);
		Tfootnote.setSelected(true);  // Set focus to the textview to running text
		infoKajian = getResources().getString(R.string.kajiansubuh);
		infoKajian2 = getResources().getString(R.string.kajiansubuh2);
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
	
	//hitung mundur
	
	private void shubuhtime(final long panjang, long akhir)
	{
		new CountDownTimer(panjang, akhir) 
		{
			public void onTick(long millisUntilFinished) 
			{
				//Tinfo.setTextSize(TypedValue.COMPLEX_UNIT_DIP, R.dimen.Textinfo);
				//progresbar view
				cardinfo3.requestFocus();
				statusviewcard = 1;
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

				TinfoIqomat.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 50);
				TinfoIqomat2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 60);
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

					TinfoIqomat.setText("TIMER IQOMAT");
					TinfoIqomat2.setText( menit + ":"+sSecondT+"");
					TprogresS.setText(sSecondT+" detik");
					TprogresM.setText(menit+" menit");
					if(sSecond==20)Tfootnote.setText("Matikan Handphone anda");
					if(sSecond==39)Tfootnote.setText("Waktu untuk Sholat Sunnah masih "+menit+" Menit lagi");
					//	if(nextSholat.equalsIgnoreCase("")&&ifJumat.!equals());
				}




				if(menit == 2)
				{
					TinfoIqomat.setText(" WAKTU MENUJU IQOMAT");
					TinfoIqomat2.setText( menit + ":"+sSecondT+"");
					TprogresS.setText(sSecondT+" DETIK");
					TprogresM.setText(menit+" MENIT");

					if(sSecond == 30)Tfootnote.setText("  MAAF MENDEKATI IQOMAT HARAP UNTUK TIDAK SHOLAT SUNNAH!!!");
					TinfoIqomat.setTextColor(Color.YELLOW);
					TinfoIqomat2.setTextColor(Color.YELLOW);
				}
				if(menit == 1){
					TinfoIqomat.setTextColor(Color.RED);
					TinfoIqomat.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 60);
					TinfoIqomat2.setTextColor(Color.RED);
					TinfoIqomat.setText("PERSIAPAN IQOMAT");
					TinfoIqomat2.setText( menit + ":"+sSecondT+"");
					TprogresS.setText(sSecondT+" detik");
					TprogresM.setText(menit+" menit");

					if(sSecond == 59){
						TinfoIqomat.setText("SAATNYA IQOMAT");
						TinfoIqomat2.setText( menit + ":"+sSecondT+"");
						TprogresS.setText(sSecondT+" detik");
						TprogresM.setText(menit+" menit");

						audio.playClick();
					}
				}
				if(menit <1){
					progressBarM.setVisibility(LinearLayout.INVISIBLE);
					TprogresM.setVisibility(LinearLayout.INVISIBLE);
					if(sSecond > 40){
						TinfoIqomat.setText("SAATNYA IQOMAT");
						TinfoIqomat2.setText( sSecondT+" Detik");
						TprogresS.setText(sSecondT+" detik");
					}else{
						TinfoIqomat.setText("SHOFF LURUS DAN RAPAT");}
					TinfoIqomat2.setText( sSecondT+" Detik");
					TprogresS.setText(sSecondT+" detik");
				}
				//	timeElapsed = startTime - millisUntilFinished;
				//timeElapsedView.setText("Time Elapsed: " + String.valueOf(timeElapsed));
				if(ifJumat.equalsIgnoreCase("Jum'at"))
				{
					if(sHour>11&&sHour<13)
					{
						TinfoIqomat.setText(" Mendengarkan khutbah ");//millisUntilFinished);
					}else{
						TinfoIqomat.setText("IQOMAT " + menit + " : "+sSecondT+" detik");//millisUntilFinished);
					}
				}else{

				}

			}


			public void onFinish()
			{
				cardinfo2.requestFocus();
				statusviewcard =0;
				progressBarM.setVisibility(LinearLayout.INVISIBLE);
				progressBarS.setVisibility(LinearLayout.INVISIBLE);
				TprogresS.setVisibility(LinearLayout.INVISIBLE);
				TprogresM.setVisibility(LinearLayout.INVISIBLE);
				TprogresS.setVisibility(LinearLayout.INVISIBLE);
				TinfoIqomat.setText("");
				TinfoIqomat2.setText("");
				updateDisplay(mYear, mMonth, mDay);
				Tnetxpray.setText("Sholat " +nextSholat);
				Tnetxpray.setVisibility(LinearLayout.VISIBLE);
				TinfoIqomat.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
				TinfoIqomat2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
				nRandom =1;
				shltjmt = 1;
				stssubuh=0;
			}
		}.start();

	}
	//tampilkan info
	public void randominfo(){
		int angkafokus;
		Random intramdom = new Random();
		angkafokus = intramdom.nextInt(randomangka.length);
		if(statusviewcard==0){
		if(angkafokus==0) cardinfo.requestFocus();
		if(angkafokus==1) cardinfo2.requestFocus(); //kosong card3timer
		if(angkafokus==3) pengurus.requestFocus();
		if(angkafokus==4) cardinfo4.requestFocus();
		if(angkafokus==5) kajian2.requestFocus();
		if(angkafokus==6) kajian1.requestFocus();


		}

	}
	//runtime
	
	class MyTimerTask extends TimerTask 
	{	@Override
		public void run() 
		{
			//buat random
			Random random = new Random();
			final String randomText = ayat2[random.nextInt(ayat2.length)];
			Random randomkas = new Random();
			final String randomTextKas = keuangan[random.nextInt(keuangan.length)];


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
			THour =""+sHour;
			if (sHour < 10) THour = "0" + sHour;

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
					
						if(sSecond==5)imageRandom();
						if(sSecond==20){
							imageRandom();
							randominfo();
						}
						if(sSecond==35)imageRandom();
						if(sSecond==50) {
							imageRandom();
							randominfo();
						}


						if(adzan.equalsIgnoreCase(nShubuh)) //adzan waktu sekarang
						{
							nextSholat = "Dzuhur "+nDhuhur;
							stssubuh = 1;
							if(sSecond==2){
								audio.playClick();
								shubuhtime(startTime2,1000);

							}
							TTime.setText(THour+":"+strMinut);

						}else
						{
							TTime.setText(THour+":"+strMinut);

						}
						if(adzan.equalsIgnoreCase(nDhuhur)) //adzan waktu sekarang
						{
							nextSholat = "Ashar  "+nAshar;
							shltjmt = 2;
							if(sSecond==1){
								audio.playClick();
								shubuhtime(startTime3,1000);
							}

							TTime.setText(THour+":"+strMinut);
						}else
						{
							TTime.setText(THour+":"+strMinut);
						}
						if(adzan.equalsIgnoreCase(nAshar)) //adzan waktu sekarang
						{
							nextSholat = "Maghrib  "+nMagrib;
							if(sSecond==2){
								audio.playClick();
								shubuhtime(startTime3,1000);
							}

							TTime.setText(THour+":"+strMinut);
						}else
						{
							TTime.setText(THour+":"+strMinut);
						}
						if(adzan.equalsIgnoreCase(nMagrib))//(adzanmaghrib.equalsIgnoreCase(nMagrib)) //adzan waktu sekarang
						{
							nextSholat = "Isya  "+nIsya;
							if(sSecond==1)shubuhtime(startTime3,1000);
							if(sSecond==2)audio.playClick();
							TTime.setText(THour+":"+strMinut);
						}else
						{
							TTime.setText(THour+":"+strMinut);
						}
						if(adzan.equalsIgnoreCase(nIsya)) //adzan waktu sekarang
						{
							nextSholat = "Shubuh "+nShubuh;
							if(sSecond==1){audio.playClick();

								shubuhtime(startTime3,1000);}
							TTime.setText(THour+":"+strMinut);
						}else	
						{
							TTime.setText(THour+":"+strMinut);
						}

						//++++++++++++++++++++++++++++


						if(praAdzan.equalsIgnoreCase(nShubuh)) //adzan waktu sekarang
						{
							Tinfo.setText(" PERSIAPAN \n ADZAN SHUBUH");
							nRandom =4;
							TTime.setText(THour+":"+strMinut);
						}else
						{
							TTime.setText(THour+":"+strMinut);
						}
						if(praAdzan.equalsIgnoreCase(nDhuhur)) //adzan waktu sekarang
						{
							Tinfo.setText(" PERSIAPAN \n ADZAN DZUHUR");
							nRandom =4;
							Tinfo.setText(THour+":"+strMinut);
						}else
						{
							TTime.setText(THour+":"+strMinut);
						}
						if(praAdzan.equalsIgnoreCase(nAshar)) //adzan waktu sekarang
						{
							Tinfo.setText(" PERSIAPAN \n ADZAN ASHAR");
							nRandom =4;
							TTime.setText(THour+":"+strMinut);
						}else
						{
							TTime.setText(THour+":"+strMinut);
						}

						if(praAdzan.equalsIgnoreCase(nMagrib)) //adzan waktu sekarang
						{
							Tinfo.setText(" PERSIAPAN ADZAN MAGHRIB");
							nRandom =4;
							TTime.setText(THour+":"+strMinut);
						}else
						{
							TTime.setText(THour+":"+strMinut);
						}

						if(praAdzan.equalsIgnoreCase(nIsya)) //adzan waktu sekarang
						{
							Tinfo.setText(" PERSIAPAN \n ADZAN ISYA");
							nRandom =4;
							TTime.setText(THour+":"+strMinut);
						}else	
						{
TTime.setText(THour+":"+strMinut);
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

								TTime.setText(THour+":"+strMinut);
								if(mYear<2022) {
									Tinfo.setText("Habis mati lampu, Waktu blm diset");
									errorwaktu = 1;
								}else{
									errorwaktu = 0;
									TTime.setText(THour+":"+strMinut);
								}


							}else
							{
								TTime.setText( THour+":"+strMinut);
							}
							//random
							if(sSecond==59)setviewtextinfo(randomText, Tinfo, 30, "#000000","#99ffffff");
							if(sSecond==35)setviewtextinfo("", Tinfo, 30, "##000000","#00345678");

						};
						//end random

					}
				}
			);
		}

	}
	
	@SuppressLint("ResourceAsColor")
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
				if(bgcolor !="")Tvinfo.setBackgroundColor(R.color.info_bg);

		}catch(Exception e){
			//Handle the Null Pointer Exception here.
			Log.e("YOUR_ACTIVITY_NAME", "Error occurred!, Error = "+e.toString());

		}

	}
	
	// updates the date in the TextView
    private void updateDisplay(int y, int m, int d) 

	{
		String tglskrg = ""+d+"-"+(m+1)+"-"+y;
    	String[] getWaktuSholat = algowkt.WaktuSholat(tglskrg,"-7'21'55","109'51'54","All",8);
		shltjmt = 1;
		nRandom = 1;
		String infoSholat = "";
		StringBuilder sbSholat = new StringBuilder() ;
    	String[] jawa = kal.MasehiToJawa(y, m, d);// (mDay, mMonth, mYear);
		//tanggal masehi

		int mrh = 0;
		
		TDateMasehi.setText(
  			//tglskrg+" "
			jawa[0]+"  "+ jawa[4]+"  \n"

			+jawa[5]+" "
			+jawa[6]+" "
			+jawa[7]+" \n"
					+jawa[2]+" "
					+jawa[11]+" "
					+jawa[3]+" "
		);

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

		
		//set string sholat
		nImsak    	= getWaktuSholat[0].toString();
		nShubuh   	= getWaktuSholat[1].toString();
		nTerbit   	= getWaktuSholat[2].toString();
		nDhuhur   	= getWaktuSholat[3].toString();
		nAshar    	= getWaktuSholat[4].toString();
		nMagrib  	= getWaktuSholat[5].toString();
		nIsya     	= getWaktuSholat[6].toString();

		//Integer.parseInt() imsak
		String[] subuhaplit =      nShubuh.split(":");
		String nShubuhmnt   = subuhaplit[1].toString();

		int number = Integer.parseInt(nShubuhmnt)-10;
		Timsak.setText(nImsak);
		Tsubuh.setText(nShubuh);
		Tterbit.setText("0"+nTerbit);
		Tdzuhur.setText(nDhuhur);
		Tashar.setText(nAshar);
		Tmaghrib.setText(nMagrib);
		Tisya.setText(nIsya);
		audio = new Audio(this.getApplicationContext());
		//audio.playClick();

	}//end update
	
	
	//rundom class
	int[] randomangka = {0,1,2,3,4,5,6};
	String[] Kajian = {
			""
	};
	String[] keuangan = {
			"06/10/2022,2000000,5000000","06/11/2022,2000000,600000","06/12/2022,2000000,700000","06/10/2022,300000,8000000","06/10/2022,3000000,900000"
	};

			String[] ayat2 = {

		"Matikan atau Silent HP anda"+
		"\nSuara HP anda Bisa menggangu Kekhusuan Sholat",

		"Luruskan dan Rapatkan Shoff Sholat"+
		"Lurus dan rapatnya Shoff bagian dari kesempurnaan Sholat",

		"اِيَّا كَ نَعْبُدُ وَاِ يَّا كَ نَسْتَعِيْنُ"+
		"\nHanya kepada Engkaulah kami menyembah dan hanya kepada Engkaulah kami mohon pertolongan.\nQS. Al-Fatihah 1: Ayat 5",
		"  وَقَالَ الرَّسُولُ يَا رَبِّ إِنَّ قَوْمِي اتَّخَذُوا هَذَا الْقُرْآنَ مَهْجُوراً الفرقان  "+
		"\nArtinya Berkatalah Rasul Wahai Rabbku sungguh kaumku telah menjadikan Al Quran ini Mahjura (sesuatu yang diabaikan)",
		"Rasulullah saw bersabda:\n وقال صلى الله عليه وسلم: {صَلاَةُ الجَمَاعَةِ تَفْضُلُ صَلاَة الفَذِّ بِسَبْعٍ وعِشْرِينَ دَرَجَةً}. \nArtinya: ShaIat berjamaah Iebih utama daripada shaIat sendirian dengan seIisih 27 derajat. (HR. aI-Bukhari).",
		"إِنَّمَا يَعۡمُرُ مَسَـٰجِدَ ٱللَّهِ مَنۡ ءَامَنَ بِٱللَّهِ وَٱلۡيَوۡمِ ٱلۡأَخِرِ وَأَقَامَ ٱلصَّلَوٰةَ وَءَاتَى ٱلزَّڪَوٰةَ وَلَمۡ يَخۡشَ إِلَّا ٱللَّهَ‌ۖ فَعَسَىٰٓ أُوْلَـٰٓٮِٕكَ أَن يَكُونُواْ مِنَ ٱلۡمُهۡتَدِينَ \nSesungguhnya yang memakmurkan masjid-masjid Allah ialah orang yang beriman kepada Allah dan hari kemudian, serta tetap mendirikan shalat, menunaikan zakat dan tidak takut (kepada  siapapun) kecuali kepada Allah, maka semoga mereka menjadi golongan yang mendapatkan petunjuk”[QS at-Taubah ayat 18]",

		"Jangan menganggap shalat sebagai beban. Karena Allah jutru menjadikan salat bagi kita untuk meringankan beban.",
		"Yang membedakan antara orang beriman dengan tidak beriman adalah meninggalkan salat.",
		"Seorang sahabat pernah bertanya kepada Rasulullah SAW, 'Amalan apa yang paling baik di sisi Allah wahai Rasulullah?' Beliau menjawab, 'Ialah salat tepat pada waktunya'.",
		
		"Jadikanlah sabar dan salat sebagai penolongmu.",
		"Jika anda mendapati kekeliruan imam dalam Sholat, maka ingatkanlah dengan mengucapkan \nSubhanAllah bagi laki-laki dan tepuk tangan bagi perempuan",

	};


	int[] images = {
		R.drawable.a1,
		R.drawable.a2,
		R.drawable.a3,
		R.drawable.a4,
		R.drawable.a5,
		R.drawable.a6,
		R.drawable.a7,
		R.drawable.a8,
		R.drawable.a9,
		R.drawable.a10,
		R.drawable.a4
	};

	public void imageRandom() {
		Random imgrandom = new Random();
		layoutbg.setBackgroundResource(images[imgrandom.nextInt(images.length)]);

	}
	
	
	public void onClickJadwal(View v) {
		Intent i = null;
		switch(v.getId()){
			case R.id.date:
				i = new Intent(MainActivity.this, AppPreferences.class);

				//MainActivity.this.startActivity(localIntent);
				
				//i = new Intent("AppPreferences");
				break;
			case R.id.masjid:
				i = new Intent("android.settings.WIFI_SETTINGS");
				break;
			case R.id.alamat:
				i = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
				break;
			case R.id.time:
				i = new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
				break;
		}
		startActivity(i);

		

	}
	
	
	//private
	Toast mToast;
	private static MediaPlayer mediaPlayer;
	private MediaPlayer mPlayer;
	private	 Audio audio; //= new Audio(this.getApplicationContext());

	private Context context;
	private MediaPlayer mp;  
	private AlarmManager alarmManager;
	private SharedPreferences prefs;


	private TextView TDateMasehi;
	private TextView TTime;
	private TextView TMasjid;
	private TextView Tinfo, Tinfo2, TinfoIqomat,TinfoIqomat2;
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
	private String nImsak;
	private String nShubuh;
	private String nTerbit;
	private String nextSholat;
	private String resetwaktu ="mati listrik waktu tanggal \nbelum diset";
	private int    errorwaktu =0;
	private String nMaghribMnt;
	private String nMaghribJam;
	private String THour ="";
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

	private long startTime2 = 1000*60*16;
	private long startTime3 = 1000*60*12;
	private long startTimetest = 1000*60*10;
	private boolean test = false;

	private final long interval2 = 1000;
	private long secondtimer = 0;
	Timer timer;
	MyTimerTask myTimerTask;
    private Calendar c;
	static final int DATE_DIALOG_ID = 0;
	final Kalender kal = new Kalender();
	final WaktuSholat_Tinggi algowkt = new WaktuSholat_Tinggi();
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
}
