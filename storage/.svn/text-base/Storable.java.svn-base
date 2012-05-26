
package hawkge.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public abstract class Storable {

    private static byte[] ivBytes;
    static {
        ivBytes = new byte[8];
        byte[] iv = "dfj39fl4q4f".getBytes();
        for(int i = 0; i<ivBytes.length && i<iv.length; i++) ivBytes[i] = iv[i];
    }

    /**
     * Returns the root directory for storage.
     * @return The root directory for storage.
     */
    protected static File getStorageRoot() {
        String userHome = System.getProperty("user.home", ".");
        String osName = System.getProperty("os.name").toLowerCase();

        File location;
        if(osName.contains("windows")) {
            String applicationData = System.getenv("APPDATA");
            if(applicationData != null) {
                location = new File(applicationData, "HawkGE/");
            } else {
                location = new File(userHome, ".HawkGE/");
            }
        } else if(osName.contains("mac")) {
            location = new File(userHome, "Library/Application Support/HawkGE/");
        } else {
            location = new File(userHome, ".HawkGE/");
        }

        if(!location.exists() && !location.mkdirs()) {
            throw new RuntimeException(
                "Unable to create the directory for saving: " + location);
        }

        return location;
    }

    /**
     * Saves the file, encrypted with the given pass.
     * @return The file where the object was saved.
     * @throws StoreException If outputting the document failed.
     */
    public File save(String pass) throws StoreException {
        Document doc = new Document((Element) this.getXML().detach());
        XMLOutputter outputter = new XMLOutputter();
        File outputfile = getLocation();
        System.out.println("* Saving file: " + outputfile.getAbsolutePath());
        OutputStream ostream = null;
        byte[] bytekey = new byte[8];
        for(int i = 0; i < pass.getBytes().length && i < bytekey.length; i++)
            bytekey[i] = pass.getBytes()[i];
        try {
            SecretKeySpec key = new SecretKeySpec(bytekey, "DES");
            IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
            ostream = new CipherOutputStream(new FileOutputStream(outputfile), cipher);
            //ostream = new FileOutputStream(outputfile);
            outputter.output(doc, ostream);
        } catch(IOException e) {
            throw new StoreException("Unable to save to " + outputfile.getAbsolutePath()
                + ": " + e.getMessage());
        } catch(InvalidKeyException e) {
            throw new StoreException("Bad password says " + e.getMessage());
        } catch(GeneralSecurityException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                ostream.close();
            } catch (IOException e) {
                throw new StoreException("Failed while closing the file " +
                    outputfile.getAbsolutePath() + ".");
            }
        }
        System.out.println("* File saved: " + outputfile.getAbsolutePath());
        return outputfile;
    }

    /**
     * Loads the file data encrypted with pass into the memory.
     * @param data The encrypted XML file.
     * @param pass The password for the encryption.
     * @return The root element of the decrypted XML file. null if the file
     *      does not exist.
     * @throws StoreException If a problem occures while reading the file.
     */
    public static Element getElement(File data, String pass) throws StoreException {
        if(!data.exists()) return null;
        System.out.println("* Loading file: " + data.getAbsolutePath());
        SAXBuilder builder = new SAXBuilder();
        Document doc = null;
        InputStream istream = null;
        byte[] bytekey = new byte[8];
        for(int i = 0; i < pass.getBytes().length && i < bytekey.length; i++)
            bytekey[i] = pass.getBytes()[i];
        try {
            SecretKeySpec key = new SecretKeySpec(bytekey, "DES");
            IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
            istream = new CipherInputStream(new FileInputStream(data), cipher);
            //istream = new FileInputStream(data);
            doc = builder.build(istream);
        } catch(IOException e) {
            throw new StoreException("Unable to read file " + data.getAbsolutePath()
                + ": " + e.getMessage());
        } catch(JDOMException e) {
            throw new StoreException("Error while parsing file " + data.getAbsolutePath()
                + ": " + e.getMessage());
        } catch(InvalidKeyException e) {
            throw new StoreException("Bad password: " + e.getMessage());
        } catch(GeneralSecurityException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                istream.close();
            } catch (IOException ex) {
                throw new StoreException("Failed while closing the file " +
                    data.getAbsolutePath() + ".");
            }
        }
        System.out.println("* Loaded file: " + data.getAbsolutePath());
        return (Element) doc.getRootElement().detach();
    }

    /**
     * Creates an XML String in which the object is saved.
     * @return The XML String.
     */
    public abstract Element getXML();

    /*
     * Creates a new Storable object from the XML Element.
     * @return The Storable object.
     * @throws StoreException If the XML Element has an incorrect structure.
     */
    // If only java had abstract static methods. Dammit.
    //public static abstract Storable readXML(Element element, User user) throws StoreException;

    /**
     * Removes the file to which this object was saved.
     * @throws StoreException Unable to delete the file.
     */
    public void remove() throws StoreException {
        File file = getLocation();
        if(!file.exists()) {
            System.out.println("* Did not exist yet: " + file.getAbsolutePath());
            return;
        }
        if(!file.delete()) {
            throw new StoreException("Could not delete: " + getLocation());
        }
        System.out.println("* Deleted file: " + file.getAbsolutePath());
    }

    /**
     * Returns a String representing the relative path to the file this
     * object should be stored.
     * @return The file this object is stored.
     */
    public abstract File getLocation();

}
