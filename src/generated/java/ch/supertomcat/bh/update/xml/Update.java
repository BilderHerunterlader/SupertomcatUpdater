//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
//


package ch.supertomcat.bh.update.xml;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType>
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <choice maxOccurs="unbounded" minOccurs="0">
 *           <element name="clearDirectory" type="{}ClearDirectoryActionDefinition"/>
 *           <element name="extractZipFile" type="{}ExtractZipFileActionDefinition"/>
 *           <element name="copyFile" type="{}CopyFileActionDefinition"/>
 *           <element name="deleteFile" type="{}DeleteFileActionDefinition"/>
 *         </choice>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "actions"
})
@XmlRootElement(name = "update")
public class Update {

    @XmlElements({
        @XmlElement(name = "clearDirectory", type = ClearDirectoryActionDefinition.class),
        @XmlElement(name = "extractZipFile", type = ExtractZipFileActionDefinition.class),
        @XmlElement(name = "copyFile", type = CopyFileActionDefinition.class),
        @XmlElement(name = "deleteFile", type = DeleteFileActionDefinition.class)
    })
    protected List<ActionBaseDefinition> actions;

    /**
     * Gets the value of the actions property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the actions property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getActions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ClearDirectoryActionDefinition }
     * {@link CopyFileActionDefinition }
     * {@link DeleteFileActionDefinition }
     * {@link ExtractZipFileActionDefinition }
     * </p>
     * 
     * 
     * @return
     *     The value of the actions property.
     */
    public List<ActionBaseDefinition> getActions() {
        if (actions == null) {
            actions = new ArrayList<>();
        }
        return this.actions;
    }

}
