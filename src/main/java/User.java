import java.io.Serializable;

/**
 * @param
 * @Author: dingxy3
 * @Description:
 * @Date: Created in  2018/4/30
 **/
public class User implements Serializable{
    private String name;

    private long age;

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    private Integer height;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }
}
