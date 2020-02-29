package com.mobquid.iitdo.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.mobquid.iitdo.R;
import com.mobquid.iitdo.models.StaticData;
import com.mobquid.iitdo.models.StaticDataResponse;
import com.mobquid.iitdo.retrofit.Api;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class JoinAboutFragment extends Fragment {

    @BindView(R.id.fragment_join_about_tv)
    TextView fragmentJoinAboutTv;
    List<StaticData> staticData;
    Unbinder unbinder;

    public JoinAboutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_join_about, container, false);
        unbinder = ButterKnife.bind(this, view);

//        fragmentJoinAboutWv.loadUrl("https://cybittech.com/clients/join_about.html");
//        fragmentJoinAboutWv.getSettings().setJavaScriptEnabled(true);
//        fragmentJoinAboutWv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//        fragmentJoinAboutWv.setWebViewClient(new HelloWebViewClient());
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

    public void fetchDataFromServer(String name) {
        Api.getClient().getStaticData(name, new Callback<StaticDataResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void success(StaticDataResponse staticDataResponse, Response response) {
                staticData = staticDataResponse.getStaticDataResponses();
                fragmentJoinAboutTv.setText(staticData.get(0).getDescription());
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    fragmentJoinAboutTv.setText(Html.fromHtml(staticData.get(0).getDescription(),Html.FROM_HTML_MODE_COMPACT));
                    fragmentJoinAboutTv.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
                } else {
                    fragmentJoinAboutTv.setText(Html.fromHtml(staticData.get(0).getDescription()));
                }
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });
    }

}
