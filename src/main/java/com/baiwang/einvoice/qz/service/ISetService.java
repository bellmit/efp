package com.baiwang.einvoice.qz.service;

import com.baiwang.einvoice.qz.beans.PrintConfig;
import com.baiwang.einvoice.qz.beans.SkConfig;

public interface ISetService {


	int saveSksetting(SkConfig skconfig);

	Integer savePrintsetting(PrintConfig printconfig);


}
