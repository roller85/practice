package id.co.okhome.okhome.Data;

import java.io.Serializable;

/**
 * Created by jhkim on 4/1/2016.
 */
public class UserOrder implements Serializable {
    private HomeInfo homeInfo;
    private HomeInfo homeInfo1;
    private PetInfo petInfo;
    private ScheduleInfo scheduleInfo;
    private ScheduleInfo scheduleInfo1;
    private ScheduleInfo scheduleInfo2;
    private CleaningDayInfo cleaningDayInfo;
    private AddressInfo addressInfo;
    private PriceInfo priceInfo;
    private TopUpInfo topUpInfo;

    public void AddTopUpExpectedInfo(int topUpCashExpected, int topUpPointExpected) {
        this.topUpInfo = new TopUpInfo(topUpCashExpected, topUpPointExpected);
    }

    public void AddTopUpUserInfo(String accountHolderName, String userBankName, String userAmountTransfer) {
        this.topUpInfo = new TopUpInfo(accountHolderName, userBankName, userAmountTransfer);
    }

    public class TopUpInfo {
        private int topUpCashExpected;
        private int topUpPointExpected;
        private String accountHolderName;
        private String userBankName;
        private String userAmountTransfer;

        public TopUpInfo(int topUpCashExpected, int topUpPointExpected) {
            this.topUpCashExpected = topUpCashExpected;
            this.topUpPointExpected = topUpPointExpected;
        }

        public TopUpInfo(String accountName, String bankName, String amountTransfer) {
            this.accountHolderName = accountName;
            this.userBankName = bankName;
            this.userAmountTransfer = amountTransfer;
        }
    }

    public void AddPriceInfo(int chargePerHour, int cleaningHours, int cleaningDays, int chargePerWeek) {
        this.priceInfo = new PriceInfo(chargePerHour, cleaningHours, cleaningDays, chargePerWeek);
    }

    public int GetChargePerWeek() {
        return priceInfo.chargePerWeek;
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

    public void AddHomeDetailInfo(String homeDetail) {
        this.homeInfo1 = new HomeInfo(homeDetail);
    }

    public class HomeInfo {
        private int numberOfBedroom;
        private int numberOfBathroom;
        private String homeType;
        private String homeSize;
        private boolean homeownerExistence;
        private String homeDetail;

        public HomeInfo(int numberOfBedroom, int numberOfBathroom, String homeType, String homeSize, boolean homeownerExistence) {
            this.numberOfBedroom = numberOfBedroom;
            this.numberOfBathroom = numberOfBathroom;
            this.homeType = homeType;
            this.homeSize = homeSize;
            this.homeownerExistence = homeownerExistence;
        }

        public HomeInfo(String homeDetail){
            this.homeDetail = homeDetail;
        }
    }

    public void AddPetInfo(boolean catExistence, boolean dogExistence, boolean otherExistence, boolean nonexist) {
        this.petInfo = new PetInfo(catExistence, dogExistence, otherExistence, nonexist);
    }

    public class PetInfo {
        private boolean catExistence;
        private boolean dogExistence;
        private boolean otherExistence;
        private boolean nonexist;

        public PetInfo(boolean catExistence, boolean dogExistence, boolean otherExistence, boolean nonexist) {
            this.catExistence = catExistence;
            this.dogExistence = dogExistence;
            this.otherExistence = otherExistence;
            this.nonexist = nonexist;
        }
    }

    public void AddPeriodInfo(int period, int duration) {
        this.scheduleInfo = new ScheduleInfo(period, duration);
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

    public int GetDurationInfo() {
        return scheduleInfo.duration;
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
        private int duration;
        private int visitDay1;
        private int visitDay2;
        private int visitDay3;
        private String timeDay1;
        private String timeDay2;
        private String timeDay3;


        public ScheduleInfo(int period1, int duration1) {
            this.period = period1;
            this.duration = duration1;
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
}
