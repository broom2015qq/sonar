package org.sonar.java.rule.checks.namerules;

import org.sonar.api.internal.google.common.collect.ImmutableList;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.VariableTree;

import java.util.List;

/**
 * 指定sonar树扫描节点
 */
public class OptionalRules2 extends IssuableSubscriptionVisitor {
    @Override
    public List<Tree.Kind> nodesToVisit() {
        //指定要扫描的节点，在visitNode方法中获取到指定的节点
        System.out.println("Tree.Kind.METHOD"+Tree.Kind.METHOD);
        return ImmutableList.of(Tree.Kind.METHOD, Tree.Kind.VARIABLE);
    }
    @Override
    public void scanFile(JavaFileScannerContext context) {
        super.context = context;
        super.scanFile(context);
    }
    @Override
    public void visitNode(Tree tree) {
        if (tree instanceof MethodTree) {
            MethodTree methodTree = (MethodTree) tree;
            System.out.println(methodTree.simpleName().name());
        }
        if (tree instanceof VariableTree) {
            VariableTree variableTree = (VariableTree) tree;
            System.out.println(variableTree.simpleName().name());
        }
    }
}
