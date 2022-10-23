package thread;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 测试用DO
 * @author styzf
 * @date 2022/9/6 0:21
 */
public class MethodTestDO {
    private String name;
    
    public String getName() {
        System.out.println(Thread.currentThread().getStackTrace()[2]);
        return name;
    }
    
    public void setName(String name) {
        System.out.println(Thread.currentThread().getStackTrace()[2]);
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "MethodTestDO{" +
                "name='" + name + '\'' +
                '}';
    }
}
