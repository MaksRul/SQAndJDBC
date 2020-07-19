public class Main {
    public static void main(String[] args) {
        StructureDB st = new StructureDB();
        st.inThisDB();
        for (int i = 0; i < 5; i++) {
            st.addApartments();
        }
        st.selectApartments();

    }
}

