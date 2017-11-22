import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by 董乐强 on 2017/11/22.
 */
public class TestConnection {
    @Test
    public void test(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgirl?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root");
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery("select * from user");
            while(res.next()){
                Integer id = res.getInt(1);
                String user = res.getString(2);
                String password=res.getString(3);
                System.out.println("id:"+id+" user:"+user+" password:"+password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
