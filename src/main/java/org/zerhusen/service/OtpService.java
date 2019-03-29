package org.zerhusen.service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class OtpService {

	private LoadingCache<String, Integer> otpCache = 
			CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(5, TimeUnit.MINUTES)
			.build(new CacheLoader<String, Integer>(){

				@Override
				public Integer load(String key) throws Exception {
					// TODO Auto-generated method stub
					return 0;
				}
				
			});
	
	private LoadingCache<String, Integer> otpfpCache = 
			CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(5, TimeUnit.MINUTES)
			.build(new CacheLoader<String, Integer>(){

				@Override
				public Integer load(String key) throws Exception {
					// TODO Auto-generated method stub
					return 0;
				}
				
			});
	
	
//	this method generates and return otp 
	
	public int generateOTP(String key) {
		
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		otpCache.put(key, otp);
		System.out.println(otpCache.asMap());
		System.out.println(otp);
		return otp;
	}
	
//	this method gets the otp
	public Integer getOtp(String key)  {
		try {
			System.out.println(otpCache.asMap());

			TimeUnit.MILLISECONDS.sleep(100);
			
			int otp = otpCache.get(key);
			
			return otp;
		}catch (Exception e) {
			return 0;
		}
	}
	
//	this method used to clear otp which are successfully matched
	public void clearOtp(String key) {
		otpCache.invalidate(key);
	}
	
	
	
	
//	for forgot password links
	
	public int genfpOtp(String key) {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		otpfpCache.put(key, otp);
		System.out.println(otpfpCache.asMap());
		System.out.println(otp);
		return otp;
	}
	
	public int getfpOtp(String key) {
		try {
			System.out.println(otpfpCache.asMap());

			TimeUnit.MILLISECONDS.sleep(100);
			
			int otp = otpfpCache.get(key);
			
			return otp;
		}catch (Exception e) {
			return 0;
		}
	}
	
	public void clearfpOtp(String key) {
		otpfpCache.invalidate(key);
	}

	
}
