package com.mazouri.tools.sample.ui;

import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by wangdongdong on 17-1-11.
 */

public abstract class SectionedRecyclerViewAdapter<H extends RecyclerView.ViewHolder, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected final static int VIEW_TYPE_HEADER = -2;
    protected final static int VIEW_TYPE_ITEM = -1;

    private final ArrayMap<Integer, Integer> mHeaderLocationMap;
    private GridLayoutManager mLayoutManager;
    private ArrayMap<Integer, Integer> mSpanMap;
    private boolean mShowHeadersForEmptySections;

    public SectionedRecyclerViewAdapter() {
        mHeaderLocationMap = new ArrayMap<>();
    }

    public abstract int getSectionCount();

    public abstract int getItemCount(int section);

    protected abstract H  onCreateHeaderViewHolder(ViewGroup parent, int viewType);

    protected abstract VH  onCreateItemViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindHeaderViewHolder(H holder, int section);

    public abstract void onSectionedBindViewHolder(VH holder, int section, int relativePosition, int absolutePosition);

    public final boolean isHeader(int position) {
        return mHeaderLocationMap.get(position) != null;
    }

    /**
     * Instructs the list view adapter to whether show headers for empty sections or not.
     *
     * @param show flag indicating whether headers for empty sections ought to be shown.
     */
    public final void shouldShowHeadersForEmptySections(boolean show) {
        mShowHeadersForEmptySections = show;
    }

    public final void setLayoutManager(@Nullable GridLayoutManager lm) {
        mLayoutManager = lm;
        if (lm == null) return;
        lm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (isHeader(position))
                    return mLayoutManager.getSpanCount();
                final int[] sectionAndPos = getSectionIndexAndRelativePosition(position);
                final int absPos = position - (sectionAndPos[0] + 1);
                return getRowSpan(mLayoutManager.getSpanCount(),
                        sectionAndPos[0], sectionAndPos[1], absPos);
            }
        });
    }

    @SuppressWarnings("UnusedParameters")
    protected int getRowSpan(int fullSpanSize, int section, int relativePosition, int absolutePosition) {
        return 1;
    }

    // returns section along with offsetted position
    public int[] getSectionIndexAndRelativePosition(int itemPosition) {
        synchronized (mHeaderLocationMap) {
            Integer lastSectionIndex = -1;
            for (final Integer sectionIndex : mHeaderLocationMap.keySet()) {
                if (itemPosition > sectionIndex) {
                    lastSectionIndex = sectionIndex;
                } else {
                    break;
                }
            }
            return new int[]{mHeaderLocationMap.get(lastSectionIndex), itemPosition - lastSectionIndex - 1};
        }
    }

    @Override
    public final int getItemCount() {
        int count = 0;
        mHeaderLocationMap.clear();
        for (int s = 0; s < getSectionCount(); s++) {
            int itemCount = getItemCount(s);
            if (mShowHeadersForEmptySections || (itemCount > 0)) {
                mHeaderLocationMap.put(count, s);
                count += itemCount + 1;
            }
        }
        return count;
    }

    /**
     * @hide
     * @deprecated
     */
    @Override
    @Deprecated
    public final int getItemViewType(int position) {
        if (isHeader(position)) {
            return getHeaderViewType(mHeaderLocationMap.get(position));
        } else {
            final int[] sectionAndPos = getSectionIndexAndRelativePosition(position);
            return getItemViewType(sectionAndPos[0],
                    // offset section view positions
                    sectionAndPos[1],
                    position - (sectionAndPos[0] + 1));
        }
    }

    @SuppressWarnings("UnusedParameters")
    @IntRange(from = 0, to = Integer.MAX_VALUE)
    public int getHeaderViewType(int section) {
        //noinspection ResourceType
        return VIEW_TYPE_HEADER;
    }

    @SuppressWarnings("UnusedParameters")
    @IntRange(from = 0, to = Integer.MAX_VALUE)
    public int getItemViewType(int section, int relativePosition, int absolutePosition) {
        //noinspection ResourceType
        return VIEW_TYPE_ITEM;
    }

    /**
     * @hide
     * @deprecated
     */
    @Override
    @Deprecated
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        StaggeredGridLayoutManager.LayoutParams layoutParams = null;
        if (holder.itemView.getLayoutParams() instanceof GridLayoutManager.LayoutParams)
            layoutParams = new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        else if (holder.itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams)
            layoutParams = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
        if (isHeader(position)) {
            if (layoutParams != null) layoutParams.setFullSpan(true);
            onBindHeaderViewHolder((H) holder, mHeaderLocationMap.get(position));
        } else {
            if (layoutParams != null) layoutParams.setFullSpan(false);
            final int[] sectionAndPos = getSectionIndexAndRelativePosition(position);
            final int absPos = position - (sectionAndPos[0] + 1);
            onSectionedBindViewHolder((VH) holder, sectionAndPos[0],
                    // offset section view positions
                    sectionAndPos[1], absPos);
        }
        if (layoutParams != null)
            holder.itemView.setLayoutParams(layoutParams);
    }

    /**
     * @hide
     * @deprecated
     */
    @Deprecated
    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;

        if(viewType == VIEW_TYPE_HEADER){
            viewHolder = onCreateHeaderViewHolder(parent, viewType);
        } else {
            viewHolder = onCreateItemViewHolder(parent, viewType);
        }

        return viewHolder;
    }
}
