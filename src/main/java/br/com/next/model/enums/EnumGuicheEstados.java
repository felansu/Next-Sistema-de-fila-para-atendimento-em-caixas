package br.com.next.model.enums;

public enum EnumGuicheEstados {
    DISPONIVEL(0),
    OCUPADO(1),
    FECHADO(2);

    private final int codigo;

    EnumGuicheEstados(int codigo) {
        this.codigo = codigo;
    }

    public static EnumGuicheEstados getTipo(Integer codigo) {
        if (codigo == null)
            return null;
        for (EnumGuicheEstados estado : EnumGuicheEstados.values())
            if (codigo.equals(estado.getCodigo()))
                return estado;
        throw new IllegalArgumentException("Não foi achado um tipo de estado com o código '" + codigo + "'.");
    }

    public int getCodigo() {
        return codigo;
    }
}