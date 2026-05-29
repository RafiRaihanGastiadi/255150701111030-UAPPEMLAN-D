public class Motor extends Kendaraan {
    private String jenisTransmisi;

    public Motor(String kodeKendaraan, String namaKendaraan, double hargaSewaPerHari, String jenisTransmisi) {
        super(kodeKendaraan, namaKendaraan, hargaSewaPerHari);
        this.jenisTransmisi = jenisTransmisi;
    }

    public String getJenisTransmisi() { return jenisTransmisi; }
    public void setJenisTransmisi(String jenisTransmisi) { this.jenisTransmisi = jenisTransmisi; }

    @Override
    public void tampilInfo() {
        String status = isTersedia() ? "Tersedia" : "Disewa";
        System.out.printf("| %-7s | %-6s | %-20s | %-12s | Rp%-14.0f | %-10s |\n", 
                "[MOTOR]", getKodeKendaraan(), getNamaKendaraan(), "Trans: " + jenisTransmisi, getHargaSewaPerHari(), status);
    }

    @Override
    public double hitungBiayaDasar(int lamaSewa) {
        double total = lamaSewa * getHargaSewaPerHari();
        if (jenisTransmisi.equalsIgnoreCase("Matik")) {
            total += (10000 * lamaSewa); 
        }
        return total;
    }
}