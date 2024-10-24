package vn.edu.tlu.custom_gridview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyarrayAdapter extends ArrayAdapter<Image> {
    private Activity context;
    private int layoutId;
    private ArrayList<Image> myArray;

    public MyarrayAdapter(Activity context, int layoutId, ArrayList<Image> arr) {
        super(context, layoutId, arr);
        this.context = context;
        this.layoutId = layoutId;
        this.myArray = arr;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutId, null);

        ImageView imgItem = convertView.findViewById(R.id.imageView1);
        TextView txtItem = convertView.findViewById(R.id.textView1);

        Image image = myArray.get(position);
        imgItem.setImageResource(image.getImg());
        txtItem.setText(image.getName());

        return convertView;
    }
}
