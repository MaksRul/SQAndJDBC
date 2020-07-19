import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
public class PullOutDb {
    private String url;
    private String user;
    private String password;

    public PullOutDb() {
        Properties pro = new Properties();
       try( InputStream is = getClass().getClassLoader().getResourceAsStream("db_properties");){
           pro.load(is);
       }catch(IOException e){
           e.printStackTrace();
       }
       url = pro.getProperty("db_url");
       user = pro.getProperty("db_user");
       password = pro.getProperty("db.password");
    }
    public String getUrl() {
        return url;
    }
    public String getUser() {
        return user;
    }
    public String getPassword() {
        return password;
    }
}