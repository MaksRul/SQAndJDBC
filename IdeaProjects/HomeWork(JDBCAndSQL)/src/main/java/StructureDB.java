import java.awt.*;
import java.sql.*;
import java.util.Scanner;

public class StructureDB {
    private PullOutDb out = new PullOutDb();
    private static Connection connection;

    public StructureDB() {
        try {
            connection = DriverManager.getConnection(out.getUrl(), out.getPassword(), out.getUser());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inThisDB() {
        try (Statement st = connection.createStatement();) {
            st.execute("DROP TABLE apartments");
            st.execute("CREATE TABLE apartmens(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ," +
                    "region VARCHAR (128)  NOT NULL ," +
                    "address VARCHAR (128) NOT NULL ," +
                    "area INT NOT NULL," +
                    " amountRooms INT NOT NULL, " +
                    "cost INT DEFAULT NULL");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addApartments() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the region of the apartment ");
        String region = sc.nextLine();
        System.out.println("Input the address of the apartment");
        String address = sc.nextLine();
        System.out.println("Input the area of the apartment");
        String sSrea = sc.nextLine();
        System.out.println("Input the amoutn rooms of the apartment");
        String amoutn = sc.nextLine();
        System.out.println("Input the cost of the apartment");
        String cost = sc.nextLine();
        int area = Integer.parseInt(sSrea);
        int amoutnRooms = Integer.parseInt((amoutn));
        int costApartment = Integer.parseInt(cost);
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO apartments(region,address,area,amountRooms,costApartment) VALUES (?,?,?,?,?)")) {
            ps.setString(1, region);
            ps.setString(2, address);
            ps.setInt(3, area);
            ps.setInt(4, amoutnRooms);
            ps.setInt(5, costApartment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectApartments() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1 : select region apertment");
            System.out.println("2 : select address apertment");
            System.out.println("3 : select area apertment");
            System.out.println("4 : select amount rooms apertment");
            System.out.println("5 : select cost apertment");

            String s = sc.nextLine();
            switch (s) {
                case "1":
                    selectRegion(sc);
                    break;
                case "2":
                    selectAddress(sc);
                    break;
                case "3":
                    selectArea(sc);
                    break;
                case "4":
                    selectAmountRooms(sc);
                case "5":
                    selectCostApartmen(sc);
                    break;
                default:
                    return;
            }
        }
    }

    public static void selectCostApartmen(Scanner sc) {
        System.out.println("Input area apartment :");
        String amountRooms = sc.nextLine();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * costApartment")) {
            select(ps);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            sc.close();
        }
    }

    public static void selectAmountRooms(Scanner sc) {
        System.out.println("Input area apartment :");
        String amountRooms = sc.nextLine();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * amountRooms")) {
            select(ps);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        sc.close();
    }

    public static void selectArea(Scanner sc) {
        System.out.println("Input area apartment :");
        String area = sc.nextLine();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * area")) {
            select(ps);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        sc.close();
    }

    public static void selectRegion(Scanner sc) {
        System.out.println("Input region apartment: ");
        String region = sc.nextLine();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * region")) {
            select(ps);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        sc.close();
    }

    public static void selectAddress(Scanner sc) {
        System.out.println("Inpup address apartment :");
        String address = sc.nextLine();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * address ")) {
            select(ps);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        sc.close();

    }

    public static void select(PreparedStatement ps) {
        try (ResultSet rs = ps.executeQuery();) {
            ResultSetMetaData setData = rs.getMetaData();
            for (int i = 1; i <= setData.getColumnCount(); i++) {
                System.out.println(setData.getCatalogName(i));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
