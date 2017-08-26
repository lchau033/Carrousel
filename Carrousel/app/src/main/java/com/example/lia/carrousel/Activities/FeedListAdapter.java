package com.example.lia.carrousel.Activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.lia.carrousel.R;

import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

/**
 * Created by Lia on 2016-07-01.
 */
public class FeedListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Map.Entry<String,String>> feedItems;

    public FeedListAdapter(Activity activity, List<Map.Entry<String,String>> feedItems) {
        this.activity = activity;
        this.feedItems = feedItems;
    }

    @Override
    public int getCount() {
        return feedItems.size();
    }

    @Override
    public Map.Entry<String,String> getItem(int location) {
        return feedItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.feed_item, null);

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView statusMsg = (TextView) convertView
                .findViewById(R.id.txtStatusMsg);

        Map.Entry<String,String> item = feedItems.get(position);

        statusMsg.setText(item.getValue());
        statusMsg.setTextColor(Color.BLACK);

        name.setText(item.getKey());
        name.setTextColor(Color.BLACK);
        name.setPaintFlags(name.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);


        return convertView;
    }

}