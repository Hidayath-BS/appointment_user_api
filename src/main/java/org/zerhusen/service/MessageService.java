package org.zerhusen.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Service;

@Service
public class MessageService {

public String sendSmsOtp(int otp,String phonenumber) {
		
		try {

			// Construct data
						String apiKey = "apikey=" + "UrA/tIRrMgE-I9p6Za178cjkeVMjrqc2ne5N4cjbOI";
						String message = "&message=" + "This is your OTP : "+otp;
						String sender = "&sender=" + "HKHMSG";
						String numbers = "&numbers=" + "91"+phonenumber;
						
						// Send data
						HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
						String data = apiKey + numbers + message + sender;
						conn.setDoOutput(true);
						conn.setRequestMethod("POST");
						conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
						conn.getOutputStream().write(data.getBytes("UTF-8"));
						final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
						final StringBuffer stringBuffer = new StringBuffer();
						String line;
						while ((line = rd.readLine()) != null) {
							stringBuffer.append(line);
						}
						rd.close();
						System.out.println(stringBuffer.toString());
						return stringBuffer.toString();
		}catch(Exception e) {
			System.out.println("Error SMS "+e);
			return "Error "+e;
		}
		
	}

	
}
