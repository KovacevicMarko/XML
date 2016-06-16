<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns2="http://www.gradskaskupstina.rs/">

<xsl:template match="/">
  <html>
  <body>
  Preambula:<xsl:value-of select="ns2:Akt/ns2:Preambula"/>  <br></br><br></br>
  Akt:<b> <xsl:value-of select="ns2:Akt/ns2:NazivAkt"/> </b>	<br></br><br></br>

  Deo <xsl:for-each select="ns2:Akt/ns2:Deo"> &#160;
  			<xsl:value-of select="@OznakaDeo"/>:  <xsl:value-of select="@NazivDeo"/> <br></br><br></br>
  				<xsl:for-each select="ns2:Glava"> &#160;
  				 <b> Glava </b>  <xsl:value-of select="@OznakaGlava"/>:  <xsl:value-of select="@NazivGlava"/>
  				    	  <xsl:for-each select="ns2:SadrzajGlava/ns2:Odeljak"> &#160;
  							 Odeljak <xsl:value-of select="@OznakaOdeljak"/>:  <xsl:value-of select="@NazivOdeljak"/>
  				   				<xsl:for-each select="ns2:Clan"> &#160;
  									Clan <xsl:value-of select="@OznakaClan"/>
  										<xsl:for-each select="ns2:Stav"> &#160;				   
  				   							Stav <xsl:value-of select="@OznakaStav"/> <br></br><br></br> 
  				   								 <xsl:value-of select="ns2:Sadrzaj/ns2:TekstStav/ns2:Tekst"/> <br></br><br></br>
  				   									<xsl:for-each select="ns2:Sadrzaj/ns2:Tacka"> 
&#160;&#160;&#160;&#160;&#160;&#160;&#160;			<b>	Tacka <xsl:value-of select="@OznakaTacka"/>.</b><br></br><br></br>	
  				   											<xsl:value-of select="ns2:Sadrzaj/ns2:TekstTacka/ns2:Tekst"/> <br></br><br></br>  
  				   												<xsl:for-each select="ns2:Sadrzaj/ns2:Alineja"> 
  				   													Alineja <xsl:value-of select="@OznakaAlineja"/>.<br></br><br></br>
																		<xsl:value-of select="ns2:SadrzajAlineja"/> <br></br><br></br>
  				   												</xsl:for-each>
													</xsl:for-each>	  				   									
  				   						</xsl:for-each>
  				   				</xsl:for-each>
  	  						</xsl:for-each>	
  	  						
  	  						<xsl:for-each select="ns2:SadrzajGlava/ns2:Clan"> &#160;
  									Clan <xsl:value-of select="@OznakaClan"/>
  										<xsl:for-each select="ns2:Stav"> &#160;				   
  				   							Stav <xsl:value-of select="@OznakaStav"/> <br></br><br></br> 
  				   								 <xsl:value-of select="ns2:Sadrzaj/ns2:TekstStav/ns2:Tekst"/> <br></br><br></br>
  				   									<xsl:for-each select="ns2:Sadrzaj/ns2:Tacka"> 
&#160;&#160;&#160;&#160;&#160;&#160;&#160;			<b>	Tacka <xsl:value-of select="@OznakaTacka"/>.</b><br></br><br></br>	
  				   											<xsl:value-of select="ns2:Sadrzaj/ns2:TekstTacka/ns2:Tekst"/> <br></br><br></br>  
  				   												<xsl:for-each select="ns2:Sadrzaj/ns2:Alineja"> 
  				   													Alineja <xsl:value-of select="@OznakaAlineja"/>.<br></br><br></br>
																		<xsl:value-of select="ns2:SadrzajAlineja"/> <br></br><br></br>
  				   												</xsl:for-each>
													</xsl:for-each>	  				   									
  				   						</xsl:for-each>
  				   			</xsl:for-each>  				   		
  	  			</xsl:for-each>
  	  	</xsl:for-each>	   
  	  
 <br></br><br></br><br></br><br></br>  
  	  
  <b>Predlagac: </b> &#160; <xsl:value-of select="ns2:Akt/ns2:PrelazneIZavrsneOdredbe/ns2:Predlagac/ns2:Ime"/> &#160; <xsl:value-of select="ns2:Akt/ns2:PrelazneIZavrsneOdredbe/ns2:Predlagac/ns2:Prezime"/> (<xsl:value-of select="ns2:Akt/ns2:PrelazneIZavrsneOdredbe/ns2:Predlagac/ns2:Stranka"/>)	<br></br>  
  	 U Novom Sadu,  <xsl:value-of select="ns2:Akt/ns2:PrelazneIZavrsneOdredbe/ns2:Datum"/>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>

