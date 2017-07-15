package organizador

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class ObjetivoSpec extends Specification implements DomainUnitTest<Objetivo> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == true
    }
    
    void testPendienteFinalizada() {
		objetivo = new Tarea("Text", "Text")
		objetivo.estado = Estado.Pendiente
		tarea2 = new Tarea("Text", "Text")
		tarea2.estado = Estado.EnEjecucion
		objetivo.agregarSubMeta(tarea2, true)
		tarea2.CambiarEstado(Estado.Finalizado)
		
        objetivoestado == Estado.Finalizado
    }  
    
    void testPendienteFinalizadaConDosTareas() {
		objetivo = new Tarea("Text", "Text")
		objetivo.estado = Estado.Pendiente
		tarea2 = new Tarea("Text", "Text")
		tarea2.estado = Estado.EnEjecucion
		objetivo.agregarSubMeta(tarea2, true)
		tarea3 = new Tarea("Text", "Text")
		tarea3.estado = Estado.EnEjecucion
		objetivo.agregarSubMeta(tarea3, true)
		
		tarea2.CambiarEstado(Estado.Finalizado)
		
		objetivoestado == Estado.Pendiente
		
		tarea3.CambiarEstado(Estado.Finalizado)
		
        objetivoestado == Estado.Finalizado
    }  
    
    void testFinalizadaNoDejaNueavsSubTareas() {
		objetivo = new Tarea("Text", "Text")
		objetivo.estado = Estado.Finalizado
		
		
		tarea2 = new Tarea("Text", "Text")
		tarea2.estado = Estado.EnEjecucion
		
		def msg = shouldFail {
			objetivo.agregarSubMeta(tarea2, true)
		}
        assert 'No se puede agregar tarea obligatoria cuando ya se comenzo' == msg
		
		tarea3 = new Tarea("Text", "Text")
		tarea3.estado = Estado.EnEjecucion
		
		msg = shouldFail {
			objetivo.agregarSubMeta(tarea3, false)
		}
        assert 'No se puede agregar tarea obligatoria cuando ya se comenzo' == msg

    } 
}
