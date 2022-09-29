package Task2;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class GeneralTask2 {

    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static long[] sizeFiles = new long[4];

    public static void main(String[] args) throws IOException {
//        D:\IT_STEP_JAVA\0_Homework\HomeWork40\src\Task2\file1.txt - Путь
        String[] dirFiles = new String[4];
        String[] infoFiles = new String[4];
        for (int i = 0; i < infoFiles.length; i++) {
            System.out.print("Введите путь к " + (i + 1) + " файлу: ");
            String dir = reader.readLine();
            dirFiles[i] = dir;
            infoFiles[i] = readFile(dir, i);
        }

        writeInFileFour(dirFiles, infoFiles);
        createReportBytes();
    }

    public static String readFile(String dir, int index) {
        StringBuilder strIn = new StringBuilder();
        try {
            File file = new File(dir);
            FileInputStream inputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

            int temp;
            while ((temp = inputStreamReader.read()) != -1) {
                strIn.append((char) temp);
            }
            sizeFiles[index] = file.length();
            inputStream.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return strIn.toString();
    }

    public static void writeInFileFour(String[] dir, String[] infoFiles) {
        StringBuilder allInfoFiles = new StringBuilder(infoFiles[infoFiles.length-1] + " ");
        for (int i = 0; i < infoFiles.length-1; i++) {
            if (i == infoFiles.length-2) {
                allInfoFiles.append(infoFiles[i]);
            } else {
                allInfoFiles.append(infoFiles[i]).append(" ");
            }
        }
        try {
            FileWriter writer = new FileWriter(dir[dir.length-1]);
            writer.write(allInfoFiles.toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createReportBytes() {
        StringBuilder report = new StringBuilder("Отчет о перенесенных файлах(в байтах):\n");
        long sumBytes = 0L;
        for (int i = 0; i < sizeFiles.length-1; i++) {
            report.append(sizeFiles[i]).append(" байт;\n");
            sumBytes += sizeFiles[i];
        }
        report.append("Итого: ").append(sumBytes).append(" байт.");
        System.out.println(report);
    }
}
