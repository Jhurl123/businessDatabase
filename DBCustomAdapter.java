package edu.dtcc.walldatabase;

///package edu.dtcc.sqlitelistview;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by fwalls on 11/10/2017.
 */

public class DBCustomAdapter extends CursorAdapter {

    public DBCustomAdapter(Context context, Cursor cursor) {
        super(context, cursor,0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        //Just
        return LayoutInflater.from(context).inflate(R.layout.test_view, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // Find fields to populate in inflated template
        TextView tvID = (TextView) view.findViewById(R.id.record_id);
        TextView tvName = (TextView) view.findViewById(R.id.name);
        TextView tvTitle = (TextView) view.findViewById(R.id.title);
        TextView tvBusiness = (TextView) view.findViewById(R.id.business);
        TextView tvAddress = (TextView) view.findViewById(R.id.address);
        TextView tvEmail = (TextView) view.findViewById(R.id.email);
        TextView tvPhone = (TextView) view.findViewById(R.id.phone);

        // Extract properties from cursor
        String id = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
        String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
        String business = cursor.getString(cursor.getColumnIndexOrThrow("business"));
        String address = cursor.getString(cursor.getColumnIndexOrThrow("address"));
        String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
        String phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));

        // Populate fields with extracted properties
        tvID.setText(String.valueOf(id));
        tvName.setText(String.valueOf(name));
        tvTitle.setText(String.valueOf(title));
        tvBusiness.setText(String.valueOf(business));
        tvAddress.setText(String.valueOf(address));
        tvEmail.setText(String.valueOf(email));
        tvPhone.setText(String.valueOf(phone));
    }
}