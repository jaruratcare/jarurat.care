package care.jarurat.hope.util;

import java.util.*;

public class SpecialtyMapper {

  private static final List<String> SPECIALTIES = List.of(
      "medical oncologist",
      "medical oncology",
      "surgical oncologist",
      "surgical oncology",
      "radiation oncologist",
      "radiation oncology",
      "gastroenterologist",
      "interventional radiologist",
      "oncologist",
      "cancer specialist");

  public static String clean(String input) {
    if (input == null)
      return "";
    return input.toLowerCase().trim();
  }

  public static String mapUserInput(String input) {
    if (input == null)
      return "";
    return input.toLowerCase().trim();
  }

  public static String fuzzyMatch(String input) {

    if (input == null || input.isBlank())
      return "";

    input = input.toLowerCase().trim();

    // EXACT match
    for (String s : SPECIALTIES)
      if (s.equalsIgnoreCase(input))
        return s;

    // CONTAINS
    for (String s : SPECIALTIES)
      if (s.contains(input) || input.contains(s))
        return s;

    // STARTS-WITH
    for (String s : SPECIALTIES)
      if (s.startsWith(input) || input.startsWith(s))
        return s;

    // LEVENSHTEIN fuzzy
    String best = null;
    int bestDist = Integer.MAX_VALUE;

    for (String s : SPECIALTIES) {
      int d = levenshtein(s, input);
      if (d < bestDist) {
        bestDist = d;
        best = s;
      }
    }

    return bestDist <= 4 ? best : input;
  }

  private static int levenshtein(String a, String b) {
    int[][] dp = new int[a.length() + 1][b.length() + 1];

    for (int i = 0; i <= a.length(); i++)
      dp[i][0] = i;
    for (int j = 0; j <= b.length(); j++)
      dp[0][j] = j;

    for (int i = 1; i <= a.length(); i++) {
      for (int j = 1; j <= b.length(); j++) {
        int cost = (a.charAt(i - 1) == b.charAt(j - 1)) ? 0 : 1;

        dp[i][j] = Math.min(
            Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
            dp[i - 1][j - 1] + cost);
      }
    }
    return dp[a.length()][b.length()];
  }
}
