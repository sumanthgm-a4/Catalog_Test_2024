import java.util.*;
import java.text.DecimalFormat;

public class ShamirSecretSharing {

    public static long decodeBase(String value, int base) {
        long result = 0;
        long power = 1;
        for (int i = value.length() - 1; i >= 0; i--) {
            int digit = Character.isDigit(value.charAt(i)) ? (value.charAt(i) - '0') : (value.charAt(i) - 'A' + 10);
            result += digit * power;
            power *= base;
        }
        return result;
    }

    public static double lagrangeInterpolation(List<Pair<Integer, Long>> points, int k) {
        double secret = 0;
        for (int i = 0; i < k; i++) {
            double term = points.get(i).getValue();
            for (int j = 0; j < k; j++) {
                if (i != j) {
                    term *= (0 - points.get(j).getKey()) / (double) (points.get(i).getKey() - points.get(j).getKey());
                }
            }
            secret += term;
        }
        return secret;
    }

    public static void main(String[] args) {

        Map<Integer, Pair<Integer, String>> input1 = new HashMap<>();
        input1.put(1, new Pair<>(10, "4"));
        input1.put(2, new Pair<>(2, "111"));
        input1.put(3, new Pair<>(10, "12"));
        input1.put(6, new Pair<>(4, "213"));

        int n = 4, k = 3;

        List<Pair<Integer, Long>> points1 = new ArrayList<>();
        for (Map.Entry<Integer, Pair<Integer, String>> entry : input1.entrySet()) {
            int x = entry.getKey();
            int base = entry.getValue().getKey();
            String encodedY = entry.getValue().getValue();
            long decodedY = decodeBase(encodedY, base);
            points1.add(new Pair<>(x, decodedY));
        }

        double secret1 = lagrangeInterpolation(points1, k);
        System.out.println("Secret for Test Case 1: " + secret1);

        Map<Integer, Pair<Integer, String>> input2 = new HashMap<>();
        input2.put(1, new Pair<>(10, "28735619723837"));
        input2.put(2, new Pair<>(16, "1A228867F0CA"));
        input2.put(3, new Pair<>(12, "32811A4AA0B7B"));
        input2.put(4, new Pair<>(11, "917978721331A"));
        input2.put(5, new Pair<>(16, "1A22886782E1"));
        input2.put(6, new Pair<>(10, "28735619654702"));
        input2.put(7, new Pair<>(14, "71AB5070CC4B"));
        input2.put(8, new Pair<>(9, "122662581541670"));
        input2.put(9, new Pair<>(8, "642121030037605"));

        n = 9;
        k = 6;

        List<Pair<Integer, Long>> points2 = new ArrayList<>();
        for (Map.Entry<Integer, Pair<Integer, String>> entry : input2.entrySet()) {
            int x = entry.getKey();
            int base = entry.getValue().getKey();
            String encodedY = entry.getValue().getValue();
            long decodedY = decodeBase(encodedY, base);
            points2.add(new Pair<>(x, decodedY));
        }

        double secret2 = lagrangeInterpolation(points2, k);

        DecimalFormat df = new DecimalFormat("#");
        System.out.println("Secret for Test Case 2: " + df.format(secret2));
    }
}

class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}