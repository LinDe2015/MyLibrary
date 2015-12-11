package utils.linde.library.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import utils.linde.library.AppWrapper;

/**
 * Created by 19195 on 2015/12/10.
 *
 * @see BaseAdapter To Wrap
 */
@SuppressWarnings("unused")
public abstract class BaseAdapterWrapper<Bean, VH extends RecyclerView.ViewHolder> extends BaseAdapter
{
    private final List<Bean> dataList;

    public BaseAdapterWrapper(List<Bean> dataList)
    {
        this.dataList = dataList == null ? new ArrayList<Bean>() : dataList;
    }

    @Override
    public final int getCount()
    {
        return dataList.size();
    }

    @Override
    public final Bean getItem(int position)
    {
        return dataList.get(position);
    }

    @Override
    public final long getItemId(int position)
    {
        return position;
    }

    @Override
    public final View getView(int position, View convertView, ViewGroup parent)
    {
        final VH vh;
        if (convertView == null)
        {
            vh = onCreateViewHolder(AppWrapper.c(), parent, getItemViewType(position));
            convertView = vh.itemView;
            convertView.setTag(vh);
        } else
        {
            //noinspection unchecked
            vh = (VH) convertView.getTag();
        }
        onBindViewHolder(vh, getItem(position), position);
        return vh.itemView;
    }

    protected abstract VH onCreateViewHolder(Context context, ViewGroup parent, int viewType);

    protected abstract void onBindViewHolder(VH vh, Bean bean, int position);

    public final void resetDataList(List<Bean> dataList)
    {
        this.dataList.clear();
        this.dataList.addAll(dataList == null ? new ArrayList<Bean>() : dataList);
        notifyDataSetChanged();
    }

    public final void addDataList(List<Bean> dataList)
    {
        this.dataList.addAll(dataList == null ? new ArrayList<Bean>() : dataList);
        notifyDataSetChanged();
    }
}
