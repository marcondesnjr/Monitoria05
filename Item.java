class Item {
    private Produto produto;
    private int quantidade;

    public Item(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public double calcularSubtotal() {
        double sub = this.produto.getPreco()*this.quantidade;
        if(this.quantidade > 50){
            sub *= 0.75;
        }else if(this.quantidade > 20){
            sub *= 0.80;
        }else if(this.quantidade > 10){
            sub *= 0.90;
        }
        return sub;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Item{" +
                "produto=" + produto +
                ", quantidade=" + quantidade +
                '}';

    }
}
