package com.android.nunuwa_app.MyDpter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.nunuwa_app.ItemsList;
import com.android.nunuwa_app.R;
import com.android.nunuwa_app.item_desc;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by gb on 5/19/19.
 */

public class Recycler_related extends RecyclerView.Adapter<Recycler_related.MyViewHolderItem> {


    private Context aContext;

    private final int SPLASH_TIME_OUT = 900;
    private List<Item_Dter.items_product> aData;
    Boolean LinIsVisible = false;
    AlertDialog.Builder alert;
    Animation zoomOut;

    public Recycler_related(Context aContext, List<Item_Dter.items_product> aData) {
        this.aContext = aContext;
        this.aData = aData;
    }


    @Override

    public Recycler_related.MyViewHolderItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(aContext);
        view = inflater.inflate(R.layout.card_related_item, parent, false);

        alert = new AlertDialog.Builder(aContext);

        return new Recycler_related.MyViewHolderItem(view);
    }


    @Override

    public void onBindViewHolder(final Recycler_related.MyViewHolderItem holder, final int position) {



        //final int imgInt = aData.get(position).getItem_image();
//        holder.datetxt.setText(itemDateStr);

        holder.nametxt.setText(aData.get(position).getItem_name());
        holder.shoptxt.setText(aData.get(position).getItem_date());
        holder.pricetxt.setText(aData.get(position).getItem_price());
       // Picasso.with(aContext).load(imgInt).into(holder.imageItem);

        holder.btnViewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aContext.startActivity(new Intent(aContext, item_desc.class));
            }
        });

        //        Blurry.with(aContext).capture(view).into(holder.imageItem);

//
//        holder.BuyNow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                aContext.startActivity(new Intent(aContext, transaction_ui.class));
//            }
//        });

        LinIsVisible = false;

//        holder.ItemImage2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!LinIsVisible) {
//                    holder.linDesc.setVisibility(View.VISIBLE);
//                    LinIsVisible=true;
//                } else {
//                    holder.linDesc.setVisibility(View.GONE);
//                    LinIsVisible=false;
//                }
//            }
//        });
    }

    @Override

    public int getItemCount() {
        return aData.size();
    }

    public static class MyViewHolderItem extends RecyclerView.ViewHolder {


        TextView nametxt, pricetxt;
        Button btnViewMore;
        TextView shoptxt;
        //LinearLayout linDesc;
        ImageView imageItem;
        ProgressBar rel_item_progress;
        //CardView cardView;
        CardView cardImtem;


        public MyViewHolderItem(View itemView) {
            super(itemView);

            //datetxt = (TextView) itemView.findViewById(R.id.ItemDate);
            //ItemImage2 = (ImageView) itemView.findViewById(R.id.ItemImageRel);
            btnViewMore = (Button) itemView.findViewById(R.id.btnViewMore);
            //rel_item_progress = (ProgressBar) itemView.findViewById(R.id.rel_item_progress);
            nametxt = (TextView) itemView.findViewById(R.id.itemName);
            shoptxt = (TextView) itemView.findViewById(R.id.itemSHop);
            pricetxt = itemView.findViewById(R.id.itemPrice);
            imageItem = (ImageView) itemView.findViewById(R.id.ItemImageRel);

            btnViewMore = (Button) itemView.findViewById(R.id.btnViewMore);
            cardImtem = (CardView) itemView.findViewById(R.id.cardItem);

        }
    }
}
