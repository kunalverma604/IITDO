package com.mobquid.iitdo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import com.mobquid.iitdo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class JoinPackagesFragment extends Fragment {

    @BindView(R.id.fragment_join_packages_wv)
    WebView fragmentJoinPackageWv;
    Unbinder unbinder;

    public JoinPackagesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_join_packages, container, false);
        unbinder = ButterKnife.bind(this, view);

        fragmentJoinPackageWv.loadUrl("https://cybittech.com/clients/join_packages.html");
        fragmentJoinPackageWv.getSettings().setJavaScriptEnabled(true);
        fragmentJoinPackageWv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        fragmentJoinPackageWv.setWebViewClient(new HelloWebViewClient());
        return view;
    }

    public class HelloWebViewClient extends WebViewClient {
        public HelloWebViewClient() {
            // do nothing
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }


}
