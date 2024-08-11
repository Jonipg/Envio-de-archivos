/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package servidorenviararchivos;

   import java.io.BufferedInputStream;
   import java.io.BufferedOutputStream;
   import java.io.DataOutputStream;
   import java.io.File;
   import java.io.FileInputStream;
   import java.net.InetAddress;
   import java.net.Socket;
   import javax.swing.JOptionPane;


/**
 *
 * @author By Shakur
 */
public class ServidorEnviarArchivos {
    //se declara  la variable
    private String nombreArchivo ="";
        
    public ServidorEnviarArchivos(String nombreArchivo)
    {
        this.nombreArchivo=nombreArchivo;
    }
        public void enviarArchivo(){
        
            try {
                //ip del servidor 
                InetAddress ip = InetAddress.getByName("192.168.72.61");
                //se crea el socket y puerto de comunicación
                Socket socket =new Socket(ip,5000);
                socket.setSoTimeout(2000);
                socket.setKeepAlive(true);
                //creamos el archivo a enviar
                File archivo =new File (nombreArchivo);
                //obteniendo el tamaño del archivo
                int tamañoArchivo=(int)archivo.length();
                
                //se crea la salida 
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                System.out.println("Preparando archivo para enviar"+archivo.getName());
                
                //se envia el nombre y Tamaño del archivo
                dos.writeUTF(archivo.getName());
                dos.writeInt(tamañoArchivo);
                //se leen los archivos en bits
                FileInputStream fis =new FileInputStream(nombreArchivo);
                BufferedInputStream bits =new BufferedInputStream(fis);
                
                //creamos el flujo de salida para enviar los datos del archivo en bits 
                BufferedOutputStream bos =new BufferedOutputStream(socket.getOutputStream());
                
                //creamos un array correspondiendo al tamaño del archivo 
                byte[] buffer =new byte[tamañoArchivo];
                
                //se incluye el archivo al arreglo
                bits.read(buffer);
                
                //realizamos el envio de los bytes que conforman el archivo
                for(int i =0; i<buffer.length; i++)
                {
                    bos.write(buffer[i]);
                    
                }
                JOptionPane.showMessageDialog(null,"Imagen enviada:"+archivo.getName());
                //se cierra los Sockets
                bits.close();
                bos.close();
                socket.close();

            }
            //capturamos si se encuentra un error durANTE SU EJECUCION
            catch (Exception e) 
            {
                System.err.println(e.toString());
                System.out.println("Se Ha enviado Correctamente");
            }
            
            
        } 
    
}
