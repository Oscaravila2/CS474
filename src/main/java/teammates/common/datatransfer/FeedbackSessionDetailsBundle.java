package teammates.common.datatransfer;

import java.util.Comparator;
import java.util.List;

import teammates.common.datatransfer.attributes.FeedbackSessionAttributes;

/**
 * Represents details of a feedback session
 * Contains:
 * <br> * The basic info of the feedback session (as a {@link FeedbackSessionAttributes} object).
 * <br> * Feedback response statistics (as a {@link FeedbackSessionStats} object).
 */
public class FeedbackSessionDetailsBundle {

    public FeedbackSessionStats stats;
    public FeedbackSessionAttributes feedbackSession;

    public FeedbackSessionDetailsBundle(FeedbackSessionAttributes feedbackSession) {
        this.feedbackSession = feedbackSession;
        this.stats = new FeedbackSessionStats();
    }

    /**
     * Sorts feedback session based courseID (ascending), then by create time (ascending), deadline
     * (ascending), then by start time (ascending), then by feedback session name
     * (ascending). The sort by CourseID part is to cater the case when this
     * method is called with combined feedback sessions from many courses
     */
    public static void sortFeedbackSessionsByCreationTime(List<FeedbackSessionDetailsBundle> sessions) {
        sessions.sort(Comparator.comparing((FeedbackSessionDetailsBundle fsd) -> fsd.feedbackSession.getCourseId())
                .thenComparing(fsd -> fsd.feedbackSession.getCreatedTime())
                .thenComparing(fsd -> fsd.feedbackSession.getEndTime())
                .thenComparing(fsd -> fsd.feedbackSession.getStartTime())
                .thenComparing(fsd -> fsd.feedbackSession.getFeedbackSessionName()));
        
        for(int i = 0; i < sessions.size()-2; i++){
            assert sessions.get(i).getCourseId() <= sessions.get(i+1).getCourseId() : "List didn't sort correctly";
		}
    }

    @Override
    public String toString() {
        return "course:" + feedbackSession.getCourseId() + ", name:" + feedbackSession.getFeedbackSessionName()
                + System.lineSeparator() + "submitted/total: " + stats.submittedTotal + "/" + stats.expectedTotal;
    }
}
