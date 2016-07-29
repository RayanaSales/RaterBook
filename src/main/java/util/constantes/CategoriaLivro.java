package util.constantes;

/**
 *
 * @author douglas
 */
public enum CategoriaLivro {
    CIENCIA("Ciência"),
    ASTRONOMIA("Astronomia"),
    ESOTERISMO("Esoterismo"),
    FANTASIA("Fantasia"),
    FILOSOFIA("Filosfia"),
    GUERRA("Guerra"),
    HISTORIA("História"),
    LINGUISTICA("Lingüistica"),
    ESTRANGEIRA("Lit. Estrangeira"),
    INFANTO_JUVENIL("Lit. Infanto-Juvenil"),
    NACIONAL("Nacional"),
    MEDICINA("Medicina"),
    MUSICA("Música"),
    POLITICA("Política"),
    PSICOLOGIA("Psicologia"),
    ROMANCE("Romance"),
    QUADRINHOS("Quadrinhos"),
    RELIGIAO("Religião"),
    SAUDE("Saúde"),
    TERROR("Terror"),
    VESTIBULAR("Vestibular");

    private final String label;

    private CategoriaLivro(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
