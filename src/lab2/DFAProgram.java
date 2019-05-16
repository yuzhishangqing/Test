package lab2;

import java.util.Scanner;

public class DFAProgram {
	private static Scanner input = new Scanner(System.in);
	private static String K;// ״̬��
	private static String Z;// ��̬��
	private static String W;// ��ĸ��
	private static char S;// ��ʼ״̬
	private static int[][] f;// ״̬ת������

	public static void main(String[] args) {
		initDFA();
		int i = 0;
		System.out.println("������һ���ַ���");
		String str = input.nextLine();
		String process = "";
		char k = S;
		while (i < str.length()) {
			int a = K.indexOf(k);
			int b = W.indexOf(str.charAt(i));
			if (f[a][b] == -1) {
				System.out.println("���ַ�������ʶ��");
				break;
			} else {
				process += "f(" + k + "," + str.charAt(i++) + ")=" + K.charAt(f[a][b]) + "\t";
				k = K.charAt(f[a][b]);
			}
		}
		if (Z.contains("" + k)) {
			System.out.println("���ַ�������ʶ��\n" + process);
		} else {
			System.out.println("���ַ�������ʶ��");
		}

	}

	//��ʼ��DFA
	private static void initDFA() {
		System.out.println("������״̬����");
		K = input.nextLine();
		System.out.println("��������̬����");
		Z = input.nextLine();
		System.out.println("��������ĸ��");
		W = input.nextLine();
		System.out.println("�����뿪ʼ״̬");
		S = input.nextLine().charAt(0);
		initF();
	}

	//��ʼ��״̬ת����ϵ
	private static void initF() {
		f = new int[K.length()][K.length()];
		for (int i = 0; i < K.length(); i++) {
			for (int j = 0; j < K.length(); j++) {
				f[i][j] = -1;
			}
		}
		System.out.println("������״̬ת��������f(K,a)=K,��#����");
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
