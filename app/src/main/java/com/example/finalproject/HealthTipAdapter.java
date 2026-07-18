package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class HealthTipAdapter extends ArrayAdapter<String> {

    Context context;
    String[] tips;

    public HealthTipAdapter(Context context, String[] tips) {
        super(context, 0, tips);
        this.context = context;
        this.tips = tips;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.grid_item, parent, false);
        }

        TextView txtTip = convertView.findViewById(R.id.txtTip);

        txtTip.setText(tips[position]);

        return convertView;
    }
}
