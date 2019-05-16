package lab3;

import java.util.Scanner;

public class lab3 {
	private static String[][] LLTable = new String[100][100];
	private static String stack = "#E";// 栈
	private static String VT = "EATBF";// 非终结符
	private static String VN = "i+*()#";// 终结符
//	private static String[] gram = { "E:TA", "E':+TA", "A: ", "T:FB", "B:F*T'", "B: ", "F:i", "F:(E)" };

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		initLLTable();// 初始化预测分析表
		String[] processStack = new String[100];// 分析栈
		String[] nowChar = new String[100];// 当前输入
		String[] result = new String[100];// 推导所用产生式或匹配

		System.out.println("请输入一个句子");
		String lang = input.nextLine();
		int i = 0, n = -1;
		char lastStack, firstLang;// 栈的最后一位字符，句子的第一位字符
		String str;
		boolean flag = false;// 能否识别标志
		while (i < lang.length()) {
			n++;
			lastStack = stack.charAt(stack.length() - 1);
			firstLang = lang.charAt(i);
			processStack[n] = stack;
			nowChar[n] = "" + firstLang;
			// 如果栈的最后一位是终结符
			if (VN.contains("" + lastStack)) {
				if (lastStack == '#' && firstLang == '#') {
					result[n] = "接受";
					System.out.println("可以识别！");
					flag = true;
					break;
				} else if (lastStack == '#' || firstLang == '#') {
					System.out.println("不能识别！");
					break;

				} else if (lastStack == firstLang) {
					result[n] = "" + lastStack + "匹配";
					stack = stack.substring(0, stack.length() - 1);
					i++;
				}
			} else {// 否则
				str = LLTable[VT.indexOf(lastStack)][VN.indexOf(firstLang)];
				if (str != null) {
					result[n] = "" + lastStack + "->" + str;
					StringBuilder b = new StringBuilder(str);
					stack = stack.substring(0, stack.length() - 1);
					stack += b.reverse().toString();
					stack = stack.trim();//因为用空格表示空，因此在这要把空格去掉
				} else {// 为空则表示不能识别
					System.out.println("不能识别！");
					break;
				}

			}
		}

		// 如果能识别则打印分析过程
		if (flag) {
			System.out.println("步骤\t分析栈\t当前输入\t推导所用产生式或匹配");
			for (int j = 0; j <= n; j++) {
				System.out.println(j + 1 + "\t" + processStack[j] + "\t" + nowChar[j] + "\t" + result[j]);
			}
		}

	}
	
	//构造预测分析表，用空格表示空
	private static void initLLTable() {
		LLTable[0][0] = "TA";
		LLTable[2][0] = "FB";
		LLTable[4][0] = "i";
		LLTable[1][1] = "+TA";
		LLTable[3][1] = " ";
		LLTable[3][2] = "*FB";
		LLTable[0][3] = "TA";
		LLTable[2][3] = "FB";
		LLTable[4][3] = "(E)";
		LLTable[1][4] = " ";
		LLTable[3][4] = " ";
		LLTable[1][5] = " ";
		LLTable[3][5] = " ";
	}

}
