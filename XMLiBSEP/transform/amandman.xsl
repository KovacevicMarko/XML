<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns2="http://www.gradskaskupstina.rs/">

<xsl:template match="/">
  <html>
  <body>
  <h1>Amandman</h1>
  
  Pravni osnov: <xsl:value-of select="ns2:Amandman/ns2:PravniOsnov"/>	<br></br>
  Amandman na akt: <xsl:value-of select="ns2:Amandman/ns2:SadrzajAmandmana/@NazivAkta"/>  <br></br>
  Predmet izmene:  <xsl:value-of select="ns2:Amandman/ns2:SadrzajAmandmana/ns2:PredmetIzmene"/>  <br></br>
  Cilj izmene:  <xsl:value-of select="ns2:Amandman/ns2:SadrzajAmandmana/ns2:CiljIzmene"/>  <br></br>
  	 <xsl:for-each select="ns2:Amandman/ns2:SadrzajAmandmana/ns2:GlavaAmandman"> &#160;	
  				  Glava <xsl:value-of select="@Oznaka"/>:  <br></br>			  
  						<xsl:for-each select="ns2:OdeljakAmandman">
  					 		Odeljak <xsl:value-of select="@OznakaOdeljka"/>: <br></br>
  					 			<xsl:for-each select="ns2:ClanAmandman">
  					 				Clan <xsl:value-of select="@OznakaClana"/>. <br></br>
  					 					  Tekst izmena: <xsl:value-of select="ns2:IzmenaClana/ns2:IzmenaSadrzaja"/> <br></br>	
  					 						<xsl:for-each select="ns2:StavAmandman">
  					 							Stav <xsl:value-of select="@OznakaStava"/>: <br></br>
  					 							Tekst izmene: <xsl:value-of select="ns2:IzmenaStava/ns2:IzmenaSadrzaja"/> <br></br>
  					 							<xsl:for-each select="ns2:TackaAmandman">
  					 								Tacka <xsl:value-of select="@OznakaTacke"/>: <br></br>
  					 								Tekst izmene: <xsl:value-of select="ns2:IzmenaTacke/ns2:IzmenaSadrzaja"/> <br></br>
	  					 								<xsl:for-each select="ns2:AlinejaAmandman">
	  					 								Alineja <xsl:value-of select="@OznakaAlineje"/> :<br></br>
	  					 								Tekst izmene: <xsl:value-of select="ns2:Izmena/ns2:IzmenaSadrzaja"/> <br></br>
	  					 								</xsl:for-each>
  					 						</xsl:for-each>
  					 					</xsl:for-each>
  					 			</xsl:for-each>
  						</xsl:for-each>
  						<xsl:for-each select="ns2:ClanAmandman">
  					 				Clan: <xsl:value-of select="@OznakaClana"/> <br></br>
  					 					<xsl:variable name="izmenaClan" select="ns2:IzmenaClana/ns2:IzmenaSadrzaja"/>
  					 					<xsl:if test="not(string-length(izmenaClan) = 0)"> 
  					 					Tekst izmena: <xsl:value-of select="ns2:IzmenaClana/ns2:IzmenaSadrzaja"/> <br></br>
  					 					</xsl:if>
  					 						<xsl:for-each select="ns2:StavAmandman">
  					 							Stav <xsl:value-of select="@OznakaStava"/>: <br></br>
  					 							Tekst izmene: <xsl:value-of select="ns2:IzmenaStava/ns2:IzmenaSadrzaja"/> <br></br>
  					 							<xsl:for-each select="ns2:TackaAmandman">
  					 								Tacka <xsl:value-of select="@OznakaTacke"/>: <br></br>
  					 								Tekst izmene: <xsl:value-of select="ns2:IzmenaTacke/ns2:IzmenaSadrzaja"/> <br></br>
	  					 								<xsl:for-each select="ns2:AlinejaAmandman">
	  					 								Alineja <xsl:value-of select="@OznakaAlineje"/> :<br></br>
	  					 								Tekst izmene: <xsl:value-of select="ns2:Izmena/ns2:IzmenaSadrzaja"/> <br></br>
	  					 								</xsl:for-each>
  					 							</xsl:for-each>
  					 						</xsl:for-each>
  					 	</xsl:for-each>
		</xsl:for-each>
		
  <br></br><br></br>	
  <br></br><br></br>	
   Predlagac: <xsl:value-of select="ns2:Amandman/ns2:Predlagac/ns2:Ime"/> 	<br></br>
			 <xsl:value-of select="ns2:Amandman/ns2:Predlagac/ns2:Prezime"/>  	<br></br>
			 <xsl:value-of select="ns2:Amandman/ns2:Predlagac/ns2:Stranka"/>  	<br></br>	
  Datum predlaganja: <xsl:value-of select="ns2:Amandman/@DatumPredlaganja"/> <br></br>	
			
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>

