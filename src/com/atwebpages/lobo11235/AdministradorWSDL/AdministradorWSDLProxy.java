package com.atwebpages.lobo11235.AdministradorWSDL;

public class AdministradorWSDLProxy implements com.atwebpages.lobo11235.AdministradorWSDL.AdministradorWSDL_PortType {
  private String _endpoint = null;
  private com.atwebpages.lobo11235.AdministradorWSDL.AdministradorWSDL_PortType administradorWSDL_PortType = null;
  
  public AdministradorWSDLProxy() {
    _initAdministradorWSDLProxy();
  }
  
  public AdministradorWSDLProxy(String endpoint) {
    _endpoint = endpoint;
    _initAdministradorWSDLProxy();
  }
  
  private void _initAdministradorWSDLProxy() {
    try {
      administradorWSDL_PortType = (new com.atwebpages.lobo11235.AdministradorWSDL.AdministradorWSDL_ServiceLocator()).getAdministradorWSDLSOAP();
      if (administradorWSDL_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)administradorWSDL_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)administradorWSDL_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (administradorWSDL_PortType != null)
      ((javax.xml.rpc.Stub)administradorWSDL_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.atwebpages.lobo11235.AdministradorWSDL.AdministradorWSDL_PortType getAdministradorWSDL_PortType() {
    if (administradorWSDL_PortType == null)
      _initAdministradorWSDLProxy();
    return administradorWSDL_PortType;
  }
  
  public java.lang.String appendAdmin(com.atwebpages.lobo11235.AdministradorWSDL.Admin admin) throws java.rmi.RemoteException{
    if (administradorWSDL_PortType == null)
      _initAdministradorWSDLProxy();
    return administradorWSDL_PortType.appendAdmin(admin);
  }
  
  public java.lang.String editAdmin(com.atwebpages.lobo11235.AdministradorWSDL.Admin admin, java.lang.String correo) throws java.rmi.RemoteException{
    if (administradorWSDL_PortType == null)
      _initAdministradorWSDLProxy();
    return administradorWSDL_PortType.editAdmin(admin, correo);
  }
  
  public java.lang.String deleteAdmin(java.lang.String correo) throws java.rmi.RemoteException{
    if (administradorWSDL_PortType == null)
      _initAdministradorWSDLProxy();
    return administradorWSDL_PortType.deleteAdmin(correo);
  }
  
  public com.atwebpages.lobo11235.AdministradorWSDL.Admin[] getAdminList() throws java.rmi.RemoteException{
    if (administradorWSDL_PortType == null)
      _initAdministradorWSDLProxy();
    return administradorWSDL_PortType.getAdminList();
  }
  
  
}