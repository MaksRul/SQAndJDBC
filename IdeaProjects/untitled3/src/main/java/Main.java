
public class Main {
    public static void main(String[] args) {
        Orders order = new Orders();
        Clients client = new Clients();
        Cargo cargo = new Cargo();
        client.inidDb();
        client.selectAnAction();
        cargo.inidDb();
        cargo.selectAnAction();
        order.inidDb();
        order.order();
    }
}
