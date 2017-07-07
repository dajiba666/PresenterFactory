package myapp.instancepresenterfactory.utils;


import myapp.apt.InstanceFactory;

/**
 * Created by yang2 on 2017/7/4.
 */

public class CreatePresenterUtil {

    public static <T>T getInstance(Class clazz){
        try {
            return (T) InstanceFactory.create(clazz);
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}
