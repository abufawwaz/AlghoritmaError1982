package com.roaita.imsakiyah.activity;

import java.util.Calendar;
import java.util.Date;

public class Kalender
{


	//utility function

    private double intPart(double floatNum){
            if ((float)floatNum < -0.0000001){
            	return (double) Math.ceil(floatNum-0.0000001);
            }
            return (double)Math.floor(floatNum +0.0000001);    
    }
    
    private static int JGREG= 15 + 31*(10+12*1582);
    //private static double HALFSECOND = 0.5;


    private static double toJulian(int y, int m, int d) {
	     int year=y;
	     int month=m; // jan=1, feb=2,...
	     int day=d;
	     int julianYear = year;
	     if (year < 0) julianYear++;
	     int julianMonth = month;
	     if (month > 2) {
	       julianMonth++;
	     }
	     else {
	       julianYear--;
	       julianMonth += 13;
	     }
	
	     double julian = (java.lang.Math.floor(365.25 * julianYear)
	          + java.lang.Math.floor(30.6001*julianMonth) + day + 1720995.0);
	     if (day + 31 * (month + 12 * year) >= JGREG) {
	       // change over to Gregorian calendar
	       int ja = (int)(0.01 * julianYear);
	       julian += 2 - ja + (0.25 * ja);
	     }
	     return java.lang.Math.floor(julian);
   }

    String[] MasehiToJawa(int year, int month, int day) {
    	double julian = toJulian(year, month, day);
    	double d=day; 
    	double m=month; 
    	double y=year;
    	int mYear;
    	int mMonth;
    	int mDay;
        int sDay;
        int Nmonth;
		String[] tgl0 = { 
			        "00",  "01","02","03","04","05","06","07","08","09",
			"10","11","12","13","14","15","16","17","18","19",
			"20","21","22","23","24","25","26","27","28","29",
			"30","31",
			};
    	String[] bulanjawa = {"Sura","Sapar","Mulud","Bakdamulud","Jumadilawal","Jumadilakhir",
    			"Rejeb","Ruwah","Pasa","Sawal","Dulkaidah","Besar"};
				
    	String[] bulanhijriyah = {"Muharram","Shafar","Rabi’ al-Awwal","Rabi’ al-Tsani","Jumada al-Ula","Jumada al-Tsaniyah",
			"Rajab","Sya’ban","Ramadhan","Syawwal","Dzul-Qa’dah","Dzul-Hijjah"};
			
    	String[] bulanhijriyaharab = {"مُحَرَّم","صَفَر","رَبِيْعُ الأَوَّل","رَبِيعُ الأٰخِر","جَمَادِى الأَوَّل","جَمَادِى الثاني",
			"رَجَب","شَعْبَان","رَمَضَان","شَوَّال","ذُوْالقَاعِدَة","ذُوْالحِجَّة"};
    	
    	String[] bulanmasehi = new String[]{
        	"Januari", "Februari", "Maret", "April","Mei","Juni",
        	"Juli","Agustus","September","Oktober","November","Desember"
        };
		
		String[] BlnMasehi = new String[]{
        	"Jan", "Feb", "Mar", "Apr","Mei","Jun",
        	"Jul","Agt","Sept","Okt","Nov","Des"
        };
		
    	
        String[] harimasehi = new String[]{
        	"Minggu","Senin", "Selasa","Rabu","Kamis","Jum'at","Sabtu"
        };
        
    	Calendar c = Calendar.getInstance();
    	c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        sDay = c.get(Calendar.DAY_OF_WEEK);
        
        c.set(Calendar.DAY_OF_MONTH, 1);
        int startDayofMonth = c.get(Calendar.DAY_OF_WEEK);
        
  	  	if(julian>=1937808 && julian<=536838867) {
 	
  	  		double mPart = (m-13)/12;
  	  		double jd = intPart((1461*(y+4800+intPart(mPart)))/4)+
  	          	intPart((367*(m-1-12*(intPart(mPart))))/12)-
  	          	intPart((3*(intPart((y+4900+intPart(mPart))/100)))/4)+d-32075;
  	          
  	  		double l = jd-1948440+10632;
  	  		double n = intPart((l-1)/10631);
  	  		l = l-10631*n+354;
  	  		double j = (intPart((10985-l)/5316))*(intPart((50*l)/17719))+(intPart(l/5670))*(intPart((43*l)/15238));
  	  		l = l-(intPart((30-j)/15))*(intPart((17719*j)/50))-(intPart(j/16))*(intPart((15238*j)/43))+29;
  	  		
  	  		m = (double)intPart((24*l)/709);
  	  		d = (double)l-intPart((709*m)/24);
  	  		y = (double)30*n+j-30;
	  	//	dwuku = wuku[(int)d];
  	  		/*
  	  		 * 
  	  		untuk menghitung tahun jawa Be, alip, ehe dsb...
  	  		double yj = y;//+512; Tahun jawa = Tahun Hijriyah + 512
  	  		double i = yj;
  	  		double yn=0.;
  	  		if (i >= 8) {
  	  			while (i > 7){
  	  					i = i - 8;
  	  					yn = i;
  	  			}
  	  		} else {
  	  			yn = i;
  	  		}
  	  		*/
  	  		
  	  		if(julian<=1948439) y--;
  	  	}
		//int tglMnol = String.valueOf(mDay);
		int tglHnol = (int)d;
  	  	return new String[] {
  	  			harimasehi[sDay-1], 					//hari Masehi	0
  	  			bulanmasehi[mMonth], 					//Bulan Masehi	1
  	  			tgl0[mDay], 								//Tgl masehi	2
  	  			String.valueOf(mYear), 					//Thn masehi	3
  	  			HariPasaran(mYear, mMonth, mDay), 		//nama pasaran	4
	  			tgl0[tglHnol],									//Tanggal Jawa 	5
	  			bulanhijriyah[(int)m-1], 				//Bulan hijriyah6
  	  			String.valueOf((int)y), 				//Tahun Jawa	7
			   	String.valueOf((int)startDayofMonth),	//Awal hari 	8
	          	bulanjawa[(int)m-1],                    //bulan jawa    9
			    String.valueOf(mMonth+1),              //nomer bulan   10
				bulanmasehi[mMonth],						//bulan singkat 11
			//	bulanhijriyah[(int)m], 					//hijri no -	12
			//	bulanhijriyah[(int)m+1], 				//hijri +1      13
				             };
    }
    
