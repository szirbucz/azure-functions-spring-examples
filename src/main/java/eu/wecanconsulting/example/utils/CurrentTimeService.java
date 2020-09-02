package eu.wecanconsulting.example.utils;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class CurrentTimeService {
	public long getCurrentTime() {
		return new Date().getTime();
	}

}
