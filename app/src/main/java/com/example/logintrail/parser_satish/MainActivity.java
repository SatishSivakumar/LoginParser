package com.example.logintrail.parser_satish;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import android.app.ProgressDialog;

public class MainActivity extends Activity {

XMLGettersSetters data;
ProgressDialog waitProgress;

    int countnum=0;



/** Called when the activity is first created. */
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    new BackgroundTask().execute();
}

public class BackgroundTask extends AsyncTask<Void, Integer, Void> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (waitProgress != null) {
            waitProgress.dismiss();
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (waitProgress != null) {
            waitProgress.dismiss();
        } else
            //We set up the layout
            setLayout();
    }

    @Override
    protected Void doInBackground(Void... params) {

        try {
            synchronized (this) {
                saxParser();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
private void saxParser() {
    try {
/**
* Create a new instance of the SAX parser
**/
        SAXParserFactory saxPF = SAXParserFactory.newInstance();
        SAXParser saxP = saxPF.newSAXParser();
        XMLReader xmlR = saxP.getXMLReader();

// URL of the XML
        URL url = new URL("http://www.papademas.net/cd_catalog3.xml");

/**
* Create the Handler to handle each of the XML tags.
**/
        XMLHandler myXMLHandler = new XMLHandler();
        xmlR.setContentHandler(myXMLHandler);
        xmlR.parse(new InputSource(url.openStream()));

    } catch (Exception e) {
        System.out.println(e);
    }

}

//And then a function which just implements the view thanks to the data we have in our XMLHandler.
//set up the different views, add them to the groupView and put everything in the layout
private void setLayout(){

    data = XMLHandler.data;

    /**
     * Get the view of the layout within the main layout, so that we can
     add TextViews.
     **/
    View layout = findViewById(R.id.layout);
    TextView count=(TextView)findViewById(R.id.count);

    /**
     * Create TextView Arrays to add the retrieved data to.
     **/
    TextView CD[];
    TextView title[];
    TextView artist[];
    TextView country[];
    TextView company[];
    TextView price[];
    TextView year[];

    /**
     * Makes the TextView length the size of the TextView arrays by
     getting the size of the
     **/
    CD = new TextView[data.getTitle().size()];
    title = new TextView[data.getTitle().size()];
    artist = new TextView[data.getArtist().size()];
    country = new TextView[data.getCountry().size()];
    company = new TextView[data.getCompany().size()];
    price = new TextView[data.getPrice().size()];
    year = new TextView[data.getYear().size()];


    /**
     * Run a for loop to set All the TextViews with text until
     * the size of the array is reached.
     *
     **/

    String co="BLACK";
    for (int i = 0; i < data.getTitle().size(); i++) {

        if ( (i/2)== 0 && i != 0){
            co="BLUE";
        }
        else{
            co="RED";
        }
        if ((data.getCD().get(i)).equals("yes")) {
            CD[i] = new TextView(this);
            CD[i].setText("Sold = " + data.getCD().get(i));
            CD[i].setTextColor(Color.parseColor(co));
            title[i] = new TextView(this);
            title[i].setText("Title = " + data.getTitle().get(i));
            title[i].setTextColor(Color.parseColor(co));
            artist[i] = new TextView(this);
            artist[i].setText("Artist = " + data.getArtist().get(i));
            artist[i].setTextColor(Color.parseColor(co));
            country[i] = new TextView(this);
            country[i].setText("Country = " + data.getCountry().get(i));
            country[i].setTextColor(Color.parseColor(co));
            company[i] = new TextView(this);
            company[i].setText("Company = " + data.getCompany().get(i));
            company[i].setTextColor(Color.parseColor(co));
            price[i] = new TextView(this);
            price[i].setText("Price = " + data.getPrice().get(i));
            price[i].setTextColor(Color.parseColor(co));
            year[i] = new TextView(this);
            year[i].setText("Year = " + data.getYear().get(i)+"\n"+"------------------------------------------------------------------------------------------");
            year[i].setTextColor(Color.parseColor(co));


                    ((ViewGroup) layout).addView(CD[i]);
            ((ViewGroup) layout).addView(title[i]);
            ((ViewGroup) layout).addView(artist[i]);
            ((ViewGroup) layout).addView(country[i]);
            ((ViewGroup) layout).addView(company[i]);
            ((ViewGroup) layout).addView(price[i]);
            ((ViewGroup) layout).addView(year[i]);
            countnum=countnum + 1;
        }
System.out.println("Count Final:- " + countnum);
    }

    count.setText("Total number of sold out :  " + countnum);
    //setContentView(layout);
}

}
