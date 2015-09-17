package com.zapoos.main.zappos.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zapoos.main.zappos.R;
import com.zapoos.main.zappos.adapter.ChildAsinsAdapter;
import com.zapoos.main.zappos.model.ChildAsins;
import com.zapoos.main.zappos.model.ProductDetails;
import com.zapoos.main.zappos.model.Response;
import com.zapoos.main.zappos.task.LoadUrlTask;
import com.zapoos.main.zappos.view.MaterialProgressBar;

import java.util.ArrayList;
import java.util.HashMap;


public class DetailsFragment extends Fragment implements LoadUrlTask.ResponseCallback {
    private static final String ARG_PARAM1 = "asin";

    private String asin;
    private MaterialProgressBar progressBar;
    private RecyclerView recyclerView;
    private ChildAsinsAdapter adapter;
    private HashMap<String, ArrayList<ChildAsins>> products = new HashMap<>();
    private ProductDetails details;

    public static DetailsFragment newInstance(String param1) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public DetailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            asin = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_details, container, false);

        progressBar = (MaterialProgressBar) v.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        new LoadUrlTask(this, getActivity(), ProductDetails.class).execute("https://zappos.amazon.com/mobileapi/v1/product/asin/" + asin);

        adapter = new ChildAsinsAdapter(getActivity());
        recyclerView = (RecyclerView) v.findViewById(R.id.product_list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                try {
                    if (details != null) {
                        String link = "http://zappos.com/app/" + details.getAsin();
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("text/plain");
                        i.putExtra(Intent.EXTRA_SUBJECT, "Zappos product");
                        String sAux = "Check out this: \n" + details.getProductName() + "\n\n";
                        sAux = sAux + link + " \n\n";
                        i.putExtra(Intent.EXTRA_TEXT, sAux);
                        startActivity(Intent.createChooser(i, "choose one"));
                    }
                } catch (Exception e) { //e.toString();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResponse(Response response) {
        progressBar.setVisibility(View.GONE);
        if (response != null) {
            details = (ProductDetails) response;
            details.collectChildAsins();
            adapter.setDetails(details);
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getActivity(), "No network connectivity", Toast.LENGTH_SHORT).show();
        }
    }

}
