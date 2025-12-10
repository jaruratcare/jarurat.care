package care.jarurat.hope.util;

public class FuzzyUtils {

  public static String normalize(String s) {
    if (s == null)
      return "";
    return s.trim().toLowerCase()
        .replaceAll("[^a-z0-9]", ""); // remove spaces, unicode mistakes etc
  }

  // Levenshtein
  public static int levenshtein(String a, String b) {
    int[] costs = new int[b.length() + 1];

    for (int j = 0; j < costs.length; j++)
      costs[j] = j;

    for (int i = 1; i <= a.length(); i++) {
      costs[0] = i;
      int prev = i - 1;
      for (int j = 1; j <= b.length(); j++) {
        int temp = costs[j];
        costs[j] = Math.min(
            Math.min(costs[j] + 1, costs[j - 1] + 1),
            prev + (a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1));
        prev = temp;
      }
    }
    return costs[b.length()];
  }

  // Fuzzy 90% match
  public static boolean fuzzyMatch(String a, String b) {

    a = normalize(a);
    b = normalize(b);

    if (a.contains(b) || b.contains(a))
      return true;

    int distance = levenshtein(a, b);

    return distance <= 3; // allow 3 differences
  }
}
