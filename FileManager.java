import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileManager {
    public static void main(String[] args) {
        crearCuentasClientes();
    }

    public static void crearCuentasClientes() {

        try {
            FileReader clientesDoc = new FileReader("CLIENTES.txt");

            BufferedReader clientesBuffer = new BufferedReader(clientesDoc);

            String strCli = "";
            String strCuenta = "";
            String clientes = "";
            String BALANCETXT = "NUMERO DE CUENTA | NOMBRE | APELLIDO | FECHA DE NACIMIENTO | BALANCE" + "\n";

            clientesBuffer.readLine();
            while ((strCli = clientesBuffer.readLine()) != null) {
                String[] datos = strCli.split(" | ");

                String cedulaCliente = datos[0];
                String nombreCliente = datos[2];
                String apellidoCliente = datos[3];
                String fechaNacimiento = datos[7];

                FileReader cuentasDoc = new FileReader("CUENTAS.txt");
                BufferedReader cuentasBuffer = new BufferedReader(cuentasDoc);
                cuentasBuffer.readLine();
                while ((strCuenta = cuentasBuffer.readLine()) != null) {
                    String[] cuentaDatos = strCuenta.split(" | ");

                    String cedulaCuenta = cuentaDatos[0];
                    String numeroCuenta = cuentaDatos[2];
                    Double balanceCuenta = Double.parseDouble(cuentaDatos[6]);

                    if (cedulaCuenta.trim().equals(cedulaCliente.trim()) && balanceCuenta > 3000) {
                        BALANCETXT += numeroCuenta + "," + nombreCliente + "," + apellidoCliente + ","
                                + fechaNacimiento + "," + balanceCuenta + "\n";
                    }

                }

                System.out.println("\n" + "\n");
                cuentasBuffer.close();

            }

            clientesDoc.close();
            escribirBalances(BALANCETXT);

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Hubo un error, revisar logs");
            System.out.println(e);
        }
    }

    public static void escribirBalances(String textBalance) {
        try {
            FileWriter fw = new FileWriter("BALANCES.TXT");
            fw.write(textBalance);
            fw.close();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}