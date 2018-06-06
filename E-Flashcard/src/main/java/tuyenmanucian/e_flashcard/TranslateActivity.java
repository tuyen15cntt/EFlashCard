package tuyenmanucian.e_flashcard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class TranslateActivity extends AppCompatActivity  {

    private static final String TAG = "TranslateActivity";
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);

        addControls();
        addEvents();
    }
    public static String getJSON(String url) {
        HttpsURLConnection con = null;
        try {
            URL u = new URL(url);
            con = (HttpsURLConnection) u.openConnection();

            con.connect();


            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            return sb.toString();


        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.disconnect();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }
    private void addEvents() {

        webview.getSettings().setJavaScriptEnabled(true);
        String text = "hello everybody";
        webview.loadUrl("https://translate.googleapis.com/translate_a/single?client=gtx&sl=en&tl=vi&dt=t&q="+text);
        String url = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=en&tl=vi&dt=t&q=hello";
        System.out.println(getJSON(url));
        //setContentView(webview);
    }



    private void addControls() {
        webview = findViewById(R.id.webview);
    }
}
