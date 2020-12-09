import java.io.*;
import java.security.*;
import java.security.spec.*;

class VerificaFirmaINICIALES
{
    public static void main (String [] args){
        /*Verificando el numero de argumentos de entrada*/
        if (args.length!=3){
            System.out.println("Sintaxis del programa es: java VerificaFirmaINICIALES archivoaFirmarINICIALES.txt clavepublicaINICIALES.txt firmaArchivo");
        }else try{
            /*En este bloque se debe colocar el código faltante*/
            FileInputStream clavepublica=new FileInputStream (args[0]);
            byte[] clave=new byte[clavepublica.available()];
            clavepublica.read(clave);
            clavepublica.close();
            X509EncodedKeySpec pubKeySpec=new X509EncodedKeySpec(clave);
            KeyFactory keyFactory = KeyFactory. getInstance("RSA");
            PublicKey pubKey=keyFactory.generatePublic(pubKeySpec);
            FileInputStream archivoFirmado=new FileInputStream(args[1]);
            byte[] firmaVerificada=new byte[archivoFirmado.available()];
            archivoFirmado.read(firmaVerificada);
            archivoFirmado.close();
            Signature firma=Signature.getInstance ("MD5withRSA");
            firma.initVerify(pubKey);
            FileInputStream datos=new FileInputStream(args[2]);
            BufferedInputStream
            buferEntrada=new BufferedInputStream(datos);
            byte[] buffer=new byte[1024];
            int longitud;
            while (buferEntrada.available() !=0){
                longitud= buferEntrada.read(buffer);
                firma.update(buffer,0,longitud);
            }
            buferEntrada.close();
            boolean verifica=firma.verify(firmaVerificada);
            System.out.println ("Verificación de la firma " +verifica);
        }catch (Exception e){
            System.err.println ("El error es de "+ e.toString() );
        }
    }
}