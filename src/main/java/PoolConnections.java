import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @param
 * @Author: dingxy3
 * @Description:数据库连接池
 * @Date: Created in  2018/5/13
 **/
public class PoolConnections {

    /**
     * 繁忙的标识
     */
    private boolean isBusy = false;

    /**
     * 真正的管道
     */
    private Connection connection;

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public PoolConnections(boolean isBusy, Connection connection) {
        this.isBusy = isBusy;
        this.connection = connection;
    }

    public void close(){
        this.isBusy =false;
    }
    public ResultSet queryBySql(String sql){

        Statement sm = null;
        ResultSet rs = null;
        try {
            sm = connection.createStatement();
            rs = sm.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  rs;
    }
}
