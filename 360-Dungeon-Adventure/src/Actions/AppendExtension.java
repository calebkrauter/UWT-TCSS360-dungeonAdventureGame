package Actions;

/**
 * Append extension.
 */
public class AppendExtension {

    /**
     * Constructor.
     */
    public AppendExtension() {
    }

    /**
     * Appends extension.
     * @param theGameFileName
     * @return new file string
     */
    public String appendExtension(String theGameFileName) {
        StringBuilder sb = new StringBuilder();
        sb.append(theGameFileName);
        sb.append(".ser");
        return String.valueOf(sb);
    }
}
