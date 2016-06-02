package id.co.okhome.okhome.Data;

/**
 * Created by jhkim on 4/19/2016.
 */
public class OrderInfo {
    private static OrderInfo mInstance = new OrderInfo();

    private HomeInfo homeInfo;
    private PetInfo petInfo;
    private HomeInfo homeInfo1;
    private ScheduleInfo scheduleInfo;
    private ScheduleInfo scheduleInfo1;
    private ScheduleInfo scheduleInfo2;
    private CleaningDayInfo cleaningDayInfo;
    private AddressInfo addressInfo;
    private PriceInfo priceInfo;
    private TopUpInfo topUpInfo;
    private TopUpInfo topUpInfo1;
    private TopUpInfo topUpInfo2;
    private UserInfo userInfo;
    private GuestInfo guestInfo;



    private OrderInfo() {

    }

    public void AddUserEmailInfo(String email) {
        this.userInfo = new UserInfo(email);
    }

    public class UserInfo {
        private String email;

        public UserInfo(String email) {
            this.email = email;
        }

    }

    public String GetUserEmailInfo() {
        return userInfo.email;
    }


    public void AddGuestInfo(String guest) {
        this.guestInfo = new GuestInfo(guest);
    }

    public String GetGuestInfo() {
        return guestInfo.guest;
    }

    public class GuestInfo {
        private String guest;

        public GuestInfo(String guest) {
            this.guest = guest;
        }

    }

    public void AddTopUpExpectedInfo(int topUpCashExpected, int topUpPointExpected, int bonus) {
        this.topUpInfo = new TopUpInfo(topUpCashExpected, topUpPointExpected, bonus);
    }

    public void AddTopUpUserInfo(String accountHolderName, String userBankName, String userAmountTransfer) {
        this.topUpInfo1 = new TopUpInfo(accountHolderName, userBankName, userAmountTransfer);
    }

    public void AddBalanceInfo(int balance) {
        this.topUpInfo2 = new TopUpInfo(balance);
    }

    public int GetExpectedCash() {
        return topUpInfo.topUpCashExpected;
    }

    public int GetBonus() {
        return topUpInfo.bonus;
    }

    public String GetAccountHolderName() {
        return topUpInfo1.accountHolderName;
    }

    public String GetUserBankName() {
        return topUpInfo1.userBankName;
    }

    public int GetBalance() {
        return topUpInfo2.balance;
    }

    public class TopUpInfo {
        private int topUpCashExpected;
        private int topUpPointExpected;
        private int bonus;
        private String accountHolderName;
        private String userBankName;
        private String userAmountTransfer;
        private int balance;

        public TopUpInfo(int topUpCashExpected, int topUpPointExpected, int bonus) {
            this.topUpCashExpected = topUpCashExpected;
            this.topUpPointExpected = topUpPointExpected;
            this.bonus = bonus;
        }

        public TopUpInfo(String accountName, String bankName, String amountTransfer) {
            this.accountHolderName = accountName;
            this.userBankName = bankName;
            this.userAmountTransfer = amountTransfer;
        }

        public TopUpInfo(int balance) {
            this.balance = balance;
        }
    }

    public void AddPriceInfo(int chargePerHour, int cleaningHours, int cleaningDays, int chargePerWeek) {
        this.priceInfo = new PriceInfo(chargePerHour, cleaningHours, cleaningDays, chargePerWeek);
    }

    public int GetChargePerWeek() {
        return priceInfo.chargePerWeek;
    }

    public int GetCleaningHours() {
        return priceInfo.cleaningHours;
    }

    public class PriceInfo {
        private int chargePerHour;
        private int cleaningHours;
        private int cleaningDays;
        private int chargePerWeek;

        public PriceInfo(int chargePerHour, int cleaningHours, int cleaningDays, int chargePerWeek) {
            this.chargePerHour = chargePerHour;
            this.cleaningHours = cleaningHours;
            this.cleaningDays = cleaningDays;
            this.chargePerWeek = chargePerWeek;
        }
    }

    public void AddHomeInfo(int numberOfBedroom, int numberOfBathroom, String homeType, String homeSize, boolean homeownerExistence) {
        this.homeInfo = new HomeInfo(numberOfBedroom, numberOfBathroom, homeType, homeSize, homeownerExistence);
    }

    public void AddHomeDetailInfo(String homeDetail, boolean checked) {
        this.homeInfo1 = new HomeInfo(homeDetail, checked);
    }

    public int GetNumberOfBedroom() {
        return homeInfo.numberOfBedroom;
    }

    public int GetNumberOfBathroom() {
        return homeInfo.numberOfBathroom;
    }

    public String GetHomeType() {
        return homeInfo.homeType;
    }

    public String GetHomeSize() {
        return homeInfo.homeSize;
    }

    public boolean GetHomeOwnerExistence() {
        return homeInfo.homeownerExistence;
    }

    public String GetHomeDetailInfo() {
        return homeInfo1.homeDetail;
    }

    public boolean GetUseOwnerTools() {
        return homeInfo1.checked;
    }

