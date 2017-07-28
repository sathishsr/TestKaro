package com.cashkaro.cashkaro.Home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cashkaro.cashkaro.R;
import com.cashkaro.cashkaro.adapter.SectionedHeaderAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by SR on 28/07/17.
 */

public class HomeFragment extends Fragment {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    private SectionedHeaderAdapter<String, String> sectionAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    /*private void setUpAdapter() {

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(mLayoutManager);


        sectionAdapter = new SectionedHeaderAdapter<>(getActivity(),
                new LinkedHashMap<String, List<String>>());
        int[] rowiewIDs = {R.id.simpleTextView, R.id.icon_txt, R.id.frameBottomList};//4-6

        int[] headerViewIDs = {R.id.header};

        sectionAdapter.setLayoutTextViews(R.layout.item_bottom_sheet, R.layout.item_bottom_sheet,
                headerViewIDs, rowiewIDs);

        sectionAdapter.setPopulationListener(new SectionedHeaderAdapter.SectionedPopulationListener<String, MenuDetails>()

                                             {

                                                 @Override
                                                 public void populateFromHeader(View view, int position, String menuDetails, View[] views) {

                                                     ((TextView) views[0]).setText(menuDetails);
                                                     ((TextView) views[0]).setTextColor(getColor(R.color.red));
                                                     ((TextView) views[0]).setTextSize(12f);

                                                     views[1].setVisibility(View.GONE);


                                                 }

                                                 @Override
                                                 public void populateFromRow(View view, int position, MenuDetails menuDetails, View[] views) {

                                                     ((TextView) views[0]).setText(menuDetails.description);

                                                     Object[] values = MenuGetter.getValues(menuDetails.menuKey, getContext());

                                                     views[1].setVisibility(View.VISIBLE);
                                                     ((SSTextView) views[1]).setText(values[1].toString());

                                                     views[2].setTag(position);

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
    }*/
    

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
