package com.constants;

import java.time.Duration;

public class FrameworkConstants {
	private static final String RESOURNCEPATH = System.getProperty("user.dir");
   
	private static final String TESTDATAJSON= RESOURNCEPATH + "/testData/TestData.json";
	private static final Duration EXPLICITWAIT=Duration.ofSeconds(10);
	 public static String getTestdatajson() {
			return TESTDATAJSON;
		}
	public static Duration getExplicitwait() {
		return EXPLICITWAIT;
	}
}
