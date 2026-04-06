package com.example.labassignment.listeners;

import com.example.labassignment.events.AuthorEvent;
import com.example.labassignment.service.domain.AuthorService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AuthorEventListener {
    private final AuthorService authorService;

    public AuthorEventListener(AuthorService authorService) {
        this.authorService = authorService;
    }

    @EventListener(AuthorEvent.class)
    public void refreshMaterializedVIew(){
        authorService.refreshMaterializedView();
    }
}
