public class Mobil extends Kendaraan {
    private int jumlahKursi;

    public Mobil(String kodeKendaraan, String namaKendaraan, double hargaSewaPerHari, int jumlahKursi) {
        super(kodeKendaraan, namaKendaraan, hargaSewaPerHari);
        this.jumlahKursi = jumlahKursi;
    }

    public int getJumlahKursi() { return jumlahKursi; }
    public void setJumlahKursi(int jumlahKursi) { this.jumlahKursi = jumlahKursi; }

    @Override
    public void tampilInfo() {
        String status = isTersedia() ? "Tersedia" : "Disewa";
        System.out.printf("| %-7s | %-6s | %-20s | %-12s | Rp%-14.0f | %-10s |\n", 
                "[MOBIL]", getKodeKendaraan(), getNamaKendaraan(), "Kursi: " + jumlahKursi, getHargaSewaPerHari(), status);
    }

    @Override
    public double hitungBiayaDasar(int lamaSewa) {
        double total = lamaSewa * getHargaSewaPerHari();
        if (jumlahKursi > 5) {
            total += 50000; 
        }
        return total;
    }
}