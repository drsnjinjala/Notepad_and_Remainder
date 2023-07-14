package com.notepad.remainder.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.notepad.remainder.R;

public class ColorListAdapter extends RecyclerView.Adapter<ColorListAdapter.ViewHolder> {
    public String[] mColors = {"FFFFFF", "0000FF", "7F00FF", "FF00FF", "BC8F8F", "3F51B5", "009688", "673AB7", "C71585", "008000", "FF007F", "FF0000", "FF7F00", "20B2AA", "4169E1", "FAEBD7", "2F4F4F", "DC143C", "DA70D6", "90EE90", "FFF8DC", "FFEFD5", "8A2BE2", "FFFF00", "7FFF00", "00FF00", "00FF7F", "00FFFF"};
    public static boolean isBlack[] = {true, true, true, false, false, true, true, true, true, true, false, true, false, false, true, false, true, true, false, false, false, false, true, false, false, false, false, false,};
    private Activity activity;
    private OnItemClickListener clickListener;

    public ColorListAdapter(Activity activity, OnItemClickListener clickListener) {
        this.activity = activity;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ColorListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.color_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull ColorListAdapter.ViewHolder holder, int position) {


        holder.couter.setText("" + position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(position, "#" + mColors[position], isBlack[position]);
            }
        });

        String color = "#" + mColors[position % mColors.length];
        for (int c = 1; c < mColors.length; c++) {
            holder.relativeLayout.setBackgroundColor(Color.parseColor(color));
            if (c == mColors.length) {
                c = 0;
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int item, String color, boolean tvColor);
    }

    @Override
    public int getItemCount() {
        return mColors.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout;
        TextView couter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
            couter = itemView.findViewById(R.id.couter);
        }
    }
}
