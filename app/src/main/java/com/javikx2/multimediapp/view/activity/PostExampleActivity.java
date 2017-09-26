package com.javikx2.multimediapp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.javikx2.multimediapp.R;
import com.javikx2.multimediapp.api.jsonplaceholder.APIClient;
import com.javikx2.multimediapp.api.jsonplaceholder.dto.Album;
import com.javikx2.multimediapp.api.jsonplaceholder.dto.AlbumPostResponse;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostExampleActivity extends BaseActivity {
    @Bind(R.id.edittext_album)
    EditText editTextAlbum;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAlbum();
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_post_example;
    }


    private void createAlbum(){
        Album album = new Album(editTextAlbum.getText().toString());
        APIClient.getInstance().createAlbum(album, new Callback<AlbumPostResponse>() {
            @Override
            public void onResponse(Call<AlbumPostResponse> call, Response<AlbumPostResponse> response) {
                String msg = getString(R.string.msg_album_creation) + response.body().getId();
                Toast.makeText(PostExampleActivity.this, msg, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<AlbumPostResponse> call, Throwable t) {
                String msg = getString(R.string.msg_error_post) + t.getMessage();
                Toast.makeText(PostExampleActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        });
    }

    public static Intent getCallingIntent(Context context){
        return new Intent(context, PostExampleActivity.class);
    }
}
