package com.capstone.mobilestore.service;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import com.capstone.mobilestore.model.Mobile;

@Component
public class MobileServiceFallbackFactory implements FallbackFactory<MobileServiceConsumer>{

	@Override
	public MobileServiceConsumer create(Throwable cause) {
		return new MobileServiceConsumer() {
			
			@Override
			public Mobile getMobileDetails(long mobileId) {
				// TODO Auto-generated method stub
				return new Mobile();
			}
		};
	}

}
