package com.tringapps.xmlparsingmodelobject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by geethu on 1/12/16.
 */
public class CustomAdapter extends BaseAdapter{

    Context context;
    ArrayList<ChannelItem> channelItems;
    LayoutInflater inflater;


    public CustomAdapter(MainActivity mainActivity, ArrayList<ChannelItem> itemS) {

        context = mainActivity;
        channelItems = itemS;

    }


    @Override
    public int getCount() {
        return channelItems.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View rowView = view;
        MyHolderClass holderTag;


        if (view == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.substitute_activity, null);
            holderTag = new MyHolderClass(rowView);
            rowView.setTag(holderTag);


        } else {
            holderTag = (MyHolderClass) rowView.getTag();

        }

        holderTag.tv1.setText(channelItems.get(i).title);
        holderTag.tv2.setText(channelItems.get(i).description);
        holderTag.tv3.setText(channelItems.get(i).link);


        return rowView;
    }

    private class MyHolderClass {

        TextView tv1,tv2,tv3;


        public MyHolderClass(View rowView) {

            tv1 = (TextView) rowView.findViewById(R.id.text1);
            tv2 = (TextView) rowView.findViewById(R.id.text2);
            tv3 = (TextView) rowView.findViewById(R.id.text3);



        }
    }

}
