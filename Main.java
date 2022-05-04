import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    //Metodo para teste rápido
    /*
    public static void main(String[] args) {
        Supermercado sm = new Supermercado("Supermercado");
        Produto produto = new Produto("prod1",50,100);
        sm.addProduto(produto);
        Cliente cliente = new Cliente("client1");
        sm.addCliente(cliente);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.addProduto(produto,12);
        pedido.setPagamento(Pedido.TipoPagamento.DINHEIRO);
        sm.concluirPedido(pedido);

        System.out.println(sm.getProdutos());
        System.out.println(sm.getClientes());
        System.out.println(sm.getPedidos());

    }

     */

    public static void main(String[] args) {
        Supermercado sm = new Supermercado("Supermercado");
        //Inicializando com alguns dados
        sm.addProduto(new Produto("prod1",10,100));
        sm.addCliente(new Cliente("cli"));
        try(Scanner scan = new Scanner(System.in)){
            while (true){

                String menu = "Escolha uma opção: \n"+
                        "1- Cadastrar Cliente\n"+
                        "2- Cadastrar Produto\n"+
                        "3- Realizar Venda\n"+
                        "4- Simular Desconto de Venda";
                System.out.println(menu);
                    int opt = scan.nextInt();
                    scan.nextLine();
                    switch (opt){
                        case 1: cadastrarCliente(scan,sm);
                        break;
                        case 2: cadastrarProduto(scan,sm);
                        break;
                        case 3: realizarVenda(scan, sm);
                        break;
                        case 4: simularDesconto(scan, sm);
                        break;
                    }
                }
        }catch (InputMismatchException e){
            System.out.println("O Valor informado é inválido");
        }
    }

    public static void cadastrarCliente(Scanner scan,Supermercado sm){
        System.out.println("Digite o nome do cliente: ");
        String nome = scan.nextLine();
        Cliente cliente = new Cliente(nome);
        sm.addCliente(cliente);
        System.out.println("Cliente "+cliente.getNome()+" cadastrado com Sucesso");
    }

    public static void cadastrarProduto(Scanner scan, Supermercado sm){
        System.out.println("Digite o nome do Produto: ");
        String nome = scan.nextLine();
        System.out.println("Digite o valor do produto");
        double valor = scan.nextDouble();
        System.out.println("Digite a quantidade em estoque: ");
        int quant = scan.nextInt();
        Produto produto = new Produto(nome,valor,quant);
        sm.addProduto(produto);
        System.out.println("Produto "+produto+" cadastrado com sucesso");
    }

    public static void realizarVenda(Scanner scan, Supermercado sm){
        Pedido pedido = new Pedido();
        while (true) {
            String menu = "Escolha uma opção: \n" +
                    "1-Adicionar produto \n" +
                    "2-Finalizar";
            System.out.println(menu);
            int opt = scan.nextInt();
            switch (opt) {
                case 1:
                    addProduto(scan, pedido, sm);
                    break;
                case 2:
                    concluirPedido(scan,pedido,sm);
                    return;
            }
        }
    }

    public static void addProduto(Scanner scan,Pedido pedido,Supermercado sm){
        String menu = "Escolha o produto: ";
        System.out.println(menu);
        listarProdutos(sm);
        int index = scan.nextInt();
        System.out.println("Digite a quantidade: ");
        int quantidade = scan.nextInt();
        pedido.addProduto(sm.getProdutos().get(index), quantidade);

    }

    private static void listarProdutos(Supermercado sm) {
        System.out.println("ID - NOME - VALOR");
        for(int i = 0; i < sm.getProdutos().size(); i++){
            Produto produto = sm.getProdutos().get(i);
            String prodLine = String.format("%d - %s - %.2f",i, produto.getNome(),produto.getPreco());
            System.out.println(prodLine);
        }
    }

    public static void concluirPedido(Scanner scan, Pedido pedido, Supermercado sm){
        String menu = "Selecione o cliente: ";
        System.out.println(menu);
        for (int i = 0; i < sm.getClientes().size(); i++) {
            Cliente cli = sm.getClientes().get(i);
            String cliLine = String.format("%d - %s",i,cli.getNome());
            System.out.println(cliLine);
        }
        int index = scan.nextInt();
        pedido.setCliente(sm.getClientes().get(index));
        menu = "Selecione o método de pagamento: \n"+
                "1- Dinheiro\n"+
                "2- Cartão\n"+
                "3- Cheque";
        System.out.println(menu);
        int opt = scan.nextInt();
        switch (opt){
            case 1: pedido.setPagamento(Pedido.TipoPagamento.DINHEIRO);
            break;
            case 2: pedido.setPagamento(Pedido.TipoPagamento.CARTAO);
            break;
            case  3: pedido.setPagamento(Pedido.TipoPagamento.CHEQUE);
            break;
        }
        sm.concluirPedido(pedido);
        System.out.println("O pedido foi realizado com sucesso! Detalhes: \n"+pedido);
    }

    public static void simularDesconto(Scanner scan, Supermercado sm){
        System.out.println("Escolha o produto para simular o desconto: ");
        listarProdutos(sm);
        int index = scan.nextInt();
        Produto produto = sm.getProdutos().get(index);
        System.out.println("Digite a quantidade: ");
        int quantidade = scan.nextInt();
        Item item = new Item(produto,quantidade);
        String out = String.format("O valor da quantidade %d do produto %s será de R$ %.2f",quantidade,produto.getNome(),item.calcularSubtotal());
        System.out.println(out);
    }
}
