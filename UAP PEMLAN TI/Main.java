import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GoDrive sistem = new GoDrive();
        Scanner input = new Scanner(System.in);
        int menu = 0;

        do {
            System.out.println("\n======= MENU GO DRIVE RENTAL SYSTEM =======");
            System.out.println("1. Tambah Kendaraan");
            System.out.println("2. Tampilkan Daftar Armada");
            System.out.println("3. Sewa Kendaraan");
            System.out.println("4. Kembalikan Kendaraan");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            
            try {
                menu = Integer.parseInt(input.nextLine());
                switch (menu) {
                    case 1:
                        System.out.print("Masukkan jenis kendaraan (mobil/motor): ");
                        String jenis = input.nextLine().trim();
                        System.out.print("Masukkan kode kendaraan: ");
                        String kode = input.nextLine().trim();
                        System.out.print("Masukkan nama kendaraan: ");
                        String nama = input.nextLine().trim();
                        System.out.print("Masukkan harga sewa per hari: ");
                        double harga = Double.parseDouble(input.nextLine());

                        if (jenis.equalsIgnoreCase("mobil")) {
                            System.out.print("Masukkan kapasitas kursi: ");
                            int kursi = Integer.parseInt(input.nextLine());
                            sistem.tambahKendaraan(new Mobil(kode, nama, harga, kursi));
                            System.out.println("[INFO] Kendaraan Mobil berhasil ditambahkan!");
                        } else if (jenis.equalsIgnoreCase("motor")) {
                            System.out.print("Masukkan jenis transmisi: ");
                            String transmisi = input.nextLine().trim();
                            sistem.tambahKendaraan(new Motor(kode, nama, harga, transmisi));
                            System.out.println("[INFO] Kendaraan Motor berhasil ditambahkan!");
                        } else {
                            System.out.println("[PENTING] Jenis tidak valid, penambahan dibatalkan.");
                        }
                        break;

                    case 2:
                        sistem.tampilkanDaftarKendaraan();
                        break;

                    case 3:
                        System.out.print("Masukkan kode kendaraan yang ingin disewa: ");
                        String kSewa = input.nextLine().trim();
                        System.out.print("Masukkan durasi sewa (dalam hari): ");
                        int durasi = Integer.parseInt(input.nextLine());
                        System.out.print("Apakah Anda Member VIP? (y/n): ");
                        boolean isVIP = input.nextLine().equalsIgnoreCase("y");
                        
                        sistem.sewaKendaraan(kSewa, durasi, isVIP);
                        break;

                    case 4:
                        System.out.print("Masukkan kode kendaraan yang ingin dikembalikan: ");
                        String kKembali = input.nextLine().trim();
                        sistem.kembalikanKendaraan(kKembali);
                        break;

                    case 5:
                        System.out.println("Terima kasih telah menggunakan Go Drive!");
                        break;

                    default:
                        System.out.println("[PEMBERITAHUAN] Menu tidak tersedia.");
                }
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Input harus berupa angka valid!");
            } catch (KendaraanUnavailableException e) {
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("[ERROR] Terjadi kesalahan sistem: " + e.getMessage());
            }
        } while (menu != 5);

        input.close();
    }
}
