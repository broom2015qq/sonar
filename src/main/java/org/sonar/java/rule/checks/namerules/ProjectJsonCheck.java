package org.sonar.java.rule.checks.namerules;
import java.io.FileReader;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.dom4j.*;
import org.apache.maven.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.tree.ClassTree;

import java.io.IOException;
import java.io.File;

@Rule(
        key = "check_projectJson",
        name = "check_projectJson",
        description = "deploy.json must exist",
        priority = Priority.CRITICAL,
        tags = {"bug"})
public class ProjectJsonCheck extends BaseTreeVisitor implements JavaFileScanner{

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectJsonCheck.class);
    private JavaFileScannerContext context;

    /**
     * 文件级别的check
     * @param context
     */
    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        String filepath= context.getFileKey();
        System.out.println("这里打印的是啥"+filepath.substring(0,filepath.indexOf("src")));
        if (readFile(filepath.substring(0,filepath.indexOf("src")))){
            context.reportIssue(this, context.getTree(), "pom.xml必须存在");
        }
        scan(context.getTree());
    }
    public static boolean check_projectJson(File file){
        boolean result = false;
        if (file.exists()){
            System.out.println("json check pass");
            result = true;
        }
        return result;
    }

    /**
     * 获得给定目录下的文件,check是否存在sc.iml
     * @param dirPath 给定的目录
     */
    public static boolean readFile(String dirPath) {
        // 建立当前目录中文件的File对象
        File file = new File(dirPath);
        // 取得代表目录中所有文件的File对象数组
        File[] list = file.listFiles();
        // 遍历file数组
        for (int i = 0; i < list.length; i++) {
            if (list[i].isDirectory()) {
                continue;
            } else {
                if (list[i].getName().contentEquals("pom1.xml")){
                    System.out.println("--------pom.xml exist------------");
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 指定目录,扫描整个目录下文件，check是否存在Dockerfile
     * @param dirPath 给定的目录
     */
    public static void readFile1(String dirPath) {
        // 建立当前目录中文件的File对象
        File file = new File(dirPath);
        if (file.isDirectory()){
            File[] list = file.listFiles();
            for (int i = 0; i < list.length; i++) {
                if (list[i].getPath()!=null){
                    readFile1(list[i].getPath());
                }
            }
        }
        else {
            if (file.getName().contentEquals("Dockerfile")){
                System.out.println("Dockerfile exist");
                return;
            }
        }

    }
    public static String getProject() throws DocumentException {
        //需要去pom里拿到工程的名字
        String project_name = "";
        MavenXpp3Reader reader = new MavenXpp3Reader();
        String myPom = System.getProperty("user.dir") + File.separator + "pom.xml";
        try {
            Model model = reader.read(new FileReader(myPom));
            System.out.println(model.getArtifactId());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return project_name;
    }
    public static void main(String[] args) throws DocumentException {
        System.out.println(getProject());
        String cur = System.getProperty("user.dir");
        readFile(cur);
    }

}
