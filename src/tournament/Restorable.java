package tournament;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public interface Restorable
{
    public void saveFile(FileWriter wr) throws IOException;
}
