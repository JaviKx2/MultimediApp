package com.javikx2.multimediapp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.javikx2.multimediapp.R;
import com.javikx2.multimediapp.api.tviso.TvisoAPIUtils;
import com.javikx2.multimediapp.api.tviso.dto.catalogue.Result;
import com.javikx2.multimediapp.view.listener.CatalogueClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CatalogueAdapter extends RecyclerView.Adapter<CatalogueAdapter.CatalogueViewHolder> {

    private final CatalogueClickListener mRecyclerListener;
    private final List<Result> catalogueItems;

    private Context mContext;

    public CatalogueAdapter(List<Result> items, Context context,
                            CatalogueClickListener recyclerClickListener) {
        this.catalogueItems = items;
        mContext = context;
        mRecyclerListener = recyclerClickListener;
    }

    @Override
    public CatalogueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mContext).inflate(
                R.layout.item_catalogue, parent, false);

        return new CatalogueViewHolder(rootView, mRecyclerListener);
    }

    @Override
    public void onBindViewHolder(CatalogueViewHolder holder, int position) {
        holder.bindNewsItem(catalogueItems.get(position));
    }

    @Override
    public int getItemCount() {
        return catalogueItems.size();
    }

    public void updateItems(List<Result> items) {
        this.catalogueItems.clear();
        this.catalogueItems.addAll(items);
        notifyDataSetChanged();
    }

    class CatalogueViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.catalogue_list_item_img)
        ImageView catalogueImg;
        @Bind(R.id.catalogue_list_item_text)
        TextView catalogueTitle;

        CatalogueViewHolder(View itemView, final CatalogueClickListener recyclerClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            bindListener(itemView, recyclerClickListener);
        }

        void bindNewsItem(Result item) {
            String img_value = item.getImages().getBackdrop();
            String img_country = item.getImages().getCountry();
            String img_size = "w600";
            String img_url = TvisoAPIUtils.getCatalogueItemImgURL(img_value, img_country, img_size);
            Picasso.with(mContext)
                    .load(img_url)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(catalogueImg);
            catalogueTitle.setText(item.getName());
        }

        private void bindListener(View itemView, final CatalogueClickListener recyclerClickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recyclerClickListener.onClickCatalogueItem(catalogueItems.get(getAdapterPosition()));
                }
            });
        }
    }
}
