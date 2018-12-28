package com.industryhit.industryhit.presentation.globalutils.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.industryhit.industryhit.R;
import com.industryhit.industryhit.businesslogic.model.CategoryData;
import com.industryhit.industryhit.presentation.globalutils.constants.GlobalMethods;
import com.industryhit.industryhit.presentation.globalutils.custom.CustomTextView;
import com.industryhit.industryhit.presentation.globalutils.custom.CustomTextViewMedium;

import java.text.Normalizer;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {


    private List<CategoryData> categoryData;
    private Context context;
    CategoryAdapter.OnItemClickListener mItemClickListener;


    public CategoryAdapter(Context context, List<CategoryData> categoryData) {
        this.categoryData = categoryData;
        this.context = context;
    }


    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_data_layout, parent, false);
        return new CategoryAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder holder, int i) {

        try {

            if (GlobalMethods.isNull(categoryData.get(i).getTitle().getRendered())){
                holder.title.setText(Html.fromHtml(categoryData.get(i).getTitle().getRendered()).toString());
            }

            if (GlobalMethods.isNull(categoryData.get(i).getContent().getRendered())){
                holder.content.setText(Html.fromHtml(categoryData.get(i).getContent().getRendered()).toString());
            }
            if (GlobalMethods.isNull(categoryData.get(i).getDate())){
                holder.date.setText(categoryData.get(i).getDate());
            }

            GlobalMethods.loadImage(context, null, "http://www.etelugumovies.com/wp-content/uploads/2015/10/Allu-Arjun-Multi-Starrer-Fi.jpg", holder.img_category, 0);

        } catch (Exception e) {

            Log.e("exception",e.toString());
        }


    }


    @Override
    public int getItemCount() {
        return categoryData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CustomTextViewMedium title;
        private CustomTextView content,date;
        private ImageView img_category;

        //    private CustomTextViewSemiBold name;

        public ViewHolder(View itemView) {
            super(itemView);

            title=(CustomTextViewMedium)itemView.findViewById(R.id.title);
            content=(CustomTextView)itemView.findViewById(R.id.content);
            date=(CustomTextView)itemView.findViewById(R.id.date);
            img_category=(ImageView)itemView.findViewById(R.id.img_category);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
       //     mItemClickListener.onItemClick(v, getAdapterPosition(), categoryData.get(getAdapterPosition()));
        }

    }


    public interface OnItemClickListener {
     //   public void onItemClick(View view, int position, CategoryData product);
    }

    public void SetOnItemClickListener(final CategoryAdapter.OnItemClickListener mItemClickListener) {
      //  this.mItemClickListener = mItemClickListener;
    }
}
