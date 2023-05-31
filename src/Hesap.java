public class Hesap {
    private String hesapNumarasi;
    private double bakiye;

    public Hesap(String hesapNumarasi, double bakiye) {
        this.hesapNumarasi = hesapNumarasi;
        this.bakiye = bakiye;
    }

    public String getHesapNumarasi() {
        return hesapNumarasi;
    }

    public double getBakiye() {
        return bakiye;
    }

    public void paraYatir(double miktar) {
        bakiye += miktar;
    }

    public void paraCek(double miktar) {
        if (miktar <= bakiye) {
            bakiye -= miktar;
        } else {
            System.out.println("Yetersiz bakiye!");
        }
    }
}