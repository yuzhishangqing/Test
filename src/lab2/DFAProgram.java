package lab2;

import java.util.Scanner;

public class DFAProgram {
	private static Scanner input = new Scanner(System.in);
	private static String K;// 状态集
	private static String Z;// 终态集
	private static String W;// 字母表
	private static char S;// 初始状态
	private static int[][] f;// 状态转化规则

	public static void main(String[] args) {
		initDFA();
		int i = 0;
		System.out.println("请输入一个字符串");
		String str = input.nextLine();
		String process = "";
		char k = S;
		while (i < str.length()) {
			int a = K.indexOf(k);
			int b = W.indexOf(str.charAt(i));
			if (f[a][b] == -1) {
				System.out.println("该字符串不能识别");
				break;
			} else {
				process += "f(" + k + "," + str.charAt(i++) + ")=" + K.charAt(f[a][b]) + "\t";
				k = K.charAt(f[a][b]);
			}
		}
		if (Z.contains("" + k)) {
			System.out.println("该字符串可以识别\n" + process);
		} else {
			System.out.println("该字符串不能识别");
		}

	}

	//初始化DFA
	private static void initDFA() {
		System.out.println("请输入状态集：");
		K = input.nextLine();
		System.out.println("请输入终态集：");
		Z = input.nextLine();
		System.out.println("请输入字母表：");
		W = input.nextLine();
		System.out.println("请输入开始状态");
		S = input.nextLine().charAt(0);
		initF();
	}

	//初始化状态转化关系
	private static void initF() {
		f = new int[K.length()][K.length()];
		for (int i = 0; i < K.length(); i++) {
			for (int j = 0; j < K.length(); j++) {
				f[i][j] = -1;
			}
		}
		System.out.println("请输入状态转化规则，如f(K,a)=K,以#结束");
		String str = input.nextLine();
		while (!"#".equals(str)) {
			int i = K.indexOf(str.substring(str.indexOf('(') + 1, str.indexOf(',')));
			int j = W.indexOf(str.substring(str.indexOf(",") + 1, str.indexOf(")")));
			int k = K.indexOf(str.substring(str.indexOf("=") + 1));
			f[i][j] = k;
			str = input.nextLine();
		}

	}
}
