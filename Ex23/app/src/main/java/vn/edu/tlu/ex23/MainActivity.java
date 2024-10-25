package vn.edu.tlu.ex23;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static ListView lv1;
    ArrayList<List> mylist;
    MyArrayAdapter myadapter;
    String URL = "https://vnexpress.net/rss/tin-moi-nhat.rss";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv1 = findViewById(R.id.lv1);
        mylist = new ArrayList<>();
        myadapter = new MyArrayAdapter(this, R.layout.layout_listview, mylist);
        lv1.setAdapter(myadapter);

        new LoadExampleTask().execute();
    }

    class LoadExampleTask extends AsyncTask<Void, Void, ArrayList<List>> {
        @Override
        protected ArrayList<List> doInBackground(Void... voids) {
            mylist = new ArrayList<>();
            try {
                XMLParser myparser = new XMLParser();
                String xml = myparser.getXmlFromUrl(URL);
                XmlPullParserFactory fc = XmlPullParserFactory.newInstance();
                XmlPullParser parser = fc.newPullParser();
                parser.setInput(new StringReader(xml));

                int eventType = parser.getEventType();
                String title = "", link = "", description = "", des = "", urlStr = "";
                Bitmap mIcon_val = null;

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {
                        String nodeName = parser.getName();
                        if (nodeName.equals("title")) title = parser.nextText();
                        else if (nodeName.equals("link")) link = parser.nextText();
                        else if (nodeName.equals("description")) {
                            description = parser.nextText();
                            urlStr = description.substring(description.indexOf("src=") + 5, description.indexOf("></a") - 2);
                            des = description.substring(description.indexOf("</br>") + 5);
                            try {
                                URL newurl = new URL(urlStr);
                                mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else if (eventType == XmlPullParser.END_TAG) {
                        if (parser.getName().equals("item")) {
                            mylist.add(new List(mIcon_val, title, des, link));
                        }
                    }
                    eventType = parser.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mylist;
        }

        @Override
        protected void onPostExecute(ArrayList<List> result) {
            super.onPostExecute(result);
            myadapter.clear();
            myadapter.addAll(result);
        }
    }
}
