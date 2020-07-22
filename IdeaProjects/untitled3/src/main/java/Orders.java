import java.sql.*;

public class Orders {
    private Clients client = new Clients();
    private DbProperties prop = new DbProperties();
    private Cargo cargo = new Cargo();
    private Connection conn;

    public void inidDb() {
        try (Connection conn = DriverManager.getConnection(prop.getUrl(), prop.getUser(), prop.getPassword());
             Statement st = conn.createStatement()) {
            st.execute("DROP TABLE IF EXISTS order ");
            st.execute("CREATE  TABLE order (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY " +
                    "name_client VARCHAR (128) name_cargo VARCHAR (128))");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void order() {
        String name = client.getName();
        String goodsName = cargo.getName();
        try (PreparedStatement ps = conn.prepareStatement("SELECT INTO order(name_client,name_cargo) V?ALUE (?,)")) {
            ps.setString(1, name);
            ps.setString(2, goodsName);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
