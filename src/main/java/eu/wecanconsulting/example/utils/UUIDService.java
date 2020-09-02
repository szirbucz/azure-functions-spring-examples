package eu.wecanconsulting.example.utils;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class UUIDService {

	public UUID generate() {
		return UUID.randomUUID();
	}
}