    String HariPasaran(int year, int month, int day){
    	String[] pasaran = new String[]{
    			"Pahing", "Pon", "Wage","Kliwon","Legi"
    	};
    	
    	Calendar tglInit = Calendar.getInstance();
    	tglInit.set(1900, 12, 1);
    	Calendar tglDicari = Calendar.getInstance();
    	tglDicari.set(year, month, day);
    	
    	long miliday = 24 * 60 * 60 * 1000;

    	long tglDicariMilis = tglDicari.getTimeInMillis();
    	long tglInitMilis = tglInit.getTimeInMillis();
    	long selisih =  (tglDicariMilis-tglInitMilis)/miliday;
    	long hasil = selisih % 5;
    	return String.valueOf(pasaran[(int)hasil]);
    }
   
					 
	StringBuilder AcaraPenting(String[] kal, StringBuilder sb, int mrh)
	{

		//hijriyah
		if(kal[5].equals("01") )
		{
			if(kal[6].equalsIgnoreCase("Sawal"))
				sb.append("\n" +kal[2]+" "+kal[1]+" : Hari Raya Idhul Fitri");
			if(kal[6].equalsIgnoreCase("Romadlon") )
				sb.append("\n" +kal[2]+" "+kal[1]+" : 1 Romadlon (Munggah)");
			if(kal[6].equalsIgnoreCase("Muharram") )
				sb.append("\n" +kal[2]+" "+kal[1]+" : Tahun Baru Islam");
			sb.append("\n" +kal[2]+" "+kal[1]+" : Awal Bulan "+kal[6]+" "+kal[7]+"H");
		}
		
		
    	if(kal[6].equalsIgnoreCase("Rajab") && kal[5].equals("27") )
    		sb.append("\n" +kal[2]+" "+kal[1]+" : Israa' Mi'raj");

    	if(kal[6].equalsIgnoreCase("RabiulAwal") && kal[5].equals("12") )
    		sb.append("\n" +kal[2]+" "+kal[1]+" : Kelahiran Nabi Muhammad SAW");

    	if(kal[9].equalsIgnoreCase("Pasa") && kal[5].equals("17") )
    		sb.append("\n" +kal[2]+" "+kal[1]+" : Nuzulul Qur'an");

    	if(kal[9].equalsIgnoreCase("Pasa") && kal[5].equals("21") )
			sb.append("\n" +kal[2]+" "+kal[1]+" : Awal Lailatul Qadar");

    	if(kal[9].equalsIgnoreCase("Besar") && kal[5].equals("10") )
    		sb.append("\n" +kal[2]+" "+kal[1]+" : Idhul Adha");

		//gerhana 8 oktober 14 

		//	if(kal[6].equalsIgnoreCase("Sawal") && kal[5].equals("1") )
    	//	sb.append(kal[2]+" "+kal[1]+" : Idhul Fitri"+"\n");
		//sb.append("\n"+"Kajian Rutin");



		//	sb.append(" ");
		//	if(kal[0].equalsIgnoreCase("Senin") && kal[4].equalsIgnoreCase("Wage"))
		//		sb.append("\n"+kal[2]+" "+kal[1]+" : Minggu Wage Puasa Weton");
		return sb;
		}
		
		
	StringBuilder KajianPenting(String[] kal, StringBuilder sb2, int mrh)
	{

		//KAJIAN PASARAN
		if(kal[0].equalsIgnoreCase("Kamis") && kal[4].equals("Pon") ){
			sb2.append("Kajian Malam Jum'at\nUst.Tedi \n 19:30)");
			
		}
		if(kal[0].equalsIgnoreCase("Kamis") && kal[4].equals("Kliwon") ){
			sb2.append("Kajian Malam Jum'at\nUst.Basuki\nBa'da Isya");

		}
		
		if(kal[0].equalsIgnoreCase("Kamis") && kal[4].equals("Legi") ){
			sb2.append("Kajian Malam Jum'at\nUst. Kiswanto\nBa'da Isya");

		}
		
		if(kal[0].equalsIgnoreCase("Kamis") && kal[4].equals("Pahing") ){
			sb2.append("Kajian Malam Jum'at\nUst.Haris\nBa'da Isya");

		}
		
		if(kal[0].equalsIgnoreCase("Kamis") && kal[4].equals("Wage") ){
			sb2.append("Kajian Malam Jum'at\nUst.Teguh R.\nBa'da Isya");

		}
		if(kal[0].equalsIgnoreCase("Minggu") && kal[4].equals("Pon") ){
			sb2.append("Kajian Ahad Pagi\nUst.Muh Zaed\nJam 06:00");

		}
		
		if(kal[0].equalsIgnoreCase("Minggu") && kal[4].equals("Wage") ){
			sb2.append("Kajian Ahad Pagi\nUst.Muh Shodiq\nJam 06:00");

		}
		if(kal[0].equalsIgnoreCase("Minggu") && kal[4].equals("Kliwon") ){
			sb2.append("Kajian Ahad Pagi\nUst.Ngatmin\nJam 06:00");

		}
		if(kal[0].equalsIgnoreCase("Minggu") && kal[4].equals("Legi") ){
			sb2.append("Kajian Ahad Pagi\nUst.Muh Abbas\nJam 06;00");
		}
		if(kal[0].equalsIgnoreCase("Minggu") && kal[4].equals("Pahing") ){
			sb2.append("Kajian Ahad Pagi\nUst.Teguh R\nJam 06:00");

		}
		
		//Aisyiyah
		if(kal[0].equalsIgnoreCase("Rabu") && kal[4].equals("Pahing") ){
			sb2.append("Rabu Pahing \nKajian Aisyiyah Kertek\n Jam 16:00");
		
		}
		if(kal[0].equalsIgnoreCase("Rabu") && kal[4].equals("Pon") ){
			sb2.append(" Rabu Pahing\nKajian Aisyiyah Kertek\nJam 16:00");

		}
		if(kal[0].equalsIgnoreCase("Rabu") && kal[4].equals("Kliwon") ){
			sb2.append(" Kajian Tafsir\nAisyiyah di SMP Muh\n16:00");
			
		}
		
		if(kal[0].equalsIgnoreCase("Jum'at") && kal[4].equals("Legi") ){
			sb2.append("Kajian Aisyiyah\nCabang kertek\nBa'da Ashar");

		}
	
//NA
		if(kal[0].equalsIgnoreCase("Kamis") && kal[4].equals("Pon") ){
			sb2.append("Kajian NA\nUst. Haris Suharto\nJam 16:00");
		}
		
		if(kal[0].equalsIgnoreCase("Jum'at") && kal[4].equals("Pahing") ){
			sb2.append(" Kajian NA \nDi Gedung Dakwah\nJam 16:00");

		}
		
		if(kal[0].equalsIgnoreCase("Jum'at") && kal[4].equals("Pon") ){
			sb2.append(" Kajian NA \nJam 16:00  ");

		}
		
		if(kal[0].equalsIgnoreCase("Minggu") && kal[4].equals("Pahing") ){
			sb2.append("Kajian NA \nRanting Campursari\nJam16:00");

		}
		
		
	

		 if(kal[0].equalsIgnoreCase("Selasa")  ){
		 	sb2.append(" Kajian \nPCPM Kertek\nJam 19:30");	}
		
		//end
		
		
    	return sb2;
		
    }
	
