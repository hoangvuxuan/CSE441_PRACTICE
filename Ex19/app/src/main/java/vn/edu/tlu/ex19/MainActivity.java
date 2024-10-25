package vn.edu.tlu.ex19;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnparse;
    ListView lv;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnparse = findViewById(R.id.btnparse);
        lv = findViewById(R.id.lv);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylist);
        lv.setAdapter(myadapter);

        btnparse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseXml();
            }
        });
    }

    private void parseXml() {
        try {
            InputStream myinput = getAssets().open("employee.xml");
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(myinput, null);

            int eventType = parser.getEventType();
            String nodeName;
            String dataShow = "";

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        nodeName = parser.getName();
                        if (nodeName.equals("employee")) {
                            dataShow += "ID: " + parser.getAttributeValue(0) + " - ";
                        } else if (nodeName.equals("name")) {
                            dataShow += "Name: " + parser.nextText() + " - ";
                        } else if (nodeName.equals("phone")) {
                            dataShow += "Phone: " + parser.nextText();
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        nodeName = parser.getName();
                        if (nodeName.equals("employee")) {
                            mylist.add(dataShow);
                            dataShow = "";
                        }
                        break;
                }
                eventType = parser.next();
            }
            myadapter.notifyDataSetChanged();
            myinput.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }
}
