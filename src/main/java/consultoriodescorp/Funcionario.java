package consultoriodescorp;

import validador.ValidaEscolaridade;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author valeria
 */
@Entity
@Table(name = "TB_FUNCIONARIO")
@NamedQueries({
    @NamedQuery(
    name="TipoFuncionario",
    query="SELECT f FROM Funcionario f WHERE f.tipo=?1"        
    )}
)
@DiscriminatorValue(value = "F")
@PrimaryKeyJoinColumn(name = "ID_FUNCIONARIO",referencedColumnName ="ID_USUARIO")
public class Funcionario extends Usuario implements Serializable {
    public static final String FUNCIONARIO_POR_TIPO = "TipoFuncionario";
    
    @Enumerated(EnumType.STRING)
    @Column(name = "CL_TIPO_FUNCIONARIO")
    private TipoFuncionario tipo;
    @Column(name = "CL_ESCOLARIDADE")
    @ValidaEscolaridade
    @NotBlank
    private String escolaridade;
    @NotBlank
    @Column(name = "CL_ESPECIALIDADE")
    private String especialidade;

    public TipoFuncionario getTipo() {
        return tipo;
    }

    public void setTipo(TipoFuncionario tipo) {
        this.tipo = tipo;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
    

}
