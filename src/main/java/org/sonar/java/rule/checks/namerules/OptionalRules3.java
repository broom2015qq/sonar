package org.sonar.java.rule.checks.namerules;

import org.sonar.api.internal.google.common.collect.ImmutableList;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.*;

import java.util.List;

/**
 * 获取注释
 */
public class OptionalRules3 {
    /**
     * 获取类注释
     * @param tree
     * @return
     */
    private String getJavadoc(ClassTree tree){
        String javadoc = null;
        for(SyntaxTrivia trivia : tree.modifiers().firstToken().trivias()){
            String comment = trivia.comment();
            if(comment != null && comment.trim().startsWith("/*")){
                // 获取javadoc注释
                System.out.println(comment);
            }
            if(comment != null && comment.trim().startsWith("//")){
                //获取普通单行注释"//"
                System.out.println(comment);
            }
        }
        return javadoc;
    }
    /**
     * 获取方法注释
     * @param tree
     * @return
     */
    private String getJavadoc(MethodTree tree){
        String javadoc = null;
        for(SyntaxTrivia trivia : tree.modifiers().firstToken().trivias()){
            String comment = trivia.comment();
            if(comment != null && comment.trim().startsWith("/*")){
                // 获取javadoc注释
                System.out.println(comment);
            }
            if(comment != null && comment.trim().startsWith("//")){
                //获取普通单行注释"//"
                System.out.println(comment);
            }
        }
        return javadoc;
    }
    /**
     * 获取成员变量注释
     *
     * 需要先判断是否为类成员变量：
     * if(variableTree.symbol().isPublic() || variableTree.symbol()
     *  .isProtected() ||variableTree.symbol().isPrivate()){getJavadoc(variableTree)}
     * @param tree
     * @return
     */
    private String getJavadoc(VariableTree tree){
        String javadoc = null;
        for(SyntaxTrivia trivia : tree.modifiers().firstToken().trivias()){
            String comment = trivia.comment();
            if(comment != null && comment.trim().startsWith("/*")){
                // 获取javadoc注释
                System.out.println(comment);
            }
            if(comment != null && comment.trim().startsWith("//")){
                //获取普通单行注释"//"
                System.out.println(comment);
            }
        }
        return javadoc;
    }
    /**
     * 获取枚举的javadoc
     * @param tree
     * @return
     */
    private String getJavadoc(EnumConstantTree tree){
        String javadoc = null;
        for(SyntaxTrivia trivia : tree.initializer().firstToken().trivias()){
            String comment = trivia.comment();
            if(comment != null && comment.trim().startsWith("/*")){
                // 获取javadoc注释
                System.out.println(comment);
            }
            if(comment != null && comment.trim().startsWith("//")){
                //获取普通单行注释"//"
                System.out.println(comment);
            }
        }
        return javadoc;
    }
}
