//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.15 at 10:30:36 PM CEST 
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
 *         &lt;element name="TipIzmene" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    protected String tipIzmene;

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
     *     {@link String }
     *     
     */
    public String getTipIzmene() {
        return tipIzmene;
    }

    /**
     * Sets the value of the tipIzmene property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipIzmene(String value) {
        this.tipIzmene = value;
    }

}
