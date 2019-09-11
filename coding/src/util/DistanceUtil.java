package util;

public class DistanceUtil {

	private static int min(int[] d) {
		int min = d[0];
		for (int i = 1; i < d.length; i++) {
			if (d[i] < min)
				min = d[i];
		}
		return min;
	}

	private static int getEditDistance(String s, String t) {
		int n = s.length();
		int m = t.length();
		if (n == 0)
			return m;
		if (m == 0)
			return n;

		int[][] d = new int[n + 1][m + 1];
		for (int i = 0; i <= n; i++)
			d[i][0] = i;
		for (int j = 0; j <= m; j++)
			d[0][j] = j;

		int cost;
		for (int i = 1; i <= n; i++) {
			char s_i = s.charAt(i - 1);
			for (int j = 1; j <= m; j++) {
				char t_j = t.charAt(j - 1);
				if (s_i == t_j)
					cost = 0;
				else
					cost = 1;
				d[i][j] = min(new int[] { d[i - 1][j] + 1, d[i][j - 1] + 1,d[i - 1][j - 1] + cost });
			}
		}
		for(int i=1;i<5;i++){
			System.out.print("[");
			for(int j=1;j<5;j++){
				System.out.print(d[i][j]+(j!=4?",":"")+(j==4?"]":""));
				
			}
			System.out.println("");
		}
		return d[n][m];
	}

	public static double getSimilarity(String s, String t) {
		return getSimilarity(s, t, true);
	}

	public static double getSimilarityDivideMaxLength(String s1, String s2) {
		return getSimilarity(s1, s2, true);
	}

	public static double getSimilarityDivideSumLength(String s1, String s2) {
		return getSimilarity(s1, s2, false);
	}

	private static double getSimilarity(String s1, String s2, boolean flag) {
		s1 = s1.trim().toUpperCase();;
		s2 = s2.trim().toUpperCase();;

		if (s1.equalsIgnoreCase(s2))
			return 1;

		int denominator = flag ? Math.max(s1.length(), s2.length()) : (s1.length() + s2.length());
		return 1 - getEditDistance(s1, s2) * 1.0 / denominator;
	}
	public static void main(String[] args) {
//		System.out.println(getSimilarity("osama bin ladin", "ladin"));
//		System.out.println(getSimilarity("1234567890", "123456789012"));
//
//		System.out.println(getEditDistance("abc", "defgh"));
//		System.out.println(getSimilarity("abc", "defgh"));
//
//		System.out.println(getSimilarity("中国", "中"));
//		System.out.println(getSimilarity("ABC", "ABcd"));
//		System.out.println(getSimilarity("osama bin laden", "osama bin ladin"));

		System.out.println(getEditDistance("word","ford"));
	}
}
