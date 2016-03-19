package com.baiwang.einvoice.qz.utils.token;


public class EncodeTokenTest {

    public static void main(String args[]) {
        //供应商税控编号，需要与UBL中的 <cac:AccountingSupplierParty><cbc:CustomerAssignedAccountID> 一致
        String supplierTaxNumber = "0000000001000032";

        //该公司持有的token_key
        String tokenKey = "OMJXJ9YYALR4YX0M";

        String tokenValue = TokenHelper.generateToken(supplierTaxNumber, tokenKey);

        System.out.println("SUPPLIER_TAX_NUMBER: " + supplierTaxNumber);
        System.out.println("SUPPLIER_TOKEN_KEY: " + tokenKey);
        System.out.println("BWTS-TOKEN: " + tokenValue);
    }

}
