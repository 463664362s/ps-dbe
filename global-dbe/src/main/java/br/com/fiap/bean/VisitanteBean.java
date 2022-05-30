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
	private List<Visitante> list;
	private UploadedFile image;
	
	private VisitanteDao visitanteDao = new VisitanteDao();
	
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
		
		return "Visitantes";
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
		
		return "visitante?faces-redirect=true";

	}
	
	public void edit() {
		visitanteDao.update(visitante);
		
		FacesContext
			.getCurrentInstance()
			.addMessage(null, new FacesMessage("Visitante atualizado com sucesso"));
	}

	public List<Visitante> getList() {
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

}
