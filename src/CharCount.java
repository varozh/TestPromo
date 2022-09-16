import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CharCount {
    public static void main(String[] args) throws IOException {
        Map<Character, Integer> list = new HashMap<Character, Integer>();
        String path = createLink();

        System.out.println("GET " + path);

        URL url = new URL(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream(), "UTF-8"));
        String line = reader.readLine();
        System.out.println(line);
        System.out.println();
        line = line.replaceAll(" ", "");
        for (char ch: line.toCharArray()) {
            if (list.containsKey(ch)) {
                int count = list.get(ch);
                list.put(ch, count++);
            }
            else
                list.put(ch, 1);
        }

        printMap(list);

        double avg = (double) line.trim().length() / list.size();
        int sr = (int) Math.round(avg);
        System.out.println("Среднее значение частоты = " + String.valueOf(line.trim().length()) + "/" + String.valueOf(list.size()) + " = " + avg);
        printMapEmd(list, sr);
    }

    private static String createLink () {
        int min = 0, max = 100500;
        int rand = (int) (Math.random() * (((max - min) + 1) + min));
        String path = "http://numbersapi.com/" + String.valueOf(rand) + "/trivia";
        return path;
    }

    private static void printMap(Map<Character, Integer> list) {
        System.out.println("Частоты:");
        for (Map.Entry<Character, Integer> i:list.entrySet()) {
            char key = i.getKey();
            int value = i.getValue();
            System.out.println(key + " - " + value + " раз(а)");
        }
    }

    private static void printMapEmd(Map<Character, Integer> list, int avg) {
        System.out.print("Символы, которые соответствуют условию наиболее близкого значения частоты к среднему значанию: ");
        for (Map.Entry<Character, Integer> i:list.entrySet()) {
            if (i.getValue() == avg)
                System.out.print(i + " ");
        }
    }
}