package tuk.mentor.domain.schedule.entity;

public enum ScheduleType {
    PROGRAM("프로그램 일정"),
    INDIVIDUAL("개인 일정");

    private final String ScheduleType;

    ScheduleType(String ScheduleType) {
        this.ScheduleType = ScheduleType;
    }
}
