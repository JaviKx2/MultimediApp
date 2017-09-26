package com.javikx2.multimediapp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.javikx2.multimediapp.R;
import com.javikx2.multimediapp.db.entity.NewsItem;
import com.javikx2.multimediapp.view.listener.FavNewsClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FavNewsAdapter extends RecyclerView.Adapter<FavNewsAdapter.NewsItemViewHolder> {
    private final FavNewsClickListener mRecyclerListener;
    private final List<NewsItem> news;

    private Context mContext;

    public FavNewsAdapter(List<NewsItem> news, Context context,
                          FavNewsClickListener recyclerClickListener) {
        this.news = news;
        mContext = context;
        mRecyclerListener = recyclerClickListener;
    }

    @Override
    public NewsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mContext).inflate(
                R.layout.item_news, parent, false);

        return new NewsItemViewHolder(rootView, mRecyclerListener);
    }

    @Override
    public void onBindViewHolder(NewsItemViewHolder holder, int position) {
        holder.bindNewsItem(news.get(position));
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public void updateNews(List<NewsItem> news){
        this.news.clear();
        this.news.addAll(news);
        notifyDataSetChanged();
    }

    public class NewsItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.news_thumbnail)
        ImageView mNewsThumbnail;
        @Bind(R.id.news_title)
        TextView mNewsTitle;
        @Bind(R.id.news_desc)
        TextView mNewsDesc;

        public NewsItemViewHolder(View itemView, final FavNewsClickListener recyclerClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            bindListener(itemView, recyclerClickListener);
        }

        public void bindNewsItem(NewsItem newsItem) {
            Picasso.with(mContext)
                    .load(newsItem.getImgURL())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(mNewsThumbnail);

            mNewsTitle.setText(Html.fromHtml(newsItem.getTitle()));
            mNewsDesc.setText(Html.fromHtml(newsItem.getText()));
        }

        private void bindListener(View itemView, final FavNewsClickListener recyclerClickListener) {
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    recyclerClickListener.onClickNewsItem(news.get(getAdapterPosition()).getId());
                    return true;
                }
            });
        }
    }
}
