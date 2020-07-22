import sun.java2d.SurfaceDataProxy;

import java.sql.*;
import java.util.Scanner;

public class Cargo {
    private String name;
    private DbProperties prop = new DbProperties();
    private static Connection conn;

    public String getName() {
        return name;
    }

    public void inidDb() {
        try (Connection conn = DriverManager.getConnection(prop.getUrl(), prop.getUser(), prop.getPassword());
             Statement st = conn.createStatement()) {
            st.execute("DROP TABLE IF EXISTS Cargo");
            st.execute("CREATE  TABLE Cargo(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY " +
                    "name VARCHAR (128) cost INT )");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void selectAnAction() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1:add product");
            System.out.println("2:delete product");
            String s = sc.nextLine();
            switch (s) {
                case "1":
                    addCorgo(sc);
                case "2":
                    deleteCargo(sc);
                    break;
                default:
                    return;
            }
        }
    }

    public void addCorgo(Scanner sc) {
        System.out.println("Input name cargo");
        String cargo = sc.nextLine();
        cargo = name;
        System.out.println("Input cost product");
        String cost = sc.nextLine();
        try (PreparedStatement ps = conn.prepareStatement("SELECT INTO Cargo (cargo,cost) value (?,?)")) {
            ps.setString(1, cargo);
            ps.setString(2, cost);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteCargo(Scanner sc) {
        System.out.println("Input the id of the product you want to delet");
        String idCargo = sc.nextLine();
        int id = Integer.parseInt(idCargo);
        System.out.println("Input the name of the product you want to delete ");
        String name = sc.nextLine();
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM Cargo WHERE name = ? AND id = ?")) {
            ps.setString(1, name);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
