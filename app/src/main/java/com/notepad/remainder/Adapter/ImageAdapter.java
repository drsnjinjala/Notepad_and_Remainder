package com.notepad.remainder.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.notepad.remainder.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ImageAdapter extends PagerAdapter {
    Context context;
    private ArrayList<String> galImages;
    //    private int[] GalImages = new int[] {
//            R.drawable.circle_img,
//            R.drawable.circle_img,
//            R.drawable.circle_img
//    };
    ImageAdapter(Context context, ArrayList<String> GalImages) {
        this.context = context;
        galImages = GalImages;
    }

    @Override
    public int getCount() {
        return galImages.size();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.image_item, null);
        ImageView imageView = view.findViewById(R.id.imageView);
        Glide.with(context).load(Uri.parse(galImages.get(position))).placeholder(R.drawable.circle_img).into(imageView);

        ((ViewPager) container).addView(imageView, 0);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }


    public static Drawable drawableFromUrl(String url) throws IOException {
        Bitmap x;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        InputStream input = connection.getInputStream();

        x = BitmapFactory.decodeStream(input);
        return new BitmapDrawable(Resources.getSystem(), x);
    }
}