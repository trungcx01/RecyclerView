package com.example.exam.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.exam.R;

public class SpinnerAdapter extends BaseAdapter {
    private int[] images = {R.drawable.cat1, R.drawable.cat2, R.drawable.cat3};
    private Context context;

    public SpinnerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
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
        View item = LayoutInflater.from(context).inflate(R.layout.image_item, viewGroup, false);
        ImageView img = item.findViewById(R.id.img);
        img.setImageResource(images[i]);
        return item;
    }
}
