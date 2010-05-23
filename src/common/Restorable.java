package common;


import java.io.FileWriter;
import java.io.IOException;

public interface Restorable
{
    public void saveFile(FileWriter wr) throws IOException;
}
