package com.salazart;

import org.apache.commons.lang3.StringUtils;

public class SplitStringUtils {

	public static void main(String[] args) {
		String text = "245.6";
		String[] outText = StringUtils.splitByCharacterType(text);
		System.out.println(outText[0]);
	}

}
