package hawkge.main;

import hawkge.ModelInterface;

/**
 * Interface to set a name variable
 * @create on May 2, 2012
 * @author jorisvi
 */
public interface NameModelInterface extends ModelInterface {
    
    /**
     * Get the name.
     * @return a String of the name
     */
    public String getName();
    
    /**
     * Set the name.
     * @param name a String object
     */
    public void setName(String name);
}
