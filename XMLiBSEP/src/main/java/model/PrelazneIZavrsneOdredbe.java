//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.12 at 06:01:10 PM CEST 
//


package model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Predlagac" type="{http://www.gradskaskupstina.rs/}TOdbornik"/>
 *         &lt;element name="Datum" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "predlagac",
    "datum"
})
@XmlRootElement(name = "PrelazneIZavrsneOdredbe", namespace = "http://www.gradskaskupstina.rs/")
public class PrelazneIZavrsneOdredbe implements Serializable {

    @XmlElement(name = "Predlagac", namespace = "http://www.gradskaskupstina.rs/", required = true)
    protected TOdbornik predlagac;
    @XmlElement(name = "Datum", namespace = "http://www.gradskaskupstina.rs/", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar datum;

    /**
     * Gets the value of the predlagac property.
     * 
     * @return
     *     possible object is
     *     {@link TOdbornik }
     *     
     */
    public TOdbornik getPredlagac() {
        return predlagac;
    }

    /**
     * Sets the value of the predlagac property.
     * 
     * @param value
     *     allowed object is
     *     {@link TOdbornik }
     *     
     */
    public void setPredlagac(TOdbornik value) {
        this.predlagac = value;
    }

    /**
     * Gets the value of the datum property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatum() {
        return datum;
    }

    /**
     * Sets the value of the datum property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatum(XMLGregorianCalendar value) {
        this.datum = value;
    }

}
