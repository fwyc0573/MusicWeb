package model;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;


public class JdbcUtils_DBCP {
    /**
     * 在java中，编写数据库连接池需实现java.sql.DataSource接口，每一种数据库连接池都是DataSource接口的实现
     * DBCP连接池就是java.sql.DataSource接口的一个具体实现
     */
    private static DataSource ds = null;
    //在静态代码块中创建数据库连接池
    static{
        try{
            //加载dbcpconfig.properties配置文件
            InputStream in = JdbcUtils_DBCP.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
            Properties prop = new Properties();
            prop.load(in);
            //创建数据源
            ds = BasicDataSourceFactory.createDataSource(prop);
        }catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }
    

    public static Connection getConnection() throws SQLException{
        //从数据源中获取数据库连接
        return ds.getConnection();
    }
    
    public static void release(Connection conn){
        if(conn!=null){
            try{
                //将Connection连接对象还给数据库连接池
                conn.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}