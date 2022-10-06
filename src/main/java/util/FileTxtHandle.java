package util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class FileTxtHandle {
    private String src;

    public FileTxtHandle(String src) {
        this.src = src;
    }

    public void txt_add(String addtxt){
        FileOutputStream fo =null;
        PrintWriter pw= null;
        try {
            fo = new FileOutputStream(src,true);
            pw=new PrintWriter(fo);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        pw.println(addtxt);
        pw.close();
    }
}
