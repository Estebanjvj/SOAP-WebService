package prueba1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Admin extends com.atwebpages.lobo11235.AdministradorWSDL.Admin{
	/*@FXML
    private Button btedit;
    @FXML
    private Button btdelete;*/
    
    Admin(int idAdmin,
            java.lang.String nombre,
            java.lang.String correo,
            java.lang.String password) {
    		super(idAdmin, nombre, correo, password);
	    	//this.btedit = new Button("Editar");
	        //this.btdelete = new Button("Eliminar");
	        
	        /*this.btedit.setOnAction(e ->{
	        	FXMLDocumentController.updateAdmin(this);
	        });
	        
	        this.btdelete.setOnAction(e ->{
	        	FXMLDocumentController.deleteAdmin(this);
	        });*/
    }
    
    /*public javafx.scene.control.Button getBtedit(){
    	return this.btedit;
    }
    
    public void setBtedit(javafx.scene.control.Button btedit) {
    	this.btedit = btedit;
    }
    
    public javafx.scene.control.Button getBtdelete(){
    	return this.btdelete;
    }
    
    public void setBtdelete(javafx.scene.control.Button btdelete) {
    	this.btedit = btdelete;
    }*/
}
