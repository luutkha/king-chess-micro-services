package micro.service.reportservice.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ReportSchedule {

    @Scheduled(fixedRate = 5000) // Run every 5 seconds
//    @Scheduled(cron = "0 0 3 * * ?")

    public void runScheduledTask() {
        System.out.println("Executing scheduled task...");
        // Your business logic here
    }
}
