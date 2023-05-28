package ios.istad.mbanking.api.notificationn;

import ios.istad.mbanking.api.notificationn.web.CreateNotificationDto;

public interface NotificationService {
    boolean pushNotification(CreateNotificationDto notificationDto);
}
