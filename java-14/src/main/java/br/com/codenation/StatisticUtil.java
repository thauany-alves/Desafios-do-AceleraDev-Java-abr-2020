package br.com.codenation;

import java.util.Arrays;

public class StatisticUtil {

	public static int average(int[] elements) {
		return Arrays.stream(elements).sum() / elements.length;
	}

	public static int mode(int[] elements) {
		int moda= 0, maxCont = 0;

		for (int i : elements) {
			int count = 0;
			for (int j : elements) {
				if (i == j) ++count;
			}
			if (count > maxCont) {
				maxCont = count;
				moda = i;
			}
		}
		return moda;
	}

	public static int median(int[] elements) {
		Arrays.sort(elements);

		if (elements.length % 2 != 0)
			return elements[elements.length / 2];
		else
			return (elements[elements.length / 2] + elements[(elements.length / 2) - 1]) / 2;

	}
}