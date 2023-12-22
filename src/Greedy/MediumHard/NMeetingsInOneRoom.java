package Greedy.MediumHard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Meeting {
    int start;
    int finish;
    int pos;

    public Meeting(int start, int finish, int pos) {
        this.start = start;
        this.finish = finish;
        this.pos = pos;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "start=" + start +
                ", finish=" + finish +
                ", pos=" + pos +
                '}';
    }
}

class MyComparator implements Comparator<Meeting> {

    @Override
    public int compare(Meeting m1, Meeting m2) {
        Integer f1 = (Integer)m1.finish;
        Integer f2 = (Integer)m2.finish;

        return f1.compareTo(f2);
    }
}

public class NMeetingsInOneRoom {

    // meet = List of meetings
    // n = #meetings
    public static void solution1(List<Meeting> meetings) {
        List<Meeting> answer = new ArrayList<>();

        int n = meetings.size();

        // 1. Sort meetings based on their finishing time
        Collections.sort(meetings, new MyComparator());

        // 2.1 For the 1st meeting
        answer.add(meetings.get(0));
        int prev_end_time = meetings.get(0).finish;

        for(int i=1; i<n; i++) {
            // Current meeting = meetings[i]
            // If curr_meeting.start > prev_meeting.finish, This meeting can be conducted
            if(meetings.get(i).start > prev_end_time) {
                answer.add(meetings.get(i));
                prev_end_time = meetings.get(i).finish;
            }
        }

        // answer = Meetings conducted
        answer.stream()
                .forEach(x -> System.out.println(x));
    }
    public static void main(String[] args) {
        int[] start_times = {5, 1, 3, 0, 5, 8};
        int[] finish_times = {9, 2, 4, 6, 7, 9};

        List<Meeting> meetings = new ArrayList<>();
        for (int i=0; i<start_times.length; i++) {
            meetings.add(new Meeting(start_times[i], finish_times[i], i+1));
        }

        // Solution
        solution1(meetings);
    }
}