    public class HomeInfo {
        private int numberOfBedroom;
        private int numberOfBathroom;
        private String homeType;
        private String homeSize;
        private boolean homeownerExistence;
        private String homeDetail;
        private boolean checked;

        public HomeInfo(int numberOfBedroom, int numberOfBathroom, String homeType, String homeSize, boolean homeownerExistence) {
            this.numberOfBedroom = numberOfBedroom;
            this.numberOfBathroom = numberOfBathroom;
            this.homeType = homeType;
            this.homeSize = homeSize;
            this.homeownerExistence = homeownerExistence;
        }

        public HomeInfo(String homeDetail, boolean checked){
            this.homeDetail = homeDetail;
            this.checked = checked;
        }
    }

    public void AddPetInfo(String pet_category) {
        this.petInfo = new PetInfo(pet_category);
    }

    public String GetPetInfo() {
        return petInfo.pet_category;
    }

    public class PetInfo {
        private String pet_category;

        public PetInfo(String pet_category) {
            this.pet_category = pet_category;
        }
    }

    public void AddPeriodInfo(int period) {
        this.scheduleInfo = new ScheduleInfo(period);
    }

    public void AddVisitDayInfo(int visitDay1, int visitDay2, int visitDay3) {
        this.scheduleInfo1 = new ScheduleInfo(visitDay1, visitDay2, visitDay3);
    }

    public void AddVisitTimeInfo(String timeDay1, String timeDay2, String timeDay3) {
        this.scheduleInfo2 = new ScheduleInfo(timeDay1, timeDay2, timeDay3);
    }

    public String GetStartTimeDay1() {
        return scheduleInfo2.timeDay1;
    }

    public String GetStartTimeDay2() {
        return scheduleInfo2.timeDay2;
    }

    public String GetStartTimeDay3() {
        return scheduleInfo2.timeDay3;
    }

    public int GetPeriodInfo() {
        return scheduleInfo.period;
    }

    public int GetVisitDay1() {
        return scheduleInfo1.visitDay1;
    }

    public int GetVisitDay2() {
        return scheduleInfo1.visitDay2;
    }

    public int GetVisitDay3() {
        return scheduleInfo1.visitDay3;
    }

    public class ScheduleInfo {
        private int period;
        private int visitDay1;
        private int visitDay2;
        private int visitDay3;
        private String timeDay1;
        private String timeDay2;
        private String timeDay3;


        public ScheduleInfo(int period) {
            this.period = period;
        }

        public ScheduleInfo(int visitDay1, int visitDay2, int visitDay3) {
            this.visitDay1 = visitDay1;
            this.visitDay2 = visitDay2;
            this.visitDay3 = visitDay3;
        }

        public ScheduleInfo(String timeDay1, String timeDay2, String timeDay3) {
            this.timeDay1 = timeDay1;
            this.timeDay2 = timeDay2;
            this.timeDay3 = timeDay3;
        }

    }

    public void AddStartDayInfo(int day, int date, int month, int year) {
        this.cleaningDayInfo = new CleaningDayInfo(day, date, month, year);
    }

    public int GetStartDay() {
        return cleaningDayInfo.startDay;
    }
    public int GetStartMonth() {
        return cleaningDayInfo.startMonth;
    }
    public int GetStartDate() {
        return cleaningDayInfo.startDate;
    }

    public class CleaningDayInfo {
        private int startDay;
        private int startDate;
        private int startMonth;
        private int startYear;

        public CleaningDayInfo(int day, int date, int month, int year) {
            this.startDay = day;
            this.startDate = date;
            this.startMonth = month;
            this.startYear = year;
        }

    }

    public void AddAddressInfo(String region, String city, String district, String detailed_address, String location_detail, String host_name, String host_phone_number) {
        this.addressInfo = new AddressInfo(region, city, district, detailed_address, location_detail, host_name, host_phone_number);
    }

    public String GetPhoneNumber() {
        return addressInfo.host_phone_number;
    }

    public String GetHostName() {
        return addressInfo.host_name;
    }

    public String GetHostRegion() {
        return addressInfo.host_region;
    }

    public String GetHostCity() {
        return addressInfo.host_city;
    }

    public String GetHostDistrict(){
        return addressInfo.host_district;
    }

    public String GetHostDetailedAddress() {
        return addressInfo.host_detailed_address;
    }

    public String GetHostLocationDetail() {
        return addressInfo.host_location_detail;
    }


    public class AddressInfo {
        private String host_region;
        private String host_city;
        private String host_district;
        private String host_detailed_address;
        private String host_location_detail;
        private String host_name;
        private String host_phone_number;

        public AddressInfo(String region, String city, String district, String detailed_address, String location_detail,
                           String host_name, String host_phone_number) {
            this.host_region = region;
            this.host_city = city;
            this.host_district = district;
            this.host_detailed_address = detailed_address;
            this.host_location_detail = location_detail;
            this.host_name = host_name;
            this.host_phone_number = host_phone_number;
        }
    }

    public static OrderInfo getInstance() {
        return mInstance;
    }
}