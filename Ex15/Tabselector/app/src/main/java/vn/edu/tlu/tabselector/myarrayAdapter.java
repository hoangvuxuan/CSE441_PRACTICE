package vn.edu.tlu.tabselector;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class myarrayAdapter extends ArrayAdapter<Item> {
    Activity context;
    ArrayList<Item> myArray;
    int LayoutId;

    public myarrayAdapter(Activity context, int LayoutId, ArrayList<Item> arr) {
        super(context, LayoutId, arr);
        this.context = context;
        this.LayoutId = LayoutId;
        this.myArray = arr;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(LayoutId, null);

        Item myItem = myArray.get(position);
        ImageView btnlike = convertView.findViewById(R.id.btnlike);
        TextView tieude = convertView.findViewById(R.id.txttieude);
        TextView maso = convertView.findViewById(R.id.txtmaso);

        tieude.setText(myItem.getTieude());
        maso.setText(myItem.getMaso());

        if (myItem.getThich() == 1) {
            btnlike.setImageResource(R.drawable.ic_launcher_background);
        } else {
            btnlike.setImageResource(R.drawable.ic_launcher_foreground);
        }

        return convertView;
    }
}

