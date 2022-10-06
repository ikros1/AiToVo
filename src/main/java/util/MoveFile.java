package util;

import java.io.*;

public class MoveFile {
    public static void cpfile(String file,String to){
        byte[] data =new byte[1024*10];

        FileInputStream fi = null;
        try {
            fi = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        FileOutputStream fo = null;
        try {
            fo = new FileOutputStream(to);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        long start =System.currentTimeMillis();
        int len =0;
        BufferedInputStream bfi =new BufferedInputStream(fi);
        BufferedOutputStream bfo =new BufferedOutputStream(fo);
        while (true) {
            try {
                if ((len=bfi.read(data))==-1) break;
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                bfo.write(data,0,len);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bfi.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bfo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end =System.currentTimeMillis();
        long score =end-start;
        System.out.println(score+"ms");
    }
}
