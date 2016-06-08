//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.08 at 01:06:07 AM CEST 
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
 *         &lt;element ref="{http://www.gradskaskupstina.rs/}referenca" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Odeljak" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Sadrzaj" type="{http://www.gradskaskupstina.rs/}TSadrzajOdeljka"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Oznaka" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="Naziv" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Clan" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Sadrzaj" type="{http://www.gradskaskupstina.rs/}TSadrzajClana"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Oznaka" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "referenca",
    "odeljak",
    "clan"
})
public class TSadrzajGlave {

    protected List<Referenca> referenca;
    @XmlElement(name = "Odeljak")
    protected List<TSadrzajGlave.Odeljak> odeljak;
    @XmlElement(name = "Clan")
    protected List<TSadrzajGlave.Clan> clan;

    /**
     * Gets the value of the referenca property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the referenca property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReferenca().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Referenca }
     * 
     * 
     */
    public List<Referenca> getReferenca() {
        if (referenca == null) {
            referenca = new ArrayList<Referenca>();
        }
        return this.referenca;
    }

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
     * {@link TSadrzajGlave.Clan }
     * 
     * 
     */
    public List<TSadrzajGlave.Clan> getClan() {
        if (clan == null) {
            clan = new ArrayList<TSadrzajGlave.Clan>();
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
     *         &lt;element name="Sadrzaj" type="{http://www.gradskaskupstina.rs/}TSadrzajClana"/>
     *       &lt;/sequence>
     *       &lt;attribute name="Oznaka" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "sadrzaj"
    })
    public static class Clan {

        @XmlElement(name = "Sadrzaj", namespace = "http://www.gradskaskupstina.rs/", required = true)
        protected TSadrzajClana sadrzaj;
        @XmlAttribute(name = "Oznaka")
        protected String oznaka;

        /**
         * Gets the value of the sadrzaj property.
         * 
         * @return
         *     possible object is
         *     {@link TSadrzajClana }
         *     
         */
        public TSadrzajClana getSadrzaj() {
            return sadrzaj;
        }

        /**
         * Sets the value of the sadrzaj property.
         * 
         * @param value
         *     allowed object is
         *     {@link TSadrzajClana }
         *     
         */
        public void setSadrzaj(TSadrzajClana value) {
            this.sadrzaj = value;
        }

        /**
         * Gets the value of the oznaka property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOznaka() {
            return oznaka;
        }

        /**
         * Sets the value of the oznaka property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOznaka(String value) {
            this.oznaka = value;
        }

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
     *         &lt;element name="Sadrzaj" type="{http://www.gradskaskupstina.rs/}TSadrzajOdeljka"/>
     *       &lt;/sequence>
     *       &lt;attribute name="Oznaka" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="Naziv" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "sadrzaj"
    })
    public static class Odeljak {

        @XmlElement(name = "Sadrzaj", namespace = "http://www.gradskaskupstina.rs/", required = true)
        protected TSadrzajOdeljka sadrzaj;
        @XmlAttribute(name = "Oznaka")
        protected String oznaka;
        @XmlAttribute(name = "Naziv")
        protected String naziv;

        /**
         * Gets the value of the sadrzaj property.
         * 
         * @return
         *     possible object is
         *     {@link TSadrzajOdeljka }
         *     
         */
        public TSadrzajOdeljka getSadrzaj() {
            return sadrzaj;
        }

        /**
         * Sets the value of the sadrzaj property.
         * 
         * @param value
         *     allowed object is
         *     {@link TSadrzajOdeljka }
         *     
         */
        public void setSadrzaj(TSadrzajOdeljka value) {
            this.sadrzaj = value;
        }

        /**
         * Gets the value of the oznaka property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOznaka() {
            return oznaka;
        }

        /**
         * Sets the value of the oznaka property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOznaka(String value) {
            this.oznaka = value;
        }

        /**
         * Gets the value of the naziv property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNaziv() {
            return naziv;
        }

        /**
         * Sets the value of the naziv property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNaziv(String value) {
            this.naziv = value;
        }

    }

}
