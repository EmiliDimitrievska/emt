package com.example.labassignment.events;

import org.springframework.context.ApplicationEvent;

public class AuthorEvent extends ApplicationEvent {
    public AuthorEvent(Object source){
        super(source);
    }
}
