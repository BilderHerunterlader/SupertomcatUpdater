//
// This file was generated by the Eclipse Implementation of JAXB, v2.3.8 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.03.08 at 09:45:38 AM CET 
//


package ch.supertomcat.bh.update.xml;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ch.supertomcat.bh.update.xml package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ch.supertomcat.bh.update.xml
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Update }
     * 
     */
    public Update createUpdate() {
        return new Update();
    }

    /**
     * Create an instance of {@link CleanDirectoryActionDefinition }
     * 
     */
    public CleanDirectoryActionDefinition createCleanDirectoryActionDefinition() {
        return new CleanDirectoryActionDefinition();
    }

    /**
     * Create an instance of {@link ExtractZipFileActionDefinition }
     * 
     */
    public ExtractZipFileActionDefinition createExtractZipFileActionDefinition() {
        return new ExtractZipFileActionDefinition();
    }

    /**
     * Create an instance of {@link CopyFileActionDefinition }
     * 
     */
    public CopyFileActionDefinition createCopyFileActionDefinition() {
        return new CopyFileActionDefinition();
    }

    /**
     * Create an instance of {@link DeleteFileActionDefinition }
     * 
     */
    public DeleteFileActionDefinition createDeleteFileActionDefinition() {
        return new DeleteFileActionDefinition();
    }

}
