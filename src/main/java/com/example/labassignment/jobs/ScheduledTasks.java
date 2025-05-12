package com.example.labassignment.jobs;

import com.example.labassignment.service.domain.BookService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    private final BookService bookDomainService;

    public ScheduledTasks(BookService bookDomainService) {
        this.bookDomainService = bookDomainService;
    }

    @Scheduled(cron = "0 * * * * *")
    public void refreshMaterializedView() {
        bookDomainService.refreshMaterializedView();
    }
}
