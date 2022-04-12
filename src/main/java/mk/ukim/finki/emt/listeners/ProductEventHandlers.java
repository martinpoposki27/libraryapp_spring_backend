package mk.ukim.finki.emt.listeners;


import mk.ukim.finki.emt.model.events.BookCreatedEvent;
import mk.ukim.finki.emt.service.BookService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProductEventHandlers {

    private final BookService bookService;

    public ProductEventHandlers(BookService bookService) {
        this.bookService = bookService;
    }

    @EventListener
    public void onProductCreated(BookCreatedEvent event) {
        this.bookService.refreshMaterializedView();
    }
}
