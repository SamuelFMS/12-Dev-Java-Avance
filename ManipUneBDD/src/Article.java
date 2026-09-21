public class Article {
    private int rsIdUser;
    private String rsDescription;
    private String rsBrand;
    private double rsPrice;

    public Article(int rsIdUser, String rsDescription, String rsBrand, double rsPrice) {
        this.rsIdUser = rsIdUser;
        this.rsDescription = rsDescription;
        this.rsBrand = rsBrand;
        this.rsPrice = rsPrice;
    }

    @Override
    public String toString() {
        return rsIdUser + " - " + rsDescription + " - " + rsBrand + " - " + rsPrice;
    }
}
