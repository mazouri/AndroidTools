package com.mazouri.tools.sample.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mazouri.tools.Tools;
import com.mazouri.tools.sample.listener.IViewItemItemListener;
import com.mazouri.tools.sample.R;
import com.mazouri.tools.sample.model.Category;
import com.mazouri.tools.sample.model.Info;

import java.util.ArrayList;

/**
 * Created by wangdongdong on 17-2-7.
 */

public class MainAdapter extends SectionedRecyclerViewAdapter<MainAdapter.ItemH, MainAdapter.ItemVH> {

    private static final String TAG = MainAdapter.class.getSimpleName();
    private Context mContext;
    private IViewItemItemListener iViewItemListener;
    private ArrayList<Category> categories = new ArrayList<>();

    public MainAdapter(Context context) {
        this.mContext = context;
    }

    protected LayoutInflater getLayoutInflater(){
        return LayoutInflater.from(mContext);
    }

    public void setIViewItemListener(IViewItemItemListener iViewItemListener) {
        this.iViewItemListener = iViewItemListener;
    }

    public void setCategories(ArrayList<Category> categories) {
        Tools.log().d(TAG, "setCategories size: " + categories.size());
        this.categories = categories;
    }

    @Override
    public int getSectionCount() {
        return categories.size();
    }

    @Override
    public int getItemCount(int section) {
        Tools.log().d(TAG, "getItemCount section : " + section);
        if (categories.get(section) == null) {
            return 0;
        }
        return categories.get(section).getInfos().size();
    }

    @Override
    protected ItemH onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.list_item_header, parent, false);
        return new ItemH(view);
    }

    @Override
    protected ItemVH onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.list_item_main, parent, false);
        return new ItemVH(view, iViewItemListener);
    }

    @Override
    public void onBindHeaderViewHolder(ItemH holder, int section) {
        Tools.log().d(TAG, "onBindHeaderViewHolder section:" + section);
        String category = categories.get(section).getCategory();
        holder.title.setText(category);
    }

    @Override
    public void onSectionedBindViewHolder(ItemVH holder, int section, int relativePosition, int absolutePosition) {
        Tools.log().d(TAG, "onSectionedBindViewHolder section:" + section + " position: " + relativePosition);

        for (Info info : categories.get(section).getInfos()) {
            Tools.log().d(TAG,"onSectionedBindViewHolder section : " + info.getTitle() + " : " + info.getResourceId());
        }

        Info info = getCurrentItem(section, relativePosition);
        holder.title.setText(info.getTitle());
        holder.image.setImageDrawable(mContext.getResources().getDrawable(info.getResourceId()));
    }

    public Info getCurrentItem(int section, int position) {
        return categories.get(section).getInfos().get(position);
    }

    public class ItemH extends RecyclerView.ViewHolder {

        final TextView title;

        public ItemH(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }

    public class ItemVH extends RecyclerView.ViewHolder implements View.OnClickListener{

        final RelativeLayout root;
        final ImageView image;
        final TextView title;

        private IViewItemItemListener iViewItemListener;

        public ItemVH(View itemView, IViewItemItemListener iViewItemListener) {
            super(itemView);
            root = (RelativeLayout) itemView.findViewById(R.id.root);
            image = (ImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
            this.iViewItemListener = iViewItemListener;
            root.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (iViewItemListener != null) {
                int[] sectionAndPos = getSectionIndexAndRelativePosition(getLayoutPosition());
//                int count=0;
//                for (int i=0; i <section; i++) {
//                    count += getItemCount(section);
//                }
//                //current position is allitemscount - aboveitemscount - sectionscount
//                int position = getLayoutPosition() - count - (section + 1);
                iViewItemListener.onItemClickListener(v, sectionAndPos[0], sectionAndPos[1]);
            }
        }
    }
}
