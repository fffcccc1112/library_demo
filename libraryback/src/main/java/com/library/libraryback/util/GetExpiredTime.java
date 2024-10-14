package com.library.libraryback.util;

import java.time.LocalDateTime;

public class GetExpiredTime {
    private LocalDateTime time=LocalDateTime.now();
    public LocalDateTime expiredtime = LocalDateTime.now().plusMinutes(60);

    public LocalDateTime getExpiredtime() {
        return expiredtime;
    }

    public void setExpiredtime(LocalDateTime expiredtime) {
        this.expiredtime = expiredtime;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
