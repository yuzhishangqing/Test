package lab4;

import java.util.Scanner;

public class lab4 {
	private static String stack = "#";// 栈
	private static String VN = "+*|i()#";// 终结符
	private static String[][] OPG = { { ">", "<", "<", "<", "<", ">", ">" }, { ">", ">", "<", "<", "<", ">", ">" },
			{ ">", ">", "<", "<", "<", ">", ">" }, { ">", ">", ">", " ", " ", ">", ">" },
			{ "<", "<", "<", "<", "<", "=", " " }, { ">", ">", ">", " ", " ", ">", ">" },
			{ "<", "<", "<", "<", "<", " ", "=" }, };

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String[] processStack = new String[100];// 分析栈
		String[] prerelation = new String[100];// 优先关系
		String[] nowChar = new String[100];// 当前输入
		String[] LastChar = new String[100];// 剩余输入串
		String[] result = new String[100];// 移进或规约
		char lastStack = 0, lastStack1;
		boolean flag = true;
		System.out.println("请输入一个句子");
		String lang = input.nextLine();

		int n = -1, i = 0;
		while (i < lang.length()) {
			n++;
			processStack[n] = stack;
			lastStack = findLastStack(stack);
			nowChar[n] = "" + lang.charAt(i);
			int a = VN.indexOf(lastStack);
			int b = VN.indexOf(nowChar[n]);
			prerelation[n] = OPG[a][b];
			if ("<".equals(prerelation[n])) {
				stack += nowChar[n];
				LastChar[n] = lang.substring(++i);
				result[n] = "移进";
			} else if (">".equals(prerelation[n])) {
				stack = stack.substring(0, stack.indexOf(lastStack));
				lastStack1 = findLastStack(stack);
				if ("=".equals(OPG[VN.indexOf(lastStack1)][VN.indexOf(lastStack)])) {
					stack = stack.substring(0, stack.indexOf(lastStack1));
				} else {
					stack = stack.substring(0, stack.indexOf(lastStack1) + 1);
				}
				stack += "N";
				result[n] = "规约";
				if ("移进".equals(result[n - 1])) {
					LastChar[n] = lang.substring(i + 1);
				} else {
					LastChar[n] = LastChar[n - 1];
				}

			} else if ("=".equals(prerelation[n])) {

				if (lastStack == '#' && "#".equals(nowChar[n])) {
					result[n] = "接受";
					LastChar[n] = LastChar[n - 1];
					System.out.println("可以识别！");
					break;
				} else {
					stack += nowChar[n];
					LastChar[n] = lang.substring(++i);
					result[n] = "移进";
				}
			} else {
				flag = false;
				System.out.println("不能识别！");
				break;
			}

		}

		//flag为true表示可以识别
		if (flag) {
			System.out.println("步骤\t栈\t优先关系\t当前输入\t剩余输入\t移进或规约");
			for (int j = 0; j <= n; j++) {
				System.out.println(j + 1 + "\t" + processStack[j] + "\t" + prerelation[j] + "\t" + nowChar[j] + "\t"
						+ LastChar[j] + "\t" + result[j]);
			}
		}

	}

	//查找栈尾的终结符
	private static char findLastStack(String stack) {
		char lastStack = 0;
		for (int j = stack.length() - 1; j >= 0; j--) {
			if (VN.contains("" + stack.charAt(j))) {
				lastStack = stack.charAt(j);
				break;
			}
		}
		return lastStack;
	}
}
