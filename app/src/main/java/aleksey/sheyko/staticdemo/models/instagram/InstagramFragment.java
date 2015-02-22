package aleksey.sheyko.staticdemo.models.instagram;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import aleksey.sheyko.staticdemo.R;
import aleksey.sheyko.staticdemo.app.adapters.ServiceAdapter;
import aleksey.sheyko.staticdemo.app.database.AccountDataSource;
import aleksey.sheyko.staticdemo.models.Account;

public class InstagramFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_instagram, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button loginButton = (Button) view.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(view);
            }
        });

        // List existing Twitter accounts
        AccountDataSource dataSource = new AccountDataSource(getActivity());
        ArrayList<Account> accounts = dataSource.read("Instagram");

        ServiceAdapter adapter = new ServiceAdapter(
                getActivity(), R.layout.list_item_service, accounts);

        ListView listView = (ListView) view.findViewById(R.id.listview);
        listView.setAdapter(adapter);
    }

    private void signIn(View view) {
        getActivity().getActionBar().hide();

        // Form urls for Instagram authentication
        String authUrlString = Constants.AUTH_URL
                + "?client_id=" + Constants.CLIENT_ID
                + "&redirect_uri=" + Constants.CALLBACK_URL
                + "&response_type=code";

        // Display the webview for user to authenticate
        WebView webView = (WebView) view.findViewById(R.id.webview);
        webView.setVisibility(View.VISIBLE);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setWebViewClient(new WebviewAuthClient(getActivity()));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(authUrlString);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getActionBar().setTitle("Instagram");
    }
}
