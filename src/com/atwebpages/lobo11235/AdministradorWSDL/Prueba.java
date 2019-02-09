package com.atwebpages.lobo11235.AdministradorWSDL;


public class Prueba {

	public static void main(String[] args) {
		AdministradorWSDL_ServiceLocator a = new AdministradorWSDL_ServiceLocator();
		try {
			AdministradorWSDL_PortType b = a.getAdministradorWSDLSOAP();
			//Admin admin = new Admin(0,"pepe el sapo", "elsapo@gmail.com", "123455");
			//System.out.println(b.appendAdmin(admin));
			System.out.println(b.getAdminList()[2].getNombre());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
