package com.baiwang.einvoice.qz.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.stereotype.Controller;

@Controller
@Path("einvoice")
public class InvoiceResource {
	
	@Path("test")
	@GET
	public String test(){
		return "test";
	}
	
	
}
