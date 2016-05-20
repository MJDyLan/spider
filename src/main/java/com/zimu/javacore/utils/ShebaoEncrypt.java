package com.zimu.javacore.utils;


public class ShebaoEncrypt {
    /*private static ContextFactory contextFactory = ContextFactory.getGlobal();
    private static Scriptable scope;

    static {
        try {
            // 初始化上下文
            Context context = contextFactory.enterContext();
            // 初始化域
            scope = context.initStandardObjects(null);
            // 设置初始值
            context.setOptimizationLevel(-1);
            context.setLanguageVersion(Context.VERSION_1_7);
			String jsPath = ShebaoEncrypt.class.getResource("/").getPath() + "loginjs/shebao/jsencrpt.js";
            scope.put("navigator", scope, "{appName:Microsoft Internet Explorer}");
            scope.put("window", scope, "{crypto:{getRandomValues:false}}");
			context.evaluateReader(scope, new FileReader(jsPath), "encrypt.js", 1, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public static synchronized String stringToHex(String password) {
		String result = null;
		try {
			Context context = contextFactory.enterContext();
			String script = "stringToHex('" + password + "')";
			Object obj = context.evaluateString(scope, script, "encrypt", 1, null);
			result = Context.toString(obj);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Context.exit();
		}
		return result;
	}
	public static synchronized String encrypt(String key, String password) {
        String result = null;
        try {
            Context context = contextFactory.enterContext();
			String script = "encrypt('" + key + "','" + password + "')";
			Object obj = context.evaluateString(scope, script, "encrypt", 1, null);
            result = Context.toString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Context.exit();
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
		System.out.println(stringToHex(encrypt("1112987hnisihnisi", "Qiujisheng89")));
    }*/
}
