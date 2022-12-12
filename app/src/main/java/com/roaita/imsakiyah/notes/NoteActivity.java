package com.roaita.imsakiyah.notes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
//import android.support.v7.app.ActionBarActivity;
import android.provider.Settings;
import android.text.SpannableString;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.roaita.imsakiyah.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class NoteActivity extends Activity
{
    private boolean doubleBackToExitPressedOnce = false;
    ArrayList<ImsakiyahItems> items;
    ImsakiyahAdapter ImsakiyahAdapter;
    ListView notes_lv;

    TextView tips;
    public List<ImsakiyahItems> getDataForListView()
    {
        items = new NotesDataSource(getApplicationContext()).getAllImsakiyah();Collections.sort(items, new Comparator<ImsakiyahItems>(){
            public int compare(ImsakiyahItems s1, ImsakiyahItems s2) {
                return s1.sDataSatu.compareToIgnoreCase(s2.sDataDua);
            }
        });
        return this.items;
    }




    public boolean onContextItemSelected(MenuItem paramMenuItem)
    {
        switch (paramMenuItem.getItemId())
        {
            case R.id.delete_item:
                int i = (int)((AdapterView.AdapterContextMenuInfo)paramMenuItem.getMenuInfo()).id;
                new NotesDataSource(getApplicationContext()).deleteOne(((ImsakiyahItems)this.items.get(i)).sDataSatu, ((ImsakiyahItems)this.items.get(i)).sDataDua);
                this.items.remove(i);
                this.ImsakiyahAdapter.notifyDataSetChanged();
                return true;

            default:
                return super.onContextItemSelected(paramMenuItem);


        }


    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_notes);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TextView localTextView = (TextView)findViewById(getResources().getIdentifier("action_bar_title", "id", "android"));
          Intent localIntent = new Intent(getApplicationContext(), SpacedService.class);
        localIntent.putExtra("KEY1", "Value to be used by the service");
        getApplicationContext().startService(localIntent);
        tips = (TextView) findViewById(R.id.tips);
         SpannableString localSpannableString = new SpannableString("Digital Imsakiyah by Roaita");
        tips.setText(localSpannableString);

        List<ImsakiyahItems> items = NoteActivity.this.getDataForListView();
        if (items.size() != 0)
        {
            tips.setVisibility(View.GONE);
        }
        this.ImsakiyahAdapter = new ImsakiyahAdapter(items);
        this.notes_lv = ((ListView)findViewById(R.id.notes_lv));
        this.notes_lv.addFooterView(new View(this), null, false);
        this.notes_lv.addHeaderView(new View(this), null, false);
        this.notes_lv.setAdapter(this.ImsakiyahAdapter);
        registerForContextMenu(this.notes_lv);

        this.notes_lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
            {
                Intent localIntent = new Intent(NoteActivity.this, AnswerCard.class);
                localIntent.putExtra("title", ((ImsakiyahItems) NoteActivity.this.items.get(paramAnonymousInt - 1)).sDataSatu);
                localIntent.putExtra("content", ((ImsakiyahItems) NoteActivity.this.items.get(paramAnonymousInt - 1)).sDataDua);
                localIntent.putExtra("from", "app");
                NoteActivity.this.startActivity(localIntent);
            }
        });
        this.notes_lv.setLongClickable(true);
    }

    public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo)
    {
        super.onCreateContextMenu(paramContextMenu, paramView, paramContextMenuInfo);
        getMenuInflater().inflate(R.menu.context, paramContextMenu);
    }

    public boolean onCreateOptionsMenu(Menu paramMenu)
    {
        getMenuInflater().inflate(R.menu.notes, paramMenu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem paramMenuItem)
    {
        int i = paramMenuItem.getItemId();
        if (i == R.id.newNotes)
        {
            startActivity(new Intent(this, NewNotes.class));
            return true;
        }
        if (i == R.id.about)
        {
            AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
            View localView = getLayoutInflater().inflate(R.layout.card4_pengurus, null);
            TextView localTextView1 = (TextView)localView.findViewById(R.id.titleabout);
            Typeface localTypeface = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");
            localTextView1.setTypeface(localTypeface);
            TextView localTextView2 = (TextView)localView.findViewById(R.id.contentabout);
            SpannableString localSpannableString = new SpannableString("Imsakiyah Digital");
         //   Linkify.addLinks(localSpannableString, 15);
            localTextView2.setTypeface(localTypeface);
            localTextView2.setText(localSpannableString);
            localBuilder.setView(localView).setPositiveButton("Ok", new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
                {
                }
            });
            localBuilder.show();
            return true;
        }
        return super.onOptionsItemSelected(paramMenuItem);
    }
    public void onClickNew(View v) {
        startActivity(new Intent(this, NewNotes.class));

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
    public class NotesAdapter extends BaseAdapter
    {
		
        
		List<NoteItems> items = null;

        public NotesAdapter(List<NoteItems> items)
        {
            this.items = items;
        }

        public int getCount()
        {
            return this.items.size();
        }

        public Object getItem(int paramInt)
        {
            return null;
        }

        public long getItemId(int paramInt)
        {
            return paramInt;
        }

        public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
        {
            if (paramView == null)
            {
                LayoutInflater localLayoutInflater = (LayoutInflater) NoteActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                paramView = localLayoutInflater.inflate(R.layout.list_item_notes, paramViewGroup, false);
                localLayoutInflater.inflate(R.layout.list_item_notes, paramViewGroup, false);
            }
            TextView localTextView1 = (TextView)paramView.findViewById(R.id.title);
            TextView localTextView2 = (TextView)paramView.findViewById(R.id.last_reviewed);
            TextView localTextView3 = (TextView)paramView.findViewById(R.id.total_reviews);

            NoteItems localNoteItems = (NoteItems)this.items.get(paramInt);
            localTextView1.setText(localNoteItems.content);
            long l1 = Long.valueOf(localNoteItems.last_reviewed).longValue();
            long l2 = System.currentTimeMillis() - l1;
            Object[] arrayOfObject = new Object[2];
            arrayOfObject[0] = Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(l2));
            arrayOfObject[1] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(l2) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l2)));
            String str = String.format("%d min, %d sec", arrayOfObject);
            localTextView2.setText("Last seen: " + str + " ago");
            localTextView3.setText("Notification sent: " + localNoteItems.total_reviews + " times");
            return paramView;
        }
    }

    public class ImsakiyahAdapter extends BaseAdapter
    {


        List<ImsakiyahItems> items = null;

        public ImsakiyahAdapter(List<ImsakiyahItems> items)
        {
            this.items = items;
        }

        public int getCount()
        {
            return this.items.size();
        }

        public Object getItem(int paramInt)
        {
            return null;
        }

        public long getItemId(int paramInt)
        {
            return paramInt;
        }

        public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
        {
            if (paramView == null)
            {
                LayoutInflater localLayoutInflater = (LayoutInflater) NoteActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                paramView = localLayoutInflater.inflate(R.layout.list_item_notes, paramViewGroup, false);
                localLayoutInflater.inflate(R.layout.list_item_notes, paramViewGroup, false);
            }
            TextView localTextView1 = (TextView)paramView.findViewById(R.id.title);
            TextView localTextView2 = (TextView)paramView.findViewById(R.id.last_reviewed);
            TextView localTextView3 = (TextView)paramView.findViewById(R.id.total_reviews);

            ImsakiyahItems localNoteItems = (ImsakiyahItems)this.items.get(paramInt);
            localTextView1.setText(localNoteItems.sDataSatu);
           // long l1 = Long.valueOf(localNoteItems.sDataDua).longValue();
          //  long l2 = System.currentTimeMillis() - l1;
            Object[] arrayOfObject = new Object[2];
           // arrayOfObject[0] = Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(l2));
          ///  arrayOfObject[1] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(l2) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l2)));
            String str = String.format("%d min, %d sec", arrayOfObject);
            localTextView2.setText("Last seen: " + str + " ago");
            localTextView3.setText("Notification sent: " + localNoteItems.sDataTiga + " times");
            return paramView;
        }
    }
}
