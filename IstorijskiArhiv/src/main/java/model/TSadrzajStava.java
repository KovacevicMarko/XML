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
 * <p>Java class for TSadrzajStava complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TSadrzajStava">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TekstStav" type="{http://www.gradskaskupstina.rs/}TTekst"/>
 *         &lt;element name="Tacka" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Sadrzaj" type="{http://www.gradskaskupstina.rs/}TSadrzajTacke"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="OznakaTacka" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TSadrzajStava", namespace = "http://www.gradskaskupstina.rs/", propOrder = {
    "tekstStav",
    "tacka"
})
public class TSadrzajStava {

    @XmlElement(name = "TekstStav", required = true)
    protected TTekst tekstStav;
    @XmlElement(name = "Tacka")
    protected List<TSadrzajStava.Tacka> tacka;

    /**
     * Gets the value of the tekstStav property.
     * 
     * @return
     *     possible object is
     *     {@link TTekst }
     *     
     */
    public TTekst getTekstStav() {
        return tekstStav;
    }

    /**
     * Sets the value of the tekstStav property.
     * 
     * @param value
     *     allowed object is
     *     {@link TTekst }
     *     
     */
    public void setTekstStav(TTekst value) {
        this.tekstStav = value;
    }

    /**
     * Gets the value of the tacka property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tacka property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTacka().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TSadrzajStava.Tacka }
     * 
     * 
     */
    public List<TSadrzajStava.Tacka> getTacka() {
        if (tacka == null) {
            tacka = new ArrayList<TSadrzajStava.Tacka>();
        }
        return this.tacka;
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
     *         &lt;element name="Sadrzaj" type="{http://www.gradskaskupstina.rs/}TSadrzajTacke"/>
     *       &lt;/sequence>
     *       &lt;attribute name="OznakaTacka" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    public static class Tacka {

        @XmlElement(name = "Sadrzaj", namespace = "http://www.gradskaskupstina.rs/", required = true)
        protected TSadrzajTacke sadrzaj;
        @XmlAttribute(name = "OznakaTacka")
        protected String oznakaTacka;

        /**
         * Gets the value of the sadrzaj property.
         * 
         * @return
         *     possible object is
         *     {@link TSadrzajTacke }
         *     
         */
        public TSadrzajTacke getSadrzaj() {
            return sadrzaj;
        }

        /**
         * Sets the value of the sadrzaj property.
         * 
         * @param value
         *     allowed object is
         *     {@link TSadrzajTacke }
         *     
         */
        public void setSadrzaj(TSadrzajTacke value) {
            this.sadrzaj = value;
        }

        /**
         * Gets the value of the oznakaTacka property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOznakaTacka() {
            return oznakaTacka;
        }

        /**
         * Sets the value of the oznakaTacka property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOznakaTacka(String value) {
            this.oznakaTacka = value;
        }

    }

}