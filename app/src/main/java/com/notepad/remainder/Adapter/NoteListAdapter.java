package com.notepad.remainder.Adapter;

import static com.notepad.remainder.DB.Converters.fromString;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.notepad.remainder.Activity.MainActivity;
import com.notepad.remainder.DB.Converters;
import com.notepad.remainder.DB.Note;
import com.notepad.remainder.R;
import com.notepad.remainder.databinding.CardsLayout2Binding;
import com.notepad.remainder.databinding.ImagevviewerBinding;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.ViewHolder> {
    private ArrayList<Note> arrayList;
    private MainActivity activity;
    private ItemClickListener itemClickListener;


    public NoteListAdapter(ArrayList<Note> arrayList, MainActivity activity, ItemClickListener itemClickListener) {
        this.arrayList = arrayList;
        this.activity = activity;
        this.itemClickListener = itemClickListener;
    }


    public interface ItemClickListener {
        void onItemClick(Note note, int po);

        void onDelete(Note note, int po);
    }

    public void updateData(ArrayList<Note> noteArrayList) {
        arrayList = noteArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.cards_layout2, parent, false);
        ViewHolder viewHolder = new ViewHolder(CardsLayout2Binding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NoteListAdapter.ViewHolder holder, int position) {
        if (arrayList.get(position).getColorCode().equals("#FFFFFF")) {
            holder.binding.tvCounter.setTextColor(Color.parseColor("#000000"));
            holder.binding.title.setTextColor(Color.parseColor("#000000"));
            holder.binding.des.setTextColor(Color.parseColor("#000000"));
        }

        if (arrayList.get(position).getTvColor()) {
            holder.binding.tvCounter.setTextColor(Color.parseColor("#FFFFFF"));
            holder.binding.title.setTextColor(Color.parseColor("#FFFFFF"));
            holder.binding.des.setTextColor(Color.parseColor("#FFFFFF"));
        }
        holder.binding.attechedImg.setVisibility(View.VISIBLE);
        holder.binding.title.setText("Title : " + arrayList.get(position).getTitle());
        Log.d("~~~", "" + arrayList.get(position).getColorCode());
        holder.binding.des.setText("Description : " + arrayList.get(position).getDes());
        holder.binding.tvCounter.setText(arrayList.get(position).getDataAndTime());

        holder.binding.bgline.setBackgroundColor(Color.parseColor(arrayList.get(position).getColorCode()));
        try {
            if (!(Converters.fromString(arrayList.get(position).getMyImageList()).get(0).equals("[]"))) {
                String imgUrl = fromString(arrayList.get(position).getMyImageList()).get(0).toString();
                Log.d("Image", "instantiateItem: 11  " +imgUrl);
                Glide.with(activity).load(imgUrl).into(holder.binding.attechedImg);
            }
        } catch (Exception e) {

        }
        holder.itemView.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.item_animation_fall_down));

        //   holder.bgline.setBackgroundColor(Color.parseColor("#" + arrayList.get(position).getColorCode()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                ArrayList<String> list = new ArrayList<>();
                list.clear();
                try {
                    if (!(Converters.fromString(arrayList.get(position).getMyImageList()).get(0).equals("[]"))) {
                        for (int i = 0; i < Converters.fromString(arrayList.get(position).getMyImageList()).size(); i++) {
                            String imgUrl = fromString(arrayList.get(position).getMyImageList()).get(i).toString();
                            list.add(imgUrl);
                        }
                        Log.d("TAG", "onClick: Size  " + Converters.fromString(arrayList.get(position).getMyImageList()).size());
                    }
                } catch (Exception e) {

                }

                final Dialog dialog = new Dialog(activity);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                Window window = dialog.getWindow();
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                ImagevviewerBinding binding = ImagevviewerBinding.inflate(activity.getLayoutInflater());
                dialog.setContentView(binding.getRoot());

                ImageAdapter adapter = new ImageAdapter(activity, list);
                binding.viewPager.setAdapter(adapter);
                dialog.show();


                /*
                //
                PopupMenu popup = new PopupMenu(activity, holder.itemView);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit:
                                itemClickListener.onItemClick(arrayList.get(position), position);
                                return true;
                            case R.id.Share:
                                Intent intent2 = new Intent();
                                intent2.setAction(Intent.ACTION_SEND);
                                intent2.setType("text/plain");
                                intent2.putExtra(Intent.EXTRA_TEXT, arrayList.get(position).getTitle() + "\n" + "Description : " + arrayList.get(position).getDes());
                                activity.startActivity(Intent.createChooser(intent2, "Share via"));
                                return true;

                            default:
                                itemClickListener.onDelete(arrayList.get(position), position);
                                return false;
                        }
                    }
                });
                popup.inflate(R.menu.options_menu);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    popup.setForceShowIcon(true);
                }
                popup.setGravity(Gravity.RIGHT);
                try {
                    @SuppressLint("DiscouragedPrivateApi") Field mFieldPopup = popup.getClass().getDeclaredField("mPopup");
                    mFieldPopup.setAccessible(true);
                    MenuPopupHelper mPopup = (MenuPopupHelper) mFieldPopup.get(popup);
                    mPopup.setForceShowIcon(true);
                } catch (Exception e) {

                }
                popup.show();*/
            }
        });
    }

    public void setFilter(ArrayList<Note> newList) {
        arrayList = new ArrayList<>();
        arrayList.addAll(newList);
        notifyDataSetChanged();
    }

    private void PopupMenu(View itemView) {
        PopupMenu popup = new PopupMenu(activity, itemView);
        popup.inflate(R.menu.options_menu);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardsLayout2Binding binding;

        public ViewHolder(CardsLayout2Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
