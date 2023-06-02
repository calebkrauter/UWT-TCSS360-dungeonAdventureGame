package Actions;

import java.io.IOException;

public class LoadAction {
    public LoadAction() {
        try {
            this.loadAction();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAction() throws IOException {


    }
}
