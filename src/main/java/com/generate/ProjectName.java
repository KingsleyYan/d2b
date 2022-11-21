package com.generate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProjectName {

    TUOPAI("jdbc:mysql://192.168.1.13:2206/wms_lp_tuopai?useSSL=false&serverTimezone=CTT&useUnicode=true" +
            "&characterEncoding=utf-8&allowMultiQueries=true", "com.mysql.jdbc.Driver", "dev", "prolog0212-dev"),
    SIYUAN_OUTBOUND("jdbc:mysql://192.168.1.90:3306/upcloud_biz_outbound_siyuan?useSSL=false&serverTimezone=CTT" +
            "&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true", "com.mysql.jdbc.Driver", "root",
            "prolog0212"), MEIKANG("jdbc:mysql://192.168.1.13:2206/wms_lp_meikang?useSSL=false&serverTimezone=CTT&useUnicode=true" +
            "&characterEncoding=utf-8&allowMultiQueries=true", "com.mysql.jdbc.Driver", "dev", "prolog0212-dev");

    private final String JDBC_MYSQL_URL;
    private final String JDBC_DRIVER_NAME;
    private final String JDBC_USERNAME;
    private final String JDBC_PASSWORD;

}
