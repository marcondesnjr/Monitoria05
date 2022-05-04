import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pedido {

    private List<Item> itens;
    private TipoPagamento pagamento;
    private Cliente cliente;
    private double total;

    public Pedido() {
    }

    public void addProduto(Produto prod, int quant){
        if(this.itens == null){
            this.itens = new ArrayList<>();
        }
        Item item = new Item(prod,quant);
        this.itens.add(item);
        total += item.calcularSubtotal();
    }

    public TipoPagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(TipoPagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Item> getItens() {
        return itens;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Double.compare(pedido.total, total) == 0 && Objects.equals(itens, pedido.itens) && pagamento == pedido.pagamento && cliente.equals(pedido.cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itens, pagamento, cliente, total);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "itens=" + itens +
                ", pagamento=" + pagamento +
                ", cliente=" + cliente +
                ", total=" + total +
                '}';
    }

    public enum TipoPagamento {DINHEIRO, CHEQUE, CARTAO}

}

