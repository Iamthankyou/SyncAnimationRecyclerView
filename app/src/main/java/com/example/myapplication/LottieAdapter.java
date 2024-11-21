package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import java.util.List;

public class LottieAdapter extends RecyclerView.Adapter<LottieAdapter.LottieViewHolder> {

    private List<LottieItem> itemList;

    public LottieAdapter(List<LottieItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public LottieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lottie, parent, false);
        return new LottieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LottieViewHolder holder, int position) {
        LottieItem item = itemList.get(position);
        holder.lottieAnimationView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        holder.lottieAnimationView.setAnimation(item.getAnimationFile());
        holder.lottieAnimationView.setScaleX(2.2f);
        holder.lottieAnimationView.setScaleY(2.2f);
        holder.lottieAnimationView.setBackgroundColor(androidx.cardview.R.color.cardview_shadow_end_color);
        holder.lottieAnimationView.setRepeatCount(LottieDrawable.INFINITE);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class LottieViewHolder extends RecyclerView.ViewHolder {
        LottieAnimationView lottieAnimationView;

        public LottieViewHolder(@NonNull View itemView) {
            super(itemView);
            lottieAnimationView = itemView.findViewById(R.id.lottie_anim);
        }
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull LottieViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.lottieAnimationView.cancelAnimation();
    }
}

