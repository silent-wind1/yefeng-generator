import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FreeMarker 学习测试
 */
public class FreeMarkerTest {
    @Test
    public void test() throws IOException, TemplateException {
        // Step2. 在Test方法中创建一个FreeMarker的全局配置对象，可以统一指定模板文件所在的路径、模板文件的字符集等
        // 不要自己手写，在官方文档里直接CV大法
        // new 出 Configuration 对象，参数为 FreeMarker 版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        // 指定模板文件所在的路径
        configuration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));

        // 设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");

        // *修改数字格式
        configuration.setNumberFormat("0.######");

        // Step3. FreeMarker模板保存在src/main/resources/templates目录下的myweb.html.ftl文件里
        // 创建模板对象，加载指定模板
        Template template = configuration.getTemplate("myweb.html.ftl");

        // Step4. 创建数据库模型
        Map dataModel = new HashMap<String, Object>();
        dataModel.put("currentYear", 2024);
        List<Map<String, Object>> menuItems = new ArrayList<>();
        Map<String, Object> menuItem1 = new HashMap<>();
        menuItem1.put("url", "https://codefather.cn");
        menuItem1.put("label", "编程导航");
        Map<String, Object> menuItem2 = new HashMap<>();
        menuItem2.put("url", "https://laoyujianli.com");
        menuItem2.put("label", "老鱼简历");
        menuItems.add(menuItem1);
        menuItems.add(menuItem2);
        dataModel.put("menuItems", menuItems);

        // Step5. 指定生成的文件
        Writer out = new FileWriter("myweb.html");
        // Step6. 调用process方法，处理并生成文件
        template.process(dataModel, out);

        // 生成文件后别忘了关闭哦
        out.close();
    }
}
