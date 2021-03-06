//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.15 at 08:30:32 PM CEST 
//


package model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TSadrzajGlave complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TSadrzajGlave">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="Odeljak" maxOccurs="unbounded" minOccurs="2">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Clan" type="{http://www.gradskaskupstina.rs/}TClan" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="OznakaOdeljak" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="NazivOdeljak" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Clan" type="{http://www.gradskaskupstina.rs/}TClan" maxOccurs="unbounded"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TSadrzajGlave", namespace = "http://www.gradskaskupstina.rs/", propOrder = {
    "odeljak",
    "clan"
})
public class TSadrzajGlave {

    @XmlElement(name = "Odeljak")
    protected List<TSadrzajGlave.Odeljak> odeljak;
    @XmlElement(name = "Clan")
    protected List<TClan> clan;

    /**
     * Gets the value of the odeljak property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the odeljak property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOdeljak().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TSadrzajGlave.Odeljak }
     * 
     * 
     */
    public List<TSadrzajGlave.Odeljak> getOdeljak() {
        if (odeljak == null) {
            odeljak = new ArrayList<TSadrzajGlave.Odeljak>();
        }
        return this.odeljak;
    }

    /**
     * Gets the value of the clan property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the clan property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClan().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TClan }
     * 
     * 
     */
    public List<TClan> getClan() {
        if (clan == null) {
            clan = new ArrayList<TClan>();
        }
        return this.clan;
    }


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
     *         &lt;element name="Clan" type="{http://www.gradskaskupstina.rs/}TClan" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *       &lt;attribute name="OznakaOdeljak" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="NazivOdeljak" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "clan"
    })
    public static class Odeljak {

        @XmlElement(name = "Clan", namespace = "http://www.gradskaskupstina.rs/", required = true)
        protected List<TClan> clan;
        @XmlAttribute(name = "OznakaOdeljak")
        protected String oznakaOdeljak;
        @XmlAttribute(name = "NazivOdeljak")
        protected String nazivOdeljak;

        /**
         * Gets the value of the clan property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the clan property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getClan().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TClan }
         * 
         * 
         */
        public List<TClan> getClan() {
            if (clan == null) {
                clan = new ArrayList<TClan>();
            }
            return this.clan;
        }

        /**
         * Gets the value of the oznakaOdeljak property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOznakaOdeljak() {
            return oznakaOdeljak;
        }

        /**
         * Sets the value of the oznakaOdeljak property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOznakaOdeljak(String value) {
            this.oznakaOdeljak = value;
        }

        /**
         * Gets the value of the nazivOdeljak property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNazivOdeljak() {
            return nazivOdeljak;
        }

        /**
         * Sets the value of the nazivOdeljak property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNazivOdeljak(String value) {
            this.nazivOdeljak = value;
        }

    }

}
