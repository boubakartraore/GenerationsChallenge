package com.example.bacar.generationschallenge.Controller;

import android.app.Activity;

import java.text.Format;
import java.text.ParseException;
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

    public Date stringToDate (String d) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date datew = format.parse(d);
            return datew;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new Date();
    }


    public Date parse( String input ) throws java.text.ParseException {

        //NOTE: SimpleDateFormat uses GMT[-+]hh:mm for the TZ which breaks
        //things a bit.  Before we go on we have to repair this.
        SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ssz" );

        //this is zero time so we need to add that TZ indicator for
        if ( input.endsWith( "Z" ) ) {
            input = input.substring( 0, input.length() - 1) + "GMT-00:00";
        } else {
            int inset = 6;

            String s0 = input.substring( 0, input.length() - inset );
            String s1 = input.substring( input.length() - inset, input.length() );

            input = s0 + "GMT" + s1;
        }

        return df.parse( input );

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
