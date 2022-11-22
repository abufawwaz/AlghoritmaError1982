package com.roaita.imsakiyah.util;
import android.app.Notification;
	import android.app.NotificationManager;
	import android.app.PendingIntent;
	import android.content.Context;
	import android.content.Intent;
	import android.media.AudioManager;
	import android.media.MediaPlayer;
	import android.net.Uri;

import com.roaita.imsakiyah.R;

import java.io.*;
	
	public class Audio{

		MediaPlayer mp;
		Context context;

		public Audio(Context ct){
			this.context = ct;
		}
		public void playClick() {
			mp = MediaPlayer.create(context, R.raw.alert);
			mp.start();
		}
		
		
	public void playClick2() {
		mp = MediaPlayer.create(context, R.raw.beep);  

		mp.start();
	}
		}
