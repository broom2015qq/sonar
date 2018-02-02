package org.sonar.java.rule.checks.namerules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.SyntaxTrivia;
import org.sonar.plugins.java.api.tree.*;
/**
 * 注释检查
 * 类、类属性、类方法的注释必须使用 Javadoc 规范，使用 /**内容/
 * Created by broom2010 on 2018/1/22.
 */
@Rule(key = "ForceJavadoc")
public class ForceJavadoc extends BaseTreeVisitor implements JavaFileScanner{

    private static final Logger LOGGER = LoggerFactory.getLogger(ForceJavadoc.class);
    private JavaFileScannerContext context;


    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        scan(context.getTree());
    }
    /**
     * 获取类注释
     * @param tree
     * @return
     */
    public void visitClass(ClassTree tree){
        String javadoc = null;
        for(SyntaxTrivia trivia : tree.modifiers().firstToken().trivias()){
            String comment = trivia.comment();
            if(comment != null && comment.trim().startsWith("/*")){
                System.out.println("-----合法的类注释----");
                System.out.println(comment);
                System.out.println("------------------------");
            }
            if(comment != null && comment.trim().startsWith("//")){
                //获取普通单行注释"//"
                System.out.println("-----不合法的类注释----");
                System.out.println(comment);
                System.out.println("------------------------");
                context.reportIssue(this, tree, "类的注释格式要求/**内容*/");

            }
        }
        super.visitClass(tree);
    }

    /**
     * 获取方法注释
     * @param tree
     * @return
     */
    @Override
    public void visitMethod(MethodTree tree){
        String javadoc = null;
        for(SyntaxTrivia trivia : tree.modifiers().firstToken().trivias()){
            String comment = trivia.comment();
            if(comment != null && comment.trim().startsWith("/*")){
                System.out.println("-----合法的类方法----");
                System.out.println(comment);
                System.out.println("------------------------");
            }
            if(comment != null && comment.trim().startsWith("//")){
                //获取普通单行注释"//"
                System.out.println("-----不合法的类方法注释----");
                System.out.println(comment);
                System.out.println("------------------------");
                context.reportIssue(this, tree, "类的方法注释格式要求/**内容*/");
            }
        }
        super.visitMethod(tree);
    }
}
