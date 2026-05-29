import java.util.ArrayList;

public class GoDrive {
    private ArrayList<Kendaraan> daftarKendaraan;

    public GoDrive() {
        daftarKendaraan = new ArrayList<>();
        daftarKendaraan.add(new Mobil("MBL01", "Toyota All New Rush 2024", 350000, 7));
        daftarKendaraan.add(new Mobil("MBL02", "Suzuki Ertiga 2023", 300000, 7));
        daftarKendaraan.add(new Mobil("MBL03", "Honda CR-V 2002", 280000, 5));
        daftarKendaraan.add(new Motor("MTR01", "Honda Stylo 160 2025", 80000, "Matic"));
        daftarKendaraan.add(new Motor("MTR02", "Yamaha All-New XMAX 2023", 100000, "Matic"));
        daftarKendaraan.add(new Motor("MTR03", "Kawasaki KLX 2022", 90000, "Manual"));
    }

    public void tambahKendaraan(Kendaraan k) {
        daftarKendaraan.add(k);
    }

    public void tampilkanDaftarKendaraan() {
        System.out.println("\n+========================================================================================+");
        System.out.println("|                                 DAFTAR ARMADA GODRIVE                                  |");
        System.out.println("+========================================================================================+");
        System.out.println("| Jenis   | Kode   | Nama Kendaraan       | Spesifikasi  | Tarif / Hari   | Status     |");
        System.out.println("+---------+--------+----------------------+--------------+----------------+------------+");
        for (Kendaraan k : daftarKendaraan) {
            k.tampilInfo();
        }
        System.out.println("+========================================================================================+");
    }

    public void sewaKendaraan(String kode, int lamaSewa, boolean isVIP) throws KendaraanUnavailableException {
        Kendaraan kendaraanDitemukan = null;
        for (Kendaraan k : daftarKendaraan) {
            if (k.getKodeKendaraan().equalsIgnoreCase(kode)) {
                kendaraanDitemukan = k;
                break;
            }
        }

        if (kendaraanDitemukan == null || !kendaraanDitemukan.isTersedia()) {
            throw new KendaraanUnavailableException("Kendaraan dengan kode " + kode + " gagal disewa. Alasan: Kendaraan sedang disewa atau tidak ditemukan!");
        }

        kendaraanDitemukan.setTersedia(false);
        double biayaDasar = kendaraanDitemukan.hitungBiayaDasar(lamaSewa);
        
        double diskonDurasi = 0;
        if (lamaSewa > 7) {
            diskonDurasi = 0.05 * biayaDasar; 
        }
        
        double diskonVIP = 0;
        if (isVIP) {
            diskonVIP = 0.10 * (biayaDasar - diskonDurasi); 
        }

        double totalBiayaAkhir = biayaDasar - diskonDurasi - diskonVIP;

        System.out.println("\n+---------------------------------------------------+");
        System.out.println("|               NOTA TRANSAKSI SEWA                 |");
        System.out.println("+---------------------------------------------------+");
        System.out.printf("| %-18s : %s\n", "Status", "BERHASIL DISEWA");
        System.out.printf("| %-18s : %s (%s)\n", "Unit Kendaraan", kendaraanDitemukan.getNamaKendaraan(), kendaraanDitemukan.getKodeKendaraan());
        System.out.printf("| %-18s : %d hari\n", "Durasi Sewa", lamaSewa);
        System.out.println("+---------------------------------------------------+");
        System.out.printf("| %-18s : Rp%,15.0f |\n", "Biaya Dasar + Ops", biayaDasar);
        if (diskonDurasi > 0) {
            System.out.printf("| %-18s : Rp%,15.0f |\n", "Diskon >7 Hari (5%)", -diskonDurasi);
        }
        if (diskonVIP > 0) {
            System.out.printf("| %-18s : Rp%,15.0f |\n", "Diskon VIP (10%)", -diskonVIP);
        }
        System.out.println("+---------------------------------------------------+");
        System.out.printf("| %-18s : Rp%,15.0f |\n", "TOTAL BIAYA AKHIR", totalBiayaAkhir);
        System.out.println("+---------------------------------------------------+");
    }

    public void kembalikanKendaraan(String kode) {
        for (Kendaraan k : daftarKendaraan) {
            if (k.getKodeKendaraan().equalsIgnoreCase(kode)) {
                if (!k.isTersedia()) {
                    k.setTersedia(true);
                    System.out.println("\n>>> [INFO] Kendaraan " + k.getNamaKendaraan() + " (" + k.getKodeKendaraan() + ") berhasil dikembalikan. Status: Tersedia.");
                    return;
                } else {
                    System.out.println("\n>>> [PEMBERITAHUAN] Kendaraan tersebut memang belum disewa.");
                    return;
                }
            }
        }
        System.out.println("\n>>> [ERROR] Kode kendaraan tidak dikenali.");
    }
}