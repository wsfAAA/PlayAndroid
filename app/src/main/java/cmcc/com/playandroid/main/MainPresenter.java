package cmcc.com.playandroid.main;


import playandroid.cmcc.com.baselibrary.basemvp.BasePresenter;

/**
 * Created by wsf on 2018/9/5.
 */

public class MainPresenter extends BasePresenter<MainActivity,MainModel> {


    @Override
    public MainModel creatModel() {
        return new MainModel();
    }
}
