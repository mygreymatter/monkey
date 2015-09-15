package com.mayo.godko.shop;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mayo.godko.Callbacks;
import com.mayo.godko.Monkey;
import com.mayo.godko.R;
import com.mayo.godko.RobotoView;

/**
 * Created by mayo on 2/9/15.
 */
public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {

    private final Monkey monkey;
    private final String mIndianCurrencySymbol;
    private Callbacks.MenuIconListener mMenuIconListener;

    public ShopAdapter(Context context) {
        monkey = Monkey.getMonkey();
        mIndianCurrencySymbol = context.getResources().getString(R.string.indian_rupee_symbol);
        monkey.nShopped = new ArrayMap<>();
        mMenuIconListener = (Callbacks.MenuIconListener) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.r_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final Shop shop = getItem(i);
        viewHolder.name.setText(shop.name);
        viewHolder.measurement.setText(shop.measurement);
        viewHolder.price.setText(mIndianCurrencySymbol + " " + shop.price);

        int resource = -1;
        switch (i) {
            case 0:
                resource = R.drawable.item_1;
                break;
            case 1:
                resource = R.drawable.item_2;
                break;
            case 2:
                resource = R.drawable.item_3;
                break;
            case 3:
                resource = R.drawable.item_4;
                break;
            case 4:
                resource = R.drawable.item_5;
                break;
        }
        viewHolder.image.setImageResource(resource);

        viewHolder.quantity.setText(Integer.toString(shop.quantity));
        viewHolder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++shop.quantity;
                viewHolder.quantity.setText(Integer.toString(shop.quantity));
                monkey.nShopped.put(shop.name, shop.quantity);

                setQuantity();
            }
        });

        viewHolder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int i = Integer.parseInt(viewHolder.quantity.getText().toString());
                if (shop.quantity > 0) {
                    --shop.quantity;
                    viewHolder.quantity.setText(Integer.toString(shop.quantity));
                    if (shop.quantity == 0)
                        monkey.nShopped.remove(shop.name);
                    else
                        monkey.nShopped.put(shop.name, shop.quantity);
                }

                setQuantity();
            }
        });

    }

    private void setQuantity(){
        if(monkey.nShopped.size() > 0)
            mMenuIconListener.setMenuIcon(1);
        else
            mMenuIconListener.setMenuIcon(0);

    }

    private Shop getItem(int pos) {
        return monkey.nShopping.get(pos);
    }

    @Override
    public int getItemCount() {
        return monkey.nShopping.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final RobotoView name;
        private final RobotoView measurement;
        private final RobotoView price;
        private final RobotoView quantity;
        private final RobotoView plus;
        private final RobotoView minus;
        private ImageView image;

        public ViewHolder(View view) {
            super(view);

            name = (RobotoView) view.findViewById(R.id.name);
            measurement = (RobotoView) view.findViewById(R.id.measurement);
            price = (RobotoView) view.findViewById(R.id.price);
            quantity = (RobotoView) view.findViewById(R.id.quantity);
            plus = (RobotoView) view.findViewById(R.id.plus);
            minus = (RobotoView) view.findViewById(R.id.minus);
            image = (ImageView) view.findViewById(R.id.image);
        }
    }

}
