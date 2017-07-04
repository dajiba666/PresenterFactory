package myapp.apt;

import com.google.auto.service.AutoService;

import java.util.Collections;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import myapp.apt.process.IntanceProcess;
import myapp.apt_lib.apt.InstanceFactory;

/**
 * Created by yang2 on 2017/7/4.
 */
@AutoService(Processor.class)
@SupportedAnnotationTypes("myapp.apt_lib.apt.InstanceFactory")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class AnnotationProcess extends AbstractProcessor {
    public Filer mFiler;
    public Messager mMessager;
    public Elements mElementUtils;

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        mFiler = processingEnv.getFiler();
        mMessager = processingEnv.getMessager();
        mElementUtils = processingEnv.getElementUtils();
        new IntanceProcess().process(roundEnvironment, this);
        return false;
    }
}
