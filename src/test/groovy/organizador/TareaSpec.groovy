package organizador

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class TareaSpec extends Specification implements DomainUnitTest<Tarea> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
    
    void testCambioEstadoPendienteAEnEjecucion() {
		def tarea = new tarea()
		tarea.estado = EnumEstado.Pendiente
		tarea.CambiarEstado(EnumEstado.EnEjecucion)
		
        tarea.estado == EnumEstado.EnEjecucion
    }
 
    void testCambioEstadoPendienteAEnEjecucionConTareasPendientes() {
		def tarea = new tarea()
		def tarea2 = new tarea()
 		tarea.estado = EnumEstado.Pendiente
 		tarea.AgergarSubMeta(tara2, true)
		tarea.CambiarEstado(EnumEstado.EnEjecucion)
		
		expect:"CambioEstadoInvalido"
			true == false
    }
    
    void testCambioEstadoEnEjecucionAEnEjecucion() {
		def tarea = new tarea()
		tarea.estado = EnumEstado.EnEjecucion
		tarea.CambiarEstado(EnumEstado.EnEjecucion)
		
		expect:"CambioEstadoInvalido"
			true == false
    }
    
    void testCambioEstadoFinalizadaAEnEjecucion() {
		def tarea = new tarea()
		tarea.estado = EnumEstado.Finalizada
		tarea.CambiarEstado(EnumEstado.EnEjecucion)
		
		expect:"CambioEstadoInvalido"
			true == false
    }
    
}
