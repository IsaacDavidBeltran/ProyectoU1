package Utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Archivo {
    private ObjectOutputStream archivo;
    private final String rutaArchivo = "src\\Utility\\archivos";

    public <T> void guardarArchivo(ArrayList<T> array) {
        try {
            archivo = new ObjectOutputStream(new FileOutputStream(rutaArchivo));
            archivo.writeObject(array);
        } catch (IOException e) {
            System.err.println("Error al Guardar Archivo" + e);
        } finally {
            if (archivo != null) {
                try {
                    archivo.close();
                } catch (IOException e) {
                    System.err.println("Error al Cerrar Archivo: " + e);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public <T> ArrayList<T> leerArchivo() {
        ArrayList<T> array = new ArrayList<>();
        ObjectInput archivo = null;

        try {
            archivo = new ObjectInputStream(new FileInputStream(rutaArchivo));
            array = (ArrayList<T>) archivo.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al Leer Archivo: " + e);
        } finally {
            if (archivo != null) {
                try {
                    archivo.close();
                } catch (IOException e) {
                    System.err.println("Error al Cerrar Archivo: " + e);
                }
            }
        }
        return array;
    }
}
