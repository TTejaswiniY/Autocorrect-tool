import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String filePath = "C:/Users/HP/Desktop/autocorrect/autocorrect/resource/dictionary.txt";

        // Load dictionary from file
        List<String> dictionaryWords = new ArrayList<>();
        try (InputStream inputStream = new FileInputStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                dictionaryWords.add(line.trim());
            }
            System.out.println("Loaded " + dictionaryWords.size() + " words from the dictionary.");
        } catch (IOException e) {
            System.out.println("Error reading dictionary: " + e.getMessage());
            return;
        }

        // Get user input for autocorrection
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a word to autocorrect: ");
        String input = scanner.nextLine();

        // Perform autocorrection for the input word
        String correctedWord = findClosestWord(input, dictionaryWords);

        // Print the autocorrected word
        System.out.println("Did you mean: " + correctedWord + "?");
    }

    // Levenshtein distance function
    public static int levenshteinDistance(String str1, String str2) {
        int lenStr1 = str1.length();
        int lenStr2 = str2.length();
        int[][] dp = new int[lenStr1 + 1][lenStr2 + 1];

        for (int i = 0; i <= lenStr1; i++) {
            for (int j = 0; j <= lenStr2; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                            dp[i - 1][j - 1] + (str1.charAt(i - 1) == str2.charAt(j - 1) ? 0 : 1));
                }
            }
        }
        return dp[lenStr1][lenStr2];
    }

    // Function to find the closest word in the dictionary
    public static String findClosestWord(String word, List<String> dictionary) {
        String closestWord = null;
        int minDistance = Integer.MAX_VALUE;

        for (String dictWord : dictionary) {
            int distance = levenshteinDistance(word, dictWord);
            if (distance < minDistance) {
                closestWord = dictWord;
                minDistance = distance;
            }
        }
        return closestWord;
    }
}
