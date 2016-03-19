<?xml version="1.0" encoding="gbk"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" indent="yes" encoding="gbk" />
	<xsl:template match="business/body">
		<xsl:variable name="fpmxSize">
			<xsl:value-of disable-output-escaping="yes"
				select="count(//fyxm/group)" />
		</xsl:variable>
		<business id="FPKJ" comment="发票开具">
			<REQUEST_COMMON_FPKJ class="REQUEST_COMMON_FPKJ">
				<COMMON_FPKJ_FPT class="COMMON_FPKJ_FPT">
					<FPQQLSH>
						<xsl:value-of select="fpqqlsh" />
					</FPQQLSH>
					<KPLX>
						<xsl:value-of select="kplx" />
					</KPLX>
					<XSF_NSRSBH>
						<xsl:value-of select="xhdwsbh" />
					</XSF_NSRSBH>
					<XSF_MC>
						<xsl:value-of select="xhdwmc" />
					</XSF_MC>
					<XSF_DZDH>
						<xsl:value-of select="xhdwdzdh" />
					</XSF_DZDH>
					<XSF_YHZH>
						<xsl:value-of select="xhdwyhzh" />
					</XSF_YHZH>
					<GMF_NSRSBH>
						<xsl:value-of select="ghdwsbh" />
					</GMF_NSRSBH>
					<GMF_MC>
						<xsl:value-of select="ghdwmc" />
					</GMF_MC>
					<GMF_DZDH>
						<xsl:value-of select="ghdwdzdh" />
					</GMF_DZDH>
					<GMF_YHZH>
						<xsl:value-of select="ghdwyhzh" />
					</GMF_YHZH>
					<GMF_YX></GMF_YX>
					<GMF_SJH>
						<xsl:value-of select="sjh" />
					</GMF_SJH>
					<KPR>
						<xsl:value-of select="kpr" />
					</KPR>
					<SKR>
						<xsl:value-of select="skr" />
					</SKR>
					<FHR>
						<xsl:value-of select="fhr" />
					</FHR>
					<YFP_DM>
						<xsl:value-of select="yfpdm" />
					</YFP_DM>
					<YFP_HM>
						<xsl:value-of select="yfphm" />
					</YFP_HM>
					<HJJE>
						<xsl:value-of select="hjje" />
					</HJJE>
					<HJSE>
						<xsl:value-of select="hjse" />
					</HJSE>
					<JSHJ>
						<xsl:value-of select="jshj" />
					</JSHJ>
					<BZ>
						<xsl:value-of select="bz" />
					</BZ>
				</COMMON_FPKJ_FPT>
				<COMMON_FPKJ_XMXXS class="COMMON_FPKJ_XMXX" size="{$fpmxSize}">
					<xsl:for-each select="fyxm/group">
						<COMMON_FPKJ_XMXX>
							<FPHXZ><xsl:value-of select="fphxz" /></FPHXZ>
							<XMMC><xsl:value-of select="spmc" /></XMMC>
							<GGXH><xsl:value-of select="ggxh" /></GGXH>
							<DW><xsl:value-of select="dw" /></DW>
							<XMSL><xsl:value-of select="spsl" /></XMSL>
							<XMDJ><xsl:value-of select="dj" /></XMDJ>
							<XMJE><xsl:value-of select="je" /></XMJE>
							<SL><xsl:value-of select="sl" /></SL>
							<SE><xsl:value-of select="se" /></SE>
						</COMMON_FPKJ_XMXX>
					</xsl:for-each>
				</COMMON_FPKJ_XMXXS>
			</REQUEST_COMMON_FPKJ>
		</business>
	</xsl:template>

</xsl:stylesheet>