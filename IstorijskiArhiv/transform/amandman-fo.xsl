<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  	xmlns:ns2="http://www.gradskaskupstina.rs/"
    xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">
    
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="amandman-page">
                    <fo:region-body margin="1in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            
            <fo:page-sequence master-reference="amandman-page">
               <fo:flow flow-name="xsl-region-body">
               	 <fo:block font-family="sans-serif" text-align="center"  font-size="18px" font-weight="bold" padding="30px">
                        Amandman
                 </fo:block>
               	 <fo:block font-family="sans-serif" text-align="center"  font-size="10px" padding="30px">
                        Pravni osnov: <xsl:value-of select="ns2:Amandman/ns2:PravniOsnov"/>
                 </fo:block>
                 <fo:block font-family="sans-serif" text-align="center"  font-size="10px" font-weight="bold" padding="30px">
                  		Amandman na akt: <xsl:value-of select="ns2:Amandman/ns2:SadrzajAmandmana/@NazivAkta"/>
                 </fo:block>
                 <fo:block font-family="sans-serif" text-align="center"  font-size="10px"  padding="30px">
                 		Predmet izmene:  <xsl:value-of select="ns2:Amandman/ns2:SadrzajAmandmana/ns2:PredmetIzmene"/>
                 </fo:block>
                 <fo:block font-family="sans-serif" text-align="center"  font-size="10px"  padding="30px">
                 		Cilj izmene:  <xsl:value-of select="ns2:Amandman/ns2:SadrzajAmandmana/ns2:CiljIzmene"/>
                 </fo:block>
                 
                <xsl:for-each select="ns2:Amandman/ns2:SadrzajAmandmana/ns2:GlavaAmandman"> 
                 	<fo:block font-family="sans-serif" text-align="center"  font-size="10px" padding="30px">
                 		Glava <xsl:value-of select="@Oznaka"/>:
                 		<xsl:for-each select="ns2:OdeljakAmandman">
                 			<fo:block font-family="sans-serif" text-align="center"  font-size="10px" padding="30px">
  					 		Odeljak <xsl:value-of select="@OznakaOdeljka"/>
  					 		<xsl:for-each select="ns2:ClanAmandman">
  					 			<fo:block font-family="sans-serif" text-align="center"  font-size="10px"  padding="30px">
  					 				Clan <xsl:value-of select="@OznakaClana"/>.
  					 				<fo:block font-family="sans-serif" text-align="center"  font-size="10px" padding="30px">
  					 				Tekst izmene: <xsl:value-of select="ns2:IzmenaClana/ns2:IzmenaSadrzaja"/>
  					 				</fo:block>
  					 			</fo:block>
  					 			<xsl:for-each select="ns2:StavAmandman">
  					 				<fo:block font-family="sans-serif" text-align="center"  font-size="10px" padding="30px">
  					 				Stav <xsl:value-of select="@OznakaStava"/>
  					 					<fo:block font-family="sans-serif" text-align="center"  font-size="10px"  padding="30px">
  					 						Tekst izmene: <xsl:value-of select="ns2:IzmenaStava/ns2:IzmenaSadrzaja"/>
										</fo:block>  					 				
  					 				</fo:block>
  					 				<xsl:for-each select="ns2:TackaAmandman">
  					 						<fo:block font-family="sans-serif" text-align="center" font-weight="bold"  font-size="10px"  padding="30px">
  					 						Tacka <xsl:value-of select="@OznakaTacke"/>.
  					 						</fo:block>
  					 						<fo:block font-family="sans-serif" text-align="center" font-size="10px"  padding="30px">
  					 						Tekst izmene: <xsl:value-of select="ns2:IzmenaTacke/ns2:IzmenaSadrzaja"/>
  					 						</fo:block>
  					 						<xsl:for-each select="ns2:AlinejaAmandman">
  					 							<fo:block font-family="sans-serif" text-align="center" font-size="10px"  padding="30px">
  					 							Alineja <xsl:value-of select="@OznakaAlineje"/>
  					 							</fo:block>
  					 							<fo:block font-family="sans-serif" text-align="center" font-size="10px"  padding="30px">
  					 							Tekst izmene: <xsl:value-of select="ns2:Izmena/ns2:IzmenaSadrzaja"/>
  					 							</fo:block>
  					 						</xsl:for-each>
  					 				</xsl:for-each>
  					 			</xsl:for-each>
  					 		</xsl:for-each>
  					 		</fo:block>
  					 	</xsl:for-each>
                 	</fo:block>
                 	<xsl:for-each select="ns2:ClanAmandman">
  					 			<fo:block font-family="sans-serif" text-align="center"  font-size="10px"  padding="30px">
  					 				Clan <xsl:value-of select="@OznakaClana"/>.
  					 				<fo:block font-family="sans-serif" text-align="center"  font-size="10px" padding="30px">
  					 				Tekst izmene: <xsl:value-of select="ns2:IzmenaClana/ns2:IzmenaSadrzaja"/>
  					 				</fo:block>
  					 			</fo:block>
  					 			<xsl:for-each select="ns2:StavAmandman">
  					 				<fo:block font-family="sans-serif" text-align="center"  font-size="10px" padding="30px">
  					 				Stav <xsl:value-of select="@OznakaStava"/>
  					 					<fo:block font-family="sans-serif" text-align="center"  font-size="10px"  padding="30px">
  					 						Tekst izmene: <xsl:value-of select="ns2:IzmenaStava/ns2:IzmenaSadrzaja"/>
										</fo:block>  					 				
  					 				</fo:block>
  					 				<xsl:for-each select="ns2:TackaAmandman">
  					 						<fo:block font-family="sans-serif" text-align="center" font-weight="bold"  font-size="10px"  padding="30px">
  					 						Tacka <xsl:value-of select="@OznakaTacke"/>.
  					 						</fo:block>
  					 						<fo:block font-family="sans-serif" text-align="center" font-size="10px"  padding="30px">
  					 						Tekst izmene: <xsl:value-of select="ns2:IzmenaTacke/ns2:IzmenaSadrzaja"/>
  					 						</fo:block>
  					 						<xsl:for-each select="ns2:AlinejaAmandman">
  					 							<fo:block font-family="sans-serif" text-align="center" font-size="10px"  padding="30px">
  					 							Alineja <xsl:value-of select="@OznakaAlineje"/>
  					 							</fo:block>
  					 							<fo:block font-family="sans-serif" text-align="center" font-size="10px"  padding="30px">
  					 							Tekst izmene: <xsl:value-of select="ns2:Izmena/ns2:IzmenaSadrzaja"/>
  					 							</fo:block>
  					 						</xsl:for-each>
  					 				</xsl:for-each>
  					 			</xsl:for-each>
  					 		</xsl:for-each>
                 	
                </xsl:for-each>
                
                </fo:flow>
            </fo:page-sequence>
        
        
        </fo:root>
    </xsl:template>
  </xsl:stylesheet>              