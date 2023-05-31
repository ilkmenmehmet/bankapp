import java.util.List;
import java.util.Scanner;

public class BankaUygulamasi {
    private static Banka banka = new Banka();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean devam = true;

        while (devam) {
            menuGoster();
            int secim = scanner.nextInt();
            scanner.nextLine();

            switch (secim) {
                case 1:
                    musteriEkle();
                    break;
                case 2:
                    hesapOlustur();
                    break;
                case 3:
                    paraTransferi();
                    break;
                case 4:
                    bakiyeGoruntule();
                    break;
                case 5:
                    devam = false;
                    break;
                default:
                    System.out.println("Geçersiz seçim. Lütfen tekrar deneyin.");
            }
        }
    }

    private static void menuGoster() {
        System.out.println("--------- Banka Uygulaması ---------");
        System.out.println("1. Müşteri Ekle");
        System.out.println("2. Hesap Oluştur");
        System.out.println("3. Para Transferi");
        System.out.println("4. Bakiye Görüntüle");
        System.out.println("5. Çıkış");
        System.out.print("Seçiminizi yapın: ");
    }

    private static void musteriEkle() {
        System.out.print("Müşteri adını girin: ");
        String musteriAdi = scanner.nextLine();

        System.out.print("Müşteri türünü girin (1-Bireysel, 2-Sendika, 3-İşletme): ");
        int musteriTur = scanner.nextInt();
        scanner.nextLine();

        Musteri musteri = null;

        switch (musteriTur) {
            case 1:
                musteri = new BireyselMusteri(musteriAdi);
                break;
            case 2:
                musteri = new SendikaMusterisi(musteriAdi);
                break;
            case 3:
                musteri = new IsletmeMusterisi(musteriAdi);
                break;
            default:
                System.out.println("Geçersiz müşteri türü.");
                return;
        }

        banka.musteriEkle(musteri);
        System.out.println("Müşteri başarıyla eklendi.");
    }

    private static void hesapOlustur() {
        System.out.print("Müşteri adını girin: ");
        String musteriAdi = scanner.nextLine();

        System.out.print("Hesap numarasını girin: ");
        String hesapNumarasi = scanner.nextLine();

        System.out.print("Hesap başlangıç bakiyesini girin: ");
        double bakiye = scanner.nextDouble();
        scanner.nextLine();

        Musteri musteri = null;
        for (Musteri m : banka.getMusteriListesi()) {
            if (m.getAd().equals(musteriAdi)) {
                musteri = m;
                break;
            }
        }

        if (musteri == null) {
            System.out.println("Müşteri bulunamadı.");
            return;
        }

        Hesap hesap = new Hesap(hesapNumarasi, bakiye);
        musteri.hesapEkle(hesap);
        System.out.println("Hesap başarıyla oluşturuldu.");
    }

    private static void paraTransferi() {
        System.out.print("Gönderen hesap numarasını girin: ");
        String gonderenHesapNumarasi = scanner.nextLine();

        System.out.print("Alıcı hesap numarasını girin: ");
        String aliciHesapNumarasi = scanner.nextLine();

        System.out.print("Transfer miktarını girin: ");
        double miktar = scanner.nextDouble();
        scanner.nextLine();

        Hesap gonderenHesap = null;
        Hesap aliciHesap = null;

        for (Musteri musteri : banka.getMusteriListesi()) {
            for (Hesap hesap : musteri.getHesapListesi()) {
                if (hesap.getHesapNumarasi().equals(gonderenHesapNumarasi)) {
                    gonderenHesap = hesap;
                } else if (hesap.getHesapNumarasi().equals(aliciHesapNumarasi)) {
                    aliciHesap = hesap;
                }
            }
        }

        if (gonderenHesap == null || aliciHesap == null) {
            System.out.println("Hesap bulunamadı.");
            return;
        }
        if (gonderenHesap.getBakiye() < miktar) {
            System.out.println("Yetersiz bakiye. İşlem gerçekleştirilemedi..");
            return;
        }


        gonderenHesap.paraCek(miktar);
        aliciHesap.paraYatir(miktar);

        System.out.println("Para transferi başarıyla gerçekleştirildi.");
    }
    private static void bakiyeGoruntule() {
        System.out.print("Müşteri adını girin: ");
        String musteriAdi = scanner.nextLine();

        Musteri musteri = null;
        for (Musteri m : banka.getMusteriListesi()) {
            if (m.getAd().equals(musteriAdi)) {
                musteri = m;
                break;
            }
        }

        if (musteri == null) {
            System.out.println("Müşteri bulunamadı.");
            return;
        }

        System.out.println("Müşteri: " + musteri.getAd());
        List<Hesap> hesapListesi = musteri.getHesapListesi();
        for (Hesap hesap : hesapListesi) {
            System.out.println("Hesap Numarası: " + hesap.getHesapNumarasi());
            System.out.println("Bakiye: " + hesap.getBakiye());
            System.out.println("---------------------");
        }
    }

}
