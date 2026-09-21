public class Article {
    private Integer rsIdUser;
    private String rsDescription;
    private String rsBrand;
    private double rsPrice;

    public Article(int rsIdUser, String rsDescription, String rsBrand, double rsPrice) {
        this.rsIdUser = rsIdUser;
        this.rsDescription = rsDescription;
        this.rsBrand = rsBrand;
        this.rsPrice = rsPrice;
    }

    public Article(String rsDescription, String rsBrand, double rsPrice) {
        this.rsIdUser = null;
        this.rsDescription = rsDescription;
        this.rsBrand = rsBrand;
        this.rsPrice = rsPrice;
    }

    @Override
    public String toString() {
        return rsIdUser + " - " + rsDescription + " - " + rsBrand + " - " + rsPrice;
    }

    public Integer getRsIdUser() {
        return rsIdUser;
    }

    public void setRsIdUser(Integer rsIdUser) {
        this.rsIdUser = rsIdUser;
    }

    public String getRsDescription() {
        return rsDescription;
    }

    public void setRsDescription(String rsDescription) {
        this.rsDescription = rsDescription;
    }

    public String getRsBrand() {
        return rsBrand;
    }

    public void setRsBrand(String rsBrand) {
        this.rsBrand = rsBrand;
    }

    public double getRsPrice() {
        return rsPrice;
    }

    public void setRsPrice(double rsPrice) {
        this.rsPrice = rsPrice;
    }
}
