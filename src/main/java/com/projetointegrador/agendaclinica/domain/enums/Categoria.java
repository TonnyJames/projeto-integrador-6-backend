package com.projetointegrador.agendaclinica.domain.enums;

public enum Categoria {

    CARDIOLOGISTA(0, "ROLE_CARDIOLOGISTA"),
    CLINICO(1, "ROLE_CLINICO"),
    DERMATOLOGISTA(2, "ROLE_DERMATOLOGISTA"),
    GERIATRA(3, "ROLE_GERIATRA"),
    GINECOLOGISTA(4, "ROLE_GINECOLOGISTA"),
    OFTALMOLOGISTA(5, "ROLE_OFTALMOLOGISTA"),
    PEDIATRA(6, "ROLE_PEDIATRA"),
    PSIQUIATRA(7, "ROLE_PSIQUIATRA"),
    OTORRINOLARINGOLOGISTA(8, "ROLE_OTORRINOLARINGOLOGISTA"),
    ONCOLOGISTA(9, "ROLE_ONCOLOGISTA"),
    ORTOPEDISTA(10, "ROLE_ORTOPEDISTA");


    private Integer codigo;
    private String descricao;

    Categoria (Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Categoria toEnum(Integer cod){
        if (cod == null) {
            return null;
        }
        for (Categoria x : Categoria.values()) {
            if (cod.equals(x.getCodigo())){
                return x;
            }
        }
        throw new IllegalArgumentException("Categoria inválida");
    }
}
