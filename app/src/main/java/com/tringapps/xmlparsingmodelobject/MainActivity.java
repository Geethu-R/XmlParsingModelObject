package com.tringapps.xmlparsingmodelobject;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String url = "http://www.feedforall.com/sample-feed.xml";
    ListView lv;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView)findViewById(R.id.list_item);

        new DownloadXmlTask().execute();
    }


    private class DownloadXmlTask extends AsyncTask<String, Void, String> {
        private XmlPullParserFactory xmlFactoryObj;
        ArrayList<ChannelItem> itemArrayList = new ArrayList<>();


        @Override
        protected String doInBackground(String... strings){
            HttpHandler sh = new HttpHandler();
            InputStream in = sh.loadXmlFromNetwork(url);

            try {
                xmlFactoryObj = XmlPullParserFactory.newInstance();
                XmlPullParser myParser;
                myParser = xmlFactoryObj.newPullParser();
                myParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                myParser.setInput(in, "UTF-8");
                itemArrayList = xmlParser(myParser);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        private ArrayList<ChannelItem> xmlParser(XmlPullParser myParser) throws XmlPullParserException, IOException {

            String text = null;

            int event = myParser.getEventType();
            ChannelItem item = null;

            while(event != XmlPullParser.END_DOCUMENT)
            {

                String name = myParser.getName();

                switch (event)
                {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        if (name.equalsIgnoreCase("item")) {
                            item = new ChannelItem();
                        }
                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;
                    case XmlPullParser.END_TAG:

                        if (item != null) {
                            if (name.equalsIgnoreCase("title")) {

                                item.title = text;
                            }
                            else if(name.equalsIgnoreCase("description"))
                            {
                                item.description = text;
                            }
                            else if(name.equalsIgnoreCase("link"))
                            {
                                item.link = text;
                                itemArrayList.add(item);
                               /* item = null;*/
                            }
                        }

                        break;

                }
                event = myParser.next();
            }


            return itemArrayList;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            CustomAdapter adapter = new CustomAdapter(MainActivity.this,itemArrayList);
            lv.setAdapter(adapter);
        }
    }
}
