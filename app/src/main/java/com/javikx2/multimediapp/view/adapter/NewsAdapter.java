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
import com.javikx2.multimediapp.api.tviso.dto.latestnews.Image;
import com.javikx2.multimediapp.api.tviso.dto.latestnews.Result;
import com.javikx2.multimediapp.view.listener.NewsClickListener;
import com.javikx2.multimediapp.api.tviso.TvisoAPIUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsItemViewHolder> {
    private final NewsClickListener mRecyclerListener;
    private final List<Result> news;

    private Context mContext;

    public NewsAdapter(List<Result> news, Context context,
                       NewsClickListener recyclerClickListener) {
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

    public void updateNews(List<Result> news){
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

        public NewsItemViewHolder(View itemView, final NewsClickListener recyclerClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            bindListener(itemView, recyclerClickListener);
        }

        public void bindNewsItem(Result newsItem) {
            Image img;
            String img_value = "";
            String img_country = "";
            if ((img = newsItem.getImage()) != null) {
                img_value = img.getNotice();
                img_country = img.getCountry();
            }
            String img_size = "w600";
            String img_url = TvisoAPIUtils.getNewsItemImgURL(img_value, img_country, img_size);

            Picasso.with(mContext)
                    .load(img_url)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(mNewsThumbnail);

            mNewsTitle.setText(Html.fromHtml(newsItem.getTitle()));
            mNewsDesc.setText(Html.fromHtml(newsItem.getShortText()));
        }

        private void bindListener(View itemView, final NewsClickListener recyclerClickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recyclerClickListener.onClickNewsItem(news.get(getAdapterPosition()));
                }
            });
        }
    }
}
