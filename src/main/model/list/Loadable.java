package model.list;

import java.io.IOException;

public interface Loadable {
    void load(String s) throws IOException, ClassNotFoundException;
}
