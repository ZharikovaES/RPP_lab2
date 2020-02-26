package ru.mirea.lab2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterPager extends PagerAdapter {

    private ArrayList<Item> list;
    private Context context;

    public AdapterPager(Context context, ArrayList<Item> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context
               .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       View view = layoutInflater.inflate(R.layout.fragment, container, false);

       ImageView imageView = view.findViewById(R.id.vp_image);
       TextView name = view.findViewById(R.id.vp_name);
       TextView desc = view.findViewById(R.id.vp_desc);

       Picasso.get().load(list.get(position).getImageUrl()).error(R.drawable.icon).fit().centerInside().into(imageView);
       name.setText(list.get(position).getCreator());
       desc.setText(list.get(position).getDescription());

       container.addView(view);
       return view;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }


}
