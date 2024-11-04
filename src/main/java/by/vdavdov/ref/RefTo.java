package by.vdavdov.ref;

import com.fasterxml.jackson.annotation.JsonIgnore;
import by.vdavdov.common.to.TitleTo;
import by.vdavdov.common.util.Util;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.lang.Nullable;

@Value
@EqualsAndHashCode(of = {"refType", "code"})
public class RefTo extends TitleTo {
    @NotNull
    RefType refType;
    @Nullable
    String aux;
    @Nullable
    @JsonIgnore
    String[] splittedAux;

    public RefTo(Long id, RefType refType, String code, String title, @Nullable String aux) {
        super(id, code, title);
        this.refType = refType;
        this.aux = aux;
        this.splittedAux = (aux != null && aux.contains("|")) ? aux.split("\\|") : null;
    }

    @JsonIgnore
    public String getAux(int idx) {
        return splittedAux == null || splittedAux.length <= idx ? null : splittedAux[idx];
    }

    @JsonIgnore
    public long getLongFromAux() {
        return Long.parseLong(Util.notNull(aux, "MAIL_NOTIFICATION {0} has no aux(mask)", this));
    }
}
