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

public class TwitterFeedsFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.twitter_feeds_wv)
    WebView twitterFeedsWv;

    public TwitterFeedsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_twitter_feeds, container, false);
        unbinder = ButterKnife.bind(this, view);
        twitterFeedsWv.loadUrl("file:///android_asset/twitterfeed.html");
        twitterFeedsWv.getSettings().setJavaScriptEnabled(true);
        twitterFeedsWv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        twitterFeedsWv.setWebViewClient(new HelloWebViewClient());

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
