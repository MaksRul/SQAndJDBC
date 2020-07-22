import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbProperties {
    private String url;
    private String user;
    private String password;

    public DbProperties() {
        Properties properties = new Properties();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        url = properties.getProperty("db.url");
        user = properties.getProperty("db.user");
        password = properties.getProperty("db.password");

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
