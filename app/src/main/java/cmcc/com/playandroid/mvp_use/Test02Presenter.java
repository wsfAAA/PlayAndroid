package cmcc.com.playandroid.mvp_use;

import com.live.base.mvp.BasePresenter;
import com.live.base.mvp.InjectModel;

public class Test02Presenter extends BasePresenter<Test02Callback> {

    @InjectModel
    Test02Model test02Model;

    public void getTest() {
        getView().setTest(test02Model.getTest());
    }
}
