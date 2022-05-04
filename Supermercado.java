import java.util.ArrayList;
import java.util.List;

public class Supermercado {

    private String nome;
    private List<Produto> produtos;
    private List<Cliente> clientes;
    private List<Pedido> pedidos;

    public Supermercado(String nome) {
        this.nome = nome;
    }

    public void addProduto(Produto prod){
        if(this.produtos == null){
            this.produtos = new ArrayList<>();
        }
        this.produtos.add(prod);
    }

    public void addCliente(Cliente cli){
        if(this.clientes == null){
            this.clientes = new ArrayList<>();
        }
        this.clientes.add(cli);
    }

    public void addPedido(Pedido ped){
        if(this.pedidos == null){
            this.pedidos = new ArrayList<>();
        }
        this.pedidos.add(ped);
    }

    /**
     * Conclui um pedido, diminuindo os valores dos produtos do estoque caso cadastrado.
     * @param ped Pedido a ser concluido
     */
    public void concluirPedido(Pedido ped){
        addPedido(ped);
        ped.getItens().forEach(item -> {
            int index = this.produtos.indexOf(item.getProduto());
            if(index != -1){
                produtos.get(index).subQuantidade(item.getQuantidade());
            }
        });
    }

    public String getNome() {
        return nome;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
}
