import java.util.Scanner; 

public class App {
    public static void main(String[] args) throws Exception {

        //polymorphisme
        DataPelanggan P01 = new Pelanggan();

        Kasir K01 = new Kasir();
        K01.login();

        Scanner scanner = new Scanner(System.in);
        String pilihanUser;
        boolean isLanjutkan = true;

        //untuk mencari dan memilih menu
        while (isLanjutkan) {
            System.out.println("========== MENU LAUNDRY CANDY ===========\n");
            System.out.println("1.\tLihat Data Pelanggan Laundy");
            System.out.println("2.\tTambah Data Pelanggan Laundry");
            System.out.println("3.\tEdit Data Pelanggan Laundry");
            System.out.println("4.\tHapus Data Pelanggan Laundry");
            System.out.println("5.\tKeluar Dari Menu");

            System.out.print("\n Pilihan anda: ");
            pilihanUser = scanner.next();

            //untuk melihat data pelanggan
            switch (pilihanUser) {
                case "1":
                    System.out.println("\n=================");
                    System.out.println("LIHAT DATA PELANGGAN LAUNDRY");
                    System.out.println("=================");
                    P01.tampilkanData();
                    break;

                //untuk menambahkan data pelanggan
                case "2":
                    System.out.println("\n=======================");
                    System.out.println("TAMBAH DATA PELANGGAN LAUNDRY");
                    System.out.println("==========================");
                    P01.isiDataPelanggan();
                    P01.tampilkanSemuaData();
                    P01.Struk();
                    P01.tampilkanData();
                    break;

                //untuk mengubah data pelanggan
                case "3":
                    System.out.println("\n================");
                    System.out.println("UBAH DATA PELANGGAN LAUNDRY");
                    System.out.println("================");
                    P01.updateData();
                    P01.tampilkanData();
                    break;

                //untuk menghapus data pelanggan
                case "4":
                    System.out.println("\n==============");
                    System.out.println("HAPUS DATA PELANGGAN LAUNDRY");
                    System.out.println("==============");
                    P01.deleteData();
                    P01.tampilkanData();
                    break;

                //untuk logout dari program
                case "5":
                    System.out.println("\n=========================================");
                    System.out.println("ANDA TELAH KELUAR, SILAHKAN LOGIN KEMBALI");
                    System.out.println("===========================================");
                    System.exit(0);
                    break;
                default:
                    System.err.println("\nInput anda tidak ditemukan\nSilahkan pilih [1-4]");
            }
        }
    //exception  handling  
    try {
        //mengisi data pelanggan
        P01.isiDataPelanggan();
        //mencetak struk
        P01.Struk();  
    } 
    catch (Exception e){
        System.out.println("Terjadi salah input");
    }


    }


}
