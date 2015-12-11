package utils.linde.library.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import utils.linde.library.AppWrapper;

/**
 * Created by 19195 on 2015/12/10.
 *
 * @see android.support.v7.widget.RecyclerView.Adapter
 */
@SuppressWarnings("unused")
public abstract class RecyclerAdapterWrapper<Bean, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter
{
    private final static int VIEW_TYPE_HEADER = 1;
    private final static int VIEW_TYPE_DATA = 2;
    private final static int VIEW_TYPE_FOOT = 3;

    private final List<Bean> dataList;
    private final List<View> headerList;
    private final List<View> footList;

    private int headerPosition;
    private int footPosition;

    public RecyclerAdapterWrapper(List<Bean> dataList)
    {
        this.dataList = dataList == null ? new ArrayList<Bean>() : dataList;
        headerList = new ArrayList<>();
        footList = new ArrayList<>();
    }

    public RecyclerAdapterWrapper(List<Bean> dataList, View headerView, View footView)
    {
        this(dataList);
        if (headerView != null)
        {
            headerList.add(headerView);
        }
        if (footView != null)
        {
            footList.add(footView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        switch (viewType)
        {
            case VIEW_TYPE_HEADER:
                return new RecyclerView.ViewHolder(headerList.get(headerPosition))
                {
                };
            case VIEW_TYPE_FOOT:
                return new RecyclerView.ViewHolder(footList.get(footPosition))
                {
                };
            case VIEW_TYPE_DATA:
                return onCreateViewHolder(AppWrapper.c(), parent);
        }
        return null;
    }

    protected abstract VH onCreateViewHolder(Context context, ViewGroup parent);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        if (position < headerList.size() || position >= (headerList.size() + dataList.size()))
        {
            return;
        }
        //noinspection unchecked
        final VH vh = (VH) holder;
        final int pos = position - headerList.size();
        onBindViewHolder(vh, dataList.get(pos), pos);
    }

    protected abstract void onBindViewHolder(VH vh, Bean bean, int position);

    @Override
    public int getItemViewType(int position)
    {
        if (position < headerList.size())
        {
            headerPosition = position;
            return VIEW_TYPE_HEADER;
        }
        if (position >= headerList.size() + dataList.size())
        {
            footPosition = position - headerList.size() - dataList.size();
            return VIEW_TYPE_FOOT;
        }
        return VIEW_TYPE_DATA;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public int getItemCount()
    {
        return headerList.size() + dataList.size() + footList.size();
    }
}
