import java.util.ArrayList;
import java.util.List;

public class Banka {
    private List<Musteri> musteriListesi;

    public Banka() {
        musteriListesi = new ArrayList<>();
    }

    public void musteriEkle(Musteri musteri) {
        musteriListesi.add(musteri);
    }

    public List<Musteri> getMusteriListesi() {
        return musteriListesi;
    }
}
