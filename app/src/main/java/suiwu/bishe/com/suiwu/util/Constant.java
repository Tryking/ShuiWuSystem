package suiwu.bishe.com.suiwu.util;

/**
 * Created by Tryking on 2017/5/11.
 */

public class Constant {
    //不同的模拟器访问本地地址是不同的，Genymotion模拟器是“10.0.3.2”，如果用其他的模拟器这里需要修改
    //如果用笔记本电脑开启wifi，真机连接wifi也是可以访问电脑的本地地址的，但是地址值也不是“127.0.0.1”
    //请自行上网查找
//    public static final String mainPath = "http://10.0.3.2:8080/";
    //如果是真机访问电脑创建的wifi，需要打开cmd命令行，输入ipconfig，然后寻找Ipv4（以太网下面那个），将“10.196.229.5”替换
    public static final String mainPath = "http://10.196.229.5:8080/";

    public static final String loginPath = mainPath + "denglv/login";
    public static final String getBillPath = mainPath + "denglv/getzhangdan";

    public static final String PREF_LOGIN_SUCCESS = "pref_login";
    public static final String PREF_ACCOUNT_NAME = "pref_account";


    public static final int ErrorRequest = 0x99;

    public static final int NewsRequestSuccess = 0x00;
    public static final int NewsRequestFail = 0x01;

    public static final int LoginSuccess = 0x10;
    public static final int LoginFailure = 0x11;
}
