package com.sixthank.gogo.src.post.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.sixthank.gogo.R;

import java.util.ArrayList;

public class RandomPagerAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<Integer> imageList = new ArrayList<>();

    public RandomPagerAdapter(Context context)
    {
        this.mContext = context;
        this.setImageList();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_random_page, null);

        ImageView imageView = view.findViewById(R.id.post_which_img);
        imageView.setImageResource(imageList.get(position));

        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view == (View)o);
    }

    private void setImageList() {
        imageList.add(R.drawable.ic_ox);
        imageList.add(R.drawable.ic_random_bar_whole);
        imageList.add(R.drawable.ic_random_txt);
    }
}
