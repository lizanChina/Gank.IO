package com.sunfusheng.gank.viewprovider;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunfusheng.gank.MainApplication;
import com.sunfusheng.gank.R;
import com.sunfusheng.gank.model.GankItemTitle;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by sunfusheng on 2017/1/17.
 */
public class GankItemTitleViewProvider extends ItemViewBinder<GankItemTitle, GankItemTitleViewProvider.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_gank_title, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull GankItemTitle item) {
        holder.tvTitle.setTypeface(MainApplication.songTi);
        holder.tvTitle.setText(item.type + "");
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
