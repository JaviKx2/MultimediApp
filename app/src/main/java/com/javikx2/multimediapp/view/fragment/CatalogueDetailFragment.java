package com.javikx2.multimediapp.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluejamesbond.text.DocumentView;
import com.bluejamesbond.text.style.TextAlignment;
import com.javikx2.multimediapp.view.activity.catalogue.CatalogueDetailActivity;

public class CatalogueDetailFragment extends Fragment {
    public static final String ARG_PAGE = "arg_page";

    public CatalogueDetailFragment() {
    }

    public static CatalogueDetailFragment newInstance(int pageNumber) {
        CatalogueDetailFragment myFragment = new CatalogueDetailFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARG_PAGE, pageNumber + 1);
        myFragment.setArguments(arguments);
        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        int pageNumber = arguments.getInt(ARG_PAGE);
        View view = new View(getActivity());

        switch (pageNumber) {
            case 1:
                DocumentView tview = new DocumentView(getActivity(), DocumentView.PLAIN_TEXT);
                tview.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
                tview.getDocumentLayoutParams().setInsetPaddingTop(20.0f);
                tview.getDocumentLayoutParams().setInsetPaddingLeft(20.0f);
                tview.getDocumentLayoutParams().setInsetPaddingRight(20.0f);
                tview.setText(((CatalogueDetailActivity) getActivity()).getItem().getShortPlot());
                view = tview;
                break;
        }
        return view;
    }
}

