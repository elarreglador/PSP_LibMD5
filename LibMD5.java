import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;

public class LibMD5 {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        try (Scanner lee = new Scanner(System.in)) {
            System.out.println("Introduce el texto para obtener hash MD5:");
            String texto = lee.nextLine();
            LibMD5 md5Primero = new LibMD5(texto);

            // SALIDA POR TERMINAL
            System.out.println("Texto origen del hash MD5: " + texto);
            System.out.println("Numero de bytes: " + md5Primero.getByteHash().length);
            System.out.println("Hash en hexadecimal : " + md5Primero.getStringHash());
            System.out.println("Hash en bytes: " + Arrays.toString(md5Primero.getByteHash()));
            System.out.println();

            String textoSegundo = "hola mundo";
            LibMD5 md5Segundo = new LibMD5(textoSegundo);

            // SALIDA POR TERMINAL
            System.out.println("Texto origen del hash MD5: " + textoSegundo);
            System.out.println("Numero de bytes: " + md5Segundo.getByteHash().length);
            System.out.println("Hash en hexadecimal : " + md5Segundo.getStringHash());
            System.out.println("Hash en bytes: " + Arrays.toString(md5Segundo.getByteHash()));
            System.out.println();

            System.out.println("Iguales = "+md5Primero.comparaByteHash(md5Segundo.getByteHash()));


        }
    }

    private byte byteHash[];
    private StringBuffer hexHash;

    public LibMD5(String texto) throws NoSuchAlgorithmException {

        // CREA OBJETO MessageDigest
        MessageDigest md = MessageDigest.getInstance("MD5");
        // INTRODUCE EL TEXTO EN MESSAGEDIGEST (en array de bytes)
        md.update(texto.getBytes());
        // ES CALCULA EL HASH
        byteHash = md.digest();

        // ARRAY DE BYTES A HEXADECIMAL PARA LECTURA HUMANA
        hexHash = new StringBuffer();
        for (int i = 0; i < byteHash.length; i++) {
            String hex = Integer.toHexString(0xff & byteHash[i]);
            if (hex.length() == 1)
                hexHash.append('0');
            hexHash.append(hex);
        }
    }

    public String getStringHash() {
        return hexHash.toString();
    }

    public byte[] getByteHash() {
        return byteHash;
    }

    public boolean comparaByteHash(byte[] byteHash2){
        if (MessageDigest.isEqual(this.getByteHash(), byteHash2)) {
            return true;
        }else{
            return false;
        }
    }

}
