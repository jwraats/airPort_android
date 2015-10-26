package jwraats.jackevers.nl.airportandroid;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jack on 25-10-2015.
 */
public class AirportAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater mInflator;
    ArrayList mAirportList;

    Cursor cursor;

    public AirportAdapter(LayoutInflater layoutInflater, String iso_country) {
        cursor = AirportDatabaseHelper.getInstance(mContext).getAirports(iso_country);
        cursor.moveToFirst();
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        cursor.moveToFirst();
        cursor.move(position);

        String icao = cursor.getString(cursor.getColumnIndex("icao"));
        String name = cursor.getString(cursor.getColumnIndex("name"));

        Airport ap = new Airport();
        ap.icao = icao;
        ap.name = name;

        return ap;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(null == convertView)
        {
            //get data
            cursor.moveToFirst();
            cursor.move(position);

            String icao = cursor.getString(cursor.getColumnIndex("icao"));
            String name = cursor.getString(cursor.getColumnIndex("name"));

            //inflate and put data
            convertView = mInflator.inflate(android.R.layout.simple_list_item_2, null);

            TextView tv1 = (TextView) convertView.findViewById(android.R.id.text1);
            tv1.setText(name);

            TextView tv2 = (TextView) convertView.findViewById(android.R.id.text2);
            tv2.setText(icao);

            return convertView;
        }


        return null;
    }
}
