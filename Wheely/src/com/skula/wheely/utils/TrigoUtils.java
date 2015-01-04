package com.skula.wheely.utils;

import com.skula.wheely.constants.Cnst;

public class TrigoUtils {

	private TrigoUtils() {
	}

	public static double getSpeed(double x, double y) {
		double res = Cnst.SPEED_MAX
				* (Math.sqrt((Cnst.X0 - x) * (Cnst.X0 - x) + (Cnst.Y0 - y)
						* (Cnst.Y0 - y)) / Cnst.RADIUS);
		if (y < Cnst.Y0) {
			return res;
		} else {
			return res * -1;
		}
	}

	public static double getAngle(double x, double y) {
		return Math.toDegrees(Math.atan2((Cnst.Y0 - y), (Cnst.X0 - x)));
	}

}