package com.capstone.mobilestore.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ItemNotFoundException extends RuntimeException{
	
	public ItemNotFoundException() {
        super("Item not found");
    }

    public ItemNotFoundException(String message) {
        super(message);
    }
}
