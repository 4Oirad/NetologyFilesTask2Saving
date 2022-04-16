import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        GameProgress gp1 = new GameProgress(100, 15, 7, 68.2);
        GameProgress gp2 = new GameProgress(32, 120, 15, 374.7);
        GameProgress gp3 = new GameProgress(87, 8, 10, 100.8);
        File[] saveFiles = new File[] {
                saveGame(gp2, "C:\\Games\\savegames\\save2.dat"),
                saveGame(gp3, "C:\\Games\\savegames\\save3.dat"),
                saveGame(gp1, "C:\\Games\\savegames\\save1.dat")
        };
        zipFiles("C:\\Games\\savegames\\save.zip", saveFiles);
        for (File file: saveFiles) {
            file.delete();
        }
    }

    static File saveGame(GameProgress gp, String fileDir) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileDir))) {
            oos.writeObject(gp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new File(fileDir);
    }

    static void zipFiles(String zipFileDir, File[] files) {
            try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFileDir))) {
                for (File file : files) {
                    FileInputStream fis = new FileInputStream(file.getAbsolutePath());
                    ZipEntry zipEntry = new ZipEntry(file.getName());
                    zos.putNextEntry(zipEntry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zos.write(buffer);
                    zos.closeEntry();
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}