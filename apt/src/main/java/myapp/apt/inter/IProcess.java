package myapp.apt.inter;

import javax.annotation.processing.RoundEnvironment;

import myapp.apt.AnnotationProcess;

/**
 * Created by yang2 on 2017/7/4.
 */

public interface IProcess {
    void process(RoundEnvironment roundEnv, AnnotationProcess process);
}
