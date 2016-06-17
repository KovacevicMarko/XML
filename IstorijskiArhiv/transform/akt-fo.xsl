<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  	xmlns:ns2="http://www.gradskaskupstina.rs/"
    xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">
    
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="akt-page">
                    <fo:region-body margin="1in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            
            <fo:page-sequence master-reference="akt-page">
               <fo:flow flow-name="xsl-region-body">
               	 <fo:block font-family="sans-serif" text-align="center"  font-size="14px" font-weight="bold" padding="30px">
                        Akt:<xsl:value-of select="ns2:Akt/ns2:NazivAkt"/> 
                 </fo:block>
                 <fo:block font-family="sans-serif" text-align="center"  font-size="10px"  padding="30px">
                        Preambula:<xsl:value-of select="ns2:Akt/ns2:Preambula"/>  
                 </fo:block>
                 
                 	<xsl:for-each select="ns2:Akt/ns2:Deo"> 
                 	<fo:block font-family="sans-serif" text-align="center" font-size="12px"  padding="30px">
  					   Deo <xsl:value-of select="@OznakaDeo"/>:  <xsl:value-of select="@NazivDeo"/>
               	 			 <xsl:for-each select="ns2:Glava"> 
  								 <fo:block font-family="sans-serif" text-align="center" font-size="12px"  padding="30px">
  									Glava <xsl:value-of select="@OznakaGlava"/>:  <xsl:value-of select="@NazivGlava"/>
               	 <!--odeljak  -->			<xsl:for-each select="ns2:SadrzajGlava/ns2:Odeljak"> 
               	 								<fo:block font-family="sans-serif" text-align="center" font-size="10px" font-weight="bold" padding="30px">
  							 				     Odeljak <xsl:value-of select="@OznakaOdeljak"/>:  <xsl:value-of select="@NazivOdeljak"/>
  							 				    	<xsl:for-each select="ns2:Clan"> 
  							 				    	   <fo:block font-family="sans-serif" text-align="center" font-size="10px" font-weight="bold" padding="30px">
  													   Clan <xsl:value-of select="@OznakaClan"/>
  													   		<xsl:for-each select="ns2:Stav"> 
  													   		   <fo:block font-family="sans-serif" text-align="center" font-size="10px" padding="30px">			   
  				   												Stav <xsl:value-of select="@OznakaStav"/> 
  				   													 <fo:block font-family="sans-serif" text-align="center" font-size="10px" padding="30px">			   
  				   								 					 <xsl:value-of select="ns2:Sadrzaj/ns2:TekstStav/ns2:Tekst"/>
  													   				 </fo:block>
  													   				 <xsl:for-each select="ns2:Sadrzaj/ns2:Tacka"> 
  													   					<fo:block font-family="sans-serif" text-align="center" font-size="10px" padding="30px">
  													   				 	 Tacka <xsl:value-of select="@OznakaTacka"/>.
  													   				 		<fo:block font-family="sans-serif" text-align="center" font-size="10px" padding="30px">
  													   				 		<xsl:value-of select="ns2:Sadrzaj/ns2:TekstTacka/ns2:Tekst"/>
  													   				 			<xsl:for-each select="ns2:Sadrzaj/ns2:Alineja"> 
  													   				 				<fo:block font-family="sans-serif" text-align="center" font-size="10px" padding="30px">
  													   				 					Alineja <xsl:value-of select="@OznakaAlineja"/>.
  													   				 						<fo:block font-family="sans-serif" text-align="center" font-size="10px" padding="30px">
  													   				 					      <xsl:value-of select="ns2:SadrzajAlineja"/>
  													   				 						</fo:block>
  													   				 				</fo:block>
  													   				 			</xsl:for-each>
  													   				 		</fo:block>
  													   				 	</fo:block>
  													   				 </xsl:for-each>
  													   		   </fo:block>
  													   		</xsl:for-each>
  													   
  													   </fo:block>
  							 				    	</xsl:for-each>
  							 				    </fo:block>
  							 				</xsl:for-each>
  							 				
  							 				
  							 				<xsl:for-each select="ns2:SadrzajGlava/ns2:Clan"> 
  							 				    	   <fo:block font-family="sans-serif" text-align="center" font-size="10px"  padding="30px">
  													   Clan <xsl:value-of select="@OznakaClan"/>
  													   		<xsl:for-each select="ns2:Stav"> 
  													   		   <fo:block font-family="sans-serif" text-align="center" font-size="10px" padding="30px">			   
  				   												Stav <xsl:value-of select="@OznakaStav"/> 
  				   													 <fo:block font-family="sans-serif" text-align="center" font-size="10px" padding="30px">			   
  				   								 					 <xsl:value-of select="ns2:Sadrzaj/ns2:TekstStav/ns2:Tekst"/>
  													   				 </fo:block>
  													   				 <xsl:for-each select="ns2:Sadrzaj/ns2:Tacka"> 
  													   					<fo:block font-family="sans-serif" text-align="center" font-size="10px" padding="30px">
  													   				 	 Tacka <xsl:value-of select="@OznakaTacka"/>.
  													   				 		<fo:block font-family="sans-serif" text-align="center" font-size="10px" padding="30px">
  													   				 		<xsl:value-of select="ns2:Sadrzaj/ns2:TekstTacka/ns2:Tekst"/>
  													   				 			<xsl:for-each select="ns2:Sadrzaj/ns2:Alineja"> 
  													   				 				<fo:block font-family="sans-serif" text-align="center" font-size="10px" padding="30px">
  													   				 					Alineja <xsl:value-of select="@OznakaAlineja"/>.
  													   				 						<fo:block font-family="sans-serif" text-align="center" font-size="10px" padding="30px">
  													   				 					      <xsl:value-of select="ns2:SadrzajAlineja"/>
  													   				 						</fo:block>
  													   				 				</fo:block>
  													   				 			</xsl:for-each>
  													   				 		</fo:block>
  													   				 	</fo:block>
  													   				 </xsl:for-each>
  													   		   </fo:block>
  													   		</xsl:for-each>
  													   
  													   </fo:block>
  							 				    	</xsl:for-each>
               	 					</fo:block>
                 			 	 </xsl:for-each> 
                 	</fo:block>
                  	</xsl:for-each>
                  
                 	
                 
                </fo:flow>
            </fo:page-sequence>
        
        
        </fo:root>
    </xsl:template>
  </xsl:stylesheet>              