import java.sql.*;
import java.util.Scanner;

public class Clients {
    private static String name;
    private DbProperties prop = new DbProperties();
    private static Connection conn;

    public String getName() {
        return this.name;
    }

    public void inidDb() {

        try (Connection conn = DriverManager.getConnection(prop.getUrl(), prop.getUser(), prop.getPassword());
             Statement st = conn.createStatement()) {
            st.execute("DROP TABLE IF EXISTS Clients");
            st.execute("CREATE TABLE Clients(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY " +
                    "name VARCHAR(128) email VARCHAR (128) namber phone INT )");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectAnAction() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1:add Client");
            System.out.println("1:delete Client");
            String s = sc.nextLine();
            switch (s) {
                case "1":
                    addClient(sc);
                case "2":
                    deleteClient(sc);
                    break;
                default:
                    return;
            }
        }
    }

    public void addClient(Scanner sc) {
        System.out.println("Input your name");
        String name = sc.nextLine();
        name = this.name;
        System.out.println("Input your email");
        String email = sc.nextLine();
        System.out.println("Input your namber phone");
        String phone = sc.nextLine();
        int namberPhone = Integer.parseInt(phone);
        try (PreparedStatement ps = conn.prepareStatement("SELECT INTO Clients(name,email,namber phone) value (?,?,?)")) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setInt(3, namberPhone);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteClient(Scanner sc) {
        System.out.println("Input the name you want to delete");
        String name = sc.nextLine();
        System.out.println("Input the id of the client you want to delete");
        String idClient = sc.nextLine();
        int id = Integer.parseInt(idClient);
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM Clients WHERE name = ? AND id =?")) {
            ps.setString(1, name);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
