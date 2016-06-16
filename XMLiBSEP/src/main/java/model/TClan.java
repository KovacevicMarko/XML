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
 * <p>Java class for TClan complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TClan">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Stav" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Sadrzaj" type="{http://www.gradskaskupstina.rs/}TSadrzajStava"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="OznakaStav" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="OznakaClan" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TClan", namespace = "http://www.gradskaskupstina.rs/", propOrder = {
    "stav"
})
public class TClan {

    @XmlElement(name = "Stav", required = true)
    protected List<TClan.Stav> stav;
    @XmlAttribute(name = "OznakaClan")
    protected String oznakaClan;

    /**
     * Gets the value of the stav property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stav property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStav().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TClan.Stav }
     * 
     * 
     */
    public List<TClan.Stav> getStav() {
        if (stav == null) {
            stav = new ArrayList<TClan.Stav>();
        }
        return this.stav;
    }

    /**
     * Gets the value of the oznakaClan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOznakaClan() {
        return oznakaClan;
    }

    /**
     * Sets the value of the oznakaClan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOznakaClan(String value) {
        this.oznakaClan = value;
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
     *         &lt;element name="Sadrzaj" type="{http://www.gradskaskupstina.rs/}TSadrzajStava"/>
     *       &lt;/sequence>
     *       &lt;attribute name="OznakaStav" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    public static class Stav {

        @XmlElement(name = "Sadrzaj", namespace = "http://www.gradskaskupstina.rs/", required = true)
        protected TSadrzajStava sadrzaj;
        @XmlAttribute(name = "OznakaStav")
        protected String oznakaStav;

        /**
         * Gets the value of the sadrzaj property.
         * 
         * @return
         *     possible object is
         *     {@link TSadrzajStava }
         *     
         */
        public TSadrzajStava getSadrzaj() {
            return sadrzaj;
        }

        /**
         * Sets the value of the sadrzaj property.
         * 
         * @param value
         *     allowed object is
         *     {@link TSadrzajStava }
         *     
         */
        public void setSadrzaj(TSadrzajStava value) {
            this.sadrzaj = value;
        }

        /**
         * Gets the value of the oznakaStav property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOznakaStav() {
            return oznakaStav;
        }

        /**
         * Sets the value of the oznakaStav property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOznakaStav(String value) {
            this.oznakaStav = value;
        }

    }

}