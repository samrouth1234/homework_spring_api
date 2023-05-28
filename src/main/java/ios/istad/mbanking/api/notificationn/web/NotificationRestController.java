package ios.istad.mbanking.api.notificationn.web;

import ios.istad.mbanking.BaseRest.BaseRest;
import ios.istad.mbanking.api.notificationn.NotificationService;
import ios.istad.mbanking.api.notificationn.web.CreateNotificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationRestController {
    private final NotificationService notificationService;

    @PostMapping
    public BaseRest<?> pushNotification(@RequestBody CreateNotificationDto notificationDto) {
        notificationService.pushNotification(notificationDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Notification has been pushed")
                .timestamp(LocalDateTime.now())
                .data(notificationDto.contents())
                .build();
    }
}

