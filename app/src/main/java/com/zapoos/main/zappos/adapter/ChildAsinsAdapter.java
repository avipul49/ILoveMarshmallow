package com.zapoos.main.zappos.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zapoos.main.zappos.R;
import com.zapoos.main.zappos.model.ChildAsins;
import com.zapoos.main.zappos.model.ProductDetails;
import com.zapoos.main.zappos.utils.ImageLoaderUtil;

import java.util.ArrayList;
import java.util.List;

public class ChildAsinsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private ProductDetails details;
    private List<String> keys = new ArrayList<>();

    public ChildAsinsAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setDetails(ProductDetails details) {
        this.details = details;
        keys.addAll(details.getMap().keySet());
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? 0 : 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                int layout = R.layout.detail_header;
                View view = inflater.inflate(layout, parent, false);
                return new VHHeader(view);

            case 1:
                layout = R.layout.product_detail_row;
                view = inflater.inflate(layout, parent, false);
                return new MyViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof MyViewHolder) {
            MyViewHolder holder = (MyViewHolder) viewHolder;
            ChildAsins current = details.getMap().get(keys.get(position - 1)).get(0);
            holder.name.setText(current.getColor());
            holder.items.setText(details.getMap().get(keys.get(position - 1)).size() + " items left");
            String price = null;
            if (current.getPrice() == null || current.getPrice().isEmpty()) {
                price = current.getOriginalPrice();
            } else
                price = current.getPrice();
            if (price != null) {
                holder.price.setText("$" + price);
            }
            if (current.getImageUrl() != null) {
                holder.image.setVisibility(View.VISIBLE);
                String imageUrl = current.getImageUrl().replace("160", "450");
                ImageLoaderUtil.displayImage(context, imageUrl, holder.image);
            } else {
                holder.image.setVisibility(View.GONE);
            }
        } else {
            VHHeader holder = (VHHeader) viewHolder;
            holder.name.setText(details.getProductName());
            holder.description.setText(Html.fromHtml(details.getDescription()));
            if (details.getDefaultImageUrl() != null) {
                String imageUrl = details.getDefaultImageUrl().replace("160", "450");
                ImageLoaderUtil.displayImage(context, imageUrl, holder.image);
            }
        }
    }

    @Override
    public int getItemCount() {
        return ((details == null) ? 0 : details.getMap().size() + 1);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;
        TextView price;
        CardView cardView;
        TextView items;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            image = (ImageView) itemView.findViewById(R.id.image);
            price = (TextView) itemView.findViewById(R.id.price);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            items = (TextView) itemView.findViewById(R.id.items);
        }
    }

    class VHHeader extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;
        TextView description;

        public VHHeader(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            image = (ImageView) itemView.findViewById(R.id.image);
            description = (TextView) itemView.findViewById(R.id.description);
        }
    }
}