//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.11 at 04:15:48 PM CEST 
//


package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TPredsednik_skupstine complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TPredsednik_skupstine">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.gradskaskupstina.rs/}TOdbornik">
 *       &lt;sequence>
 *         &lt;element name="Mandat" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPredsednik_skupstine", namespace = "http://www.gradskaskupstina.rs/", propOrder = {
    "mandat"
})
public class TPredsednikSkupstine
    extends TOdbornik
{

    @XmlElement(name = "Mandat", required = true)
    protected String mandat;

    /**
     * Gets the value of the mandat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMandat() {
        return mandat;
    }

    /**
     * Sets the value of the mandat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMandat(String value) {
        this.mandat = value;
    }

}
