import java.io.*;
import java.util.Scanner;
public class ex15 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите имя файла => "); String
                fname = sc.nextLine();
        try {
            File fl = new File(fname);
            fl.createNewFile();
            System.out.println("Полный путь файла: " + fl.getAbsolutePath());
            System.out.print("Введите количество строк для записи в файл => ");
            int n = sc.nextInt();
            DataOutputStream dOut =
                    new DataOutputStream(new FileOutputStream(fl));
            sc.nextLine();
            for (int i = 0; i < n; i++) {
                System.out.print("Введите строку для записи в файл => ");
                String s = sc.nextLine();
            dOut.writeUTF(s);
            }
            dOut.flush();
            dOut.close();
            DataInputStream rd = new DataInputStream(new FileInputStream(fl));
            while (true) {
                System.out.println(rd.readUTF());
            }
        } catch (Exception e) {
            System.out.println("" + e);
        }
    }
}

