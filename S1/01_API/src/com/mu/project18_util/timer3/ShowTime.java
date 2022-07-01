package src.com.mu.project18_util.timer3;

import java.time.LocalDateTime;
import java.util.TimerTask;

public class ShowTime extends TimerTask {
    @Override
    public void run() {
        //任务...
        System.out.println(LocalDateTime.now());
    }
}
