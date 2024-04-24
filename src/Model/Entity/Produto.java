package Model.Entity;

public class Produto {
    private Integer idProduto, CategoriaidCategoria;
    private String nomeProduto;

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getCategoriaidCategoria() {
        return CategoriaidCategoria;
    }

    public void setCategoriaidCategoria(Integer categoriaidCategoria) {
        CategoriaidCategoria = categoriaidCategoria;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

}
