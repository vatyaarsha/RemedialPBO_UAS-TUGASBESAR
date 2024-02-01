import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.sql.*;

public class Pelanggan extends DataPelanggan {
    //Informasi koneksi database
    static final String DB_URL = "jdbc:mysql://localhost:3306/dbpenjualan_laundry";
    static final String USER = "root";
    static final String PASS = "";
    static Connection conn;
    static ResultSet rs;

    //List untuk menyimpan objek pelanggan
    static List<Pelanggan> pelangganList = new ArrayList<>();

    //Method untuk mengisi data pelanggan dan menyimpannya dalam list
    public void isiDataPelanggan() {
        //Membaca input dari pengguna
        Scanner scanStr = new Scanner(System.in);
        Scanner scanIn = new Scanner(System.in);

        System.out.print("Masukkan no faktur = ");
        No_faktur = scanStr.next();
        System.out.print("Masukkan nama pelanggan = ");
        nama_Pelanggan = scanStr.next();
        System.out.print("Masukkan no HP = ");
        noHP_Pelanggan = scanStr.next();
        System.out.print("Masukkan alamat = ");
        alamat_Pelanggan = scanStr.next();
        System.out.print("Masukkan jenis laundry = ");
        jenisLaundry = scanStr.next();
        System.out.print("Masukkan waktu laundry = ");
        waktuLaundry = scanStr.next();
        System.out.print("Masukkan berat laundry = ");
        beratLaundry = scanIn.nextInt();
        System.out.print("Masukkan harga laundry = ");
        hargaLaundry = scanIn.nextInt();

        //Membuat objek Pelanggan dan menambahkannya ke list
        Pelanggan pelanggan = new Pelanggan();
        pelanggan.No_faktur = No_faktur;
        pelanggan.nama_Pelanggan = nama_Pelanggan;
        pelanggan.noHP_Pelanggan = noHP_Pelanggan;
        pelanggan.alamat_Pelanggan = alamat_Pelanggan;
        pelanggan.jenisLaundry = jenisLaundry;
        pelanggan.waktuLaundry = waktuLaundry;
        pelanggan.beratLaundry = beratLaundry;
        pelanggan.hargaLaundry = hargaLaundry;

        Integer totalBayar = hargaLaundry * beratLaundry;
        this.totalBayar = totalBayar;
        pelanggan.totalBayar = totalBayar;
        pelangganList.add(pelanggan);
    }
    //Method untuk menampilkan semua data pelanggan dari list
    public void tampilkanSemuaData() {
        //Menampilkan header
        System.out.println("+--------------------------------+");
        System.out.println("|    DATA PELANGGAN LAUNDRY CANDY   |");
        System.out.println("+--------------------------------+");
        Integer totalBayar = hargaLaundry * beratLaundry;
        this.totalBayar = totalBayar;

        //Menampilkan data pelanggan dari list
        for (Pelanggan pelanggan : pelangganList) {
            System.out.println("No Faktur: " + pelanggan.No_faktur);
            System.out.println("Nama: " + pelanggan.nama_Pelanggan);
            System.out.println("No HP: " + pelanggan.noHP_Pelanggan);
            System.out.println("Alamat: " + pelanggan.alamat_Pelanggan);
            System.out.println("Jenis: " + pelanggan.jenisLaundry);
            System.out.println("Waktu: " + pelanggan.waktuLaundry);
            System.out.println("Berat: " + pelanggan.beratLaundry);
            System.out.println("Harga: " + pelanggan.hargaLaundry);
            System.out.println("Total: " + pelanggan.totalBayar);
            System.out.println("----------------------------------");
        }
    }
     //Method untuk mencetak struk
    @Override
    public void Struk() throws Exception {
        Integer totalBayar = hargaLaundry * beratLaundry  ;
        this.totalBayar = totalBayar;

        // Menampilkan hari, tanggal, dan waktu
        Date date = new Date();
        SimpleDateFormat hari = new SimpleDateFormat("'Hari/Tanggal \t:' EEEEEEEEEE dd-MM-yy");
        SimpleDateFormat jam = new SimpleDateFormat("'Waktu \t\t:' hh:mm:ss z");

        //menampilkan informasi struk
        System.out.println("----------- Laundry Candy -----------");
        System.out.println(hari.format(date));
        System.out.println(jam.format(date));
        System.out.println("Faktur \t        : " + No_faktur);
        System.out.println("====================================");
        System.out.println("---------- DATA PELANGGAN LAUNDRY ----------");
        System.out.println("Nama \t        : " + nama_Pelanggan);
        System.out.println("No HP \t\t: " + noHP_Pelanggan);
        System.out.println("Alamat \t\t: " + alamat_Pelanggan);
        System.out.println("------ DATA PENGEMBALIAN LAUNDRY -------");
        System.out.println("Jenis \t      : " + jenisLaundry);
        System.out.println("Waktu \t      : " + waktuLaundry);
        System.out.println("Berat \t\t  : " + beratLaundry);
        System.out.println("Harga \t\t: " + hargaLaundry);
        System.out.println("Total  \t        : " + this.totalBayar);
        System.out.println("------------------------------------");
        System.out.println("Kasir \t\t: Vatya Arsha M\n");

        // Menggunakan metode string
        System.out.println("toUpperCase\t: " + nama_Pelanggan.toUpperCase());
        System.out.println("length\t\t: " + nama_Pelanggan.length());

        // Call the insertDataNew method to insert data into the database
        insertDataNew();
    }

