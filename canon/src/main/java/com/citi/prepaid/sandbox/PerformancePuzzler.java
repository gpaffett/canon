package com.citi.prepaid.sandbox;

public class PerformancePuzzler {

    protected static int global;

    public static void main(String[] args) {
	long t1 = System.nanoTime();

	int value = 0;
	for (int i = 0; i < 100 * 1000 * 1000; i++) {
	    value = calculate(value);
	}

	long t2 = System.nanoTime();
	System.out.println("Execution time: " + ((t2 - t1) * 1e-6)
		+ " milliseconds");
    }

    protected static int calculate(int arg) {
	//L1:  assert (arg >= 0) : "should be positive";
	if (arg < 0) throw new IllegalArgumentException("arg = " + arg + " < 0");

	global = arg * 6;
	global += 3;
	global /= 2;
	return arg + 2;
    }

}
