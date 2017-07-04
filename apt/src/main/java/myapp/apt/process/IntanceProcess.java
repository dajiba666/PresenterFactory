package myapp.apt.process;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic;

import myapp.apt.AnnotationProcess;
import myapp.apt.inter.IProcess;
import myapp.apt.util.Utils;
import myapp.apt_lib.aop.MemoryCache;
import myapp.apt_lib.apt.InstanceFactory;

/**
 * Created by yang2 on 2017/7/4.
 */

public class IntanceProcess implements IProcess {

    private static final String CLASS_NAME = "InstanceFactory";

    private static final String METHOD_NAME = "create";

    @Override
    public void process(RoundEnvironment roundEnv, AnnotationProcess process) {
        TypeSpec.Builder tb = TypeSpec.classBuilder(CLASS_NAME)
                .addJavadoc("@创建presenter工厂类")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL);

        MethodSpec.Builder method = MethodSpec.methodBuilder(METHOD_NAME)
                .addAnnotation(MemoryCache.class)
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(Object.class)
                .addParameter(Class.class, "clazz")
                .addException(IllegalAccessException.class)
                .addException(InstantiationException.class);

        List<ClassName> list = new ArrayList<>();

        CodeBlock.Builder cb = CodeBlock.builder();

        cb.beginControlFlow("switch(clazz.getSimpleName())");

        for (TypeElement typeElement : ElementFilter.typesIn(roundEnv.getElementsAnnotatedWith(InstanceFactory.class))) {

            process.mMessager.printMessage(Diagnostic.Kind.NOTE, "正在创建" + typeElement.toString());

            if (!Utils.isValidClass(process.mMessager, typeElement)) return;

            ClassName className = ClassName.get(typeElement);

            if (list.contains(className)) continue;

            list.add(className);

            cb.addStatement("case $S: return new $T()",typeElement.getSimpleName(),typeElement);

        }

        cb.addStatement("default: return clazz.newInstance()");

        cb.endControlFlow();

        method.addCode(cb.build());

        tb.addMethod(method.build());

        JavaFile javaFile = JavaFile.builder("myapp.apt",tb.build()).build();

        try {
            javaFile.writeTo(process.mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
