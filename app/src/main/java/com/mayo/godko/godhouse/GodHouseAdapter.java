package com.mayo.godko.godhouse;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mayo.godko.ActGodHouseDetails;
import com.mayo.godko.Monkey;
import com.mayo.godko.R;
import com.mayo.godko.Tags;

/**
 * Created by mayo on 2/9/15.
 */
public class GodHouseAdapter extends RecyclerView.Adapter<GodHouseAdapter.ViewHolder> {

    private final Monkey monkey;
    private final Context mContext;

    public GodHouseAdapter(Context context) {
        monkey = Monkey.getMonkey();
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.r_godhouse, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.name.setText(monkey.nGodHouses.get(i).name);
        int resource = -1;

        switch (i) {
            case 0:
                resource = R.drawable.monkey_1;
                break;
            case 1:
                resource = R.drawable.monkey_2;
                break;
            case 2:
                resource = R.drawable.monkey_3;
                break;
            case 3:
                resource = R.drawable.monkey_4;
                break;
        }

        viewHolder.image.setImageResource(resource);
        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ActGodHouseDetails.class);
                intent.putExtra(Tags.POSITION, i);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    String transitionName = mContext.getResources().getString(R.string.godhouse_image);
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mContext, viewHolder.image, transitionName);
                    ActivityCompat.startActivity((Activity) mContext,intent, options.toBundle());

                } else
                    mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return monkey.nGodHouses.size();
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    private static MyClickListener myClickListener;

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView name;
        private final ImageView image;

        public ViewHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.name);
            image = (ImageView) view.findViewById(R.id.image);


            //view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(v, getAdapterPosition());


        }
    }

    public interface MyClickListener {
        public void onItemClick(View v, int position);
    }
}
