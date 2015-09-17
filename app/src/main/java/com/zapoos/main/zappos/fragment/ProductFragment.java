package com.zapoos.main.zappos.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zapoos.main.zappos.R;
import com.zapoos.main.zappos.adapter.ProductAdapter;
import com.zapoos.main.zappos.model.Product;
import com.zapoos.main.zappos.model.Response;
import com.zapoos.main.zappos.model.SearchResponse;
import com.zapoos.main.zappos.task.LoadUrlTask;
import com.zapoos.main.zappos.view.CustomEditText;
import com.zapoos.main.zappos.view.MaterialProgressBar;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;


public class ProductFragment extends Fragment implements CustomEditText.OnActionListener, LoadUrlTask.ResponseCallback {

    private static String TAG = ProductFragment.class.getSimpleName();

    private RecyclerView recyclerView;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private ProductAdapter adapter;
    private View containerView;
    private ProductFragmentListener drawerListener;
    private CustomEditText searchText;
    private ArrayList<Product> products = new ArrayList<>();
    private MaterialProgressBar progressBar;

    public ProductFragment() {

    }

    public void setDrawerListener(ProductFragmentListener listener) {
        this.drawerListener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_main, container, false);
        searchText = (CustomEditText) layout.findViewById(R.id.search_text);
        progressBar = (MaterialProgressBar) layout.findViewById(R.id.progress_bar);
        searchText.setOnActionListener(this);

        adapter = new ProductAdapter(getActivity(), products);
        recyclerView = (RecyclerView) layout.findViewById(R.id.product_list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                DetailsFragment detailsFragment = new DetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("asin", products.get(position).getAsin());
                bundle.putString("imageUrl", products.get(position).getImageUrl());
                detailsFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.container, detailsFragment).addToBackStack(null).commit();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void refresh() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onAction() {
        if (!searchText.getText().toString().isEmpty()) {
            try {
                progressBar.setVisibility(View.VISIBLE);
                new LoadUrlTask(this, getActivity(), SearchResponse.class).execute("https://zappos.amazon.com/mobileapi/v1/search?term=" +
                        URLEncoder.encode(searchText.getText().toString(), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onResponse(Response response) {
        SearchResponse searchResponse = (SearchResponse) response;
        progressBar.setVisibility(View.GONE);
        if (searchResponse != null && searchResponse.getResults() != null) {
            products.clear();
            products.addAll(searchResponse.getResults());
            adapter.notifyDataSetChanged();
        }
    }

    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }
    }

    public interface ProductFragmentListener {
        public void onDrawerItemSelected(Product position);
    }

    public boolean isOpen() {
        return mDrawerLayout.isDrawerOpen(Gravity.LEFT);
    }

    public void close() {
        mDrawerLayout.closeDrawer(Gravity.LEFT);
    }
}