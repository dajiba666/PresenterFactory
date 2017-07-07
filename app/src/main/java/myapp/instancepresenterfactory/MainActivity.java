package myapp.instancepresenterfactory;

public class MainActivity extends BaseActivity<TextPresenter> {

    @Override
    protected void initView() {
        mPresenter.text("测试代码");
    }

    @Override
    protected int getLayoutId() {

        return R.layout.activity_main;

    }


}
