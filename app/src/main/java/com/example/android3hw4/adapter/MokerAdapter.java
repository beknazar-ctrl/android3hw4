package com.example.android3hw4.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3hw4.data.MokerModel;
import com.example.android3hw4.databinding.ItemListBinding;
import com.example.android3hw4.framework.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.Callback;

import retrofit2.Call;
import retrofit2.Response;

public class MokerAdapter extends RecyclerView.Adapter<MokerAdapter.ViewHolder> {

    private final List<MokerModel> mokerAdapterList = new ArrayList<>();
    private MokerAdapter adapter;
    private Callback callback;


    public MokerAdapter(Callback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public MokerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListBinding itemView = ItemListBinding.inflate(LayoutInflater.from(parent.getContext()));

        return new ViewHolder(itemView, callback);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(mokerAdapterList.get(position));

    }

    @Override
    public int getItemCount() {
        return mokerAdapterList.size();
    }

    public void addItems(List<MokerModel> list) {

        mokerAdapterList.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemListBinding itemView;
        private Callback callback;

        public ViewHolder(@NonNull ItemListBinding itemView, Callback callback) {
            super(itemView.getRoot());
            this.callback = callback;
            this.itemView = itemView;

        }

        public void onBind(MokerModel mokerModel) {
            itemView.content.setText(mokerModel.getContent());
            itemView.ongroup.setText(""+mokerModel.getGroup());
            itemView.title.setText(mokerModel.getTitle());
            itemView.user.setText(""+mokerModel.getUser());
            itemView.getRoot().setOnClickListener(v -> {

                callback.Mokerclick(mokerModel);


            });
            itemView.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    AlertDialog.Builder builder =new AlertDialog.Builder(v.getRootView().getContext());
                    builder.setMessage("Do you want to delete?").
                            setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    Toast.makeText(v.getRootView().getContext(), "clicked", Toast.LENGTH_SHORT).show();

                                    RetrofitBuilder.getInstance().deleteById(mokerModel.getId()).enqueue(new retrofit2.Callback<MokerModel>() {
                                        @Override
                                        public void onResponse(Call<MokerModel> call, Response<MokerModel> response) {

                                        }

                                        @Override
                                        public void onFailure(Call<MokerModel> call, Throwable t) {


                                        }

                                    });
                                }

                            })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                            dialog.dismiss();
                        }
                    });
                    builder.create().show();
                    return false;
                }
            });


        }
    }

    public interface Callback {
        void Mokerclick(MokerModel mokerModel);

    }
}
