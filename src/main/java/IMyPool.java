/**
 * @param
 * @Author: dingxy3
 * @Description:面向接口 抽取连接池对象
 * @Date: Created in  2018/5/13
 **/
public interface IMyPool {

    /**
     * 获取连接池
     * @return
     */
    PoolConnections getConnection();

    /**
     * 创建连接池
     * @param count
     */
    void createConnection(int count);
}
