package Task1;

import java.io.*;

public class GeneralTask1 {
    public static void main(String[] args) {
        File fileTask1 = new File(System.getProperty("user.dir") + File.separator, "Task1.txt");
        try {
            FileInputStream inputStream = new FileInputStream(fileTask1);
            byte[] bufferInput = new byte[inputStream.available()];
            inputStream.read(bufferInput);
            inputStream.close();

            String[] array = new String(bufferInput).split("\n");
            replaceWord(array);

            for (String str : array) {
                System.out.println(str);
            }

            FileOutputStream outputStream = new FileOutputStream(fileTask1);
            for (String str : array) {
                byte[] bufferOutput = (str + "\n").getBytes();
                outputStream.write(bufferOutput);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void replaceWord(String[] array) {
        String[] tempFirstLine = array[0].split(" ");
        String[] tempLastLine = array[array.length-1].split(" ");

        String firstWord = tempFirstLine[0];
        String lastWord = tempLastLine[tempLastLine.length-1];

        tempFirstLine[0] = lastWord;
        tempLastLine[tempLastLine.length-1] = firstWord;

        array[0] = String.join(" ", tempFirstLine);
        array[array.length-1] = String.join(" ", tempLastLine);
    }
}
