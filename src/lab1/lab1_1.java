package lab1;

import java.util.Scanner;

public class lab1_1 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String gram[] = new String[20];// 文法
		String VN[] = new String[50];// 非终结符
		String VT[] = new String[50];// 终结符
		int i = 0;

		System.out.println("请输入文法（如S:aA）");
		String str = input.nextLine();
		while (!"#".equals(str)) {
			gram[i++] = str;
			str = input.nextLine();
		}
		gram[i++] = str;
		int result = isGram(gram);
		switch (result) {
		case 0:
			System.out.println("0型文法");
			find(gram,VN,VT);
			break;
		case 1:
			System.out.println("1型文法");
			find(gram,VN,VT);
			break;
		case 2:
			System.out.println("2型文法");
			find(gram,VN,VT);
			break;
		case 3:
			System.out.println("3型文法");
			find(gram,VN,VT);
			break;
		default:
			System.out.println("输入的文法不合法");
			break;
		}

	}

	private static int isGram(String[] gram) {
		int m = 0, n = 0, index = 0;
		String left[] = new String[gram.length];
		String right[] = new String[gram.length];

		for (int i = 0; i < gram.length; i++) {
			int count = 0;
			if(!"#".equals(gram[i])){
				String str = gram[i];
				while ((index = str.indexOf(":")) != -1) {
					str = str.substring(index + 1,str.length());
					count++;
				}
				
				if (count == 1) {
					left[m++] = gram[i].substring(0, gram[i].indexOf(":"));
					right[n++] = gram[i].substring(gram[i].indexOf(":") + 1, gram[i].length());
				} else {
					return -1;
				}
			}
			else
				break;

		}

		for (int i = 0; i < m; i++) {
			int count = 0;
			for (int j = 0; j < left[i].length(); j++) {
				if (left[i].charAt(j) >= 'A' && left[i].charAt(j) <= 'Z')
					count ++;
			}
			if(count == 0)
				return -1;
		}

		return isOneGram(left, right,m,n);

	}

	private static int isOneGram(String[] left, String[] right, int m, int n) {
		for (int i = 0; i < m; i++) {
			if (left[i].length() > right[i].length())
				return 0;
		}
		return isTwoGram(left, right,m,n);
	}

	private static int isTwoGram(String[] left, String[] right, int m, int n) {
		for (int i = 0; i < n; i++) {
			if (left[i].length() != 1)
				return 1;
		}
		return isThreeGram(left, right,m,n);
	}

	private static int isThreeGram(String[] left, String[] right, int m, int n) {
		for (int i = 0; i < n; i++) {
			if (right[i].length() > 2) {
				System.out.println("1");
				return 2;				
			}
			if (right[i].length() == 1 || right[i].length() == 2) {
				if (right[i].charAt(0) < 'a' || right[i].charAt(0) > 'z') {
					System.out.println("2");
					return 2;
				}
				if (right[i].length() == 2) {
					if (right[i].charAt(1) < 'A' || right[i].charAt(1) > 'Z') {
						return 2;						
					}
				}
			}
		}
		return 3;
	}

	private static void find(String[] gram, String[] vN, String[] vT) {
		int n = 0, m = 0;
		for (int i = 0; i < gram.length; i++) {
			if (!"#".equals(gram[i])) {
				for (int j = 0; j < gram[i].length(); j++) {
					if (gram[i].charAt(j) >= 'a' && gram[i].charAt(j) <= 'z') {
						vT[n++] = String.valueOf(gram[i].charAt(j));
					}
					if (gram[i].charAt(j) >= 'A' && gram[i].charAt(j) <= 'Z') {
						vN[m++] = String.valueOf(gram[i].charAt(j));
					}
				}
			} else
				break;
		}

		System.out.println("非终结符为：");
		String str = "";
		String str1 = "";
		for (int i = 0; i < m; i++) {
			if(!str.contains(vN[i]))
				str += vN[i] + " ";
		}
		System.out.print(str);
		System.out.println();
		System.out.println("终结符为：");
		for (int i = 0; i < n; i++) {
			if(!str1.contains(vT[i]))
				str1 += vT[i] + " ";
		}
		System.out.print(str1);
	}
}
