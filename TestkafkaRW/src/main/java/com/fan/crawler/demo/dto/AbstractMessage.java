package com.fan.crawler.demo.dto;

import java.io.Serializable;

public class AbstractMessage implements Serializable {

	private volatile long seq = 0;;

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
