package mk.ukim.finki.emt.jobs;

import mk.ukim.finki.emt.service.BookService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final BookService bookService;

    public ScheduledTasks(BookService bookService) {
        this.bookService = bookService;
    }

    @Scheduled(fixedDelay = 5000)
    public void refreshMaterializedView() {
        //this.productService.refreshMaterializedView();
    }
}
