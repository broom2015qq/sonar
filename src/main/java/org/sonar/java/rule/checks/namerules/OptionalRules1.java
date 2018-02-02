package org.sonar.java.rule.checks.namerules;

import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.MethodTree;

public class OptionalRules1 extends BaseTreeVisitor implements JavaFileScanner {
    /**
     * sonar树扫描节点,重写visitClass visitMethod方法
     */
    private JavaFileScannerContext context;

    @Override
    public void visitClass(ClassTree tree){
        //类名
        System.out.println(tree.simpleName().name());
        //是否为抽象类
        System.out.println("是否为抽象类"+tree.symbol().isAbstract());
        super.visitClass(tree);
    }

    @Override
    public void visitMethod(MethodTree tree){
        //方法名
        System.out.println(tree.simpleName().name());
        //是否为抽象方法
        System.out.println("是否为抽象方法"+tree.symbol().isAbstract());
        //是否是public方法
        System.out.println("是否为public方法"+tree.symbol().isPublic());
        super.visitMethod(tree);
    }
    public void scanFile(JavaFileScannerContext context){
        this.context = context;
        scan(context.getTree());
    }
}
