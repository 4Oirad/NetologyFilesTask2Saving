import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        GameProgress gp1 = new GameProgress(100, 15, 7, 68.2);
        GameProgress gp2 = new GameProgress(32, 120, 15, 374.7);
        GameProgress gp3 = new GameProgress(87, 8, 10, 100.8);
        saveGame(gp1, "C:\\Games\\savegames\\save1.dat");
        saveGame(gp2, "C:\\Games\\savegames\\save2.dat");
        saveGame(gp3, "C:\\Games\\savegames\\save3.dat");
        zipFiles("C:\\Games\\savegames\\save1.zip", "C:\\Games\\savegames\\save1.dat");


    }

    static void saveGame(GameProgress gp, String fileDir) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileDir))) {
            oos.writeObject(gp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void zipFiles(String zipFileDir, String filesDir) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFileDir));
        FileInputStream fis = new FileInputStream(filesDir)) {
            ZipEntry zipEntry = new ZipEntry("save1.dat");
            zos.putNextEntry(zipEntry);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zos.write(buffer);
            zos.closeEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}