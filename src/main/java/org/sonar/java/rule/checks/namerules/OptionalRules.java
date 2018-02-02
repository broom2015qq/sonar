package org.sonar.java.rule.checks.namerules;

import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.MethodTree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 继承了父类BaseTreeVisitor，实现了JavaFileScanner接口
 */
public class OptionalRules extends BaseTreeVisitor implements JavaFileScanner {

    /**
     * 文本式读取被扫描java文件
     * @param context
     */
    @Override
    public void scanFile(JavaFileScannerContext context) {
        scan(context.getTree());
        visitFile(context.getFile());
    }

    private void visitFile(File file) {
        //直接获取当前java文件的所有文本
        List<String> lines = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null){
                lines.add(line);
            }

        }catch (IOException  e){
            throw new IllegalStateException(e);
        }
    }

}
