package com.fan.demo.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MessageCounter {

	private volatile long time;

	public void setNumber(long number) {
		this.number += number;
	}

	public void setCount(long count) {
		this.count += count;
	}

	private volatile long number;
	private volatile long count;
	String outMessage = "";

	Thread t;

	public MessageCounter(String outMessage, long intervalTime) {
		this.outMessage = outMessage;

		t = new Thread(new Runnable() {
			public void run() {
				while (true) {

					long tempTime = System.currentTimeMillis() - time;
					if (tempTime > intervalTime) {

						BigDecimal b = new BigDecimal(count);
						
						BigDecimal result=b.divide(new BigDecimal(1024*1024*10), 3, RoundingMode.HALF_UP);

						System.out.println(outMessage + ":时间=" + tempTime + ",数量=" + number + ",大小=" +count+",速度"+ result.toString()+"M/S");
						time = System.currentTimeMillis();
						number = 0;
						count = 0;
					}
				}
			}
		});
		t.setDaemon(true);
		t.start();
	}

}
