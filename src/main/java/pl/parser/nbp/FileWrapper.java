package pl.parser.nbp;

import java.io.File;

public class FileWrapper {
    private File file;
    private int year;

    public File getFile() {
        return file;
    }

    public int getYear() {
        return year;
    }

    public FileWrapper(File file, int year) {
        this.file = file;
        this.year = year;
    }
}
