package br.com.fiap.auth;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.fiap.model.Visitante;

public class AuthorizationListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent arg0) {
		String page = FacesContext.getCurrentInstance().getViewRoot().getViewId();
		
		if(page.equals("/login.xhtml") || page.equals("/register.xhtml")) {
			return;
		}
		
		//early return
		
		//se usuário nao está logado, vai para login
		Visitante visitante = (Visitante) FacesContext
			.getCurrentInstance()
			.getExternalContext()
			.getSessionMap()
			.get("nome");
		
		if (visitante != null) return;
	
		
		FacesContext
				.getCurrentInstance()
				.getApplication()
				.getNavigationHandler()
				.handleNavigation(
						FacesContext.getCurrentInstance(), null, "login.xhtml");
			
		
		
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
