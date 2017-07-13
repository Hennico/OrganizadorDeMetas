package organizador

class Objetivo extends SubMeta {

    static constraints = {
    }

    public void CambiarEstado(Estado estado) {
		if (this.validarCambiarEstado(estado))
			this.estado == estado
	}

}
