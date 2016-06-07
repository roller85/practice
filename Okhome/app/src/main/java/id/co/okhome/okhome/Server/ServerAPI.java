package id.co.okhome.okhome.server;

public class ServerAPI{

    public static final String BASE_URL = "http://ec2-52-74-249-70.ap-southeast-1.compute.amazonaws.com";
    public static final String LOGIN = BASE_URL + "/login.php";
    public static final String LOGINAUTO = BASE_URL + "/loginAuto.php";
    public static final String SIGNUP = BASE_URL + "/signup.php";
    public static final String LOADUSERTOKEN = BASE_URL + "/loadUserToken.php";
    public static final String CHARGEINFOADD = BASE_URL + "/chargeInfoAdd.php";
    public static final String USERINFOADD = BASE_URL + "/userInfoAdd.php";
    public static final String GUESTINFOADD = BASE_URL + "/guestInfoAdd.php";
    public static final String LOADORDERHISTORY = BASE_URL + "/loadOrderHistory.php";
    public static final String LOADORDERFUTURE = BASE_URL + "/loadOrderFuture.php";

    /*
    public static final String GETBALANCE = BASE_URL + "/getBalance.php";
    public static final String BASICINFO1ADD = BASE_URL + "/basicInfo1Add.php";
    public static final String BASICINFO2ADD = BASE_URL + "/basicInfo2Add.php";
    public static final String CLEANINGPACKAGE = BASE_URL + "/cleaningPackageAdd.php";
    */
}
