import java.util.Comparator;

public class VisitorComparator implements Comparator<Visitor> {

    @Override
    public int compare(Visitor v1, Visitor v2) {
        // Validate null values
        if (v1 == null && v2 == null) return 0;
        if (v1 == null) return 1;
        if (v2 == null) return -1;

        // 1. Compare membership status (call isMember())
        boolean v1Member = v1.isMember();
        boolean v2Member = v2.isMember();
        if (v1Member != v2Member) {
            return v1Member ? -1 : 1;
        }

        // 2. Compare ages
        int ageCompare = Integer.compare(v1.getAge(), v2.getAge());
        if (ageCompare != 0) {
            return ageCompare;
        }

        // 3. Compare visitor IDs (call getVisitorId())
        return v1.getVisitorId().compareTo(v2.getVisitorId());
    }
}