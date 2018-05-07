import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @param
 * @Author: dingxy3
 * @Description:测试连接池
 * @Date: Created in  2018/4/29
 **/
public class TestPool {

    public static void main(String[] args) {
      /*  String a= "1";
        a.toString();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("2","2");
        map.toString();
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        list.add(map);
        if (null !=list && !list.isEmpty()) {
            Map<String,Object> map1 =list.get(0);
            //if (map1.containsKey("CODE")){
                System.out.println(list.get(0).get("CODE"));
            //}
        }
        long b = (Long)map.get("3");*/

       // Map<String,Object> map3 =null;
        Map<String,Object> map3 =new HashMap<String, Object>();
        System.out.println("null的size"+ map3.size());
        User user = new User();
        System.out.println("123"+user.getName());
        System.out.println("123"+user.getAge());
         Map<String,Object> map = null ;
        //System.out.println("456"+map.size());
        //map.putAll(map);
        System.out.println("456"+user.getHeight());

    }
}
