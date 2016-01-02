package com.dianping.viewholder;

import android.content.ClipData;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dianping.R;
import com.dianping.events.CitySelectedEvent;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/12/30.
 */
public class CityViewHolder extends RecyclerView.ViewHolder {

    public TextView tv_city_shortKey;
    public TextView tv_cityName;
    public CityViewHolder(View itemView) {
        super(itemView);
        tv_city_shortKey  = (TextView)itemView.findViewById(R.id.tv_city_shortKey);
        tv_cityName =(TextView) itemView.findViewById(R.id.tv_cityName);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new CitySelectedEvent(tv_cityName.getText().toString()));
            }
        });
    }
}
