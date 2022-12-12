package com.roaita.imsakiyah.notes;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.roaita.imsakiyah.R;


public class SpacedService extends Service
{

	private PendingIntent yourPendingIntent;




    @Override
    public void onCreate() {
        super.onCreate();
        final Handler handler = new Handler();
        //handler.postDelayed(new Runnable() {
          //  public void run() {
                //NotesDataSource nds = new NotesDataSource(SpacedService.this);
              //  List<NoteItems> items = nds.getAllNotesForNotification();
              //  for(NoteItems item : items)
              //  {
                  //  SpacedService.this.notification(item.title,item.content);
                   // nds.incrementTotalReviews(item.content);
             //   }
              //  handler.postDelayed(this, 120000/2); //now is every 2 minutes
          //  }
     //   }, 120000/2);



    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void notification(String title, String message)
    {
        //int random = 1 + (int)(Math.random() * ((100 - 1) + 1)); // generates random number from 1 to 100

        int random = message.hashCode();
        final NotificationManager mgr= (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Notification note=new Notification(R.drawable.ic_launcher,"Smart Note "+"::"+title,System.currentTimeMillis());

        // This pending intent will open after notification click
        Intent intent = new Intent(this,AnswerCard.class);
        intent.putExtra("title",title);
        intent.putExtra("content",message);
        intent.putExtra("from","notification");
        PendingIntent i=PendingIntent.getActivity(this, random,intent,PendingIntent.FLAG_UPDATE_CURRENT);

      //  note.setLatestEventInfo(this,title,"Reminder From"+" Smart Note", i);
     
		//note.number=2;
        note.flags |= Notification.FLAG_AUTO_CANCEL;

        mgr.notify(random, note);
		
		//baru
		
		
		Notification notification;
		Intent notificationIntent = new Intent(this, NoteActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
		NotificationManager mNotificationManager = null;
	
			NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
			notification = builder.setContentIntent(contentIntent)
				.setSmallIcon(R.drawable.ic_launcher).setTicker(title).setWhen(System.currentTimeMillis())
				.setAutoCancel(true).setContentTitle(title)
				.setContentText(message).build();
			mNotificationManager.notify(
			
		random, notification);
		
		
		
		
		
		
		
    }
}
