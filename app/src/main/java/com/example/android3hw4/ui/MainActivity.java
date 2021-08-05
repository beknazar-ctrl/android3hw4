package com.example.android3hw4.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.android3hw4.R;
import com.example.android3hw4.adapter.MokerAdapter;
import com.example.android3hw4.data.MokerModel;
import com.example.android3hw4.databinding.ActivityMainBinding;
import com.example.android3hw4.framework.RetrofitBuilder;
import com.example.android3hw4.ui.activities.SecondActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MokerAdapter.Callback {

    private ActivityMainBinding binding;
    private final MokerAdapter adapter = new MokerAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recyclerV.setAdapter(adapter);

        getPosts();

        opnSeconActivity();
    }

    private void opnSeconActivity() {

        binding.btnCreate.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        });
    }

    private void createPosts(MokerModel mokerModel) {
        mokerModel = new MokerModel("post", "second", 1, 2);

        RetrofitBuilder.getInstance().createMokerModel(mokerModel).enqueue(new Callback<MokerModel>() {
            @Override
            public void onResponse(Call<MokerModel> call, Response<MokerModel> response) {

            }

            @Override
            public void onFailure(Call<MokerModel> call, Throwable t) {

            }
        });
    }

    private void getPosts() {

        RetrofitBuilder.getInstance().getPosts().enqueue(new Callback<List<MokerModel>>() {
            @Override
            public void onResponse(Call<List<MokerModel>> call, Response<List<MokerModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.e("tag", "Success " + response.body());
                    adapter.addItems(response.body());
                } else {
                    Log.e("tag", "error" + response.code());
                }

            }

            @Override
            public void onFailure(Call<List<MokerModel>> call, Throwable t) {

                Log.e("tag", "failure" + t.getLocalizedMessage());
            }
        });

    }

    @Override
    public void Mokerclick(MokerModel mokerModel) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("id",+ mokerModel.getId());
        intent.putExtra("title", mokerModel.getTitle());
        intent.putExtra("content", mokerModel.getContent());
        intent.putExtra("user", + mokerModel.getUser());
        intent.putExtra("group", + mokerModel.getGroup());
        startActivity(intent);

    }
}