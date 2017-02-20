package Negocio;

import Modelo.Cliente;
import Modelo.Lavadora;
import Modelo.Mayorista;
import Modelo.Mueble;
import Modelo.Particular;
import Modelo.Producto;
import Modelo.Televisor;
import Modelo.TipoMayorista;
import Modelo.dniErroneo;
import Modelo.fechaErronea;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MenuPrincipal {

    NegociosService servicio;

    MenuPrincipal() {
        servicio = new NegociosService();
    }

    public void inciarAplicacion() {
        try {
            // menu Principal
            int opcion = -1;
            do {
                System.out.println("1.Productos");
                System.out.println("2.Clientes");
                System.out.println("3.Ventas");
                System.out.println("0. Para Salir");
                System.out.println("Introduzca la opcion");
                Scanner sc = new Scanner(System.in);
                opcion = sc.nextInt();
                if (opcion == 1) {
                    menuProductos();
                }
                if (opcion == 2) {
                    menuClientes();
                }
                if (opcion == 3) {
                    menuVentas();
                }

            } while (opcion != 0);

            System.out.println("Gracias por usar nuestra aplicacion");
            System.out.println("Hasta la proxima");

        } catch (Exception e) {
            System.out.println("Opcion no valida");
            this.inciarAplicacion();
        }
    }

    private void menuProductos() {
        try {
            int opcionProductos = -1;
            do {
                System.out.println("1.Introducir Producto");
                System.out.println("2.Dar de baja un producto");
                System.out.println("3.Imprimir los datos de un producto");
                System.out.println("4.Imprimir todos los productos");
                System.out.println("0. Salir del menu");
                Scanner sc = new Scanner(System.in);
                opcionProductos = sc.nextInt();

                if (opcionProductos == 1) {
                    Producto p = datosProducto();
                    servicio.introducirProducto(p);
                }
                if (opcionProductos == 2) {
                    System.out.println("Introduzca el número de producto: ");
                    int num = sc.nextInt();
                    servicio.elimninarProducto(num);
                }
                if (opcionProductos == 3) {
                    System.out.println("Introduzca el número de producto: ");
                    int nprod = sc.nextInt();
                    System.out.println(servicio.buscarProducto(nprod));
                }
                if (opcionProductos == 4) {
                    System.out.println(servicio.imprimirTodosProductos());
                }

            } while (opcionProductos != 0);
        } catch (Exception e) {
            System.out.println("Opcion no valida" + e.getMessage());
            this.menuProductos();
        }
    }

    public Producto datosProducto() throws Exception {
        Scanner sc = new Scanner(System.in);
        Producto producto = null;
        String nombre;
        double precio;
        int opcion = -1;
        do {
            System.out.println("Introduzca el nombre: ");
            nombre = sc.nextLine();

            System.out.println("Introduzca precio base: ");
            precio = Double.parseDouble(sc.nextLine());

            System.out.println("Introduzca el tipo de producto: ");
            System.out.println("1. Mueble");
            System.out.println("2. Lavadora");
            System.out.println("3. Televisor");
            opcion = sc.nextInt();
            if (opcion == 1) {
                producto = pedirMueble();
            }
            if (opcion == 2) {
                producto = pedirLavadora();
            }
            if (opcion == 3) {
                producto = pedirTelevisor();
            }
            if (producto != null) {
                producto.setNombre(nombre);
                producto.setPrecio(precio);
            }

        } while (opcion != 1 && opcion != 2 && opcion != 3);

        return producto;
    }

    public Mueble pedirMueble() throws ParseException {
        Mueble m = new Mueble();
        Scanner sc = new Scanner(System.in);
        String fecha = null;
        m.setTipoMadera(pedirMadera());

        System.out.println("Introduzca el estilo:");
        m.setEstilo(sc.nextLine());
        
        System.out.println("Introduzca la fecha (dd-MMM-yyyy): ");
         try {
               fecha = sc.nextLine();
               m.setAnyoFab(this.validarFecha(fecha));
            } catch (fechaErronea f) {
                throw new fechaErronea(", la fecha "+fecha+" no es una fecha correcta");
            }
        return m;

    }

    public Lavadora pedirLavadora() {
        Scanner sc = new Scanner(System.in);
        Lavadora l = new Lavadora();

        System.out.println("Introduzca las revoluciones(rpm): ");
        int rev = Integer.parseInt(sc.nextLine());
        l.setRevoluciones(rev);

        System.out.println("Introduzca la capacidad (kg): ");
        int c = Integer.parseInt(sc.nextLine());
        l.setCarga(c);

        return l;
    }

    public Televisor pedirTelevisor() {
        Televisor tv = new Televisor();
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduzca las pulgadas: ");
        double pulgadas = Double.parseDouble(sc.nextLine());
        tv.setPulgadas(pulgadas);
        tv.setTipo(tipoTelevisor());
        return tv;
    }

    private Televisor.TipoTelevisor tipoTelevisor() {
        Televisor.TipoTelevisor t = null;
        String opcion;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Introduzca el tipo de Televisor");
            System.out.println("1.Plasma");
            System.out.println("2.LCD");
            System.out.println("3.LED");
            System.out.println("4.OLED");

            opcion = sc.nextLine();

        } while (!opcion.equals("1") && !opcion.equals("2") && !opcion.equals("3") && !opcion.equals("4"));

        if (opcion.equals("1")) {
            t = Televisor.TipoTelevisor.PLASMA;
        }
        if (opcion.equals("2")) {
            t = Televisor.TipoTelevisor.LCD;
        }
        if (opcion.equals("3")) {
            t = Televisor.TipoTelevisor.LED;
        }
        if (opcion.equals("4")) {
            t = Televisor.TipoTelevisor.OLED;
        }
        return t;
    }

    private Mueble.Madera pedirMadera() {
        Mueble.Madera m = null;
        String opcion;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Introduzca el tipo de Madera");
            System.out.println("1.Pino");
            System.out.println("2.Roble");
            System.out.println("3.Haya");

            opcion = sc.nextLine();

        } while (!opcion.equals("1") && !opcion.equals("2") && !opcion.equals("3"));

        if (opcion.equals("1")) {
            m = Mueble.Madera.PINO;
        }
        if (opcion.equals("2")) {
            m = Mueble.Madera.ROBLE;
        }
        if (opcion.equals("3")) {
            m = Mueble.Madera.HAYA;
        }
        return m;
    }

    private void menuClientes() {
        Scanner sc = new Scanner(System.in);
        int ncliente;

        try {
            int opcionClientes = -1;
            do {
                System.out.println("1.Introducir Cliente");
                System.out.println("2.Dar de baja un cliente");
                System.out.println("3.Imprimir los datos de un cliente");
                System.out.println("4.Imprimir todos los clientes");
                System.out.println("0. Salir del menu");

                opcionClientes = sc.nextInt();
                if (opcionClientes == 1) {
                    Cliente c = datosCliente();
                    if (servicio.introducirCliente(c) == true) {
                        System.out.println("Cliente añadido");
                    }
                }
                if (opcionClientes == 2) {
                    System.out.println("Introduzca el identificador del cliente: ");
                    int num = sc.nextInt();
                    if (servicio.eliminarCliente(num) == true) {
                        System.out.println("Cliente Eliminado");
                    }

                }
                if (opcionClientes == 3) {

                    System.out.println("Introduzca el identificador del cliente: ");
                    ncliente = sc.nextInt();
                    System.out.println(servicio.buscarCliente(ncliente));

                }
                if (opcionClientes == 4) {

                    System.out.println(servicio.imprimirTodosClientes());
                }

            } while (opcionClientes != 0);

        } catch (Exception e) {
            System.out.println("Opcion no valida");
            this.menuClientes();
        }
    }

    private Cliente datosCliente() throws Exception {
        Scanner sc = new Scanner(System.in);
        Cliente cliente = null;
        String nombre;
        String razonSocial;
        double precio;
        int opcion = -1;
        do {
            System.out.println("Introduzca el nombre: ");
            nombre = sc.nextLine();

            System.out.println("Introduzca su razón Social: ");
            razonSocial = sc.nextLine();

            System.out.println("Introduzca el tipo de cliente: ");
            System.out.println("1. Mayorista");
            System.out.println("2. Particular");
            opcion = sc.nextInt();
            if (opcion == 1) {
                cliente = nuevoMayorista();
            }
            if (opcion == 2) {
                cliente = nuevoParticular();
            }

            if (cliente != null) {
                cliente.setNombre(nombre);
                cliente.setRazonSocial(razonSocial);
            }

        } while (opcion != 1 && opcion != 2);

        return cliente;
    }

    private Mayorista nuevoMayorista() throws ParseException {
        Mayorista m = new Mayorista();
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduzca su CIF:");
        m.setCif(sc.nextLine());

        m.setTipoMayorista(tipoMayorista());

        return m;

    }

    private Particular nuevoParticular() throws ParseException {
        Particular p = new Particular();
        p.setDni(pedirDni());
        return p;

    }

    private String pedirDni() {
        Particular p = new Particular();
        Scanner sc = new Scanner(System.in);
        String respuesta;
        String letra;
        String numero;
        String dni = null;
        String dni1;
        boolean respuestad;
        System.out.println("Introduzca su DNI");
        respuesta = sc.nextLine();

        if (respuesta.length() == 9) {
            try {
                dni = tamaño9(respuesta);
            } catch (dniErroneo e) {
                System.out.println(e.getMessage());
                dni = e.getDniok();
            }
        } else if (respuesta.length() == 8) {
            dni = tamaño8(respuesta);
        } else {
            System.out.println("Tamaño de DNI incorrecto, vuelve a introducirlo");
            pedirDni();
        }
        return dni;
    }

    private String tamaño9(String respuesta) {
       
        String dni = "";
        String numero;
        String letra;

        numero = respuesta.substring(0, 8);
        letra = letra(numero);
        dni = numero + letra;
        if (respuesta.equals(dni)) {
            dni = respuesta;

        } else {
            throw new dniErroneo(dni,respuesta);
        }

        return dni;
    }
    
      public String letra(String dni){
        int miDni = Integer.parseInt(dni.substring(0, 8));
        int resto;
        String letra;
        String[] letras = {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E"};
        resto=miDni%23;
        letra=letras[resto];
        return letra;
    }

    private String tamaño8(String respuesta) {
        String letra;
        String dni;
        letra = letra(respuesta);
        dni = respuesta + letra;
        return dni;
    }

    private TipoMayorista tipoMayorista() {
        TipoMayorista t = null;
        String opcion;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Introduzca el tipo de Mayorista");
            System.out.println("1.Gran superficie");
            System.out.println("2.Tienda");

            opcion = sc.nextLine();

        } while (!opcion.equals("1") && !opcion.equals("2"));

        if (opcion.equals("1")) {
            t = TipoMayorista.GRAN_SUPERFICIE;
        }
        if (opcion.equals("2")) {
            t = TipoMayorista.TIENDA;
        }
        return t;
    }

    private void menuVentas() {
        Scanner sc = new Scanner(System.in);

        try {
            String opcionVentas = "-1";
            do {
                System.out.println("1.Introducir Venta");
                System.out.println("2.Dar de baja una venta");
                System.out.println("3.Imprimir los datos de una venta");
                System.out.println("4.Imprimir todas las ventas");
                System.out.println("0. Salir del menu");
                opcionVentas = sc.nextLine();

                if (opcionVentas.equals("1")) {
                    System.out.println("Introduce el número de cliente.");
                    int nv = Integer.parseInt(sc.nextLine());
                    System.out.println("Introduce el número de producto.");
                    int np = Integer.parseInt(sc.nextLine());
                    System.out.println("Introduce el nombre del vendedor: ");
                    String v = sc.nextLine();
                    servicio.introducirVenta(nv, np, v);
                }
                if (opcionVentas.equals("2")) {
                    System.out.println("Introduzca número de venta: ");
                    int nv = Integer.parseInt(sc.nextLine());
                    servicio.eliminarVenta(nv);
                }
                if (opcionVentas.equals("3")) {
                    System.out.println("Introduzca número de venta: ");
                    int nv = Integer.parseInt(sc.nextLine());
                    servicio.buscarVenta(nv);
                }
                if (opcionVentas.equals("4")) {
                    System.out.println(servicio.imprimirtodasVentas());
                }

            } while (!opcionVentas.equals("0"));

        } catch (Exception e) {
            System.out.println("Opcion no valida");
            this.menuVentas();
        }

    }

    private LocalDate validarFecha(String fecha) throws ParseException {
       DateTimeFormatter sfd = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");
       LocalDate fec= null;
        
        try {
              fec= LocalDate.parse (fecha , sfd );
            } catch (DateTimeParseException f) {
                throw new fechaErronea(" La fecha "+fecha+" no es una fecha correcta");
            }
       return fec;
    }

}
