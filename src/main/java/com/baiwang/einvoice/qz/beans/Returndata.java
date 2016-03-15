package com.baiwang.einvoice.qz.beans;

import java.io.Serializable;

public class Returndata implements Serializable{

	private static final long serialVersionUID = 2473759974115651607L;
	
	private String fpdm;
	
	private String fphm;
	
	private String kprq;

	public String getFpdm() {
		return fpdm;
	}

	public Returndata setFpdm(String fpdm) {
		this.fpdm = fpdm;
		return this;
	}

	public String getFphm() {
		return fphm;
	}

	public Returndata setFphm(String fphm) {
		this.fphm = fphm;
		return this;
	}

	public String getKprq() {
		return kprq;
	}

	public Returndata setKprq(String kprq) {
		this.kprq = kprq;
		return this;
	}
	
	
	

}
