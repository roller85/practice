package id.co.okhome.okhome.Data;

import java.io.Serializable;

/**
 * Created by jhkim on 4/1/2016.
 */
public class UserOrder implements Serializable {
    private HomeInfo homeInfo;
    private PetInfo petInfo;
    private ScheduleInfo scheduleInfo;
    private CleaningDayInfo cleaningDayInfo;

    public void AddHomeInfo(int numberOfBedroom, int numberOfBathroom, String homeType, String homeSize, boolean homeownerExistence) {
        this.homeInfo = new HomeInfo(numberOfBedroom, numberOfBathroom, homeType, homeSize, homeownerExistence);
    }

    public void AddHomeDetailInfo(String homeDetail) {
        this.homeInfo = new HomeInfo(homeDetail);
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

    public void AddPeriodInfo(int period) {
        this.scheduleInfo = new ScheduleInfo(period);
    }

    public void AddVisitDayInfo(int visitDay1, int visitDay2, int visitDay3) {
        this.scheduleInfo = new ScheduleInfo(visitDay1, visitDay2, visitDay3);
    }

    public void AddVisitTimeInfo(String timeDay1, String timeDay2, String timeDay3) {
        this.scheduleInfo = new ScheduleInfo(timeDay1, timeDay2, timeDay3);
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

    public void AddStartDayInfo(int day, int month, int year) {
        this.cleaningDayInfo = new CleaningDayInfo(day, month, year);
    }

    public class CleaningDayInfo {
        private int startDay;
        private int startMonth;
        private int startYear;

        public CleaningDayInfo(int day, int month, int year) {
            this.startDay = day;
            this.startMonth = month;
            this.startYear = year;
        }
    }
}
