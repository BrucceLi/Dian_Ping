package com.dianping.adapter;

import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dianping.R;
import com.dianping.model.City;
import com.dianping.viewholder.CityViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/30.
 */
public class CityRecyclerAdapter extends RecyclerView.Adapter<CityViewHolder> {




    List<City> mList;

    StringBuilder _shortKeys = new StringBuilder();
    List<String> hasKeyViewList = new ArrayList<>();

    public CityRecyclerAdapter(List<City> cities)
    {
        mList=cities;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_city_item,parent,false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {

        String key = mList.get(position).getShotKey();
        String cityName = mList.get(position).getCityName();

        if (_shortKeys.indexOf(key, 0) == -1) {
            _shortKeys.append(key);
            hasKeyViewList.add(cityName);
        }
        if (hasKeyViewList.contains(cityName)) {
            holder.tv_city_shortKey.setText(key);
            holder.tv_city_shortKey.setVisibility(View.VISIBLE);
        } else {
            holder.tv_city_shortKey.setVisibility(View.GONE);
        }
        holder.tv_cityName.setText(cityName);

    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }
}
