package com.cashkaro.cashkaro.Home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cashkaro.cashkaro.R;
import com.cashkaro.cashkaro.adapter.SectionedHeaderAdapter;
import com.cashkaro.cashkaro.model.Offers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.cashkaro.cashkaro.utils.Constants.descriptions;
import static com.cashkaro.cashkaro.utils.Constants.header;
import static com.cashkaro.cashkaro.utils.Constants.title;

/**
 * Created by SR on 28/07/17.
 */

public class HomeFragment extends Fragment {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    LinkedHashMap<String, List<Offers>> dashList = new LinkedHashMap<>();
    Unbinder unbinder;
    private SectionedHeaderAdapter<String, Offers> sectionAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);

        setUpAdapter();
        loadOffers();

        return view;
    }


    private void setUpAdapter() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(recyclerView.getContext(), 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0 || position == 3 || position == 6)
                    return 2;
                else
                    return 1;

            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setLayoutManager(gridLayoutManager);

        sectionAdapter = new SectionedHeaderAdapter<>(getActivity(),
                new LinkedHashMap<String, List<Offers>>());
        int[] rowiewIDs = {R.id.title, R.id.description, R.id.offerText, R.id.getOffer};

        int[] headerViewIDs = {R.id.header};

        sectionAdapter.setLayoutTextViews(R.layout.item_list_header, R.layout.item_list_row_dashboard,
                headerViewIDs, rowiewIDs);

        sectionAdapter.setPopulationListener(new SectionedHeaderAdapter.SectionedPopulationListener<String, Offers>()

                                             {

                                                 @Override
                                                 public void populateFromHeader(View view, int position, String s, View[] views) {

                                                     ((TextView) views[0]).setText(s);


                                                 }

                                                 @Override
                                                 public void populateFromRow(View view, int position, Offers offers, View[] views) {

                                                     ((TextView) views[0]).setText(offers.getTitle());
                                                     ((TextView) views[1]).setText(offers.getDescription());
                                                     ((TextView) views[2]).setText(offers.getExtraOffer());

                                                 }

                                                 @Override
                                                 public void onHeaderCreate(View[] views) {

                                                 }

                                                 @Override
                                                 public void onRowCreate(View[] views) {


                                                 }
                                             }

        );

        recyclerView.setAdapter(sectionAdapter);
    }

    private void loadOffers() {

        final List<Offers> offersList = new ArrayList<>();
        for (int count = 0; count < 2; count++) {
            Offers offers = new Offers();
            offers.setDescription(descriptions[count]);
            offers.setTitle(title[count]);
            offers.setExtraOffer("+ 7.5% Extra \n Cash-back");
            offersList.add(offers);

        }
        for (String s : header)
            dashList.put(s, offersList);

        sectionAdapter.addAll(dashList);
        sectionAdapter.notifyDataSetChanged();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
