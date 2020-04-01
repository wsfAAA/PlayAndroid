package cmcc.com.playandroid.mvp_use;


import com.live.base.mvp.BaseMvpActivity;
import com.live.base.mvp.InjectPresenter;

import cmcc.com.playandroid.databinding.ActivityTest02Binding;

public class Test02Activity extends BaseMvpActivity<ActivityTest02Binding> implements Test02Callback {

    @InjectPresenter
    Test02Presenter test02Presenter;

    @Override
    protected void initView() {
        test02Presenter.getTest();
    }

    @Override
    protected ActivityTest02Binding getViewBinding() {
        return ActivityTest02Binding.inflate(getLayoutInflater());
    }

    @Override
    public void setTest(String message) {
        viewBinding.tvTextOne.setText(message);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
