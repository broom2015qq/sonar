package org.sonar.java.rule.checks;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

/**
 * Created by huqingen on 2017/3/16.
 */
public class MyFirstCustomCheckTest1 {
    @Test
    public void test() {
        JavaCheckVerifier.verify("src/test/files/MyFirstCustomCheck.java", new MyFirstCustomCheck());
    }
}
