import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        GameProgress gp1 = new GameProgress(100, 15, 7, 68.2);
        GameProgress gp2 = new GameProgress(32, 120, 15, 374.7);
        GameProgress gp3 = new GameProgress(87, 8, 10, 100.8);
        saveGame(gp1, "C:\\Games\\savegames\\save1.dat");
    }

    static void saveGame(GameProgress gp, String fileDir) {
        try (FileOutputStream fos = new FileOutputStream(fileDir);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gp);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void zipFiles(String zipFileDir, String[] filesDir) throws FileNotFoundException {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipFileDir));
            FileInputStream fis = new FileInputStream(filesDir[0])) {
            ZipEntry entry = new ZipEntry("packed_notes.txt");
            zout.putNextEntry(entry);
            // считываем содержимое файла в массив
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            // добавляем содержимое к архиву
            zout.write(buffer);
            // закрываем текущую запись для новой записи
            zout.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
