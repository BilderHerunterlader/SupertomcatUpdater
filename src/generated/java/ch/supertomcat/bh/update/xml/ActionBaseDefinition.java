//
// This file was generated by the Eclipse Implementation of JAXB, v2.3.8 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.03.08 at 09:45:38 AM CET 
//


package ch.supertomcat.bh.update.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ActionBaseDefinition complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ActionBaseDefinition"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActionBaseDefinition")
@XmlSeeAlso({
    CleanDirectoryActionDefinition.class,
    ExtractZipFileActionDefinition.class,
    CopyFileActionDefinition.class,
    DeleteFileActionDefinition.class
})
public abstract class ActionBaseDefinition {


}
