package com.github.shipengyan.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-09-25 17:26
 * @since 1.0
 */
@Slf4j
public class ProcessUtilTest {

    @Test
    public void run16() throws Exception {

        ProcessUtil util = new ProcessUtil();

        String[] args = {""};

        Process process = util.startNewJavaProcess("", "com.github.shipengyan.framework.util.ProcessUtilTest.A", args);

        System.in.read();
    }


    @Test
    public void run33() throws Exception {
        int ret = startJvm(A.class);
        log.info("ret={}", ret);
    }


    public int startJvm(Class klass) throws IOException, InterruptedException {
        String javaHome  = System.getProperty("java.home");
        String javaBin   = javaHome + File.separator + "bin" + File.separator + "java";
        String classpath = System.getProperty("java.class.path");
        String className = klass.getCanonicalName();

        ProcessBuilder builder = new ProcessBuilder(javaBin + ".exe", "-cp", classpath, className);

        Process process = builder.start();
        process.waitFor();
        return process.exitValue();
    }


    public static class A {

        public static void main(String[] args) throws InterruptedException {
            for (int i = 0; i < 1000; i++) {
                System.out.println(i);
                Thread.sleep(100);
            }
            Thread.sleep(60 * 1000);
        }
    }
}
