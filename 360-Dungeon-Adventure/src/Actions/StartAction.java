package Actions;

import java.io.IOException;

public class StartAction {
    public StartAction() {
        try {
            this.startAction();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void startAction() throws IOException {

    }
}
