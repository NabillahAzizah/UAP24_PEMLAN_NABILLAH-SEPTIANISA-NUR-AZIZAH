package UAP24_PEMLAN_NAbillahSeptianisa;

import java.util.Scanner;

public class AksiUser extends Aksi {
    @Override
    public void tampilanAksi() {
        System.out.println("Aksi User:");
        System.out.println("1. Pesan Film");
        System.out.println("2. Lihat Saldo");
        System.out.println("3. Lihat List Film");
        System.out.println("4. Lihat Pesanan");
        System.out.println("5. Logout");
        System.out.println("6. Tutup Aplikasi");
    }

    @Override
    public void keluar() {
        Akun.logout();
        System.out.println("Anda telah logout.");
    }

    @Override
    public void tutupAplikasi() {
        System.out.println("Aplikasi ditutup.");
        System.exit(0);
    }

    @Override
    public void lihatListFilm() {
        if (Film.getFilms().isEmpty()) {
            System.out.println("Tidak ada film tersedia.");
        } else {
            for (Film film : Film.getFilms().values()) {
                System.out.println(film.getName() + " - " + film.getDescription() + " - Harga: " + film.getPrice() + " - Stok: " + film.getStock());
            }
        }
    }

    public void lihatSaldo() {
        User currentUser = Akun.getCurrentUser();
        if (currentUser != null) {
            System.out.println("Saldo anda: " + currentUser.getSaldo());
        }
    }

    public void pesanFilm() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nama Film yang ingin dipesan: ");
        String namaFilm = scanner.nextLine();
        Film filmPilihan = Film.getFilms().get(namaFilm);
        if (filmPilihan == null) {
            System.out.println("Film yang dicari tidak ditemukan.");
            return;
        }
        System.out.print("Jumlah tiket yang ingin dipesan: ");
        int jumlahTiket = scanner.nextInt();
        scanner.nextLine(); // consume newline

        int totalHarga = (int) (filmPilihan.getPrice() * jumlahTiket);
        User currentUser = Akun.getCurrentUser();

        if (filmPilihan.getStock() < jumlahTiket) {
            System.out.println("Stok tiket tidak mencukupi.");
        } else if (currentUser.getSaldo() < totalHarga) {
            System.out.println("Harga satuan tiket " + filmPilihan.getPrice());
            System.out.println("Total harga " + totalHarga);
            System.out.println("Saldo tidak mencukupi, saldo yang dimiliki " + currentUser.getSaldo() + ".");
        } else {
            System.out.println("Harga satuan tiket " + filmPilihan.getPrice());
            System.out.println("Total harga " + totalHarga);
            filmPilihan.setStock(filmPilihan.getStock() - jumlahTiket);
            currentUser.setSaldo(currentUser.getSaldo() - totalHarga);
            currentUser.addPesanan(filmPilihan, jumlahTiket);
            System.out.println("Tiket berhasil dipesan.");
        }
    }
    public void lihatPesanan() {
        User currentUser = Akun.getCurrentUser();
        if (currentUser != null) {
            if (currentUser.getPesanan().isEmpty()) {
                System.out.println("Kamu belum pernah melakukan pemesanan.");
            } else {
                for (Pesanan pesanan : currentUser.getPesanan().values()) {
                    System.out.println("Film: " + pesanan.getFilm().getName() + " - Jumlah: " + pesanan.getKuantitas() + " - Total Harga: " + (pesanan.getFilm().getPrice() * pesanan.getKuantitas()));
                }
            }
        }
    }
}