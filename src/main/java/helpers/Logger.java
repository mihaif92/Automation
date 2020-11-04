package helpers;

public class Logger {

	public static void log(String log) {
		System.out.println(log);
//		Log.info(log);
	}
	public static void log(boolean log) {
		System.out.println(log);
	}
	public static void log(int log) {
		System.out.println(log);
	}
	public static void log(Throwable e) {
		System.out.println(e.getMessage());
		e.printStackTrace();
	}

	public static void logRoi(String log) {
		System.out.println("Roi's results are:   " + log);
	}
}
