package Actions;

public class AppendExtension {

    public AppendExtension() {
    }

    public String appendExtension(String theGameFileName) {
        StringBuilder sb = new StringBuilder();
        sb.append(theGameFileName);
        sb.append(".ser");
        return String.valueOf(sb);
    }
}
