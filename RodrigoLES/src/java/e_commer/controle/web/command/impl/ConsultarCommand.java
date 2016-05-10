
package e_commer.controle.web.command.impl;

import e_commer.core.aplicacao.Resultado;
import e_commer.dominio.EntidadeDominio;


public class ConsultarCommand extends AbstractCommand{

	
	public Resultado execute(EntidadeDominio entidade) {
		
		return fachada.consultar(entidade);
	}

}
