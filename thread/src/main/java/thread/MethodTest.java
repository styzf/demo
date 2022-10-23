package thread;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 测试反射，以及线程获取堆栈判断是否为反射创建的方法
 * @author styzf
 * @date 2022/9/6 0:19
 */
public class MethodTest {
    
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> aClass = Class.forName("thread.MethodTestDO");
        Method method = aClass.getMethod("getName");
        MethodTestDO methodTestDO = new MethodTestDO();
        for (int i = 0; i < 20; i++) {
            method.invoke(methodTestDO);
        }
    }
    
}
