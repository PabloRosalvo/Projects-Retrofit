package com.example.android.requestapi.RequestAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.android.requestapi.Activity.DetailActivity;
import com.example.android.requestapi.Model.PojoRequest;
import com.example.android.requestapi.R;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterRequest extends RecyclerView.Adapter<AdapterRequest.ViewHolder> {

    ArrayList<PojoRequest> listaResquet;
    Context context;

    public AdapterRequest(ArrayList<PojoRequest> listaResquet, Context context) {
        this.listaResquet = listaResquet;
        this.context = context;
    }


    @Override
    public AdapterRequest.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_request, parent, false);
        return new AdapterRequest.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterRequest.ViewHolder holder, int position) {

        holder.textFirsName.setText(listaResquet.get(position).getFirst_name());
        holder.textLastname.setText(listaResquet.get(position).getLast_name());

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(listaResquet.get(position).getAvatar())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.imageViewR);

    }


    @Override
    public int getItemCount() {
        return listaResquet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textFirsName;
        TextView textLastname;
        ImageView imageViewR;

        public ViewHolder(View itemView) {
            super(itemView);

            textFirsName = itemView.findViewById(R.id.first_name);
            textLastname = itemView.findViewById(R.id.last_name);
            imageViewR = itemView.findViewById(R.id.imageViewR);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        PojoRequest clickedDataItem = listaResquet.get(pos);
                        Intent intent = new Intent(context, DetailActivity.class);

                        intent.putExtra("first_name", listaResquet.get(pos).getFirst_name());
                        intent.putExtra("last_name", listaResquet.get(pos).getLast_name());
                        intent.putExtra("avatar", listaResquet.get(pos).getAvatar());

                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getFirst_name(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
