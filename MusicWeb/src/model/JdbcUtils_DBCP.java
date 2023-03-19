package model;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;


public class JdbcUtils_DBCP {
    /**
     * ��java�У���д���ݿ����ӳ���ʵ��java.sql.DataSource�ӿڣ�ÿһ�����ݿ����ӳض���DataSource�ӿڵ�ʵ��
     * DBCP���ӳؾ���java.sql.DataSource�ӿڵ�һ������ʵ��
     */
    private static DataSource ds = null;
    //�ھ�̬������д������ݿ����ӳ�
    static{
        try{
            //����dbcpconfig.properties�����ļ�
            InputStream in = JdbcUtils_DBCP.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
            Properties prop = new Properties();
            prop.load(in);
            //��������Դ
            ds = BasicDataSourceFactory.createDataSource(prop);
        }catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }
    

    public static Connection getConnection() throws SQLException{
        //������Դ�л�ȡ���ݿ�����
        return ds.getConnection();
    }
    
    public static void release(Connection conn){
        if(conn!=null){
            try{
                //��Connection���Ӷ��󻹸����ݿ����ӳ�
                conn.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}