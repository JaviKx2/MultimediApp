package com.javikx2.multimediapp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.javikx2.multimediapp.R;
import com.javikx2.multimediapp.db.entity.CatalogueItem;
import com.javikx2.multimediapp.view.listener.FavCatalogueClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FavCatalogueAdapter extends RecyclerView.Adapter<FavCatalogueAdapter.NewsItemViewHolder> {
    private final FavCatalogueClickListener mRecyclerListener;
    private final List<CatalogueItem> items;

    private Context mContext;

    public FavCatalogueAdapter(List<CatalogueItem> items, Context context,
                               FavCatalogueClickListener recyclerClickListener) {
        this.items = items;
        mContext = context;
        mRecyclerListener = recyclerClickListener;
    }

    @Override
    public NewsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mContext).inflate(
                R.layout.item_catalogue, parent, false);

        return new NewsItemViewHolder(rootView, mRecyclerListener);
    }

    @Override
    public void onBindViewHolder(NewsItemViewHolder holder, int position) {
        holder.bindNewsItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateItems(List<CatalogueItem> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    class NewsItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.catalogue_list_item_img)
        ImageView catalogueImg;
        @Bind(R.id.catalogue_list_item_text)
        TextView catalogueTitle;
        NewsItemViewHolder(View itemView, final FavCatalogueClickListener recyclerClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            bindListener(itemView, recyclerClickListener);
        }

        void bindNewsItem(CatalogueItem item) {
            Picasso.with(mContext)
                    .load(item.getImgURL())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(catalogueImg);
            catalogueTitle.setText(item.getName());
        }

        private void bindListener(View itemView, final FavCatalogueClickListener recyclerClickListener) {
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    recyclerClickListener.onClickCatalogueItem(items.get(getAdapterPosition()).getId());
                    return true;
                }
            });
        }
    }
}
