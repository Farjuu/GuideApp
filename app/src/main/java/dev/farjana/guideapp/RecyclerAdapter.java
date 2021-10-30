package dev.farjana.guideapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapter extends FirebaseRecyclerAdapter<Item, RecyclerAdapter.ViewHolder> {
    Context context;
    List<Item> itemList;
    FirebaseRecyclerOptions<Item> options;


    public RecyclerAdapter(FirebaseRecyclerOptions<Item> options) {
        super(options);
        this.options = options;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int view) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);

        final ViewHolder holder = new ViewHolder(v);
        v.setOnClickListener(view1 -> {
            int position = holder.getAdapterPosition();
           /* Intent intent = new Intent(parent.getContext(), WebActivity.class);
            intent.putExtra("url", options.getSnapshots().get(position).getSiteURl());
            parent.getContext().startActivity(intent);
*/
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(options.getSnapshots().get(position).getSiteURl()));
            intent.setPackage("com.android.vending");
            parent.getContext().startActivity(intent);
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerAdapter.ViewHolder holder, int position, Item item) {
        Glide.with(holder.imageView.getContext()).load(item.getImageUrl()).into(holder.imageView);

        holder.textView.setText(item.getImageName());
        holder.desc.setText(item.getDesc());
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        TextView textView, desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recycler_img);
            textView = itemView.findViewById(R.id.recycler_text);
            desc = itemView.findViewById(R.id.desc);
        }
    }
}

