package myapp.instancepresenterfactory;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.lang.reflect.ParameterizedType;

import myapp.instancepresenterfactory.utils.CreatePresenterUtil;

/**
 * Created by yang2 on 2017/7/4.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    protected P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        _injectPresenter();
        initView();
    }

    protected abstract void initView() ;

    private void _injectPresenter() {
        if (this.getClass().getGenericSuperclass() instanceof ParameterizedType
                &&((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments().length>0){
            Class mPresenterClass = (Class) ((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments()[0];
            mPresenter = CreatePresenterUtil.getInstance(mPresenterClass);
            mPresenter.setView(this);
        }
    }

    protected abstract int getLayoutId();
}
