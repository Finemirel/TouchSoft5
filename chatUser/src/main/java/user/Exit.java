package user;

public class Exit {
	
	private static boolean needExit;

	public static boolean isNeedExit() {
		return needExit;
	}

	public static void setNeedExit(boolean needExit) {
		Exit.needExit = needExit;
	}

}
