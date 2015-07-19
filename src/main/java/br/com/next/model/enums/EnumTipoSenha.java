package br.com.next.model.enums;

public enum EnumTipoSenha {
    COMUM(0),
    PREFERENCIAL(1);

    private final int codigo;

    EnumTipoSenha(int codigo) {
        this.codigo = codigo;
    }

    public static EnumTipoSenha getTipo(Integer codigo) {
        if (codigo == null)
            return null;
        for (EnumTipoSenha estado : EnumTipoSenha.values())
            if (codigo.equals(estado.getCodigo()))
                return estado;
        throw new IllegalArgumentException("Não foi achado um tipo de estado com o código '" + codigo + "'.");
    }

    public int getCodigo() {
        return codigo;
    }
}