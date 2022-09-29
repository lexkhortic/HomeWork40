package Task3;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GeneralTask3 {

    public static File file = new File(System.getProperty("user.dir") + File.separator, "Task3.txt");

    public static void main(String[] args) {
        writeFile(createNewLine(createMapStudent(readFile())));
    }

    public static String readFile() {
        StringBuilder strIn = new StringBuilder();
        try {
            FileInputStream inputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

            int temp;
            while ((temp = inputStreamReader.read()) != -1) {
                strIn.append((char) temp);
            }
            inputStream.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(strIn);

        return strIn.toString();
    }

    public static void writeFile(String newString) {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(newString);
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static HashMap<String, ArrayList<Integer>> createMapStudent(String strIn) {
        String[] arrayLine = strIn.split("\n");
        HashMap<String, ArrayList<Integer>> students = new HashMap<>();

        int countSpace = 0;
        for (int i = 0; i < arrayLine.length; i++) {
            StringBuilder surname = new StringBuilder();
            ArrayList<Integer> grades = new ArrayList<>();
            for (int j = 0; j < arrayLine[i].length(); j++) {
                if (arrayLine[i].charAt(j) != ' ' && arrayLine[i].charAt(j) != '\r' && countSpace == 0) {
                    surname.append(arrayLine[i].charAt(j));
                } else if (arrayLine[i].charAt(j) != ' ' && arrayLine[i].charAt(j) != '\r' && countSpace > 0) {
                    grades.add(Integer.parseInt(String.valueOf(arrayLine[i].charAt(j))));
                } else if (arrayLine[i].charAt(j) == ' '){
                    countSpace++;
                }
            }
            students.put(surname.toString(), grades);
            countSpace = 0;
        }

        students.entrySet().forEach(k -> {
            System.out.println("Ключ: " + k.getKey() + ", Значение: " + Arrays.toString(k.getValue().toArray()));
        });

        return students;
    }

    public static String createNewLine(HashMap<String, ArrayList<Integer>> students) {
        StringBuilder allStringForWrite = new StringBuilder();
        students.forEach((key, value) -> {
            if (isMoreThen(value, 7)) {
                allStringForWrite.append(key.toLowerCase()).append(" ");
                allStringForWrite.append(toStringGrades(value));
            } else {
                allStringForWrite.append(key).append(" ");
                allStringForWrite.append(toStringGrades(value));
            }
        });

        return allStringForWrite.toString();
    }

    public static boolean isMoreThen(ArrayList<Integer> grades, int avg) {
        double sum = 0;
        for (Integer el : grades) {
            sum = sum + el;
        }
        return (sum / grades.size()) > avg;
    }

    public static String toStringGrades(ArrayList<Integer> grades) {
        StringBuilder gradesToString = new StringBuilder();
        for (int i = 0; i < grades.size(); i++) {
            if (i == grades.size()-1) {
                gradesToString.append(grades.get(i)).append("\n");
            } else {
                gradesToString.append(grades.get(i)).append(" ");
            }
        }
        return gradesToString.toString();
    }
}
