package lab3;

import java.util.Scanner;

public class lab3 {
	private static String[][] LLTable = new String[100][100];
	private static String stack = "#E";// ջ
	private static String VT = "EATBF";// ���ս��
	private static String VN = "i+*()#";// �ս��
//	private static String[] gram = { "E:TA", "E':+TA", "A: ", "T:FB", "B:F*T'", "B: ", "F:i", "F:(E)" };

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		initLLTable();// ��ʼ��Ԥ�������
		String[] processStack = new String[100];// ����ջ
		String[] nowChar = new String[100];// ��ǰ����
		String[] result = new String[100];// �Ƶ����ò���ʽ��ƥ��

		System.out.println("������һ������");
		String lang = input.nextLine();
		int i = 0, n = -1;
		char lastStack, firstLang;// ջ�����һλ�ַ������ӵĵ�һλ�ַ�
		String str;
		boolean flag = false;// �ܷ�ʶ���־
		while (i < lang.length()) {
			n++;
			lastStack = stack.charAt(stack.length() - 1);
			firstLang = lang.charAt(i);
			processStack[n] = stack;
			nowChar[n] = "" + firstLang;
			// ���ջ�����һλ���ս��
			if (VN.contains("" + lastStack)) {
				if (lastStack == '#' && firstLang == '#') {
					result[n] = "����";
					System.out.println("����ʶ��");
					flag = true;
					break;
				} else if (lastStack == '#' || firstLang == '#') {
					System.out.println("����ʶ��");
					break;

				} else if (lastStack == firstLang) {
					result[n] = "" + lastStack + "ƥ��";
					stack = stack.substring(0, stack.length() - 1);
					i++;
				}
			} else {// ����
				str = LLTable[VT.indexOf(lastStack)][VN.indexOf(firstLang)];
				if (str != null) {
					result[n] = "" + lastStack + "->" + str;
					StringBuilder b = new StringBuilder(str);
					stack = stack.substring(0, stack.length() - 1);
					stack += b.reverse().toString();
					stack = stack.trim();//��Ϊ�ÿո��ʾ�գ��������Ҫ�ѿո�ȥ��
				} else {// Ϊ�����ʾ����ʶ��
					System.out.println("����ʶ��");
					break;
				}

			}
		}

		// �����ʶ�����ӡ��������
		if (flag) {
			System.out.println("����\t����ջ\t��ǰ����\t�Ƶ����ò���ʽ��ƥ��");
			for (int j = 0; j <= n; j++) {
				System.out.println(j + 1 + "\t" + processStack[j] + "\t" + nowChar[j] + "\t" + result[j]);
			}
		}

	}
	
	//����Ԥ��������ÿո��ʾ��
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
