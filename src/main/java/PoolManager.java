/**
 * @param
 * @Author: dingxy3
 * @Description:内部类实现线程安全
 * @Date: Created in  2018/5/13
 **/
public class PoolManager {

    private static class CreatePool{
          private static MyPoolImpl pools = new MyPoolImpl();
    }
    public static MyPoolImpl getInstance(){
            return CreatePool.pools;
    }
}
