package myapp.instancepresenterfactory.utils;


import myapp.apt.PresenterFactory;

/**
 * Created by yang2 on 2017/7/4.
 */

public class CreatePresenterUtil {

    public static <T>T getInstance(Class clazz){
        try {
            return (T) PresenterFactory.create(clazz);
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}
