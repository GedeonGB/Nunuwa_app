package com.android.nunuwa_app.MyDpter;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.nunuwa_app.ItemsList;
import com.android.nunuwa_app.R;
import com.android.nunuwa_app.item_desc;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by gb on 10/23/18.
 */

public class Recycler_Item_front extends RecyclerView.Adapter<Recycler_Item_front.MyViewHolderItem> {


    private Context aContext;
    private final int SPLASH_TIME_OUT = 900;
    private List<Ct_dpter.items> aData;
    AlertDialog.Builder alert;
    Animation zoomOut;

    public Recycler_Item_front(Context aContext, List<Ct_dpter.items> aData) {
        this.aContext = aContext;
        this.aData = aData;
    }


    @Override
    public Recycler_Item_front.MyViewHolderItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(aContext);
        view = inflater.inflate(R.layout.card_front_list, parent, false);

        alert = new AlertDialog.Builder(aContext);

        return new Recycler_Item_front.MyViewHolderItem(view);
    }


    @Override

    public void onBindViewHolder(final Recycler_Item_front.MyViewHolderItem holder, final int position) {


        final int imgInt = aData.get(position).getCt_item_image();
        //holder.datetxt.setText(itemDateStr);
        holder.shoptxt.setText(aData.get(position).getShort_desc());
        holder.nametxt.setText(aData.get(position).getCt_item_name());
        Picasso.with(aContext).load(imgInt).into(holder.imageItem);

        holder.cardImtem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aContext.startActivity(new Intent(aContext, ItemsList.class));
            }
        });

        holder.btnViewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aContext.startActivity(new Intent(aContext, ItemsList.class));
            }
        });



    }

    @Override

    public int getItemCount() {
        return aData.size();
    }

    public static class MyViewHolderItem extends RecyclerView.ViewHolder {

        //TextView datetxt;
        TextView nametxt;
        TextView shoptxt;
        ImageView imageItem;
        //CardView cardView;
        Button btnViewMore;
        CardView cardImtem;
        ProgressBar rel_item_progress;


        public MyViewHolderItem(View itemView) {
            super(itemView);

//            datetxt = (TextView) itemView.findViewById(R.id.ItemDate);
            btnViewMore = (Button) itemView.findViewById(R.id.btnViewMore);
            nametxt = (TextView) itemView.findViewById(R.id.itemName);
            shoptxt = (TextView) itemView.findViewById(R.id.itemShop);
            imageItem = (ImageView) itemView.findViewById(R.id.ItemImage);
            rel_item_progress = (ProgressBar) itemView.findViewById(R.id.rel_item_progress);
            cardImtem = (CardView) itemView.findViewById(R.id.cardItem);

        }
    }
}



