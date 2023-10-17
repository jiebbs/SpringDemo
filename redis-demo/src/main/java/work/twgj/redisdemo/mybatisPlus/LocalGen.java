package work.twgj.redisdemo.mybatisPlus;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author weijie.zhu
 * @date 2023/9/4 11:57
 */
public class LocalGen {

    private static Logger log = LoggerFactory.getLogger(LocalGen.class);

    public static final String URL = "jdbc:mysql://localhost:3306/local?useSSL=false&useUnicode=true&characterEncoding=utf-8";

    public static final String USERNAME = "root";

    public static final String PASSWORD = "123456";

    public static void main(String[] args) {
        // 要生成的数据库表名
        List<String> tableNames = Arrays.asList(
            "t_student"
        );
        // 生成代码
        create(tableNames).execute();
    }

    private static FastAutoGenerator create(List<String> tableNames) {
        FastAutoGenerator generator = FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                // 全局配置
                .globalConfig(builder -> {
                    // 获取生成的代码路径,这里没有写死，是运行时获取的，这样可以防止不同的开发人员来回修改生成路径的问题。
                    String outputDir = System.getProperty("user.dir") + "/redis-demo/src/main/java";
                    log.info("outputDir:{}",outputDir);
                    builder.disableOpenDir()
                            .outputDir(outputDir)
                            .dateType(DateType.ONLY_DATE)
                            // 生成的类注释中的作者名称，为了统一表示，这里写死了
                            .author("weijie.zhu");
                })
                // 生成的代码包路径配置
                .packageConfig(builder -> {
                    // 生成的代包公共路径
                    builder.parent("work.twgj.redisdemo");
                    // 生成的mapper xml的存放目录，是在parent路径下面的
                    // 生成的实例类目录
                    builder.entity("entity")
                            // 生成的service目录
                            .service("service")
                            // 生成的mapper目录
                            .mapper("mapper")
                            // xml需要使用实际路径生成，如果使用xml()方法就不能生成在resources下了
                            .pathInfo(Collections.singletonMap(OutputFile.xml,"D://myproject/spring-demo/redis-demo/src/main/resources/mappers"));
                }).strategyConfig(builder -> {
                    // 添加要生成的的数据库表
                    builder.addInclude(tableNames)
                            // 启用大写模式
                            .addTablePrefix("T_")
                            .addFieldPrefix("C_","N_","D_")
                            .enableCapitalMode();
                    // 配置生成的实体类策略，不生成serialVersionID
                    builder.entityBuilder().disableSerialVersionUID()
                            // 如果数据库表名带下划线，按驼峰命名法
                            .columnNaming(NamingStrategy.underline_to_camel)
                            // 使用lombok
                            .enableLombok()
                            // 标记实例类的主键生成方式，如果插入时没有指定，刚自动分配一个，默认是雪花算法
                            .idType(IdType.ASSIGN_ID)
                            // 实例类每次生成的时候，覆盖旧的实体类
                            .enableFileOverride()
                            // 指定生成的实体类名称
                            .convertFileName(entityName -> entityName + "Entity")
                            // 指定生成的service接口名称
                            .serviceBuilder().convertServiceFileName(entityName ->  entityName + "Service")
                            // 指定生成的serviceImpl的名称
                            .convertServiceImplFileName(entityName ->  entityName + "ServiceImpl");
                }).templateConfig(builder -> {
                    // 不生成Controller
                    builder.disable(TemplateType.CONTROLLER);
                    builder.disable(TemplateType.SERVICE);
                    builder.disable(TemplateType.SERVICE_IMPL);
                });
        return generator;
    }
}