    public void insertDataNew() {
        try {
            // Explicitly load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
                String sql = "INSERT INTO identitas_jualan (Faktur, Nama, NoHP, Alamat, Jenis, Waktu, Berat, Harga, Total) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                    pstmt.setString(1, No_faktur);
                    pstmt.setString(2, nama_Pelanggan);
                    pstmt.setString(3, noHP_Pelanggan);
                    pstmt.setString(4, alamat_Pelanggan);
                    pstmt.setString(5, jenisLaundry);
                    pstmt.setString(6, waktuLaundry);
                    pstmt.setInt(7, beratLaundry);
                    pstmt.setInt(8, hargaLaundry);
                    pstmt.setInt(9, totalBayar);

                    int rowsInserted = pstmt.executeUpdate();
                    if (rowsInserted > 0) {
                        System.out.println("\nData inserted successfully into 'identitas_jualan' table!");
                    } else {
                        System.out.println("\nFailed to insert data into 'identitas_jualan' table.");
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }

    public void tampilkanData() throws Exception {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            String sql = "SELECT * FROM identitas_jualan";
            rs = stmt.executeQuery(sql);

            System.out.println("+--------------------------------+");
            System.out.println("|    DATA PELANGGAN LAUNDRY CANDY   |");
            System.out.println("+--------------------------------+");

            while (rs.next()) {
                String No_faktur = rs.getString("Faktur");
                String nama_Pelanggan = rs.getString("Nama");
                String noHP_Pelanggan = rs.getString("NoHP");
                String alamat_Pelanggan = rs.getString("Alamat");
                String jenisLaundry = rs.getString("Jenis");
                String waktuLaundry = rs.getString("Waktu");
                String beratLaundry = rs.getString("Berat");
                String hargaLaundry = rs.getString("Harga");
                String totalBayar = rs.getString("Total");

                System.out.println(String.format("%s. %s -- %s -- (%s)", No_faktur, nama_Pelanggan, noHP_Pelanggan, alamat_Pelanggan, jenisLaundry, waktuLaundry, beratLaundry, hargaLaundry, totalBayar));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateData() throws Exception {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            Scanner scanStr = new Scanner(System.in);
            Scanner scanIn = new Scanner(System.in);

            System.out.print("Masukkan Faktur yang akan diubah = ");
            No_faktur = scanStr.next();
            System.out.print("Masukkan Nama = ");
            nama_Pelanggan = scanStr.next();
            System.out.print("Masukkan No HP = ");
            noHP_Pelanggan = scanStr.next();
            System.out.print("Masukkan Alamat = ");
            alamat_Pelanggan = scanStr.next();
            System.out.print("Masukkan Jenis = ");
            jenisLaundry = scanStr.next();
            System.out.print("Masukkan Waktu = ");
            waktuLaundry = scanStr.next();
            System.out.print("Masukkan Berat = ");
            beratLaundry = scanIn.nextInt();
            System.out.print("Masukkan Harga = ");
            hargaLaundry = scanIn.nextInt();

            Integer totalBayar = hargaLaundry * beratLaundry;
            this.totalBayar = totalBayar;

            String sql = "UPDATE identitas_jualan SET Nama=?, NoHP=?, Alamat=?, Jenis=?, Waktu=?, Berat=?, Harga=?, Total=? WHERE Faktur=?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nama_Pelanggan);
                pstmt.setString(2, noHP_Pelanggan);
                pstmt.setString(3, alamat_Pelanggan);
                pstmt.setString(4, jenisLaundry);
                pstmt.setString(5, waktuLaundry);
                pstmt.setInt(6, beratLaundry);
                pstmt.setInt(7, hargaLaundry);
                pstmt.setInt(8, totalBayar);
                pstmt.setString(9, No_faktur);

                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteData() throws Exception {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            Scanner scannerDel = new Scanner(System.in);
            // ambil input dari user
            System.out.print("Faktur yang mau dihapus : ");
            String no_faktur = (scannerDel.nextLine());

            // buat query hapus
            String sql = "DELETE FROM identitas_jualan WHERE Faktur=?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, no_faktur);
                pstmt.executeUpdate();
            }

            System.out.println("Data telah terhapus...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*public static void main(String[] args) {
        Pelanggan pelanggan = new Pelanggan();
        try {
            pelanggan.isiDataPelanggan();
            pelanggan.Struk();
            pelanggan.tampilkanData();
            pelanggan.updateData();
            pelanggan.deleteData();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

