package ios.istad.mbanking.api.notificationn.web;

import com.fasterxml.jackson.annotation.JsonProperty;
public record CreateNotificationDto(@JsonProperty("included_segments")
                                    String[] includedSegments,
                                    ContentDto contents) {
}
