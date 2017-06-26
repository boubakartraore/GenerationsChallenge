package com.example.bacar.generationschallenge.Controller;

import android.app.Activity;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Bacar on 20/06/2017.
 */

public class myDate extends Activity {

    private String date;
    private String heure;

    public String myHeure (Date d) {
        Format lheure = new SimpleDateFormat("HH:mm", Locale.FRENCH);
        heure = lheure.format(d);

        return heure;
    }

    public String myDate (Date d) {
        Format lheure = new SimpleDateFormat("dd MMM yyyy", Locale.FRENCH);
        date = lheure.format(d);

        return date;
    }






    /*

    Date now = new Date(2017,05,15,12,12);

    String dd;
    String hh;
    Format ladate;
    Format heure;

    ladate = new SimpleDateFormat("dd MMM yyyy",Locale.FRANCE);
    heure = new SimpleDateFormat( "hh:mm", Locale.FRENCH);

    dd = ladate.format(now);
    hh = heure.format(now);

    text.setText(now.toString());

    */
}
