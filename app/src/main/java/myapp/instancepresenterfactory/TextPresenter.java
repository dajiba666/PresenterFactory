package myapp.instancepresenterfactory;

import android.util.Log;

import myapp.apt_lib.apt.InstanceFactory;

/**
 * Created by yang2 on 2017/7/4.
 */
@InstanceFactory
public class TextPresenter extends BasePresenter {
    public TextPresenter(){}

    public void text(String name){

        Log.d(TextPresenter.class.getName(),name);

    }

    @Override
    public void setView(BaseActivity view) {

    }
}
