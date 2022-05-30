package br.com.fiap.bean;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.primefaces.model.file.UploadedFile;

import br.com.fiap.dao.VisitanteDao;
import br.com.fiap.model.Visitante;

@Named
@RequestScoped
public class VisitanteBean {

	private Visitante visitante = new Visitante();
	
	private VisitanteDao visitanteDao = new VisitanteDao();
	
	public void save1()  {

		visitanteDao.create(getVisitante());
		
		FacesContext
			.getCurrentInstance()
			.addMessage(null, new FacesMessage("Usuário cadastrado com sucesso"));
		
	}
	
	public List<Visitante> getList() {
		return visitanteDao.listAll();
	}
	
	public String cadastro(){
		if (visitanteDao.exist(visitante)) {
			//salvar o usuario logado na secao
			FacesContext
				.getCurrentInstance()
				.getExternalContext()
				.getSessionMap()
				.put("nome", visitante);
			
			return "setups";
		}
		
		FacesContext
			.getCurrentInstance()
			.getExternalContext()
			.getFlash()
			.setKeepMessages(true);
		
		FacesContext
			.getCurrentInstance()
			.addMessage(null, new FacesMessage("Login inválido"));

		return "login?faces-redirect=true";
	}
	


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
	
	@Named
	@RequestScoped

		private Visitante visitante1 = new Visitante();
		private List<Visitante> list;
		private UploadedFile image;
		
		private VisitanteDao visitanteDao1 = new VisitanteDao();
		
		public VisitanteBean() {
			list = this.list();
		}
		
		public String save() throws IOException {
			System.out.println(this.visitante);
			
			System.out.println(image.getFileName()); 
			
			ServletContext servletContext = (ServletContext) FacesContext
																.getCurrentInstance()
																.getExternalContext()
																.getContext();
			String servletPath = servletContext.getRealPath("/");
			
			System.err.println(servletPath);
			
			FileOutputStream out = 
					new FileOutputStream(servletPath + "\\images\\" + image.getFileName());
			out.write(image.getContent());
			out.close();
			
			visitante.setImagePath("\\images\\" + image.getFileName());
			
			visitanteDao.create(visitante);
			
			FacesContext
				.getCurrentInstance()
				.addMessage(null, new FacesMessage("Visitante cadastrado com sucesso"));
			
			return "setups";
		}
			
		public List<Visitante> list() {
			return visitanteDao.listAll();
		}
		
		public String delete(Visitante visitante) {
			visitanteDao.remove(visitante);
			
			FacesContext
				.getCurrentInstance()
				.getExternalContext() 
				.getFlash()
				.setKeepMessages(true);
			
			FacesContext
				.getCurrentInstance()
				.addMessage(null, new FacesMessage("Visitante apagado com sucesso"));
			
			return "setups?faces-redirect=true";

		}
		
		public void edit() {
			visitanteDao.update(visitante);
			
			FacesContext
				.getCurrentInstance()
				.addMessage(null, new FacesMessage("Setup atualizado com sucesso"));
		}

		public List<Visitante> getList1() {
			return list;
		}

		public void setList(List<Visitante> list) {
			this.list = list;
		}

		public Visitante getVisitante() {
			return visitante;
		}

		public void setVisitante(Visitante visitante) {
			this.visitante = visitante;
		}

		public UploadedFile getImage() {
			return image;
		}

		public void setImage(UploadedFile image) {
			this.image = image;
		}

		public VisitanteDao getVisitanteDao1() {
			return visitanteDao1;
		}

		public void setVisitanteDao1(VisitanteDao visitanteDao1) {
			this.visitanteDao1 = visitanteDao1;
		}



}
