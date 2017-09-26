package com.javikx2.multimediapp.view.activity.news;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.javikx2.multimediapp.R;
import com.javikx2.multimediapp.api.tviso.dto.latestnews.Result;
import com.javikx2.multimediapp.db.manager.NewsDbManager;
import com.javikx2.multimediapp.api.tviso.TvisoAPIUtils;
import com.javikx2.multimediapp.view.activity.BaseActivity;
import com.squareup.picasso.Picasso;

import butterknife.Bind;

public class NewsDetailActivity extends BaseActivity {
    private static final String INTENT_EXTRA_NEWS_TITLE = "com.javikx2.INTENT_NEWS_TITLE";
    private static final String INTENT_EXTRA_NEWS_TEXT = "com.javikx2.INTENT_NEWS_TEXT";
    private static final String INTENT_EXTRA_NEWS_IMGNOTICE = "com.javikx2.INTENT_NEWS_IMGNOTICE";
    private static final String INTENT_EXTRA_NEWS_IMGCOUNTRY = "com.javikx2.INTENT_NEWS_IMGCOUNTRY";
    private static final String INTENT_EXTRA_NEWS_SOURCE = "com.javikx2.INTENT_NEWS_SOURCE";

    private String mSourceURL;
    private String mTitle;
    private String mText;
    private String mImgURL;

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.news_detail_img)
    ImageView img_view;
    @Bind(R.id.news_detail_title)
    TextView title_view;
    @Bind(R.id.news_detail_desc)
    TextView text_view;
    @Bind(R.id.news_detail_source)
    TextView source_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBar();
        handleCallingIntent();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_news_detail;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_news_detail, menu);
        MenuItem item = menu.findItem(R.id.menu_item_share);
        ShareActionProvider mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        mShareActionProvider.setShareIntent(getIntentForSharingContent());
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_fav:
                saveNewsItem();
                Toast.makeText(this, R.string.txt_save_into_favs, Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveNewsItem() {
        new NewsDbManager(this).insert(mTitle, mText, mImgURL, mSourceURL);
    }

    private void setActionBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar;
        if ((actionBar = getSupportActionBar()) != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void handleCallingIntent() {
        Intent intent = getIntent();
        mTitle = intent.getStringExtra(INTENT_EXTRA_NEWS_TITLE);
        mText = intent.getStringExtra(INTENT_EXTRA_NEWS_TEXT);
        String mImgValue = intent.getStringExtra(INTENT_EXTRA_NEWS_IMGNOTICE);
        String mImgCountry = intent.getStringExtra(INTENT_EXTRA_NEWS_IMGCOUNTRY);
        mSourceURL = intent.getStringExtra(INTENT_EXTRA_NEWS_SOURCE);
        SpannableString url_underlined = new SpannableString(mSourceURL);
        url_underlined.setSpan(new UnderlineSpan(), 0, url_underlined.length(), 0);
        if (mImgValue != null && mImgCountry != null) {
            String mImgSize = "w600";
            mImgURL = TvisoAPIUtils.getNewsItemImgURL(mImgValue, mImgCountry, mImgSize);
            Picasso.with(this)
                    .load(mImgURL)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(img_view);
        }
        title_view.setText(Html.fromHtml(mTitle));
        text_view.setText(Html.fromHtml(mText));
        source_view.setText(url_underlined);
        source_view.setTextColor(getResources().getColor(android.R.color.holo_blue_bright));
        source_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIntentForOpeningAnURL(mSourceURL);
            }
        });
    }

    private void setIntentForOpeningAnURL(String url) {
        if (!url.startsWith("https://") && !url.startsWith("http://")) {
            url = "http://" + url;
        }
        Intent openUrlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(openUrlIntent);
    }

    private Intent getIntentForSharingContent() {
        String texttosend;
        texttosend = mTitle + "\n";
        texttosend += mText + "\n";
        texttosend += mSourceURL;
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, texttosend);
        return intent;
    }

    public static Intent getCallingIntent(Context context, Result newsItem) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra(INTENT_EXTRA_NEWS_TITLE, newsItem.getTitle());
        intent.putExtra(INTENT_EXTRA_NEWS_TEXT, newsItem.getShortText());
        if (newsItem.getImage() != null) {
            intent.putExtra(INTENT_EXTRA_NEWS_IMGNOTICE, newsItem.getImage().getNotice());
            intent.putExtra(INTENT_EXTRA_NEWS_IMGCOUNTRY, newsItem.getImage().getCountry());
        } else {
            intent.putExtra(INTENT_EXTRA_NEWS_IMGNOTICE, "");
            intent.putExtra(INTENT_EXTRA_NEWS_IMGCOUNTRY, "");
        }
        intent.putExtra(INTENT_EXTRA_NEWS_SOURCE, newsItem.getSource());
        return intent;
    }
}
