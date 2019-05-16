package lab4;

import java.util.Scanner;

public class lab4 {
	private static String stack = "#";// ջ
	private static String VN = "+*|i()#";// �ս��
	private static String[][] OPG = { { ">", "<", "<", "<", "<", ">", ">" }, { ">", ">", "<", "<", "<", ">", ">" },
			{ ">", ">", "<", "<", "<", ">", ">" }, { ">", ">", ">", " ", " ", ">", ">" },
			{ "<", "<", "<", "<", "<", "=", " " }, { ">", ">", ">", " ", " ", ">", ">" },
			{ "<", "<", "<", "<", "<", " ", "=" }, };

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String[] processStack = new String[100];// ����ջ
		String[] prerelation = new String[100];// ���ȹ�ϵ
		String[] nowChar = new String[100];// ��ǰ����
		String[] LastChar = new String[100];// ʣ�����봮
		String[] result = new String[100];// �ƽ����Լ
		char lastStack = 0, lastStack1;
		boolean flag = true;
		System.out.println("������һ������");
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
				result[n] = "�ƽ�";
			} else if (">".equals(prerelation[n])) {
				stack = stack.substring(0, stack.indexOf(lastStack));
				lastStack1 = findLastStack(stack);
				if ("=".equals(OPG[VN.indexOf(lastStack1)][VN.indexOf(lastStack)])) {
					stack = stack.substring(0, stack.indexOf(lastStack1));
				} else {
					stack = stack.substring(0, stack.indexOf(lastStack1) + 1);
				}
				stack += "N";
				result[n] = "��Լ";
				if ("�ƽ�".equals(result[n - 1])) {
					LastChar[n] = lang.substring(i + 1);
				} else {
					LastChar[n] = LastChar[n - 1];
				}

			} else if ("=".equals(prerelation[n])) {

				if (lastStack == '#' && "#".equals(nowChar[n])) {
					result[n] = "����";
					LastChar[n] = LastChar[n - 1];
					System.out.println("����ʶ��");
					break;
				} else {
					stack += nowChar[n];
					LastChar[n] = lang.substring(++i);
					result[n] = "�ƽ�";
				}
			} else {
				flag = false;
				System.out.println("����ʶ��");
				break;
			}

		}

		//flagΪtrue��ʾ����ʶ��
		if (flag) {
			System.out.println("����\tջ\t���ȹ�ϵ\t��ǰ����\tʣ������\t�ƽ����Լ");
			for (int j = 0; j <= n; j++) {
				System.out.println(j + 1 + "\t" + processStack[j] + "\t" + prerelation[j] + "\t" + nowChar[j] + "\t"
						+ LastChar[j] + "\t" + result[j]);
			}
		}

	}

	//����ջβ���ս��
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
