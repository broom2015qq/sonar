package org.sonar.java.rule.checks.namerules;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;
import org.sonar.plugins.java.api.JavaFileScanner;

/**
 * Created by broom on 2018/01/22.
 */
public class ForceJavadocTest {
    @Test
    public void test() {
        JavaCheckVerifier.verify("src/test/files/HiClass.java", new ForceJavadoc());
    }

    @Test
    public void test1() {
        JavaCheckVerifier.verify("src/test/files/MyNameIsAbstract.java", new ForceJavadoc());
    }

}