	StringBuilder jadwalkhotib(String[] kal, StringBuilder khotib)
	{

		//Khotib
		//aku
		if(kal[0].equalsIgnoreCase("Jum'at") && kal[4].equals("Pon") ){
			khotib.append("KHOTIB \nUST. TEDI");

		}

		if(kal[0].equalsIgnoreCase("Jum'at") && kal[4].equals("Wage") ){
			khotib.append("KHOTIB \nUST. YUSRON");

		}


		if(kal[0].equalsIgnoreCase("Jum'at") && kal[4].equals("Kliwon") ){
			khotib.append("KHOTIB \nUST. KISWANTO");

		}
		if(kal[0].equalsIgnoreCase("Jum'at") && kal[4].equals("Pahing") ){
			khotib.append("KHOTIB \nUST. RIDLO AR");

		}


		if(kal[0].equalsIgnoreCase("Jum'at") && kal[4].equals("Legi") ){
			khotib.append("KHOTIB \nUST. TEGUH RIDWAN");

		}
		
		
		return khotib;
		}
	
	
	
	
	StringBuilder JadwalSholat(String[] kal, StringBuilder sbSholat)
	{

	//asli
if(kal[10].equals("1") && kal[2].equals("01") )
			sbSholat.append("4:11\n5:53\n11:48\n15:12\n18:04\n19:18");
 
if(kal[10].equals("1") && kal[2].equals("02") )
			sbSholat.append("4:11\n5:53\n11:48\n15:12\n18:04\n19:18");
 
if(kal[10].equals("1") && kal[2].equals("03") )
			sbSholat.append("4:11\n5:53\n11:48\n15:12\n18:04\n19:18");
 
if(kal[10].equals("1") && kal[2].equals("04") )
			sbSholat.append("4:11\n5:53\n11:48\n15:12\n18:04\n19:18");
 
if(kal[10].equals("1") && kal[2].equals("05") )
			sbSholat.append("4:11\n5:53\n11:48\n15:12\n18:06\n19:22");
 		
if(kal[10].equals("1") && kal[2].equals("06") )
			sbSholat.append("4:13\n5:56\n11:51\n15:16\n18:06\n19:22");
 
if(kal[10].equals("1") && kal[2].equals("07") )
			sbSholat.append("4:13\n5:56\n11:51\n15:16\n18:06\n19:22");
 
if(kal[10].equals("1") && kal[2].equals("08") )
			sbSholat.append("4:13\n5:56\n11:51\n15:16\n18:06\n19:22");
		//±-----------+-----------------------
if(kal[10].equals("1") && kal[2].equals("09") )
			sbSholat.append("4:13\n5:56\n11:51\n15:16\n18:06\n19:22");
 
if(kal[10].equals("1") && kal[2].equals("10") )
			sbSholat.append("4:13\n5:56\n11:51\n15:16\n18:06\n19:22");
 
if(kal[10].equals("1") && kal[2].equals("11") )
			sbSholat.append("4:16\n5:58\n11:53\n15:17\n18:09\n19:23");
 
if(kal[10].equals("1") && kal[2].equals("12") )
			sbSholat.append("4:16\n5:58\n11:53\n15:17\n18:09\n19:23");
 
if(kal[10].equals("1") && kal[2].equals("13") )
			sbSholat.append("4:16\n5:58\n11:53\n15:17\n18:09\n19:23");
 
if(kal[10].equals("1") && kal[2].equals("14") )
			sbSholat.append("4:16\n5:58\n11:53\n15:17\n18:09\n19:23");
 
if(kal[10].equals("1") && kal[2].equals("15") )
			sbSholat.append("4:17\n5:58\n11:53\n15:17\n18:10\n21:04");
 
if(kal[10].equals("1") && kal[2].equals("16") )
			sbSholat.append("4:19\n6:00\n11:55\n15:18\n18:10\n19:23");
 
if(kal[10].equals("1") && kal[2].equals("17") )
			sbSholat.append("4:19\n6:00\n11:55\n15:18\n15:24\n15:26");
 
if(kal[10].equals("1") && kal[2].equals("18") )
			sbSholat.append("4:19\n6:00\n11:55\n15:18\n18:10\n19:23");
 
if(kal[10].equals("1") && kal[2].equals("19") )
			sbSholat.append("4:19\n6:00\n11:55\n15:18\n18:10\n19:23");
 
if(kal[10].equals("1") && kal[2].equals("20") )
			sbSholat.append("4:19\n6:00\n11:55\n15:18\n18:10\n19:23");
 
if(kal[10].equals("1") && kal[2].equals("21") )
			sbSholat.append("4:22\n6:02\n11:56\n15:18\n18:11\n19:24");
 
if(kal[10].equals("1") && kal[2].equals("22") )
			sbSholat.append("4:22\n6:02\n11:56\n15:18\n18:11\n19:24");
 
if(kal[10].equals("1") && kal[2].equals("23") )
			sbSholat.append("4:22\n6:02\n11:56\n15:18\n18:11\n19:24");
 
if(kal[10].equals("1") && kal[2].equals("24") )
			sbSholat.append("4:22\n6:02\n11:56\n15:18\n18:11\n19:24");
 
if(kal[10].equals("1") && kal[2].equals("25") )
			sbSholat.append("4:22\n6:02\n11:56\n15:18\n18:11\n19:24");
 
if(kal[10].equals("1") && kal[2].equals("26") )
			sbSholat.append("4:24\n6:03\n11:57\n15:17\n18:11\n19:24");
 
if(kal[10].equals("1") && kal[2].equals("27") )
			sbSholat.append("4:24\n6:03\n11:57\n15:17\n18:11\n19:24");
 
if(kal[10].equals("1") && kal[2].equals("28") )
			sbSholat.append("4:24\n6:03\n11:57\n15:17\n18:11\n19:24");
 
if(kal[10].equals("1") && kal[2].equals("29") )
			sbSholat.append("4:24\n6:03\n11:57\n15:17\n18:11\n19:24");
 
if(kal[10].equals("1") && kal[2].equals("30") )
			sbSholat.append("4:24\n6:03\n11:57\n15:17\n18:11\n19:24");
 
if(kal[10].equals("1") && kal[2].equals("31") )
			sbSholat.append("4:26\n6:05\n11:58\n15:16\n18:11\n19:24");
 
if(kal[10].equals("2") && kal[2].equals("01") )
			sbSholat.append("4:26\n6:05\n11:58\n15:16\n18:11\n19:24");
 
if(kal[10].equals("2") && kal[2].equals("02") )
			sbSholat.append("4:26\n6:05\n11:58\n15:16\n18:11\n19:24");
 
if(kal[10].equals("2") && kal[2].equals("03") )
			sbSholat.append("4:26\n6:05\n11:58\n15:16\n18:11\n19:24");
 
if(kal[10].equals("2") && kal[2].equals("04") )
			sbSholat.append("4:28\n6:08\n11:58\n15:14\n18:10\n19:20");
 
if(kal[10].equals("2") && kal[2].equals("05") )
			sbSholat.append("4:28\n6:08\n11:58\n15:14\n18:10\n19:20");
 
if(kal[10].equals("2") && kal[2].equals("06") )
			sbSholat.append("4:28\n6:08\n11:58\n15:14\n18:10\n19:20");
 
if(kal[10].equals("2") && kal[2].equals("07") )
			sbSholat.append("4:28\n6:08\n11:58\n15:14\n18:10\n19:20");
 
if(kal[10].equals("2") && kal[2].equals("08") )
			sbSholat.append("4:28\n6:08\n11:58\n15:14\n18:10\n19:20");
 
if(kal[10].equals("2") && kal[2].equals("09") )
			sbSholat.append("4:29\n6:07\n11:58\n15:12\n18:09\n19:19");
 
if(kal[10].equals("2") && kal[2].equals("10") )
			sbSholat.append("4:29\n6:07\n11:58\n15:12\n18:09\n19:19");
 
if(kal[10].equals("2") && kal[2].equals("11") )
			sbSholat.append("4:29\n6:07\n11:58\n15:12\n18:09\n19:19");
 
if(kal[10].equals("2") && kal[2].equals("12") )
			sbSholat.append("4:29\n6:07\n11:58\n15:12\n18:09\n19:19");
 
if(kal[10].equals("2") && kal[2].equals("13") )
			sbSholat.append("4:29\n6:07\n11:58\n15:12\n18:09\n19:19");
 
if(kal[10].equals("2") && kal[2].equals("14") )
			sbSholat.append("4:31\n6:08\n11:58\n15:10\n18:08\n19:17");
 
if(kal[10].equals("2") && kal[2].equals("15") )
			sbSholat.append("4:31\n6:08\n11:58\n15:10\n18:08\n19:17");
 
if(kal[10].equals("2") && kal[2].equals("16") )
			sbSholat.append("4:31\n6:08\n11:58\n15:10\n18:08\n19:17");
 
if(kal[10].equals("2") && kal[2].equals("17") )
			sbSholat.append("4:31\n6:08\n11:58\n15:10\n18:08\n19:17");
 
if(kal[10].equals("2") && kal[2].equals("18") )
			sbSholat.append("4:31\n6:08\n11:58\n15:10\n18:08\n19:17");
 
if(kal[10].equals("2") && kal[2].equals("19") )
			sbSholat.append("4:33\n7:08\n11:58\n15:06\n18:07\n19:16");
 
if(kal[10].equals("2") && kal[2].equals("20") )
			sbSholat.append("4:33\n7:08\n11:58\n15:06\n18:07\n19:16");
 
if(kal[10].equals("2") && kal[2].equals("21") )
			sbSholat.append("4:33\n7:08\n11:58\n15:06\n18:07\n19:16");
 
if(kal[10].equals("2") && kal[2].equals("22") )
			sbSholat.append("4:33\n7:08\n11:58\n15:06\n18:07\n19:16");
 
if(kal[10].equals("2") && kal[2].equals("23") )
			sbSholat.append("4:33\n7:08\n11:58\n15:06\n18:07\n19:16");
 
if(kal[10].equals("2") && kal[2].equals("24") )
			sbSholat.append("4:34\n7:08\n11:57\n15:02\n18:05\n19:15");
 
if(kal[10].equals("2") && kal[2].equals("25") )
			sbSholat.append("4:34\n7:08\n11:57\n15:02\n18:05\n19:15");
 
if(kal[10].equals("2") && kal[2].equals("26") )
			sbSholat.append("4:34\n7:08\n11:57\n15:02\n18:05\n19:15");
 
if(kal[10].equals("2") && kal[2].equals("27") )
			sbSholat.append("4:34\n7:08\n11:57\n15:02\n18:05\n19:15");
 
if(kal[10].equals("2") && kal[2].equals("28") )
			sbSholat.append("4:34\n7:08\n11:57\n15:02\n18:05\n19:15");
 
if(kal[10].equals("2") && kal[2].equals("29") )
			sbSholat.append("4:34\n7:08\n11:57\n15:02\n18:05\n19:15");
 
if(kal[10].equals("3") && kal[2].equals("01") )
			sbSholat.append("4:34\n6:08\n11:56\n14:59\n18:03\n19:12");
 
if(kal[10].equals("3") && kal[2].equals("02") )
			sbSholat.append("4:34\n6:08\n11:56\n14:59\n18:03\n19:12");
 
if(kal[10].equals("3") && kal[2].equals("03") )
			sbSholat.append("4:34\n6:08\n11:56\n14:59\n18:03\n19:12");
 
if(kal[10].equals("3") && kal[2].equals("04") )
			sbSholat.append("4:34\n6:08\n11:56\n14:59\n18:03\n19:12");
 
if(kal[10].equals("3") && kal[2].equals("05") )
			sbSholat.append("4:34\n6:08\n11:56\n14:59\n18:03\n19:12");
 
if(kal[10].equals("3") && kal[2].equals("06") )
			sbSholat.append("4:34\n6:08\n11:55\n15:01\n18:01\n19:10");
 
if(kal[10].equals("3") && kal[2].equals("07") )
			sbSholat.append("4:34\n6:08\n11:55\n15:01\n18:01\n19:10");
 
if(kal[10].equals("3") && kal[2].equals("08") )
			sbSholat.append("4:34\n6:08\n11:55\n15:01\n18:01\n19:10");
 
if(kal[10].equals("3") && kal[2].equals("09") )
			sbSholat.append("4:34\n6:08\n11:55\n15:01\n18:01\n19:10");
 
if(kal[10].equals("3") && kal[2].equals("10") )
			sbSholat.append("4:34\n6:08\n11:55\n15:01\n18:01\n19:10");
 
if(kal[10].equals("3") && kal[2].equals("11") )
			sbSholat.append("4:35\n6:08\n11:54\n15:03\n17:59\n19:10");
 
if(kal[10].equals("3") && kal[2].equals("12") )
			sbSholat.append("4:35\n6:08\n11:54\n15:03\n17:59\n19:10");
 
if(kal[10].equals("3") && kal[2].equals("13") )
			sbSholat.append("4:35\n6:08\n11:54\n15:03\n17:59\n19:10");
 
if(kal[10].equals("3") && kal[2].equals("14") )
			sbSholat.append("4:35\n6:08\n11:54\n15:03\n17:59\n19:10");
 
if(kal[10].equals("3") && kal[2].equals("15") )
			sbSholat.append("4:35\n6:08\n11:54\n15:03\n17:59\n19:10");
 
if(kal[10].equals("3") && kal[2].equals("16") )
			sbSholat.append("4:34\n6:08\n11:52\n15:03\n17:56\n19:05");
 
if(kal[10].equals("3") && kal[2].equals("17") )
			sbSholat.append("4:34\n6:08\n11:52\n15:03\n17:56\n19:05");
 
if(kal[10].equals("3") && kal[2].equals("18") )
			sbSholat.append("4:34\n6:08\n11:52\n15:03\n17:56\n19:05");
 
if(kal[10].equals("3") && kal[2].equals("19") )
			sbSholat.append("4:34\n6:08\n11:52\n15:03\n17:56\n19:05");
 
if(kal[10].equals("3") && kal[2].equals("20") )
			sbSholat.append("4:34\n6:08\n11:52\n15:03\n17:56\n19:05");
 
if(kal[10].equals("3") && kal[2].equals("21") )
			sbSholat.append("4:34\n6:08\n11:51\n15:04\n17:54\n19:02");
 
if(kal[10].equals("3") && kal[2].equals("22") )
			sbSholat.append("4:34\n6:08\n11:51\n15:04\n17:54\n19:02");
 
if(kal[10].equals("3") && kal[2].equals("23") )
			sbSholat.append("4:34\n6:08\n11:51\n15:04\n17:54\n19:02");
 
if(kal[10].equals("3") && kal[2].equals("24") )
			sbSholat.append("4:34\n6:08\n11:51\n15:04\n17:54\n19:02");
 
if(kal[10].equals("3") && kal[2].equals("25") )
			sbSholat.append("4:34\n6:08\n11:51\n15:04\n17:54\n19:02");
 
if(kal[10].equals("3") && kal[2].equals("26") )
			sbSholat.append("4:33\n6:08\n11:49\n15:04\n17:51\n19:00");
 
if(kal[10].equals("3") && kal[2].equals("27") )
			sbSholat.append("4:33\n6:08\n11:49\n15:04\n17:51\n19:00");
 
if(kal[10].equals("3") && kal[2].equals("28") )
			sbSholat.append("4:33\n6:08\n11:49\n15:04\n17:51\n19:00");
 
if(kal[10].equals("3") && kal[2].equals("29") )
			sbSholat.append("4:33\n6:08\n11:49\n15:04\n17:51\n19:00");
 
if(kal[10].equals("3") && kal[2].equals("30") )
			sbSholat.append("4:33\n6:08\n11:49\n15:04\n17:51\n19:00");
 
if(kal[10].equals("3") && kal[2].equals("31") )
			sbSholat.append("4:33\n6:08\n11:49\n15:04\n17:51\n19:00");
 
if(kal[10].equals("4") && kal[2].equals("01") )
			sbSholat.append("4:32\n6:09\n11:49\n15:05\n17:49\n18:57");
 
if(kal[10].equals("4") && kal[2].equals("02") )
			sbSholat.append("4:32\n6:09\n11:49\n15:05\n17:49\n18:57");
 
if(kal[10].equals("4") && kal[2].equals("03") )
			sbSholat.append("4:32\n6:09\n11:49\n15:05\n17:49\n18:57");
 
if(kal[10].equals("4") && kal[2].equals("04") )
			sbSholat.append("4:32\n6:09\n11:49\n15:05\n17:49\n18:57");
 
if(kal[10].equals("4") && kal[2].equals("05") )
			sbSholat.append("4:32\n6:09\n11:49\n15:05\n17:49\n18:57");
 
if(kal[10].equals("4") && kal[2].equals("06") )
			sbSholat.append("4:31\n6:07\n11:46\n15:04\n17:46\n18:54");
 
if(kal[10].equals("4") && kal[2].equals("07") )
			sbSholat.append("4:31\n6:07\n11:46\n15:04\n17:46\n18:54");
 
if(kal[10].equals("4") && kal[2].equals("08") )
			sbSholat.append("4:31\n6:07\n11:46\n15:04\n17:46\n18:54");
 
if(kal[10].equals("4") && kal[2].equals("09") )
			sbSholat.append("4:31\n6:07\n11:46\n15:04\n17:46\n18:54");
 
if(kal[10].equals("4") && kal[2].equals("10") )
			sbSholat.append("4:31\n6:07\n11:46\n15:04\n17:46\n18:54");
 
if(kal[10].equals("4") && kal[2].equals("11") )
			sbSholat.append("4:31\n6:07\n11:45\n15:03\n17:44\n18:53");
 
if(kal[10].equals("4") && kal[2].equals("12") )
			sbSholat.append("4:31\n6:07\n11:45\n15:03\n17:44\n18:53");
 
if(kal[10].equals("4") && kal[2].equals("13") )
			sbSholat.append("4:31\n6:07\n11:45\n15:03\n17:44\n18:53");
 
if(kal[10].equals("4") && kal[2].equals("14") )
			sbSholat.append("4:31\n6:07\n11:45\n15:03\n17:44\n18:53");
 
if(kal[10].equals("4") && kal[2].equals("15") )
			sbSholat.append("4:31\n6:07\n11:45\n15:03\n17:44\n18:53");
 
if(kal[10].equals("4") && kal[2].equals("16") )
			sbSholat.append("4:30\n6:07\n11:44\n15:03\n17:42\n18:51");
 
if(kal[10].equals("4") && kal[2].equals("17") )
			sbSholat.append("4:30\n6:07\n11:44\n15:03\n17:42\n18:51");
 
if(kal[10].equals("4") && kal[2].equals("18") )
			sbSholat.append("4:30\n6:07\n11:44\n15:03\n17:42\n18:51");
 
if(kal[10].equals("4") && kal[2].equals("19") )
			sbSholat.append("4:30\n6:07\n11:44\n15:03\n17:42\n18:51");
 
if(kal[10].equals("4") && kal[2].equals("20") )
			sbSholat.append("4:30\n6:07\n11:44\n15:03\n17:42\n18:51");
 
if(kal[10].equals("4") && kal[2].equals("21") )
			sbSholat.append("4:29\n6:06\n11:42\n15:02\n17:37\n18:48");
 
if(kal[10].equals("4") && kal[2].equals("22") )
			sbSholat.append("4:29\n6:06\n11:42\n15:02\n17:37\n18:48");
 
if(kal[10].equals("4") && kal[2].equals("23") )
			sbSholat.append("4:29\n6:06\n11:42\n15:02\n17:37\n18:48");
 
if(kal[10].equals("4") && kal[2].equals("24") )
			sbSholat.append("4:29\n6:06\n11:42\n15:02\n17:37\n18:48");
 
if(kal[10].equals("4") && kal[2].equals("25") )
			sbSholat.append("4:29\n6:06\n11:42\n15:02\n17:37\n18:48");
 
if(kal[10].equals("4") && kal[2].equals("26") )
			sbSholat.append("4:28\n6:06\n11:41\n15:01\n17:37\n18:47");
 
if(kal[10].equals("4") && kal[2].equals("27") )
			sbSholat.append("4:28\n6:06\n11:41\n15:01\n17:37\n18:47");
 
if(kal[10].equals("4") && kal[2].equals("28") )
			sbSholat.append("4:28\n6:06\n11:41\n15:01\n17:37\n18:47");
 
if(kal[10].equals("4") && kal[2].equals("29") )
			sbSholat.append("4:28\n6:06\n11:41\n15:01\n17:37\n18:47");
 
if(kal[10].equals("4") && kal[2].equals("30") )
			sbSholat.append("4:28\n6:06\n11:41\n15:01\n17:37\n18:47");
 
if(kal[10].equals("4") && kal[2].equals("31") )
			sbSholat.append("4:28\n6:06\n11:41\n15:01\n17:37\n18:47");
 
if(kal[10].equals("5") && kal[2].equals("01") )
			sbSholat.append("4:28\n6:06\n11:41\n15:01\n17:37\n18:47");
 
if(kal[10].equals("5") && kal[2].equals("02") )
			sbSholat.append("4:29\n6:07\n11:41\n15:01\n17:36\n18:46");
 
if(kal[10].equals("5") && kal[2].equals("03") )
			sbSholat.append("4:29\n6:07\n11:41\n15:01\n17:36\n18:46");
 
if(kal[10].equals("5") && kal[2].equals("04") )
			sbSholat.append("4:29\n6:07\n11:41\n15:01\n17:36\n18:46");
 
if(kal[10].equals("5") && kal[2].equals("05") )
			sbSholat.append("4:29\n6:07\n11:41\n15:01\n17:36\n18:46");
 
if(kal[10].equals("5") && kal[2].equals("06") )
			sbSholat.append("4:29\n6:07\n11:41\n15:01\n17:36\n18:46");
 
if(kal[10].equals("5") && kal[2].equals("07") )
			sbSholat.append("4:28\n6:07\n11:40\n15:00\n17:34\n18:45");
 
if(kal[10].equals("5") && kal[2].equals("08") )
			sbSholat.append("4:28\n6:07\n11:40\n15:00\n17:34\n18:45");
 
if(kal[10].equals("5") && kal[2].equals("09") )
			sbSholat.append("4:28\n6:07\n11:40\n15:00\n17:34\n18:45");
 
if(kal[10].equals("5") && kal[2].equals("10") )
			sbSholat.append("4:28\n6:07\n11:40\n15:00\n17:34\n18:45");
 
if(kal[10].equals("5") && kal[2].equals("11") )
			sbSholat.append("4:28\n6:07\n11:40\n15:00\n17:34\n18:45");
 
if(kal[10].equals("5") && kal[2].equals("12") )
			sbSholat.append("4:28\n6:08\n11:40\n15:00\n17:33\n18:45");
 
if(kal[10].equals("5") && kal[2].equals("13") )
			sbSholat.append("4:28\n6:08\n11:40\n15:00\n17:33\n18:45");
 
if(kal[10].equals("5") && kal[2].equals("14") )
			sbSholat.append("4:28\n6:08\n11:40\n15:00\n17:33\n18:45");
 
if(kal[10].equals("5") && kal[2].equals("15") )
			sbSholat.append("4:28\n6:08\n11:40\n15:00\n17:33\n18:45");
 
if(kal[10].equals("5") && kal[2].equals("16") )
			sbSholat.append("4:28\n6:08\n11:40\n15:00\n17:33\n18:45");
 
if(kal[10].equals("5") && kal[2].equals("17") )
			sbSholat.append("4:29\n6:10\n11:41\n15:01\n17:34\n18:46");
 
if(kal[10].equals("5") && kal[2].equals("18") )
			sbSholat.append("4:29\n6:10\n11:41\n15:01\n17:34\n18:46");
 
if(kal[10].equals("5") && kal[2].equals("19") )
			sbSholat.append("4:29\n6:10\n11:41\n15:01\n17:34\n18:46");
 
if(kal[10].equals("5") && kal[2].equals("20") )
			sbSholat.append("4:29\n6:10\n11:41\n15:01\n17:34\n18:46");
 
if(kal[10].equals("5") && kal[2].equals("21") )
			sbSholat.append("4:29\n6:10\n11:41\n15:01\n17:34\n18:46");
 
if(kal[10].equals("5") && kal[2].equals("22") )
			sbSholat.append("4:29\n6:10\n11:41\n15:01\n17:33\n18:45");
 
if(kal[10].equals("5") && kal[2].equals("23") )
			sbSholat.append("4:29\n6:10\n11:41\n15:01\n17:33\n18:45");
 
if(kal[10].equals("5") && kal[2].equals("24") )
			sbSholat.append("4:29\n6:10\n11:41\n15:01\n17:33\n18:45");
 
if(kal[10].equals("5") && kal[2].equals("25") )
			sbSholat.append("4:29\n6:10\n11:41\n15:01\n17:33\n18:45");
 
if(kal[10].equals("5") && kal[2].equals("26") )
			sbSholat.append("4:29\n6:10\n11:41\n15:01\n17:33\n18:45");
 
if(kal[10].equals("5") && kal[2].equals("27") )
			sbSholat.append("4:30\n6:12\n11:42\n15:02\n17:33\n18:46");
 
if(kal[10].equals("5") && kal[2].equals("28") )
			sbSholat.append("4:30\n6:12\n11:42\n15:02\n17:33\n18:46");
 
if(kal[10].equals("5") && kal[2].equals("29") )
			sbSholat.append("4:30\n6:12\n11:42\n15:02\n17:33\n18:46");
 
if(kal[10].equals("5") && kal[2].equals("30") )
			sbSholat.append("4:30\n6:12\n11:42\n15:02\n17:33\n18:46");
 
if(kal[10].equals("5") && kal[2].equals("31") )
			sbSholat.append("4:30\n6:12\n11:42\n15:02\n17:33\n18:46");
 
if(kal[10].equals("6") && kal[2].equals("01") )
			sbSholat.append("4:30\n6:12\n11:42\n15:02\n17:33\n18:46");
 
if(kal[10].equals("6") && kal[2].equals("02") )
			sbSholat.append("4:31\n6:12\n11:42\n15:02\n17:33\n18:46");
 
if(kal[10].equals("6") && kal[2].equals("03") )
			sbSholat.append("4:31\n6:12\n11:42\n15:02\n17:33\n18:46");
 
if(kal[10].equals("6") && kal[2].equals("04") )
			sbSholat.append("4:31\n6:12\n11:42\n15:02\n17:33\n18:46");
 
if(kal[10].equals("6") && kal[2].equals("05") )
			sbSholat.append("4:31\n6:12\n11:42\n15:02\n17:33\n18:46");
 
if(kal[10].equals("6") && kal[2].equals("06") )
			sbSholat.append("4:31\n6:12\n11:42\n15:02\n17:33\n18:46");
 
if(kal[10].equals("6") && kal[2].equals("07") )
			sbSholat.append("4:30\n6:13\n11:43\n15:03\n17:33\n18:47");
 
if(kal[10].equals("6") && kal[2].equals("08") )
			sbSholat.append("4:30\n6:13\n11:43\n15:03\n17:33\n18:47");
 
if(kal[10].equals("6") && kal[2].equals("09") )
			sbSholat.append("4:30\n6:13\n11:43\n15:03\n17:33\n18:47");
 
if(kal[10].equals("6") && kal[2].equals("10") )
			sbSholat.append("4:30\n6:13\n11:43\n15:03\n17:33\n18:47");
 
if(kal[10].equals("6") && kal[2].equals("11") )
			sbSholat.append("4:30\n6:13\n11:43\n15:03\n17:33\n18:47");
 
if(kal[10].equals("6") && kal[2].equals("12") )
			sbSholat.append("4:33\n6:15\n11:44\n15:04\n17:34\n18:48");
 
if(kal[10].equals("6") && kal[2].equals("13") )
			sbSholat.append("4:33\n6:15\n11:44\n15:04\n17:34\n18:48");
 
if(kal[10].equals("6") && kal[2].equals("14") )
			sbSholat.append("4:33\n6:15\n11:44\n15:04\n17:34\n18:48");
 
if(kal[10].equals("6") && kal[2].equals("15") )
			sbSholat.append("4:33\n6:15\n11:44\n15:04\n17:34\n18:48");
 
if(kal[10].equals("6") && kal[2].equals("16") )
			sbSholat.append("4:33\n6:15\n11:44\n15:04\n17:34\n18:48");
 
if(kal[10].equals("6") && kal[2].equals("17") )
			sbSholat.append("4:34\n6:17\n11:45\n15:05\n17:35\n18:49");
 
if(kal[10].equals("6") && kal[2].equals("18") )
			sbSholat.append("4:34\n6:17\n11:45\n15:05\n17:35\n18:49");
 
if(kal[10].equals("6") && kal[2].equals("19") )
			sbSholat.append("4:34\n6:17\n11:45\n15:05\n17:35\n18:49");
 
if(kal[10].equals("6") && kal[2].equals("20") )
			sbSholat.append("4:34\n6:17\n11:45\n15:05\n17:35\n18:49");
 
if(kal[10].equals("6") && kal[2].equals("21") )
			sbSholat.append("4:34\n6:17\n11:45\n15:05\n17:35\n18:49");
 
if(kal[10].equals("6") && kal[2].equals("22") )
			sbSholat.append("4:35\n6:17\n11:46\n15:06\n17:36\n18:50");
 
if(kal[10].equals("6") && kal[2].equals("23") )
			sbSholat.append("4:35\n6:17\n11:46\n15:06\n17:36\n18:50");
 
if(kal[10].equals("6") && kal[2].equals("24") )
			sbSholat.append("4:35\n6:17\n11:46\n15:06\n17:36\n18:50");
 
if(kal[10].equals("6") && kal[2].equals("25") )
			sbSholat.append("4:35\n6:17\n11:46\n15:06\n17:36\n18:50");
 
if(kal[10].equals("6") && kal[2].equals("26") )
			sbSholat.append("4:35\n6:17\n11:46\n15:06\n17:36\n18:50");
 
if(kal[10].equals("6") && kal[2].equals("27") )
			sbSholat.append("4:36\n6:18\n11:47\n15:06\n17:37\n18:51");
 
if(kal[10].equals("6") && kal[2].equals("28") )
			sbSholat.append("4:36\n6:18\n11:47\n15:06\n17:37\n18:51");
 
if(kal[10].equals("6") && kal[2].equals("29") )
			sbSholat.append("4:36\n6:18\n11:47\n15:06\n17:37\n18:51");
 
if(kal[10].equals("6") && kal[2].equals("30") )
			sbSholat.append("4:36\n6:18\n11:47\n15:06\n17:37\n18:51");
 
if(kal[10].equals("6") && kal[2].equals("31") )
			sbSholat.append("4:36\n6:18\n11:47\n15:06\n17:37\n18:51");
 
if(kal[10].equals("7") && kal[2].equals("01") )
			sbSholat.append("4:36\n6:18\n11:47\n15:06\n17:37\n18:51");
 
if(kal[10].equals("7") && kal[2].equals("02") )
			sbSholat.append("4:36\n6:18\n11:47\n15:06\n17:37\n18:51");
 
if(kal[10].equals("7") && kal[2].equals("03") )
			sbSholat.append("4:37\n6:18\n11:48\n15:10\n17:38\n18:52");
 
if(kal[10].equals("7") && kal[2].equals("04") )
			sbSholat.append("4:37\n6:18\n11:48\n15:10\n17:38\n18:52");
 
if(kal[10].equals("7") && kal[2].equals("05") )
			sbSholat.append("4:37\n6:18\n11:48\n15:10\n17:38\n18:52");
 
if(kal[10].equals("7") && kal[2].equals("06") )
			sbSholat.append("4:37\n6:18\n11:48\n15:10\n17:38\n18:52");
 
if(kal[10].equals("7") && kal[2].equals("07") )
			sbSholat.append("4:37\n6:18\n11:48\n15:10\n17:38\n18:52");
 
if(kal[10].equals("7") && kal[2].equals("08") )
			sbSholat.append("4:38\n6:19\n11:49\n15:10\n17:40\n18:53");
 
if(kal[10].equals("7") && kal[2].equals("09") )
			sbSholat.append("4:38\n6:19\n11:49\n15:10\n17:40\n18:53");
 
if(kal[10].equals("7") && kal[2].equals("10") )
			sbSholat.append("4:38\n6:19\n11:49\n15:10\n17:40\n18:53");
 
if(kal[10].equals("7") && kal[2].equals("11") )
			sbSholat.append("4:38\n6:19\n11:49\n15:10\n17:40\n18:53");
 
if(kal[10].equals("7") && kal[2].equals("12") )
			sbSholat.append("4:38\n6:19\n11:49\n15:10\n17:40\n18:53");
 
if(kal[10].equals("7") && kal[2].equals("13") )
			sbSholat.append("4:38\n6:20\n11:50\n15:10\n17:41\n18:54");
 
if(kal[10].equals("7") && kal[2].equals("14") )
			sbSholat.append("4:38\n6:20\n11:50\n15:10\n17:41\n18:54");
 
if(kal[10].equals("7") && kal[2].equals("15") )
			sbSholat.append("4:38\n6:20\n11:50\n15:10\n17:41\n18:54");
 
if(kal[10].equals("7") && kal[2].equals("16") )
			sbSholat.append("4:38\n6:20\n11:50\n15:10\n17:41\n18:54");
 
if(kal[10].equals("7") && kal[2].equals("17") )
			sbSholat.append("4:38\n6:20\n11:50\n15:10\n17:41\n18:54");
 
if(kal[10].equals("7") && kal[2].equals("18") )
			sbSholat.append("4:38\n6:19\n11:50\n15:10\n17:42\n18:54");
 
if(kal[10].equals("7") && kal[2].equals("19") )
			sbSholat.append("4:38\n6:19\n11:50\n15:10\n17:42\n18:54");
 
if(kal[10].equals("7") && kal[2].equals("20") )
			sbSholat.append("4:38\n6:19\n11:50\n15:10\n17:42\n18:54");
 
if(kal[10].equals("7") && kal[2].equals("21") )
			sbSholat.append("4:38\n6:19\n11:50\n15:10\n17:42\n18:54");
 
if(kal[10].equals("7") && kal[2].equals("22") )
			sbSholat.append("4:38\n6:19\n11:50\n15:10\n17:42\n18:54");
 
if(kal[10].equals("7") && kal[2].equals("23") )
			sbSholat.append("4:38\n6:19\n11:50\n15:10\n17:43\n18:55");
 
if(kal[10].equals("7") && kal[2].equals("24") )
			sbSholat.append("4:38\n6:19\n11:50\n15:10\n17:43\n18:55");
 
if(kal[10].equals("7") && kal[2].equals("25") )
			sbSholat.append("4:38\n6:19\n11:50\n15:10\n17:43\n18:55");
 
if(kal[10].equals("7") && kal[2].equals("26") )
			sbSholat.append("4:38\n6:19\n11:50\n15:10\n17:43\n18:55");
 
if(kal[10].equals("7") && kal[2].equals("27") )
			sbSholat.append("4:38\n6:19\n11:50\n15:10\n17:43\n18:55");
 
if(kal[10].equals("7") && kal[2].equals("28") )
			sbSholat.append("4:38\n6:18\n11:50\n15:10\n17:43\n18:55");
 
if(kal[10].equals("7") && kal[2].equals("29") )
			sbSholat.append("4:38\n6:18\n11:50\n15:10\n17:43\n18:55");
 
if(kal[10].equals("7") && kal[2].equals("30") )
			sbSholat.append("4:38\n6:18\n11:50\n15:10\n17:43\n18:55");
 
if(kal[10].equals("7") && kal[2].equals("31") )
			sbSholat.append("4:38\n6:18\n11:50\n15:10\n17:43\n18:55");
 
if(kal[10].equals("8") && kal[2].equals("01") )
			sbSholat.append("4:38\n6:18\n11:50\n15:10\n17:43\n18:55");
 
if(kal[10].equals("8") && kal[2].equals("02") )
			sbSholat.append("4:38\n6:18\n11:50\n15:10\n17:43\n18:55");
 
if(kal[10].equals("8") && kal[2].equals("03") )
			sbSholat.append("4:38\n6:17\n11:50\n15:10\n17:44\n18:55");
 
if(kal[10].equals("8") && kal[2].equals("04") )
			sbSholat.append("4:38\n6:17\n11:50\n15:10\n17:44\n18:55");
 
if(kal[10].equals("8") && kal[2].equals("05") )
			sbSholat.append("4:38\n6:17\n11:50\n15:10\n17:44\n18:55");
 
if(kal[10].equals("8") && kal[2].equals("06") )
			sbSholat.append("4:38\n6:17\n11:50\n15:10\n17:44\n18:55");
 
if(kal[10].equals("8") && kal[2].equals("07") )
			sbSholat.append("4:38\n6:17\n11:50\n15:10\n17:44\n18:55");
 
if(kal[10].equals("8") && kal[2].equals("08") )
			sbSholat.append("4:35\n6:15\n11:49\n15:09\n17:44\n18:54");
 
if(kal[10].equals("8") && kal[2].equals("09") )
			sbSholat.append("4:35\n6:15\n11:49\n15:09\n17:44\n18:54");
 
if(kal[10].equals("8") && kal[2].equals("10") )
			sbSholat.append("4:35\n6:15\n11:49\n15:09\n17:44\n18:54");
 
if(kal[10].equals("8") && kal[2].equals("11") )
			sbSholat.append("4:35\n6:15\n11:49\n15:09\n17:44\n18:54");
 
if(kal[10].equals("8") && kal[2].equals("12") )
			sbSholat.append("4:35\n6:15\n11:49\n15:09\n17:44\n18:54");
 
if(kal[10].equals("8") && kal[2].equals("13") )
			sbSholat.append("4:35\n6:13\n11:48\n15:05\n17:44\n18:54");
 
if(kal[10].equals("8") && kal[2].equals("14") )
			sbSholat.append("4:35\n6:13\n11:48\n15:05\n17:44\n18:54");
 
if(kal[10].equals("8") && kal[2].equals("15") )
			sbSholat.append("4:35\n6:13\n11:48\n15:05\n17:44\n18:54");
 
if(kal[10].equals("8") && kal[2].equals("16") )
			sbSholat.append("4:35\n6:13\n11:48\n15:05\n17:44\n18:54");
 
if(kal[10].equals("8") && kal[2].equals("17") )
			sbSholat.append("4:35\n6:13\n11:48\n15:05\n17:44\n18:54");
 
if(kal[10].equals("8") && kal[2].equals("18") )
			sbSholat.append("4:34\n6:11\n15:47\n15:07\n17:44\n18:53");
 
if(kal[10].equals("8") && kal[2].equals("19") )
			sbSholat.append("4:34\n6:11\n15:47\n15:07\n17:44\n18:53");
 
if(kal[10].equals("8") && kal[2].equals("20") )
			sbSholat.append("4:34\n6:11\n15:47\n15:07\n17:44\n18:53");
 
if(kal[10].equals("8") && kal[2].equals("21") )
			sbSholat.append("4:34\n6:11\n15:47\n15:07\n17:44\n18:53");
 
if(kal[10].equals("8") && kal[2].equals("22") )
			sbSholat.append("4:34\n6:11\n15:47\n15:07\n17:44\n18:53");
 
if(kal[10].equals("8") && kal[2].equals("23") )
			sbSholat.append("4:34\n6:11\n15:47\n15:07\n17:44\n18:53");
 
if(kal[10].equals("8") && kal[2].equals("24") )
			sbSholat.append("4:32\n6:07\n11:46\n15:05\n17:44\n18:53");
 
if(kal[10].equals("8") && kal[2].equals("25") )
			sbSholat.append("4:32\n6:07\n11:46\n15:05\n17:44\n18:53");
 
if(kal[10].equals("8") && kal[2].equals("26") )
			sbSholat.append("4:32\n6:07\n11:46\n15:05\n17:44\n18:53");
 
if(kal[10].equals("8") && kal[2].equals("27") )
			sbSholat.append("4:32\n6:07\n11:46\n15:05\n17:44\n18:53");
 
if(kal[10].equals("8") && kal[2].equals("28") )
			sbSholat.append("4:32\n6:07\n11:46\n15:05\n17:44\n18:53");
 
if(kal[10].equals("8") && kal[2].equals("29") )
			sbSholat.append("4:30\n6:06\n11:44\n15:02\n17:43\n18:52");
 
if(kal[10].equals("8") && kal[2].equals("30") )
			sbSholat.append("4:30\n6:06\n11:44\n15:02\n17:43\n18:52");
 
if(kal[10].equals("8") && kal[2].equals("31") )
			sbSholat.append("4:30\n6:06\n11:44\n15:02\n17:43\n18:52");
 
if(kal[10].equals("9") && kal[2].equals("01") )
			sbSholat.append("4:30\n6:06\n11:44\n15:02\n17:43\n18:52");
 
if(kal[10].equals("9") && kal[2].equals("02") )
			sbSholat.append("4:30\n6:06\n11:44\n15:02\n17:43\n18:52");
 
if(kal[10].equals("9") && kal[2].equals("03") )
			sbSholat.append("4:28\n6:04\n11:43\n14:59\n17:43\n18:51");
 
if(kal[10].equals("9") && kal[2].equals("04") )
			sbSholat.append("4:28\n6:04\n11:43\n14:59\n17:43\n18:51");
 
if(kal[10].equals("9") && kal[2].equals("05") )
			sbSholat.append("4:28\n6:04\n11:43\n14:59\n17:43\n18:51");
 
if(kal[10].equals("9") && kal[2].equals("06") )
			sbSholat.append("4:28\n6:04\n11:43\n14:59\n17:43\n18:51");
 
if(kal[10].equals("9") && kal[2].equals("07") )
			sbSholat.append("4:28\n6:04\n11:43\n14:59\n17:43\n18:51");
 
if(kal[10].equals("9") && kal[2].equals("08") )
			sbSholat.append("4:25\n5:30\n11:41\n14:57\n17:42\n18:50");
 
if(kal[10].equals("9") && kal[2].equals("09") )
			sbSholat.append("4:25\n5:30\n11:41\n14:57\n17:42\n18:50");
 
if(kal[10].equals("9") && kal[2].equals("10") )
			sbSholat.append("4:25\n5:30\n11:41\n14:57\n17:42\n18:50");
 
if(kal[10].equals("9") && kal[2].equals("11") )
			sbSholat.append("4:25\n5:30\n11:41\n14:57\n17:42\n18:50");
 
if(kal[10].equals("9") && kal[2].equals("12") )
			sbSholat.append("4:25\n5:30\n11:41\n14:57\n17:42\n18:50");
 
if(kal[10].equals("9") && kal[2].equals("13") )
			sbSholat.append("4:23\n5:57\n11:39\n14:54\n17:41\n18:50");
 
if(kal[10].equals("9") && kal[2].equals("14") )
			sbSholat.append("4:23\n5:57\n11:39\n14:54\n17:41\n18:50");
 
if(kal[10].equals("9") && kal[2].equals("15") )
			sbSholat.append("4:23\n5:57\n11:39\n14:54\n17:41\n18:50");
 
if(kal[10].equals("9") && kal[2].equals("16") )
			sbSholat.append("4:23\n5:57\n11:39\n14:54\n17:41\n18:50");
 
if(kal[10].equals("9") && kal[2].equals("17") )
			sbSholat.append("4:23\n5:57\n11:39\n14:54\n17:41\n18:50");
 
if(kal[10].equals("9") && kal[2].equals("18") )
			sbSholat.append("4:20\n5:54\n11:37\n14:50\n17:40\n18:48");
 
if(kal[10].equals("9") && kal[2].equals("19") )
			sbSholat.append("4:20\n5:54\n11:37\n14:50\n17:40\n18:48");
 
if(kal[10].equals("9") && kal[2].equals("20") )
			sbSholat.append("4:20\n5:54\n11:37\n14:50\n17:40\n18:48");
 
if(kal[10].equals("9") && kal[2].equals("21") )
			sbSholat.append("4:20\n5:54\n11:37\n14:50\n17:40\n18:48");
 
if(kal[10].equals("9") && kal[2].equals("22") )
			sbSholat.append("4:20\n5:54\n11:37\n14:50\n17:40\n18:48");
 
if(kal[10].equals("9") && kal[2].equals("23") )
			sbSholat.append("4:20\n5:54\n11:37\n14:50\n17:40\n18:48");
 
if(kal[10].equals("9") && kal[2].equals("24") )
			sbSholat.append("4:17\n5:50\n11:36\n14:46\n17:39\n18:48");
 
if(kal[10].equals("9") && kal[2].equals("25") )
			sbSholat.append("4:17\n5:50\n11:36\n14:46\n17:39\n18:48");
 
if(kal[10].equals("9") && kal[2].equals("26") )
			sbSholat.append("4:17\n5:50\n11:36\n14:46\n17:39\n18:48");
 
if(kal[10].equals("9") && kal[2].equals("27") )
			sbSholat.append("4:17\n5:50\n11:36\n14:46\n17:39\n18:48");
 
if(kal[10].equals("9") && kal[2].equals("28") )
			sbSholat.append("4:17\n5:50\n11:36\n14:46\n17:39\n18:48");
 
if(kal[10].equals("9") && kal[2].equals("29") )
			sbSholat.append("4:15\n5:48\n11:34\n14:43\n17:39\n18:48");
 
if(kal[10].equals("9") && kal[2].equals("30") )
			sbSholat.append("4:15\n5:48\n11:34\n14:43\n17:39\n18:48");
 
if(kal[10].equals("10") && kal[2].equals("01") )
			sbSholat.append("4:15\n5:48\n11:34\n14:43\n17:39\n18:48");
 
if(kal[10].equals("10") && kal[2].equals("02") )
			sbSholat.append("4:15\n5:48\n11:34\n14:43\n17:39\n18:48");
 
if(kal[10].equals("10") && kal[2].equals("03") )
			sbSholat.append("4:15\n5:48\n11:34\n14:43\n17:39\n18:48");
 
if(kal[10].equals("10") && kal[2].equals("04") )
			sbSholat.append("4:11\n5:45\n11:32\n14:38\n17:38\n18:47");
 
if(kal[10].equals("10") && kal[2].equals("05") )
			sbSholat.append("4:11\n5:45\n11:32\n14:38\n17:38\n18:47");
 
if(kal[10].equals("10") && kal[2].equals("06") )
			sbSholat.append("4:11\n5:45\n11:32\n14:38\n17:38\n18:47");
 
if(kal[10].equals("10") && kal[2].equals("07") )
			sbSholat.append("4:11\n5:45\n11:32\n14:38\n17:38\n18:47");
 
if(kal[10].equals("10") && kal[2].equals("08") )
			sbSholat.append("4:11\n5:45\n11:32\n14:38\n17:38\n18:47");
 
if(kal[10].equals("10") && kal[2].equals("09") )
			sbSholat.append("4:09\n5:53\n11:31\n14:34\n17:38\n18:47");
 
if(kal[10].equals("10") && kal[2].equals("10") )
			sbSholat.append("4:09\n5:53\n11:31\n14:34\n17:38\n18:47");
 
if(kal[10].equals("10") && kal[2].equals("11") )
			sbSholat.append("4:09\n5:53\n11:31\n14:34\n17:38\n18:47");
 
if(kal[10].equals("10") && kal[2].equals("12") )
			sbSholat.append("4:09\n5:53\n11:31\n14:34\n17:38\n18:47");
 
if(kal[10].equals("10") && kal[2].equals("13") )
			sbSholat.append("4:09\n5:53\n11:31\n14:34\n17:38\n18:47");
 
if(kal[10].equals("10") && kal[2].equals("14") )
			sbSholat.append("4:07\n5:40\n11:30\n14:35\n17:38\n18:48");
 
if(kal[10].equals("10") && kal[2].equals("15") )
			sbSholat.append("4:07\n5:40\n11:30\n14:35\n17:38\n18:48");
 
if(kal[10].equals("10") && kal[2].equals("16") )
			sbSholat.append("4:07\n5:40\n11:30\n14:35\n17:38\n18:48");
 
if(kal[10].equals("10") && kal[2].equals("17") )
			sbSholat.append("4:07\n5:40\n11:30\n14:35\n17:38\n18:48");
 
if(kal[10].equals("10") && kal[2].equals("18") )
			sbSholat.append("4:07\n5:40\n11:30\n14:35\n17:38\n18:48");
 
if(kal[10].equals("10") && kal[2].equals("19") )
			sbSholat.append("4:04\n5:39\n11:29\n14:37\n17:38\n18:47");
 
if(kal[10].equals("10") && kal[2].equals("20") )
			sbSholat.append("4:04\n5:39\n11:29\n14:37\n17:38\n18:47");
 
if(kal[10].equals("10") && kal[2].equals("21") )
			sbSholat.append("4:04\n5:39\n11:29\n14:37\n17:38\n18:47");
 
if(kal[10].equals("10") && kal[2].equals("22") )
			sbSholat.append("4:04\n5:39\n11:29\n14:37\n17:38\n18:47");
 
if(kal[10].equals("10") && kal[2].equals("23") )
			sbSholat.append("4:04\n5:39\n11:29\n14:37\n17:38\n18:47");
 
if(kal[10].equals("10") && kal[2].equals("24") )
			sbSholat.append("4:01\n5:38\n11:28\n14:40\n17:36\n18:47");
 
if(kal[10].equals("10") && kal[2].equals("25") )
			sbSholat.append("4:01\n5:38\n11:28\n14:40\n17:36\n18:47");
 
if(kal[10].equals("10") && kal[2].equals("26") )
			sbSholat.append("4:01\n5:38\n11:28\n14:40\n17:36\n18:47");
 
if(kal[10].equals("10") && kal[2].equals("27") )
			sbSholat.append("4:01\n5:38\n11:28\n14:40\n17:36\n18:47");
 
if(kal[10].equals("10") && kal[2].equals("28") )
			sbSholat.append("4:01\n5:38\n11:28\n14:40\n17:36\n18:47");
 
if(kal[10].equals("10") && kal[2].equals("29") )
			sbSholat.append("3:59\n5:37\n11:28\n14:42\n17:39\n18:49");
 
if(kal[10].equals("10") && kal[2].equals("30") )
			sbSholat.append("3:59\n5:37\n11:28\n14:42\n17:39\n18:49");
 
if(kal[10].equals("10") && kal[2].equals("31") )
			sbSholat.append("3:59\n5:37\n11:28\n14:42\n17:39\n18:49");
 
if(kal[10].equals("11") && kal[2].equals("01") )
			sbSholat.append("3:59\n5:37\n11:28\n14:42\n17:39\n18:49");
 
if(kal[10].equals("11") && kal[2].equals("02") )
			sbSholat.append("3:59\n5:37\n11:28\n14:42\n17:39\n18:49");
 
if(kal[10].equals("11") && kal[2].equals("03") )
			sbSholat.append("3:58\n5:36\n11:28\n14:46\n17:40\n18:50");
 
if(kal[10].equals("11") && kal[2].equals("04") )
			sbSholat.append("3:58\n5:36\n11:28\n14:46\n17:40\n18:50");
 
if(kal[10].equals("11") && kal[2].equals("05") )
			sbSholat.append("3:58\n5:36\n11:28\n14:46\n17:40\n18:50");
 
if(kal[10].equals("11") && kal[2].equals("06") )
			sbSholat.append("3:58\n5:36\n11:28\n14:46\n17:40\n18:50");
 
if(kal[10].equals("11") && kal[2].equals("07") )
			sbSholat.append("3:58\n5:36\n11:28\n14:46\n17:40\n18:50");
 
if(kal[10].equals("11") && kal[2].equals("08") )
			sbSholat.append("3:57\n5:36\n11:29\n14:47\n17:42\n18:55");
 
if(kal[10].equals("11") && kal[2].equals("09") )
			sbSholat.append("3:57\n5:36\n11:29\n14:47\n17:42\n18:55");
 
if(kal[10].equals("11") && kal[2].equals("10") )
			sbSholat.append("3:57\n5:36\n11:29\n14:47\n17:42\n18:55");
 
if(kal[10].equals("11") && kal[2].equals("11") )
			sbSholat.append("3:57\n5:36\n11:29\n14:47\n17:42\n18:55");
 
if(kal[10].equals("11") && kal[2].equals("12") )
			sbSholat.append("3:57\n5:36\n11:29\n14:47\n17:42\n18:55");
 
if(kal[10].equals("11") && kal[2].equals("13") )
			sbSholat.append("3:56\n5:33\n11:29\n14:49\n17:43\n18:56");
 
if(kal[10].equals("11") && kal[2].equals("14") )
			sbSholat.append("3:56\n5:33\n11:29\n14:49\n17:43\n18:56");
 
if(kal[10].equals("11") && kal[2].equals("15") )
			sbSholat.append("3:56\n5:33\n11:29\n14:49\n17:43\n18:56");
 
if(kal[10].equals("11") && kal[2].equals("16") )
			sbSholat.append("3:56\n5:33\n11:29\n14:49\n17:43\n18:56");
 
if(kal[10].equals("11") && kal[2].equals("17") )
			sbSholat.append("3:56\n5:33\n11:29\n14:49\n17:43\n18:56");
 
if(kal[10].equals("11") && kal[2].equals("18") )
			sbSholat.append("3:56\n5:36\n11:30\n14:52\n17:45\n18:58");
 
if(kal[10].equals("11") && kal[2].equals("19") )
			sbSholat.append("3:56\n5:36\n11:30\n14:52\n17:45\n18:58");
 
if(kal[10].equals("11") && kal[2].equals("20") )
			sbSholat.append("3:56\n5:36\n11:30\n14:52\n17:45\n18:58");
 
if(kal[10].equals("11") && kal[2].equals("21") )
			sbSholat.append("3:56\n5:36\n11:30\n14:52\n17:45\n18:58");
 
if(kal[10].equals("11") && kal[2].equals("22") )
			sbSholat.append("3:56\n5:36\n11:30\n14:52\n17:45\n18:58");
 
if(kal[10].equals("11") && kal[2].equals("23") )
			sbSholat.append("3:56\n5:37\n11:32\n14:55\n17:47\n17:01");
 
if(kal[10].equals("11") && kal[2].equals("24") )
			sbSholat.append("3:56\n5:37\n11:32\n14:55\n17:47\n17:01");
 
if(kal[10].equals("11") && kal[2].equals("25") )
			sbSholat.append("3:56\n5:37\n11:32\n14:55\n17:47\n17:01");
 
if(kal[10].equals("11") && kal[2].equals("26") )
			sbSholat.append("3:56\n5:37\n11:32\n14:55\n17:47\n17:01");
 
if(kal[10].equals("11") && kal[2].equals("27") )
			sbSholat.append("3:56\n5:37\n11:32\n14:55\n17:47\n17:01");
 
if(kal[10].equals("11") && kal[2].equals("28") )
			sbSholat.append("3:56\n5:38\n11:33\n14:57\n17:49\n19:03");
 
if(kal[10].equals("11") && kal[2].equals("29") )
			sbSholat.append("3:56\n5:38\n11:33\n14:57\n17:49\n19:03");
 
if(kal[10].equals("11") && kal[2].equals("30") )
			sbSholat.append("3:56\n5:38\n11:33\n14:57\n17:49\n19:03");
 
if(kal[10].equals("11") && kal[2].equals("31") )
			sbSholat.append("3:56\n5:38\n11:33\n14:57\n17:49\n19:03");
 
if(kal[10].equals("12") && kal[2].equals("01") )
			sbSholat.append("3:56\n5:38\n11:33\n14:57\n17:49\n19:03");
 
if(kal[10].equals("12") && kal[2].equals("02") )
			sbSholat.append("3:57\n5:40\n11:35\n15:00\n17:51\n19:06");
 
if(kal[10].equals("12") && kal[2].equals("03") )
			sbSholat.append("3:57\n5:40\n11:35\n15:00\n17:51\n19:06");
 
if(kal[10].equals("12") && kal[2].equals("04") )
			sbSholat.append("3:57\n5:40\n11:35\n15:00\n17:51\n19:06");
 
if(kal[10].equals("12") && kal[2].equals("05") )
			sbSholat.append("3:57\n5:40\n11:35\n15:00\n17:51\n19:06");
 
if(kal[10].equals("12") && kal[2].equals("06") )
			sbSholat.append("3:57\n5:40\n11:35\n15:00\n17:51\n19:06");
 
if(kal[10].equals("12") && kal[2].equals("07") )
			sbSholat.append("3:58\n5:41\n11:37\n15:02\n17:53\n19:09");
 
if(kal[10].equals("12") && kal[2].equals("08") )
			sbSholat.append("3:58\n5:41\n11:37\n15:02\n17:53\n19:09");
 
if(kal[10].equals("12") && kal[2].equals("09") )
			sbSholat.append("3:58\n5:41\n11:37\n15:02\n17:53\n19:09");
 
if(kal[10].equals("12") && kal[2].equals("10") )
			sbSholat.append("3:58\n5:41\n11:37\n15:02\n17:53\n19:09");
 
if(kal[10].equals("12") && kal[2].equals("11") )
			sbSholat.append("3:58\n5:41\n11:37\n15:02\n17:53\n19:09");
 
if(kal[10].equals("12") && kal[2].equals("12") )
			sbSholat.append("3:59\n5:43\n11:39\n15:05\n17:56\n19:11");
 
if(kal[10].equals("12") && kal[2].equals("13") )
			sbSholat.append("3:59\n5:43\n11:39\n15:05\n17:56\n19:11");
 
if(kal[10].equals("12") && kal[2].equals("14") )
			sbSholat.append("3:59\n5:43\n11:39\n15:05\n17:56\n19:11");
 
if(kal[10].equals("12") && kal[2].equals("15") )
			sbSholat.append("3:59\n5:43\n11:39\n15:05\n17:56\n19:11");
 
if(kal[10].equals("12") && kal[2].equals("16") )
			sbSholat.append("3:59\n5:43\n11:39\n15:05\n17:56\n19:11");
 
if(kal[10].equals("12") && kal[2].equals("17") )
			sbSholat.append("4:00\n5:45\n11:39\n15:07\n17:58\n19:13");
 
if(kal[10].equals("12") && kal[2].equals("18") )
			sbSholat.append("4:00\n5:45\n11:39\n15:07\n17:58\n19:13");
 
if(kal[10].equals("12") && kal[2].equals("19") )
			sbSholat.append("4:00\n5:45\n11:39\n15:07\n17:58\n19:13");
 
if(kal[10].equals("12") && kal[2].equals("20") )
			sbSholat.append("4:00\n5:45\n11:39\n15:07\n17:58\n19:13");
 
if(kal[10].equals("12") && kal[2].equals("21") )
			sbSholat.append("4:00\n5:45\n11:39\n15:07\n17:58\n19:13");
 
if(kal[10].equals("12") && kal[2].equals("22") )
			sbSholat.append("4:01\n5:48\n11:44\n15:10\n18:01\n19:16");
 
if(kal[10].equals("12") && kal[2].equals("23") )
			sbSholat.append("4:01\n5:48\n11:44\n15:10\n18:01\n19:16");
 
if(kal[10].equals("12") && kal[2].equals("24") )
			sbSholat.append("4:01\n5:48\n11:44\n15:10\n18:01\n19:16");
 
if(kal[10].equals("12") && kal[2].equals("25") )
			sbSholat.append("4:01\n5:48\n11:44\n15:10\n18:01\n19:16");
 
if(kal[10].equals("12") && kal[2].equals("26") )
			sbSholat.append("4:01\n5:48\n11:44\n15:10\n18:01\n19:16");
 
if(kal[10].equals("12") && kal[2].equals("27") )
			sbSholat.append("4:05\n5:50\n11:46\n15:12\n18:03\n19:18");
 
if(kal[10].equals("12") && kal[2].equals("28") )
			sbSholat.append("4:05\n5:50\n11:46\n15:12\n18:03\n19:18");
 
if(kal[10].equals("12") && kal[2].equals("29") )
			sbSholat.append("4:05\n5:50\n11:46\n15:12\n18:03\n19:18");
 
if(kal[10].equals("12") && kal[2].equals("30") )
			sbSholat.append("4:05\n5:50\n11:46\n15:12\n18:03\n19:18");
 
if(kal[10].equals("12") && kal[2].equals("31") )
			sbSholat.append("4:05\n5:50\n11:46\n15:12\n18:03\n19:18");
 




		//end


    	return sbSholat;
    }
}//end class
