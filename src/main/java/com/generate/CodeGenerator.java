package com.generate;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.sun.javafx.PlatformUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.generate.ProjectName.MEIKANG;

public class CodeGenerator {

    /**
     * 项目名
     */
    public static final String PROJECT_NAME = "";
    //public static final String PROJECT_NAME = "/generator-code";
    /**
     * 代码生成位置
     */
    public static final String PARENT_NAME = "com.prolog";

    /**
     * modular 名字
     */
    public static final String MODULAR_NAME = "";

    /**
     * 基本路径
     */
    public static final String SRC_MAIN_JAVA = "src/main/java/";

    /**
     * 作者
     */
    public static final String AUTHOR = "KingYan";

    /**
     * 是否是 rest 接口
     */
    private static final boolean REST_CONTROLLER_STYLE = true;

    public static void main(String[] args) {

        String moduleName = "deleteme";
        String[] tableNames = new String[]{"inv_container_merge_task_item"};
        String tablePrefix = "#";

        autoGenerator(MEIKANG, moduleName, tableNames, tablePrefix);
    }

    public static void autoGenerator(ProjectName project, String moduleName, String[] tableNames, String tablePrefix) {
        new AutoGenerator().setGlobalConfig(getGlobalConfig()).setDataSource(getDataSourceConfig(project)).setPackageInfo(getPackageConfig(moduleName)).setStrategy(getStrategyConfig(tableNames, tablePrefix)).setCfg(getInjectionConfig(moduleName)).setTemplate(getTemplateConfig()).execute();
    }

    private static String getDateTime() {
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDate.format(formatter);
    }

    private static InjectionConfig getInjectionConfig(final String moduleName) {
        return new InjectionConfig() {
            @Override
            public void initMap() {
                Map map = new HashMap();
                map.put("dateTime", getDateTime());
                setMap(map);
                final String projectPath = System.getProperty("user.dir") + PROJECT_NAME;
                List<FileOutConfig> fileOutConfigList = new ArrayList<FileOutConfig>();
                // 自定义配置会被优先输出
                fileOutConfigList.add(new FileOutConfig("/templates/mapper.xml.vm") {
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        // 自定义输出文件名，如果entity设置了前后缀，此次注意xml的名称也会跟着发生变化
                        return projectPath + "/src/main/resources/mapper/" + moduleName + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                    }
                });
                setFileOutConfigList(fileOutConfigList);
            }
        };
    }


    private static StrategyConfig getStrategyConfig(String[] tableNames, String tablePrefix) {
        return new StrategyConfig().setNaming(NamingStrategy.underline_to_camel).setColumnNaming(NamingStrategy.underline_to_camel).setInclude(tableNames).setRestControllerStyle(REST_CONTROLLER_STYLE).setEntityBuilderModel(true).setControllerMappingHyphenStyle(true).entityTableFieldAnnotationEnable(true).setTablePrefix(tablePrefix + "_").setEntityLombokModel(true);
    }

    private static PackageConfig getPackageConfig(String moduleName) {
        return new PackageConfig().setModuleName(moduleName).setParent(PARENT_NAME).setService("service").setServiceImpl("service.impl").setController("controller").setEntity("entity");
    }

    private static DataSourceConfig getDataSourceConfig(ProjectName project) {
        return new DataSourceConfig().setUrl(project.getJDBC_MYSQL_URL()).setDriverName(project.getJDBC_DRIVER_NAME()).setUsername(project.getJDBC_USERNAME()).setPassword(project.getJDBC_PASSWORD());
    }

    private static GlobalConfig getGlobalConfig() {
        String projectPath = System.getProperty("user.dir") + PROJECT_NAME;
        String filePath = projectPath + "/" + MODULAR_NAME + SRC_MAIN_JAVA;
        if (PlatformUtil.isWindows()) {
            filePath = filePath.replaceAll("/+|\\\\+", "\\\\");
        } else {
            filePath = filePath.replaceAll("/+|\\\\+", "/");
        }
        return new GlobalConfig().setOutputDir(filePath).setDateType(DateType.ONLY_DATE).setIdType(IdType.UUID).setAuthor(AUTHOR).setBaseColumnList(true).setSwagger2(true).setEnableCache(false).setBaseResultMap(true).setOpen(false);
    }

    private static TemplateConfig getTemplateConfig() {
        return new TemplateConfig().setController("/templates-generator/controller.java.vm").setService("/templates" +
                "-generator/service.java.vm").setServiceImpl("/templates-generator/serviceImpl.java.vm").setEntity(
                        "/templates-generator/entity.java.vm").setMapper("/templates-generator/mapper.java.vm").setXml("/templates-generator/mapper.xml.vm");
    }

    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        sb.append("please input " + tip + " : ");
        System.out.println(sb.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("please input the correct " + tip + ". ");
    }
}