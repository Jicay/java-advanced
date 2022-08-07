public class Excalibur {
    private static Excalibur INSTANCE;

    private String name;

    private Excalibur(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static final Excalibur getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Excalibur("Sword");
        }
        return INSTANCE;
    }
}
