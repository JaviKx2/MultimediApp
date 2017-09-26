package com.javikx2.multimediapp.view.fragment;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.javikx2.multimediapp.R;
import com.javikx2.multimediapp.api.tviso.dto.catalogue.Result;
import com.javikx2.multimediapp.view.activity.catalogue.CatalogueActivity;
import com.javikx2.multimediapp.view.adapter.CatalogueAdapter;
import com.javikx2.multimediapp.view.listener.CatalogueClickListener;
import com.javikx2.multimediapp.view.navigation.Navigator;

import java.util.ArrayList;
import java.util.List;

public class CatalogueFragment extends Fragment {
    public static final String ARG_PAGE = "arg_page";
    private List<Result> mItems = new ArrayList<>();

    public CatalogueFragment() {
    }

    public static CatalogueFragment newInstance(int pageNumber) {
        CatalogueFragment myFragment = new CatalogueFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARG_PAGE, pageNumber);
        myFragment.setArguments(arguments);
        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        int pageNumber = arguments.getInt(ARG_PAGE);
        selectItems(pageNumber + 1);
        if (!mItems.isEmpty()) {
            return recyclerView();
        } else {
            return defaultTextView();
        }
    }

    private void selectItems(int mediaType){
        mItems.clear();
        mItems.addAll(((CatalogueActivity) getActivity()).getItemsByMediaType(mediaType));
    }

    private RecyclerView recyclerView() {
        RecyclerView recyclerView = new RecyclerView(getActivity());
        CatalogueAdapter mCatalogueAdapter = new CatalogueAdapter(new ArrayList<Result>(), getActivity(), new CatalogueClickListener() {
            @Override
            public void onClickCatalogueItem(Result catalogueItem) {
                Navigator.getInstance().navigateToCatalogueDetail(getActivity(), catalogueItem);
            }
        });
        recyclerView.setAdapter(mCatalogueAdapter);
        mCatalogueAdapter.updateItems(mItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return recyclerView;
    }

    private TextView defaultTextView() {
        TextView tview = new TextView(getActivity());
        tview.setText(R.string.no_content);
        tview.setTextSize(20);
        int dim = (int) getResources().getDimension(R.dimen.activity_horizontal_margin);
        tview.setPadding(dim, dim, dim , dim);
        tview.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        return tview;
    }
}
