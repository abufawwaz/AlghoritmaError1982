package com.roaita.imsakiyah.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.roaita.imsakiyah.R;
import com.roaita.imsakiyah.notes.Keuangan;
import com.roaita.imsakiyah.notes.NoteActivity;
import com.roaita.imsakiyah.notes.SpacedService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;


public class UpdateActivity extends Activity {
    private boolean doubleBackToExitPressedOnce = false;
    private static SharedPreferences pref;
    private EditText eversi, eaddurl;
    private TextView infoupdate;
    private WebView swebview;
    private String versiapk = "versiapk", fileApk ,
    ApkUrl = "https://www.mbswonosobo.com/apk/"
    ;
    //https://www.mbswonosobo.com/apk/imsakiyah1.apk

    public void onClickSave(View v) {
        if (!eversi.getText().toString().trim().isEmpty())
            setDataSave(versiapk, String.valueOf(eversi.getText()));

    }

    /**
     * Deklarasi Edit Preferences dan mengubah data
     * yang memiliki key KEY_USERNAME_SEDANG_LOGIN dengan parameter username
     */
    public void setDataSave(String namadata, String data) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(namadata, data);
        editor.apply();
        Toast.makeText(getApplicationContext(), "Data Sudah Disimpan !!!", Toast.LENGTH_SHORT).show();

    }

    /**
     * Mengembalikan nilai dari key KEY_USERNAME_SEDANG_LOGIN berupa String
     */
    public static String getDataSave(String namadata) {
        return pref.getString(namadata, "");
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_update);
        pref = getSharedPreferences("mypreferences", MODE_PRIVATE);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        eversi = (EditText) findViewById(R.id.Eversi);
        eaddurl = (EditText) findViewById(R.id.addresUrl);
        infoupdate =  (TextView) this.findViewById(R.id.progrssupdate);
        swebview = (WebView) this.findViewById(R.id.webview);

        if(!getDataSave(versiapk).trim().isEmpty()) eversi.setText(getDataSave(versiapk));

        /*swebview.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition
                    , String mimetype, long contentLength) {
                String fileName = URLUtil.guessFileName(url, contentDisposition, mimetype);
                try {
                    String address = Environment.getExternalStorageDirectory().getAbsolutePath() + "/"
                            + Environment.DIRECTORY_DOWNLOADS + "/" +
                            fileName;
                    File file = new File(address);
                    boolean a = file.createNewFile();

                    URL link = new URL(url);
                    downloadFile(link, address);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

         */

        swebview.setDownloadListener(new DownloadListener()
        {
            @Override
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimeType,
                                        long contentLength) {
                DownloadManager.Request request = new DownloadManager.Request(
                        Uri.parse(url));
                request.setMimeType(mimeType);
                String cookies = CookieManager.getInstance().getCookie(url);
                request.addRequestHeader("cookie", cookies);
                request.addRequestHeader("User-Agent", userAgent);
                request.setDescription("Downloading File...");
                request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimeType));
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(
                        Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(
                                url, contentDisposition, mimeType));
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(request);
                Toast.makeText(getApplicationContext(), "Downloading File", Toast.LENGTH_LONG).show();
            }});



    }

    public void   onClickBrows(View v) {
        WebSettings webSettings = swebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        swebview.loadUrl(ApkUrl);

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.swebview.canGoBack()) {
            this.swebview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void onClickUpdate(View v) {
        //download file
        infoupdate.setText("Membuat folder");
        String address = Environment.getExternalStorageDirectory().getAbsolutePath() + "/"
                + Environment.DIRECTORY_DOWNLOADS + "/";

        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        File folder = new File(extStorageDirectory, "Imsakiyah");
        folder.mkdir();
        infoupdate.setText("Membuat file");
        fileApk = "imsakiyah" +eversi.getText().toString()+".apk";
        File file = new File(address, "imsakiyah" +eversi.getText().toString()+".apk");
        try {
            file.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        DownloadFile( "https://www.mbswonosobo.com/apk/imsakiyah2.apk", file);

    }

    public void DownloadFile(String fileURL, File directory) {
        infoupdate.setText("Mencoba memdownload file ...");
        eaddurl.setText(fileURL);
        try {
            FileOutputStream f = new FileOutputStream(directory);
            URL u = new URL(fileURL);
            HttpURLConnection c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            infoupdate.setText("Mencoba memdownload Apk ...");

            //c.setDoOutput(true);
            c.connect();
            InputStream in = c.getInputStream();
            byte[] buffer = new byte[1024];
            int len1 = 0;
            while ((len1 = in.read(buffer)) > 0) {
                f.write(buffer, 0, len1);
            }
            f.close();
            InstallFile(fileApk);
        } catch (Exception e) {
            infoupdate.setText("ada kesalahan gagal mendownload file ...");

            System.out.println("exception in DownloadFile: --------" + e.toString());
            e.printStackTrace();
        }



    }
    public void downloadFile(URL url, String outputFileName) throws IOException {

        try (InputStream in = url.openStream();
             ReadableByteChannel rbc = Channels.newChannel(in);
             FileOutputStream fos = new FileOutputStream(outputFileName)) {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        }
        // do your work here

    }
    public void InstallFile(String file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/APPS/" + file)), "application/vnd.android.package-archive");
        startActivity(intent);
    }
}