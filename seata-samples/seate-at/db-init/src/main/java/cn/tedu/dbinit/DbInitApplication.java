package cn.tedu.dbinit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootApplication
public class DbInitApplication {

    //注入配置的数据源
    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(DbInitApplication.class, args);
    }


    @PostConstruct
    public void init() throws SQLException {
        exec(dataSource, "sql/account.sql");
        exec(dataSource, "sql/storage.sql");
        exec(dataSource, "sql/order.sql");
        exec(dataSource, "sql/seata-server.sql");
    }


    private void exec(DataSource accountDatasource, String script) throws SQLException {
        ClassPathResource rc = new ClassPathResource(script, DbInitApplication.class.getClassLoader());
        //因为脚本有中文，所以要设置编码字符集
        EncodedResource er = new EncodedResource(rc, "utf-8");
        //执行SQL脚本文件
        ScriptUtils.executeSqlScript(accountDatasource.getConnection(), er);
    }



}
