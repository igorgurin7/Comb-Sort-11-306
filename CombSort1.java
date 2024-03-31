package aisd;

import java.util.*;
import java.io.*;

public class CombSort1{
    public static void main(String[] args) {
        try {
            FileWriter fileWriter = new FileWriter("RandomNumbers.txt");
            for (int i = 0; i < 50; i++) {
                int numElements = (int) (Math.random() * 9901 + 100); // генерируем случайное количество элементов от 100 до 10000
                for (int j = 0; j < numElements; j++) {
                    int num = (int) (Integer.MIN_VALUE + Math.random() * Integer.MAX_VALUE); // генерируем случайное число в пределах int
                    fileWriter.write(num + " ");
                }
                fileWriter.write("\n");
            }
            fileWriter.close();
            System.out.println("Файл успешно создан");
        } catch (IOException e) {
            System.out.println("Произошла ошибка при записи в файл");
            e.printStackTrace();
        }


        try {
            File file = new File("RandomNumbers.txt");
            Scanner scanner = new Scanner(file);
            List<List<Integer>> numbersList = new ArrayList<>();

            while (scanner.hasNextLine()) {
                List<Integer> numbers = new ArrayList<>();
                String line = scanner.nextLine();
                if (!line.isEmpty()) {
                    String[] numStrings = line.split(" ");
                    for (String numStr : numStrings) {
                        numbers.add(Integer.parseInt(numStr));
                    }
                    numbersList.add(numbers);
                }
            }
            scanner.close();

            numbersList.sort(Comparator.comparingInt(List::size));


            for (List<Integer> numbers : numbersList) {
                combSort(numbers);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
            e.printStackTrace();
        }



    }

    public static void combSort(List<Integer> list) {
        int gap = list.size();
        int iterations = 0;

        long startTime = System.currentTimeMillis();

        boolean isSorted = false;

        while (!isSorted || gap != 1) {

            if (gap > 1) {
                gap = (int) Math.floor(gap * 10 / 13); // gap / 1.3
            } else {
                gap = 1;
            }

            isSorted = true;
            for (int i = gap; i < list.size(); i++) {
                iterations++;

                if (list.get(i - gap) > list.get(i)) {
                    int tmp = list.get(i);
                    list.set(i, list.get(i - gap));
                    list.set(i - gap, tmp);
                    isSorted = false;
                }
            }
        }

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        System.out.println("Количество входных данных: " + list.size());
        System.out.println("Количество итераций: " + iterations);
        System.out.println("Время выполнения: " + executionTime);
        System.out.println(" ");

    }
}

