# PresenterFactory

## 使用说明
    
### 该框架相对来说有一定局限性，但是胜在使用方便快捷，能再简洁程度上比dagger2上有较大的优势
     
#### 首先定义一个BaseActivity，自定义泛型P
 ```   
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
        //此处获取到PresenterFactiry生成的对象
        if (this.getClass().getGenericSuperclass() instanceof ParameterizedType
                &&((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments().length>0){
            Class mPresenterClass = (Class) ((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments()[0];
            mPresenter = CreatePresenterUtil.getInstance(mPresenterClass);
            mPresenter.setView(this);
        }
    }

    protected abstract int getLayoutId();
}
```

#### 在Presenter中定义注解


```
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
```
## 以后就不需要像dagger2一样每次都需要写那些烦人的配置了~_~
