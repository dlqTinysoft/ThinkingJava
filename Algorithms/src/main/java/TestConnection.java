

import java.io.IOException;
import java.io.InputStream;
/**
 * Created by 董乐强 on 2017/11/22.
 */
public class TestConnection extends ClassLoader {
    //类加载器
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        String fileName = name.substring(name.lastIndexOf(".")+1)+".class";
        InputStream is = getClass().getResourceAsStream(fileName);
        if(is == null){
           return super.loadClass(name);
        }
        try {
            byte[] b = new byte[is.available()];
            is.read(b);
            return defineClass(name,b,0,b.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name);
        }
    }
}
