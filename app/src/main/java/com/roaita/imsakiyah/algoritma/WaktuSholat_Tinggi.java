package com.roaita.imsakiyah.algoritma;


import java.util.Scanner;
public class WaktuSholat_Tinggi {

	
	
	public String[] WaktuSholat(String tgl, String lintangin,String bujurin, String sholat, int derajat){
        //menginputkan data - data yang dibutuhkan
	
		 //Scanner isiandata = new Scanner(System.in);

		// System.out.print("Isi tanggal (dd-mm-yyyy) :");
		 
		 String waktu[]=tgl.split("-");
		 double tanggal=Double.parseDouble(waktu[0]);
		 double bulan=Double.parseDouble(waktu[1]);
		 double tahun=Double.parseDouble(waktu[2]);

		// System.out.print("Isi lintang (dd'mm'ss) :");
		 String lintangtempat[]=lintangin.split("'");
		 double lintang_derajat=Double.parseDouble(lintangtempat[0]);
		 double lintang_menit=Double.parseDouble(lintangtempat[1]);
		 double lintang_detik=Double.parseDouble(lintangtempat[2]);

		// System.out.print("Isi bujur (ddd'mm'ss) :");
		// String bujurtempat[]=isiandata.nextLine().split("'");
		
		String bujurtempat[]=bujurin.split("'");
		 double bujur_derajat=Double.parseDouble(bujurtempat[0]);
		 double bujur_menit=Double.parseDouble(bujurtempat[1]);
		 double bujur_detik=Double.parseDouble(bujurtempat[2]);
		// isiandata.close();


		/*contoh

		double tanggal= 31;
        double bulan = 7;
        double tahun= 2022;

        double lintang_derajat= -7;//Double.parseDouble(lintangtempat[0]);
        double lintang_menit=21;//Double.parseDouble(lintangtempat[1]);
        double lintang_detik=55;//Double.parseDouble(lintangtempat[2]);

        double bujur_derajat=109;//Double.parseDouble(bujurtempat[0]);
        double bujur_menit=53;//Double.parseDouble(bujurtempat[1]);
        double bujur_detik=52;//Double.parseDouble(bujurtempat[2]);
*/




        System.out.print(System.lineSeparator());
        System.out.println("HASIL :\n Lintang <-0+> :"+lintang_derajat);

        double timezone= 7 ;
        double tinggilokasi= 1000 ;
        double sudutsubuh= -19;
        double sudutisya=-18;
        double ihtiyat=0;
		double ihtiyatsubuh=0.05;
        //PERINGATAN!!!!
        //Bila nilai lintang/bujur yang dikehendaki positif maka kode lintang dibawah menjadi ditambah...bukan dikurang
        //Postif   = lintang_derajat+(lintang_menit/60)+(lintang_detik/3600)
        //Negative = lintang_derajat-(lintang_menit/60)-(lintang_detik/3600);

        //ubah ke nilai desimal
		double lintang;
		if(lintang_derajat <0){
			lintang= lintang_derajat-(lintang_menit/60)-(lintang_detik/3600);
			System.out.println("Lintang negatif :"+lintang_derajat);
		}else{
			System.out.println("Lintang positif :"+lintang_derajat);
			lintang = lintang_derajat+(lintang_menit/60)+(lintang_detik/3600);	
		}
		double bujur= bujur_derajat+(bujur_menit/60)+(bujur_detik/3600) ;

        //mengubah ke nilai radian
        double lintang_r = Math.toRadians(lintang);
        double sudutsubuh_r = Math.toRadians(sudutsubuh);
        double sudutisya_r = Math.toRadians(sudutisya);

        //menentukan julian day 12 UT
        int abad= (int)tahun/100;
        int b= 2+(abad/4)-abad;
        if ( bulan <= 2) {bulan=bulan+12; tahun = tahun - 1;}
        double JD =(1720994.5 + (int)(365.25*tahun) + (int)(30.60001 *(bulan + 1)) + b + tanggal+(double)12/24);

        //julian day ke Pukul 12 LT waktu lokal anda
        double JDL = JD-timezone/24;
        double sudut_tanggal= 2*Math.PI*(JDL-2451545)/365.25;

        //mail awal/deklinasi matahari/delta
        double deklinasijam12= 0.37877+23.264* Math.sin(Math.toRadians(57.297*sudut_tanggal-79.547))+0.3812*Math.sin(Math.toRadians(2*57.297*sudut_tanggal-82.682))+0.17132*Math.sin(Math.toRadians(3*57.297*sudut_tanggal-59.722));
        double deklinasijam12_r=Math.toRadians(deklinasijam12);
        //hitung equation of time
        double U =((JDL-2451545)/36525);
        double LO =Math.toRadians((280.46607+36000.7698*U)%360);
        double EoT_jam12=(-1*(1789+237*U)*Math.sin(LO)-(7146-62*U)*Math.cos(LO)+(9934-14*U)*Math.sin(2*LO)-(29+5*U)*Math.cos(2*LO)+(74+10*U)*Math.sin(3*LO)+(320-4*U)*Math.cos(3*LO)-212*Math.sin(4*LO))/1000;

        //mulai hitung waktu sholat (j=jah/derajat, q=qoh/menit, n=ni/detik)
        //dzuhur
        double perkiraan_dzuhur =(12+timezone-bujur/15-EoT_jam12/60)+ihtiyat;
        //jdl dzuhur
        double jdl_dzuhur=JDL+ perkiraan_dzuhur /24-0.5;
        double sudut_tanggal_d= 2*Math.PI*(jdl_dzuhur-2451545)/365.25;
        double U_d =((jdl_dzuhur-2451545)/36525);
        double LO_d =Math.toRadians((280.46607+36000.7698*U_d)%360);
        double deklinasi_d= 0.37877+23.264* Math.sin(Math.toRadians(57.297*sudut_tanggal_d-79.547))+0.3812*Math.sin(Math.toRadians(2*57.297*sudut_tanggal_d-82.682))+0.17132*Math.sin(Math.toRadians(3*57.297*sudut_tanggal_d-59.722));
        double EoT_d=(-1*(1789+237*U_d)*Math.sin(LO_d)-(7146-62*U_d)*Math.cos(LO_d)+(9934-14*U_d)*Math.sin(2*LO_d)-(29+5*U_d)*Math.cos(2*LO_d)+(74+10*U_d)*Math.sin(3*LO_d)+(320-4*U_d)*Math.cos(3*LO_d)-212*Math.sin(4*LO_d))/1000;
        double dzuhur=12+timezone-bujur/15-EoT_d/60;

        int jdzuhur=(int)dzuhur;
        double qdzuhur= (dzuhur%1)*60;
        double ndzuhur=(qdzuhur%1)*60;

        //ashar (ha=hour angle)
        //sebenarnya rumus sudutwaktu_a4 cuma 1 baris tapi karna terlalu panjang dipisahlah jadi 1,2,3
        double sudutwaktu_a1=Math.sin(Math.atan(1/(1+Math.tan(Math.abs(lintang_r-deklinasijam12_r)))));
        double sudutwaktu_a2=Math.sin(deklinasijam12_r)*Math.sin(lintang_r);
        double sudutwaktu_a3=Math.cos(deklinasijam12_r)*Math.cos(lintang_r);
        double sudutwaktu_a4=(sudutwaktu_a1-sudutwaktu_a2)/sudutwaktu_a3;
        double perkiraan_ashar=Math.toDegrees(Math.acos(sudutwaktu_a4));

        //jdl ashar
        double jdl_ashar=JDL+perkiraan_ashar/360;
        double sudut_tanggal_a= 2*Math.PI*(jdl_ashar-2451545)/365.25;
        double U_a =((jdl_ashar-2451545)/36525);
        double LO_a =Math.toRadians((280.46607+36000.7698*U_a)%360);
        double deklinasi_a= 0.37877+23.264* Math.sin(Math.toRadians(57.297*sudut_tanggal_a-79.547))+0.3812*Math.sin(Math.toRadians(2*57.297*sudut_tanggal_a-82.682))+0.17132*Math.sin(Math.toRadians(3*57.297*sudut_tanggal_a-59.722));
        double deklinasi_a_r=Math.toRadians(deklinasi_a);
        double EoT_a=(-1*(1789+237*U_a)*Math.sin(LO_a)-(7146-62*U_a)*Math.cos(LO_a)+(9934-14*U_a)*Math.sin(2*LO_a)-(29+5*U_a)*Math.cos(2*LO_a)+(74+10*U_a)*Math.sin(3*LO_a)+(320-4*U_a)*Math.cos(3*LO_a)-212*Math.sin(4*LO_a))/1000;
        double alt_nampak=Math.toDegrees(Math.atan(1/(1+Math.tan(Math.abs(lintang_r-deklinasi_a_r)))));
        double koreksi_alt=(1/(60*Math.tan(Math.toRadians(alt_nampak+7.31/(alt_nampak+4.4)))));
        double alt_sejati=alt_nampak-koreksi_alt;
        double cosSudutwaktu_a=(Math.sin(Math.toRadians(alt_sejati))-Math.sin(deklinasi_a_r)*Math.sin(lintang_r))/(Math.cos(deklinasi_a_r)*Math.cos(lintang_r));
        double sudutwaktu_a=Math.toDegrees(Math.acos(cosSudutwaktu_a));
        double ashar=(12+timezone-bujur/15-EoT_a/60+sudutwaktu_a/15)+ihtiyat;

        int jashar=(int)ashar;
        double qashar= (ashar-jashar)*60;
        double nashar=(qashar%1)*60;


        //maghrib
        double perkiraan_cosSudutW_m=(Math.sin(Math.toRadians(-0.8333-0.0347*Math.sqrt(tinggilokasi)))-Math.sin(deklinasijam12_r)*(Math.sin(lintang_r)))/(Math.cos(deklinasijam12_r)*Math.cos(lintang_r));
        double perkiraan_SudutW_m=Math.toDegrees(Math.acos(perkiraan_cosSudutW_m));

        //jdl maghrib
        double jdl_maghrib=JDL+perkiraan_SudutW_m/360;
        double sudut_tanggal_m= 2*Math.PI*(jdl_maghrib-2451545)/365.25;
        double U_m =((jdl_maghrib-2451545)/36525);
        double LO_m =Math.toRadians((280.46607+36000.7698*U_m)%360);
        double deklinasi_m= 0.37877+23.264* Math.sin(Math.toRadians(57.297*sudut_tanggal_m-79.547))+0.3812*Math.sin(Math.toRadians(2*57.297*sudut_tanggal_m-82.682))+0.17132*Math.sin(Math.toRadians(3*57.297*sudut_tanggal_m-59.722));
        double deklinasi_m_r=Math.toRadians(deklinasi_m);
        double EoT_m=(-1*(1789+237*U_m)*Math.sin(LO_m)-(7146-62*U_m)*Math.cos(LO_m)+(9934-14*U_m)*Math.sin(2*LO_m)-(29+5*U_m)*Math.cos(2*LO_m)+(74+10*U_m)*Math.sin(3*LO_m)+(320-4*U_m)*Math.cos(3*LO_m)-212*Math.sin(4*LO_m))/1000;
        double cosSudutwaktu_m=(Math.sin(Math.toRadians(-0.8333-0.0347*Math.sqrt(tinggilokasi)))-Math.sin(deklinasi_m_r)*(Math.sin(lintang_r)))/(Math.cos(deklinasi_m_r)*Math.cos(lintang_r));
        double sudutwaktu_m=Math.toDegrees(Math.acos(cosSudutwaktu_m));

        double maghrib=(12+timezone-bujur/15-EoT_m/60+sudutwaktu_m/15)+ihtiyat;
        int jmaghrib=(int)maghrib;
        double qmaghrib= (maghrib%1)*60;
        double nmaghrib=(qmaghrib%1)*60;

        //isya
        double ha_isya=Math.toDegrees(Math.acos((Math.sin(sudutisya_r)-Math.sin(lintang_r)*Math.sin(deklinasijam12_r))/(Math.cos(lintang_r)*Math.cos(deklinasijam12_r))));
        double jdl_isya=JDL+ha_isya/360;
        double sudut_tanggal_i= 2*Math.PI*(jdl_isya-2451545)/365.25;
        double U_i =((jdl_isya-2451545)/36525);
        double LO_i =Math.toRadians((280.46607+36000.7698*U_i)%360);
        double deklinasi_i= 0.37877+23.264* Math.sin((57.297*sudut_tanggal_i-79.547)*Math.PI/180)+0.3812*Math.sin((2*57.297*sudut_tanggal_i-82.682)*Math.PI/180)+0.17132*Math.sin((3*57.297*sudut_tanggal_i-59.722)*Math.PI/180);
        double deklinasi_i_r=Math.toRadians(deklinasi_i);
        double EoT_i=(-1*(1789+237*U_i)*Math.sin(LO_i)-(7146-62*U_i)*Math.cos(LO_i)+(9934-14*U_i)*Math.sin(2*LO_i)-(29+5*U_i)*Math.cos(2*LO_i)+(74+10*U_i)*Math.sin(3*LO_i)+(320-4*U_i)*Math.cos(3*LO_i)-212*Math.sin(4*LO_i))/1000;
        double cosSudutwaktu_i= (Math.sin(sudutisya_r)-Math.sin(deklinasi_i_r)*Math.sin(lintang_r))/(Math.cos(deklinasi_i_r)*Math.cos(lintang_r));
        double sudutwaktu_i=Math.toDegrees(Math.acos(cosSudutwaktu_i));
        double isya=(12+timezone-bujur/15-EoT_i/60+sudutwaktu_i/15)+ihtiyat;

        int jisya=(int)isya;
        double qisya= (isya%1)*60;
        double nisya=(qisya%1)*60;


        //shubuh
        double ha_subuh= Math.toDegrees(Math.acos((Math.sin(Math.toRadians(sudutsubuh))-Math.sin(Math.toRadians(deklinasijam12))*Math.sin(Math.toRadians(lintang)))/(Math.cos(Math.toRadians(deklinasijam12))*Math.cos(Math.toRadians(lintang)))));
        double jdl_subuh=JDL-ha_subuh/360;
        double sudut_tanggal_s= 2*Math.PI*(jdl_subuh-2451545)/365.25;
        double U_s =((jdl_subuh-2451545)/36525);
        double LO_s =Math.toRadians((280.46607+36000.7698*U_s)%360);
        double deklinasi_s= 0.37877+23.264* Math.sin(Math.toRadians(57.297*sudut_tanggal_s-79.547))+0.3812*Math.sin(Math.toRadians(2*57.297*sudut_tanggal_s-82.682))+0.17132*Math.sin(Math.toRadians(3*57.297*sudut_tanggal_s-59.722));
        double EoT_s=(-1*(1789+237*U_s)*Math.sin(LO_s)-(7146-62*U_s)*Math.cos(LO_s)+(9934-14*U_s)*Math.sin(2*LO_s)-(29+5*U_s)*Math.cos(2*LO_s)+(74+10*U_s)*Math.sin(3*LO_s)+(320-4*U_s)*Math.cos(3*LO_s)-212*Math.sin(4*LO_s))/1000;
        double deklinasi_s_r=Math.toRadians(deklinasi_s);
        double cosSudutwaktu_s= (Math.sin(sudutsubuh_r)-Math.sin(deklinasi_s_r)*Math.sin(lintang_r))/(Math.cos(deklinasi_s_r)*Math.cos(lintang_r));
        double sudutwaktu_s=Math.toDegrees(Math.acos(cosSudutwaktu_s));
        double subuh = (12+timezone-bujur/15-EoT_s/60-sudutwaktu_s/15)+ihtiyatsubuh;

        int jsubuh=(int)subuh;
        double qsubuh= (subuh%1)*60;
        double nsubuh=(qsubuh%1)*60;


        //imsak 10 menit
        double imsak=subuh-0.166666666;
        int jimsak=(int)imsak;
        double qimsak= (imsak%1)*60;
        double nimsak=(qimsak%1)*60;

        //terbit
        double perkiraan_terbit=Math.toDegrees(Math.acos((Math.sin(Math.toRadians(-0.8333-0.0347*Math.sqrt(tinggilokasi)))-Math.sin(Math.toRadians(deklinasijam12))*Math.sin(Math.toRadians(lintang)))/(Math.cos(Math.toRadians(deklinasijam12))*Math.cos(Math.toRadians(lintang)))));
        double jdl_terbit=JDL-perkiraan_terbit/360;
        double sudut_tanggal_t= 2*Math.PI*(jdl_terbit-2451545)/365.25;
        double U_t =((jdl_terbit-2451545)/36525);
        double LO_t =Math.toRadians((280.46607+36000.7698*U_t)%360);
        double deklinasi_t= 0.37877+23.264* Math.sin(Math.toRadians(57.297*sudut_tanggal_t-79.547))+0.3812*Math.sin(Math.toRadians(2*57.297*sudut_tanggal_t-82.682))+0.17132*Math.sin(Math.toRadians(3*57.297*sudut_tanggal_t-59.722));
        double deklinasi_t_r=Math.toRadians(deklinasi_t);
        double EoT_t=(-1*(1789+237*U_t)*Math.sin(LO_t)-(7146-62*U_t)*Math.cos(LO_t)+(9934-14*U_t)*Math.sin(2*LO_t)-(29+5*U_t)*Math.cos(2*LO_t)+(74+10*U_t)*Math.sin(3*LO_t)+(320-4*U_t)*Math.cos(3*LO_t)-212*Math.sin(4*LO_t))/1000;
        double cosSudutwaktu_t=(Math.sin(Math.toRadians(-0.8333-0.0347*Math.sqrt(tinggilokasi)))-Math.sin(deklinasi_t_r)*(Math.sin(lintang_r)))/(Math.cos(deklinasi_t_r)*Math.cos(lintang_r));
        double sudutwaktu_t=Math.toDegrees(Math.acos(cosSudutwaktu_t));
        double terbit= (12+timezone-bujur/15-EoT_t/60-sudutwaktu_t/15)-ihtiyat;

        int jterbit=(int)terbit;
        double qterbit= (terbit%1)*60;
        double nterbit=(qterbit%1)*60;

        System.out.println("julian day    = " + JD);
        System.out.println("JDL           = " + JDL);
        System.out.println("sudut_tanggal = " + sudut_tanggal);
        System.out.println("U             = " + U);
        System.out.println("LO            = " + LO);
        System.out.println("deklinasi mat = " + deklinasijam12);
        System.out.println("EoT_jam12     = " + (EoT_jam12));
        System.out.print(System.lineSeparator());
        System.out.print(System.lineSeparator());



        System.out.println("jdl_dzuhur       = " + (jdl_dzuhur));
        System.out.println("sudut_tanggal_d  = " + (sudut_tanggal_d));
        System.out.println("U_d              = " + (U_d));
        System.out.println("LO_d             = " + (LO_d));
        System.out.println("deklinasi_d      = " + (deklinasi_d));
        System.out.println("EoT_d            = " + (EoT_d));
        System.out.println("dzuhur           = " + (dzuhur));
        System.out.print(System.lineSeparator());
        System.out.println("jdl_ashar        = " + (jdl_ashar));
        System.out.println("sudut_tanggal_a  = " + (sudut_tanggal_a));
        System.out.println("U_a              = " + (U_a));
        System.out.println("LO_a             = " + (LO_a));
        System.out.println("deklinasi_a      = " + (deklinasi_a));
        System.out.println("EoT_a            = " + (EoT_a));
        System.out.println("alt_nampak       = " + (alt_nampak));
        System.out.println("koreksi_alt      = " + (koreksi_alt));
        System.out.println("alt_sejati       = " + (alt_sejati));
        System.out.println("cosSudutwaktu_a  = " + (cosSudutwaktu_a));
        System.out.println("sudutwaktu_a    = " + (sudutwaktu_a));
        System.out.println("ashar            = " + (ashar));
        System.out.print(System.lineSeparator());

        System.out.println("jdl_maghrib      = " + (jdl_maghrib ));
        System.out.println("sudut_tanggal_m  = " + (sudut_tanggal_m));
        System.out.println("U_m              = " + (U_m));
        System.out.println("LO_m             = " + (LO_m));
        System.out.println("deklinasi_m      = " + (deklinasi_m));
        System.out.println("EoT_m            = " + (EoT_m));
        System.out.println("cosSudutwaktu_m  = " + (cosSudutwaktu_m));
        System.out.println("sudutwaktu_m    = " + (sudutwaktu_m));
        System.out.println("maghrib          = " + (maghrib ));
        System.out.print(System.lineSeparator());

        System.out.println("jdl_isya         = " + (jdl_isya ));
        System.out.println("sudut_tanggal_i  = " + (sudut_tanggal_i));
        System.out.println("U_i              = " + (U_i));
        System.out.println("LO_i             = " + (LO_i));
        System.out.println("deklinasi_i      = " + (deklinasi_i));
        System.out.println("EoT_i            = " + (EoT_i));
        System.out.println("cosSudutwaktu_i  = " + (cosSudutwaktu_i));
        System.out.println("sudutwaktu_i     = " + (sudutwaktu_i));
        System.out.println("isya             = " + (isya));

        System.out.print(System.lineSeparator());
        System.out.println("jdl_subuh        = " + (jdl_subuh ));
        System.out.println("sudut_tanggal_s  = " + (sudut_tanggal_s));
        System.out.println("U_s              = " + (U_s));
        System.out.println("LO_s             = " + (LO_s));
        System.out.println("delta_s          = " + (deklinasi_s));
        System.out.println("EoT_s            = " + (EoT_s));
        System.out.println("cosSudutwaktu_s  = " + (cosSudutwaktu_s));
        System.out.println("sudutwaktu_s     = " + (sudutwaktu_s));
        System.out.println("subuh            = " + (subuh));
        //System.out.println("imsak   = " + (jimsak) + ":"+ ((int)qimsak)+ ":"+ ((int)nimsak));
        System.out.print(System.lineSeparator());
        System.out.println("jdl_terbit       = " + (jdl_terbit ));
        System.out.println("sudut_tanggal_t  = " + (sudut_tanggal_t));
        System.out.println("U_t              = " + (U_t));
        System.out.println("LO_t             = " + (LO_t));
        System.out.println("delta_t          = " + (deklinasi_t));
        System.out.println("EoT_t            = " + (EoT_t));
        System.out.println("cosSudutwaktu_t  = " + (cosSudutwaktu_t));
        System.out.println("sudutwaktu_t     = " + (sudutwaktu_t));
        System.out.println("terbit           = " + (terbit));
        System.out.print(System.lineSeparator());
/*
        System.out.println("HASIL AKHIR:");
        System.out.println("imsak   = " + (jimsak) + ":"+ ((int)qimsak)+ ":"+ ((int)nimsak));
        System.out.print(System.lineSeparator());
        System.out.println("subuh   = " + (jsubuh) + ":"+ ((int)qsubuh)+ ":"+ ((int)nsubuh));
        System.out.print(System.lineSeparator());
        System.out.println("terbit  = " + (jterbit) + ":"+ ((int)qterbit)+ ":"+ ((int)nterbit));
        System.out.print(System.lineSeparator());
        System.out.println("dzuhur  = " + (jdzuhur) + ":"+ ((int)qdzuhur)+ ":"+ ((int)ndzuhur));
        System.out.print(System.lineSeparator());
        System.out.println("ashar   = " + (jashar) + ":"+ ((int)qashar)+ ":"+ ((int)nashar));
        System.out.print(System.lineSeparator());
        System.out.println("maghrib = " + (jmaghrib) + ":"+ ((int)qmaghrib)+ ":"+ ((int)nmaghrib));
        System.out.print(System.lineSeparator());
        System.out.println("isya    = " + (jisya) + ":"+ ((int)qisya)+ ":"+ ((int)nisya));
        System.out.print(System.lineSeparator());
		*/
		String wktimsak   = (jimsak) + ":"+ ((int)qimsak);//+ ":"+ ((int)nimsak);

        String wktsubuh   = (jsubuh) + ":"+ ((int)qsubuh);//+ ":"+ ((int)nsubuh);
            String mntterbit ="0"+((int)qterbit);
            if ( ((int)qterbit)>9)mntterbit =""+((int)qterbit);

        String wktterbit  =  (jterbit) + ":"+ mntterbit;//+ ":"+ ((int)nterbit);

        String wktdzuhur  = (jdzuhur) + ":"+ ((int)qdzuhur);//+ ":"+ ((int)ndzuhur);

        String wktashar   = (jashar) + ":"+ ((int)qashar);//+ ":"+ ((int)nashar);

        String wktmaghrib = (jmaghrib) + ":"+ ((int)qmaghrib);//+ ":"+ ((int)nmaghrib);
            String mqisya ="0"+((int)qisya);
            if ( ((int)qisya)>9)mntterbit =""+((int)qisya);

            String wktisya    = (jisya) + ":"+ mqisya;//+ ":"+ ((int)nisya);

		
		return new String[] {wktimsak,wktsubuh,wktterbit,wktdzuhur,wktashar,wktmaghrib,wktisya};
    }
}
