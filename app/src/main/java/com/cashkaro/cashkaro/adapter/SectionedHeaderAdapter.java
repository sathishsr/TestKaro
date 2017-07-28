package com.cashkaro.cashkaro.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SectionedHeaderAdapter<H, V> extends RecyclerView.Adapter<SectionedHeaderAdapter.ViewHolder> {

    public List<SectionOrRow<H, V>> elements = new ArrayList<>();

    public LinkedHashMap<H, List<V>> values = new LinkedHashMap<>();
    private Context mContext;
    private int headerLayoutID = 0;
    private int rowLayoutID = 0;
    private int[] headerViewIds;
    private int[] rowViewIds;
    private int lastSelectedPosition;

    private SectionedPopulationListener<H, V> listener;

    public SectionedHeaderAdapter(Context context, LinkedHashMap<H, List<V>> values) {

        mContext = context;

        this.mContext = context;

        setElements(values);
    }

    public void setElements(LinkedHashMap<H, List<V>> rows) {

        if (rows.size() > 0) {

            Iterator it = rows.entrySet().iterator();

            while (it.hasNext()) {

                Map.Entry<H, List<V>> pair = (Map.Entry<H, List<V>>) it.next();

                H key = pair.getKey();

                List<V> list = pair.getValue();

                values.put(key, list);

                if (null != key && !"".equals(key))
                    elements.add(new SectionOrRow<H, V>(key, null));

                for (V val : list) {
                    elements.add(new SectionOrRow<H, V>(null, val));
                }
            }
        }
    }

    public void setChilds(H key, List<V> list) {

        values.put(key, list);

        LinkedHashMap<H, List<V>> rows = new LinkedHashMap<>();

        rows.putAll(values);

        values.clear();
        elements.clear();
        setElements(rows);
    }


    @Override
    public int getItemViewType(int position) {

        super.getItemViewType(position);

        SectionOrRow<H, V> item = elements.get(position);

        return item.isRow() ? 1 : 0;

    }


    @Override
    public SectionedHeaderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;
        SectionedHeaderAdapter.ViewHolder holder;

        if (view == null) {

            if (viewType == 0) {

                view = LayoutInflater.from(parent.getContext())
                        .inflate(headerLayoutID, parent, false);

                holder = new SectionedHeaderAdapter.ViewHolder(view, headerViewIds);

            } else {

                view = LayoutInflater.from(parent.getContext())
                        .inflate(rowLayoutID, parent, false);

                holder = new SectionedHeaderAdapter.ViewHolder(view, rowViewIds);
            }

            view.setTag(holder);

        } else {

            holder = (SectionedHeaderAdapter.ViewHolder) view.getTag();
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(SectionedHeaderAdapter.ViewHolder holder, int position) {

        holder.mView.setTag(elements.get(position).isRow() ? rowLayoutID : headerLayoutID, position);

        if (listener != null) {

            if (elements.get(position).isRow()) {

                listener.onRowCreate(holder.getViews());

                listener.populateFromRow(holder.mView, position, elements.get(position).getRow(),
                        holder.getViews());

            } else {

                listener.onHeaderCreate(holder.getViews());

                listener.populateFromHeader(holder.mView, position, elements.get(position).getSection(),
                        holder.getViews());
            }
        }

    }


    public void setLayoutTextViews(int headerLayoutID, int rowLayoutID, int[] headerViewIDs, int[] rowViewIDs) {

        this.headerLayoutID = headerLayoutID;

        this.rowLayoutID = rowLayoutID;

        this.headerViewIds = headerViewIDs;

        this.rowViewIds = rowViewIDs;
    }

    public void setLayoutTextViews(int headerLayoutID, int rowLayoutID) {

        this.headerLayoutID = headerLayoutID;

        this.rowLayoutID = rowLayoutID;
    }

    public void clear() {
        values.clear();
        elements.clear();
    }

    public void add(H key, List<V> value) {

        values.put(key, value);

        elements.add(new SectionOrRow<H, V>(key, null));

        for (V val : value) {
            elements.add(new SectionOrRow<H, V>(null, val));
        }
    }

    public void addAll(LinkedHashMap<H, List<V>> rows) {

        setElements(rows);
    }


    public List<V> getChilds(H key) {
        if (values.containsKey(key)) {
            return values.get(key);
        }
        return new ArrayList<>();
    }


    public int getCount() {

        return elements.size();
    }

    public SectionOrRow<H, V> getItem(int position) {

        if (getCount() > 0)
            return elements.get(position);

        return null;
    }

    public List<SectionOrRow<H, V>> getItems() {
        return elements;
    }

    public long getItemId(int position) {
        return position;
    }

    public void setPopulationListener(SectionedPopulationListener<H, V> listener) {

        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    public void setLastSelectedPosition(int position) {
        lastSelectedPosition = position;
    }

    public interface SectionedPopulationListener<H, V> {

        void populateFromHeader(View view, int position, H var3, View[] views);

        void populateFromRow(View view, int var2, V var3, View[] views);

        void onHeaderCreate(View[] views);

        void onRowCreate(View[] views);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        private View[] views;


        public ViewHolder(View view, int[] viewIds) {
            super(view);
            mView = view;
            if (mView != null && viewIds != null) {
                this.views = new View[viewIds.length];

                for (int i = 0; i < viewIds.length; ++i) {
                    this.views[i] = mView.findViewById(viewIds[i]);
                }

            }
        }

        public View[] getViews() {

            return this.views;
        }


    }

}
