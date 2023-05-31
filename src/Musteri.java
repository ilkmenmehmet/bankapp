import java.util.List;
import java.util.ArrayList;
public class Musteri {
    private String ad;
    private List<Hesap> hesapListesi;

    public Musteri(String ad) {
        this.ad = ad;
        hesapListesi = new ArrayList<>();
    }

    public String getAd() {
        return ad;
    }

    public void hesapEkle(Hesap hesap) {
        hesapListesi.add(hesap);
    }

    public List<Hesap> getHesapListesi() {
        return hesapListesi;
    }
}