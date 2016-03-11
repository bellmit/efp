package com.baiwang.einvoice.qz.vo;

import java.io.Serializable;

public class Returnmsg implements Serializable{
	
	private static final long serialVersionUID = -3889395616240293759L;

	private String returncode;
	
	private String returnmsg;
	
	private Returndata returndata;

	public String getReturncode() {
		return returncode;
	}

	public Returnmsg setReturncode(String returncode) {
		this.returncode = returncode;
		return this;
	}

	public String getReturnmsg() {
		return returnmsg;
	}

	public Returnmsg setReturnmsg(String returnmsg) {
		this.returnmsg = returnmsg;
		return this;
	}

	public Returndata getReturndata() {
		return returndata;
	}

	public Returnmsg setReturndata(Returndata returndata) {
		this.returndata = returndata;
		return this;
	}
	
	
	
}
