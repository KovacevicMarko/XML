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
 * <p>Java class for TOdbornik complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TOdbornik">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.gradskaskupstina.rs/}TGradjanin">
 *       &lt;sequence>
 *         &lt;element name="Stranka" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Username" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TOdbornik", namespace = "http://www.gradskaskupstina.rs/", propOrder = {
    "stranka",
    "username"
})
public class TOdbornik
    extends TGradjanin
{

    @XmlElement(name = "Stranka", required = true)
    protected String stranka;
    @XmlElement(name = "Username", required = true)
    protected String username;

    /**
     * Gets the value of the stranka property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStranka() {
        return stranka;
    }

    /**
     * Sets the value of the stranka property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStranka(String value) {
        this.stranka = value;
    }

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

}
