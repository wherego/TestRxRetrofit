package ciom.htf.rxretrofit.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.camnter.easyrecyclerview.widget.EasyRecyclerView;
import com.camnter.easyrecyclerview.widget.decorator.EasyBorderDividerItemDecoration;
import com.htf.rxretrofit.R;
import com.htf.rxretrofit.adapter.MyOnScrollListener;
import com.htf.rxretrofit.adapter.WelfareAdapter;
import com.htf.rxretrofit.base.BaseSwipeRefreshLayoutActivity;
import com.htf.rxretrofit.bean.Beauty;
import com.htf.rxretrofit.iview.IBeautyView;
import com.htf.rxretrofit.presenter.BeautyPresenter;

import java.util.ArrayList;

import butterknife.Bind;

public class MainActivity extends BaseSwipeRefreshLayoutActivity implements WelfareAdapter.OnClickListener, IBeautyView {

    @Bind(R.id.main_rv)
    EasyRecyclerView mainRv;

    private EasyBorderDividerItemDecoration dataDecoration;
    private LinearLayoutManager mLinearLayoutManager;
    private WelfareAdapter welfareAdapter;
    
    private BeautyPresenter beautyPresenter;
    private int emptyCount = 0;//加载返回数据为空后继续加载的次数
    private static final int EMPTY_LIMIT = 5;//数据为空最多加载次数

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        
        dataDecoration = new EasyBorderDividerItemDecoration(
                getResources().getDimensionPixelOffset(R.dimen.data_border_divider_height), //
                getResources().getDimensionPixelOffset(R.dimen.data_border_padding_infra_spans));
        
        mLinearLayoutManager = (LinearLayoutManager) mainRv.getLayoutManager();
        mainRv.addItemDecoration(dataDecoration);
        mActionBarHelper.setDrawerTitle(this.getResources().getString(R.string.app_menu));
    }

    @Override
    protected void initData() {
        beautyPresenter = new BeautyPresenter();
        beautyPresenter.attachView(this);
        welfareAdapter = new WelfareAdapter(this);
        welfareAdapter.setListener(this);
        mainRv.setLayoutManager(mLinearLayoutManager);
        mainRv.setAdapter(welfareAdapter);
        
        setRefreshStatus(true);//
        refresh(true);
        beautyPresenter.getData(true);
    }

    @Override
    protected void initListeners() {
        mainRv.addOnScrollListener(getRecyclerViewOnScrollListener());
        welfareAdapter.setOnItemClickListener((view, position) -> {

        });
    }

    @Override
    public void onSwipeRefresh() {
        beautyPresenter.getData(true);
        refresh(true);
    }

    private void loadMoreRequest() {
        if (this.emptyCount >= EMPTY_LIMIT) {
            this.showToast(MainActivity.this.getString(R.string.main_empty_data),
                    Toast.LENGTH_LONG);
            return;
        }

        setRefreshStatus(false);//
        beautyPresenter.getData(false);
        refresh(true);
    }

    @Override
    public void onClickPicture(String url, String title, View view) {
        PictureActivity.startActivityByActivityOptionsCompat(this, url, title, view);
    }

    @Override
    public void onBeautySuccess(ArrayList<Beauty> beauties, boolean refresh) {
        
        if(refresh) {
            emptyCount = 0;
            welfareAdapter.clear();
            welfareAdapter.setList(beauties);
        } else {
            welfareAdapter.addAll(beauties);
        }
        welfareAdapter.notifyDataSetChanged();
        refresh(false);
        if (beauties.size() == 0) this.emptyCount++;
    }

    @Override
    public void onFailure(Throwable e) {
        showToast(e.getMessage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        beautyPresenter.detachView();
    }

    /**
     * LinearLayoutManager 时的滚动监听
     *
     * @return RecyclerView.OnScrollListener
     */
    public RecyclerView.OnScrollListener getRecyclerViewOnScrollListener() {
        return new MyOnScrollListener(() -> {
            loadMoreRequest();
        });
    }
}
