package com.fan.demo.dto;

public class ByteMessage extends AbstractMessage {

	public ByteMessage(int size) {
		a = new byte[size];
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	byte[] a;

	public byte[] getA() {
		return a;
	}

	public void setA(byte[] a) {
		this.a = a;
	}

}
