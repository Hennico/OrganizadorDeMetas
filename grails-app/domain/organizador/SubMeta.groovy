package organizador

class SubMeta {
	public Estado estado;
	public List<SubMeta> subMetasOpocionales;
	public List<SubMeta> subMetasObligatorias;
	public List<Objetivo> listeners;
	public String nombre;
	public String descripcion;

	public SubMeta (String nombre, String descripcion) {
		subMetasOpocionales = new ArrayList<SubMeta>();
		subMetasObligatorias = new ArrayList<SubMeta>();
		listeners = new ArrayList<Objetivo>();

		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public void agregarSubMeta(SubMeta subMeta, boolean obligatorio) {
		if (estado == Estado.Cancelado || estado == Estado.Terminado) {
			throw new Exception("No se puede agregar tarea portque esta finalizada/cancelada");
		}
		if (estado != Estado.Pendiente && obligatorio) {
			throw new Exception("No se puede agregar tarea obligatoria cuando ya se comenzo");
		}

		if (obligatorio) {
			subMetasObligatorias.add(subMeta);
		} else {
			subMetasOpocionales.add(subMeta);
		}
	}

	public void agregarSubMetaObligatoria(SubMeta subMetao) {
		this.agregarSubMeta(subMeta, true)
	}

	public void agregarSubMetaOpcional(SubMeta subMetao) {
		this.agregarSubMeta(subMeta, false)
	}

	protected void informChange() {
		for(Objetivo listener : listeners) {
			listener.notify();
		}
	}

	protected void agragarListener(Objetivo listener) {
		listeners.add(listener)
	}

	protected boolean validarCambiarEstado(Estado nuevoEstado) {
		switch(nuevoEstado) {
			case Estado.Cancelado:
				return estado != Estado.Termiando;

			case Estado.Termiando:
				if (estado != Estado.EnEjecucion) return false;
				for(SubMeta opcional : subMetasOpocionales) {
					if (opcional.estado != Estado.Finalizado || opcional.estado != Estado.Cancelado)
						return false;
				}
				return true;

			case Estado.EnEjecucion:
				if (estado != Estado.Pendiente) return false;
				for(SubMeta opcional : subMetasObligatorias) {
					if (opcional.estado != Estado.Finalizado || opcional.estado != Estado.Cancelado)
						return false;
				}
				return true;

			case Estado.Pendiente:
				return false;
		}
		throw new Exception("El estado que se utilizo no es valido");
	}

    static constraints = {
    }
}
