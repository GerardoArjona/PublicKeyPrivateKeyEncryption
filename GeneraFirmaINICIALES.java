import java.io.*;
import java.security.*;
class GeneraFirmaINICALES
{
    public static void main (String [] args)
    {
        /*Verificando el numero de argumentos de entrada*/
        if (args.length!=1){
            System.out.println("Sintaxis del programa es: java GeneraFirmaINICIALES archivoaFirmar");
        }else try{
            /*En este bloque se debe colocar el código faltante*/
            KeyPairGenerator genClave=KeyPairGenerator.getInstance("RSA");
            SecureRandom aleatorio=SecureRandom.getInstance("SHA1PRNG","SUN");
            genClave.initialize(1024, aleatorio);
            KeyPair pardeClaves= genClave.generateKeyPair();
            PrivateKey privada=pardeClaves.getPrivate();
            PublicKey publica=pardeClaves.getPublic();
            Signature firma=Signature.getInstance("MD5withRSA");
            firma.initSign(privada);

            FileInputStream archivo=new FileInputStream(args[0]);
            BufferedInputStream buferEntrada=new BufferedInputStream(archivo);
            byte[] buffer=new byte[1024];
            int longitud;
            while (buferEntrada.available() !=0){
                longitud =buferEntrada.read(buffer);
                firma.update(buffer, 0, longitud);
            }
            buferEntrada.close();
            byte[] firmaReal=firma.sign();

            /*Guardando los datos firmados en un archivo*/
            FileOutputStream archivoFirma=new FileOutputStream("firmaINICIALES.txt");
            archivoFirma.write(firmaReal);
            archivoFirma.close();

            /*Guardando la clave pública en un archivo*/
            byte[] clave=publica.getEncoded();
            FileOutputStream clavePublica=new FileOutputStream("clavePublicaINICIALES.txt");
            clavePublica.write(clave);
            clavePublica.close();
        }
        catch (Exception e){
            System.out.println("El error es " + e) ;
        }
    }
}
