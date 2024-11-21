package com.example.myapplication;

import android.animation.ValueAnimator;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private LottieAdapter adapter;
    private ValueAnimator animator;
    private float globalProgress = 0f;

    private void setupGlobalAnimator(RecyclerView recyclerView) {
        animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setDuration(4000);
        animator.setRepeatCount(ValueAnimator.INFINITE);

        animator.addUpdateListener(animation -> {
            globalProgress = (float) animation.getAnimatedValue();
            updateRecyclerViewProgress(globalProgress, recyclerView);
        });

        animator.start();
    }

    private void updateRecyclerViewProgress(float progress, RecyclerView recyclerView) {
        Log.i("MainActivity", "updateRecyclerViewProgress: size: " + recyclerView.getChildCount());
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            View child = recyclerView.getChildAt(i);
            RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(child);

            if (viewHolder instanceof LottieAdapter.LottieViewHolder) {
                LottieAdapter.LottieViewHolder holder = (LottieAdapter.LottieViewHolder) viewHolder;
                holder.lottieAnimationView.setProgress(progress);
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        List<LottieItem> items = new ArrayList<>();
        adapter = new LottieAdapter(items);
        final int SIZE = 100;
        for (int i = 0; i < SIZE; i++) {
            items.add(new LottieItem(R.raw.animation_lottie, "First Animation"));
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        setupGlobalAnimator(recyclerView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (animator != null && animator.isRunning()) {
            animator.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (animator != null && animator.isPaused()) {
            animator.resume();
        }
    }
}