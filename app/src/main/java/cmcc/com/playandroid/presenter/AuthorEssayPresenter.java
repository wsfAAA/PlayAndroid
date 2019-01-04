package cmcc.com.playandroid.presenter;

import cmcc.com.playandroid.activity.AuthorEssayActivity;
import cmcc.com.playandroid.model.AuthorEssayModel;
import playandroid.cmcc.com.baselibrary.mvp.BasePresenter;

/**
 * Created by wsf on 2019/1/4.
 */

public class AuthorEssayPresenter extends BasePresenter<AuthorEssayActivity, AuthorEssayModel> {
    @Override
    public AuthorEssayModel creatModel() {
        return new AuthorEssayModel();
    }

    public void requestData(String mUrl) {
         mBaseModel.requestData(mUrl);
    }
}
