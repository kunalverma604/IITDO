package com.mobquid.iitdo.fragments;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.barteksc.pdfviewer.PDFView;
import com.mobquid.iitdo.R;

import butterknife.ButterKnife;

public class JoinBrochureFragment extends Fragment {

//    @BindView(R.id.join_pdf_brochure_wv)
//    WebView joinPdfBrochureWv;

    public JoinBrochureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_join_brochure, container, false);
        ButterKnife.bind(this, view);
        // joinPdfBrochureWv.loadUrl("https://cybittech.com/clients/IITDO-Brochure.pdf");
        PDFView  pdfView = view.findViewById(R.id.pdfv);
        pdfView.fromUri(Uri.parse("http://iitdo.org/wp-content/uploads/2016/12/IITDO-Brochure.pdf")).load();

        return view;
    }

}
