import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

/**
 * @param
 * @Author: dingxy3
 * @Description:
 * @Date: Created in  2018/5/13
 **/
public class MyPoolImpl implements  IMyPool {

    //初始化管道参数
    private static String jdbcDriver = null;
    private static String jdbcUrl = null;
    private static String userName = null;
    private static String pwd = null;

    //存放管道
    private static Vector<PoolConnections> poolConnections = new Vector<PoolConnections>();
    //初始化管道参数
    private static int count;
    private static int stepCount;
    private static int poolMaxSize;

    //构造初始化实现者
    public MyPoolImpl() {
        init();
    }

    @Override
    public PoolConnections getConnection() {
        if (poolConnections.size() <= 0) {
            System.out.println("初始化没有成功");
            throw new RuntimeException("初始化没有成功");
        }
        PoolConnections poolConnection = getRealCon();
        while (null == poolConnection){
            createConnection(stepCount);
            poolConnection =getRealCon();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return poolConnection;
    }

    private synchronized PoolConnections getRealCon() {

        for (PoolConnections con : poolConnections) {
            if (!con.isBusy()) {
                Connection connection = con.getConnection();
                try {
                    if (!connection.isValid(2000)) {
                        Connection vaildCon = DriverManager.getConnection(jdbcUrl, userName, pwd);
                        con.setConnection(vaildCon);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                con.setBusy(true);
            }
            return  con;
        }

        return null;
    }



    @Override
    public void createConnection(int count) {
        if (poolMaxSize>0 && poolConnections.size()+count>poolMaxSize){
            System.out.println("超过最大值");
            throw  new RuntimeException("超过最大值");
        }
        try {
            for (int i = 0 ; i<count ; i++){
                Connection con  = DriverManager.getConnection(jdbcUrl,userName,pwd);
                PoolConnections poolConnection =   new PoolConnections(false,con);
                poolConnections.add(poolConnection);
            }

        } catch ( Exception e) {
            e.printStackTrace();
        }
    }
    private void  init(){
        InputStream in = MyPoolImpl.class.getClassLoader().getResourceAsStream("jdbc.properties");

        Properties pro = new Properties();
        try {
            pro.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        jdbcDriver =  pro.getProperty("jdbcDriver");
        jdbcUrl =  pro.getProperty("jdbcUrl");
        userName =  pro.getProperty("userName");
        pwd =  pro.getProperty("pwd");

        count = Integer.parseInt(pro.getProperty("count"));
        stepCount = Integer.parseInt(pro.getProperty("stepCount"));
        poolMaxSize = Integer.parseInt(pro.getProperty("poolMaxSize"));
        //注册驱动
        try {
            Driver driver = (Driver) Class.forName(jdbcDriver).newInstance();
            DriverManager.registerDriver(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
