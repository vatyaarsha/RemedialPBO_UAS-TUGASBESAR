import java.util.Scanner;

public class Kasir implements Login {

    //untuk mengisi data Login
    String password = "kasir";
    String captcha = "kasir0906";
    String username = "Vatya";
    String inputCaptcha;
    String inputPassword;
    String inputUsername;

    //method login
    public void login(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("LAKUKAN LOGIN TERLEBIH DAHULU");

        //Username
        System.err.println("Username\t: "+username);
        System.out.println("Masukkan username");
        inputUsername = scanner.next();

        while(!inputUsername.equalsIgnoreCase(username)){
            System.out.println("Masukkan username");
            inputUsername = scanner.next();
        }

        System.out.println("LANJUTKAN LOGIN\n\n");

        //Captcha
        System.out.println("Kode captcha = " + captcha);
        System.out.println("Masukkan captcha");
        inputCaptcha = scanner.next();

        while(!inputCaptcha.equalsIgnoreCase(captcha)){
            System.out.println("ANDA BUKAN KASIR\n");
            System.out.println("Masukkan captcha");
            inputCaptcha = scanner.next();
        }

        System.out.println("CAPTCHA BENAR, ANDA KASIR\n\n");

        //Password
        System.out.println("Masukkan Password");
        inputPassword = scanner.next();

        while(!inputPassword.equals(password)){
            System.out.println("PASSWORD SALAH\n");
            System.out.println("Masukkan password : ");
            inputPassword = scanner.next();
        }
        
        System.out.println("BERHASIL LOGIN\n\n");

        
    }

}



