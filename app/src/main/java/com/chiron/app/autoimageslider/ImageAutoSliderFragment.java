package com.chiron.app.autoimageslider;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class ImageAutoSliderFragment extends Fragment {
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private Unbinder unbinder;
    // TODO: Rename parameter arguments, choose names that match

    public ImageAutoSliderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_image_auto_slider, container, false);
        View view = inflater.inflate(R.layout.fragment_image_auto_slider, container, false);
        unbinder = ButterKnife.bind(this, view);

//        setTitle(R.string.app_name_default);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initImageSlider(view);
    }

    private List<ImageSlider> getImageList(){
        List<ImageSlider> imageList = new ArrayList<>();
        imageList.add(new ImageSlider("Logo",R.mipmap.ic_launcher));
        imageList.add(new ImageSlider("Steve aoki",R.mipmap.ic_launcher));
        imageList.add(new ImageSlider("Dancellenium",R.mipmap.ic_launcher));
        imageList.add(new ImageSlider("Henry",R.mipmap.ic_launcher));
        return imageList;
    }

    int currentPage = 0;
    int NUM_PAGES = 0;

    private void initImageSlider(View view){

        //Set the pager with an adapter
        viewPager.setAdapter(new ImageSliderAdapter(getActivity(),getImageList()));

        CirclePageIndicator indicator = (CirclePageIndicator)
                view.findViewById(R.id.indicator);

        indicator.setViewPager(viewPager);

        final float density = getResources().getDisplayMetrics().density;

        //Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES =getImageList().size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
