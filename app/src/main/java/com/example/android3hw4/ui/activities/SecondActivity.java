package com.example.android3hw4.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.android3hw4.R;
import com.example.android3hw4.data.MokerModel;
import com.example.android3hw4.databinding.ActivitySecondBinding;
import com.example.android3hw4.framework.RetrofitBuilder;
import com.example.android3hw4.ui.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {

    ActivitySecondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String getTitle = getIntent().getStringExtra("title");
        String getContent = getIntent().getStringExtra("content");
        String getUser = getIntent().getStringExtra("user");
        String getGroup = getIntent().getStringExtra("group");
        String getId = getIntent().getStringExtra("id");

        binding.editTitle.setText(getTitle);
        binding.editContent.setText(getContent);
        binding.editUser.setText(getUser);
        binding.editGroup.setText(getGroup);

        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RetrofitBuilder.getInstance().createMokerModel(new MokerModel(binding.editTitle.getText().toString().trim(), binding.editContent.getText().toString().trim(),
                        Integer.parseInt(binding.editUser.getText().toString().trim()),Integer.parseInt(binding.editGroup.getText().toString().trim()) )).enqueue(new Callback<MokerModel>() {
                    @Override
                    public void onResponse(Call<MokerModel> call, Response<MokerModel> response) {

                    }

                    @Override
                    public void onFailure(Call<MokerModel> call, Throwable t) {

                    }
                });

                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }

        });

        binding.btnUpgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RetrofitBuilder.getInstance().upgrade(""+getId, new MokerModel(binding.editTitle.getText().toString().trim(), binding.editContent.getText().toString().trim(),
                        Integer.parseInt(binding.editUser.getText().toString().trim()),Integer.parseInt(binding.editGroup.getText().toString().trim()))).enqueue(new Callback<MokerModel>() {
                    @Override
                    public void onResponse(Call<MokerModel> call, Response<MokerModel> response) {

                    }

                    @Override
                    public void onFailure(Call<MokerModel> call, Throwable t) {

                    }
                });

                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}