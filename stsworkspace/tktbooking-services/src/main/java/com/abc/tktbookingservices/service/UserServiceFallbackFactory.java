package com.abc.tktbookingservices.service;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import com.abc.tktbookingservices.model.User;

@Component
public class UserServiceFallbackFactory implements FallbackFactory<UserServiceConsumer>{

	@Override
	public UserServiceConsumer create(Throwable cause) {
		return new UserServiceConsumer() {
			
			@Override
			public User getUserDetails(int userId) {
				// TODO Auto-generated method stub
				return new User();
			}
		};
	}

}
