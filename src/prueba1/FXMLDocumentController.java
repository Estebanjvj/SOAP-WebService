/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba1;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.axis.session.Session;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import com.atwebpages.lobo11235.AdministradorWSDL.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
//java mail api
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.Message;
/**
 *
 * @author zaory
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    TextField tfnombre, tfpasswod, tfcorreo, tfrepassword;
    @FXML
    TableView tvtable;
    @FXML
    TableColumn tcid, tcnombre, tcpassword, tccorreo, tceditar, tceliminar;
    @FXML
    Button btsave, btreset, btdelete;
    
    String saving = null;
    
    private final Properties properties = new Properties();
	
	private String password;
 
	private javax.mail.Session session;
            
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
		tcid.setCellValueFactory(new PropertyValueFactory<Admin, String>("idAdmin"));
		tcnombre.setCellValueFactory(new PropertyValueFactory<Admin, String>("nombre"));
		tccorreo.setCellValueFactory(new PropertyValueFactory<Admin, String>("correo"));
		tcpassword.setCellValueFactory(new PropertyValueFactory<Admin, String>("password"));

		tvtable.getItems().setAll(getAdmins());
		tvtable.setOnMousePressed(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				init();
			}
			private void init() {
				tvtable.setOnMouseClicked(me -> {
					if(me.getClickCount()== 2 && me.getButton().equals(MouseButton.PRIMARY)) {
						Admin cur = (Admin) tvtable.getSelectionModel().getSelectedItem();
						tfnombre.setText(cur.getNombre());
						tfcorreo.setText(cur.getCorreo());
						btdelete.setVisible(true);
						saving = cur.getCorreo();
					}
				});
			}
		});
    }
    
    /**
     * get from web services
     * @return
     */
    public static ObservableList<Admin> getAdmins() {
    	ObservableList<Admin> admins = FXCollections.observableArrayList();
    	AdministradorWSDL_ServiceLocator adminServerLocator = new AdministradorWSDL_ServiceLocator();
		try {
			AdministradorWSDL_PortType portType = adminServerLocator.getAdministradorWSDLSOAP();
			com.atwebpages.lobo11235.AdministradorWSDL.Admin[] lista = portType.getAdminList();
			for (com.atwebpages.lobo11235.AdministradorWSDL.Admin admin : lista) {
				Admin current = new Admin(admin.getIdAdmin(),admin.getNombre(),admin.getCorreo(),admin.getPassword());
				admins.add(current);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return admins;
    }
    
    @FXML
    private void clearFields(ActionEvent event) {
    	tfcorreo.setText("");
    	tfnombre.setText("");
    	tfpasswod.setText("");
    	tfrepassword.setText("");
    	btdelete.setVisible(false);
    }
    
    /**
     * Add or Update to web service
     * @param event
     */
    @FXML
    private void addAdmin(ActionEvent event) {
    	AdministradorWSDL_ServiceLocator adminServerLocator = new AdministradorWSDL_ServiceLocator();
    	try {
			AdministradorWSDL_PortType portType = adminServerLocator.getAdministradorWSDLSOAP();
			if(tfpasswod.getText().length()>0 && tfpasswod.getText().compareTo(tfrepassword.getText())==0)
			{
				Admin admin = new Admin(0,tfnombre.getText(), tfcorreo.getText(), tfpasswod.getText());
				if(saving != null) { /* hoy toca actualizar */
					String respuesta = portType.editAdmin(admin, saving);
					if(respuesta.compareTo("sucessfull")==0) {
						String mensaje="<html><body><img src=\"http://lobo11235.atwebpages.com/Lobos-do-Mar.ico\" alt=\"Website Icon\" /><h1>Bienvenido a LOBO</h1>Estimado usuario, sus datos han sido cambiados, sus nuevos datos son los siguientes:<br><b>Usuario:</b> "+tfcorreo.getText()+"<br><b>contraseña:</b> "+tfpasswod.getText()+"</body></html>";
						infoAlert("Edición", "Editar administrador", tfnombre.getText()+" ha sido editado satisfactoriamente", AlertType.INFORMATION);
						sendEmail(tfcorreo.getText(),mensaje,"Actualización de información: LOBO");
						tvtable.getItems().setAll(getAdmins());
						tfcorreo.setText("");
				    	tfnombre.setText("");
				    	tfpasswod.setText("");
				    	tfrepassword.setText("");
				    	btdelete.setVisible(false);
					}
					else
						infoAlert("Alerta", "Ha ocurrido un error", respuesta, AlertType.ERROR);
				}
				else { /* hoy toca agregar */
					String mensaje="<html><body><img src=\"http://lobo11235.atwebpages.com/Lobos-do-Mar.ico\" alt=\"Website Change Request\" /><h1>Bienvenido a LOBO</h1>Bienvenido al sistema, <a href=\"http://lobo11235.atwebpages.com/pages/loginAd.php\">Aquí</a> podrá editar la información y ver el progreso de la campaña actual con los siguientes datos:<br><b>Usuario:</b> "+tfcorreo.getText()+"<br><b>contraseña:</b> "+tfpasswod.getText()+"</body></html>";
					String respuesta = portType.appendAdmin(admin);
					if(respuesta.compareTo("sucessfull")==0) {
						infoAlert("Agregado", "Nuevo administrador", tfnombre.getText()+" ha sido agregado satisfactoriamente", AlertType.INFORMATION);
						sendEmail(tfcorreo.getText(),mensaje,"Bienvenido a LOBO");
						tvtable.getItems().setAll(getAdmins());
						tfcorreo.setText("");
				    	tfnombre.setText("");
				    	tfpasswod.setText("");
				    	tfrepassword.setText("");
				    	btdelete.setVisible(false);
					}
					else
						infoAlert("Alerta", "Ha ocurrido un error", respuesta, AlertType.ERROR);
				}
				saving = null;
			} else {
				infoAlert("Advertencia", "Error de contraseñas", "Las contraseñas deben coincidir y tener al menos un caracter.", AlertType.ERROR);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//System.out.println("add");
    }
    
    @FXML
    private void deleteAdmin(ActionEvent event) {
    	if(confirmDialog("Confirmar", "Eliminar administrador", "¿Desea eliminar este administrador?", AlertType.CONFIRMATION)) {
    		AdministradorWSDL_ServiceLocator adminServerLocator = new AdministradorWSDL_ServiceLocator();
        	try {
    			AdministradorWSDL_PortType portType = adminServerLocator.getAdministradorWSDLSOAP();
    			String respuesta = portType.deleteAdmin(saving);
    			if(respuesta.compareTo("sucessfull")==0) {
    				infoAlert("Eliminado", "Eliminar administrador", "se ha eliminado", AlertType.INFORMATION);
    				String mensaje="<html><body><img src=\"http://lobo11235.atwebpages.com/Lobos-do-Mar.ico\" alt=\"Website Icon\" /><h1>Bienvenido a LOBO</h1>Estimado usuario, lamentablemente hemos decidido cerrar esta cuenta, si presenta algún inconveniente, por favor contacte a su correspondiente administrador de la aplicación LOBO</body></html>";
					sendEmail(tfcorreo.getText(),mensaje,"Mensaje de LOBO");
    				tvtable.getItems().setAll(getAdmins());
    				tfcorreo.setText("");
    		    	tfnombre.setText("");
    		    	tfpasswod.setText("");
    		    	tfrepassword.setText("");
    		    	btdelete.setVisible(false);
    			}
    			else
    				infoAlert("Alerta", "Ha ocurrido un error", respuesta, AlertType.ERROR);
    			saving = null;
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    }
    
    private static void infoAlert(String title, String header, String body, AlertType tipo) {
    	Alert alert = new Alert(tipo);
    	alert.setTitle(title);
    	alert.setHeaderText(header);
    	alert.setContentText(body);

    	alert.showAndWait();
    }
    
    private boolean confirmDialog(String title, String header, String body, AlertType tipo) {
    	Alert alert = new Alert(tipo);
    	alert.setTitle(title);
    	alert.setHeaderText(header);
    	alert.setContentText(body);

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    	    return true;
    	} else {
    	    return false;
    	}
    }
 
	public void sendEmail(String email, String me, String asunto){
		String fromEmail = "imnotstupid@something.xd";
		String fromPassword = "YourDamnPassword";
		Properties props = new Properties();
		try{
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.auth", "true");
            props.put("mail.debug", "true");
            props.put ("mail.smtp.port", "465");
            props.put("mail.store.protocol", "pop3");
            props.put("mail.transport.protocol", "smtp");

            session = javax.mail.Session.getDefaultInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, fromPassword);
                }
            });

            if(session != null){
            	
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(fromEmail));
                message.setSubject(asunto);
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse((String)email));
                message.setContent(me, "text/html; charset=utf-8");
                Transport.send(message);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
	}
}
