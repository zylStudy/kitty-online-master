package com.guoxiaoxing.kitty.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.guoxiaoxing.kitty.AppConfig;
import com.guoxiaoxing.kitty.R;
import com.guoxiaoxing.kitty.adapter.MainShoppingAdapter;
import com.guoxiaoxing.kitty.bean.SimpleBackPage;
import com.guoxiaoxing.kitty.ui.base.BaseFragment;
import com.guoxiaoxing.kitty.util.UIHelper;
import com.guoxiaoxing.kitty.util.log.Logger;
import com.guoxiaoxing.kitty.widget.banner.ConvenientBanner;
import com.guoxiaoxing.kitty.widget.banner.holder.CBViewHolderCreator;
import com.guoxiaoxing.kitty.widget.banner.holder.LocalImageHolderView;
import com.guoxiaoxing.kitty.widget.banner.listener.OnItemClickListener;
import com.guoxiaoxing.kitty.widget.banner.transforms.CubeOutTransformer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


public class MainShoppingFragment extends BaseFragment implements AdapterView.OnItemClickListener,
        ViewPager.OnPageChangeListener, OnItemClickListener {

    private static final String TAG = "MainShoppingFragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Context mContext;
    private String mParam1;
    private String mParam2;

    @Bind(R.id.tb_main_shopping_fragment)
    Toolbar mToolbar;
    @Bind(R.id.tv_scan)
    TextView mTvScan;
    @Bind(R.id.tv_notification)
    TextView mTvNotification;
    @Bind(R.id.et_search)
    EditText mEtSearch;
    @Bind(R.id.cb_shopping)
    ConvenientBanner mConvenientBanner;
    @Bind(R.id.ctl_layout)
    CollapsingToolbarLayout mCtlLayout;
    @Bind(R.id.tb_content)
    TabLayout mTbContent;
    @Bind(R.id.vp_title)
    ViewPager mVpTitle;

    private OnFragmentInteractionListener mListener;
    MainShoppingAdapter mAdapter = null;
    LinearLayoutManager mLayoutManager;
    int moreNum = 2;
    boolean isDrag = true;

    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    private View headerView;

    public MainShoppingFragment() {
    }

    public static MainShoppingFragment newInstance(String param1, String param2) {
        MainShoppingFragment fragment = new MainShoppingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.t(TAG).d("onCreate()");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Logger.t(TAG).d("onAttach()");
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Logger.t(TAG).d("onViewCreated()");

    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.t(TAG).d("onResume()");
        mConvenientBanner.startTurning(AppConfig.VIEWPAGER_TRANSFORM_TIME);
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.t(TAG).d("onPause()");
        mConvenientBanner.stopTurning();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logger.t(TAG).d("onDestroyView()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Logger.t(TAG).d("onDetach()");
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onItemClick(int position) {

    }


    @Override
    public void onClick(View v) {

        super.onClick(v);

        switch (v.getId()) {
            //消息通知
            case R.id.tv_notification:
                UIHelper.showSimpleBack(getActivity(), SimpleBackPage.SEARCH);
                break;
            //搜索框
            case R.id.et_search:
                UIHelper.showSimpleBack(getActivity(), SimpleBackPage.SEARCH);
                break;
            case R.id.tv_scan:
                UIHelper.showScanActivity(getActivity());
                break;
            default:
                break;

        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_shopping;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
    }

    @Override
    public void initView(View view) {
        Logger.t(TAG).d("onCreateView() -- initView()");
        initToolbar();
        initContentView();
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void setToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onHomeFragmentInteraction(uri);
        }
    }

    public interface OnFragmentInteractionListener {
        void onHomeFragmentInteraction(Uri uri);
    }

    public int getScreenHeight() {
        return getActivity().findViewById(android.R.id.content).getHeight();
    }

    public static int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private void initToolbar() {
        mContext = getActivity();
        mEtSearch.setFocusable(false);
        mTvScan.setOnClickListener(this);
        mTvNotification.setOnClickListener(this);
        mEtSearch.setOnClickListener(this);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initHeaderView() {


        for (int position = 0; position < 7; position++) {
            localImages.add(getResId("ic_test_" + position, R.drawable.class));
        }

        //本地图片例子
        mConvenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, localImages)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
//                .setOnPageChangeListener(this)//监听翻页事件
                .setOnItemClickListener(this);
        mConvenientBanner.getViewPager().setPageTransformer(true, new CubeOutTransformer());
//        mCbHomeAd.setManualPageable(false);//设置不能手动影响

    }

    private void initContentView() {

        initHeaderView();

        //Toolbar
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//使用CollapsingToolbarLayout后，title需要设置到CollapsingToolbarLayout上
//        mCtlLayout.setTitle("失控");

//设置ViewPager
        setupViewPager(mVpTitle);

//给TabLayout增加Tab, 并关联ViewPager
        mTbContent.addTab(mTbContent.newTab().setText("关注"));
        mTbContent.addTab(mTbContent.newTab().setText("热门"));
        mTbContent.addTab(mTbContent.newTab().setText("我配"));
        mTbContent.addTab(mTbContent.newTab().setText("私搭"));
        mTbContent.addTab(mTbContent.newTab().setText("晒货"));
        mTbContent.addTab(mTbContent.newTab().setText("星榜"));
        mTbContent.addTab(mTbContent.newTab().setText("妆呗"));
        mTbContent.addTab(mTbContent.newTab().setText("男票"));
        mTbContent.addTab(mTbContent.newTab().setText("好吃"));
        mTbContent.addTab(mTbContent.newTab().setText("脸赞"));
        mTbContent.setupWithViewPager(mVpTitle);
    }

    private void setupViewPager(ViewPager mViewPager) {
        MyPagerAdapter adapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(DetailFragment.newInstance(""), "关注");
        adapter.addFragment(DetailFragment.newInstance(""), "热门");
        adapter.addFragment(DetailFragment.newInstance(""), "我配");
        adapter.addFragment(DetailFragment.newInstance(""), "私搭");
        adapter.addFragment(DetailFragment.newInstance(""), "晒货");
        adapter.addFragment(DetailFragment.newInstance(""), "星榜");
        adapter.addFragment(DetailFragment.newInstance(""), "妆呗");
        adapter.addFragment(DetailFragment.newInstance(""), "男票");
        adapter.addFragment(DetailFragment.newInstance(""), "好吃");
        adapter.addFragment(DetailFragment.newInstance(""), "脸赞");
        mViewPager.setAdapter(adapter);
    }

    static class MyPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

}