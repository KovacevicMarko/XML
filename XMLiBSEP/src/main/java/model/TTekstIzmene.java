//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.15 at 08:27:11 PM CEST 
//


package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TTekstIzmene complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TTekstIzmene">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IzmenaSadrzaja" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TipIzmene" type="{http://www.gradskaskupstina.rs/}TTipIzmene"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TTekstIzmene", namespace = "http://www.gradskaskupstina.rs/", propOrder = {
    "izmenaSadrzaja",
    "tipIzmene"
})
public class TTekstIzmene {

    @XmlElement(name = "IzmenaSadrzaja", required = true)
    protected String izmenaSadrzaja;
    @XmlElement(name = "TipIzmene", required = true)
    protected TTipIzmene tipIzmene;

    /**
     * Gets the value of the izmenaSadrzaja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIzmenaSadrzaja() {
        return izmenaSadrzaja;
    }

    /**
     * Sets the value of the izmenaSadrzaja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIzmenaSadrzaja(String value) {
        this.izmenaSadrzaja = value;
    }

    /**
     * Gets the value of the tipIzmene property.
     * 
     * @return
     *     possible object is
     *     {@link TTipIzmene }
     *     
     */
    public TTipIzmene getTipIzmene() {
        return tipIzmene;
    }

    /**
     * Sets the value of the tipIzmene property.
     * 
     * @param value
     *     allowed object is
     *     {@link TTipIzmene }
     *     
     */
    public void setTipIzmene(TTipIzmene value) {
        this.tipIzmene = value;
    }

}