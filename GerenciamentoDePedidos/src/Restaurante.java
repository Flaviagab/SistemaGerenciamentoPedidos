import java.util.*;

public class Restaurante {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE PEDIDOS DO RESTAURANTE ===\n");

        Queue<String> filaDePedidos = registrarPedidos();

        Stack<String> pilhaDePratosProtos = prepararPratos(filaDePedidos);

        entregarPratos(pilhaDePratosProtos);

        System.out.println("\n=== TODOS OS PEDIDOS FORAM PROCESSADOS ===");
        scanner.close();
    }


    public static Queue<String> registrarPedidos() {
        Queue<String> novafila = new LinkedList<>();

        System.out.println("--- REGISTRO DE PEDIDOS ---");
        System.out.print("Quantos pedidos você deseja registrar? ");

        int quantidadePedidos = 0;
        try {
            quantidadePedidos = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Número inválido! Definindo 0 pedidos.");
            return novafila;
        }

        if (quantidadePedidos <= 0) {
            System.out.println("Nenhum pedido será registrado.\n");
            return novafila;
        }

        System.out.println();
        for (int i = 1; i <= quantidadePedidos; i++) {
            System.out.print("Digite a descrição do pedido " + i + ": ");
            String pedido = scanner.nextLine().trim();

            if (!pedido.isEmpty()) {
                novafila.offer(pedido);
                System.out.println("Pedido \"" + pedido + "\" adicionado à fila!");
            } else {
                System.out.println(" Pedido vazio não foi adicionado.");
                i--;
            }
        }

        System.out.println("\nTotal de pedidos na fila: " + novafila.size());
        System.out.println("Ordem dos pedidos: " + novafila);
        System.out.println();

        return novafila;
    }

    public static Stack<String> prepararPratos(Queue<String> filaPedidos) {
        Stack<String> novaPilha = new Stack<>();

        System.out.println("--- PREPARAÇÃO DOS PRATOS ---");

        if (filaPedidos.isEmpty()) {
            System.out.println("Nenhum pedido para preparar!\n");
            return novaPilha;
        }

        Queue<String> filaTemporaria = new LinkedList<>(filaPedidos);

        int contador = 1;
        while (!filaTemporaria.isEmpty()) {
            String pedido = filaTemporaria.poll();

            System.out.println(contador + ". Cozinha preparando: [" + pedido + "]...");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            novaPilha.push(pedido);
            System.out.println("Prato [" + pedido + "] está pronto e foi colocado na área de entrega!");

            contador++;
        }

        System.out.println("\nTodos os " + novaPilha.size() + " pratos foram preparados!");
        System.out.println("Pilha de pratos prontos (topo para base): " + novaPilha);
        System.out.println();

        return novaPilha;
    }

    public static void entregarPratos(Stack<String> pilhaPratos) {
        System.out.println("--- ENTREGA DOS PRATOS ---");

        if (pilhaPratos.isEmpty()) {
            System.out.println("Nenhum prato para entregar!\n");
            return;
        }

        Stack<String> pilhaTemporaria = new Stack<>();
        pilhaTemporaria.addAll(pilhaPratos);

        int contador = 1;
        while (!pilhaTemporaria.isEmpty()) {
            String prato = pilhaTemporaria.pop();

            System.out.println(contador + ". Entregando o prato: [" + prato + "]");

            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println(" Prato [" + prato + "] foi entregue ao cliente!");
            contador++;
        }

        System.out.println("\nTodos os pratos foram entregues com sucesso!");
    }
}