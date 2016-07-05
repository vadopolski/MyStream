package mystream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyStream {
    public static void main(String[] args) {
        double copy[]=null;
        double data[]={3.5, 7, 9.01};
        write(data, "data.txt");
        copy=read("data.txt"); // читает файлы
//        for(int i=0; i<copy.length; ++i)
//            System.out.println(copy[i]);
    }
    
    static void write(double[] d, String name) {
        try(DataOutputStream dos = 
            new DataOutputStream( // работает с простыми типами данными
                new BufferedOutputStream(
                    new FileOutputStream(name) )) ) {// создание объекта который будет закрываться с внешнего класса
            dos.writeInt(d.length ); //             
            for (int i=0; i<d.length;++i) // Блок try - проходим по всем объектам и сохраняем в файл
                dos.writeDouble(d[i]);
            }          
        catch(IOException ex) {ex.printStackTrace();}
    }
    
    static double[] read(String name){
        double data[] = null;// объявим массив
        try (DataInputStream dis=
            new DataInputStream(
                new BufferedInputStream(
                    new FileInputStream(name)))) {
//                    double d=0;
                    int size = dis.readInt(); // спросим перед чтением
                    data = new double[size]; 
                    for (int i=0; i<size; ++i) {
                        data[i] = dis.readDouble();
//                        d = dis.readDouble();
//                        System.out.println(d);  
                    }                         
            } 
            catch (EOFException ex) {System.out.println("end of file...");}
            catch (IOException ex) {ex.printStackTrace();}
            return data;
    }    
}
