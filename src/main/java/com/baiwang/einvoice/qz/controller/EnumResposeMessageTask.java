package com.baiwang.einvoice.qz.controller;

import java.util.concurrent.Callable;

public class EnumResposeMessageTask implements  Callable<String>{
	public String call() throws Exception {
		
        Thread.sleep(4000);
        return "Ready!";
    }
}
