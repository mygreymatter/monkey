package com.mayo.godko.priest;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mayo.godko.Monkey;
import com.mayo.godko.R;
import com.mayo.godko.RobotoView;
import com.mayo.godko.Tags;

/**
 * Created by mayo on 2/9/15.
 */
public class PriestsAdapter extends RecyclerView.Adapter<PriestsAdapter.ViewHolder> {

    private final Monkey monkey;
    private Context context = null;

    public PriestsAdapter(){
        monkey = Monkey.getMonkey();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.r_priest,parent,false);
        ViewHolder vh = new ViewHolder(v);
        if(null == context)
            context = parent.getContext();

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.name.setText(monkey.nPriests.get(i).name);
        viewHolder.services.setText(monkey.nPriests.get(i).services);
        viewHolder.contact.setText(monkey.nPriests.get(i).contact);

        viewHolder.contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPriest(context,monkey.nPriests.get(i).contact);
            }
        });


        /*int resource = -1;

        switch (i){
            case 0: resource = R.drawable.monkey_1; break;
            case 1: resource = R.drawable.monkey_2; break;
            case 2: resource = R.drawable.monkey_3; break;
            case 3: resource = R.drawable.monkey_4; break;
        }

        viewHolder.mImage.setImageResource(resource);*/

    }

    @Override
    public int getItemCount() {
        return monkey.nPriests.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private final RobotoView name;
        private final RobotoView services;
        private final RobotoView contact;
        private final ImageView mImage;

        public ViewHolder(View view) {
            super(view);

            name = (RobotoView) view.findViewById(R.id.name);
            mImage = (ImageView) view.findViewById(R.id.image);
            services = (RobotoView) view.findViewById(R.id.servicesValues);
            contact = (RobotoView) view.findViewById(R.id.contact);

        }
    }

    public void callPriest(Context context,String contact) {

            try {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + contact));
                context.startActivity(callIntent);
            } catch (ActivityNotFoundException e) {
                Log.i(Tags.LOG,"Call failed: " + e);

            }
    }
}
