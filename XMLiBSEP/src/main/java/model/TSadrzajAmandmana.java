//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.15 at 08:27:11 PM CEST 
//


package model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TSadrzajAmandmana complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TSadrzajAmandmana">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GlavaAmandman" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice>
 *                   &lt;element name="OdeljakAmandman" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="ClanAmandman" type="{http://www.gradskaskupstina.rs/}TClanAmandnam" maxOccurs="unbounded"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="OznakaOdeljka" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="ClanAmandman" type="{http://www.gradskaskupstina.rs/}TClanAmandnam" maxOccurs="unbounded"/>
 *                 &lt;/choice>
 *                 &lt;attribute name="OznakaGlave" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="PredmetIzmene" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CiljIzmene" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="NazivAkta" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TSadrzajAmandmana", namespace = "http://www.gradskaskupstina.rs/", propOrder = {
    "glavaAmandman",
    "predmetIzmene",
    "ciljIzmene"
})
public class TSadrzajAmandmana {

    @XmlElement(name = "GlavaAmandman", required = true)
    protected List<TSadrzajAmandmana.GlavaAmandman> glavaAmandman;
    @XmlElement(name = "PredmetIzmene", required = true)
    protected String predmetIzmene;
    @XmlElement(name = "CiljIzmene", required = true)
    protected String ciljIzmene;
    @XmlAttribute(name = "NazivAkta")
    protected String nazivAkta;

    /**
     * Gets the value of the glavaAmandman property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the glavaAmandman property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGlavaAmandman().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TSadrzajAmandmana.GlavaAmandman }
     * 
     * 
     */
    public List<TSadrzajAmandmana.GlavaAmandman> getGlavaAmandman() {
        if (glavaAmandman == null) {
            glavaAmandman = new ArrayList<TSadrzajAmandmana.GlavaAmandman>();
        }
        return this.glavaAmandman;
    }

    /**
     * Gets the value of the predmetIzmene property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPredmetIzmene() {
        return predmetIzmene;
    }

    /**
     * Sets the value of the predmetIzmene property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPredmetIzmene(String value) {
        this.predmetIzmene = value;
    }

    /**
     * Gets the value of the ciljIzmene property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCiljIzmene() {
        return ciljIzmene;
    }

    /**
     * Sets the value of the ciljIzmene property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCiljIzmene(String value) {
        this.ciljIzmene = value;
    }

    /**
     * Gets the value of the nazivAkta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNazivAkta() {
        return nazivAkta;
    }

    /**
     * Sets the value of the nazivAkta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNazivAkta(String value) {
        this.nazivAkta = value;
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
     *       &lt;choice>
     *         &lt;element name="OdeljakAmandman" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="ClanAmandman" type="{http://www.gradskaskupstina.rs/}TClanAmandnam" maxOccurs="unbounded"/>
     *                 &lt;/sequence>
     *                 &lt;attribute name="OznakaOdeljka" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="ClanAmandman" type="{http://www.gradskaskupstina.rs/}TClanAmandnam" maxOccurs="unbounded"/>
     *       &lt;/choice>
     *       &lt;attribute name="OznakaGlave" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "odeljakAmandman",
        "clanAmandman"
    })
    public static class GlavaAmandman {

        @XmlElement(name = "OdeljakAmandman", namespace = "http://www.gradskaskupstina.rs/")
        protected List<TSadrzajAmandmana.GlavaAmandman.OdeljakAmandman> odeljakAmandman;
        @XmlElement(name = "ClanAmandman", namespace = "http://www.gradskaskupstina.rs/")
        protected List<TClanAmandnam> clanAmandman;
        @XmlAttribute(name = "OznakaGlave")
        protected String oznakaGlave;

        /**
         * Gets the value of the odeljakAmandman property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the odeljakAmandman property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOdeljakAmandman().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TSadrzajAmandmana.GlavaAmandman.OdeljakAmandman }
         * 
         * 
         */
        public List<TSadrzajAmandmana.GlavaAmandman.OdeljakAmandman> getOdeljakAmandman() {
            if (odeljakAmandman == null) {
                odeljakAmandman = new ArrayList<TSadrzajAmandmana.GlavaAmandman.OdeljakAmandman>();
            }
            return this.odeljakAmandman;
        }

        /**
         * Gets the value of the clanAmandman property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the clanAmandman property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getClanAmandman().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TClanAmandnam }
         * 
         * 
         */
        public List<TClanAmandnam> getClanAmandman() {
            if (clanAmandman == null) {
                clanAmandman = new ArrayList<TClanAmandnam>();
            }
            return this.clanAmandman;
        }

        /**
         * Gets the value of the oznakaGlave property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOznakaGlave() {
            return oznakaGlave;
        }

        /**
         * Sets the value of the oznakaGlave property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOznakaGlave(String value) {
            this.oznakaGlave = value;
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
         *         &lt;element name="ClanAmandman" type="{http://www.gradskaskupstina.rs/}TClanAmandnam" maxOccurs="unbounded"/>
         *       &lt;/sequence>
         *       &lt;attribute name="OznakaOdeljka" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "clanAmandman"
        })
        public static class OdeljakAmandman {

            @XmlElement(name = "ClanAmandman", namespace = "http://www.gradskaskupstina.rs/", required = true)
            protected List<TClanAmandnam> clanAmandman;
            @XmlAttribute(name = "OznakaOdeljka")
            @XmlSchemaType(name = "anySimpleType")
            protected String oznakaOdeljka;

            /**
             * Gets the value of the clanAmandman property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the clanAmandman property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getClanAmandman().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link TClanAmandnam }
             * 
             * 
             */
            public List<TClanAmandnam> getClanAmandman() {
                if (clanAmandman == null) {
                    clanAmandman = new ArrayList<TClanAmandnam>();
                }
                return this.clanAmandman;
            }

            /**
             * Gets the value of the oznakaOdeljka property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOznakaOdeljka() {
                return oznakaOdeljka;
            }

            /**
             * Sets the value of the oznakaOdeljka property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOznakaOdeljka(String value) {
                this.oznakaOdeljka = value;
            }

        }

    }

}
