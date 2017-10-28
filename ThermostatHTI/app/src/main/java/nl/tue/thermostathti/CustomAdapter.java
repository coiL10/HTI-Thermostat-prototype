package nl.tue.thermostathti;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.thermostatapp.util.Switch;

import java.util.ArrayList;

/**
 * Created by Loic on 28.10.2017.
 */

public class CustomAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Switch> mDataSource;

    public CustomAdapter(Context context, ArrayList<Switch> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = mInflater.inflate(R.layout.row_item, parent, false);

        ImageView dayImageView = (ImageView) rowView.findViewById(R.id.dayNight);

        TextView switchTimeTextView = (TextView) rowView.findViewById(R.id.switchTime);

        ImageButton thumbnailImageView = (ImageButton) rowView.findViewById(R.id.deleteButton);

        Switch s = (Switch) getItem(position);

        if (s.getType().equals("day")){
            dayImageView.setImageResource(R.drawable.sun24);
        } else {
            dayImageView.setImageResource(R.drawable.moon64);
        }

        switchTimeTextView.setText(s.getTime());

        return rowView;
    }
}