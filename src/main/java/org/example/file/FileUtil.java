package org.example.file;

import org.example.enums.FileAddress;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class FileUtil {
    public static void writeFileObject(FileAddress fileName,Object o) throws Exception {
        try (FileOutputStream fos = new FileOutputStream(fileName.getAddress());
             ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(o);
        }
    }

    public static void writeFileObject(FileAddress fileName,Object o,int number) throws Exception{
        try (FileOutputStream fos = new FileOutputStream(fileName.getAddress());
             ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(o);
            oos.writeInt(number);
        }
    }
    public static Object[] readFileObject(FileAddress fileName) {
        Object[] result = new Object[2];
        try (FileInputStream fis = new FileInputStream(fileName.getAddress());
             ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            result[0] = ois.readObject();
            result[1] = ois.readInt();
        }
        catch (Exception e){
            return null;
        }
        return result;
    }
    public static Object readFileObject2(FileAddress fileName)  {
        try (FileInputStream fis = new FileInputStream(fileName.getAddress());
             ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            return ois.readObject();
        }
        catch (Exception e){
            return  null;
        }
    }


}
