package Actions;

import Model.MapGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class LoadAction {
    public LoadAction() {
        try {
            this.loadAction();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAction() throws IOException {
        MapGenerator user = null;
        user = new MapGenerator();




        FileOutputStream fileOutputStream = new FileOutputStream("USERINFO.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);


        objectOutputStream.writeObject(user);
        objectOutputStream.close();
        fileOutputStream.close();

    }
}
