package lab5;

import java.util.Scanner;

public class lab5 {
	private static String[][] Action = new String[10][10];
	private static String[][] Goto = new String[10][10];
	private static String VT = "ab#";
	private static String VN = "SB";
	private static String[] Gram = { "S':S", "S:BB", "B:aB", "B:b" };

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		initAction(Action);
		initGoto(Goto);

		// ��ӡLR(1)������
		System.out.println("״̬\tAction\t\tgoto\t\t");
		System.out.println("\t" + VT.charAt(0) + "\t" + VT.charAt(1) + "\t" + VT.charAt(2) + "\t" + VN.charAt(0) + "\t"
				+ VN.charAt(1));
		for (int i = 0; i < 10; i++) {
			System.out.println(i + "\t" + Action[i][0] + "\t" + Action[i][1] + "\t" + Action[i][2] + "\t" + Goto[i][0]
					+ "\t" + Goto[i][1]);
		}

		// ��������
		String SStack = "0";// ״̬ջ
		String CStack = "#";// ����ջ
		System.out.println("������һ���ַ�");
		String lang = input.nextLine();// ���봮
		String[] a = new String[100];
		String[] b = new String[100];
		String[] c = new String[100];
		String[] d = new String[100];
		String[] e = new String[100];
		int i = 0, n = -1;
		boolean flag = false;
		while (i < lang.length()) {
			n++;
			a[n] = SStack;
			b[n] = CStack;
			c[n] = lang.substring(i);
			char lastSStack = SStack.charAt(SStack.length() - 1);// ״̬ջ�����һ��״̬
			char firstLang = lang.charAt(i);// ʣ�����봮�ĵ�һ���ַ�
//			System.out.println(lastSStack + SStack);
			int j = Integer.parseInt(lastSStack + "");
			d[n] = Action[j][VT.indexOf("" + firstLang)];
			if (d[n].contains("S")) {
				SStack += d[n].substring(d[n].indexOf("S") + 1);
				CStack += firstLang;
				e[n] = "";
				i++;
			} else if (d[n].contains("r")) {
				String nowGram = Gram[Integer.parseInt(d[n].substring(d[n].indexOf("r") + 1))];// ��ǰ����
				String[] str = nowGram.split(":");// str[0]��str[1]
				SStack = SStack.substring(0, SStack.length() - str[1].length());
				CStack = CStack.substring(0, CStack.length() - str[1].length());
				CStack += str[0];
				lastSStack = SStack.charAt(SStack.length() - 1);
				e[n] = Goto[Integer.parseInt("" + lastSStack)][VN.indexOf(str[0])];
				SStack += e[n];
			} else if ("acc".equals(d[n]) && "#S".equals(CStack) && "#".equals("" + firstLang)) {
				e[n] = "";
				flag = true;
				break;
			} else {
				break;
			}

		}

		//�������ʶ�𣬴�ӡ��������
		if (flag) {
			System.out.println("����ʶ��");
			System.out.println("����\t״̬ջ\t����ջ\t���봮\tACTION\tGOTO\t");
			for (int m = 0; m < n; m++) {
				System.out.println(m + "\t" + a[m] + "\t" + b[m] + "\t" + c[m] + "\t" + d[m] + "\t" + e[m]);
			}
		} else {
			System.out.println("����ʶ��");
		}

	}

	private static void initGoto(String[][] goto2) {
		goto2[0][0] = "1";
		goto2[0][1] = "2";
		goto2[2][1] = "5";
		goto2[3][1] = "8";
		goto2[6][1] = "9";

	}

	private static void initAction(String[][] action) {
		action[0][0] = "S3";
		action[2][0] = "S6";
		action[3][0] = "S3";
		action[4][0] = "r3";
		action[6][0] = "S6";
		action[8][0] = "r2";
		action[0][1] = "S4";
		action[2][1] = "S7";
		action[3][1] = "S4";
		action[4][1] = "r3";
		action[6][1] = "S7";
		action[8][1] = "r2";
		action[1][2] = "acc";
		action[5][2] = "r1";
		action[7][2] = "r3";
		action[9][2] = "r2";

	}
}
